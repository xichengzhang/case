package com.netease.ssm.pojo;

/**
 * Created by bjzhangxicheng on 2017/2/21.
 */
public class Project {

    private Integer id;
    private String name;
    private String technology;//所用技术
    private String remarks;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public static void main(String[] args) {
        String[] ss = null;
        for(String s : ss){
            System.out.println(s);
        }
    }
}
