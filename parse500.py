import urllib

import requests
from bs4 import BeautifulSoup

import database as DB
import datetime
from changeUtils import changeleixing, changeTeamName, dealPankou, gzipData, deflate, checkContain

requests.adapters.DEFAULT_RETRIES = 5
s = requests.session()
s.keep_alive = False

def parseData(bisaiDate,leixings):
    tempUrl = 'http://live.500.com/wanchang.php?e='+bisaiDate
    req = urllib.request.Request(tempUrl)
    req.add_header('Accept','text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')
    req.add_header('Accept-Language', 'zh-CN,zh;q=0.9')
    req.add_header('Accept-Encoding', 'gzip,deflate')
    req.add_header('Cache-Control','max-age=0')
    req.add_header('Connection', 'keep-alive')
    req.add_header('Cookie',
                   '__utmz=63332592.1563862138.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; bdshare_firstime=1563862141629; ck_RegFromUrl=http%3A//live.500.com/; sdc_session=1565686167748; Hm_lvt_4f816d475bb0b9ed640ae412d6b42cab=1563862133,1565686168; __utmc=63332592; ck_RegUrl=live.500.com; __utma=63332592.1207027776.1563862138.1565761436.1566199533.7; motion_id=1566200339532_0.8839423026115882; Hm_lpvt_4f816d475bb0b9ed640ae412d6b42cab=1566200939; WT_FPC=id=undefined:lv=1566200939417:ss=1566199531890; sdc_userflag=1566199531894::1566200939423::2; CLICKSTRN_ID=223.68.192.125-1563862133.804552::0FC4B13DEF23459EB431241528984585; __utmt=1; __utmb=63332592.2.10.1566199533')
    req.add_header('Host','live.500.com')
    req.add_header('Upgrade-Insecure-Requests','1')
    req.add_header('User-Agent',
                   "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3809.100 Safari/537.36")
    req.add_header('Content-Type','text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')


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
        leixing,leixing2 = changeleixing(tr.select('.ssbox_01 a')[0].get_text().strip())
        if leixing not in leixings and (leixing2 == '' or (leixing2 != '' and leixing2 not in leixings)):
            continue
        zhudui = changeTeamName(tr.select('.p_lr01 a span')[0].get_text().strip())
        kedui = changeTeamName(tr.select('.p_lr01 a span')[1].get_text().strip())

        zhubifen = tr.select('.clt1')[0].get_text().strip()
        kebifen = tr.select('.clt3')[0].get_text().strip()
        linchangpankou = dealPankou(tr.select('.fhuise')[0].get_text().strip())

        if zhubifen=='' or kebifen=='':
            continue
        tId = tr.attrs['id']


        element = {'id':tId,'zhudui':zhudui,'kedui':kedui,'zhubifen':zhubifen,'kebifen':kebifen,'linchangpankou':linchangpankou}
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

        #print(leixing+" "+ zhudui+ " "+kedui+" "+zhubifen+":"+kebifen)
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

def updateBifen(bisaiDate,leixings,matchs):
    print(str(bisaiDate[0]))
    map = parseData(str(bisaiDate[0]), leixings)

    afterDay = (bisaiDate[0]+datetime.timedelta(days=1)).strftime("%Y-%m-%d")

    if datetime.datetime.now().strftime("%Y-%m-%d") != afterDay:
        map2 = parseData(afterDay, leixings)

        for leixing in map2:
            arr = map2[leixing]
            if leixing in map:
                map[leixing] = map[leixing]+arr
            else:
                map[leixing] = arr

    updateDatas = []

    for match in matchs:
        bisaileixing = match[0]
        zhudui = match[1]
        kedui = match[2]
        if not map.__contains__(bisaileixing):
            continue
        results = map[bisaileixing]

        for result in results:
            if checkContain(zhudui,result['zhudui']) and checkContain(kedui,result['kedui']):
                lpakou = result['linchangpankou']
                if lpakou is None or lpakou == '':
                    lpakou = getlpk(result['id'])
                updateData = [result['zhubifen'],result['kebifen'],lpakou,bisaiDate,bisaileixing,zhudui,kedui]
                updateDatas.append(tuple(updateData))
                continue

    DB.updateBifen(database,tuple(updateDatas))


def updateTask(database):
    dates = DB.queryNotAnalysisDate(database)
    if dates is None or len(dates) == 0:
        return
    for bisaiDate in dates:
        try:
            leixings = DB.queryLeixingByDate(database,bisaiDate)
            teams = DB.queryByDate(database,bisaiDate)
            lxs = []
            for lx in leixings:
                lxs.append(lx[0])

            updateBifen(bisaiDate,lxs,teams)
        except Exception as e:
            print(e)



if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    updateTask(database)
    #lexings = ['韩足杯','芬甲']
    #parseData('2019-07-02',lexings)