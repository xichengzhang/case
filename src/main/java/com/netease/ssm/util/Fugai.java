package com.netease.ssm.util;

/**
 * Created by bjzhangxicheng on 2018/5/3.
 */
public class Fugai {
    public static void main(String[] args) {
        String mp4_url="";
        String repovideourl = "http://flv.bn.netease.com/videolib3/1805/03/xDVNe7656/SD/xDVNe7656.flv";
        mp4_url = repovideourl.replace(".flv", "-mobile.mp4");
        mp4_url = mp4_url.replaceAll("http://[^/]*/", "http://flv2.bn.netease.com" + "/");
        System.out.println(mp4_url);
        System.out.println(HttpUtil.isConnectFg(mp4_url));

        String repovideourl2 = "http://flv.bn.netease.com/videolib3/1805/03/xDVNe7656/SD/xDVNe7656.flv";
        String m3u8_url="";
        if (repovideourl2.lastIndexOf(".flv") > 0) {
            m3u8_url = repovideourl2.replace("xDVNe7656"+".flv", "movie_index.m3u8");
        }
        System.out.println(m3u8_url);
        System.out.println(HttpUtil.isConnectFg(m3u8_url));

        System.out.println((1&5));

        System.out.println("D/C/1000_VQH0S1TDC.xml".substring(4,"D/C/1000_VQH0S1TDC.xml".length()));

        try{
            System.out.println("11112323232");
            return;
        }catch (Exception e){

        }finally {
            System.out.println("$$$$$$");
        }
    }
}
