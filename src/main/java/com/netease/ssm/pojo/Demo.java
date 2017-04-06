package com.netease.ssm.pojo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2016/12/22.
 */
public class Demo {
    private String vid;
    private String title;
    private String[] dome;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDome() {
        return dome;
    }

    public void setDome(String[] dome) {
        this.dome = dome;
    }


    public static String formatLmuser(String lmuser){
        if(lmuser == null || lmuser.equals("")){
            return null;
        }
        String[] lmuserArr = lmuser.split("@");
        if(lmuserArr.length < 2){
            return lmuser;
        }
        if(lmuserArr[1].equals("163.com")){
            return lmuserArr[0];
        }else{
            return lmuser;
        }
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static void main(String[] args) {

        /*String xx = "分享到 \uE639 \uE638 \uE63A  \uE6BD\t3  0 \t收藏\n" +
                "管理应具有外行的心态\n" +
                "2017/02/10\t\uE641举报\n" +
                "管理 者应具 有外行 的心态，缺乏直接经 验 实际上 可以 作为 一 个优势，因为较少的历史经验不 会使他们的愿景发生混乱，他们可能以新的方式、新的视角看问题。这个全新的视角可能使他们产生很多新的理念和创新，这无疑也将导致新员工质疑现行惯例，而这些质疑可能导致产生新的方法、理念和创新。";

        //System.out.println(xx);
        System.out.println(replaceBlank(xx));*/
        String ss = "123";
        System.out.println(ss.indexOf('@'));
    }

}
