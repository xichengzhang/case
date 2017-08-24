package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.TestEnum;
import com.netease.ssm.pojo.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public class MainUtil {

    private static final String BIG_IMG_HZ_URL = "http://dm.netease.com/cv-news/video_cover?docid={vid}&urls={url}";
    private static final String IR_ENCODING = "UTF-8";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        System.out.println(lastMonth(2));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -10);
        String starttime = sdf.format(c.getTime());
        System.out.println(starttime);

        String x = "qwewrrr";
        System.out.println(x.replace("rrr","vvv"));
        System.out.println(x.replace("eee","vvv"));

        String vvv = "http://enc.bn.netease.com/snapshot/videolib2/2017/8/3/A/ECRGJLV3A_3.jpg";
        System.out.println(vvv.substring(0, vvv.indexOf("_") + 1));

    }

    public void test(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }

    public static final String replaceTabToBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t");
            Matcher m = p.matcher(str);
            dest = m.replaceAll(" ");
        }
        return dest;
    }

    public static String lastMonth(int allMonth) {
        Date date = new Date();
        int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))-allMonth;
        int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));
        if(month <= 0){
            int yearFlag = (month*(-1))/12 + 1;
            int monthFlag = (month *(-1))%12;
            year -= yearFlag;
            month=monthFlag*(-1) +12;
        }
        else if(day>28){
            if(month==2){
                if(year%400==0||(year %4==0&&year%100!=0)){
                    day=29;
                }else day=28;
            }else if((month==4||month==6||month==9||month==11)&&day==31){
                day=30;
            }
        }
        String y = year+"";String m ="";String d ="";
        if(month<10) m = "0"+month;
        else m=month+"";
        if(day<10) d = "0"+day;
        else d = day+"";

        return y+"-"+m+"-"+d +" 00:00:00";
    }

}
