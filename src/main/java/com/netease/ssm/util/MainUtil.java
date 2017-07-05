package com.netease.ssm.util;

import com.netease.ssm.pojo.TestEnum;

import java.util.Date;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public class MainUtil {

    public static void main(String[] args) {


    }

    public void test(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }
}
