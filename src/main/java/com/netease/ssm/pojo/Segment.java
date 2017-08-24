package com.netease.ssm.pojo;

/**
 * Created by bjzhangxicheng on 2017/8/14.
 */
public class Segment {

    private String vid;

    private String url ;

    private String title;

    private String description;

    private Long updateTime;

    public Segment(){

    }

    public Segment(String vid,String url,String title,String description,Long updateTime){
        this.vid = vid;
        this.url = url;
        this.title = title;
        this.description = description;
        this.updateTime = updateTime;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "vid:"+vid+",updateTime:"+updateTime+",url:"+url+",title:"+title+",description:"+description;
    }
}
