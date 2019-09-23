import time
import database as DB
import urllib
from bs4 import BeautifulSoup
import random
import datetime
from changeUtils import changeleixing, changeTeamName, dealPankou, gzipData, deflate, checkContain
import requests

def updateMatchRealTime(database):
    Nowtimestamp = time.time()
    todayDate = time.strftime('%Y-%m-%d', time.localtime(Nowtimestamp))
    yestodayTimestamp = Nowtimestamp - 24 * 60 * 60
    yestodayDate = time.strftime('%Y-%m-%d', time.localtime(yestodayTimestamp))
    matchs = DB.queryNeedUpadteRealTime(database, todayDate, yestodayDate)
    if len(matchs) == 0:
        return
    leixingSet = set()
    for temp in matchs:
        leixingSet.add(temp[0])
    leixings = list(leixingSet)
    map = parseData(leixings)
    updateDatas = []
    for match in matchs:
        bisaileixing = match[0]
        bisaishijian = match[1]
        zhudui = match[2]
        kedui = match[3]
        if not map.__contains__(bisaileixing):
            continue
        results = map[bisaileixing]

        for result in results:
            if checkContain(zhudui, result['zhudui']) and checkContain(kedui, result['kedui']):
                updateData = [result['realTime'], bisaishijian, bisaileixing, zhudui, kedui]
                updateDatas.append(tuple(updateData))
                continue

    DB.updateRealTime(database, tuple(updateDatas))
    #保证每场比赛最多查找2下时间
    DB.updateAddRealTimeFlag(database,tuple(matchs))

def updateMacthLinchang(database):
    nowtimestamp = time.time()
    nowTime = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(nowtimestamp))
    endtimestamp = nowtimestamp + 29 * 60
    endtime = time.strftime('%Y-%m-%d %H:%M:%S', time.localtime(endtimestamp))
    matchs = DB.queryNeedUpdateLinchang(database, nowTime, endtime)
    if len(matchs) ==0:
        return
    leixingSet = set()
    for temp in matchs:
        leixingSet.add(temp[0])
    leixings = list(leixingSet)
    map = parseData(leixings,True)
    updateDatas = []
    for match in matchs:
        bisaileixing = match[0]
        bisaishijian = match[1]
        zhudui = match[2]
        kedui = match[3]
        if not map.__contains__(bisaileixing):
            continue
        results = map[bisaileixing]
        for result in results:
            if checkContain(zhudui, result['zhudui']) and checkContain(kedui, result['kedui']):
                lpakou = result['linchangpankou']
                if lpakou is None or lpakou == '':
                    lpakou = getlpk(result['id'])
                updateData = [lpakou, bisaishijian, bisaileixing, zhudui, kedui]
                updateDatas.append(tuple(updateData))
                continue
    DB.updateLinchang(database, tuple(updateDatas))
    # 保证每场比赛最多查找2下时间
    DB.updateAddLinchangFlag(database, tuple(matchs))



