import pandas as pd
import database as DB
from itertools import combinations,product,chain
from myProcessPools import MyProcessPool
import math
import datetime
import pymysql


maxArr1=["qushipianlizhi","bocaiyinglizhishu","chuxuanzhishu","xuandanzhishu","dingdanzhishu"]
maxArr2=["zhongjiyoushi","zonghefangchabi","bocaiyinglizhishu","kailibianhualv","youshizhishu2","youshizhishu4", "youshizhishu5","youhuayoushi",]
maxArr3=["bianyilisanzhishu","qiwangfangchabi","lisanzhibianhualvyoubian","fcyoubian", "fdyoubian","qiwangfengxianbiyoubian",
         "zonghezhishuyoubian", "peilvfangchaheyoubian","kailizhishuchayoubian","qiwangfengxianbiyoubian","zonghezhishuyoubian",
         "peilvfangchaheyoubian","kailizhishuchayoubian","peifuzhishutiaozhengxia6youbian","lisanzhichajiyoubian",
         "peifuzhishutiaozhengyoubian", "peifuzhishutiaozhengxia1youbian", "peifuzhishutiaozhengxia2youbian","peifuzhishutiaozhengxia3youbian",
         "peifuzhishutiaozhengxia5youbian",]

minArr1=["zonghefengxianzhishu","peilvqiwang","peilvqujian", "zhibiao", "zhibiaoqujianbi", "kailizhishuchu", "kailizhishuji",
         "peilvlisanzhi1", "peilvlisanzhi2","lisanzhicha","lisanzhibianhualv","peifuzhishu", "kaipeicha","peilvfanhuancha",]
minArr2=["peilvqiwangjunzhi","lisanzhi", "kaipeifangchahe", "kaipeichagaodicha","shaixuanbi", "shaixuancha", "zhongjishaixuan","fa", "fb",
         "pingjunpeilvcha","zuigaopeilvbianhua","zuidipeilvbianhua","pingjunpeilvbianhua","zuigaoshenglvbianhua",
         "zuidishenglvbianhua", "pingjunshenglvbiasnhua",]
minArr3=["zonghefengxianzhishu","zhibiao","bianyilisanzhishuxia","lisanzhichu", "lisanbi","qujianqiwangchaquan", "qujianqiwangchazhu",
         "fc", "fd","qiwangfengxianbi", "zonghezhishu","peilvfangchahe","zonghefengxianbi", "kailizhishucha","pellvqujianchu",
         "peilvqujianji", "peilvqujianbi", "peifuzhishutiaozhengxia1", "peifuzhishutiaozhengxia2","peifuzhishutiaozhengxia3",
         "peifuzhishutiaozhengxia4", "peifuzhishutiaozhengxia5", "peifuzhishutiaozhengxia6","lisanzhichuyoubian",
         "lisanzhijiyoubian", "lisanzhichachuyoubian", "lisanzhishuyoubian","bianyilisanzhishuxiayoubian", "zonghefengxianbiyoubian",
         "peilvqujianbiyoubian","qiwangfangchabiyoubian","fengxianzhishuyoubian","peifuzhishutiaozhengxia4youbian","peifuzhishu",
         "peifuzhishuxin",]

validCols = ["chuxuanzhishu","xuandanzhishu","peifuzhishu",'peilvfanhuancha','zonghezhishu']

oldeffectiveCols =['qushipianlizhi','zonghefengxianzhishu','peilvqiwangzhi','zhibiao',
                   'lisanzhicha','bocaiyinglizhishu','dingdanzhishu','lisanzhibianhualv',
                'kailibianhualv','baolengzhishu','youhuayoushi','pingjunpeilvbianhua','pingjunshenglvbianhua','shaixuanbi',
                   'kailizhishu','zonghefangchabi'
                ]

oldeffectiveCols2 =['pingjunpeilvcha','pingjunshenglvcha','peilvqiwangjunzhi','kaipeichagaodicha','youshicha',
                'fa','youshichayoubian','shaixuanbiyoubian','shaixuanchayoubian','zhongjishaixuanyoubian']

