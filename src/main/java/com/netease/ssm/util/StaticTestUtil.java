package com.netease.ssm.util;

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
}
