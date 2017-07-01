package com.netease.ssm.util;

import com.netease.ssm.pojo.TestEnum;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public class MainUtil {

    public static void main(String[] args) {

        for(TestEnum ts : TestEnum.values()){
            System.out.println(ts.getKaiYanId() + " " + ts.getCategoryId() + " " + ts.getCutsid());
        }
    }
}
