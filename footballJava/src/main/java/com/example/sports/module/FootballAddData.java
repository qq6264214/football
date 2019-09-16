package com.example.sports.module;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "football_data")
@IdClass(priKey.class)
public class FootballAddData implements Serializable {

    private static final long serialVersionUID = -822180306369151535L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 10)
    private Integer id;

    //比赛时间
    @Id
    private Date bisaishijian;

    //主队
    @Id
    private String zhudui;

    //客队
    @Id
    private String kedui;

    @Id
    //比赛类型
    private String bisaileixing;

    //场次
    private String changci;

    //盘口
    private Double pankou;

    //临场盘口
    private Double linchangpankou;

    //主队比分
    private Integer zhubifen;

    //客队比分
    private Integer kebifen;

    //即时平均赔率
    private Double peilv1;
    private Double peilv2;
    private Double peilv3;

    //赔率变化率
    private Double peilvbianhua1;
    private Double peilvbianhua2;
    private Double peilvbianhua3;

    //趋势偏离值
    private Double qushipianlizhi1;
    private Double qushipianlizhi2;
    private Double qushipianlizhi3;

    //胜负趋势差
    private Double shengfuqushicha;

    //综合风险指数
    private Double zonghefengxianzhishu1;
    private Double zonghefengxianzhishu2;
    private Double zonghefengxianzhishu3;

    //足彩指数
    private Double zucaizhishu1;
    private Double zucaizhishu2;
    private Double zucaizhishu3;

    //冷热指数
    private Double lengrezhishu1;
    private Double lengrezhishu2;
    private Double lengrezhishu3;

    //赔率期望
    private Double peilvqiwang1;
    private Double peilvqiwang2;
    private Double peilvqiwang3;

    //赔率区间
    private Double peilvqujian1;
    private Double peilvqujian2;
    private Double peilvqujian3;

    //指标1（绝对值或自然数最小）
    private Double zhibiao1;
    private Double zhibiao2;
    private Double zhibiao3;

    //指标区间比
    private Double zhibiaoqujianbi1;
    private Double zhibiaoqujianbi2;
    private Double zhibiaoqujianbi3;

    //凯利方差比
    private Double kailifangchabi1;
    private Double kailifangchabi2;
    private Double kailifangchabi3;

    //离散值差（绝对值最小）
    private Double lisanzhicha1;
    private Double lisanzhicha2;
    private Double lisanzhicha3;

    //赔付指数（最接近1）
    private Double peifuzhishu1;
    private Double peifuzhishu2;
    private Double peifuzhishu3;

    //赔付指数--调整（最接近1）
    private Double peifuzhishuxin1;
    private Double peifuzhishuxin2;
    private Double peifuzhishuxin3;

    //博彩公司盈利指数（最高）
    private Double bocaiyinglizhishu1;
    private Double bocaiyinglizhishu2;
    private Double bocaiyinglizhishu3;

    //初选指数（最大值）
    private Double chuxuanzhishu1;
    private Double chuxuanzhishu2;
    private Double chuxuanzhishu3;

    //选胆指数（最大值）
    private Double xuandanzhishu1;
    private Double xuandanzhishu2;
    private Double xuandanzhishu3;

    //赔率离散值（最低）
    private Double peilvlisanzhi11;
    private Double peilvlisanzhi12;
    private Double peilvlisanzhi13;

    private Double peilvlisanzhi21;
    private Double peilvlisanzhi22;
    private Double peilvlisanzhi23;

    //离散值变化率
    private Double lisanzhibianhualv1;
    private Double lisanzhibianhualv2;
    private Double lisanzhibianhualv3;

    //定胆指数（最大值）
    private Double dingdanzhishu1;
    private Double dingdanzhishu2;
    private Double dingdanzhishu3;

    //爆冷指数
    private Double baolengzhishu1;
    private Double baolengzhishu2;
    private Double baolengzhishu3;

    //凯利指数--初盘
    private Double kailizhishuchu1;
    private Double kailizhishuchu2;
    private Double kailizhishuchu3;

    //凯利指数--即时
    private Double kailizhishuji1;
    private Double kailizhishuji2;
    private Double kailizhishuji3;

    //凯利指数变化率
    private Double kailibianhualv1;
    private Double kailibianhualv2;
    private Double kailibianhualv3;

    //凯赔差
    private Double kaipeicha1;
    private Double kaipeicha2;
    private Double kaipeicha3;

    //偏离值
    private Double pianlizhi1;
    private Double pianlizhi2;
    private Double pianlizhi3;

    //中值偏离值
    private Double zhongzhipianlizhi1;
    private Double zhongzhipianlizhi2;
    private Double zhongzhipianlizhi3;

    //偏离率
    private Double pianlilv1;
    private Double pianlilv2;
    private Double pianlilv3;

    //综合偏离值
    private Double zonghepianlizhi1;
    private Double zonghepianlizhi2;
    private Double zonghepianlizhi3;

    //方差
    private Double fangcha1;
    private Double fangcha2;
    private Double fangcha3;

    //赔率返还差区间
    private Double peilvfanhuanchaqujian1;
    private Double peilvfanhuanchaqujian2;
    private Double peilvfanhuanchaqujian3;

    //赔率返还差
    private Double peilvfanhuancha1;
    private Double peilvfanhuancha2;
    private Double peilvfanhuancha3;


    //综合评价
    private Double zonghepingjia11;
    private Double zonghepingjia12;
    private Double zonghepingjia13;

    private Double zonghepingjia2;

    private Double zonghepingjia31;
    private Double zonghepingjia32;
    private Double zonghepingjia33;


    //赔率中值
    private Double peilvzhongzhi1;
    private Double peilvzhongzhi2;
    private Double peilvzhongzhi3;

    //平均赔率
    private Double pingjunpeilv1;
    private Double pingjunpeilv2;
    private Double pingjunpeilv3;

    //平均赔率差
    private Double pingjunpeilvcha1;
    private Double pingjunpeilvcha2;
    private Double pingjunpeilvcha3;

    //胜率中值
    private Double shenglvzhongzhi1;
    private Double shenglvzhongzhi2;
    private Double shenglvzhongzhi3;

    //平均胜率
    private Double pingjunshenglv1;
    private Double pingjunshenglv2;
    private Double pingjunshenglv3;

    //平均胜率差
    private Double pingjunshenglvcha1;
    private Double pingjunshenglvcha2;
    private Double pingjunshenglvcha3;

    //凯利指数中值
    private Double kailizhishuzhongzhi1;
    private Double kailizhishuzhongzhi2;
    private Double kailizhishuzhongzhi3;

    //凯利指数
    private Double kailizhishu1;
    private Double kailizhishu2;
    private Double kailizhishu3;

    //爆冷指数 重复
    private Double baolengzhishuxin1;
    private Double baolengzhishuxin2;
    private Double baolengzhishuxin3;

    //赔率期望均值
    private Double peilvqiwangjunzhi1;
    private Double peilvqiwangjunzhi2;
    private Double peilvqiwangjunzhi3;

    //离散值
    private Double lisanzhi1;
    private Double lisanzhi2;
    private Double lisanzhi3;

    //赔率方差和
    private Double kaipeifangchahe1;
    private Double kaipeifangchahe2;
    private Double kaipeifangchahe3;

    //凯赔差高低差
    private Double kaipeichagaodicha1;
    private Double kaipeichagaodicha2;
    private Double kaipeichagaodicha3;

    //优势指数
    private Double youshizhishu11;
    private Double youshizhishu12;
    private Double youshizhishu13;

    private Double youshizhishu21;
    private Double youshizhishu22;
    private Double youshizhishu23;

    private Double youshizhishu31;
    private Double youshizhishu32;
    private Double youshizhishu33;

    private Double youshizhishu41;
    private Double youshizhishu42;
    private Double youshizhishu43;

    private Double youshizhishu51;
    private Double youshizhishu52;
    private Double youshizhishu53;

    //优化优势
    private Double youhuayoushi1;
    private Double youhuayoushi2;
    private Double youhuayoushi3;

    //终极优势
    private Double zhongjiyoushi1;
    private Double zhongjiyoushi2;
    private Double zhongjiyoushi3;

    //优势差
    private Double youshicha1;
    private Double youshicha2;
    private Double youshicha3;

    //筛选比
    private Double shaixuanbi1;
    private Double shaixuanbi2;
    private Double shaixuanbi3;

    //筛选差
    private Double shaixuancha1;
    private Double shaixuancha2;
    private Double shaixuancha3;

    //终极筛选
    private Double zhongjishaixuan1;
    private Double zhongjishaixuan2;
    private Double zhongjishaixuan3;

    //fa
    private Double fa1;
    private Double fa2;
    private Double fa3;

    //fb
    private Double fb1;
    private Double fb2;
    private Double fb3;

    //初赔最高赔率
    private Double chupeizuigaopeilv1;
    private Double chupeizuigaopeilv2;
    private Double chupeizuigaopeilv3;

    //即时最高赔率
    private Double jishizuigaopeilv1;
    private Double jishizuigaopeilv2;
    private Double jishizuigaopeilv3;

    //最高赔率变化
    private Double zuigaopeilvbianhua1;
    private Double zuigaopeilvbianhua2;
    private Double zuigaopeilvbianhua3;

    //初赔最低赔率
    private Double chupeizuidipeilv1;
    private Double chupeizuidipeilv2;
    private Double chupeizuidipeilv3;

    //即时最低赔率
    private Double jishizuidipeilv1;
    private Double jishizuidipeilv2;
    private Double jishizuidipeilv3;

    //最低赔率变化
    private Double zuidipeilvbianhua1;
    private Double zuidipeilvbianhua2;
    private Double zuidipeilvbianhua3;

    //初赔平均赔率
    private Double chupeipingjunpeilv1;
    private Double chupeipingjunpeilv2;
    private Double chupeipingjunpeilv3;

    //即时平均赔率
    private Double jishipingjunpeilv1;
    private Double jishipingjunpeilv2;
    private Double jishipingjunpeilv3;

    //平均赔率变化
    private Double pingjunpeilvbianhua1;
    private Double pingjunpeilvbianhua2;
    private Double pingjunpeilvbianhua3;

    //初赔最高胜率
    private Double chupeizuigaoshenglv1;
    private Double chupeizuigaoshenglv2;
    private Double chupeizuigaoshenglv3;

    //即时最高胜率
    private Double jishizuigaoshenglv1;
    private Double jishizuigaoshenglv2;
    private Double jishizuigaoshenglv3;

    //最高胜率变化
    private Double zuigaoshenglvbianhua1;
    private Double zuigaoshenglvbianhua2;
    private Double zuigaoshenglvbianhua3;

    //初赔最低胜率
    private Double chupeizuidishenglv1;
    private Double chupeizuidishenglv2;
    private Double chupeizuidishenglv3;

    //即时最低胜率
    private Double jishizuidishenglv1;
    private Double jishizuidishenglv2;
    private Double jishizuidishenglv3;

    //最低胜率变化
    private Double zuidishenglvbianhua1;
    private Double zuidishenglvbianhua2;
    private Double zuidishenglvbianhua3;

    //初赔平均胜率
    private Double chupeipingjunshenglv1;
    private Double chupeipingjunshenglv2;
    private Double chupeipingjunshenglv3;

    //即时平均胜率
    private Double jishipingjunshenglv1;
    private Double jishipingjunshenglv2;
    private Double jishipingjunshenglv3;

    //平均胜率变化
    private Double pingjunshenglvbianhua1;
    private Double pingjunshenglvbianhua2;
    private Double pingjunshenglvbianhua3;

    //盈利指数
    private Double yinglizhishu1;
    private Double yinglizhishu2;
    private Double yinglizhishu3;

    //风险指数
    private Double fengxianzhishu1;
    private Double fengxianzhishu2;
    private Double fengxianzhishu3;

    //优势差右边的部分
    private Double youshichayoubian1;
    private Double youshichayoubian2;
    private Double youshichayoubian3;

    //下面右边的一个
    private Double weizhi;

    //筛选比右边
    private Double shaixuanbiyoubian1;
    private Double shaixuanbiyoubian2;
    private Double shaixuanbiyoubian3;

    //筛选差右边
    private Double shaixuanchayoubian1;
    private Double shaixuanchayoubian2;
    private Double shaixuanchayoubian3;

    //终极筛选右边
    private Double zhongjishaixuanyoubian1;
    private Double zhongjishaixuanyoubian2;
    private Double zhongjishaixuanyoubian3;

    //综合方差比
    private Double zonghefangchabi1;
    private Double zonghefangchabi2;
    private Double zonghefangchabi3;


    //三版
    //离散值初
    private Double lisanzhichu1;
    private Double lisanzhichu2;
    private Double lisanzhichu3;

    //离散值即
    private Double lisanzhiji1;
    private Double lisanzhiji2;
    private Double lisanzhiji3;

    //离散值差初
    private Double lisanzhichachu1;
    private Double lisanzhichachu2;
    private Double lisanzhichachu3;

    //离散值差即
    private Double lisanzhichaji1;
    private Double lisanzhichaji2;
    private Double lisanzhichaji3;

    //离散指数
    private Double lisanzhishu1;
    private Double lisanzhishu2;
    private Double lisanzhishu3;

    //离散比
    private Double lisanbi1;
    private Double lisanbi2;
    private Double lisanbi3;

    //变异离散指数
    private Double bianyilisanzhishu1;
    private Double bianyilisanzhishu2;
    private Double bianyilisanzhishu3;

    //变异离散指数下
    private Double bianyilisanzhishuxia1;
    private Double bianyilisanzhishuxia2;
    private Double bianyilisanzhishuxia3;

    //区间期望差(全)
    private Double qujianqiwangchaquan1;
    private Double qujianqiwangchaquan2;
    private Double qujianqiwangchaquan3;

    //区间期望差(主)
    private Double qujianqiwangchazhu1;
    private Double qujianqiwangchazhu2;
    private Double qujianqiwangchazhu3;

    //FC
    private Double fc1;
    private Double fc2;
    private Double fc3;

    //FD
    private Double fd1;
    private Double fd2;
    private Double fd3;

    //期望风险比
    private Double qiwangfengxianbi1;
    private Double qiwangfengxianbi2;
    private Double qiwangfengxianbi3;

    //综合指数
    private Double zonghezhishu1;
    private Double zonghezhishu2;
    private Double zonghezhishu3;

    //赔率方差和
    private Double peilvfangchahe1;
    private Double peilvfangchahe2;
    private Double peilvfangchahe3;

    //综合风险比
    private Double zonghefengxianbi1;
    private Double zonghefengxianbi2;
    private Double zonghefengxianbi3;

    //凯利指数差
    private Double kailizhishucha1;
    private Double kailizhishucha2;
    private Double kailizhishucha3;

    //赔率区间（初）
    private Double pellvqujianchu1;
    private Double pellvqujianchu2;
    private Double pellvqujianchu3;

    //赔率区间（即）
    private Double peilvqujianji1;
    private Double peilvqujianji2;
    private Double peilvqujianji3;

    //赔率区间比
    private Double peilvqujianbi1;
    private Double peilvqujianbi2;
    private Double peilvqujianbi3;

    //赔付指数（调整）下边的部分
    private Double peifuzhishutiaozhengxia11;
    private Double peifuzhishutiaozhengxia12;
    private Double peifuzhishutiaozhengxia13;

    private Double peifuzhishutiaozhengxia21;
    private Double peifuzhishutiaozhengxia22;
    private Double peifuzhishutiaozhengxia23;

    private Double peifuzhishutiaozhengxia31;
    private Double peifuzhishutiaozhengxia32;
    private Double peifuzhishutiaozhengxia33;

    private Double peifuzhishutiaozhengxia41;
    private Double peifuzhishutiaozhengxia42;
    private Double peifuzhishutiaozhengxia43;

    private Double peifuzhishutiaozhengxia51;
    private Double peifuzhishutiaozhengxia52;
    private Double peifuzhishutiaozhengxia53;

    private Double peifuzhishutiaozhengxia61;
    private Double peifuzhishutiaozhengxia62;
    private Double peifuzhishutiaozhengxia63;

    //期望方差比
    private Double qiwangfangchabi1;
    private Double qiwangfangchabi2;
    private Double qiwangfangchabi3;

    //均衡指数
    private Double junhengzhishu1;
    private Double junhengzhishu2;
    private Double junhengzhishu3;

    //离散值（初）右边
    private Double lisanzhichuyoubian1;
    private Double lisanzhichuyoubian2;
    private Double lisanzhichuyoubian3;

    //离散值（即）右边
    private Double lisanzhijiyoubian1;
    private Double lisanzhijiyoubian2;
    private Double lisanzhijiyoubian3;

    //离散值差（初）右边
    private Double lisanzhichachuyoubian1;
    private Double lisanzhichachuyoubian2;
    private Double lisanzhichachuyoubian3;

    //离散值差（即）右边
    private Double lisanzhichajiyoubian1;
    private Double lisanzhichajiyoubian2;
    private Double lisanzhichajiyoubian3;

    //离散值变化率右边
    private Double lisanzhibianhualvyoubian1;
    private Double lisanzhibianhualvyoubian2;
    private Double lisanzhibianhualvyoubian3;

    //离散指数右边
    private Double lisanzhishuyoubian1;
    private Double lisanzhishuyoubian2;
    private Double lisanzhishuyoubian3;

    //离散比右边
    private Double lisanbiyoubian1;
    private Double lisanbiyoubian2;
    private Double lisanbiyoubian3;

    //变异离散指数右边
    private Double bianyilisanzhishuyoubian1;
    private Double bianyilisanzhishuyoubian2;
    private Double bianyilisanzhishuyoubian3;

    //变异离散指数下边右边
    private Double bianyilisanzhishuxiayoubian1;
    private Double bianyilisanzhishuxiayoubian2;
    private Double bianyilisanzhishuxiayoubian3;

    //区间期望差(全)右边
    private Double qujianqiwangchaquanyoubian1;
    private Double qujianqiwangchaquanyoubian2;
    private Double qujianqiwangchaquanyoubian3;

    //区间期望差(主)右边
    private Double qujianqiwangchazhuyoubian1;
    private Double qujianqiwangchazhuyoubian2;
    private Double qujianqiwangchazhuyoubian3;

    //FC右边
    private Double fcyoubian1;
    private Double fcyoubian2;
    private Double fcyoubian3;

    //FD右边
    private Double fdyoubian1;
    private Double fdyoubian2;
    private Double fdyoubian3;

    //期望风险比右边
    private Double qiwangfengxianbiyoubian1;
    private Double qiwangfengxianbiyoubian2;
    private Double qiwangfengxianbiyoubian3;

    //综合指数右边
    private Double zonghezhishuyoubian1;
    private Double zonghezhishuyoubian2;
    private Double zonghezhishuyoubian3;

    //赔率方差和右边
    private Double peilvfangchaheyoubian1;
    private Double peilvfangchaheyoubian2;
    private Double peilvfangchaheyoubian3;

    //综合风险比右边
    private Double zonghefengxianbiyoubian1;
    private Double zonghefengxianbiyoubian2;
    private Double zonghefengxianbiyoubian3;

    //凯利指数差右边
    private Double kailizhishuchayoubian1;
    private Double kailizhishuchayoubian2;
    private Double kailizhishuchayoubian3;

    //爆冷指数右边
    private Double baolengzhishuyoubian1;
    private Double baolengzhishuyoubian2;
    private Double baolengzhishuyoubian3;

    //指标1右边
    private Double zhibiao1youbian1;
    private Double zhibiao1youbian2;
    private Double zhibiao1youbian3;

    //赔率区间（初）右边
    private Double peilvqujianchuyoubian1;
    private Double peilvqujianchuyoubian2;
    private Double peilvqujianchuyoubian3;

    //赔率区间（即）右边
    private Double peilvqujianjiyoubian1;
    private Double peilvqujianjiyoubian2;
    private Double peilvqujianjiyoubian3;

    //赔率区间比右边
    private Double peilvqujianbiyoubian1;
    private Double peilvqujianbiyoubian2;
    private Double peilvqujianbiyoubian3;

    //风险指数右边
    private Double fengxianzhishuyoubian1;
    private Double fengxianzhishuyoubian2;
    private Double fengxianzhishuyoubian3;

    //综合风险指数右边
    private Double zonghefengxianzhishuyoubian1;
    private Double zonghefengxianzhishuyoubian2;
    private Double zonghefengxianzhishuyoubian3;

    //赔付指数右边
    private Double peifuzhishuyoubian1;
    private Double peifuzhishuyoubian2;
    private Double peifuzhishuyoubian3;

    //赔付指数（调整）右边
    private Double peifuzhishutiaozhengyoubian1;
    private Double peifuzhishutiaozhengyoubian2;
    private Double peifuzhishutiaozhengyoubian3;


    //赔付指数（调整）下1-6右边
    private Double peifuzhishutiaozhengxia1youbian1;
    private Double peifuzhishutiaozhengxia1youbian2;
    private Double peifuzhishutiaozhengxia1youbian3;

    private Double peifuzhishutiaozhengxia2youbian1;
    private Double peifuzhishutiaozhengxia2youbian2;
    private Double peifuzhishutiaozhengxia2youbian3;

    private Double peifuzhishutiaozhengxia3youbian1;
    private Double peifuzhishutiaozhengxia3youbian2;
    private Double peifuzhishutiaozhengxia3youbian3;

    private Double peifuzhishutiaozhengxia4youbian1;
    private Double peifuzhishutiaozhengxia4youbian2;
    private Double peifuzhishutiaozhengxia4youbian3;

    private Double peifuzhishutiaozhengxia5youbian1;
    private Double peifuzhishutiaozhengxia5youbian2;
    private Double peifuzhishutiaozhengxia5youbian3;

    private Double peifuzhishutiaozhengxia6youbian1;
    private Double peifuzhishutiaozhengxia6youbian2;
    private Double peifuzhishutiaozhengxia6youbian3;

    //期望方差比右边
    private Double qiwangfangchabiyoubian1;
    private Double qiwangfangchabiyoubian2;
    private Double qiwangfangchabiyoubian3;

    //均衡指数
    private Double junhengzhishuyoubian1;
    private Double junhengzhishuyoubian2;
    private Double junhengzhishuyoubian3;






    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Date getBisaishijian() {
        return bisaishijian;
    }

    public void setBisaishijian( Date bisaishijian ) {
        this.bisaishijian = bisaishijian;
    }

    public String getZhudui() {
        return zhudui;
    }

    public void setZhudui( String zhudui ) {
        this.zhudui = zhudui;
    }

    public String getKedui() {
        return kedui;
    }

    public void setKedui( String kedui ) {
        this.kedui = kedui;
    }

    public String getBisaileixing() {
        return bisaileixing;
    }

    public void setBisaileixing( String bisaileixing ) {
        this.bisaileixing = bisaileixing;
    }

    public String getChangci() {
        return changci;
    }

    public void setChangci( String changci ) {
        this.changci = changci;
    }

    public Double getQushipianlizhi1() {
        return qushipianlizhi1;
    }

    public void setQushipianlizhi1( Double qushipianlizhi1 ) {
        this.qushipianlizhi1 = qushipianlizhi1;
    }

    public Double getQushipianlizhi2() {
        return qushipianlizhi2;
    }

    public void setQushipianlizhi2( Double qushipianlizhi2 ) {
        this.qushipianlizhi2 = qushipianlizhi2;
    }

    public Double getQushipianlizhi3() {
        return qushipianlizhi3;
    }

    public void setQushipianlizhi3( Double qushipianlizhi3 ) {
        this.qushipianlizhi3 = qushipianlizhi3;
    }

    public Double getShengfuqushicha() {
        return shengfuqushicha;
    }

    public void setShengfuqushicha( Double shengfuqushicha ) {
        this.shengfuqushicha = shengfuqushicha;
    }

    public Double getZonghefengxianzhishu1() {
        return zonghefengxianzhishu1;
    }

    public void setZonghefengxianzhishu1( Double zonghefengxianzhishu1 ) {
        this.zonghefengxianzhishu1 = zonghefengxianzhishu1;
    }

    public Double getZonghefengxianzhishu2() {
        return zonghefengxianzhishu2;
    }

    public void setZonghefengxianzhishu2( Double zonghefengxianzhishu2 ) {
        this.zonghefengxianzhishu2 = zonghefengxianzhishu2;
    }

    public Double getZonghefengxianzhishu3() {
        return zonghefengxianzhishu3;
    }

    public void setZonghefengxianzhishu3( Double zonghefengxianzhishu3 ) {
        this.zonghefengxianzhishu3 = zonghefengxianzhishu3;
    }

    public Double getZonghepingjia31() {
        return zonghepingjia31;
    }

    public void setZonghepingjia31( Double zonghepingjia31 ) {
        this.zonghepingjia31 = zonghepingjia31;
    }

    public Double getZonghepingjia32() {
        return zonghepingjia32;
    }

    public void setZonghepingjia32( Double zonghepingjia32 ) {
        this.zonghepingjia32 = zonghepingjia32;
    }

    public Double getZonghepingjia33() {
        return zonghepingjia33;
    }

    public void setZonghepingjia33( Double zonghepingjia33 ) {
        this.zonghepingjia33 = zonghepingjia33;
    }

    public Double getFa1() {
        return fa1;
    }

    public void setFa1( Double fa1 ) {
        this.fa1 = fa1;
    }

    public Double getFa2() {
        return fa2;
    }

    public void setFa2( Double fa2 ) {
        this.fa2 = fa2;
    }

    public Double getFa3() {
        return fa3;
    }

    public void setFa3( Double fa3 ) {
        this.fa3 = fa3;
    }

    public Double getFb1() {
        return fb1;
    }

    public void setFb1( Double fb1 ) {
        this.fb1 = fb1;
    }

    public Double getFb2() {
        return fb2;
    }

    public void setFb2( Double fb2 ) {
        this.fb2 = fb2;
    }

    public Double getFb3() {
        return fb3;
    }

    public void setFb3( Double fb3 ) {
        this.fb3 = fb3;
    }

    public Double getPeilvbianhua1() {
        return peilvbianhua1;
    }

    public void setPeilvbianhua1( Double peilvbianhua1 ) {
        this.peilvbianhua1 = peilvbianhua1;
    }

    public Double getPeilvbianhua2() {
        return peilvbianhua2;
    }

    public void setPeilvbianhua2( Double peilvbianhua2 ) {
        this.peilvbianhua2 = peilvbianhua2;
    }

    public Double getPeilvbianhua3() {
        return peilvbianhua3;
    }

    public void setPeilvbianhua3( Double peilvbianhua3 ) {
        this.peilvbianhua3 = peilvbianhua3;
    }

    public Double getBocaiyinglizhishu1() {
        return bocaiyinglizhishu1;
    }

    public void setBocaiyinglizhishu1( Double bocaiyinglizhishu1 ) {
        this.bocaiyinglizhishu1 = bocaiyinglizhishu1;
    }

    public Double getBocaiyinglizhishu2() {
        return bocaiyinglizhishu2;
    }

    public void setBocaiyinglizhishu2( Double bocaiyinglizhishu2 ) {
        this.bocaiyinglizhishu2 = bocaiyinglizhishu2;
    }

    public Double getBocaiyinglizhishu3() {
        return bocaiyinglizhishu3;
    }

    public void setBocaiyinglizhishu3( Double bocaiyinglizhishu3 ) {
        this.bocaiyinglizhishu3 = bocaiyinglizhishu3;
    }

    public Double getLisanzhibianhualv1() {
        return lisanzhibianhualv1;
    }

    public void setLisanzhibianhualv1( Double lisanzhibianhualv1 ) {
        this.lisanzhibianhualv1 = lisanzhibianhualv1;
    }

    public Double getLisanzhibianhualv2() {
        return lisanzhibianhualv2;
    }

    public void setLisanzhibianhualv2( Double lisanzhibianhualv2 ) {
        this.lisanzhibianhualv2 = lisanzhibianhualv2;
    }

    public Double getLisanzhibianhualv3() {
        return lisanzhibianhualv3;
    }

    public void setLisanzhibianhualv3( Double lisanzhibianhualv3 ) {
        this.lisanzhibianhualv3 = lisanzhibianhualv3;
    }

    public Double getPankou() {
        return pankou;
    }

    public void setPankou( Double pankou ) {
        this.pankou = pankou;
    }

    public Double getPeilv1() {
        return peilv1;
    }

    public void setPeilv1( Double peilv1 ) {
        this.peilv1 = peilv1;
    }

    public Double getPeilv2() {
        return peilv2;
    }

    public void setPeilv2( Double peilv2 ) {
        this.peilv2 = peilv2;
    }

    public Double getPeilv3() {
        return peilv3;
    }

    public void setPeilv3( Double peilv3 ) {
        this.peilv3 = peilv3;
    }

    public Double getZucaizhishu1() {
        return zucaizhishu1;
    }

    public void setZucaizhishu1( Double zucaizhishu1 ) {
        this.zucaizhishu1 = zucaizhishu1;
    }

    public Double getZucaizhishu2() {
        return zucaizhishu2;
    }

    public void setZucaizhishu2( Double zucaizhishu2 ) {
        this.zucaizhishu2 = zucaizhishu2;
    }

    public Double getZucaizhishu3() {
        return zucaizhishu3;
    }

    public void setZucaizhishu3( Double zucaizhishu3 ) {
        this.zucaizhishu3 = zucaizhishu3;
    }

    public Double getLengrezhishu1() {
        return lengrezhishu1;
    }

    public void setLengrezhishu1( Double lengrezhishu1 ) {
        this.lengrezhishu1 = lengrezhishu1;
    }

    public Double getLengrezhishu2() {
        return lengrezhishu2;
    }

    public void setLengrezhishu2( Double lengrezhishu2 ) {
        this.lengrezhishu2 = lengrezhishu2;
    }

    public Double getLengrezhishu3() {
        return lengrezhishu3;
    }

    public void setLengrezhishu3( Double lengrezhishu3 ) {
        this.lengrezhishu3 = lengrezhishu3;
    }

    public Double getPeilvqiwang1() {
        return peilvqiwang1;
    }

    public void setPeilvqiwang1( Double peilvqiwang1 ) {
        this.peilvqiwang1 = peilvqiwang1;
    }

    public Double getPeilvqiwang2() {
        return peilvqiwang2;
    }

    public void setPeilvqiwang2( Double peilvqiwang2 ) {
        this.peilvqiwang2 = peilvqiwang2;
    }

    public Double getPeilvqiwang3() {
        return peilvqiwang3;
    }

    public void setPeilvqiwang3( Double peilvqiwang3 ) {
        this.peilvqiwang3 = peilvqiwang3;
    }

    public Double getPeilvqujian1() {
        return peilvqujian1;
    }

    public void setPeilvqujian1( Double peilvqujian1 ) {
        this.peilvqujian1 = peilvqujian1;
    }

    public Double getPeilvqujian2() {
        return peilvqujian2;
    }

    public void setPeilvqujian2( Double peilvqujian2 ) {
        this.peilvqujian2 = peilvqujian2;
    }

    public Double getPeilvqujian3() {
        return peilvqujian3;
    }

    public void setPeilvqujian3( Double peilvqujian3 ) {
        this.peilvqujian3 = peilvqujian3;
    }

    public Double getZhibiao1() {
        return zhibiao1;
    }

    public void setZhibiao1( Double zhibiao1 ) {
        this.zhibiao1 = zhibiao1;
    }

    public Double getZhibiao2() {
        return zhibiao2;
    }

    public void setZhibiao2( Double zhibiao2 ) {
        this.zhibiao2 = zhibiao2;
    }

    public Double getZhibiao3() {
        return zhibiao3;
    }

    public void setZhibiao3( Double zhibiao3 ) {
        this.zhibiao3 = zhibiao3;
    }

    public Double getZhibiaoqujianbi1() {
        return zhibiaoqujianbi1;
    }

    public void setZhibiaoqujianbi1( Double zhibiaoqujianbi1 ) {
        this.zhibiaoqujianbi1 = zhibiaoqujianbi1;
    }

    public Double getZhibiaoqujianbi2() {
        return zhibiaoqujianbi2;
    }

    public void setZhibiaoqujianbi2( Double zhibiaoqujianbi2 ) {
        this.zhibiaoqujianbi2 = zhibiaoqujianbi2;
    }

    public Double getZhibiaoqujianbi3() {
        return zhibiaoqujianbi3;
    }

    public void setZhibiaoqujianbi3( Double zhibiaoqujianbi3 ) {
        this.zhibiaoqujianbi3 = zhibiaoqujianbi3;
    }

    public Double getKailifangchabi1() {
        return kailifangchabi1;
    }

    public void setKailifangchabi1( Double kailifangchabi1 ) {
        this.kailifangchabi1 = kailifangchabi1;
    }

    public Double getKailifangchabi2() {
        return kailifangchabi2;
    }

    public void setKailifangchabi2( Double kailifangchabi2 ) {
        this.kailifangchabi2 = kailifangchabi2;
    }

    public Double getKailifangchabi3() {
        return kailifangchabi3;
    }

    public void setKailifangchabi3( Double kailifangchabi3 ) {
        this.kailifangchabi3 = kailifangchabi3;
    }

    public Double getLisanzhicha1() {
        return lisanzhicha1;
    }

    public void setLisanzhicha1( Double lisanzhicha1 ) {
        this.lisanzhicha1 = lisanzhicha1;
    }

    public Double getLisanzhicha2() {
        return lisanzhicha2;
    }

    public void setLisanzhicha2( Double lisanzhicha2 ) {
        this.lisanzhicha2 = lisanzhicha2;
    }

    public Double getLisanzhicha3() {
        return lisanzhicha3;
    }

    public void setLisanzhicha3( Double lisanzhicha3 ) {
        this.lisanzhicha3 = lisanzhicha3;
    }

    public Double getPeifuzhishu1() {
        return peifuzhishu1;
    }

    public void setPeifuzhishu1( Double peifuzhishu1 ) {
        this.peifuzhishu1 = peifuzhishu1;
    }

    public Double getPeifuzhishu2() {
        return peifuzhishu2;
    }

    public void setPeifuzhishu2( Double peifuzhishu2 ) {
        this.peifuzhishu2 = peifuzhishu2;
    }

    public Double getPeifuzhishu3() {
        return peifuzhishu3;
    }

    public void setPeifuzhishu3( Double peifuzhishu3 ) {
        this.peifuzhishu3 = peifuzhishu3;
    }

    public Double getPeifuzhishuxin1() {
        return peifuzhishuxin1;
    }

    public void setPeifuzhishuxin1( Double peifuzhishuxin1 ) {
        this.peifuzhishuxin1 = peifuzhishuxin1;
    }

    public Double getPeifuzhishuxin2() {
        return peifuzhishuxin2;
    }

    public void setPeifuzhishuxin2( Double peifuzhishuxin2 ) {
        this.peifuzhishuxin2 = peifuzhishuxin2;
    }

    public Double getPeifuzhishuxin3() {
        return peifuzhishuxin3;
    }

    public void setPeifuzhishuxin3( Double peifuzhishuxin3 ) {
        this.peifuzhishuxin3 = peifuzhishuxin3;
    }

    public Double getChuxuanzhishu1() {
        return chuxuanzhishu1;
    }

    public void setChuxuanzhishu1( Double chuxuanzhishu1 ) {
        this.chuxuanzhishu1 = chuxuanzhishu1;
    }

    public Double getChuxuanzhishu2() {
        return chuxuanzhishu2;
    }

    public void setChuxuanzhishu2( Double chuxuanzhishu2 ) {
        this.chuxuanzhishu2 = chuxuanzhishu2;
    }

    public Double getChuxuanzhishu3() {
        return chuxuanzhishu3;
    }

    public void setChuxuanzhishu3( Double chuxuanzhishu3 ) {
        this.chuxuanzhishu3 = chuxuanzhishu3;
    }

    public Double getXuandanzhishu1() {
        return xuandanzhishu1;
    }

    public void setXuandanzhishu1( Double xuandanzhishu1 ) {
        this.xuandanzhishu1 = xuandanzhishu1;
    }

    public Double getXuandanzhishu2() {
        return xuandanzhishu2;
    }

    public void setXuandanzhishu2( Double xuandanzhishu2 ) {
        this.xuandanzhishu2 = xuandanzhishu2;
    }

    public Double getXuandanzhishu3() {
        return xuandanzhishu3;
    }

    public void setXuandanzhishu3( Double xuandanzhishu3 ) {
        this.xuandanzhishu3 = xuandanzhishu3;
    }


    public Double getDingdanzhishu1() {
        return dingdanzhishu1;
    }

    public void setDingdanzhishu1( Double dingdanzhishu1 ) {
        this.dingdanzhishu1 = dingdanzhishu1;
    }

    public Double getDingdanzhishu2() {
        return dingdanzhishu2;
    }

    public void setDingdanzhishu2( Double dingdanzhishu2 ) {
        this.dingdanzhishu2 = dingdanzhishu2;
    }

    public Double getDingdanzhishu3() {
        return dingdanzhishu3;
    }

    public void setDingdanzhishu3( Double dingdanzhishu3 ) {
        this.dingdanzhishu3 = dingdanzhishu3;
    }

    public Double getBaolengzhishu1() {
        return baolengzhishu1;
    }

    public void setBaolengzhishu1( Double baolengzhishu1 ) {
        this.baolengzhishu1 = baolengzhishu1;
    }

    public Double getBaolengzhishu2() {
        return baolengzhishu2;
    }

    public void setBaolengzhishu2( Double baolengzhishu2 ) {
        this.baolengzhishu2 = baolengzhishu2;
    }

    public Double getBaolengzhishu3() {
        return baolengzhishu3;
    }

    public void setBaolengzhishu3( Double baolengzhishu3 ) {
        this.baolengzhishu3 = baolengzhishu3;
    }

    public Double getKailizhishuchu1() {
        return kailizhishuchu1;
    }

    public void setKailizhishuchu1( Double kailizhishuchu1 ) {
        this.kailizhishuchu1 = kailizhishuchu1;
    }

    public Double getKailizhishuchu2() {
        return kailizhishuchu2;
    }

    public void setKailizhishuchu2( Double kailizhishuchu2 ) {
        this.kailizhishuchu2 = kailizhishuchu2;
    }

    public Double getKailizhishuchu3() {
        return kailizhishuchu3;
    }

    public void setKailizhishuchu3( Double kailizhishuchu3 ) {
        this.kailizhishuchu3 = kailizhishuchu3;
    }

    public Double getKailizhishuji1() {
        return kailizhishuji1;
    }

    public void setKailizhishuji1( Double kailizhishuji1 ) {
        this.kailizhishuji1 = kailizhishuji1;
    }

    public Double getKailizhishuji2() {
        return kailizhishuji2;
    }

    public void setKailizhishuji2( Double kailizhishuji2 ) {
        this.kailizhishuji2 = kailizhishuji2;
    }

    public Double getKailizhishuji3() {
        return kailizhishuji3;
    }

    public void setKailizhishuji3( Double kailizhishuji3 ) {
        this.kailizhishuji3 = kailizhishuji3;
    }

    public Double getKailibianhualv1() {
        return kailibianhualv1;
    }

    public void setKailibianhualv1( Double kailibianhualv1 ) {
        this.kailibianhualv1 = kailibianhualv1;
    }

    public Double getKailibianhualv2() {
        return kailibianhualv2;
    }

    public void setKailibianhualv2( Double kailibianhualv2 ) {
        this.kailibianhualv2 = kailibianhualv2;
    }

    public Double getKailibianhualv3() {
        return kailibianhualv3;
    }

    public void setKailibianhualv3( Double kailibianhualv3 ) {
        this.kailibianhualv3 = kailibianhualv3;
    }

    public Double getKaipeicha1() {
        return kaipeicha1;
    }

    public void setKaipeicha1( Double kaipeicha1 ) {
        this.kaipeicha1 = kaipeicha1;
    }

    public Double getKaipeicha2() {
        return kaipeicha2;
    }

    public void setKaipeicha2( Double kaipeicha2 ) {
        this.kaipeicha2 = kaipeicha2;
    }

    public Double getKaipeicha3() {
        return kaipeicha3;
    }

    public void setKaipeicha3( Double kaipeicha3 ) {
        this.kaipeicha3 = kaipeicha3;
    }

    public Double getPianlizhi1() {
        return pianlizhi1;
    }

    public void setPianlizhi1( Double pianlizhi1 ) {
        this.pianlizhi1 = pianlizhi1;
    }

    public Double getPianlizhi2() {
        return pianlizhi2;
    }

    public void setPianlizhi2( Double pianlizhi2 ) {
        this.pianlizhi2 = pianlizhi2;
    }

    public Double getPianlizhi3() {
        return pianlizhi3;
    }

    public void setPianlizhi3( Double pianlizhi3 ) {
        this.pianlizhi3 = pianlizhi3;
    }

    public Double getZhongzhipianlizhi1() {
        return zhongzhipianlizhi1;
    }

    public void setZhongzhipianlizhi1( Double zhongzhipianlizhi1 ) {
        this.zhongzhipianlizhi1 = zhongzhipianlizhi1;
    }

    public Double getZhongzhipianlizhi2() {
        return zhongzhipianlizhi2;
    }

    public void setZhongzhipianlizhi2( Double zhongzhipianlizhi2 ) {
        this.zhongzhipianlizhi2 = zhongzhipianlizhi2;
    }

    public Double getZhongzhipianlizhi3() {
        return zhongzhipianlizhi3;
    }

    public void setZhongzhipianlizhi3( Double zhongzhipianlizhi3 ) {
        this.zhongzhipianlizhi3 = zhongzhipianlizhi3;
    }

    public Double getPianlilv1() {
        return pianlilv1;
    }

    public void setPianlilv1( Double pianlilv1 ) {
        this.pianlilv1 = pianlilv1;
    }

    public Double getPianlilv2() {
        return pianlilv2;
    }

    public void setPianlilv2( Double pianlilv2 ) {
        this.pianlilv2 = pianlilv2;
    }

    public Double getPianlilv3() {
        return pianlilv3;
    }

    public void setPianlilv3( Double pianlilv3 ) {
        this.pianlilv3 = pianlilv3;
    }

    public Double getZonghepianlizhi1() {
        return zonghepianlizhi1;
    }

    public void setZonghepianlizhi1( Double zonghepianlizhi1 ) {
        this.zonghepianlizhi1 = zonghepianlizhi1;
    }

    public Double getZonghepianlizhi2() {
        return zonghepianlizhi2;
    }

    public void setZonghepianlizhi2( Double zonghepianlizhi2 ) {
        this.zonghepianlizhi2 = zonghepianlizhi2;
    }

    public Double getZonghepianlizhi3() {
        return zonghepianlizhi3;
    }

    public void setZonghepianlizhi3( Double zonghepianlizhi3 ) {
        this.zonghepianlizhi3 = zonghepianlizhi3;
    }

    public Double getFangcha1() {
        return fangcha1;
    }

    public void setFangcha1( Double fangcha1 ) {
        this.fangcha1 = fangcha1;
    }

    public Double getFangcha2() {
        return fangcha2;
    }

    public void setFangcha2( Double fangcha2 ) {
        this.fangcha2 = fangcha2;
    }

    public Double getFangcha3() {
        return fangcha3;
    }

    public void setFangcha3( Double fangcha3 ) {
        this.fangcha3 = fangcha3;
    }

    public Double getPeilvfanhuanchaqujian1() {
        return peilvfanhuanchaqujian1;
    }

    public void setPeilvfanhuanchaqujian1( Double peilvfanhuanchaqujian1 ) {
        this.peilvfanhuanchaqujian1 = peilvfanhuanchaqujian1;
    }

    public Double getPeilvfanhuanchaqujian2() {
        return peilvfanhuanchaqujian2;
    }

    public void setPeilvfanhuanchaqujian2( Double peilvfanhuanchaqujian2 ) {
        this.peilvfanhuanchaqujian2 = peilvfanhuanchaqujian2;
    }

    public Double getPeilvfanhuanchaqujian3() {
        return peilvfanhuanchaqujian3;
    }

    public void setPeilvfanhuanchaqujian3( Double peilvfanhuanchaqujian3 ) {
        this.peilvfanhuanchaqujian3 = peilvfanhuanchaqujian3;
    }

    public Double getPeilvfanhuancha1() {
        return peilvfanhuancha1;
    }

    public void setPeilvfanhuancha1( Double peilvfanhuancha1 ) {
        this.peilvfanhuancha1 = peilvfanhuancha1;
    }

    public Double getPeilvfanhuancha2() {
        return peilvfanhuancha2;
    }

    public void setPeilvfanhuancha2( Double peilvfanhuancha2 ) {
        this.peilvfanhuancha2 = peilvfanhuancha2;
    }

    public Double getPeilvfanhuancha3() {
        return peilvfanhuancha3;
    }

    public void setPeilvfanhuancha3( Double peilvfanhuancha3 ) {
        this.peilvfanhuancha3 = peilvfanhuancha3;
    }

    public Double getZonghepingjia11() {
        return zonghepingjia11;
    }

    public void setZonghepingjia11( Double zonghepingjia11 ) {
        this.zonghepingjia11 = zonghepingjia11;
    }

    public Double getZonghepingjia12() {
        return zonghepingjia12;
    }

    public void setZonghepingjia12( Double zonghepingjia12 ) {
        this.zonghepingjia12 = zonghepingjia12;
    }

    public Double getZonghepingjia13() {
        return zonghepingjia13;
    }

    public void setZonghepingjia13( Double zonghepingjia13 ) {
        this.zonghepingjia13 = zonghepingjia13;
    }

    public Double getZonghepingjia2() {
        return zonghepingjia2;
    }

    public void setZonghepingjia2( Double zonghepingjia2 ) {
        this.zonghepingjia2 = zonghepingjia2;
    }

    public Double getPeilvzhongzhi1() {
        return peilvzhongzhi1;
    }

    public void setPeilvzhongzhi1( Double peilvzhongzhi1 ) {
        this.peilvzhongzhi1 = peilvzhongzhi1;
    }

    public Double getPeilvzhongzhi2() {
        return peilvzhongzhi2;
    }

    public void setPeilvzhongzhi2( Double peilvzhongzhi2 ) {
        this.peilvzhongzhi2 = peilvzhongzhi2;
    }

    public Double getPeilvzhongzhi3() {
        return peilvzhongzhi3;
    }

    public void setPeilvzhongzhi3( Double peilvzhongzhi3 ) {
        this.peilvzhongzhi3 = peilvzhongzhi3;
    }

    public Double getPingjunpeilv1() {
        return pingjunpeilv1;
    }

    public void setPingjunpeilv1( Double pingjunpeilv1 ) {
        this.pingjunpeilv1 = pingjunpeilv1;
    }

    public Double getPingjunpeilv2() {
        return pingjunpeilv2;
    }

    public void setPingjunpeilv2( Double pingjunpeilv2 ) {
        this.pingjunpeilv2 = pingjunpeilv2;
    }

    public Double getPingjunpeilv3() {
        return pingjunpeilv3;
    }

    public void setPingjunpeilv3( Double pingjunpeilv3 ) {
        this.pingjunpeilv3 = pingjunpeilv3;
    }

    public Double getPingjunpeilvcha1() {
        return pingjunpeilvcha1;
    }

    public void setPingjunpeilvcha1( Double pingjunpeilvcha1 ) {
        this.pingjunpeilvcha1 = pingjunpeilvcha1;
    }

    public Double getPingjunpeilvcha2() {
        return pingjunpeilvcha2;
    }

    public void setPingjunpeilvcha2( Double pingjunpeilvcha2 ) {
        this.pingjunpeilvcha2 = pingjunpeilvcha2;
    }

    public Double getPingjunpeilvcha3() {
        return pingjunpeilvcha3;
    }

    public void setPingjunpeilvcha3( Double pingjunpeilvcha3 ) {
        this.pingjunpeilvcha3 = pingjunpeilvcha3;
    }

    public Double getShenglvzhongzhi1() {
        return shenglvzhongzhi1;
    }

    public void setShenglvzhongzhi1( Double shenglvzhongzhi1 ) {
        this.shenglvzhongzhi1 = shenglvzhongzhi1;
    }

    public Double getShenglvzhongzhi2() {
        return shenglvzhongzhi2;
    }

    public void setShenglvzhongzhi2( Double shenglvzhongzhi2 ) {
        this.shenglvzhongzhi2 = shenglvzhongzhi2;
    }

    public Double getShenglvzhongzhi3() {
        return shenglvzhongzhi3;
    }

    public void setShenglvzhongzhi3( Double shenglvzhongzhi3 ) {
        this.shenglvzhongzhi3 = shenglvzhongzhi3;
    }

    public Double getPingjunshenglv1() {
        return pingjunshenglv1;
    }

    public void setPingjunshenglv1( Double pingjunshenglv1 ) {
        this.pingjunshenglv1 = pingjunshenglv1;
    }

    public Double getPingjunshenglv2() {
        return pingjunshenglv2;
    }

    public void setPingjunshenglv2( Double pingjunshenglv2 ) {
        this.pingjunshenglv2 = pingjunshenglv2;
    }

    public Double getPingjunshenglv3() {
        return pingjunshenglv3;
    }

    public void setPingjunshenglv3( Double pingjunshenglv3 ) {
        this.pingjunshenglv3 = pingjunshenglv3;
    }

    public Double getPingjunshenglvcha1() {
        return pingjunshenglvcha1;
    }

    public void setPingjunshenglvcha1( Double pingjunshenglvcha1 ) {
        this.pingjunshenglvcha1 = pingjunshenglvcha1;
    }

    public Double getPingjunshenglvcha2() {
        return pingjunshenglvcha2;
    }

    public void setPingjunshenglvcha2( Double pingjunshenglvcha2 ) {
        this.pingjunshenglvcha2 = pingjunshenglvcha2;
    }

    public Double getPingjunshenglvcha3() {
        return pingjunshenglvcha3;
    }

    public void setPingjunshenglvcha3( Double pingjunshenglvcha3 ) {
        this.pingjunshenglvcha3 = pingjunshenglvcha3;
    }

    public Double getKailizhishuzhongzhi1() {
        return kailizhishuzhongzhi1;
    }

    public void setKailizhishuzhongzhi1( Double kailizhishuzhongzhi1 ) {
        this.kailizhishuzhongzhi1 = kailizhishuzhongzhi1;
    }

    public Double getKailizhishuzhongzhi2() {
        return kailizhishuzhongzhi2;
    }

    public void setKailizhishuzhongzhi2( Double kailizhishuzhongzhi2 ) {
        this.kailizhishuzhongzhi2 = kailizhishuzhongzhi2;
    }

    public Double getKailizhishuzhongzhi3() {
        return kailizhishuzhongzhi3;
    }

    public void setKailizhishuzhongzhi3( Double kailizhishuzhongzhi3 ) {
        this.kailizhishuzhongzhi3 = kailizhishuzhongzhi3;
    }

    public Double getKailizhishu1() {
        return kailizhishu1;
    }

    public void setKailizhishu1( Double kailizhishu1 ) {
        this.kailizhishu1 = kailizhishu1;
    }

    public Double getKailizhishu2() {
        return kailizhishu2;
    }

    public void setKailizhishu2( Double kailizhishu2 ) {
        this.kailizhishu2 = kailizhishu2;
    }

    public Double getKailizhishu3() {
        return kailizhishu3;
    }

    public void setKailizhishu3( Double kailizhishu3 ) {
        this.kailizhishu3 = kailizhishu3;
    }

    public Double getPeilvqiwangjunzhi1() {
        return peilvqiwangjunzhi1;
    }

    public void setPeilvqiwangjunzhi1( Double peilvqiwangjunzhi1 ) {
        this.peilvqiwangjunzhi1 = peilvqiwangjunzhi1;
    }

    public Double getPeilvqiwangjunzhi2() {
        return peilvqiwangjunzhi2;
    }

    public void setPeilvqiwangjunzhi2( Double peilvqiwangjunzhi2 ) {
        this.peilvqiwangjunzhi2 = peilvqiwangjunzhi2;
    }

    public Double getPeilvqiwangjunzhi3() {
        return peilvqiwangjunzhi3;
    }

    public void setPeilvqiwangjunzhi3( Double peilvqiwangjunzhi3 ) {
        this.peilvqiwangjunzhi3 = peilvqiwangjunzhi3;
    }

    public Double getLisanzhi1() {
        return lisanzhi1;
    }

    public void setLisanzhi1( Double lisanzhi1 ) {
        this.lisanzhi1 = lisanzhi1;
    }

    public Double getLisanzhi2() {
        return lisanzhi2;
    }

    public void setLisanzhi2( Double lisanzhi2 ) {
        this.lisanzhi2 = lisanzhi2;
    }

    public Double getLisanzhi3() {
        return lisanzhi3;
    }

    public void setLisanzhi3( Double lisanzhi3 ) {
        this.lisanzhi3 = lisanzhi3;
    }

    public Double getKaipeifangchahe1() {
        return kaipeifangchahe1;
    }

    public void setKaipeifangchahe1( Double kaipeifangchahe1 ) {
        this.kaipeifangchahe1 = kaipeifangchahe1;
    }

    public Double getKaipeifangchahe2() {
        return kaipeifangchahe2;
    }

    public void setKaipeifangchahe2( Double kaipeifangchahe2 ) {
        this.kaipeifangchahe2 = kaipeifangchahe2;
    }

    public Double getKaipeifangchahe3() {
        return kaipeifangchahe3;
    }

    public void setKaipeifangchahe3( Double kaipeifangchahe3 ) {
        this.kaipeifangchahe3 = kaipeifangchahe3;
    }

    public Double getKaipeichagaodicha1() {
        return kaipeichagaodicha1;
    }

    public void setKaipeichagaodicha1( Double kaipeichagaodicha1 ) {
        this.kaipeichagaodicha1 = kaipeichagaodicha1;
    }

    public Double getKaipeichagaodicha2() {
        return kaipeichagaodicha2;
    }

    public void setKaipeichagaodicha2( Double kaipeichagaodicha2 ) {
        this.kaipeichagaodicha2 = kaipeichagaodicha2;
    }

    public Double getKaipeichagaodicha3() {
        return kaipeichagaodicha3;
    }

    public void setKaipeichagaodicha3( Double kaipeichagaodicha3 ) {
        this.kaipeichagaodicha3 = kaipeichagaodicha3;
    }

    public Double getYoushizhishu11() {
        return youshizhishu11;
    }

    public void setYoushizhishu11( Double youshizhishu11 ) {
        this.youshizhishu11 = youshizhishu11;
    }

    public Double getYoushizhishu12() {
        return youshizhishu12;
    }

    public void setYoushizhishu12( Double youshizhishu12 ) {
        this.youshizhishu12 = youshizhishu12;
    }

    public Double getYoushizhishu13() {
        return youshizhishu13;
    }

    public void setYoushizhishu13( Double youshizhishu13 ) {
        this.youshizhishu13 = youshizhishu13;
    }

    public Double getYoushizhishu21() {
        return youshizhishu21;
    }

    public void setYoushizhishu21( Double youshizhishu21 ) {
        this.youshizhishu21 = youshizhishu21;
    }

    public Double getYoushizhishu22() {
        return youshizhishu22;
    }

    public void setYoushizhishu22( Double youshizhishu22 ) {
        this.youshizhishu22 = youshizhishu22;
    }

    public Double getYoushizhishu23() {
        return youshizhishu23;
    }

    public void setYoushizhishu23( Double youshizhishu23 ) {
        this.youshizhishu23 = youshizhishu23;
    }

    public Double getYoushizhishu31() {
        return youshizhishu31;
    }

    public void setYoushizhishu31( Double youshizhishu31 ) {
        this.youshizhishu31 = youshizhishu31;
    }

    public Double getYoushizhishu32() {
        return youshizhishu32;
    }

    public void setYoushizhishu32( Double youshizhishu32 ) {
        this.youshizhishu32 = youshizhishu32;
    }

    public Double getYoushizhishu33() {
        return youshizhishu33;
    }

    public void setYoushizhishu33( Double youshizhishu33 ) {
        this.youshizhishu33 = youshizhishu33;
    }

    public Double getYoushizhishu41() {
        return youshizhishu41;
    }

    public void setYoushizhishu41( Double youshizhishu41 ) {
        this.youshizhishu41 = youshizhishu41;
    }

    public Double getYoushizhishu42() {
        return youshizhishu42;
    }

    public void setYoushizhishu42( Double youshizhishu42 ) {
        this.youshizhishu42 = youshizhishu42;
    }

    public Double getYoushizhishu43() {
        return youshizhishu43;
    }

    public void setYoushizhishu43( Double youshizhishu43 ) {
        this.youshizhishu43 = youshizhishu43;
    }

    public Double getYoushizhishu51() {
        return youshizhishu51;
    }

    public void setYoushizhishu51( Double youshizhishu51 ) {
        this.youshizhishu51 = youshizhishu51;
    }

    public Double getYoushizhishu52() {
        return youshizhishu52;
    }

    public void setYoushizhishu52( Double youshizhishu52 ) {
        this.youshizhishu52 = youshizhishu52;
    }

    public Double getYoushizhishu53() {
        return youshizhishu53;
    }

    public void setYoushizhishu53( Double youshizhishu53 ) {
        this.youshizhishu53 = youshizhishu53;
    }

    public Double getYouhuayoushi1() {
        return youhuayoushi1;
    }

    public void setYouhuayoushi1( Double youhuayoushi1 ) {
        this.youhuayoushi1 = youhuayoushi1;
    }

    public Double getYouhuayoushi2() {
        return youhuayoushi2;
    }

    public void setYouhuayoushi2( Double youhuayoushi2 ) {
        this.youhuayoushi2 = youhuayoushi2;
    }

    public Double getYouhuayoushi3() {
        return youhuayoushi3;
    }

    public void setYouhuayoushi3( Double youhuayoushi3 ) {
        this.youhuayoushi3 = youhuayoushi3;
    }

    public Double getZhongjiyoushi1() {
        return zhongjiyoushi1;
    }

    public void setZhongjiyoushi1( Double zhongjiyoushi1 ) {
        this.zhongjiyoushi1 = zhongjiyoushi1;
    }

    public Double getZhongjiyoushi2() {
        return zhongjiyoushi2;
    }

    public void setZhongjiyoushi2( Double zhongjiyoushi2 ) {
        this.zhongjiyoushi2 = zhongjiyoushi2;
    }

    public Double getZhongjiyoushi3() {
        return zhongjiyoushi3;
    }

    public void setZhongjiyoushi3( Double zhongjiyoushi3 ) {
        this.zhongjiyoushi3 = zhongjiyoushi3;
    }

    public Double getYoushicha1() {
        return youshicha1;
    }

    public void setYoushicha1( Double youshicha1 ) {
        this.youshicha1 = youshicha1;
    }

    public Double getYoushicha2() {
        return youshicha2;
    }

    public void setYoushicha2( Double youshicha2 ) {
        this.youshicha2 = youshicha2;
    }

    public Double getYoushicha3() {
        return youshicha3;
    }

    public void setYoushicha3( Double youshicha3 ) {
        this.youshicha3 = youshicha3;
    }

    public Double getShaixuanbi1() {
        return shaixuanbi1;
    }

    public void setShaixuanbi1( Double shaixuanbi1 ) {
        this.shaixuanbi1 = shaixuanbi1;
    }

    public Double getShaixuanbi2() {
        return shaixuanbi2;
    }

    public void setShaixuanbi2( Double shaixuanbi2 ) {
        this.shaixuanbi2 = shaixuanbi2;
    }

    public Double getShaixuanbi3() {
        return shaixuanbi3;
    }

    public void setShaixuanbi3( Double shaixuanbi3 ) {
        this.shaixuanbi3 = shaixuanbi3;
    }

    public Double getShaixuancha1() {
        return shaixuancha1;
    }

    public void setShaixuancha1( Double shaixuancha1 ) {
        this.shaixuancha1 = shaixuancha1;
    }

    public Double getShaixuancha2() {
        return shaixuancha2;
    }

    public void setShaixuancha2( Double shaixuancha2 ) {
        this.shaixuancha2 = shaixuancha2;
    }

    public Double getShaixuancha3() {
        return shaixuancha3;
    }

    public void setShaixuancha3( Double shaixuancha3 ) {
        this.shaixuancha3 = shaixuancha3;
    }

    public Double getZhongjishaixuan1() {
        return zhongjishaixuan1;
    }

    public void setZhongjishaixuan1( Double zhongjishaixuan1 ) {
        this.zhongjishaixuan1 = zhongjishaixuan1;
    }

    public Double getZhongjishaixuan2() {
        return zhongjishaixuan2;
    }

    public void setZhongjishaixuan2( Double zhongjishaixuan2 ) {
        this.zhongjishaixuan2 = zhongjishaixuan2;
    }

    public Double getZhongjishaixuan3() {
        return zhongjishaixuan3;
    }

    public void setZhongjishaixuan3( Double zhongjishaixuan3 ) {
        this.zhongjishaixuan3 = zhongjishaixuan3;
    }

    public Double getChupeizuigaopeilv1() {
        return chupeizuigaopeilv1;
    }

    public void setChupeizuigaopeilv1( Double chupeizuigaopeilv1 ) {
        this.chupeizuigaopeilv1 = chupeizuigaopeilv1;
    }

    public Double getChupeizuigaopeilv2() {
        return chupeizuigaopeilv2;
    }

    public void setChupeizuigaopeilv2( Double chupeizuigaopeilv2 ) {
        this.chupeizuigaopeilv2 = chupeizuigaopeilv2;
    }

    public Double getChupeizuigaopeilv3() {
        return chupeizuigaopeilv3;
    }

    public void setChupeizuigaopeilv3( Double chupeizuigaopeilv3 ) {
        this.chupeizuigaopeilv3 = chupeizuigaopeilv3;
    }

    public Double getJishizuigaopeilv1() {
        return jishizuigaopeilv1;
    }

    public void setJishizuigaopeilv1( Double jishizuigaopeilv1 ) {
        this.jishizuigaopeilv1 = jishizuigaopeilv1;
    }

    public Double getJishizuigaopeilv2() {
        return jishizuigaopeilv2;
    }

    public void setJishizuigaopeilv2( Double jishizuigaopeilv2 ) {
        this.jishizuigaopeilv2 = jishizuigaopeilv2;
    }

    public Double getJishizuigaopeilv3() {
        return jishizuigaopeilv3;
    }

    public void setJishizuigaopeilv3( Double jishizuigaopeilv3 ) {
        this.jishizuigaopeilv3 = jishizuigaopeilv3;
    }

    public Double getZuigaopeilvbianhua1() {
        return zuigaopeilvbianhua1;
    }

    public void setZuigaopeilvbianhua1( Double zuigaopeilvbianhua1 ) {
        this.zuigaopeilvbianhua1 = zuigaopeilvbianhua1;
    }

    public Double getZuigaopeilvbianhua2() {
        return zuigaopeilvbianhua2;
    }

    public void setZuigaopeilvbianhua2( Double zuigaopeilvbianhua2 ) {
        this.zuigaopeilvbianhua2 = zuigaopeilvbianhua2;
    }

    public Double getZuigaopeilvbianhua3() {
        return zuigaopeilvbianhua3;
    }

    public void setZuigaopeilvbianhua3( Double zuigaopeilvbianhua3 ) {
        this.zuigaopeilvbianhua3 = zuigaopeilvbianhua3;
    }

    public Double getChupeizuidipeilv1() {
        return chupeizuidipeilv1;
    }

    public void setChupeizuidipeilv1( Double chupeizuidipeilv1 ) {
        this.chupeizuidipeilv1 = chupeizuidipeilv1;
    }

    public Double getChupeizuidipeilv2() {
        return chupeizuidipeilv2;
    }

    public void setChupeizuidipeilv2( Double chupeizuidipeilv2 ) {
        this.chupeizuidipeilv2 = chupeizuidipeilv2;
    }

    public Double getChupeizuidipeilv3() {
        return chupeizuidipeilv3;
    }

    public void setChupeizuidipeilv3( Double chupeizuidipeilv3 ) {
        this.chupeizuidipeilv3 = chupeizuidipeilv3;
    }

    public Double getJishizuidipeilv1() {
        return jishizuidipeilv1;
    }

    public void setJishizuidipeilv1( Double jishizuidipeilv1 ) {
        this.jishizuidipeilv1 = jishizuidipeilv1;
    }

    public Double getJishizuidipeilv2() {
        return jishizuidipeilv2;
    }

    public void setJishizuidipeilv2( Double jishizuidipeilv2 ) {
        this.jishizuidipeilv2 = jishizuidipeilv2;
    }

    public Double getJishizuidipeilv3() {
        return jishizuidipeilv3;
    }

    public void setJishizuidipeilv3( Double jishizuidipeilv3 ) {
        this.jishizuidipeilv3 = jishizuidipeilv3;
    }

    public Double getZuidipeilvbianhua1() {
        return zuidipeilvbianhua1;
    }

    public void setZuidipeilvbianhua1( Double zuidipeilvbianhua1 ) {
        this.zuidipeilvbianhua1 = zuidipeilvbianhua1;
    }

    public Double getZuidipeilvbianhua2() {
        return zuidipeilvbianhua2;
    }

    public void setZuidipeilvbianhua2( Double zuidipeilvbianhua2 ) {
        this.zuidipeilvbianhua2 = zuidipeilvbianhua2;
    }

    public Double getZuidipeilvbianhua3() {
        return zuidipeilvbianhua3;
    }

    public void setZuidipeilvbianhua3( Double zuidipeilvbianhua3 ) {
        this.zuidipeilvbianhua3 = zuidipeilvbianhua3;
    }

    public Double getChupeipingjunpeilv1() {
        return chupeipingjunpeilv1;
    }

    public void setChupeipingjunpeilv1( Double chupeipingjunpeilv1 ) {
        this.chupeipingjunpeilv1 = chupeipingjunpeilv1;
    }

    public Double getChupeipingjunpeilv2() {
        return chupeipingjunpeilv2;
    }

    public void setChupeipingjunpeilv2( Double chupeipingjunpeilv2 ) {
        this.chupeipingjunpeilv2 = chupeipingjunpeilv2;
    }

    public Double getChupeipingjunpeilv3() {
        return chupeipingjunpeilv3;
    }

    public void setChupeipingjunpeilv3( Double chupeipingjunpeilv3 ) {
        this.chupeipingjunpeilv3 = chupeipingjunpeilv3;
    }

    public Double getJishipingjunpeilv1() {
        return jishipingjunpeilv1;
    }

    public void setJishipingjunpeilv1( Double jishipingjunpeilv1 ) {
        this.jishipingjunpeilv1 = jishipingjunpeilv1;
    }

    public Double getJishipingjunpeilv2() {
        return jishipingjunpeilv2;
    }

    public void setJishipingjunpeilv2( Double jishipingjunpeilv2 ) {
        this.jishipingjunpeilv2 = jishipingjunpeilv2;
    }

    public Double getJishipingjunpeilv3() {
        return jishipingjunpeilv3;
    }

    public void setJishipingjunpeilv3( Double jishipingjunpeilv3 ) {
        this.jishipingjunpeilv3 = jishipingjunpeilv3;
    }

    public Double getPingjunpeilvbianhua1() {
        return pingjunpeilvbianhua1;
    }

    public void setPingjunpeilvbianhua1( Double pingjunpeilvbianhua1 ) {
        this.pingjunpeilvbianhua1 = pingjunpeilvbianhua1;
    }

    public Double getPingjunpeilvbianhua2() {
        return pingjunpeilvbianhua2;
    }

    public void setPingjunpeilvbianhua2( Double pingjunpeilvbianhua2 ) {
        this.pingjunpeilvbianhua2 = pingjunpeilvbianhua2;
    }

    public Double getPingjunpeilvbianhua3() {
        return pingjunpeilvbianhua3;
    }

    public void setPingjunpeilvbianhua3( Double pingjunpeilvbianhua3 ) {
        this.pingjunpeilvbianhua3 = pingjunpeilvbianhua3;
    }

    public Double getChupeizuigaoshenglv1() {
        return chupeizuigaoshenglv1;
    }

    public void setChupeizuigaoshenglv1( Double chupeizuigaoshenglv1 ) {
        this.chupeizuigaoshenglv1 = chupeizuigaoshenglv1;
    }

    public Double getChupeizuigaoshenglv2() {
        return chupeizuigaoshenglv2;
    }

    public void setChupeizuigaoshenglv2( Double chupeizuigaoshenglv2 ) {
        this.chupeizuigaoshenglv2 = chupeizuigaoshenglv2;
    }

    public Double getChupeizuigaoshenglv3() {
        return chupeizuigaoshenglv3;
    }

    public void setChupeizuigaoshenglv3( Double chupeizuigaoshenglv3 ) {
        this.chupeizuigaoshenglv3 = chupeizuigaoshenglv3;
    }

    public Double getJishizuigaoshenglv1() {
        return jishizuigaoshenglv1;
    }

    public void setJishizuigaoshenglv1( Double jishizuigaoshenglv1 ) {
        this.jishizuigaoshenglv1 = jishizuigaoshenglv1;
    }

    public Double getJishizuigaoshenglv2() {
        return jishizuigaoshenglv2;
    }

    public void setJishizuigaoshenglv2( Double jishizuigaoshenglv2 ) {
        this.jishizuigaoshenglv2 = jishizuigaoshenglv2;
    }

    public Double getJishizuigaoshenglv3() {
        return jishizuigaoshenglv3;
    }

    public void setJishizuigaoshenglv3( Double jishizuigaoshenglv3 ) {
        this.jishizuigaoshenglv3 = jishizuigaoshenglv3;
    }

    public Double getZuigaoshenglvbianhua1() {
        return zuigaoshenglvbianhua1;
    }

    public void setZuigaoshenglvbianhua1( Double zuigaoshenglvbianhua1 ) {
        this.zuigaoshenglvbianhua1 = zuigaoshenglvbianhua1;
    }

    public Double getZuigaoshenglvbianhua2() {
        return zuigaoshenglvbianhua2;
    }

    public void setZuigaoshenglvbianhua2( Double zuigaoshenglvbianhua2 ) {
        this.zuigaoshenglvbianhua2 = zuigaoshenglvbianhua2;
    }

    public Double getZuigaoshenglvbianhua3() {
        return zuigaoshenglvbianhua3;
    }

    public void setZuigaoshenglvbianhua3( Double zuigaoshenglvbianhua3 ) {
        this.zuigaoshenglvbianhua3 = zuigaoshenglvbianhua3;
    }

    public Double getChupeizuidishenglv1() {
        return chupeizuidishenglv1;
    }

    public void setChupeizuidishenglv1( Double chupeizuidishenglv1 ) {
        this.chupeizuidishenglv1 = chupeizuidishenglv1;
    }

    public Double getChupeizuidishenglv2() {
        return chupeizuidishenglv2;
    }

    public void setChupeizuidishenglv2( Double chupeizuidishenglv2 ) {
        this.chupeizuidishenglv2 = chupeizuidishenglv2;
    }

    public Double getChupeizuidishenglv3() {
        return chupeizuidishenglv3;
    }

    public void setChupeizuidishenglv3( Double chupeizuidishenglv3 ) {
        this.chupeizuidishenglv3 = chupeizuidishenglv3;
    }

    public Double getJishizuidishenglv1() {
        return jishizuidishenglv1;
    }

    public void setJishizuidishenglv1( Double jishizuidishenglv1 ) {
        this.jishizuidishenglv1 = jishizuidishenglv1;
    }

    public Double getJishizuidishenglv2() {
        return jishizuidishenglv2;
    }

    public void setJishizuidishenglv2( Double jishizuidishenglv2 ) {
        this.jishizuidishenglv2 = jishizuidishenglv2;
    }

    public Double getJishizuidishenglv3() {
        return jishizuidishenglv3;
    }

    public void setJishizuidishenglv3( Double jishizuidishenglv3 ) {
        this.jishizuidishenglv3 = jishizuidishenglv3;
    }

    public Double getZuidishenglvbianhua1() {
        return zuidishenglvbianhua1;
    }

    public void setZuidishenglvbianhua1( Double zuidishenglvbianhua1 ) {
        this.zuidishenglvbianhua1 = zuidishenglvbianhua1;
    }

    public Double getZuidishenglvbianhua2() {
        return zuidishenglvbianhua2;
    }

    public void setZuidishenglvbianhua2( Double zuidishenglvbianhua2 ) {
        this.zuidishenglvbianhua2 = zuidishenglvbianhua2;
    }

    public Double getZuidishenglvbianhua3() {
        return zuidishenglvbianhua3;
    }

    public void setZuidishenglvbianhua3( Double zuidishenglvbianhua3 ) {
        this.zuidishenglvbianhua3 = zuidishenglvbianhua3;
    }

    public Double getChupeipingjunshenglv1() {
        return chupeipingjunshenglv1;
    }

    public void setChupeipingjunshenglv1( Double chupeipingjunshenglv1 ) {
        this.chupeipingjunshenglv1 = chupeipingjunshenglv1;
    }

    public Double getChupeipingjunshenglv2() {
        return chupeipingjunshenglv2;
    }

    public void setChupeipingjunshenglv2( Double chupeipingjunshenglv2 ) {
        this.chupeipingjunshenglv2 = chupeipingjunshenglv2;
    }

    public Double getChupeipingjunshenglv3() {
        return chupeipingjunshenglv3;
    }

    public void setChupeipingjunshenglv3( Double chupeipingjunshenglv3 ) {
        this.chupeipingjunshenglv3 = chupeipingjunshenglv3;
    }

    public Double getJishipingjunshenglv1() {
        return jishipingjunshenglv1;
    }

    public void setJishipingjunshenglv1( Double jishipingjunshenglv1 ) {
        this.jishipingjunshenglv1 = jishipingjunshenglv1;
    }

    public Double getJishipingjunshenglv2() {
        return jishipingjunshenglv2;
    }

    public void setJishipingjunshenglv2( Double jishipingjunshenglv2 ) {
        this.jishipingjunshenglv2 = jishipingjunshenglv2;
    }

    public Double getJishipingjunshenglv3() {
        return jishipingjunshenglv3;
    }

    public void setJishipingjunshenglv3( Double jishipingjunshenglv3 ) {
        this.jishipingjunshenglv3 = jishipingjunshenglv3;
    }

    public Double getPingjunshenglvbianhua1() {
        return pingjunshenglvbianhua1;
    }

    public void setPingjunshenglvbianhua1( Double pingjunshenglvbianhua1 ) {
        this.pingjunshenglvbianhua1 = pingjunshenglvbianhua1;
    }

    public Double getPingjunshenglvbianhua2() {
        return pingjunshenglvbianhua2;
    }

    public void setPingjunshenglvbianhua2( Double pingjunshenglvbianhua2 ) {
        this.pingjunshenglvbianhua2 = pingjunshenglvbianhua2;
    }

    public Double getPingjunshenglvbianhua3() {
        return pingjunshenglvbianhua3;
    }

    public void setPingjunshenglvbianhua3( Double pingjunshenglvbianhua3 ) {
        this.pingjunshenglvbianhua3 = pingjunshenglvbianhua3;
    }

    public Double getYinglizhishu1() {
        return yinglizhishu1;
    }

    public void setYinglizhishu1( Double yinglizhishu1 ) {
        this.yinglizhishu1 = yinglizhishu1;
    }

    public Double getYinglizhishu2() {
        return yinglizhishu2;
    }

    public void setYinglizhishu2( Double yinglizhishu2 ) {
        this.yinglizhishu2 = yinglizhishu2;
    }

    public Double getYinglizhishu3() {
        return yinglizhishu3;
    }

    public void setYinglizhishu3( Double yinglizhishu3 ) {
        this.yinglizhishu3 = yinglizhishu3;
    }

    public Double getFengxianzhishu1() {
        return fengxianzhishu1;
    }

    public void setFengxianzhishu1( Double fengxianzhishu1 ) {
        this.fengxianzhishu1 = fengxianzhishu1;
    }

    public Double getFengxianzhishu2() {
        return fengxianzhishu2;
    }

    public void setFengxianzhishu2( Double fengxianzhishu2 ) {
        this.fengxianzhishu2 = fengxianzhishu2;
    }

    public Double getFengxianzhishu3() {
        return fengxianzhishu3;
    }

    public void setFengxianzhishu3( Double fengxianzhishu3 ) {
        this.fengxianzhishu3 = fengxianzhishu3;
    }

    public Double getYoushichayoubian1() {
        return youshichayoubian1;
    }

    public void setYoushichayoubian1( Double youshichayoubian1 ) {
        this.youshichayoubian1 = youshichayoubian1;
    }

    public Double getYoushichayoubian2() {
        return youshichayoubian2;
    }

    public void setYoushichayoubian2( Double youshichayoubian2 ) {
        this.youshichayoubian2 = youshichayoubian2;
    }

    public Double getYoushichayoubian3() {
        return youshichayoubian3;
    }

    public void setYoushichayoubian3( Double youshichayoubian3 ) {
        this.youshichayoubian3 = youshichayoubian3;
    }

    public Double getWeizhi() {
        return weizhi;
    }

    public void setWeizhi( Double weizhi ) {
        this.weizhi = weizhi;
    }

    public Double getShaixuanbiyoubian1() {
        return shaixuanbiyoubian1;
    }

    public void setShaixuanbiyoubian1( Double shaixuanbiyoubian1 ) {
        this.shaixuanbiyoubian1 = shaixuanbiyoubian1;
    }

    public Double getShaixuanbiyoubian2() {
        return shaixuanbiyoubian2;
    }

    public void setShaixuanbiyoubian2( Double shaixuanbiyoubian2 ) {
        this.shaixuanbiyoubian2 = shaixuanbiyoubian2;
    }

    public Double getShaixuanbiyoubian3() {
        return shaixuanbiyoubian3;
    }

    public void setShaixuanbiyoubian3( Double shaixuanbiyoubian3 ) {
        this.shaixuanbiyoubian3 = shaixuanbiyoubian3;
    }

    public Double getShaixuanchayoubian1() {
        return shaixuanchayoubian1;
    }

    public void setShaixuanchayoubian1( Double shaixuanchayoubian1 ) {
        this.shaixuanchayoubian1 = shaixuanchayoubian1;
    }

    public Double getShaixuanchayoubian2() {
        return shaixuanchayoubian2;
    }

    public void setShaixuanchayoubian2( Double shaixuanchayoubian2 ) {
        this.shaixuanchayoubian2 = shaixuanchayoubian2;
    }

    public Double getShaixuanchayoubian3() {
        return shaixuanchayoubian3;
    }

    public void setShaixuanchayoubian3( Double shaixuanchayoubian3 ) {
        this.shaixuanchayoubian3 = shaixuanchayoubian3;
    }

    public Double getZhongjishaixuanyoubian1() {
        return zhongjishaixuanyoubian1;
    }

    public void setZhongjishaixuanyoubian1( Double zhongjishaixuanyoubian1 ) {
        this.zhongjishaixuanyoubian1 = zhongjishaixuanyoubian1;
    }

    public Double getZhongjishaixuanyoubian2() {
        return zhongjishaixuanyoubian2;
    }

    public void setZhongjishaixuanyoubian2( Double zhongjishaixuanyoubian2 ) {
        this.zhongjishaixuanyoubian2 = zhongjishaixuanyoubian2;
    }

    public Double getZhongjishaixuanyoubian3() {
        return zhongjishaixuanyoubian3;
    }

    public void setZhongjishaixuanyoubian3( Double zhongjishaixuanyoubian3 ) {
        this.zhongjishaixuanyoubian3 = zhongjishaixuanyoubian3;
    }

    public Double getZonghefangchabi1() {
        return zonghefangchabi1;
    }

    public void setZonghefangchabi1( Double zonghefangchabi1 ) {
        this.zonghefangchabi1 = zonghefangchabi1;
    }

    public Double getZonghefangchabi2() {
        return zonghefangchabi2;
    }

    public void setZonghefangchabi2( Double zonghefangchabi2 ) {
        this.zonghefangchabi2 = zonghefangchabi2;
    }

    public Double getZonghefangchabi3() {
        return zonghefangchabi3;
    }

    public void setZonghefangchabi3( Double zonghefangchabi3 ) {
        this.zonghefangchabi3 = zonghefangchabi3;
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

    public Double getPeilvlisanzhi11() {
        return peilvlisanzhi11;
    }

    public void setPeilvlisanzhi11( Double peilvlisanzhi11 ) {
        this.peilvlisanzhi11 = peilvlisanzhi11;
    }

    public Double getPeilvlisanzhi12() {
        return peilvlisanzhi12;
    }

    public void setPeilvlisanzhi12( Double peilvlisanzhi12 ) {
        this.peilvlisanzhi12 = peilvlisanzhi12;
    }

    public Double getPeilvlisanzhi13() {
        return peilvlisanzhi13;
    }

    public void setPeilvlisanzhi13( Double peilvlisanzhi13 ) {
        this.peilvlisanzhi13 = peilvlisanzhi13;
    }

    public Double getPeilvlisanzhi21() {
        return peilvlisanzhi21;
    }

    public void setPeilvlisanzhi21( Double peilvlisanzhi21 ) {
        this.peilvlisanzhi21 = peilvlisanzhi21;
    }

    public Double getPeilvlisanzhi22() {
        return peilvlisanzhi22;
    }

    public void setPeilvlisanzhi22( Double peilvlisanzhi22 ) {
        this.peilvlisanzhi22 = peilvlisanzhi22;
    }

    public Double getPeilvlisanzhi23() {
        return peilvlisanzhi23;
    }

    public void setPeilvlisanzhi23( Double peilvlisanzhi23 ) {
        this.peilvlisanzhi23 = peilvlisanzhi23;
    }

    public Double getBaolengzhishuxin1() {
        return baolengzhishuxin1;
    }

    public void setBaolengzhishuxin1( Double baolengzhishuxin1 ) {
        this.baolengzhishuxin1 = baolengzhishuxin1;
    }

    public Double getBaolengzhishuxin2() {
        return baolengzhishuxin2;
    }

    public void setBaolengzhishuxin2( Double baolengzhishuxin2 ) {
        this.baolengzhishuxin2 = baolengzhishuxin2;
    }

    public Double getBaolengzhishuxin3() {
        return baolengzhishuxin3;
    }

    public void setBaolengzhishuxin3( Double baolengzhishuxin3 ) {
        this.baolengzhishuxin3 = baolengzhishuxin3;
    }

    public Double getLinchangpankou() {
        return linchangpankou;
    }

    public void setLinchangpankou( Double linchangpankou ) {
        this.linchangpankou = linchangpankou;
    }

    public Double getLisanzhichu1() {
        return lisanzhichu1;
    }

    public void setLisanzhichu1( Double lisanzhichu1 ) {
        this.lisanzhichu1 = lisanzhichu1;
    }

    public Double getLisanzhichu2() {
        return lisanzhichu2;
    }

    public void setLisanzhichu2( Double lisanzhichu2 ) {
        this.lisanzhichu2 = lisanzhichu2;
    }

    public Double getLisanzhichu3() {
        return lisanzhichu3;
    }

    public void setLisanzhichu3( Double lisanzhichu3 ) {
        this.lisanzhichu3 = lisanzhichu3;
    }

    public Double getLisanzhiji1() {
        return lisanzhiji1;
    }

    public void setLisanzhiji1( Double lisanzhiji1 ) {
        this.lisanzhiji1 = lisanzhiji1;
    }

    public Double getLisanzhiji2() {
        return lisanzhiji2;
    }

    public void setLisanzhiji2( Double lisanzhiji2 ) {
        this.lisanzhiji2 = lisanzhiji2;
    }

    public Double getLisanzhiji3() {
        return lisanzhiji3;
    }

    public void setLisanzhiji3( Double lisanzhiji3 ) {
        this.lisanzhiji3 = lisanzhiji3;
    }

    public Double getLisanzhichachu1() {
        return lisanzhichachu1;
    }

    public void setLisanzhichachu1( Double lisanzhichachu1 ) {
        this.lisanzhichachu1 = lisanzhichachu1;
    }

    public Double getLisanzhichachu2() {
        return lisanzhichachu2;
    }

    public void setLisanzhichachu2( Double lisanzhichachu2 ) {
        this.lisanzhichachu2 = lisanzhichachu2;
    }

    public Double getLisanzhichachu3() {
        return lisanzhichachu3;
    }

    public void setLisanzhichachu3( Double lisanzhichachu3 ) {
        this.lisanzhichachu3 = lisanzhichachu3;
    }

    public Double getLisanzhichaji1() {
        return lisanzhichaji1;
    }

    public void setLisanzhichaji1( Double lisanzhichaji1 ) {
        this.lisanzhichaji1 = lisanzhichaji1;
    }

    public Double getLisanzhichaji2() {
        return lisanzhichaji2;
    }

    public void setLisanzhichaji2( Double lisanzhichaji2 ) {
        this.lisanzhichaji2 = lisanzhichaji2;
    }

    public Double getLisanzhichaji3() {
        return lisanzhichaji3;
    }

    public void setLisanzhichaji3( Double lisanzhichaji3 ) {
        this.lisanzhichaji3 = lisanzhichaji3;
    }

    public Double getLisanzhishu1() {
        return lisanzhishu1;
    }

    public void setLisanzhishu1( Double lisanzhishu1 ) {
        this.lisanzhishu1 = lisanzhishu1;
    }

    public Double getLisanzhishu2() {
        return lisanzhishu2;
    }

    public void setLisanzhishu2( Double lisanzhishu2 ) {
        this.lisanzhishu2 = lisanzhishu2;
    }

    public Double getLisanzhishu3() {
        return lisanzhishu3;
    }

    public void setLisanzhishu3( Double lisanzhishu3 ) {
        this.lisanzhishu3 = lisanzhishu3;
    }

    public Double getLisanbi1() {
        return lisanbi1;
    }

    public void setLisanbi1( Double lisanbi1 ) {
        this.lisanbi1 = lisanbi1;
    }

    public Double getLisanbi2() {
        return lisanbi2;
    }

    public void setLisanbi2( Double lisanbi2 ) {
        this.lisanbi2 = lisanbi2;
    }

    public Double getLisanbi3() {
        return lisanbi3;
    }

    public void setLisanbi3( Double lisanbi3 ) {
        this.lisanbi3 = lisanbi3;
    }

    public Double getBianyilisanzhishu1() {
        return bianyilisanzhishu1;
    }

    public void setBianyilisanzhishu1( Double bianyilisanzhishu1 ) {
        this.bianyilisanzhishu1 = bianyilisanzhishu1;
    }

    public Double getBianyilisanzhishu2() {
        return bianyilisanzhishu2;
    }

    public void setBianyilisanzhishu2( Double bianyilisanzhishu2 ) {
        this.bianyilisanzhishu2 = bianyilisanzhishu2;
    }

    public Double getBianyilisanzhishu3() {
        return bianyilisanzhishu3;
    }

    public void setBianyilisanzhishu3( Double bianyilisanzhishu3 ) {
        this.bianyilisanzhishu3 = bianyilisanzhishu3;
    }

    public Double getBianyilisanzhishuxia1() {
        return bianyilisanzhishuxia1;
    }

    public void setBianyilisanzhishuxia1( Double bianyilisanzhishuxia1 ) {
        this.bianyilisanzhishuxia1 = bianyilisanzhishuxia1;
    }

    public Double getBianyilisanzhishuxia2() {
        return bianyilisanzhishuxia2;
    }

    public void setBianyilisanzhishuxia2( Double bianyilisanzhishuxia2 ) {
        this.bianyilisanzhishuxia2 = bianyilisanzhishuxia2;
    }

    public Double getBianyilisanzhishuxia3() {
        return bianyilisanzhishuxia3;
    }

    public void setBianyilisanzhishuxia3( Double bianyilisanzhishuxia3 ) {
        this.bianyilisanzhishuxia3 = bianyilisanzhishuxia3;
    }

    public Double getQujianqiwangchaquan1() {
        return qujianqiwangchaquan1;
    }

    public void setQujianqiwangchaquan1( Double qujianqiwangchaquan1 ) {
        this.qujianqiwangchaquan1 = qujianqiwangchaquan1;
    }

    public Double getQujianqiwangchaquan2() {
        return qujianqiwangchaquan2;
    }

    public void setQujianqiwangchaquan2( Double qujianqiwangchaquan2 ) {
        this.qujianqiwangchaquan2 = qujianqiwangchaquan2;
    }

    public Double getQujianqiwangchaquan3() {
        return qujianqiwangchaquan3;
    }

    public void setQujianqiwangchaquan3( Double qujianqiwangchaquan3 ) {
        this.qujianqiwangchaquan3 = qujianqiwangchaquan3;
    }

    public Double getQujianqiwangchazhu1() {
        return qujianqiwangchazhu1;
    }

    public void setQujianqiwangchazhu1( Double qujianqiwangchazhu1 ) {
        this.qujianqiwangchazhu1 = qujianqiwangchazhu1;
    }

    public Double getQujianqiwangchazhu2() {
        return qujianqiwangchazhu2;
    }

    public void setQujianqiwangchazhu2( Double qujianqiwangchazhu2 ) {
        this.qujianqiwangchazhu2 = qujianqiwangchazhu2;
    }

    public Double getQujianqiwangchazhu3() {
        return qujianqiwangchazhu3;
    }

    public void setQujianqiwangchazhu3( Double qujianqiwangchazhu3 ) {
        this.qujianqiwangchazhu3 = qujianqiwangchazhu3;
    }

    public Double getFc1() {
        return fc1;
    }

    public void setFc1( Double fc1 ) {
        this.fc1 = fc1;
    }

    public Double getFc2() {
        return fc2;
    }

    public void setFc2( Double fc2 ) {
        this.fc2 = fc2;
    }

    public Double getFc3() {
        return fc3;
    }

    public void setFc3( Double fc3 ) {
        this.fc3 = fc3;
    }

    public Double getFd1() {
        return fd1;
    }

    public void setFd1( Double fd1 ) {
        this.fd1 = fd1;
    }

    public Double getFd2() {
        return fd2;
    }

    public void setFd2( Double fd2 ) {
        this.fd2 = fd2;
    }

    public Double getFd3() {
        return fd3;
    }

    public void setFd3( Double fd3 ) {
        this.fd3 = fd3;
    }

    public Double getQiwangfengxianbi1() {
        return qiwangfengxianbi1;
    }

    public void setQiwangfengxianbi1( Double qiwangfengxianbi1 ) {
        this.qiwangfengxianbi1 = qiwangfengxianbi1;
    }

    public Double getQiwangfengxianbi2() {
        return qiwangfengxianbi2;
    }

    public void setQiwangfengxianbi2( Double qiwangfengxianbi2 ) {
        this.qiwangfengxianbi2 = qiwangfengxianbi2;
    }

    public Double getQiwangfengxianbi3() {
        return qiwangfengxianbi3;
    }

    public void setQiwangfengxianbi3( Double qiwangfengxianbi3 ) {
        this.qiwangfengxianbi3 = qiwangfengxianbi3;
    }

    public Double getZonghezhishu1() {
        return zonghezhishu1;
    }

    public void setZonghezhishu1( Double zonghezhishu1 ) {
        this.zonghezhishu1 = zonghezhishu1;
    }

    public Double getZonghezhishu2() {
        return zonghezhishu2;
    }

    public void setZonghezhishu2( Double zonghezhishu2 ) {
        this.zonghezhishu2 = zonghezhishu2;
    }

    public Double getZonghezhishu3() {
        return zonghezhishu3;
    }

    public void setZonghezhishu3( Double zonghezhishu3 ) {
        this.zonghezhishu3 = zonghezhishu3;
    }

    public Double getPeilvfangchahe1() {
        return peilvfangchahe1;
    }

    public void setPeilvfangchahe1( Double peilvfangchahe1 ) {
        this.peilvfangchahe1 = peilvfangchahe1;
    }

    public Double getPeilvfangchahe2() {
        return peilvfangchahe2;
    }

    public void setPeilvfangchahe2( Double peilvfangchahe2 ) {
        this.peilvfangchahe2 = peilvfangchahe2;
    }

    public Double getPeilvfangchahe3() {
        return peilvfangchahe3;
    }

    public void setPeilvfangchahe3( Double peilvfangchahe3 ) {
        this.peilvfangchahe3 = peilvfangchahe3;
    }

    public Double getZonghefengxianbi1() {
        return zonghefengxianbi1;
    }

    public void setZonghefengxianbi1( Double zonghefengxianbi1 ) {
        this.zonghefengxianbi1 = zonghefengxianbi1;
    }

    public Double getZonghefengxianbi2() {
        return zonghefengxianbi2;
    }

    public void setZonghefengxianbi2( Double zonghefengxianbi2 ) {
        this.zonghefengxianbi2 = zonghefengxianbi2;
    }

    public Double getZonghefengxianbi3() {
        return zonghefengxianbi3;
    }

    public void setZonghefengxianbi3( Double zonghefengxianbi3 ) {
        this.zonghefengxianbi3 = zonghefengxianbi3;
    }

    public Double getKailizhishucha1() {
        return kailizhishucha1;
    }

    public void setKailizhishucha1( Double kailizhishucha1 ) {
        this.kailizhishucha1 = kailizhishucha1;
    }

    public Double getKailizhishucha2() {
        return kailizhishucha2;
    }

    public void setKailizhishucha2( Double kailizhishucha2 ) {
        this.kailizhishucha2 = kailizhishucha2;
    }

    public Double getKailizhishucha3() {
        return kailizhishucha3;
    }

    public void setKailizhishucha3( Double kailizhishucha3 ) {
        this.kailizhishucha3 = kailizhishucha3;
    }

    public Double getPellvqujianchu1() {
        return pellvqujianchu1;
    }

    public void setPellvqujianchu1( Double pellvqujianchu1 ) {
        this.pellvqujianchu1 = pellvqujianchu1;
    }

    public Double getPellvqujianchu2() {
        return pellvqujianchu2;
    }

    public void setPellvqujianchu2( Double pellvqujianchu2 ) {
        this.pellvqujianchu2 = pellvqujianchu2;
    }

    public Double getPellvqujianchu3() {
        return pellvqujianchu3;
    }

    public void setPellvqujianchu3( Double pellvqujianchu3 ) {
        this.pellvqujianchu3 = pellvqujianchu3;
    }

    public Double getPeilvqujianji1() {
        return peilvqujianji1;
    }

    public void setPeilvqujianji1( Double peilvqujianji1 ) {
        this.peilvqujianji1 = peilvqujianji1;
    }

    public Double getPeilvqujianji2() {
        return peilvqujianji2;
    }

    public void setPeilvqujianji2( Double peilvqujianji2 ) {
        this.peilvqujianji2 = peilvqujianji2;
    }

    public Double getPeilvqujianji3() {
        return peilvqujianji3;
    }

    public void setPeilvqujianji3( Double peilvqujianji3 ) {
        this.peilvqujianji3 = peilvqujianji3;
    }

    public Double getPeilvqujianbi1() {
        return peilvqujianbi1;
    }

    public void setPeilvqujianbi1( Double peilvqujianbi1 ) {
        this.peilvqujianbi1 = peilvqujianbi1;
    }

    public Double getPeilvqujianbi2() {
        return peilvqujianbi2;
    }

    public void setPeilvqujianbi2( Double peilvqujianbi2 ) {
        this.peilvqujianbi2 = peilvqujianbi2;
    }

    public Double getPeilvqujianbi3() {
        return peilvqujianbi3;
    }

    public void setPeilvqujianbi3( Double peilvqujianbi3 ) {
        this.peilvqujianbi3 = peilvqujianbi3;
    }

    public Double getPeifuzhishutiaozhengxia11() {
        return peifuzhishutiaozhengxia11;
    }

    public void setPeifuzhishutiaozhengxia11( Double peifuzhishutiaozhengxia11 ) {
        this.peifuzhishutiaozhengxia11 = peifuzhishutiaozhengxia11;
    }

    public Double getPeifuzhishutiaozhengxia12() {
        return peifuzhishutiaozhengxia12;
    }

    public void setPeifuzhishutiaozhengxia12( Double peifuzhishutiaozhengxia12 ) {
        this.peifuzhishutiaozhengxia12 = peifuzhishutiaozhengxia12;
    }

    public Double getPeifuzhishutiaozhengxia13() {
        return peifuzhishutiaozhengxia13;
    }

    public void setPeifuzhishutiaozhengxia13( Double peifuzhishutiaozhengxia13 ) {
        this.peifuzhishutiaozhengxia13 = peifuzhishutiaozhengxia13;
    }

    public Double getPeifuzhishutiaozhengxia21() {
        return peifuzhishutiaozhengxia21;
    }

    public void setPeifuzhishutiaozhengxia21( Double peifuzhishutiaozhengxia21 ) {
        this.peifuzhishutiaozhengxia21 = peifuzhishutiaozhengxia21;
    }

    public Double getPeifuzhishutiaozhengxia22() {
        return peifuzhishutiaozhengxia22;
    }

    public void setPeifuzhishutiaozhengxia22( Double peifuzhishutiaozhengxia22 ) {
        this.peifuzhishutiaozhengxia22 = peifuzhishutiaozhengxia22;
    }

    public Double getPeifuzhishutiaozhengxia23() {
        return peifuzhishutiaozhengxia23;
    }

    public void setPeifuzhishutiaozhengxia23( Double peifuzhishutiaozhengxia23 ) {
        this.peifuzhishutiaozhengxia23 = peifuzhishutiaozhengxia23;
    }

    public Double getPeifuzhishutiaozhengxia31() {
        return peifuzhishutiaozhengxia31;
    }

    public void setPeifuzhishutiaozhengxia31( Double peifuzhishutiaozhengxia31 ) {
        this.peifuzhishutiaozhengxia31 = peifuzhishutiaozhengxia31;
    }

    public Double getPeifuzhishutiaozhengxia32() {
        return peifuzhishutiaozhengxia32;
    }

    public void setPeifuzhishutiaozhengxia32( Double peifuzhishutiaozhengxia32 ) {
        this.peifuzhishutiaozhengxia32 = peifuzhishutiaozhengxia32;
    }

    public Double getPeifuzhishutiaozhengxia33() {
        return peifuzhishutiaozhengxia33;
    }

    public void setPeifuzhishutiaozhengxia33( Double peifuzhishutiaozhengxia33 ) {
        this.peifuzhishutiaozhengxia33 = peifuzhishutiaozhengxia33;
    }

    public Double getPeifuzhishutiaozhengxia41() {
        return peifuzhishutiaozhengxia41;
    }

    public void setPeifuzhishutiaozhengxia41( Double peifuzhishutiaozhengxia41 ) {
        this.peifuzhishutiaozhengxia41 = peifuzhishutiaozhengxia41;
    }

    public Double getPeifuzhishutiaozhengxia42() {
        return peifuzhishutiaozhengxia42;
    }

    public void setPeifuzhishutiaozhengxia42( Double peifuzhishutiaozhengxia42 ) {
        this.peifuzhishutiaozhengxia42 = peifuzhishutiaozhengxia42;
    }

    public Double getPeifuzhishutiaozhengxia43() {
        return peifuzhishutiaozhengxia43;
    }

    public void setPeifuzhishutiaozhengxia43( Double peifuzhishutiaozhengxia43 ) {
        this.peifuzhishutiaozhengxia43 = peifuzhishutiaozhengxia43;
    }

    public Double getPeifuzhishutiaozhengxia51() {
        return peifuzhishutiaozhengxia51;
    }

    public void setPeifuzhishutiaozhengxia51( Double peifuzhishutiaozhengxia51 ) {
        this.peifuzhishutiaozhengxia51 = peifuzhishutiaozhengxia51;
    }

    public Double getPeifuzhishutiaozhengxia52() {
        return peifuzhishutiaozhengxia52;
    }

    public void setPeifuzhishutiaozhengxia52( Double peifuzhishutiaozhengxia52 ) {
        this.peifuzhishutiaozhengxia52 = peifuzhishutiaozhengxia52;
    }

    public Double getPeifuzhishutiaozhengxia53() {
        return peifuzhishutiaozhengxia53;
    }

    public void setPeifuzhishutiaozhengxia53( Double peifuzhishutiaozhengxia53 ) {
        this.peifuzhishutiaozhengxia53 = peifuzhishutiaozhengxia53;
    }

    public Double getPeifuzhishutiaozhengxia61() {
        return peifuzhishutiaozhengxia61;
    }

    public void setPeifuzhishutiaozhengxia61( Double peifuzhishutiaozhengxia61 ) {
        this.peifuzhishutiaozhengxia61 = peifuzhishutiaozhengxia61;
    }

    public Double getPeifuzhishutiaozhengxia62() {
        return peifuzhishutiaozhengxia62;
    }

    public void setPeifuzhishutiaozhengxia62( Double peifuzhishutiaozhengxia62 ) {
        this.peifuzhishutiaozhengxia62 = peifuzhishutiaozhengxia62;
    }

    public Double getPeifuzhishutiaozhengxia63() {
        return peifuzhishutiaozhengxia63;
    }

    public void setPeifuzhishutiaozhengxia63( Double peifuzhishutiaozhengxia63 ) {
        this.peifuzhishutiaozhengxia63 = peifuzhishutiaozhengxia63;
    }

    public Double getQiwangfangchabi1() {
        return qiwangfangchabi1;
    }

    public void setQiwangfangchabi1( Double qiwangfangchabi1 ) {
        this.qiwangfangchabi1 = qiwangfangchabi1;
    }

    public Double getQiwangfangchabi2() {
        return qiwangfangchabi2;
    }

    public void setQiwangfangchabi2( Double qiwangfangchabi2 ) {
        this.qiwangfangchabi2 = qiwangfangchabi2;
    }

    public Double getQiwangfangchabi3() {
        return qiwangfangchabi3;
    }

    public void setQiwangfangchabi3( Double qiwangfangchabi3 ) {
        this.qiwangfangchabi3 = qiwangfangchabi3;
    }

    public Double getJunhengzhishu1() {
        return junhengzhishu1;
    }

    public void setJunhengzhishu1( Double junhengzhishu1 ) {
        this.junhengzhishu1 = junhengzhishu1;
    }

    public Double getJunhengzhishu2() {
        return junhengzhishu2;
    }

    public void setJunhengzhishu2( Double junhengzhishu2 ) {
        this.junhengzhishu2 = junhengzhishu2;
    }

    public Double getJunhengzhishu3() {
        return junhengzhishu3;
    }

    public void setJunhengzhishu3( Double junhengzhishu3 ) {
        this.junhengzhishu3 = junhengzhishu3;
    }

    public Double getLisanzhichuyoubian1() {
        return lisanzhichuyoubian1;
    }

    public void setLisanzhichuyoubian1( Double lisanzhichuyoubian1 ) {
        this.lisanzhichuyoubian1 = lisanzhichuyoubian1;
    }

    public Double getLisanzhichuyoubian2() {
        return lisanzhichuyoubian2;
    }

    public void setLisanzhichuyoubian2( Double lisanzhichuyoubian2 ) {
        this.lisanzhichuyoubian2 = lisanzhichuyoubian2;
    }

    public Double getLisanzhichuyoubian3() {
        return lisanzhichuyoubian3;
    }

    public void setLisanzhichuyoubian3( Double lisanzhichuyoubian3 ) {
        this.lisanzhichuyoubian3 = lisanzhichuyoubian3;
    }

    public Double getLisanzhijiyoubian1() {
        return lisanzhijiyoubian1;
    }

    public void setLisanzhijiyoubian1( Double lisanzhijiyoubian1 ) {
        this.lisanzhijiyoubian1 = lisanzhijiyoubian1;
    }

    public Double getLisanzhijiyoubian2() {
        return lisanzhijiyoubian2;
    }

    public void setLisanzhijiyoubian2( Double lisanzhijiyoubian2 ) {
        this.lisanzhijiyoubian2 = lisanzhijiyoubian2;
    }

    public Double getLisanzhijiyoubian3() {
        return lisanzhijiyoubian3;
    }

    public void setLisanzhijiyoubian3( Double lisanzhijiyoubian3 ) {
        this.lisanzhijiyoubian3 = lisanzhijiyoubian3;
    }

    public Double getLisanzhichachuyoubian1() {
        return lisanzhichachuyoubian1;
    }

    public void setLisanzhichachuyoubian1( Double lisanzhichachuyoubian1 ) {
        this.lisanzhichachuyoubian1 = lisanzhichachuyoubian1;
    }

    public Double getLisanzhichachuyoubian2() {
        return lisanzhichachuyoubian2;
    }

    public void setLisanzhichachuyoubian2( Double lisanzhichachuyoubian2 ) {
        this.lisanzhichachuyoubian2 = lisanzhichachuyoubian2;
    }

    public Double getLisanzhichachuyoubian3() {
        return lisanzhichachuyoubian3;
    }

    public void setLisanzhichachuyoubian3( Double lisanzhichachuyoubian3 ) {
        this.lisanzhichachuyoubian3 = lisanzhichachuyoubian3;
    }

    public Double getLisanzhichajiyoubian1() {
        return lisanzhichajiyoubian1;
    }

    public void setLisanzhichajiyoubian1( Double lisanzhichajiyoubian1 ) {
        this.lisanzhichajiyoubian1 = lisanzhichajiyoubian1;
    }

    public Double getLisanzhichajiyoubian2() {
        return lisanzhichajiyoubian2;
    }

    public void setLisanzhichajiyoubian2( Double lisanzhichajiyoubian2 ) {
        this.lisanzhichajiyoubian2 = lisanzhichajiyoubian2;
    }

    public Double getLisanzhichajiyoubian3() {
        return lisanzhichajiyoubian3;
    }

    public void setLisanzhichajiyoubian3( Double lisanzhichajiyoubian3 ) {
        this.lisanzhichajiyoubian3 = lisanzhichajiyoubian3;
    }

    public Double getLisanzhibianhualvyoubian1() {
        return lisanzhibianhualvyoubian1;
    }

    public void setLisanzhibianhualvyoubian1( Double lisanzhibianhualvyoubian1 ) {
        this.lisanzhibianhualvyoubian1 = lisanzhibianhualvyoubian1;
    }

    public Double getLisanzhibianhualvyoubian2() {
        return lisanzhibianhualvyoubian2;
    }

    public void setLisanzhibianhualvyoubian2( Double lisanzhibianhualvyoubian2 ) {
        this.lisanzhibianhualvyoubian2 = lisanzhibianhualvyoubian2;
    }

    public Double getLisanzhibianhualvyoubian3() {
        return lisanzhibianhualvyoubian3;
    }

    public void setLisanzhibianhualvyoubian3( Double lisanzhibianhualvyoubian3 ) {
        this.lisanzhibianhualvyoubian3 = lisanzhibianhualvyoubian3;
    }

    public Double getLisanzhishuyoubian1() {
        return lisanzhishuyoubian1;
    }

    public void setLisanzhishuyoubian1( Double lisanzhishuyoubian1 ) {
        this.lisanzhishuyoubian1 = lisanzhishuyoubian1;
    }

    public Double getLisanzhishuyoubian2() {
        return lisanzhishuyoubian2;
    }

    public void setLisanzhishuyoubian2( Double lisanzhishuyoubian2 ) {
        this.lisanzhishuyoubian2 = lisanzhishuyoubian2;
    }

    public Double getLisanzhishuyoubian3() {
        return lisanzhishuyoubian3;
    }

    public void setLisanzhishuyoubian3( Double lisanzhishuyoubian3 ) {
        this.lisanzhishuyoubian3 = lisanzhishuyoubian3;
    }

    public Double getLisanbiyoubian1() {
        return lisanbiyoubian1;
    }

    public void setLisanbiyoubian1( Double lisanbiyoubian1 ) {
        this.lisanbiyoubian1 = lisanbiyoubian1;
    }

    public Double getLisanbiyoubian2() {
        return lisanbiyoubian2;
    }

    public void setLisanbiyoubian2( Double lisanbiyoubian2 ) {
        this.lisanbiyoubian2 = lisanbiyoubian2;
    }

    public Double getLisanbiyoubian3() {
        return lisanbiyoubian3;
    }

    public void setLisanbiyoubian3( Double lisanbiyoubian3 ) {
        this.lisanbiyoubian3 = lisanbiyoubian3;
    }

    public Double getBianyilisanzhishuyoubian1() {
        return bianyilisanzhishuyoubian1;
    }

    public void setBianyilisanzhishuyoubian1( Double bianyilisanzhishuyoubian1 ) {
        this.bianyilisanzhishuyoubian1 = bianyilisanzhishuyoubian1;
    }

    public Double getBianyilisanzhishuyoubian2() {
        return bianyilisanzhishuyoubian2;
    }

    public void setBianyilisanzhishuyoubian2( Double bianyilisanzhishuyoubian2 ) {
        this.bianyilisanzhishuyoubian2 = bianyilisanzhishuyoubian2;
    }

    public Double getBianyilisanzhishuyoubian3() {
        return bianyilisanzhishuyoubian3;
    }

    public void setBianyilisanzhishuyoubian3( Double bianyilisanzhishuyoubian3 ) {
        this.bianyilisanzhishuyoubian3 = bianyilisanzhishuyoubian3;
    }

    public Double getBianyilisanzhishuxiayoubian1() {
        return bianyilisanzhishuxiayoubian1;
    }

    public void setBianyilisanzhishuxiayoubian1( Double bianyilisanzhishuxiayoubian1 ) {
        this.bianyilisanzhishuxiayoubian1 = bianyilisanzhishuxiayoubian1;
    }

    public Double getBianyilisanzhishuxiayoubian2() {
        return bianyilisanzhishuxiayoubian2;
    }

    public void setBianyilisanzhishuxiayoubian2( Double bianyilisanzhishuxiayoubian2 ) {
        this.bianyilisanzhishuxiayoubian2 = bianyilisanzhishuxiayoubian2;
    }

    public Double getBianyilisanzhishuxiayoubian3() {
        return bianyilisanzhishuxiayoubian3;
    }

    public void setBianyilisanzhishuxiayoubian3( Double bianyilisanzhishuxiayoubian3 ) {
        this.bianyilisanzhishuxiayoubian3 = bianyilisanzhishuxiayoubian3;
    }

    public Double getQujianqiwangchaquanyoubian1() {
        return qujianqiwangchaquanyoubian1;
    }

    public void setQujianqiwangchaquanyoubian1( Double qujianqiwangchaquanyoubian1 ) {
        this.qujianqiwangchaquanyoubian1 = qujianqiwangchaquanyoubian1;
    }

    public Double getQujianqiwangchaquanyoubian2() {
        return qujianqiwangchaquanyoubian2;
    }

    public void setQujianqiwangchaquanyoubian2( Double qujianqiwangchaquanyoubian2 ) {
        this.qujianqiwangchaquanyoubian2 = qujianqiwangchaquanyoubian2;
    }

    public Double getQujianqiwangchaquanyoubian3() {
        return qujianqiwangchaquanyoubian3;
    }

    public void setQujianqiwangchaquanyoubian3( Double qujianqiwangchaquanyoubian3 ) {
        this.qujianqiwangchaquanyoubian3 = qujianqiwangchaquanyoubian3;
    }

    public Double getQujianqiwangchazhuyoubian1() {
        return qujianqiwangchazhuyoubian1;
    }

    public void setQujianqiwangchazhuyoubian1( Double qujianqiwangchazhuyoubian1 ) {
        this.qujianqiwangchazhuyoubian1 = qujianqiwangchazhuyoubian1;
    }

    public Double getQujianqiwangchazhuyoubian2() {
        return qujianqiwangchazhuyoubian2;
    }

    public void setQujianqiwangchazhuyoubian2( Double qujianqiwangchazhuyoubian2 ) {
        this.qujianqiwangchazhuyoubian2 = qujianqiwangchazhuyoubian2;
    }

    public Double getQujianqiwangchazhuyoubian3() {
        return qujianqiwangchazhuyoubian3;
    }

    public void setQujianqiwangchazhuyoubian3( Double qujianqiwangchazhuyoubian3 ) {
        this.qujianqiwangchazhuyoubian3 = qujianqiwangchazhuyoubian3;
    }

    public Double getFcyoubian1() {
        return fcyoubian1;
    }

    public void setFcyoubian1( Double fcyoubian1 ) {
        this.fcyoubian1 = fcyoubian1;
    }

    public Double getFcyoubian2() {
        return fcyoubian2;
    }

    public void setFcyoubian2( Double fcyoubian2 ) {
        this.fcyoubian2 = fcyoubian2;
    }

    public Double getFcyoubian3() {
        return fcyoubian3;
    }

    public void setFcyoubian3( Double fcyoubian3 ) {
        this.fcyoubian3 = fcyoubian3;
    }

    public Double getFdyoubian1() {
        return fdyoubian1;
    }

    public void setFdyoubian1( Double fdyoubian1 ) {
        this.fdyoubian1 = fdyoubian1;
    }

    public Double getFdyoubian2() {
        return fdyoubian2;
    }

    public void setFdyoubian2( Double fdyoubian2 ) {
        this.fdyoubian2 = fdyoubian2;
    }

    public Double getFdyoubian3() {
        return fdyoubian3;
    }

    public void setFdyoubian3( Double fdyoubian3 ) {
        this.fdyoubian3 = fdyoubian3;
    }

    public Double getQiwangfengxianbiyoubian1() {
        return qiwangfengxianbiyoubian1;
    }

    public void setQiwangfengxianbiyoubian1( Double qiwangfengxianbiyoubian1 ) {
        this.qiwangfengxianbiyoubian1 = qiwangfengxianbiyoubian1;
    }

    public Double getQiwangfengxianbiyoubian2() {
        return qiwangfengxianbiyoubian2;
    }

    public void setQiwangfengxianbiyoubian2( Double qiwangfengxianbiyoubian2 ) {
        this.qiwangfengxianbiyoubian2 = qiwangfengxianbiyoubian2;
    }

    public Double getQiwangfengxianbiyoubian3() {
        return qiwangfengxianbiyoubian3;
    }

    public void setQiwangfengxianbiyoubian3( Double qiwangfengxianbiyoubian3 ) {
        this.qiwangfengxianbiyoubian3 = qiwangfengxianbiyoubian3;
    }

    public Double getZonghezhishuyoubian1() {
        return zonghezhishuyoubian1;
    }

    public void setZonghezhishuyoubian1( Double zonghezhishuyoubian1 ) {
        this.zonghezhishuyoubian1 = zonghezhishuyoubian1;
    }

    public Double getZonghezhishuyoubian2() {
        return zonghezhishuyoubian2;
    }

    public void setZonghezhishuyoubian2( Double zonghezhishuyoubian2 ) {
        this.zonghezhishuyoubian2 = zonghezhishuyoubian2;
    }

    public Double getZonghezhishuyoubian3() {
        return zonghezhishuyoubian3;
    }

    public void setZonghezhishuyoubian3( Double zonghezhishuyoubian3 ) {
        this.zonghezhishuyoubian3 = zonghezhishuyoubian3;
    }

    public Double getPeilvfangchaheyoubian1() {
        return peilvfangchaheyoubian1;
    }

    public void setPeilvfangchaheyoubian1( Double peilvfangchaheyoubian1 ) {
        this.peilvfangchaheyoubian1 = peilvfangchaheyoubian1;
    }

    public Double getPeilvfangchaheyoubian2() {
        return peilvfangchaheyoubian2;
    }

    public void setPeilvfangchaheyoubian2( Double peilvfangchaheyoubian2 ) {
        this.peilvfangchaheyoubian2 = peilvfangchaheyoubian2;
    }

    public Double getPeilvfangchaheyoubian3() {
        return peilvfangchaheyoubian3;
    }

    public void setPeilvfangchaheyoubian3( Double peilvfangchaheyoubian3 ) {
        this.peilvfangchaheyoubian3 = peilvfangchaheyoubian3;
    }

    public Double getZonghefengxianbiyoubian1() {
        return zonghefengxianbiyoubian1;
    }

    public void setZonghefengxianbiyoubian1( Double zonghefengxianbiyoubian1 ) {
        this.zonghefengxianbiyoubian1 = zonghefengxianbiyoubian1;
    }

    public Double getZonghefengxianbiyoubian2() {
        return zonghefengxianbiyoubian2;
    }

    public void setZonghefengxianbiyoubian2( Double zonghefengxianbiyoubian2 ) {
        this.zonghefengxianbiyoubian2 = zonghefengxianbiyoubian2;
    }

    public Double getZonghefengxianbiyoubian3() {
        return zonghefengxianbiyoubian3;
    }

    public void setZonghefengxianbiyoubian3( Double zonghefengxianbiyoubian3 ) {
        this.zonghefengxianbiyoubian3 = zonghefengxianbiyoubian3;
    }

    public Double getKailizhishuchayoubian1() {
        return kailizhishuchayoubian1;
    }

    public void setKailizhishuchayoubian1( Double kailizhishuchayoubian1 ) {
        this.kailizhishuchayoubian1 = kailizhishuchayoubian1;
    }

    public Double getKailizhishuchayoubian2() {
        return kailizhishuchayoubian2;
    }

    public void setKailizhishuchayoubian2( Double kailizhishuchayoubian2 ) {
        this.kailizhishuchayoubian2 = kailizhishuchayoubian2;
    }

    public Double getKailizhishuchayoubian3() {
        return kailizhishuchayoubian3;
    }

    public void setKailizhishuchayoubian3( Double kailizhishuchayoubian3 ) {
        this.kailizhishuchayoubian3 = kailizhishuchayoubian3;
    }

    public Double getBaolengzhishuyoubian1() {
        return baolengzhishuyoubian1;
    }

    public void setBaolengzhishuyoubian1( Double baolengzhishuyoubian1 ) {
        this.baolengzhishuyoubian1 = baolengzhishuyoubian1;
    }

    public Double getBaolengzhishuyoubian2() {
        return baolengzhishuyoubian2;
    }

    public void setBaolengzhishuyoubian2( Double baolengzhishuyoubian2 ) {
        this.baolengzhishuyoubian2 = baolengzhishuyoubian2;
    }

    public Double getBaolengzhishuyoubian3() {
        return baolengzhishuyoubian3;
    }

    public void setBaolengzhishuyoubian3( Double baolengzhishuyoubian3 ) {
        this.baolengzhishuyoubian3 = baolengzhishuyoubian3;
    }

    public Double getZhibiao1youbian1() {
        return zhibiao1youbian1;
    }

    public void setZhibiao1youbian1( Double zhibiao1youbian1 ) {
        this.zhibiao1youbian1 = zhibiao1youbian1;
    }

    public Double getZhibiao1youbian2() {
        return zhibiao1youbian2;
    }

    public void setZhibiao1youbian2( Double zhibiao1youbian2 ) {
        this.zhibiao1youbian2 = zhibiao1youbian2;
    }

    public Double getZhibiao1youbian3() {
        return zhibiao1youbian3;
    }

    public void setZhibiao1youbian3( Double zhibiao1youbian3 ) {
        this.zhibiao1youbian3 = zhibiao1youbian3;
    }

    public Double getPeilvqujianchuyoubian1() {
        return peilvqujianchuyoubian1;
    }

    public void setPeilvqujianchuyoubian1( Double peilvqujianchuyoubian1 ) {
        this.peilvqujianchuyoubian1 = peilvqujianchuyoubian1;
    }

    public Double getPeilvqujianchuyoubian2() {
        return peilvqujianchuyoubian2;
    }

    public void setPeilvqujianchuyoubian2( Double peilvqujianchuyoubian2 ) {
        this.peilvqujianchuyoubian2 = peilvqujianchuyoubian2;
    }

    public Double getPeilvqujianchuyoubian3() {
        return peilvqujianchuyoubian3;
    }

    public void setPeilvqujianchuyoubian3( Double peilvqujianchuyoubian3 ) {
        this.peilvqujianchuyoubian3 = peilvqujianchuyoubian3;
    }

    public Double getPeilvqujianjiyoubian1() {
        return peilvqujianjiyoubian1;
    }

    public void setPeilvqujianjiyoubian1( Double peilvqujianjiyoubian1 ) {
        this.peilvqujianjiyoubian1 = peilvqujianjiyoubian1;
    }

    public Double getPeilvqujianjiyoubian2() {
        return peilvqujianjiyoubian2;
    }

    public void setPeilvqujianjiyoubian2( Double peilvqujianjiyoubian2 ) {
        this.peilvqujianjiyoubian2 = peilvqujianjiyoubian2;
    }

    public Double getPeilvqujianjiyoubian3() {
        return peilvqujianjiyoubian3;
    }

    public void setPeilvqujianjiyoubian3( Double peilvqujianjiyoubian3 ) {
        this.peilvqujianjiyoubian3 = peilvqujianjiyoubian3;
    }

    public Double getPeilvqujianbiyoubian1() {
        return peilvqujianbiyoubian1;
    }

    public void setPeilvqujianbiyoubian1( Double peilvqujianbiyoubian1 ) {
        this.peilvqujianbiyoubian1 = peilvqujianbiyoubian1;
    }

    public Double getPeilvqujianbiyoubian2() {
        return peilvqujianbiyoubian2;
    }

    public void setPeilvqujianbiyoubian2( Double peilvqujianbiyoubian2 ) {
        this.peilvqujianbiyoubian2 = peilvqujianbiyoubian2;
    }

    public Double getPeilvqujianbiyoubian3() {
        return peilvqujianbiyoubian3;
    }

    public void setPeilvqujianbiyoubian3( Double peilvqujianbiyoubian3 ) {
        this.peilvqujianbiyoubian3 = peilvqujianbiyoubian3;
    }

    public Double getFengxianzhishuyoubian1() {
        return fengxianzhishuyoubian1;
    }

    public void setFengxianzhishuyoubian1( Double fengxianzhishuyoubian1 ) {
        this.fengxianzhishuyoubian1 = fengxianzhishuyoubian1;
    }

    public Double getFengxianzhishuyoubian2() {
        return fengxianzhishuyoubian2;
    }

    public void setFengxianzhishuyoubian2( Double fengxianzhishuyoubian2 ) {
        this.fengxianzhishuyoubian2 = fengxianzhishuyoubian2;
    }

    public Double getFengxianzhishuyoubian3() {
        return fengxianzhishuyoubian3;
    }

    public void setFengxianzhishuyoubian3( Double fengxianzhishuyoubian3 ) {
        this.fengxianzhishuyoubian3 = fengxianzhishuyoubian3;
    }

    public Double getZonghefengxianzhishuyoubian1() {
        return zonghefengxianzhishuyoubian1;
    }

    public void setZonghefengxianzhishuyoubian1( Double zonghefengxianzhishuyoubian1 ) {
        this.zonghefengxianzhishuyoubian1 = zonghefengxianzhishuyoubian1;
    }

    public Double getZonghefengxianzhishuyoubian2() {
        return zonghefengxianzhishuyoubian2;
    }

    public void setZonghefengxianzhishuyoubian2( Double zonghefengxianzhishuyoubian2 ) {
        this.zonghefengxianzhishuyoubian2 = zonghefengxianzhishuyoubian2;
    }

    public Double getZonghefengxianzhishuyoubian3() {
        return zonghefengxianzhishuyoubian3;
    }

    public void setZonghefengxianzhishuyoubian3( Double zonghefengxianzhishuyoubian3 ) {
        this.zonghefengxianzhishuyoubian3 = zonghefengxianzhishuyoubian3;
    }

    public Double getPeifuzhishuyoubian1() {
        return peifuzhishuyoubian1;
    }

    public void setPeifuzhishuyoubian1( Double peifuzhishuyoubian1 ) {
        this.peifuzhishuyoubian1 = peifuzhishuyoubian1;
    }

    public Double getPeifuzhishuyoubian2() {
        return peifuzhishuyoubian2;
    }

    public void setPeifuzhishuyoubian2( Double peifuzhishuyoubian2 ) {
        this.peifuzhishuyoubian2 = peifuzhishuyoubian2;
    }

    public Double getPeifuzhishuyoubian3() {
        return peifuzhishuyoubian3;
    }

    public void setPeifuzhishuyoubian3( Double peifuzhishuyoubian3 ) {
        this.peifuzhishuyoubian3 = peifuzhishuyoubian3;
    }

    public Double getPeifuzhishutiaozhengyoubian1() {
        return peifuzhishutiaozhengyoubian1;
    }

    public void setPeifuzhishutiaozhengyoubian1( Double peifuzhishutiaozhengyoubian1 ) {
        this.peifuzhishutiaozhengyoubian1 = peifuzhishutiaozhengyoubian1;
    }

    public Double getPeifuzhishutiaozhengyoubian2() {
        return peifuzhishutiaozhengyoubian2;
    }

    public void setPeifuzhishutiaozhengyoubian2( Double peifuzhishutiaozhengyoubian2 ) {
        this.peifuzhishutiaozhengyoubian2 = peifuzhishutiaozhengyoubian2;
    }

    public Double getPeifuzhishutiaozhengyoubian3() {
        return peifuzhishutiaozhengyoubian3;
    }

    public void setPeifuzhishutiaozhengyoubian3( Double peifuzhishutiaozhengyoubian3 ) {
        this.peifuzhishutiaozhengyoubian3 = peifuzhishutiaozhengyoubian3;
    }

    public Double getPeifuzhishutiaozhengxia1youbian1() {
        return peifuzhishutiaozhengxia1youbian1;
    }

    public void setPeifuzhishutiaozhengxia1youbian1( Double peifuzhishutiaozhengxia1youbian1 ) {
        this.peifuzhishutiaozhengxia1youbian1 = peifuzhishutiaozhengxia1youbian1;
    }

    public Double getPeifuzhishutiaozhengxia1youbian2() {
        return peifuzhishutiaozhengxia1youbian2;
    }

    public void setPeifuzhishutiaozhengxia1youbian2( Double peifuzhishutiaozhengxia1youbian2 ) {
        this.peifuzhishutiaozhengxia1youbian2 = peifuzhishutiaozhengxia1youbian2;
    }

    public Double getPeifuzhishutiaozhengxia1youbian3() {
        return peifuzhishutiaozhengxia1youbian3;
    }

    public void setPeifuzhishutiaozhengxia1youbian3( Double peifuzhishutiaozhengxia1youbian3 ) {
        this.peifuzhishutiaozhengxia1youbian3 = peifuzhishutiaozhengxia1youbian3;
    }

    public Double getPeifuzhishutiaozhengxia2youbian1() {
        return peifuzhishutiaozhengxia2youbian1;
    }

    public void setPeifuzhishutiaozhengxia2youbian1( Double peifuzhishutiaozhengxia2youbian1 ) {
        this.peifuzhishutiaozhengxia2youbian1 = peifuzhishutiaozhengxia2youbian1;
    }

    public Double getPeifuzhishutiaozhengxia2youbian2() {
        return peifuzhishutiaozhengxia2youbian2;
    }

    public void setPeifuzhishutiaozhengxia2youbian2( Double peifuzhishutiaozhengxia2youbian2 ) {
        this.peifuzhishutiaozhengxia2youbian2 = peifuzhishutiaozhengxia2youbian2;
    }

    public Double getPeifuzhishutiaozhengxia2youbian3() {
        return peifuzhishutiaozhengxia2youbian3;
    }

    public void setPeifuzhishutiaozhengxia2youbian3( Double peifuzhishutiaozhengxia2youbian3 ) {
        this.peifuzhishutiaozhengxia2youbian3 = peifuzhishutiaozhengxia2youbian3;
    }

    public Double getPeifuzhishutiaozhengxia3youbian1() {
        return peifuzhishutiaozhengxia3youbian1;
    }

    public void setPeifuzhishutiaozhengxia3youbian1( Double peifuzhishutiaozhengxia3youbian1 ) {
        this.peifuzhishutiaozhengxia3youbian1 = peifuzhishutiaozhengxia3youbian1;
    }

    public Double getPeifuzhishutiaozhengxia3youbian2() {
        return peifuzhishutiaozhengxia3youbian2;
    }

    public void setPeifuzhishutiaozhengxia3youbian2( Double peifuzhishutiaozhengxia3youbian2 ) {
        this.peifuzhishutiaozhengxia3youbian2 = peifuzhishutiaozhengxia3youbian2;
    }

    public Double getPeifuzhishutiaozhengxia3youbian3() {
        return peifuzhishutiaozhengxia3youbian3;
    }

    public void setPeifuzhishutiaozhengxia3youbian3( Double peifuzhishutiaozhengxia3youbian3 ) {
        this.peifuzhishutiaozhengxia3youbian3 = peifuzhishutiaozhengxia3youbian3;
    }

    public Double getPeifuzhishutiaozhengxia4youbian1() {
        return peifuzhishutiaozhengxia4youbian1;
    }

    public void setPeifuzhishutiaozhengxia4youbian1( Double peifuzhishutiaozhengxia4youbian1 ) {
        this.peifuzhishutiaozhengxia4youbian1 = peifuzhishutiaozhengxia4youbian1;
    }

    public Double getPeifuzhishutiaozhengxia4youbian2() {
        return peifuzhishutiaozhengxia4youbian2;
    }

    public void setPeifuzhishutiaozhengxia4youbian2( Double peifuzhishutiaozhengxia4youbian2 ) {
        this.peifuzhishutiaozhengxia4youbian2 = peifuzhishutiaozhengxia4youbian2;
    }

    public Double getPeifuzhishutiaozhengxia4youbian3() {
        return peifuzhishutiaozhengxia4youbian3;
    }

    public void setPeifuzhishutiaozhengxia4youbian3( Double peifuzhishutiaozhengxia4youbian3 ) {
        this.peifuzhishutiaozhengxia4youbian3 = peifuzhishutiaozhengxia4youbian3;
    }

    public Double getPeifuzhishutiaozhengxia5youbian1() {
        return peifuzhishutiaozhengxia5youbian1;
    }

    public void setPeifuzhishutiaozhengxia5youbian1( Double peifuzhishutiaozhengxia5youbian1 ) {
        this.peifuzhishutiaozhengxia5youbian1 = peifuzhishutiaozhengxia5youbian1;
    }

    public Double getPeifuzhishutiaozhengxia5youbian2() {
        return peifuzhishutiaozhengxia5youbian2;
    }

    public void setPeifuzhishutiaozhengxia5youbian2( Double peifuzhishutiaozhengxia5youbian2 ) {
        this.peifuzhishutiaozhengxia5youbian2 = peifuzhishutiaozhengxia5youbian2;
    }

    public Double getPeifuzhishutiaozhengxia5youbian3() {
        return peifuzhishutiaozhengxia5youbian3;
    }

    public void setPeifuzhishutiaozhengxia5youbian3( Double peifuzhishutiaozhengxia5youbian3 ) {
        this.peifuzhishutiaozhengxia5youbian3 = peifuzhishutiaozhengxia5youbian3;
    }

    public Double getPeifuzhishutiaozhengxia6youbian1() {
        return peifuzhishutiaozhengxia6youbian1;
    }

    public void setPeifuzhishutiaozhengxia6youbian1( Double peifuzhishutiaozhengxia6youbian1 ) {
        this.peifuzhishutiaozhengxia6youbian1 = peifuzhishutiaozhengxia6youbian1;
    }

    public Double getPeifuzhishutiaozhengxia6youbian2() {
        return peifuzhishutiaozhengxia6youbian2;
    }

    public void setPeifuzhishutiaozhengxia6youbian2( Double peifuzhishutiaozhengxia6youbian2 ) {
        this.peifuzhishutiaozhengxia6youbian2 = peifuzhishutiaozhengxia6youbian2;
    }

    public Double getPeifuzhishutiaozhengxia6youbian3() {
        return peifuzhishutiaozhengxia6youbian3;
    }

    public void setPeifuzhishutiaozhengxia6youbian3( Double peifuzhishutiaozhengxia6youbian3 ) {
        this.peifuzhishutiaozhengxia6youbian3 = peifuzhishutiaozhengxia6youbian3;
    }

    public Double getQiwangfangchabiyoubian1() {
        return qiwangfangchabiyoubian1;
    }

    public void setQiwangfangchabiyoubian1( Double qiwangfangchabiyoubian1 ) {
        this.qiwangfangchabiyoubian1 = qiwangfangchabiyoubian1;
    }

    public Double getQiwangfangchabiyoubian2() {
        return qiwangfangchabiyoubian2;
    }

    public void setQiwangfangchabiyoubian2( Double qiwangfangchabiyoubian2 ) {
        this.qiwangfangchabiyoubian2 = qiwangfangchabiyoubian2;
    }

    public Double getQiwangfangchabiyoubian3() {
        return qiwangfangchabiyoubian3;
    }

    public void setQiwangfangchabiyoubian3( Double qiwangfangchabiyoubian3 ) {
        this.qiwangfangchabiyoubian3 = qiwangfangchabiyoubian3;
    }

    public Double getJunhengzhishuyoubian1() {
        return junhengzhishuyoubian1;
    }

    public void setJunhengzhishuyoubian1( Double junhengzhishuyoubian1 ) {
        this.junhengzhishuyoubian1 = junhengzhishuyoubian1;
    }

    public Double getJunhengzhishuyoubian2() {
        return junhengzhishuyoubian2;
    }

    public void setJunhengzhishuyoubian2( Double junhengzhishuyoubian2 ) {
        this.junhengzhishuyoubian2 = junhengzhishuyoubian2;
    }

    public Double getJunhengzhishuyoubian3() {
        return junhengzhishuyoubian3;
    }

    public void setJunhengzhishuyoubian3( Double junhengzhishuyoubian3 ) {
        this.junhengzhishuyoubian3 = junhengzhishuyoubian3;
    }
}
