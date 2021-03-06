# -*- coding: utf-8 -*-
import pymysql
import datetime

class Database():
    def __init__(self,host,user,pwd,db):
        self.host=host
        self.user=user
        self.pwd=pwd
        self.db=db
    def __GetConnect(self):
        self.conn = pymysql.connect(host=self.host,port=3306,user=self.user,passwd=self.pwd,database=self.db,charset='utf8')
        cur=self.conn.cursor()
        if not cur:
            raise(NameError,"连接数据库失败")
        else:
            return cur
    def execQuery(self,sql,args=None):
        cur = self.__GetConnect()
        cur.execute(sql,args)
        resList = cur.fetchall()
        self.conn.close()
        return resList
    def execNonQuery(self,sql,args=None):
        cur = self.__GetConnect()
        cur.execute(sql,args)
        self.conn.commit()
        self.conn.close()
    def execManyNonQuery(self,sql,args=None):
        cur = self.__GetConnect()
        cur.executemany(sql,args)
        self.conn.commit()
        self.conn.close()

#查询所有没比分的时间
def queryNotAnalysisDate(database):
    sql = 'SELECT bisaishijian FROM football_data WHERE linchangpankou IS NULL  GROUP BY bisaishijian ORDER BY bisaishijian ASC '
    return database.execQuery(sql)

def queryByDate(database,bisaishijian):
    sql = 'SELECT bisaileixing,zhudui,kedui FROM football_data WHERE linchangpankou IS NULL AND bisaishijian=%s'
    return database.execQuery(sql,(bisaishijian))

def queryLeixingByDate(database,bisaishijian):
    sql = 'SELECT bisaileixing FROM football_data WHERE linchangpankou IS NULL AND bisaishijian=%s GROUP BY bisaileixing'
    return database.execQuery(sql, (bisaishijian))

#查询所有临场盘口
def queryNoPankouDates(database):
    sql = 'SELECT bisaishijian FROM football_data WHERE linchangpankou IS NULL  GROUP BY bisaishijian ORDER BY bisaishijian DESC '
    return database.execQuery(sql)

def queryNoPankouByDate(database,bisaishijian):
    sql = 'SELECT bisaileixing,zhudui,kedui,zhubifen,kebifen FROM football_data WHERE linchangpankou IS NULL AND bisaishijian=%s'
    return database.execQuery(sql,(bisaishijian))

def queryNoPankouLeixingByDate(database,bisaishijian):
    sql = 'SELECT bisaileixing FROM football_data WHERE linchangpankou IS NULL AND bisaishijian=%s GROUP BY bisaileixing'
    return database.execQuery(sql, (bisaishijian))



#更新比赛结果
def updateBifen(database,result):
    sql = 'UPDATE football_data SET zhubifen=%s,kebifen=%s,linchangpankou=%s WHERE bisaishijian=%s AND bisaileixing=%s AND zhudui=%s AND kedui=%s'

    if result == None or len(result) == 0:
        return
    database.execManyNonQuery(sql,result)

def updatePankou(database,result):
    sql = 'UPDATE football_data SET linchangpankou=%s WHERE bisaishijian=%s AND bisaileixing=%s AND zhudui=%s AND kedui=%s'

    if result == None or len(result) == 0:
        return
    database.execManyNonQuery(sql,result)

def queryCount(database,sql):
    list = database.execQuery(sql)

    return list[0][0]
#type  : 1表示单一值,2max 3min

def insertCondition(database,condition):
    sql = 'INSERT IGNORE INTO `condition` (`pankou`,`linchangpankou`, `condition`,`type`, `total_count`, `count`,' \
          '`percent`,`model`,`col_name`,`col_val`,`com_index`) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
    if condition == None or len(condition) == 0:
        return

    database.execManyNonQuery(sql,condition)

def queryNeedUpadteRealTime(database,today,yestoday):
    sql = 'SELECT bisaileixing,bisaishijian,zhudui,kedui FROM forecast_data WHERE bisaishijian<=%s AND bisaishijian>= %s AND real_time is NULL AND add_real_time<2'
    return database.execQuery(sql, (today,yestoday))