effectiveCols= ['lisanzhishu','lisanbiyoubian','fcyoubian','fdyoubian','qiwangfengxianbiyoubian','zonghezhishuyoubian',
                'peilvfangchaheyoubian','zonghefengxianbiyoubian','kailizhishuchayoubian','zhibiao','peilvqujianbi',
                'junhengzhishu','peifuzhishutiaozhengxia5youbian','junhengzhishuyoubian']

glyuzhi=0.4
qwyuzhi=1.2
psyuzhi=100

def getPvMap(database):
    ouPeiList = DB.queryOupei(database)
    _ouPeiMap = {}
    for i in ouPeiList:
        _ouPeiMap[i[0]] = [i[1], i[2], i[3]]
    return _ouPeiMap

def screenData(ouPeiMap,constr,namestr,rType,conds,data,lessColMap,modelId,pk,sindex):
    dataTrain= data
    # for pk in ouPeiMap:
    nameList = namestr.split(',')
    isLess = False
    # 校验是不是之前的约束条件中已经少于盘数阈值了
    for y in range(len(nameList) - 2, 2, -1):
        tNameList = nameList[:y]
        tNameList.sort()
        tName = ','.join(tNameList)
        if not lessColMap.__contains__(pk):
            break
        lessCols = lessColMap[pk]
        if tName in lessCols:
            isLess = True
            break
    if isLess:return
    pv = ouPeiMap[pk]
    dataByPk = dataTrain[dataTrain['pankou']==pk]
    screenedData = eval('dataByPk[%s]'%(constr))
    totalCount = len(screenedData)
    if totalCount<psyuzhi:
        conArr = constr.split('&')
        namesArr = namestr.split(',')
        for x in range(len(nameList),len(nameList)-1,-1):
            tNameList = namesArr[:x]
            tNameList.sort()
            tName = ','.join(tNameList)
            tConArr = conArr[:2*x]
            tConStr = '&'.join(tConArr)
            tsData = eval('dataByPk[%s]'%(tConStr))
            if len(tsData)>=psyuzhi:
                break
            lessCols = []
            if lessColMap.__contains__(pk):
                lessCols = lessColMap[pk]
                if len(lessCols)>5000:
                    lessCols =lessCols[:2000]
            lessCols.append(tName)
            lessColMap[pk] = lessCols
        return
    sCount = len(screenedData[screenedData['zhubifen'] > screenedData['kebifen']])
    pCount = len(screenedData[screenedData['zhubifen'] == screenedData['kebifen']])
    fCount = len(screenedData[screenedData['zhubifen'] < screenedData['kebifen']])

    tarr = [[sCount,'胜'],[pCount,'平'],[fCount,'负']]

    if rType==-1:
        for tindex in range(len(tarr)):
            ar = tarr[tindex]
            count = ar[0]
            resultType = ar[1]
            if  count / totalCount * (pv[tindex]-0.05) >= qwyuzhi:
                # print(('盘口:%s,类型:%s,期望:%s,总场次:%s,命中场次:%s,比例:%s,列名:%s')
                #       % (pk, resultType, round(count / totalCount * pv[tindex], 2), totalCount, count,
                #          round(count / totalCount, 2), namestr))
                conTemp = [pk, pk, constr.strip(), tindex + 1, totalCount, count, round(count / totalCount, 3),
                           modelId, namestr, 0,sindex
                           ]
                conds.append(conTemp)
    else:
        tindex = rType
        ar= tarr[tindex]
        count = ar[0]
        resultType = ar[1]
        if  count/totalCount*(pv[tindex]-0.05)>=qwyuzhi:
            print(('盘口:%s,类型:%s,期望:%s,总场次:%s,命中场次:%s,比例:%s,列名:%s')
              % (pk, resultType, round(count/totalCount*pv[tindex], 2), totalCount, count, round(count/totalCount, 2),namestr))
            conTemp = [pk, pk, constr.strip(), tindex + 1, totalCount, count, round(count / totalCount, 3),
                       modelId, namestr, 0,sindex
                       ]
            conds.append(conTemp)
    return conds

