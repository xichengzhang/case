package com.netease.ssm.pojo;

import lombok.Getter;

/**
 * Created by bjzhangxicheng on 2019/4/24.
 */
@Getter
public enum VideoType {

    OPEN_COURSE(11, "公开课app", 84),
    SHADIAO(12, "沙雕小程序", 85),
    CHEESE(13, "cheese小程序", 86),
    PISTACHIOS(14, "开心果小程序", 87);

    private int type;
    private String name;
    private int dyType;

    VideoType(int type, String name, int dyType) {
        this.type = type;
        this.name = name;
        this.dyType = dyType;
    }

    public static boolean exist(int type) {
        for (VideoType v : VideoType.values()) {
            if (v.type == type) {
                return true;
            }
        }
        return false;
    }

    public static int getDyType(int type){
        for (VideoType v : VideoType.values()) {
            if (v.type == type) {
                return v.dyType;
            }
        }
        return 9;
    }

    public static void main(String[] args) {
        System.out.println(getDyType(14));
    }

}
