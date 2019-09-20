import database as DB


def wrapRules(database):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 '
    # for i in range(-5,4):
    colName = 'kailizhishucha'
    for i in range(3):
        name = colName + str(i+1)
        for j in range(-5,5):
            condition = '=-0.01'
            totalSql = orginSql + ' AND ROUND(' + name + ',2)=-0.01 AND ROUND(%s,2)!=-0.01 AND ROUND(%s,2)!=-0.01'
            totalSql = totalSql%((i+1)%3+1,(i+2)%3+1)
            if j>-5:
                totalSql = totalSql+ ' AND pankou=%s '%(str(round(j/4,2)))
                condition = condition+ ',pankou=' + str(round(j/4,2))
            count = DB.queryCount(database,totalSql)
            if count < 5:
                continue
            # 胜
            printResult(name + condition, totalSql, ' AND zhubifen>kebifen ', count, '胜')
            # 平
            printResult(name + condition, totalSql, ' AND zhubifen=kebifen ', count, '平')
            # 负
            printResult(name + condition, totalSql, ' AND zhubifen<kebifen ', count, '负')
            # 上盘
            printResult(name + condition, totalSql, ' AND (zhubifen-kebifen-pankou)*pankou>0 ', count, '上盘')
            # 下盘
            printResult(name + condition, totalSql, ' AND (zhubifen-kebifen-pankou)*pankou<0 ', count, '下盘')
            print('--------------------------------------------------------------------')


def printResult(condName,totalSql,condStr,totalCount,typeName):
    countSql = totalSql + condStr
    count = DB.queryCount(database, countSql)
    if count / totalCount>=0.6:
        print(('%s 总数 %s, %s总数:%s,%s概率:%s ')%(condName,totalCount,typeName,str(count),typeName,str(round(count / totalCount * 100, 1))+'%'))



if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    wrapRules(database)