def wrapcondstrArr(colArr,type=1):
    sqlArr = [[],[],[]]
    colNames=[[],[],[]]
    for colName in colArr:
        for i in range(3):
            name = colName + str(i + 1)
            name2 = colName + str((i + 1) % 3 + 1)
            name3 = colName + str((i + 2) % 3 + 1)
            consql= ''
            if type==1:
                consql = '(dataByPk["%s"]>dataByPk["%s"])&(dataByPk["%s"]>dataByPk["%s"])'%(name,name2,name,name3)
            elif type==2:
                consql = '(dataByPk["%s"]<dataByPk["%s"])&(dataByPk["%s"]<dataByPk["%s"])' % (name, name2, name, name3)
            sqlArr[i].append(consql)
            colNames[i].append(name)
    return sqlArr,colNames

def wrapTotalConstr(colArr,type=1):
    sqlArr = []
    colNames = []
    for colName in colArr:
        tArr = []
        cArr = []
        for i in range(3):
            name = colName + str(i + 1)
            name2 = colName + str((i + 1) % 3 + 1)
            name3 = colName + str((i + 2) % 3 + 1)
            if type==1:
                consql = '(dataByPk["%s"]>dataByPk["%s"])&(dataByPk["%s"]>dataByPk["%s"])' % (name, name2, name, name3)
                cArr.append(name+'_max')
                tArr.append(consql)
            elif type==2:
                consql = '(dataByPk["%s"]<dataByPk["%s"])&(dataByPk["%s"]<dataByPk["%s"])' % (name, name2, name, name3)
                cArr.append(name + '_min')
                tArr.append(consql)
            else:
                tArr.append('(dataByPk["%s"]>dataByPk["%s"])&(dataByPk["%s"]>dataByPk["%s"])' % (name, name2, name, name3))
                cArr.append(name + '_max')
                tArr.append('(dataByPk["%s"]<dataByPk["%s"])&(dataByPk["%s"]<dataByPk["%s"])' % (name, name2, name, name3))
                cArr.append(name + '_min')
                # tArr.append('(dataByPk["%s"]>dataByPk["%s"])&(dataByPk["%s"]>dataByPk["%s"])' % (name, name3, name3, name2))
                # cArr.append(name + '_max2')
                # # 等于
                # tArr.append('(dataByPk["%s"]>dataByPk["%s"])&(dataByPk["%s"]=dataByPk["%s"])' % (name, name2, name2, name3))
                # cArr.append(name + '_max3')
                # tArr.append('(dataByPk["%s"]=dataByPk["%s"])&(dataByPk["%s"]>dataByPk["%s"])' % (name, name2, name2, name3))
                # cArr.append(name + '_max4')

        sqlArr.append(tArr)
        colNames.append(cArr)
    return sqlArr, colNames


def mytask(tArr,nameArr,database,j,data,printStr,modelId,ouPeiMap,pk,sindex):
    conds = []
    lessColMap={}

    for ti in range(len(tArr)):
        t = tArr[ti]
        sql = ''
        namestr = ''
        for m in range(len(t)):
            if m == len(t) - 1:
                sql += t[m]
                namestr += nameArr[ti][m]
            else:
                sql = sql + t[m] + '&'
                namestr += nameArr[ti][m] + ','

        screenData(ouPeiMap, sql, namestr, j, conds, data,lessColMap,modelId,pk,sindex)
        print(('%s,当前进度:%s/%s') % (printStr, ti + 1, len(tArr)))
        if len(conds) > 5:
            DB.insertCondition(database, conds)
            conds = []
    if len(conds) > 0:
        DB.insertCondition(database, conds)

