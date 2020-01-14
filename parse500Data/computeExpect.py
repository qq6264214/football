import database as DB
import datetime
import pickle
import re

conflictVal = 0.6


def compute(database,bisaishijian,bisaishijianend,yuzhi=0.75,qwyuzhi=1.5,countYuzhi=5,limitType=-1,futype=-1):
    ouPeiList = DB.queryOupei(database)
    peilvMap = {}
    sxpeilv = 0.8
    results={}
    for i in ouPeiList:
        i =  list(i)
        pankou = i[0]
        del i[0]
        peilvMap[pankou]=i

    forecastList = DB.queryResult(database,bisaishijian,bisaishijianend,yuzhi,countYuzhi)
    yesCount=0
    totalCount = 0
    # 默认期望
    expect = 0
    for j in forecastList:
        j = list(j)
        lpk = j[0]
        # if lpk not in [0,0.25,-0.25,0.5,-0.5,0.75,-0.75]:
        #     continue
        if peilvMap[lpk] is None:
            print(("盘口为%s,找不到欧赔,跳过")%(lpk))
        zbf = j[1]
        kbf = j[2]
        del j[0]
        del j[0]
        del j[0]
        #检查是否冲突
        # j[0] = -1
        # j[1] = -1
        # j[2] = -1
        ctArr = j[3:6]
        levelArr = j[6:9]
        teams = j[9:15]
        changci = j[14]
        shijian = j[15]
        # print(shijian.weekday())
        # if shijian.weekday()<4 and (bool(re.search('[A-Z]', changci[0])) or bool(re.search('[a-z]', changci[0]))):
        #     continue

        # if bool(re.search('[A-Z]', changci[0])) or bool(re.search('[a-z]', changci[0])):
        #     continue
            # print(changci)


        j=j[:3]
        eArr = []
        cNum =0
        for ix in range(len(j)):
            if j[ix] <= yuzhi  or j[ix]*(peilvMap[lpk][ix]-0.05)<qwyuzhi or ctArr[ix]<countYuzhi:
                eArr.append(-1)
            else:
                eArr.append(j[ix]*(peilvMap[lpk][ix]-0.05))
                cNum+=1
        maxIndex = eArr.index(max(eArr))
        maxVal = eArr[maxIndex]


        if limitType != -1 and maxIndex!=limitType:
            continue


        if limitType==2 :
            # if lpk>0.75:
            #     continue
            #
            if futype==1 and lpk<=0:
                continue
            elif futype ==0 and lpk>0:
                continue
        # ctMaxIndex = levelArr.index(max(levelArr))
        # if maxIndex!=ctMaxIndex:
        #     continue
        # if cNum!=1:
        #     continue
        if maxVal == 999 or maxVal==-1:
            continue


        # if (maxIndex == 0 and pankou>=0.5) or (maxIndex==2 and pankou<=-0.5):
        #     continue
        if not results.__contains__(lpk):
            results[lpk]=[0,0,0]
        if maxIndex in [0,1,2] :
            # print(teams)
            if (maxIndex == 0 and zbf>kbf) or (maxIndex == 1 and zbf==kbf) or (maxIndex == 2 and zbf<kbf):
                expect += peilvMap[lpk][maxIndex]-1.05
                yesCount += 1
                totalCount += 1
                results[lpk][maxIndex] += peilvMap[lpk][maxIndex]-1.05
            else:
                expect = expect - 1
                totalCount += 1
                results[lpk][maxIndex] += -1
        elif zbf-kbf-lpk==0:
            continue
        elif lpk==0:
            continue
        elif maxIndex==3:
            cs = 1 if lpk> 0 else -1
            if (zbf-kbf-lpk)*cs == 0.25:
                expect += sxpeilv*0.5
            elif (zbf-kbf-lpk)*cs>0:
                expect += sxpeilv
            elif (zbf-kbf-lpk)*cs == -0.25:
                expect -= 0.5
            else:
                expect = expect - 1
        elif maxIndex==4:
            cs = 1 if lpk > 0 else -1
            if (zbf-kbf-lpk)*cs == -0.25:
                expect += sxpeilv*0.5
            elif (zbf-kbf-lpk)*cs<0:
                expect += sxpeilv
            elif (zbf-kbf-lpk)*cs == 0.25:
                expect -= 0.5
            else:
                expect = expect - 1
    expect = round(expect,2)
    # print(('时间:%s-%s,期望:%s')%(bisaishijian,bisaishijianend,expect))
    # print(results)
    return expect,totalCount,yesCount,results

