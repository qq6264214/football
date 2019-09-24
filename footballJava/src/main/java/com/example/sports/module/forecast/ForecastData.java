package com.example.sports.module.forecast;


import com.example.sports.module.baseData.priKey;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "forecast_data")
@IdClass(priKey.class)
public class ForecastData {

    private static final long serialVersionUID = -822180306369158535L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10)
    private Integer id;

    //文件时间
    @Id
    private Date bisaishijian;

    //比赛时间
    private java.util.Date realTime;

    //主队
    @Id
    @Column(columnDefinition="varchar(50)")
    private String zhudui;

    //客队
    @Id
    @Column(columnDefinition="varchar(50)")
    private String kedui;

    @Id
    @Column(columnDefinition="varchar(50)")
    //比赛类型
    private String bisaileixing;

    //场次
    private String changci;

    //盘口
    private Double pankou;

    //临场盘口
    private Double linchangpankou;

    //胜最大概率
    private Double shengmax;

    //胜最小概率
    private Double shengmin;

    //平最大概率
    private Double pingmax;

    //平最小概率
    private Double pingmin;

    //负最大概率
    private Double fumax;

    //负最小概率
    private Double fumin;

    //上盘最大概率
    private Double shangmax;

    //上盘最小概率
    private Double shangmin;

    //下盘最大概率
    private Double xiamax;

    //下盘最小概率
    private Double xiamin;

    //添加真实比赛时间次数
    private Integer addRealTime;

    //添加临场盘口次数
    private Integer addLinchangpankou;

    private Integer startAnalysis;

    private Integer correctAnalysis;

    private Integer zhubifen;

    private Integer kebifen;


    //详情
    @Column(columnDefinition="TEXT")
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBisaishijian() {
        return bisaishijian;
    }

    public void setBisaishijian(Date bisaishijian) {
        this.bisaishijian = bisaishijian;
    }

    public java.util.Date getRealTime() {
        return realTime;
    }

    public void setRealTime(java.util.Date realTime) {
        this.realTime = realTime;
    }

    public String getZhudui() {
        return zhudui;
    }

    public void setZhudui(String zhudui) {
        this.zhudui = zhudui;
    }

    public String getKedui() {
        return kedui;
    }

    public void setKedui(String kedui) {
        this.kedui = kedui;
    }

    public String getBisaileixing() {
        return bisaileixing;
    }

    public void setBisaileixing(String bisaileixing) {
        this.bisaileixing = bisaileixing;
    }

    public String getChangci() {
        return changci;
    }

    public void setChangci(String changci) {
        this.changci = changci;
    }

    public Double getPankou() {
        return pankou;
    }

    public void setPankou(Double pankou) {
        this.pankou = pankou;
    }

    public Double getLinchangpankou() {
        return linchangpankou;
    }

    public void setLinchangpankou(Double linchangpankou) {
        this.linchangpankou = linchangpankou;
    }

    public Double getShengmax() {
        return shengmax;
    }

    public void setShengmax(Double shengmax) {
        this.shengmax = shengmax;
    }

    public Double getShengmin() {
        return shengmin;
    }

    public void setShengmin(Double shengmin) {
        this.shengmin = shengmin;
    }

    public Double getPingmax() {
        return pingmax;
    }

    public void setPingmax(Double pingmax) {
        this.pingmax = pingmax;
    }

    public Double getPingmin() {
        return pingmin;
    }

    public void setPingmin(Double pingmin) {
        this.pingmin = pingmin;
    }

    public Double getFumax() {
        return fumax;
    }

    public void setFumax(Double fumax) {
        this.fumax = fumax;
    }

    public Double getFumin() {
        return fumin;
    }

    public void setFumin(Double fumin) {
        this.fumin = fumin;
    }

    public Double getShangmax() {
        return shangmax;
    }

    public void setShangmax(Double shangmax) {
        this.shangmax = shangmax;
    }

    public Double getShangmin() {
        return shangmin;
    }

    public void setShangmin(Double shangmin) {
        this.shangmin = shangmin;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getXiamax() {
        return xiamax;
    }

    public void setXiamax(Double xiamax) {
        this.xiamax = xiamax;
    }

    public Double getXiamin() {
        return xiamin;
    }

    public void setXiamin(Double xiamin) {
        this.xiamin = xiamin;
    }

    public Integer getStartAnalysis() {
        return startAnalysis;
    }

    public void setStartAnalysis(Integer startAnalysis) {
        this.startAnalysis = startAnalysis;
    }

    public Integer getCorrectAnalysis() {
        return correctAnalysis;
    }

    public void setCorrectAnalysis(Integer correctAnalysis) {
        this.correctAnalysis = correctAnalysis;
    }

    public Integer getAddRealTime() {
        return addRealTime;
    }

    public void setAddRealTime(Integer addRealTime) {
        this.addRealTime = addRealTime;
    }

    public Integer getAddLinchangpankou() {
        return addLinchangpankou;
    }

    public void setAddLinchangpankou(Integer addLinchangpankou) {
        this.addLinchangpankou = addLinchangpankou;
    }

    public Integer getZhubifen() {
        return zhubifen;
    }

    public void setZhubifen( Integer zhubifen ) {
        this.zhubifen = zhubifen;
    }

    public Integer getKebifen() {
        return kebifen;
    }

    public void setKebifen( Integer kebifen ) {
        this.kebifen = kebifen;
    }
}
