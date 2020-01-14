import urllib
import requests
from bs4 import BeautifulSoup
import gzip
from io import StringIO,BytesIO
import zlib
import database as DB
import datetime
from changeUtils import changeleixing, changeTeamName, dealPankou, gzipData, deflate, checkContain

requests.adapters.DEFAULT_RETRIES = 5
s = requests.session()
s.keep_alive = False

def parseData(bisaiDate,leixings):
    tempUrl = 'https://live.leisu.com/wanchang?date='+str(bisaiDate)
    req = urllib.request.Request(tempUrl)
    req.add_header('Accept',
                   'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3')
    req.add_header('Accept-Language', 'zh-CN,zh;q=0.9')
    req.add_header('Accept-Encoding', 'gzip,deflate')
    req.add_header('Cache-Control', 'max-age=0')
    req.add_header('Connection', 'keep-alive')
    req.add_header('Cookie','acw_tc=2f61f27c15662043202395048e4426defba93f49a45f4cf8db50cc5b36835a; lang=; PHPSESSID=rv690j4lh7gqsuh29qrrhs3qb3; Hm_lvt_63b82ac6d9948bad5e14b1398610939a=1566204298,1566204368,1566204377; SERVERID=4ab2f7c19b72630dd03ede01228e3e61|1566974507|1566974422; Hm_lpvt_63b82ac6d9948bad5e14b1398610939a=1566974507')

    req.add_header('Host', 'live.leisu.com')
    req.add_header('If-None-Match','W/"fe010-hQk3q4B/k1m4zDOwdbMVMB9r2hI"')
    req.add_header('Sec-Fetch-Mode','navigate')
    req.add_header('Sec-Fetch-Site','none')
    req.add_header('Sec-Fetch-User','?1')

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
    soup = BeautifulSoup(content, 'html.parser')
    trs = soup.select('li .clearfix-row')
    map = {}


    for tr in trs:
        leixing, leixing2 = changeleixing(tr.select('.event-name span')[0].get_text().strip())
        if leixing not in leixings and (leixing2 == '' or (leixing2 != '' and leixing2 not in leixings)):
            continue
        zhudui = changeTeamName(tr.select('.lab-team-home .name')[0].get_text().strip())
        kedui = changeTeamName(tr.select('.lab-team-away .name')[0].get_text().strip())

        zhubifen = tr.select('.lab-score .color-red')[0].get_text().strip().split('-')[0]
        kebifen = tr.select('.lab-score .color-red')[0].get_text().strip().split('-')[1]
        linchangpankou = dealPankou(tr.select('.lab-ratel')[0].get_text().strip())
        if linchangpankou is None:
            continue
        element = {'zhudui': zhudui, 'kedui': kedui,'linchangpankou': linchangpankou,'zhubifen':zhubifen,'kebifen':kebifen}
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

def updatePankou(bisaiDate,leixings,matchs):
    print(str(bisaiDate[0]))
    curDay = (bisaiDate[0]).strftime("%Y%m%d")
    map = parseData(curDay, leixings)

    afterDay = (bisaiDate[0] + datetime.timedelta(days=1)).strftime("%Y%m%d")

    if datetime.datetime.now().strftime("%Y%m%d") != afterDay:
        map2 = parseData(afterDay, leixings)

        for leixing in map2:
            arr = map2[leixing]
            if leixing in map:
                map[leixing] = map[leixing] + arr
            else:
                map[leixing] = arr

    updateDatas = []

    for match in matchs:
        bisaileixing = match[0]
        zhudui = match[1]
        kedui = match[2]
        zhubifen = match[3]
        kebifen = match[4]
        if not map.__contains__(bisaileixing):
            continue
        results = map[bisaileixing]

        for result in results:
            if checkContain(zhudui, result['zhudui']) and checkContain(kedui, result['kedui']):
                if zhubifen == int(result['zhubifen']) and kebifen == int(result['kebifen']):
                    updateData = [result['linchangpankou'], bisaiDate, bisaileixing,
                                  zhudui, kedui]
                    updateDatas.append(tuple(updateData))
                    continue

    DB.updatePankou(database, tuple(updateDatas))



def updateTask(database):
    dates = DB.queryNoPankouDates(database)
    if dates is None or len(dates) == 0:
        return
    for bisaiDate in dates:
        try:
            leixings = DB.queryNoPankouLeixingByDate(database,bisaiDate)
            teams = DB.queryNoPankouByDate(database,bisaiDate)
            lxs = []
            for lx in leixings:
                lxs.append(lx[0])

            updatePankou(bisaiDate,lxs,teams)
        except Exception as e:
            print(e)




if __name__ == '__main__':
    database = DB.Database('localhost', 'root', 'root', 'sports')
    updateTask(database)
    #lexings = ['韩足杯','芬甲']
    #parseData('2019-07-02',lexings)