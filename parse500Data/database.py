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

