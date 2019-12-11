import pandas as pd
import pymysql
import database as DB
from itertools import combinations
from myProcessPools import MyProcessPool
import math
from pandasRule import getPvMap
import datetime



def predict(database,ouPeiMap={},tList=[],taksId=None,totalTasks=None):
    if len(tList)==0:
        tList = DB.queryNotFirstAna(database)

    if len(tList)== 0:
        return
    if ouPeiMap=={}:
        ouPeiMap = getPvMap(database)

    smallModelId=6
    bigModelId=7
    lastShijian = ''
    totalData = None
    tList= list(tList)
    for modelId in range(bigModelId,smallModelId,-1):
        conditionMap = {}
        copyTList = tList[:]
        for tIndex in range(len(copyTList)):
            t = copyTList[tIndex]
            print('任务总数:%s,当前任务:%s,总数:%s,当前:%s'%(totalTasks,taksId,len(copyTList),tIndex))
            maxmins=None
            if ouPeiMap.__contains__(t[1]):
                if t[3]!=lastShijian:
                    totalData = getTotalData(t[3])
                    lastShijian = t[3]
                conditionMap=getCondiMap(conditionMap,database,t[1],modelId,ouPeiMap[t[1]])
                maxmins = getMaxMins(totalData,conditionMap, t[1], t[2], t[3], t[4], t[5])
            if maxmins is None:
                if modelId == smallModelId+1:
                    maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0 ,0 ,0, 0, 0, 0,-1,-1,-1]
                    maxmins.append(t[0])
                    DB.updatePrediction(database, tuple(maxmins))
                    tList.remove(t)
            else:
                maxmins.append(t[0])
                DB.updatePrediction(database,tuple(maxmins))
                tList.remove(t)

# 1 2 3 表示胜 平 负
def getCondiMap(cMap,database,pankou,modelId,oupeiArr):
    minQw=1.25
    if cMap.__contains__(str(pankou)+'_1'):
        return cMap
    for i in range(3):
        minPercent = minQw/(oupeiArr[i]-0.05)
        colArr = DB.queryConditionByPankouAndType(database, pankou,i+1,modelId,minPercent)
        condiArr = []
        for colName, colVal, conditionSql, resType,percent,total_count in colArr:
            tempArr=[conditionSql,colName,percent,total_count]
            condiArr.append(tempArr)
        cMap[str(pankou)+'_'+str(i+1)]=condiArr
    return cMap

def getTotalData(bisaishijian):
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='sports', charset="utf8")
    d3 = datetime.date(year=bisaishijian.year, month=bisaishijian.month, day=1 if bisaishijian.day<16 else 16)
    orginSql = 'SELECT * FROM football_data WHERE 1=1  AND bisaishijian<"%s" AND zhubifen is not null ' % ( d3)
    dataByPk = pd.read_sql(sql=orginSql, con=conn)
    conn.close()
    return dataByPk

