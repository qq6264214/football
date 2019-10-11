import database as DB
from itertools import combinations
from myThreadPools import MyThreadPools
from changeUtils import point2Arr,point3Arr,point4Arr
#列名,值
colsMap = {}
#sql
conditionsMap = {}
#类别
typesMap = {}

def firstAna(database):
    tList = DB.queryNotFirstAna(database)
    for t in tList:
        pankou = t[1]
        maxmins = getMaxMins(database,pankou,pankou,t[2],t[3],t[4],t[5])
        # 暂时只返回max值,min值,总盘数暂时不考虑
        if maxmins is None:
            maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
        maxmins.append(t[0])
        DB.updatePrediction(database,tuple(maxmins))

def linchangAna(database):
    tList = DB.queryNotLinchangAna(database)
    for t in tList:
        pankou = t[1]
        maxmins = getMaxMins(database, pankou, t[6], t[2], t[3], t[4], t[5])
        # 暂时只返回max值,min值,总盘数暂时不考虑
        if maxmins is None:
            maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
        maxmins.append(t[0])
        DB.updateLinchangPrediction(database, tuple(maxmins))
    DB.updateNotNeedLinchang(database)


def wrapPankouKey(pankou,linchangpankou):
    return str(pankou) + '_' + str(linchangpankou)


def getMaxMins(database,pankou,linchangpankou,bisaileixing,bisaishijian,zhudui,kedui):
    global colsMap, conditionsMap,typesMap
    pankouKey = wrapPankouKey(pankou,linchangpankou)
    tmap = {}
    conditiontMap = {}
    rTypeMap = {}
    if not colsMap.__contains__(pankouKey):
        colArr = DB.queryColNameAndValByPankou(database, pankou, linchangpankou,2)
        for colName, colVal, conditionSql,resType in colArr:
            valArr = []
            conditionSqlArr = []
            resTypeArr = []
            if tmap.__contains__(colName):
                valArr = tmap[colName]
                conditionSqlArr = conditiontMap[colName]
                resTypeArr = rTypeMap[colName]
            valArr.append(colVal)
            conditionSqlArr.append(conditionSql)
            resTypeArr.append(resType)

            tmap[colName] = valArr
            conditiontMap[colName] = conditionSqlArr
            rTypeMap[colName] = resTypeArr


        colsMap[pankouKey] = tmap
        conditionsMap[pankouKey] = conditiontMap
        typesMap[pankouKey] = rTypeMap

    else:
        tmap = colsMap[pankouKey]
        conditiontMap = conditionsMap[pankouKey]
        rTypeMap = typesMap[pankouKey]
    if len(tmap) == 0:
        return
    keys = tmap.keys()
    sqlCols = ''
    keyarr = []
    for i in keys:
        i = i[:-1]
        str = i+'1,'+i+'2,'+i+'3'

        sqlCols = sqlCols + str + ','
        keyarr.append(i + '1')
        keyarr.append(i + '2')
        keyarr.append(i + '3')

    sqlCols = sqlCols[0:len(sqlCols) - 1]
    sqlParam = [bisaileixing,bisaishijian,zhudui,kedui]
    pValList = DB.queryVals(database, sqlCols, sqlParam)
    if len(pValList) == 0:
        return
    pValList = pValList[0]
    #sql数组
    sqlArr = [[],[],[],[],[]]
    # 判断查询出来的值是否能在条件表中存在
    for j in range(len(pValList)):
        colName = keyarr[j]
        val = pValList[j]
        if colName not in tmap:
            continue
        relName = colName[:-1]
        point=2
        if relName in point3Arr:
            point=3
        elif relName in point4Arr:
            point=4
        val2=0
        val3=0
        if j%3==0:
            val2=pValList[j+1]
            val3= pValList[j+2]
        elif j%3==1:
            val2 = pValList[j + 1]
            val3 = pValList[j + -1]
        else:
            val2 = pValList[j + -2]
            val3 = pValList[j + -1]

        for v in tmap[colName]:
            lastVal = v - 1 / pow(10, point - 1)
            if abs(val-val2)>=lastVal and abs(val-val2)<v and abs(val-val3)>v and abs(val2-val3)>v:
                tIndex = tmap[colName].index(v)
                conSql = conditiontMap[colName][tIndex]
                tType = rTypeMap[colName][tIndex]
                sqlArr[tType - 1].append(conSql)
                break

    sqlArrLen = 0
    for tArr in sqlArr:
        sqlArrLen += len(tArr)
    # 全部为空
    if sqlArrLen==0:
        return
    # 将所有可能符合的sql组合起来
    combinArr = [[],[],[],[],[]]
    for k in range(len(sqlArr)):
        for m in range(len(sqlArr[k])):
            if m > 2:
                break
            combinArr[k] += (list(combinations(sqlArr[k], m + 1)))
    combinNum = 0
    for cbArr in combinArr:
        combinNum += len(cbArr)
    print('组合总共的个数:%s'%(combinNum))

    maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
    maxPreNum = 4096 #最大预测条数

    #分为5类
    for h in range(len(combinArr)):
        curNum = 0
        cSqlArr= combinArr[h]
        for cArr in cSqlArr:
            if curNum>maxPreNum:
                print(('组合个数超过%s个')%(maxPreNum))
                break
            curNum +=1
            totalCount, pCount = queryCount(database, pankou, pankou, cArr,h,bisaishijian)
            if totalCount is None:
                continue
            maxmins[2 * h] = maxmins[2 * h] if pCount / totalCount <= maxmins[2 * h] else pCount /totalCount
            if maxmins[2 * h + 1] == -1:
                maxmins[2 * h + 1] = pCount / totalCount
            else:
                maxmins[2 * h + 1] = maxmins[2 * h + 1] if pCount / totalCount >= maxmins[2 * h + 1] else pCount /totalCount
    return maxmins



# [总盘数，胜盘数，平盘数，负盘数，上盘盘数，下盘盘数]
def queryCount(database,pankou,linchangpankou,conditionStrs,index,bisaishijian):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 AND pankou=%s AND bisaishijian<="%s" '%(pankou,bisaishijian)
    constr = ''
    for i in conditionStrs:
        constr = constr+ ' '+ i + ' '
    orginSql +=constr
    totalCount=0
    try:
        totalCount = database.execQuery(orginSql)[0][0]
    except:
        return None,None
    rArr = [[' AND zhubifen>kebifen ', '胜'], [' AND zhubifen=kebifen ', '平'], [' AND zhubifen<kebifen ', '负'],
            [' AND (zhubifen-kebifen-pankou)*pankou>0 ', '上盘'], [' AND (zhubifen-kebifen-pankou)*pankou<0 ', '下盘']]
    if totalCount<20:
        return None,None

    # for index in range(len(rArr)):
    i = rArr[index]
    countSql = orginSql + i[0]
    pCount = DB.queryCount(database, countSql)

    return totalCount,pCount



if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    firstAna(database)
    # linchangAna(database)
