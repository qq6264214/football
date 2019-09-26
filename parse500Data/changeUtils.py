import gzip
from io import StringIO,BytesIO
import zlib


teams = {
    '马特斯': '莱格尼察',
    '伊罗多度士': '艾罗多度士',
    '高沙根男生': '科萨肯',
    '维特斯': '维迪斯',
    '雷克斯欧斯': '雷克索',
    '科瓦皮耶达迪': '科瓦彼',
    '基尔史莫纳夏普尔': '谢莫纳',
    '拉那那夏普尔': '赖阿南纳',
    '邓根伦': '杜根伦',
    '基拿云': '格莱纳',
    '克里夫顿维尔':'克利夫',
    '克鲁萨德': '十字军',
    '马斯里': '马萨雷',
    '艾尔格纳': '高纳',
    '古比斯': '库普斯',
    '英特土尔库': '国际图',
    '首尔埃兰': '首尔衣恋',
    'FC安养': '安阳FC',
    '马夫拉': '马弗拉',
    '艾路卡': '阿罗卡',
    '自由州星队':'自由洲明',
    '贝联': '波洛克瓦',
    '登克尔克': '邓多克',
    '费恩夏普': '费恩哈',
    '阿玛盖尔': '阿马盖尔',
    '尼克宾': '罗兰法',
    '靴尔科治': 'HB科治',
    '科灵': '科尔IF',
    'HNK哥里卡': '戈里察',
    '里杰卡': '里耶卡',
    '塞林西尼': '阿吉斯',
    '赤尼达塔高维斯': '辛迪泰',
    '斯塔尔南': '加尔扎',
    '维京古': '维京格',
    '卡迪斯': '加的斯',
    '阿尔拉扬': '赖扬',
    '欧斯特':'欧斯蒂',
    '奥斯杰克':'奥西耶',
    '隆德里纳':'隆迪那',
    '捍卫者竞技':'德芬索',
    # '图拉':'图兵工厂',
    '图兵工厂':'图拉',
    '卢克尼奥体育':'卢捷诺',
    '阿尔艾利开罗':'开罗阿赫',
    '奥迪沃特':'奥迪沃',
    '拉菲奥里塔':'拉费奥',
    '英奥达尼':'英奥达',
    '韦纳穆':'瓦纳默',
    '奥斯积克':'奥西耶',
    '埃斯克年青人':'埃施',
    'FC圣科洛马': '圣科FC',
    '布罗雷': '布罗姆',
    '阿根廷青年人': '阿青年',
    '比紹夫斯韋達FV': '比绍夫斯',
    '超体联盟':'超级竞技',
    '毕德威斯特大学':'华特斯',
    '贝联':'波洛克瓦',
    '莫雷坎比 ': '莫克姆',
    '图塔杜雷斯': '都拉斯',
    'GFC阿雅克肖': '阿雅GF',
    'FC巴黎': '巴黎FC',
    '伊塔尔': '依塔',
    '阿富拉夏普尔': '阿福拉',
    '迪克瓦夏普尔': '彼达',
    '切奥尔雷': '卓利',
    #'斯托克港':'南港队',





}

matchType={
    '捷乙': '捷克乙',
    '苏联杯': '苏联赛杯',
    '巴西甲': '巴甲',
    '新加坡联': '新加联',
    '日职': '日职联',
    '阿甲': '阿超',
    '阿尔巴超':'阿巴超',
    '世外亚洲':'世亚预'
}


