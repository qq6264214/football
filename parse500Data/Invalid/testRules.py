import database as DB
from itertools import combinations

_ouPeiMap={}


def wrapcondSqlArr(colArr,type=1):
    sqlArr = [[],[],[]]
    for colName in colArr:
        for i in range(3):
            name = colName + str(i + 1)
            name2 = colName + str((i + 1) % 3 + 1)
            name3 = colName + str((i + 2) % 3 + 1)
            consql= ''
            if type==1:
                consql = ' and %s>%s and %s>%s '%(name,name2,name,name3)
            elif type==2:
                consql = ' and %s<%s and %s<%s ' % (name, name2, name, name3)
            sqlArr[i].append(consql)

    return sqlArr


def wrapSeniorRules(database, consql):
    orginSql = 'SELECT count(1) FROM football_data WHERE 1=1 and bisaishijian<"2019-09-01" '
    pankousql = 'SELECT pankou,COUNT(1),pankou FROM football_data WHERE 1=1 and pankou is not null %s GROUP BY pankou' \
                ' HAVING count(1)>30 order by pankou'
    conds = []
    tpankousql = pankousql % (consql)
    pankouList = database.execQuery(tpankousql)
    if len(pankouList)==0:
        return 0
    for j in pankouList:
        totalSql = orginSql + consql
        totalSql = totalSql + ' AND pankou=%s ' % (j[0])
        count = j[1]
        appendCons(totalSql, count, j[0], j[0], conds, database,consql)
    DB.insertCondition(database, conds)
def appendCons(totalSql, count, pankou, linchangpankou, conds, database,consql):
    rArr = [[' AND zhubifen>kebifen ', '胜'], [' AND zhubifen=kebifen ', '平'], [' AND zhubifen<kebifen ', '负'],
            [' AND (zhubifen-kebifen-pankou)*pankou>0 ', '上盘'],
            [' AND (zhubifen-kebifen-pankou)*pankou<0 ', '下盘']]
    for index in range(len(rArr)-2):
        i = rArr[index]
        countSql = totalSql + i[0]
        pCount = DB.queryCount(database, countSql)
        if pCount is not None:
            qw= _ouPeiMap[pankou][index]* (pCount/count)
            if pCount / count < 0.33:
                continue
            elif index<3 and qw <1.25:
                continue
            elif index>=3 and pCount / count < 0.7:
                continue
            print(('盘口:%s,类型:%s,期望:%s,总场次:%s,命中场次:%s,比例:%s')
                  % (pankou,i[1],round(qw,2),count, pCount, round(pCount / count, 3)))

            conTemp = [pankou, linchangpankou, consql.strip(), index + 1, count, pCount, round(pCount / count, 3),4,'',0
                       ]
            conds.append(conTemp)
    return conds


if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    ouPeiList = DB.queryOupei(database)
    global _ouPeiMap
    for i in ouPeiList:
        _ouPeiMap[i[0]] = [i[1], i[2], i[3]]
    # consql = ' AND ABS(pianlilv1)=ABS(pianlilv2) AND ABS(pianlilv1)!=ABS(pianlilv3) '
    # wrapSeniorRules(database, consql)

    maxArr = ["zhongjiyoushi","qushipianlizhi","zonghefangchabi","bianyilisanzhishu","qiwangfangchabi","lisanzhibianhualvyoubian",
              "fcyoubian", "fdyoubian","qiwangfengxianbiyoubian","zonghezhishuyoubian", "peilvfangchaheyoubian","kailizhishuchayoubian",
              "peifuzhishutiaozhengxia6youbian","bocaiyinglizhishu", "chuxuanzhishu","xuandanzhishu","dingdanzhishu","lisanzhichajiyoubian",
              "peifuzhishutiaozhengyoubian", "peifuzhishutiaozhengxia1youbian", "peifuzhishutiaozhengxia2youbian",
              "peifuzhishutiaozhengxia3youbian","peifuzhishutiaozhengxia5youbian","kailibianhualv","youshizhishu2",
              "youshizhishu4", "youshizhishu5", "youhuayoushi",]

    minArr = ['peilvbianhua',"zonghefengxianzhishu","peilvqiwang","peilvqujian", "zhibiao", "zhibiaoqujianbi",
              "kailizhishuchu", "kailizhishuji","peilvqiwangjunzhi", "lisanzhi", "kaipeifangchahe", "kaipeichagaodicha",
              "shaixuanbi", "shaixuancha", "zhongjishaixuan","fa", "fb","peilvlisanzhi1", "peilvlisanzhi2", "pingjunpeilvcha",
              "bianyilisanzhishuxia","lisanzhichu", "lisanbi","qujianqiwangchaquan", "qujianqiwangchazhu", "fc", "fd",
              "qiwangfengxianbi", "zonghezhishu","peilvfangchahe","zonghefengxianbi", "kailizhishucha","pellvqujianchu",
              "peilvqujianji", "peilvqujianbi","peifuzhishutiaozhengxia1","peifuzhishutiaozhengxia2", "peifuzhishutiaozhengxia3",
              "peifuzhishutiaozhengxia4", "peifuzhishutiaozhengxia5","peifuzhishutiaozhengxia6","lisanzhichuyoubian",
              "lisanzhijiyoubian", "lisanzhichachuyoubian","lisanzhishuyoubian", "bianyilisanzhishuxiayoubian","zonghefengxianbiyoubian",
              "peilvqujianbiyoubian","qiwangfangchabiyoubian","lisanzhicha","lisanzhibianhualv","zuigaopeilvbianhua",
              "zuidipeilvbianhua","pingjunpeilvbianhua","zuigaoshenglvbianhua",  "zuidishenglvbianhua", "pingjunshenglvbianhua",
              "fengxianzhishuyoubian","peifuzhishutiaozhengxia4youbian","peifuzhishu", "peifuzhishuxin","kaipeicha","peilvfanhuancha",
              ]

    sqlArr1 = wrapcondSqlArr(maxArr,1)
    sqlArr2 = wrapcondSqlArr(minArr,2)
    sqlArr= sqlArr1

    sqlArr = [sqlArr1[0]+sqlArr2[0],sqlArr1[1]+sqlArr2[1],sqlArr1[2]+sqlArr2[2]]

    for j in range(len(sqlArr)):
        for i in range(5,20):
            tArr = (list(combinations(sqlArr[j], i)))
            for t in tArr:
                sql = ''
                for m in t:
                    sql+=m
                wrapSeniorRules(database,sql)