def getMaxMins(totalData,conditionMap,pankou,bisaileixing,bisaishijian,zhudui,kedui):
    if len(conditionMap[str(pankou)+'_1'])+len(conditionMap[str(pankou)+'_2'])+len(conditionMap[str(pankou)+'_3']) == 0:
        return
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='sports', charset="utf8")
    sql = "SELECT * FROM football_data where bisaishijian='%s' and bisaileixing='%s' and zhudui='%s' and kedui='%s'"%(bisaishijian,bisaileixing,zhudui,kedui)
    dataByPk = pd.read_sql(sql=sql, con=conn)
    conn.close()
    if len(dataByPk) == 0:
        return
    screenedCond=[[],[],[]]
    colconArr = [[],[],[]]
    colsCount=[set(),set(),set()]

    for rType in range(1,4):
        conditionArr = conditionMap[str(pankou)+'_'+str(rType)]

        lastPercent = 0
        lastCount = 0
        lastColName1=''
        lastColName2=''
        for cs in conditionArr:
            constr = cs[0]
            screenedData = eval('dataByPk[%s]' % (constr))
            if len(screenedData) == 0:
                continue
            # 可能遇到强相关条件,选择跳过
            if lastPercent == cs[2] and lastCount == cs[3] and lastColName1 == cs[1].split(',')[0] and lastColName2 == cs[1].split(',')[1]:
                continue
            lastPercent = cs[2]
            lastCount = cs[3]
            lastColName1 = cs[1].split(',')[0]
            lastColName2 = cs[1].split(',')[1]

            screenedCond[rType-1].append(constr)
            colnames = cs[1]
            colconArr[rType-1].append(colnames.split(','))

            cSet = set(colnames.split(','))
            colsCount[rType-1]=colsCount[rType-1].union(cSet)
            # 超过200个条件就跳过
            if len(screenedCond[rType-1])>=200:
                break

    sqlArrLen = 0
    for h in screenedCond:
        sqlArrLen += len(h)
    if sqlArrLen==0:
        return
    # 将所有可能符合的条件组合起来
    combinArr = [[], [], []]
    colComArr = [[],[],[]]
    for k in range(len(screenedCond)):
        for m in range(3,0,-1):
            if len(screenedCond[k]) < m:
                combinArr[k].append([])
                colComArr[k].append([])
            else:
                combinArr[k].append(list(combinations(screenedCond[k], m )))
                colComArr[k].append(list(combinations(colconArr[k], m )))
    maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,len(colsCount[0]),len(colsCount[1]),len(colsCount[2]),0,0,0,-1,-1,-1]
    maxPreNum = 4950  # 最大预测条数
    # 分为3类
    for h in range(len(combinArr)):
        temArrs = combinArr[h]
        for level in range(len(temArrs)):
            checkedCols = []
            cSqlArr = temArrs[level]
            avgs = []
            # 多个约束条件已经生成结果,不再使用低级结果
            if maxmins[2*h]>0:
                break
            curNum = 0
            # step = math.floor(len(cSqlArr)/maxPreNum)
            # if step<1: step=1
            # b = [cSqlArr[h:h + step] for h in range(0, len(cSqlArr), step)]
            # for bi in b:
            #     cArr=bi[0]
            for cIndex in range(len(cSqlArr)):
                cArr = cSqlArr[cIndex]
                tcolsArr = colComArr[h][level][cIndex]
                tcols = []
                for y in tcolsArr:
                    tcols+=y
                tcols = list(set(tcols))
                tcols.sort()
                tcols = ','.join(tcols)
                if tcols in checkedCols:
                    continue
                checkedCols.append(tcols)
                if curNum > maxPreNum:
                    print(('组合个数超过%s个,选择跳过') % (maxPreNum))
                    break
                curNum += 1
                totalCount, pCount = queryCount(totalData,pankou, cArr, h, level,bisaishijian)
                if totalCount is None:
                    continue
                avgs.append(pCount / totalCount)
                maxmins[2 * h] = maxmins[2 * h] if pCount / totalCount <= maxmins[2 * h] else pCount / totalCount
                if maxmins[2 * h + 1] == -1:
                    maxmins[2 * h + 1] = pCount / totalCount
                else:
                    maxmins[2 * h + 1] = maxmins[2 * h + 1] if pCount / totalCount >= maxmins[
                        2 * h + 1] else pCount / totalCount
                maxmins[13+h]=len(temArrs)-level
            if len(avgs)>0:
                maxmins[16+h]=sum(avgs)/len(avgs)
    print("比赛类型:%s,采集时间:%s,主队:%s,客队:%s,胜:%s,平:%s,负:%s,胜level:%s,平level:%s,负level:%s" %
          (bisaileixing, bisaishijian, zhudui, kedui, len(colsCount[0]), len(colsCount[1]), len(colsCount[2]),maxmins[13],maxmins[14],maxmins[15]))
    return maxmins
# [总盘数，胜盘数，平盘数，负盘数，上盘盘数，下盘盘数]
def queryCount(totalData,pankou,conditionStrs,index,level,bisaishijian):
    dataByPk= totalData[totalData['pankou']==pankou]
    constr = ''
    for i in conditionStrs:
        constr = constr + i + '&'
    constr = constr[:-1]
    screenedData = eval('dataByPk[%s]' % (constr))
    totalCount = len(screenedData)
    d1 = datetime.datetime.strptime('2019-09-01', '%Y-%m-%d').date()
    d2 = bisaishijian
    numYuzhi = (d2.month-d1.month)*15*2
    if d2.day>15:
        numYuzhi+=15
    numYuzhi = numYuzhi/210
    # numYuzhi=0
    if totalCount<(40+level*5)*(1+numYuzhi):
        return None,None
    pCount=0
    if index == 0:
        pCount = len(screenedData[screenedData['zhubifen'] > screenedData['kebifen']])
    elif index == 1:
        pCount = len(screenedData[screenedData['zhubifen'] == screenedData['kebifen']])
    elif index ==2:
        pCount = len(screenedData[screenedData['zhubifen'] < screenedData['kebifen']])

    return totalCount,pCount


def runAll():
    database = DB.Database('localhost', 'root', 'root', 'sports')
    ouPeiMap = getPvMap(database)
    maxPools = 3
    myThreads = MyProcessPool(maxPools)
    tList = DB.queryNotFirstAna(database)
    step = math.ceil(len(tList) / (maxPools * 6))
    b = [tList[h:h + step] for h in range(0, len(tList), step)]
    for bIndex in range(len(b)):
        bi = b[bIndex]
        myThreads.submit(predict, database, ouPeiMap, bi,bIndex,len(b))
        # predicts(database, ouPeiMap, bi,bIndex,len(b))
    myThreads.wait()

if __name__ == '__main__':
    runAll()
