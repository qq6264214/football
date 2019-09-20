import database as DB


def wrapRules(database):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 '
    # for i in range(-5,4):
    colName = 'kailizhishucha'
    conds = []
    for i in range(3):
        name = colName + str(i+1)
        for j in range(-11,11):
            condition = '=-0.01'
            constr = ' AND ROUND(' + name + ',2)=-0.01 AND ROUND(%s,2)!=-0.01 AND ROUND(%s,2)!=-0.01'%(colName+str((i+1)%3+1),colName+str((i+2)%3+1))
            totalSql = orginSql + constr
            if j>-11:
                totalSql = totalSql+ ' AND pankou=%s '%(str(round(j/4,2)))
                condition = condition+ ',pankou=' + str(round(j/4,2))
            count = DB.queryCount(database,totalSql)
            if count < 5:
                continue
            addCond(name + condition, totalSql, count, round(j/4,2), conds,constr)
            print('--------------------------------------------------------------------')
    DB.insertCondition(database,conds)


def addCond(condName,totalSql,count,pankou,conds,constr):
    rArr = [[' AND zhubifen>kebifen ','胜'],[' AND zhubifen=kebifen ','平'],[' AND zhubifen<kebifen ','负'],
            [' AND (zhubifen-kebifen-pankou)*pankou>0 ','上盘'],[' AND (zhubifen-kebifen-pankou)*pankou<0 ','下盘']]
    for index in range(len(rArr)):
        i = rArr[index]
        pCount = printResult(condName, totalSql, i[0], count, i[1])
        if pCount is not None:
            conTemp=[pankou,constr.strip(),index+1,count,pCount,round(pCount / count,3)]
            conds.append(conTemp)

    return conds

def printResult(condName,totalSql,condStr,totalCount,typeName):
    countSql = totalSql + condStr
    count = DB.queryCount(database, countSql)
    if count / totalCount>=0.6:
        print(('%s 总数 %s, %s总数:%s,%s概率:%s ')%(condName,totalCount,typeName,str(count),typeName,str(round(count / totalCount * 100, 1))+'%'))
        return count
    return None

if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    wrapRules(database)