def updateRealTime(database,result):
    sql = 'UPDATE forecast_data SET real_time=%s WHERE bisaishijian=%s AND bisaileixing=%s AND zhudui=%s AND kedui=%s'
    database.execManyNonQuery(sql, result)
def updateAddRealTimeFlag(database,result):
    sql = 'UPDATE forecast_data SET add_real_time=1+add_real_time WHERE  bisaileixing=%s AND bisaishijian=%s  AND zhudui=%s AND kedui=%s'
    database.execManyNonQuery(sql, result)

def queryNeedUpdateLinchang(database,startTime,endTime):
    sql = 'SELECT bisaileixing,bisaishijian,zhudui,kedui FROM forecast_data WHERE real_time IS NOT NULL AND linchangpankou IS NULL  AND real_time<=%s AND add_linchangpankou<2'
    return database.execQuery(sql,(endTime,))

def updateLinchang(database,result):
    sql = 'UPDATE forecast_data SET linchangpankou=%s WHERE bisaishijian=%s AND bisaileixing=%s AND zhudui=%s AND kedui=%s'
    database.execManyNonQuery(sql, result)
def updateAddLinchangFlag(database,result):
    sql = 'UPDATE forecast_data SET add_linchangpankou=1+add_linchangpankou WHERE  bisaileixing=%s AND bisaishijian=%s  AND zhudui=%s AND kedui=%s'
    database.execManyNonQuery(sql, result)

def queryNotFirstAna(database):
    sql = 'SELECT id,pankou,bisaileixing,bisaishijian,zhudui,kedui FROM forecast_data WHERE start_analysis=0 ' \
          'AND pankou is not null ORDER BY bisaishijian,real_time'
    return database.execQuery(sql)

def queryColNameAndValByPankou(database,pankou,linchangpankou,model=1):
    sql = 'SELECT col_name,col_val,`condition`,`type` FROM `condition` WHERE pankou=%s  AND model=%s ORDER BY total_count DESC,percent ASC'
    return database.execQuery(sql,(pankou,model))

def queryConditionByPankouAndType(database,pankou,rType,model=1,minpercent=1):
    sql = 'SELECT col_name,col_val,`condition`,`type`,percent,total_count,`count`,com_index FROM `condition` WHERE pankou=%s AND type=%s ' \
          'AND model=%s AND percent>%s ORDER BY percent DESC,total_count DESC '
    return database.execQuery(sql, (pankou,rType, model,minpercent))

def queryAllComIndex(database,pankou,rType,model):
    sql = 'SELECT com_index FROM `condition` WHERE pankou=%s AND type=%s AND model=%s GROUP BY com_index ORDER BY com_index'
    return database.execQuery(sql, (pankou, rType, model))
#conditionSql,colName,percent,total_count,pcount
def queryCondByPkAndComIndex(database,pankou,rType,model,comIndex):
    sql = 'SELECT `condition`,col_name,percent,total_count,`count` FROM `condition` WHERE pankou=%s AND type=%s ' \
          'AND model=%s AND com_index=%s ORDER BY percent DESC,total_count DESC '
    return database.execQuery(sql, (pankou, rType, model, comIndex))



def queryAvgPercent(database,pankou,rType,model=1,percent=0):
    sql = 'SELECT SUM(count)/SUM(total_count),count(1) FROM `condition` WHERE pankou=%s AND type=%s ' \
          'AND model=%s and percent>%s'
    return database.execQuery(sql, (pankou, rType, model,percent))


def queryVals(database,sqlCols,result):
    sql = 'SELECT ' + sqlCols +' FROM football_data WHERE bisaileixing=%s AND bisaishijian=%s  AND zhudui=%s AND kedui=%s'
    return database.execQuery(sql,result)

def updatePrediction(database,params):
    sql = 'UPDATE  forecast_data SET shengmax=%s,shengmin=%s,pingmax=%s,pingmin=%s,fumax=%s,fumin=%s,shangmax=%s,' \
          'shangmin=%s,xiamax=%s,xiamin=%s,sheng_count=%s,ping_count=%s,fu_count=%s,sheng_level=%s,ping_level=%s,' \
          'fu_level=%s,sheng_avg=%s,ping_avg=%s,fu_avg=%s,start_analysis=1 WHERE id=%s'
    database.execNonQuery(sql,params)