def checkIsConflict(conflictVal,smax,pmax,fmax,shmax,xmax,pankou):

    if  (smax>conflictVal and pmax>conflictVal) or (pmax>conflictVal and fmax>conflictVal) or \
        (fmax>conflictVal and smax>conflictVal) or (shmax>conflictVal and xmax>conflictVal) or \
        (pankou>0 and smax>conflictVal and xmax>conflictVal) or (pankou>0 and (pmax>conflictVal or fmax>conflictVal) and shmax>conflictVal ) or \
        (pankou<0 and fmax>conflictVal and xmax>conflictVal) or (pankou<0 and (smax>conflictVal or pmax>conflictVal) and shmax>conflictVal):
        return True
    return False


def comMoney():
    database = DB.Database('localhost', 'root', 'root', 'sports')
    money = 300
    num = 30
    pm = money / num
    maxMoney = money
    print(("初始资金:%s") % (money))
    maxExpect = -100
    maxj = 20
    maxm = 110
    maxToc = 0
    maxYc = 0
    for j in range(0, 30, 100):
        for m in range(1200, 1340, 1000):
            expect = 0
            tCount = 0
            pCount = 0
            d = datetime.datetime.strptime('2019-12-18', '%Y-%m-%d')
            for i in range(1, 16):
                d1 = (d + datetime.timedelta(days=i)).date()
                d2 = (d + datetime.timedelta(days=i + 1)).date()
                cuExpect3, totalCount3, yesCount3 = 0, 0, 0
                cuExpect, totalCount, yesCount = 0, 0, 0
                cuExpect21, totalCount21, yesCount21 = 0, 0, 0
                cuExpect22, totalCount22, yesCount22 = 0, 0, 0

                # cuExpect3, totalCount3, yesCount3, results3 = compute(database, d1, d2, 0, 1.23, 11, 0)

                cuExpect,totalCount,yesCount,results = compute(database,d1,d2,0,1.2,21,1)

                # cuExpect21, totalCount21, yesCount21,results22 = compute(database, d1, d2, 0, 1.28, 7, 2,1)
                # cuExpect22, totalCount22, yesCount22, results22 = compute(database, d1, d2, 0, 1.2, 6, 2,0)
                cuExpect2 = cuExpect21+cuExpect22
                totalCount2 = totalCount21+totalCount22
                yesCount2 = yesCount21+yesCount22

                cuExpect += cuExpect2+cuExpect3
                totalCount += totalCount2+totalCount3
                yesCount += yesCount2+yesCount3

                # cuExpect, totalCount, yesCount, results = compute(database, '2019-11-03', '2019-12-18', 0, m / 1000, j,2,1)
                money += pm * cuExpect
                pCount += yesCount
                tCount += totalCount
                print(("时间:%s,期望:%s,资金:%s,总盘数:%s,命中盘数:%s,命中率:%s") % (d1,cuExpect,round(money,2),totalCount,yesCount,
                                                                     round(yesCount / totalCount, 2) if totalCount > 0 else 0))
                if money>maxMoney:
                    maxMoney = money
                    pm = money/num

                expect += cuExpect

            print(('条件阈值:%s,期望阈值:%s,期望总和:%s,预测总盘数:%s,预测命中总盘数:%s,比例:%s,场均:%s')
              % (j, m/1000, round(expect, 2), tCount, pCount, round(pCount / tCount, 3) if tCount > 0 else 0,
                 round(expect/tCount,2) if tCount > 0 else 0))
            if expect > maxExpect:
                maxj = j
                maxm = m
                maxExpect = expect
                maxToc = tCount
                maxYc = pCount
    print("-------------------------------------")
    print("最大期望:%s,条件阈值%s,期望阈值:%s,总盘数:%s,命中盘数:%s,命中率:%s,场均期望:%s" %
          (round(maxExpect, 2), maxj, maxm/1000, maxToc, maxYc, round(maxYc / maxToc, 3) if maxToc > 0 else 0,round(maxExpect/maxToc,2)))