def changeTeamName(teamName):
    if teamName == '纽卡斯尔喷气机':
        return '纽喷射'
    elif teamName == '谢菲尔德星期三':
        return '谢周三'
    elif teamName == '阿克宁顿':
        return '阿克灵'
    elif teamName == '汉密尔顿':
        return '哈密尔'
    elif teamName == '巴塞罗那':
        return '巴萨'
    elif teamName == '老虎竞技':
        return '泰格雷'
    elif teamName == '圣胡安圣马丁':
        return '圣马SJ'
    elif teamName == '圣菲联合':
        return '圣塔菲'
    elif teamName == '防御与正义':
        return '防卫者'
    elif teamName == '云达不莱梅':
        return '不来梅'
    elif teamName == '阿波隆利马索尔':
        return '阿波罗'
    elif teamName == '贝弗伦':
        return '华斯兰'
    elif teamName == '亚松森自由':
        return '自由队'
    elif teamName == '博雷斯拉夫':
        return '博莱斯'
    elif teamName == '波特兰伐木工':
        return '伐木者'
    elif teamName == 'CS卡拉奥华大学':
        return '克拉约'
    elif teamName == '老虎大学':
        return '塔格雷'
    elif teamName == '桑纳菲尤尔':
        return '桑德'
    elif teamName == '墨西哥美洲':
        return '美洲队'
    elif teamName == '阿雷索':
        return '阿雷佐'

    elif teamName == '圣本图':
        return '萨本托'
    elif teamName == '约森独立队':
        return '德尔瓦'
    elif teamName == '格里姆斯塔':
        return '谢夫'
    elif teamName == '因弗内斯':
        return '伊凡尼'
    #leisu
    elif teamName == '梅迪亚什沼气':
        return '加斯梅'
    elif teamName == '乔治罗尼亚':
        return '比亚韦'
    elif teamName == '比达耶路撒冷':
        return '贝塔'
    elif teamName == '阿洛顿':
        return '阿隆顿'
    elif teamName == '史拉斯科':
        return '斯拉斯'
    elif teamName == '希尔星格':
        return '比利希'
    elif teamName == '布奈萨克宁哈普尔':
        return '比尼萨'
    elif teamName == '阿舒多':
        return '阿什杜'
    elif teamName == '华森':
        return '瓦兹姆'
    elif teamName == 'SKA哈巴罗夫斯克':
        return 'SKA恩'
    elif teamName == 'FK秋明':
        return  '图们'
    elif teamName == '希姆基':
        return '卡希梅'
    elif teamName == '圣马洛':
        return '圣马罗'
    elif teamName == '阿尔菲斯':
        return '哈萨法'
    elif teamName == '活赛恩':
        return '侯赛恩'
    elif teamName == '艾尔利':
        return '阿尔利'
    elif teamName == '贝鲁扎达':
        return 'CR比罗'
    elif teamName == '塞堤夫':
        return '塞提夫'
    elif teamName == '柏拉道':
        return '巴拉特'
    elif teamName == '奥帕瓦':
        return '欧帕瓦'
    elif teamName == '沙斯辛':
        return '施切钦'
    elif teamName == '阿斯特拉':
        return '久尔久'
    elif teamName == '沃斯克拉':
        return '沃斯卡'
    elif teamName == '比尔舒华夏普尔':
        return '贝尔谢'
    elif teamName == '宾菲加B队':
        return '本菲卡B'
    elif teamName == '皮尔森':
        return '比尔森'
    elif teamName == '艾沙赫':
        return '阿尔萨'
    elif teamName == '库尼奥1905':
        return '古尼奥'
    elif teamName == '瑞普斯威尔':
        return '拉珀斯'
    elif teamName == '格罗巴斯帕奇':
        return '格罗萨'
    elif teamName in teams:
        return teams[teamName]

    else:
        return teamName


