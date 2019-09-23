import database as DB


def wrap3CorrectValueRules(database, colName,point=2):
    # colName = 'fc'
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 '
    pankousql = 'SELECT pankou,COUNT(1),linchangpankou FROM football_data WHERE 1=1 and pankou is not null AND linchangpankou is not null %s GROUP BY pankou,' \
                'linchangpankou HAVING count(1)>10 order by pankou,linchangpankou'
    for i in range(3):
        conds = []
        name = colName + str(i + 1)
        valuesql = 'SELECT ROUND(%s,%s),COUNT(1) from football_data WHERE %s is not null  GROUP BY ROUND(%s,%s) HAVING count(1)>10 ORDER BY ROUND(%s,%s)' % (
            name,point, name, name,point, name,point)
        valList = database.execQuery(valuesql)
        if valList is None or len(valList) == 0:
            continue
        for k in valList:
            if k[1] < 5:
                continue
            num = k[0]
            constr = ' AND ROUND(' + name + ',%s)=%s AND ROUND(%s,%s)!=%s AND ROUND(%s,%s)!=%s' % (
                point,num, colName + str((i + 1) % 3 + 1),point, num, colName + str((i + 2) % 3 + 1),point, num)
            tpankousql = pankousql % (constr)
            pankouList = database.execQuery(tpankousql)
            for j in pankouList:
                # if j[1] < 10:
                #     continue
                print(('列名:%s,值:%s,盘口:%s,临场盘口:%s') % (name, num, j[0], j[2]))
                totalSql = orginSql + constr
                totalSql = totalSql + ' AND pankou=%s AND  linchangpankou=%s' % (j[0], j[2])
                count = j[1]
                addCond(totalSql, count, j[0], j[2], conds, constr, name, num)

        DB.insertCondition(database, conds)


def wrap1CorrectValueRules(database, colName):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 '
    pankousql = 'SELECT pankou,COUNT(1),linchangpankou FROM football_data WHERE 1=1 and pankou is not null AND linchangpankou is not null %s GROUP BY pankou,' \
                'linchangpankou HAVING count(1)>10 order by pankou,linchangpankou'

    conds = []
    name = colName
    valuesql = 'SELECT ROUND(%s,2),COUNT(1) from football_data WHERE %s is not null  GROUP BY ROUND(%s,2) HAVING count(1)>10 ORDER BY ROUND(%s,2)' % (
        name, name, name, name)
    valList = database.execQuery(valuesql)
    if valList is None or len(valList) == 0:
        return
    for k in valList:
        num = k[0]
        constr = ' AND ROUND(' + name + ',2)=%s '%(num)
        tpankousql = pankousql % (constr)
        pankouList = database.execQuery(tpankousql)
        for j in pankouList:
            print(('列名:%s,值:%s,盘口:%s,临场盘口:%s') % (name, num, j[0], j[2]))
            totalSql = orginSql + constr
            totalSql = totalSql + ' AND pankou=%s AND  linchangpankou=%s' % (j[0], j[2])
            count = j[1]
            addCond(totalSql, count, j[0], j[2], conds, constr, name, num)

    DB.insertCondition(database, conds)

def addCond(totalSql, count, pankou, linchangpankou, conds, constr, colName, colVal):
    rArr = [[' AND zhubifen>kebifen ', '胜'], [' AND zhubifen=kebifen ', '平'], [' AND zhubifen<kebifen ', '负'],
            [' AND (zhubifen-kebifen-linchangpankou)*linchangpankou>0 ', '上盘'], [' AND (zhubifen-kebifen-linchangpankou)*linchangpankou<0 ', '下盘']]
    for index in range(len(rArr)):
        i = rArr[index]
        pCount = printResult(totalSql, i[0], count)
        if pCount is not None:
            if ((1 < pankou < 2 and i == 0) or (-1 > pankou > -2 and i == 2)) and pCount / count < 0.78:
                continue
            elif ((pankou >= 2 and i == 0) or (pankou <= -2 and i == 2)) and pCount / count < 0.92:
                continue
            elif pCount / count < 0.7:
                continue

            conTemp = [pankou, linchangpankou, constr.strip(), index + 1, count, pCount, round(pCount / count, 3),
                       colName, colVal]
            conds.append(conTemp)
    return conds


def printResult(totalSql, condStr, totalCount):
    countSql = totalSql + condStr
    count = DB.queryCount(database, countSql)

    return count


