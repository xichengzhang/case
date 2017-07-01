package com.netease.ssm.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bjzhangxicheng on 2017/6/15.
 */
public class StaticTestUtil {

    private static int x = 0;

    public static int test(){
        for(int i=0; i<5; i++){
            x = i;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(getShortDateOneWeekAgo(new Date()));
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 获得一周之前的短日期
     * @param date
     * @return
     */
    public static String getShortDateOneWeekAgo(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, -5);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }
}
