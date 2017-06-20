package com.netease.ssm.pojo;

/**
 * Created by bjzhangxicheng on 2017/6/13.
 */
public class Youtube {

    private String vid;

    private String url ;

    private String title;

    private String description;

    private String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "URL:"+getUrl()+" , IMAGE:"+getImagePath() + " , TITLE:"+getTitle() + " , DESC:"+getDescription();
    }
}