if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    arr1 = ["qushipianlizhi", "zonghefengxianzhishu", "peilvqiwang", "peilvqujian", "zhibiao", "zhibiaoqujianbi",
            "kailifangchabi",
            "baolengzhishu", "kailizhishuchu", "kailizhishuji", "fangcha", "zonghepingjia1", "zonghepingjia3"]

    arr2 = ["kailizhishuzhongzhi", "kailizhishu", "baolengzhishuxin", "peilvqiwangjunzhi", "lisanzhi",
            "kaipeifangchahe", "kaipeichagaodicha",
            "zhongjiyoushi", "youshicha", "shaixuanbi", "shaixuancha", "zhongjishaixuan", "fa", "fb",
            "youshichayoubian", "shaixuanbiyoubian", "shaixuanchayoubian", "zhongjishaixuanyoubian",
            "zonghefangchabi"]

    arr3 = ["lisanzhichu", "lisanbi",
            "bianyilisanzhishu",
            "qujianqiwangchaquan", "qujianqiwangchazhu", "fc", "fd", "qiwangfengxianbi",
            "zonghezhishu",
            "peilvfangchahe", "zonghefengxianbi", "kailizhishucha", "pellvqujianchu", "peilvqujianji",
            "peilvqujianbi",
            "peifuzhishutiaozhengxia1", "peifuzhishutiaozhengxia2", "peifuzhishutiaozhengxia3",
            "peifuzhishutiaozhengxia4", "peifuzhishutiaozhengxia5", "peifuzhishutiaozhengxia6", "qiwangfangchabi",
            "junhengzhishu", "lisanzhichuyoubian", "lisanzhijiyoubian", "lisanzhichachuyoubian",
            "lisanzhibianhualvyoubian", "lisanzhishuyoubian", "bianyilisanzhishuyoubian",
            "bianyilisanzhishuxiayoubian", "qujianqiwangchaquanyoubian", "qujianqiwangchazhuyoubian", "fcyoubian",
            "fdyoubian", "qiwangfengxianbiyoubian", "zonghezhishuyoubian", "peilvfangchaheyoubian",
            "zonghefengxianbiyoubian",
            "kailizhishuchayoubian", "baolengzhishuyoubian", "zhibiao1youbian", "peilvqujianchuyoubian",
            "peilvqujianjiyoubian",
            "peilvqujianbiyoubian", "zonghefengxianzhishuyoubian", "peifuzhishuyoubian",
            "peifuzhishutiaozhengxia6youbian", "qiwangfangchabiyoubian", "junhengzhishuyoubian"]
    #2位小数
    point2Arr = ["peilv","peilvlisanzhi1", "peilvlisanzhi2","peilvzhongzhi","pingjunpeilv", "pingjunpeilvcha",
                 "chupeizuigaopeilv", "jishizuigaopeilv", "chupeizuidipeilv", "jishizuidipeilv","chupeipingjunpeilv", "jishipingjunpeilv","bianyilisanzhishuxia"]
    #3位小数
    point3Arr = ["peilvbianhua","zucaizhishu","lengrezhishu","lisanzhicha","bocaiyinglizhishu","chuxuanzhishu", "xuandanzhishu","lisanzhibianhualv","dingdanzhishu",
                "shenglvzhongzhi", "pingjunshenglv","zuigaopeilvbianhua", "zuidipeilvbianhua","pingjunpeilvbianhua","yinglizhishu",
                "chupeizuigaoshenglv", "jishizuigaoshenglv", "zuigaoshenglvbianhua", "chupeizuidishenglv",
                "jishizuidishenglv", "zuidishenglvbianhua", "pingjunshenglvbianhua",
                "chupeipingjunshenglv", "jishipingjunshenglv","lisanzhichajiyoubian","lisanbiyoubian","fengxianzhishuyoubian",
                "peifuzhishutiaozhengyoubian", "peifuzhishutiaozhengxia1youbian", "peifuzhishutiaozhengxia2youbian",
                "peifuzhishutiaozhengxia3youbian", "peifuzhishutiaozhengxia4youbian", "peifuzhishutiaozhengxia5youbian", ]
    # 4位小数
    point4Arr = ["peifuzhishu","peifuzhishuxin",  "kailibianhualv", "kaipeicha","pianlizhi", "zhongzhipianlizhi", "pianlilv",
            "zonghepianlizhi",  "peilvfanhuanchaqujian", "peilvfanhuancha","youshizhishu1", "youshizhishu2", "youshizhishu3",
            "youshizhishu4", "youshizhishu5", "youhuayoushi",]

    # 单独元素
    arrType2 = ['shengfuqushicha', 'zonghepingjia2', 'weizhi']

    colArrs = arr1 + arr2 + arr3
    # for j in arrType2:
    #     wrap1CorrectValueRules(database, j)
    # for k in point2Arr:
    #     wrap3CorrectValueRules(database, k, 2)
    # for m in point3Arr:
    #     wrap3CorrectValueRules(database, m, 3)
    for n in point4Arr:
        wrap3CorrectValueRules(database, n, 4)
    # for i in colArrs:
    #     wrap3CorrectValueRules(database, i)
