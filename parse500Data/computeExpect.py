import database as DB

conflictVal = 0.6


def compute(database,bisaishijian,bisaishijianend,yuzhi=0.75):
    ouPeiList = DB.queryOupei(database)
    peilvMap = {}
    sxpeilv = 0.85

    for i in ouPeiList:
        i =  list(i)
        pankou = i[0]
        del i[0]
        peilvMap[pankou]=i

    forecastList = DB.queryResult(database,bisaishijian,bisaishijianend,yuzhi)
    #默认期望
    expect = 0
    for j in forecastList:
        j = list(j)
        lpk = j[0]
        if peilvMap[lpk] is None:
            print(("临场盘口为%s,找不到欧赔,跳过")%(lpk))
        zbf = j[1]
        kbf = j[2]
        del j[0]
        del j[0]
        del j[0]
        #检查是否冲突
        # if checkIsConflict(conflictVal,j[0],j[1],j[2],j[3],j[4],lpk):
        #     continue
        maxIndex = j.index(max(j))
        #maxVal = j[maxIndex]
        if maxIndex in [0,1,2] :
            if (peilvMap[lpk][maxIndex])*j[maxIndex]<=1.02:
                continue
            if (maxIndex == 0 and zbf>kbf) or (maxIndex == 1 and zbf==kbf) or (maxIndex == 2 and zbf<kbf):
                expect += peilvMap[lpk][maxIndex]-1
            else:
                expect = expect - 1
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
    print(('时间:%s-%s,期望:%s')%(bisaishijian,bisaishijianend,expect))

def checkIsConflict(conflictVal,smax,pmax,fmax,shmax,xmax,pankou):

    if  (smax>conflictVal and pmax>conflictVal) or (pmax>conflictVal and fmax>conflictVal) or \
        (fmax>conflictVal and smax>conflictVal) or (shmax>conflictVal and xmax>conflictVal) or \
        (pankou>0 and smax>conflictVal and xmax>conflictVal) or (pankou>0 and (pmax>conflictVal or fmax>conflictVal) and shmax>conflictVal ) or \
        (pankou<0 and fmax>conflictVal and xmax>conflictVal) or (pankou<0 and (smax>conflictVal or pmax>conflictVal) and shmax>conflictVal):
        return True
    return False


if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    for i in range(1,24):
        i=i if i>9 else '0'+str(i)
        d = '2019-09-%s'%(i)
        compute(database,d,d,0.75)