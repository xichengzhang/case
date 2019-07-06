package com.netease.ssm.controller;

/**
 * Created by bjzhangxicheng on 2017/11/29.
 */
public class La {

    public static void main(String[] args) {
         Po po = new Po();
        po.setPoi("wwww");
        System.out.println(po.getPoi());
        new Te(po);
        po.setPoi("eeeee");
    }

    static class Te implements Runnable{

        private Po po;

        public Te(Po po){
            this.po = po;
        }

        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Te:"+po.getPoi());
        }
    }

    static class Po {
        private String Poi;

        public String getPoi() {
            return Poi;
        }

        public void setPoi(String poi) {
            Poi = poi;
        }
    }
}
