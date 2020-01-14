import database as DB
from Invalid.rules import addCond
from math import pow

_ouPeiMap={}


def wrapTwoEqualRules(database, colName, point):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 '
    pankousql = 'SELECT pankou,COUNT(1),linchangpankou FROM football_data WHERE 1=1 and pankou is not null AND linchangpankou is not null %s GROUP BY pankou,' \
                'linchangpankou HAVING count(1)>10 order by pankou,linchangpankou'
    for i in range(3):
        conds = []
        name = colName + str(i + 1)
        name2 = colName + str((i + 1) % 3 + 1)
        name3 = colName + str((i + 2) % 3 + 1)
        valuesql = 'SELECT ROUND(%s,%s),COUNT(1) from football_data WHERE %s is not null  AND ROUND(%s,%s)=ROUND(%s,%s) AND ROUND(%s,%s)!=ROUND(%s,%s)' \
                   ' GROUP BY ROUND(%s,%s) HAVING count(1)>10 ORDER BY ROUND(%s,%s)' % (
                       name, point, name, name, point, name2, point, name, point, name3, point, name, point, name,
                       point)
        valList = database.execQuery(valuesql)
        if valList is None or len(valList) == 0:
            continue
        for k in valList:
            if k[1] < 5:
                continue
            num = k[0]
            constr = ' AND ROUND(' + name + ',%s)=%s AND ROUND(%s,%s)=%s AND ROUND(%s,%s)!=%s' % (
                point, num, colName + str((i + 1) % 3 + 1), point, num, colName + str((i + 2) % 3 + 1), point, num)
            tpankousql = pankousql % (constr)
            pankouList = database.execQuery(tpankousql)
            for j in pankouList:
                # print(('列名:%s,值:%s,盘口:%s,临场盘口:%s') % (name, num, j[0], j[2]))
                totalSql = orginSql + constr
                totalSql = totalSql + ' AND pankou=%s AND  linchangpankou=%s' % (j[0], j[2])
                count = j[1]
                addCond(totalSql, count, j[0], j[2], conds, constr, name, num, database)

        # DB.insertCondition(database, conds)


def wrapcondSql(database,colArr,point):

    for colName in colArr:

        for i in range(3):
            flag = 0
            for j in range(1, 500):
                lastVal = (j-1)/pow(10, point - 1)
                j = j / pow(10, point - 1)
                print(('当前列名:%s,数值:%s') % (colName, j))
                name = colName + str(i + 1)
                name2 = colName + str((i + 1) % 3 + 1)
                name3 = colName + str((i + 2) % 3 + 1)
                consql = 'and abs(%s-%s)>=%s and abs(%s-%s)<%s and abs(%s-%s)>%s and abs(%s-%s)>%s '%(name,name2,lastVal,name,name2,j,name,name3,j,name2,name3,j)
                # consql = 'and abs(%s)>abs(%s) and abs(%s)>abs(%s)'%(name,name2,name,name3)
                num = wrapSeniorRules(database, consql,name,j)

                if num is not None and num == 0:
                    flag += 1
                else:
                    flag = 0
                if flag == 10:
                    print(('当前列名:%s,数值:%s,超过5次无效,退出') % (colName,j))
                    break
    return


def wrapSeniorRules(database, consql,colName,colValue):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 and bisaishijian<"2019-09-01" '
    pankousql = 'SELECT pankou,COUNT(1),linchangpankou FROM football_data WHERE 1=1 and pankou is not null %s GROUP BY pankou' \
                ' HAVING count(1)>20 order by pankou'
    conds = []
    tpankousql = pankousql % (consql)
    pankouList = database.execQuery(tpankousql)
    if len(pankouList)==0:
        return 0

    for j in pankouList:
        # print(('列名:%s,值:%s,盘口:%s,临场盘口:%s') % (name, num, j[0], j[2]))
        totalSql = orginSql + consql
        totalSql = totalSql + ' AND pankou=%s ' % (j[0])
        count = j[1]
        appendCons(totalSql, count, j[0], j[0], conds, consql, database,colName,colValue)

    DB.insertCondition(database, conds)


def appendCons(totalSql, count, pankou, linchangpankou, conds, constr, database,colName,colValue):
    rArr = [[' AND zhubifen>kebifen ', '胜'], [' AND zhubifen=kebifen ', '平'], [' AND zhubifen<kebifen ', '负'],
            [' AND (zhubifen-kebifen-pankou)*pankou>0 ', '上盘'],
            [' AND (zhubifen-kebifen-pankou)*pankou<0 ', '下盘']]
    for index in range(len(rArr)-2):
        i = rArr[index]
        countSql = totalSql + i[0]
        pCount = DB.queryCount(database, countSql)
        if pCount is not None:
            if index<3 and _ouPeiMap[pankou][index]* (pCount/count)<1.2:
                continue
            elif index>=3 and pCount / count < 0.7:
                continue
            print(('盘口:%s,临场盘口:%s,类型:%s,总场次:%s,命中场次:%s,比例:%s')
                  % (pankou, linchangpankou, i[1], count, pCount, round(pCount / count, 3)))
            conTemp = [pankou, linchangpankou, constr.strip(), index + 1, count, pCount, round(pCount / count, 3),3,colName,colValue
                       ]
            conds.append(conTemp)
    return conds