def changeleixing(bisaileixing):
    if bisaileixing == '美冠杯':
        return bisaileixing,'中北美冠'
    elif bisaileixing == '捷甲':
        return bisaileixing,'捷克甲'
    elif bisaileixing == '智甲':
        return bisaileixing,'智利甲'
    elif bisaileixing == '墨西联':
        return bisaileixing,'墨超'
    elif bisaileixing == '英足总杯':
        return bisaileixing,'足总杯'
    elif bisaileixing == '亚冠杯':
        return bisaileixing,'亚冠联赛'
    elif bisaileixing == '墨西杯':
        return bisaileixing,'墨西哥杯'
    elif bisaileixing == '埃及甲':
        return bisaileixing,'埃及超'
    elif bisaileixing == '韩足杯':
        return bisaileixing,'韩足总杯'
    elif bisaileixing == '巴乙':
        return bisaileixing,'巴西乙'
    elif bisaileixing == '欧冠':
        return bisaileixing,'欧冠联赛'
    elif bisaileixing == '乌超':
        return bisaileixing,'乌克超'
    elif bisaileixing == '欧罗巴':
        return bisaileixing,'欧罗巴联赛'
    elif bisaileixing == '波甲' or bisaileixing == '波兰超':
        return bisaileixing,'波兰甲'
    elif bisaileixing == '比杯':
        return bisaileixing,'比利时杯'
    elif bisaileixing == '葡杯':
        return bisaileixing,'葡萄牙杯'
    elif bisaileixing == '土杯':
        return bisaileixing,'土耳其杯'
    elif bisaileixing == '俄杯':
        return bisaileixing,'俄罗斯杯'
    elif bisaileixing == '西班牙杯':
        return bisaileixing,'国王杯'
    elif bisaileixing == '荷杯':
        return bisaileixing,'荷兰杯'
    elif bisaileixing == '苏联赛杯':
        return bisaileixing,'苏联杯'
    elif bisaileixing == '吉尼斯杯':
        return bisaileixing,'国际冠军杯'
    elif bisaileixing == '欧U21外':
        return bisaileixing,'欧洲U21'
    elif bisaileixing == '世青赛':
        return bisaileixing,'U20世界杯'
    elif bisaileixing == '英亚杯':
        return bisaileixing,'英超亚洲杯'
    elif bisaileixing == '爱联杯':
        return bisaileixing,'爱联赛杯'
    elif bisaileixing == '丹麦甲':
        return bisaileixing,'丹甲'
    elif bisaileixing == '沙特超':
        return bisaileixing,'沙特联'
    elif bisaileixing == '阿尔甲':
        return bisaileixing,'阿尔及甲'
    elif bisaileixing == '约超联':
        return bisaileixing,'约旦超'
    elif bisaileixing == '德丙':
        return bisaileixing,'德丙联'
    elif bisaileixing == '意丙1B':
        return bisaileixing,'意丙1A'
    elif bisaileixing == '挪甲':
        return bisaileixing,'挪超'
    elif bisaileixing == '韩K联':
        return bisaileixing,'K1联赛'
    elif bisaileixing == '英议联':
        return bisaileixing,'英非联'
    elif bisaileixing == '英议北':
        return bisaileixing,'英非北'
    elif bisaileixing == '国际友谊':
        return bisaileixing,'友谊赛'
    elif bisaileixing == '里约锦标':
        return bisaileixing,'里洲赛'
    elif bisaileixing == '墨西哥杯':
        return bisaileixing,'墨西杯'
    elif bisaileixing == '丹麦超':
        return bisaileixing,'丹超'
    elif bisaileixing == '韩挑K联':
        return bisaileixing,'K2联赛'
    elif bisaileixing == '哥伦甲秋':
        return bisaileixing,'哥甲'
    elif bisaileixing == '秘鲁甲秋':
        return bisaileixing,'秘鲁甲'
    elif bisaileixing == '巴拉甲秋':
        return bisaileixing,'巴拉联'
    elif bisaileixing == '克亚甲':
        return bisaileixing,'克罗甲'
    elif bisaileixing == '冰岛甲':
        return bisaileixing,'冰甲'
    elif bisaileixing == '南球杯':
        return bisaileixing,'南俱杯'
    elif bisaileixing == '埃及超':
        return bisaileixing,'埃及甲'
    elif bisaileixing == '苏总杯':
        return bisaileixing,'苏足总杯'
    elif bisaileixing == '自由杯':
        return bisaileixing,'解放者杯'
    elif bisaileixing == '欧联杯':
        return bisaileixing,'欧罗巴联赛'
    elif bisaileixing == '欧青U19':
        return bisaileixing,'欧U19'
    elif bisaileixing == '欧冠杯':
        return bisaileixing,'欧冠联赛'
    elif bisaileixing == '哥斯甲春':
        return bisaileixing,'哥斯甲'
    elif bisaileixing == '英议南':
        return bisaileixing,'英非南'
    elif bisaileixing == '格鲁甲':
        return bisaileixing,'格超'
    elif bisaileixing == '德地区北':
        return bisaileixing,'德北联'
    elif bisaileixing in matchType:
        return bisaileixing,matchType[bisaileixing]
    else:
        return bisaileixing,''


