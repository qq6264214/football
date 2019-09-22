import database as DB
from itertools import combinations

colsMap = {}
conditionsMap = {}

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

def wrapPankouKey(pankou,linchangpankou):
    return str(pankou) + '_' + str(linchangpankou)



def getMaxMins(database,pankou,linchangpankou,bisaileixing,bisaishijian,zhudui,kedui):
    global colsMap, conditionsMap
    pankouKey = wrapPankouKey(pankou,linchangpankou)
    tmap = {}
    conditiontMap = {}
    if not colsMap.__contains__(pankouKey):
        colArr = DB.queryColNameAndValByPankou(database, pankou, linchangpankou)
        for colName, colVal, conditionSql in colArr:
            valArr = []
            conditionSqlArr = []
            if tmap.__contains__(colName):
                valArr = tmap[colName]
                conditionSqlArr = conditiontMap[colName]
            valArr.append(colVal)
            conditionSqlArr.append(conditionSql)
            tmap[colName] = valArr
            conditiontMap[colName] = conditionSqlArr
        colsMap[pankouKey] = tmap
        conditionsMap[pankouKey] = conditiontMap
    else:
        tmap = colsMap[pankouKey]
        conditiontMap = conditionsMap[pankouKey]
    if len(tmap) == 0:
        return
    keys = tmap.keys()
    sqlCols = ''
    keyarr = []
    for i in keys:
        sqlCols = sqlCols + i + ','
        keyarr.append(i)
    sqlCols = sqlCols[0:len(sqlCols) - 1]
    sqlParam = [bisaileixing,bisaishijian,zhudui,kedui]
    pValList = DB.queryVals(database, sqlCols, sqlParam)
    if len(pValList) == 0:
        return
    pValList = pValList[0]
    sqlArr = []
    # 判断查询出来的值是否能在条件表中存在
    for j in range(len(pValList)):
        colName = keyarr[j]
        val = pValList[j]
        if val in tmap[colName]:
            tIndex = tmap[colName].index(val)
            conSql = conditiontMap[colName][tIndex]
            sqlArr.append(conSql)
    if len(sqlArr) == 0:
        return
    # 将所有可能符合的sql组合起来
    combinArr = []
    for k in range(len(sqlArr)):
        combinArr += (list(combinations(sqlArr, k + 1)))
    maxmins = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
    for cArr in combinArr:
        counts = queryCount(database, pankou, pankou, cArr)
        if counts is None:
            continue
        for h in range(5):
            maxmins[2 * h] = maxmins[2 * h] if counts[h + 1] / counts[0] <= maxmins[2 * h] else counts[h + 1] / counts[
                0]
            if maxmins[2 * h + 1] == -1:
                maxmins[2 * h + 1] = counts[h + 1] / counts[0]
            else:
                maxmins[2 * h + 1] = maxmins[2 * h + 1] if counts[h + 1] / counts[0] >= maxmins[2 * h + 1] else counts[h + 1] /counts[ 0]
    return maxmins



# [总盘数，胜盘数，平盘数，负盘数，上盘盘数，下盘盘数]
def queryCount(database,pankou,linchangpankou,conditionStrs):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 AND pankou=%s AND linchangpankou=%s'%(pankou,linchangpankou)
    constr = ''
    for i in conditionStrs:
        constr = constr+ ' '+ i + ' '
    orginSql +=constr
    totalCount = database.execQuery(orginSql)[0][0]
    rArr = [[' AND zhubifen>kebifen ', '胜'], [' AND zhubifen=kebifen ', '平'], [' AND zhubifen<kebifen ', '负'],
            [' AND (zhubifen-kebifen-pankou)*pankou>0 ', '上盘'], [' AND (zhubifen-kebifen-pankou)*pankou<0 ', '下盘']]
    if totalCount<8:
        return None
    counts = [totalCount]
    for index in range(len(rArr)):
        i = rArr[index]
        countSql = orginSql + i[0]
        pCount = DB.queryCount(database, countSql)
        counts.append(pCount)
    return counts



if __name__ == '__main__':
   database = DB.Database('localhost', 'root', 'root', 'sports')
   firstAna(database)