if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    # 2位小数
    point2Arr = ["peilv", "peilvlisanzhi1", "peilvlisanzhi2", "peilvzhongzhi", "pingjunpeilv", "pingjunpeilvcha",
                 "chupeizuigaopeilv", "jishizuigaopeilv", "chupeizuidipeilv", "jishizuidipeilv", "chupeipingjunpeilv",
                 "jishipingjunpeilv", "bianyilisanzhishuxia", "qushipianlizhi", "zonghefengxianzhishu", "peilvqiwang",
                 "peilvqujian", "zhibiao", "zhibiaoqujianbi","kailifangchabi","baolengzhishu", "kailizhishuchu",
                 "kailizhishuji", "fangcha", "zonghepingjia1", "zonghepingjia3","kailizhishuzhongzhi", "kailizhishu",
                 "baolengzhishuxin", "peilvqiwangjunzhi", "lisanzhi","kaipeifangchahe", "kaipeichagaodicha","zhongjiyoushi",
                 "youshicha", "shaixuanbi", "shaixuancha", "zhongjishaixuan", "fa", "fb","youshichayoubian", "shaixuanbiyoubian",
                 "shaixuanchayoubian", "zhongjishaixuanyoubian","zonghefangchabi", "lisanzhichu", "lisanbi","bianyilisanzhishu",
                 "qujianqiwangchaquan", "qujianqiwangchazhu", "fc", "fd", "qiwangfengxianbi","zonghezhishu", "peilvfangchahe",
                 "zonghefengxianbi", "kailizhishucha", "pellvqujianchu", "peilvqujianji","peilvqujianbi","peifuzhishutiaozhengxia1",
                 "peifuzhishutiaozhengxia2", "peifuzhishutiaozhengxia3","peifuzhishutiaozhengxia4", "peifuzhishutiaozhengxia5",
                 "peifuzhishutiaozhengxia6", "qiwangfangchabi","junhengzhishu", "lisanzhichuyoubian", "lisanzhijiyoubian", "lisanzhichachuyoubian",
                 "lisanzhibianhualvyoubian", "lisanzhishuyoubian", "bianyilisanzhishuyoubian","bianyilisanzhishuxiayoubian",
                 "qujianqiwangchaquanyoubian", "qujianqiwangchazhuyoubian", "fcyoubian","fdyoubian", "qiwangfengxianbiyoubian",
                 "zonghezhishuyoubian", "peilvfangchaheyoubian","zonghefengxianbiyoubian","kailizhishuchayoubian", "baolengzhishuyoubian",
                 "zhibiao1youbian", "peilvqujianchuyoubian", "peilvqujianjiyoubian","peilvqujianbiyoubian",
                 "zonghefengxianzhishuyoubian", "peifuzhishuyoubian", "peifuzhishutiaozhengxia6youbian", "qiwangfangchabiyoubian",
                 "junhengzhishuyoubian"]
    # 3位小数
    point3Arr = ["peilvbianhua", "zucaizhishu", "lengrezhishu", "lisanzhicha", "bocaiyinglizhishu", "chuxuanzhishu",
                 "xuandanzhishu", "lisanzhibianhualv", "dingdanzhishu",
                 "shenglvzhongzhi", "pingjunshenglv", "zuigaopeilvbianhua", "zuidipeilvbianhua", "pingjunpeilvbianhua",
                 "yinglizhishu",
                 "chupeizuigaoshenglv", "jishizuigaoshenglv", "zuigaoshenglvbianhua", "chupeizuidishenglv",
                 "jishizuidishenglv", "zuidishenglvbianhua", "pingjunshenglvbianhua",
                 "chupeipingjunshenglv", "jishipingjunshenglv", "lisanzhichajiyoubian", "lisanbiyoubian",
                 "fengxianzhishuyoubian",
                 "peifuzhishutiaozhengyoubian", "peifuzhishutiaozhengxia1youbian", "peifuzhishutiaozhengxia2youbian",
                 "peifuzhishutiaozhengxia3youbian", "peifuzhishutiaozhengxia4youbian",
                 "peifuzhishutiaozhengxia5youbian", ]
    # 4位小数
    point4Arr = ["peifuzhishu", "peifuzhishuxin", "kailibianhualv", "kaipeicha", "pianlizhi", "zhongzhipianlizhi",
                 "pianlilv",
                 "zonghepianlizhi", "peilvfanhuanchaqujian", "peilvfanhuancha", "youshizhishu1", "youshizhishu2",
                 "youshizhishu3",
                 "youshizhishu4", "youshizhishu5", "youhuayoushi", ]
    # 单独元素
    arrType2 = ['shengfuqushicha', 'zonghepingjia2', 'weizhi']
    # for k in point2Arr:
    #     wrapTwoEqualRules(database, k, 2)
    # for m in point3Arr:
    #     wrapTwoEqualRules(database, m, 3)
    # for n in point4Arr:
    #     wrapTwoEqualRules(database, n, 4)
    # consql = ' and qushipianlizhi1>qushipianlizhi3 and qushipianlizhi3>qushipianlizhi2 and shengfuqushicha>1 ' \
    #          ' and zonghefengxianzhishu1<zonghefengxianzhishu2 and zonghefengxianzhishu1<zonghefengxianzhishu3 ' \
    #          ' and zonghefengxianzhishu1>0 and zonghepingjia32>0 '
    # wrapSeniorRules(database, consql)
    ouPeiList = DB.queryOupei(database)
    global _ouPeiMap
    for i in ouPeiList:
        _ouPeiMap[i[0]]=[i[1],i[2],i[3]]
    wrapcondSql(database,point2Arr,2)
    wrapcondSql(database, point3Arr, 3)
    wrapcondSql(database, point4Arr, 4)