def runAll():
    database = DB.Database('localhost', 'root', 'root', 'sports')
    ouPeiMap = getPvMap(database)
    data = pd.DataFrame(pd.read_csv('football_data.csv'))  # 读取数据
    perPool = 3
    myThreads = MyProcessPool(9)
    maxArr = [maxArr1, maxArr2, maxArr3]
    minArr = [minArr1, minArr2, minArr3]
    for i in range(10, 11, 1):
        for y in range(0, 3):
            taskId = 1
            sqlArr1, colNames1 = wrapcondstrArr(maxArr[y], 1)
            sqlArr2, colNames2 = wrapcondstrArr(minArr[y], 2)
            sqlArr = [sqlArr1[0] + sqlArr2[0], sqlArr1[1] + sqlArr2[1], sqlArr1[2] + sqlArr2[2]]
            colNameArr = [colNames1[0] + colNames2[0], colNames1[1] + colNames2[1], colNames1[2] + colNames2[2]]
            for j in range(len(sqlArr)):
                tArr = (list(combinations(sqlArr[j], i)))
                nameArr = (list(combinations(colNameArr[j], i)))
                step = math.ceil(len(tArr) / (perPool * 5))
                if step > 50000:
                    step = 50000
                b = [tArr[h:h + step] for h in range(0, len(tArr), step)]
                c = [nameArr[m:m + step] for m in range(0, len(nameArr), step)]
                for bi in range(len(b)):
                    printStr = '当前组合数:%s,版数:%s,类别:%s,任务总数:%s,任务ID:%s' % (i, y + 1, j + 1, len(b), taskId)
                    # myThreads.submit(mytask, b[bi], c[bi], database, j, data, printStr, i,ouPeiMap)
                    mytask(b[bi], c[bi], database, j, data, printStr, i,ouPeiMap)
                    taskId += 1
            myThreads.wait()
            print((
                      "---------------------当前时间:%s,当前组合个数:%s,版数:%s---------------------------------------------------------") % (
                  datetime.datetime.now(), i, y))
def dfs(multiArray, kNums, cur, result,ans):
    if len(result) == kNums:
        ans.append(list(result))
    if len(multiArray) == cur:
        return
    if len(result) < kNums:
        for i in multiArray[cur]:
            result.append(i)
            dfs(multiArray, kNums, cur + 1, result,ans)
            result.pop()
        dfs(multiArray, kNums, cur + 1, result,ans)


def combinations_t(multiArray, kNums):
    ans=[]
    if len(multiArray) >= kNums >= 0:
        dfs(multiArray, kNums, 0, [],ans)
    return ans

def flatten(a):
    b = []
    for i in a:
        if isinstance(i, tuple) or isinstance(i, list):
            b += i
        else:
            b.append(i)

    return b


def chceckIsEnough(tIter,nIter,scomsIter,ccomsIter,lessNum,maxNum,dataByPk,currentI,
                   database,sindex,pk,ouPeiMap,taskId,comLen,lessMap={}):
    for x, y in zip(tIter, nIter):
        xList = flatten(x)
        yList = flatten(y)
        tempys = yList[:]
        tempys.sort()
        colnames = ','.join(tempys)
        if lessMap.__contains__(pk) and colnames in lessMap[pk]:
            continue
        sql = ''
        for m in range(len(xList)):
            sql = sql + xList[m] + '&'
        sql = sql[:-1]
        sceenData = eval('dataByPk[%s]' % (sql))
        if len(sceenData) < psyuzhi:
            lessMap[pk].append(colnames)
            continue
        if lessNum<maxNum:
            tNextIter = product([xList],scomsIter[lessNum])
            nNextIter = product([yList],ccomsIter[lessNum])
            chceckIsEnough(tNextIter, nNextIter, scomsIter, ccomsIter, lessNum+1, maxNum,
                           sceenData,currentI,database,sindex,pk,ouPeiMap,taskId,comLen,lessMap)
        else:
            tArr = [[]]
            nameArr = [[]]
            str1=''
            str2=''
            for l in range(lessNum,currentI):
                str1 += 'scomsIter[%s],' % (l)
                str2 += 'ccomsIter[%s],' % (l)
            str1 = str1[:-1]
            str2 = str2[:-1]
            tFinalIter = eval('product([xList],%s)' % (str1))
            nFinalIter = eval('product([yList],%s)' % (str2))
            for m, n in zip(tFinalIter, nFinalIter):
                mList = flatten(m)
                nList = flatten(n)
                if len(tArr) % 45 == 0 and len(tArr[len(tArr) - 1]) == 20000:
                    tArr = [[]]
                    nameArr = [[]]
                elif len(tArr[len(tArr) - 1]) >= 20000:
                    printStr = '当前组合数:%s,版数:%s,排列总数:%s,盘口:%s,当前排列:%s,任务ID:%s' % (currentI, 1, comLen,pk, sindex + 1, taskId)
                    mytask(tArr[len(tArr) - 1], nameArr[len(nameArr) - 1], database, -1, dataByPk,
                                                   printStr, currentI, ouPeiMap,pk,sindex)
                    taskId += 1
                    tArr.append([])
                    nameArr.append([])
                tArr[len(tArr) - 1].append(mList)
                nameArr[len(nameArr) - 1].append(nList)
            taskId += 1
            printStr = '当前组合数:%s,版数:%s,排列总数:%s,盘口:%s,当前排列:%s,任务ID:%s' % (currentI, 1, comLen, pk, sindex + 1, taskId)
            mytask(tArr[len(tArr) - 1], nameArr[len(nameArr) - 1], database, -1, dataByPk,
                                                 printStr, currentI, ouPeiMap,pk,sindex)