def queryNotLinchangAna(database):
    sql = 'SELECT id,pankou,bisaileixing,bisaishijian,zhudui,kedui,linchangpankou FROM forecast_data WHERE ' \
          'correct_analysis=0 AND pankou is not null AND linchangpankou is not null AND linchangpankou!=pankou ORDER BY real_time'
    return database.execQuery(sql)

def updateLinchangPrediction(database,params):
    sql = 'UPDATE  forecast_data SET shengmax=%s,shengmin=%s,pingmax=%s,pingmin=%s,fumax=%s,fumin=%s,shangmax=%s,shangmin=%s,xiamax=%s,xiamin=%s,correct_analysis=1 WHERE id=%s'
    database.execNonQuery(sql,params)

def updateNotNeedLinchang(database):
    sql = 'UPDATE forecast_data SET correct_analysis=1 WHERE start_analysis=1 AND pankou=linchangpankou AND pankou is not null AND correct_analysis=0'
    database.execNonQuery(sql)

def updateForecastDataResult(database):
    sql = 'UPDATE forecast_data  c INNER JOIN football_data a on a.bisaileixing=c.bisaileixing AND a.bisaishijian=c.bisaishijian ' \
          'AND a.zhudui=c.zhudui AND a.kedui=c.kedui SET c.zhubifen = a.zhubifen,c.kebifen=a.kebifen WHERE c.zhubifen is  null'

    database.execNonQuery(sql)

def queryResult(database,bisaishijian,bisaishijianend,yuzhi,count_yuzhi):

    # sql = 'SELECT a.pankou,a.zhubifen,a.kebifen,a.sheng_avg,a.ping_avg,' \
    #       'a.fu_avg,sheng_count,ping_count,fu_count,sheng_level,ping_level,fu_level  FROM forecast_data a INNER JOIN football_data b on b.bisaileixing=a.bisaileixing ' \
    #       'AND b.bisaishijian=a.bisaishijian AND b.zhudui=a.zhudui AND b.kedui=a.kedui WHERE (a.sheng_avg>%s OR a.ping_avg>%s ' \
    #       'OR a.fu_avg>%s) AND a.bisaishijian>=%s AND a.bisaishijian<%s AND a.zhubifen is not NULL AND start_analysis=1'
    sql = 'SELECT pankou,zhubifen,kebifen,sheng_avg,ping_avg,fu_avg,sheng_count,ping_count,fu_count,sheng_level,' \
          'ping_level,fu_level,bisaileixing,zhudui,kedui,zhubifen,kebifen,changci,bisaishijian  FROM forecast_data  WHERE (sheng_avg>%s OR ping_avg>%s OR fu_avg>%s) ' \
          'AND (sheng_count>%s or ping_count>%s or fu_count>%s) AND bisaishijian>=%s AND bisaishijian<%s ' \
          'AND zhubifen is not NULL AND start_analysis=1'

    return database.execQuery(sql,(yuzhi,yuzhi,yuzhi,count_yuzhi,count_yuzhi,count_yuzhi,bisaishijian,bisaishijianend))

def queryOupei(database):
    sql = 'SELECT pankou,ROUND(AVG(peilv1),4),ROUND(AVG(peilv2),4),ROUND(AVG(peilv3),4) FROM football_data where bisaishijian<"2019-11-03" GROUP BY pankou ORDER BY pankou'
    return database.execQuery(sql)
def insertHistoryForeData(database,bisaishijian):
    sql = 'INSERT IGNORE INTO forecast_data (bisaileixing,changci,bisaishijian,zhudui,kedui,pankou,linchangpankou,zhubifen,kebifen) ' \
          'SELECT bisaileixing,changci,bisaishijian,zhudui,kedui,pankou,linchangpankou,zhubifen,kebifen FROM football_data WHERE bisaishijian>=%s'

    database.execNonQuery(sql,bisaishijian)

def queryLastIndex(database):
    sql = 'SELECT last_index FROM combinations_index  ORDER BY id DESC  LIMIT 0,1'
    return database.execQuery(sql)

def updateLastIndex(database,lastIndex):
    sql = 'UPDATE combinations_index SET last_index =%s WHERE id=1'
    database.execNonQuery(sql, lastIndex)