def dealPankou(pankou):
    flag= False
    num = 0
    if '受' in pankou:
        flag= True
        pankou = pankou[1:]
    if pankou == '-' or pankou is None or pankou == '':
        return None
    elif '/' in pankou:
        arr = pankou.split('/')
        num1 = getNumPankou(arr[0])
        num2 = getNumPankou(arr[1])
        num = (num1 + num2) / 2
    else:
        num = getNumPankou(pankou)
    if flag:
        return 0-num
    else:
        return num

def getNumPankou(str):

    if len(str) == 2 or len(str) == 1:
        if str == u'平手' or str == '平':
            return 0
        elif str == u'半球' or str == '半':
            return 0.5
        elif str == u'球半':
            return 1.5
        elif str.find(u'一') == 0:
            return 1
        elif str.find(u'两') == 0:
            return 2
        elif str.find(u'三') == 0:
            return 3
        elif str.find(u'四') == 0:
            return 4
        elif str.find(u'五') == 0:
            return 5
        elif str.find(u'六') == 0:
            return 6
        elif str.find(u'七') == 0:
            return 7
        elif str.find(u'八') == 0:
            return 8
        elif str.find(u'九') == 0:
            return 9
        elif str.find(u'十') == 0:
            return 10

    elif len(str) == 3:
        if str.find(u'一') == 0:
            return 1.5
        elif str.find(u'两') == 0:
            return 2.5
        elif str.find(u'三') == 0:
            return 3.5
        elif str.find(u'四') == 0:
            return 4.5
        elif str.find(u'五') == 0:
            return 5.5
        elif str.find(u'六') == 0:
            return 6.5
        elif str.find(u'七') == 0:
            return 7.5
        elif str.find(u'八') == 0:
            return 8.5
        elif str.find(u'九') == 0:
            return 9.5
        elif str.find(u'十') == 0:
            return 10.5

def gzipData(data):

    buf = BytesIO(data)
    f = gzip.GzipFile(fileobj=buf)
    return f.read()


def deflate(data):
    try:
        return zlib.decompress(data, -zlib.MAX_WBITS)
    except zlib.error:
        return zlib.decompress(data)

def checkContain(chars,str):
    indexPosition = -1
    for char in iter(chars):
        if str.find(char,indexPosition+1)>indexPosition:
            indexPosition = str.find(char,1+indexPosition)
        else:
            return False
    return True

arr1 = {"peilv","peilvbianhua","qushipianlizhi","shengfuqushicha","zonghefengxianzhishu","zucaizhishu",
            "lengrezhishu","peilvqiwang","peilvqujian","zhibiao","zhibiaoqujianbi","kailifangchabi","lisanzhicha","peifuzhishu",
            "peifuzhishuxin","bocaiyinglizhishu","chuxuanzhishu","xuandanzhishu","peilvlisanzhi1","peilvlisanzhi2","lisanzhibianhualv",
            "dingdanzhishu","baolengzhishu","kailizhishuchu","kailizhishuji","kailibianhualv","kaipeicha","pianlizhi","zhongzhipianlizhi","pianlilv",
            "zonghepianlizhi","fangcha","peilvfanhuanchaqujian","peilvfanhuancha","zonghepingjia1","zonghepingjia2","zonghepingjia3"}

