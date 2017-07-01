package com.netease.ssm.pojo;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public enum TestEnum {

    SHENGHUO(36,119,"VC7T21ILQ","VBF8F3SGL",0),
    YUNDONG(18,405,"VBUAGJ6EP","VBF8F3SGL",0),
    CHUANGYI(2,371,"VBNI9MVCU","VBF8F3SGL",0),
    GUANGGAO(14,423,"VBP3D2ESJ","VBK30GJUF",0),
    YINYUE(20,367,"VBME0EGOB","VBMDK3R02",0),
    LVXING(6,88,"VBNIA0T9K","VBF8F3SGL",0),
    SHISHANG(24,382,"VBKO1U923","VBF8F3SGL",0),
    JILU(22,42,"VBP3DDP03","VBK30GJUF",0),
    KAIWEI(4,90,"VBUAGM2AM","VBF8F3SGL",0),
    YOUXI(30,128,"VBI045HD1","VBF8F1PSA",0),
    MENCHOU(26,362,"VBLSE3PJN","VAP4BFR16",0),
    DONGMAN(10,134,"VBH9EOB9V","VBF8F1PSA",0),
    KEPU(32,477,"VC7T1R3JV","VBF8F3SGL",0),
    JUQING(12,423,"VC7NSN0T7","VBK30GJUF",0),
    GAOXIAO(28,45,"VC7T2VFGB","VAP4BFE3U",0),
    YUGAO(8,423,"VBUSRDPM6","VBK30GJUF",2),
    ZONGYI(38,39,"VBUSRFB79","VBK30GJUF",0),
    JIJIN(34,423,"VCH554KKH","VBK30GJUF",0);

    private int kaiYanId;  //开眼栏目id

    private int categoryId;  //二级分类id

    private String cutsid;  //对应栏目id

    private String mapsid;  //对应订阅号id

    private int isrecommend; //对应时效 0长效 1中效 2短效

    private TestEnum(int kaiYanId,int categoryId,String cutsid,String mapsid,int isrecommend){
        this.kaiYanId = kaiYanId;
        this.categoryId = categoryId;
        this.cutsid = cutsid;
        this.mapsid = mapsid;
        this.isrecommend = isrecommend;
    }

    public int getKaiYanId() {
        return kaiYanId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCutsid() {
        return cutsid;
    }

    public String getMapsid() {
        return mapsid;
    }

    public int getIsrecommend() {
        return isrecommend;
    }
}
