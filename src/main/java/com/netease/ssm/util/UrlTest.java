package com.netease.ssm.util;

import com.netease.ssm.pojo.VideoUrlType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjzhangxicheng on 2017/5/26.
 */
public class UrlTest {

    public static void main(String[] args) {

        /*List<VideoUrlType> list = new ArrayList<VideoUrlType>();



        VideoUrlType v1 = new VideoUrlType();
        v1.setId(1);
        v1.setUrlType("flv");
        v1.setProbability(new BigDecimal(50f/100f));

        VideoUrlType v2 = new VideoUrlType();
        v2.setId(2);
        v2.setUrlType("m3u8");
        v2.setProbability(new BigDecimal(0.10));

        VideoUrlType v3 = new VideoUrlType();
        v3.setId(3);
        v3.setUrlType("mp4");
        v3.setProbability(new BigDecimal(0.40));

        list.add(v1);
        list.add(v2);
        list.add(v3);

        int v1GetTimes = 0;
        int v2GetTimes = 0;
        int v3GetTimes = 0;

        int times = 100;
        for (int i = 0; i < times; i++) {
            int id = RandomUrlUtil.pay(list);
            switch (id) {
                case 1:
                    v1GetTimes++;
                    break;
                case 2:
                    v2GetTimes++;
                    break;
                case 3:
                    v3GetTimes++;
                    break;
            }
        }

        System.out.println("次数" + times);
        System.out.println(v1.getUrlType()+"  获取概率次数" + v1GetTimes);
        System.out.println(v2.getUrlType()+" 获取概率次数" + v2GetTimes);
        System.out.println(v3.getUrlType()+"  获取概率次数" + v3GetTimes);

        System.out.println(5d/100d);System.out.println(5f/100f);System.out.println(5/100);

        System.out.println(test());*/

        System.out.println("http://flv.bn.netease.com/videolib3/1706/05/hKuAM0124/SD/hKuAM0124.flv");
        System.out.println(getMp4Url("http://flv.bn.netease.com/videolib3/1706/05/hKuAM0124/SD/hKuAM0124.flv"));
    }

    private final static String wangsuCDN = "http://flv2.bn.netease.com";


    public static String getMp4Url(String repovideourl){
        String mp4_url="";
        if (repovideourl.lastIndexOf(".flv") > 0) {
            mp4_url = repovideourl.replace(".flv", "-mobile.mp4");
            mp4_url = mp4_url.replaceAll("http://[^/]*/", wangsuCDN + "/");
        }
        return mp4_url;
    }


    public static int test() {
        try{
            System.out.println("test !!!");
            //int a =  1/0;
            System.out.println("2222");
            return 1;
        }catch (Exception e){
            System.out.println("catch ");
            return 3;
        }
    }
}