arr2 = {"peilvzhongzhi","pingjunpeilv","pingjunpeilvcha","shenglvzhongzhi","pingjunshenglv","pingjunshenglvcha","kailizhishuzhongzhi",
            "kailizhishu","baolengzhishuxin","peilvqiwangjunzhi","lisanzhi","kaipeifangchahe","kaipeichagaodicha",
            "youshizhishu1","youshizhishu2","youshizhishu3","youshizhishu4","youshizhishu5","youhuayoushi","zhongjiyoushi","youshicha",
            "","shaixuanbi","shaixuancha","zhongjishaixuan","","fa","fb","chupeizuigaopeilv","jishizuigaopeilv","zuigaopeilvbianhua",
            "chupeizuidipeilv","jishizuidipeilv","zuidipeilvbianhua","chupeipingjunpeilv","jishipingjunpeilv","pingjunpeilvbianhua",
            "chupeizuigaoshenglv","jishizuigaoshenglv","zuigaoshenglvbianhua","chupeizuidishenglv","jishizuidishenglv","zuidishenglvbianhua",
            "chupeipingjunshenglv","jishipingjunshenglv","pingjunshenglvbianhua","yinglizhishu","fengxianzhishu","youshichayoubian",
            "weizhi","shaixuanbiyoubian","shaixuanchayoubian","zhongjishaixuanyoubian","","","zonghefangchabi"}

arr3 = {"lisanzhichu","lisanzhiji","lisanzhichachu","lisanzhichaji","","lisanzhishu","lisanbi","bianyilisanzhishu",
            "bianyilisanzhishuxia","qujianqiwangchaquan","qujianqiwangchazhu","fc","fd","qiwangfengxianbi","zonghezhishu",
            "peilvfangchahe","zonghefengxianbi","kailizhishucha","","","pellvqujianchu","peilvqujianji","peilvqujianbi",
            "","","","","peifuzhishutiaozhengxia1","peifuzhishutiaozhengxia2","peifuzhishutiaozhengxia3",
            "peifuzhishutiaozhengxia4","peifuzhishutiaozhengxia5","peifuzhishutiaozhengxia6","qiwangfangchabi",
            "junhengzhishu","lisanzhichuyoubian","lisanzhijiyoubian","lisanzhichachuyoubian","lisanzhichajiyoubian",
            "lisanzhibianhualvyoubian","lisanzhishuyoubian","lisanbiyoubian","bianyilisanzhishuyoubian",
            "bianyilisanzhishuxiayoubian","qujianqiwangchaquanyoubian","qujianqiwangchazhuyoubian","fcyoubian",
            "fdyoubian","qiwangfengxianbiyoubian","zonghezhishuyoubian","peilvfangchaheyoubian","zonghefengxianbiyoubian",
            "kailizhishuchayoubian","baolengzhishuyoubian","zhibiao1youbian","peilvqujianchuyoubian","peilvqujianjiyoubian",
            "peilvqujianbiyoubian","fengxianzhishuyoubian","zonghefengxianzhishuyoubian","peifuzhishuyoubian",
            "peifuzhishutiaozhengyoubian","peifuzhishutiaozhengxia1youbian","peifuzhishutiaozhengxia2youbian",
            "peifuzhishutiaozhengxia3youbian","peifuzhishutiaozhengxia4youbian","peifuzhishutiaozhengxia5youbian",
            "peifuzhishutiaozhengxia6youbian","qiwangfangchabiyoubian","junhengzhishuyoubian"}





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


def getPoint(colName):
    if colName in point2Arr:
        return 2
    elif colName in point3Arr:
        return 3
    elif colName in point4Arr:
        return 4
    elif colName in arrType2:
        return 2
    return