def processTask(ss,cc,ouPeiMap,data,database,comlen,comnum,sindex):

    taskId = 1
    lessNum = 4
    str1 = ''
    str2 = ''
    for l in range(lessNum):
        str1 += 'ss[%s],' % (l)
        str2 += 'cc[%s],' % (l)
    str1 = str1[:-1]
    str2 = str2[:-1]
    # tIter = eval('product(%s)' % (str1))
    # nIter = eval('product(%s)' % (str2))
    lessMap = {}
    for pk in ouPeiMap.keys():
        dataByPk = data[data['pankou'] == pk]
        if len(dataByPk) < psyuzhi:
            continue
        if not lessMap.__contains__(pk):
            lessMap[pk] = []
        chceckIsEnough(eval('product(%s)' % (str1)), eval('product(%s)' % (str2)), ss, cc, lessNum, comnum-2, dataByPk, comnum,
                       database, sindex, pk, ouPeiMap,taskId,comlen,lessMap)

def run1Maxmin():
    database = DB.Database('localhost', 'root', 'root', 'sports')
    ouPeiMap = getPvMap(database)
    conn = pymysql.connect(host='localhost', user='root', passwd='root', db='sports', charset="utf8")
    orginSql = 'SELECT * FROM football_data WHERE 1=1  AND bisaishijian<"%s"  ' % ('2019-11-03')
    data = pd.read_sql(sql=orginSql, con=conn)
    conn.close()
    # data = pd.DataFrame(pd.read_csv('football_data.csv'))  # 读取数据
    myProcess = MyProcessPool(9)
    lastIndex = DB.queryLastIndex(database)[0][0]
    # for comnum in range(13,14, 1):
    comnum=8
    oldeffectiveCols.sort(reverse=True)
    sqlArr, colNameArr = wrapTotalConstr(oldeffectiveCols,3)
    scoms = combinations(sqlArr,comnum)
    ccoms = combinations(colNameArr,comnum)
    comlen = sum(1 for x in combinations(sqlArr,comnum))
    sindex=0
    currentQueIndex =  0

    for ss,cc in zip(scoms,ccoms):
        if sindex <= lastIndex:
            sindex += 1
            continue
        # processTask(ss, cc, ouPeiMap, data, database, comlen, comnum, sindex)
        myProcess.submit(processTask,ss, cc, ouPeiMap, data, database, comlen, comnum, sindex)
        currentQueIndex+=1
        if currentQueIndex%45==0:
            myProcess.wait()
            DB.updateLastIndex(database, sindex)
        sindex += 1
    myProcess.wait()
    DB.updateLastIndex(database, sindex)

if __name__ == '__main__':
    # runAll()
    run1Maxmin()