def parseData(leixings, isNeedPankou=False):
    tempUrl = 'https://live.500.com/2h1.php'
    req = urllib.request.Request(tempUrl)
    req.add_header('Accept',
                   'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')
    req.add_header('Accept-Language', 'zh-CN,zh;q=0.9')
    req.add_header('Accept-Encoding', 'gzip,deflate')
    req.add_header('Cache-Control', 'max-age=0')
    req.add_header('Connection', 'keep-alive')
    req.add_header('Cookie',
                   '__utmz=63332592.1563862138.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; bdshare_firstime=1563862141629; ck_RegFromUrl=http%3A//live.500.com/; sdc_session=1565686167748; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1563862133,1565686168; __utmc=63332592; ck_RegUrl=live.500.com; __utma=63332592.1207027776.1563862138.1565761436.1566199533.7; motion_id=1566200339532_0.8839423026115882; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1566200939; WT_FPC=id=undefined:lv=1566200939417:ss=1566199531890; sdc_userflag=1566199531894::1566200939423::2; CLICKSTRN_ID=223.68.192.125-1563862133.804552::0FC4B13DEF23459EB431241528984585; __utmt=1; __utmb=63332592.2.10.1566199533')
    req.add_header('Host', 'live.500.com')
    req.add_header('Upgrade-Insecure-Requests', '1')
    req.add_header('User-Agent',
                   "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/" + str(round(random.random() * 100, 2)) + ".3809.100 Safari/537.36")
    req.add_header('Content-Type',
                   'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')
    res = urllib.request.urlopen(tempUrl)
    content = res.read()
    encoding = res.info().get('Content-Encoding')
    if encoding == 'gzip':
        content = gzipData(content)
    elif encoding == 'deflate':
        content = deflate(content)
    soup = BeautifulSoup(content, 'html.parser')
    trs = soup.select('#table_match tbody tr')
    map = {}
    for tr in trs:
        if tr.attrs.__contains__('parentid'):
            continue
        leixing, leixing2 = changeleixing(tr.select('.ssbox_01 a')[0].get_text().strip())
        if leixing not in leixings and (leixing2 == '' or (leixing2 != '' and leixing2 not in leixings)):
            continue
        realTime = str(datetime.datetime.now().year)+'-'+tr.select('td')[3].get_text().strip()+':00'
        realTime = datetime.datetime.strptime(realTime,'%Y-%m-%d %H:%M:%S')
        zhudui = changeTeamName(tr.select('.p_lr01 a ')[0].get_text().strip())
        kedui = changeTeamName(tr.select('.p_lr01 a')[1].get_text().strip())
        tId = tr.attrs['id']
        if isNeedPankou:
            linchangpankou = dealPankou(tr.select('.tdp20')[0].get_text().strip())
            element = {'id': tId, 'zhudui': zhudui, 'kedui': kedui, 'linchangpankou': linchangpankou, 'realTime': realTime}
        else:
            element = {'id': tId, 'zhudui': zhudui, 'kedui': kedui,'realTime': realTime}
        elements = []
        if map.__contains__(leixing):
            elements = map.get(leixing)
        elements.append(element)
        map[leixing] = elements
        if leixing2 != '':
            elements = []
            if map.__contains__(leixing2):
                elements = map.get(leixing2)
            elements.append(element)
            map[leixing2] = elements
    return map

def getlpk(id):
    if id is None or id == '':
        return None
    id=id[1:]
    tempUrl = 'http://odds.500.com/fenxi/yazhi-'+id+'.shtml'
    req = urllib.request.Request(tempUrl)
    req.add_header('Accept',
                   'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')
    req.add_header('Accept-Language', 'zh-CN,zh;q=0.9')
    #req.add_header('Accept-Encoding', 'gzip,deflate')
    req.add_header('Cache-Control', 'max-age=0')
    req.add_header('Connection', 'keep-alive')
    req.add_header('Cookie',
                   '__utmz=63332592.1563862138.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; bdshare_firstime=1563862141629; ck_RegFromUrl=http%3A//live.500.com/; sdc_session=1565686167748; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1563862133,1565686168; __utmc=63332592; ck_RegUrl=live.500.com; __utma=63332592.1207027776.1563862138.1565761436.1566199533.7; motion_id=1566200339532_0.8839423026115882; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1566200939; WT_FPC=id=undefined:lv=1566200939417:ss=1566199531890; sdc_userflag=1566199531894::1566200939423::2; CLICKSTRN_ID=223.68.192.125-1563862133.804552::0FC4B13DEF23459EB431241528984585; __utmt=1; __utmb=63332592.2.10.1566199533')
    req.add_header('Host', 'odds.500.com')
    req.add_header('Upgrade-Insecure-Requests', '1')
    req.add_header('User-Agent',
                   "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3809.100 Safari/537.36")
    res = urllib.request.urlopen(tempUrl)
    content = res.read()
    encoding = res.info().get('Content-Encoding')
    if encoding == 'gzip':
        content = gzipData(content)
    elif encoding == 'deflate':
        content = deflate(content)
    content = content.decode("gb2312", 'ignore')
    soup = BeautifulSoup(content, 'html.parser')
    trs = soup.select("#datatb tbody tr")
    for tr in trs:
        tds = tr.select('.pl_table_data tbody tr td')
        if len(tds)>2:
            return dealPankou(tds[1].get_text().strip().split(' ')[0])
    return None




if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    updateMatchRealTime(database)
    # updateMacthLinchang(database)