package com.example.sports.module;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FootballData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20)
    private Integer id;

    private String zhudui;

    private String kedui;

    private String bisaileixing;

    private String changci;

    private Double jishipingjunpeilv1;

    private Double jishipingjunpeilv2;

    private Double jishipingjunpeilv3;

    //百分数转换
    private Double peilvbianhualv1;

    private Double peilvbianhualv2;

    private Double peilvbianhualv3;

    private Double qushipianlizhi1;

    private Double qushipianlizhi2;

    private Double qushipianlizhi3;

    private Double shengfuqushicha;

    private Double zonghefengxianzhishu1;

    private Double zonghefengxianzhishu2;

    private Double zonghefengxianzhishu3;

    private Double zucaizhishu1;

    private Double zucaizhishu2;

    private Double zucaizhishu3;

    private Double lengrezhishu1;

    private Double lengrezhishu2;

    private Double lengrezhishu3;

    private Double peilvqiwangzhi1;

    private Double peilvqiwangzhi2;

    private Double peilvqiwangzhi3;

    private Double peilvqujian1;

    private Double peilvqujian2;

    private Double peilvqujian3;

    private Double zhibiao1;

    private Double zhibiao2;

    private Double zhibiao3;

    private Double zhibiaoqujianbi1;
    private Double zhibiaoqujianbi2;
    private Double zhibiaoqujianbi3;

    private Double kailifangchabi1;
    private Double kailifangchabi2;
    private Double kailifangchabi3;

    private Double lisanzhicha1;
    private Double lisanzhicha2;
    private Double lisanzhicha3;

    private Double peifuzhishu1;
    private Double peifuzhishu2;
    private Double peifuzhishu3;

    private Double peifuzhishutiaozheng1;
    private Double peifuzhishutiaozheng2;
    private Double peifuzhishutiaozheng3;

    private Double bocaiyinglizhishu1;
    private Double bocaiyinglizhishu2;
    private Double bocaiyinglizhishu3;





}