def getRules(database):

    ruleMap = {}
    for x in range(1,30,1):
        for y in range(120,135,1):
            cuExpect, totalCount, yesCount, results = compute(database, '2019-10-01', '2019-11-01', 0, y / 100, x)
            for r in results.keys():
                pDict = {}
                tArr = results[r]
                if not ruleMap.__contains__(r) :
                    for i in range(len(tArr)):
                        rDict = {'condyz':x,'expectyz':y,'expect':tArr[i]}
                        pDict[i] = rDict
                    ruleMap[r] = pDict
                else:
                    pDict = ruleMap[r]
                    for i in range(len(tArr)):
                        if pDict[i]['expect']<tArr[i]:
                            rDict = {'condyz':x,'expectyz':y,'expect':tArr[i]}
                            pDict[i] = rDict
                    ruleMap[r] = pDict
    print(ruleMap)
    texpect = 0
    for pk in ruleMap.keys():
        for rs in ruleMap[pk].keys():
            texpect += ruleMap[pk][rs]['expect']
    print(texpect)

    dictfile = open("myfile", 'wb')
    pickle.dump(ruleMap, dictfile)
    dictfile.close()

def computeByRules(database,bisaishijian,bisaishijianend):
    dictfile = open("myfile", 'rb')
    ruleMap = pickle.load(dictfile)
    minCondyz = 100
    for pk in ruleMap.keys():
        for rs in ruleMap[pk].keys():
            if ruleMap[pk][rs]['expect']>0:
                minCondyz = minCondyz if minCondyz<=ruleMap[pk][rs]['condyz'] else ruleMap[pk][rs]['condyz']

    print(ruleMap)
    ouPeiList = DB.queryOupei(database)
    peilvMap = {}
    results={}
    for i in ouPeiList:
        i =  list(i)
        pankou = i[0]
        del i[0]
        peilvMap[pankou]=i

    forecastList = DB.queryResult(database,bisaishijian,bisaishijianend,0,minCondyz)
    yesCount=0
    totalCount = 0
    # 默认期望
    expect = 0

    for x in forecastList:
        j = list(x)
        lpk = j[0]
        if peilvMap[lpk] is None:
            print(("盘口为%s,找不到欧赔,跳过")%(lpk))
        if not ruleMap.__contains__(lpk):
            print("盘口为%s,找不到条件,跳过"%(lpk))
        zbf = j[1]
        kbf = j[2]
        del j[0]
        del j[0]
        del j[0]
        ctArr = j[3:6]
        j=j[:3]


        dMap = ruleMap[lpk]

        for cIndex in dMap.keys():
            if dMap[cIndex]['expect'] <=0:
                continue
            qwyuzhi = dMap[cIndex]['expectyz']/100
            countYuzhi = dMap[cIndex]['condyz']
            eArr = []
            for ix in range(len(j)):
                if j[ix]*(peilvMap[lpk][ix]-0.05)<qwyuzhi or ctArr[ix]<countYuzhi:
                    eArr.append(-1)
                else:
                    eArr.append(j[ix]*(peilvMap[lpk][ix]-0.05))
            maxIndex = eArr.index(max(eArr))
            maxVal = eArr[maxIndex]
            if maxVal == 999 or maxVal==-1:
                continue
            if not results.__contains__(lpk):
                results[lpk]=[0,0,0]
            if cIndex == maxIndex:
                if (maxIndex == 0 and zbf>kbf) or (maxIndex == 1 and zbf==kbf) or (maxIndex == 2 and zbf<kbf):
                    expect += peilvMap[lpk][maxIndex]-1.05
                    yesCount += 1
                    totalCount += 1
                    results[lpk][maxIndex] += peilvMap[lpk][maxIndex]-1.05
                else:
                    expect = expect - 1
                    totalCount += 1
                    results[lpk][maxIndex] += -1
                break

    expect = round(expect,2)
    print(expect,totalCount,yesCount,results)
    return expect,totalCount,yesCount,results


if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    # getRules(database)
    # computeByRules(database,'2019-11-01','2019-12-01')
    comMoney()