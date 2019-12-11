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
    totalData = None
    tList= list(tList)
    modelId = 8
    cMap = {}
    for tIndex in range(len(tList)):
        t = tList[tIndex]
        if totalTasks is not None:
            print('任务总数:%s,当前任务:%s,总数:%s,当前:%s'%(totalTasks,taksId,len(tList),tIndex))
        maxmins=None
        if ouPeiMap.__contains__(t[1]):
            cMap = getCondiMap(cMap,database,t[1],modelId,ouPeiMap[t[1]])
            if totalData is None:
                totalData = getTotalData(t[3])
            maxmins = getMaxMins(database,cMap,modelId,totalData, t[1], t[2], t[3], t[4], t[5])
        if maxmins is None:
            maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0 ,0 ,0, 0, 0, 0,-1,-1,-1]
        maxmins.append(t[0])
        DB.updatePrediction(database,tuple(maxmins))


# 1 2 3 表示胜 平 负
def getCondiMap(cMap,database,pankou,modelId,oupeiArr):
    if cMap.__contains__(str(pankou)+'_1'):
        return cMap
    for i in range(3):
        # minPercent,count = DB.queryAvgPercent(database, pankou,i+1,modelId)[0]
        # if minPercent is None:
        #     cMap[str(pankou) + '_' + str(i + 1)]=[]
        #     continue
        # while count>500:
        #     minPercent, count = DB.queryAvgPercent(database, pankou, i + 1, modelId,minPercent)[0]
        colArr = DB.queryConditionByPankouAndType(database, pankou,i+1,modelId,0)
        comDict = {}
        for colName, colVal, conditionSql, resType,percent,total_count,pcount,comIndex in colArr:
            tempArr=[conditionSql,colName,percent,total_count,pcount]
            condiArr = []
            if comDict.__contains__(comIndex):
                condiArr = comDict[comIndex]
            condiArr.append(tempArr)
            comDict[comIndex] = condiArr
        cMap[str(pankou)+'_'+str(i+1)]=comDict
    return cMap

def getTotalData(bisaishijian):
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='sports', charset="utf8")
    d3 = datetime.date(year=bisaishijian.year, month=bisaishijian.month, day=1 if bisaishijian.day<16 else 16)
    orginSql = 'SELECT * FROM football_data WHERE 1=1  AND bisaishijian<"%s" AND zhubifen is not null ' % ( '2019-09-01')
    dataByPk = pd.read_sql(sql=orginSql, con=conn)
    conn.close()
    return dataByPk


def getMaxMins(database,condiMap,modelId,totalData,pankou,bisaileixing,bisaishijian,zhudui,kedui):
    if len(condiMap[str(pankou)+'_1']) + len(condiMap[str(pankou)+'_2']) +len(condiMap[str(pankou)+'_3'])==0:
        return
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='sports', charset="utf8")
    sql = "SELECT * FROM football_data where bisaishijian='%s' and bisaileixing='%s' and zhudui='%s' and kedui='%s'"%(bisaishijian,bisaileixing,zhudui,kedui)
    dataByPk = pd.read_sql(sql=sql, con=conn)
    conn.close()
    if len(dataByPk) == 0:
        return

    maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0,
               -1, -1, -1]
    colsCount=[0,0,0]
    totalCount = [0,0,0]
    perCount = [0,0,0]
    percents = [[],[],[]]
    for rType in range(1,4):
        comDict = condiMap[str(pankou)+'_'+str(rType)]
        condset = set()
        # comIndexs = DB.queryAllComIndex(database,pankou,rType,modelId)
        for comIndex in comDict.keys():
            # comIndex = comIndex[0]
            conArr = comDict[comIndex]
            # conArr = DB.queryCondByPkAndComIndex(database,pankou,rType,modelId,comIndex)
            for cs in conArr:
                constr = cs[0]
                screenedData = eval('dataByPk[%s]' % (constr))
                if len(screenedData) == 0:
                    continue
                else:
                    colnames = cs[1].split(',')
                    for col in colnames:
                        condset.add(col.split('_')[0][:-1])
                    h= rType-1
                    maxmins[10+h] +=1
                    # maxmins[13 + h] +=cs[3]
                    totalCount[h]+=cs[3]
                    perCount[h]+=cs[4]
                    percents[h].append(cs[2])
                    maxmins[2 * h] = maxmins[2 * h] if cs[2] <= maxmins[2 * h] else cs[2]
                    if maxmins[2 * h + 1] == -1:
                        maxmins[2 * h + 1] = cs[2]
                    else:
                        maxmins[2 * h + 1] = maxmins[2 * h + 1] if cs[2] >= maxmins[
                            2 * h + 1] else cs[2]
                    break
        maxmins[12 + rType] = len(condset)
    for m in range(3):
        # tAvg = 0 if len(percents[h])==0 else sum(percents[h])/len(percents[h])
        tAvg = -1 if totalCount[m]==0 else perCount[m]/totalCount[m]
        maxmins[16+m] = tAvg
    print("比赛类型:%s,采集时间:%s,主队:%s,客队:%s,胜:%s,平:%s,负:%s,胜level:%s,平level:%s,负level:%s" %
          (bisaileixing, bisaishijian, zhudui, kedui, maxmins[10],maxmins[11], maxmins[12],maxmins[13],maxmins[14],maxmins[15]))
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
        # predict(database, ouPeiMap, bi,bIndex,len(b))
    myThreads.wait()

if __name__ == '__main__':
   runAll()
