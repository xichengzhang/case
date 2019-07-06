package com.netease.ssm.pojo;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by bjzhangxicheng on 2017/8/28.
 */
public class CatchBean {

    private String catchId; //抓取id

    private String url ;  //mp4地址

    private String title;  //标题

    private String description;  //描述

    private Long updateTime;  //抓取最后时间

    private String coverPic;  //封面图

    private int flag;  //B站和西瓜的区分变量 1:B站 2:西瓜

    private String sourceUrl; //视频源页面地址 https://www.bilibili.com/video/av13698087/

    private List<String> comments; //跟帖

    private boolean hideAd;

    public CatchBean(){

    }

    public CatchBean(String catchId,String url,String title,String description,Long updateTime,String coverPic,int flag,List<String> comments,String sourceUrl){
        this.catchId = catchId;
        this.url = url;
        this.title = title;
        this.description = description;
        this.updateTime = updateTime;
        this.coverPic = coverPic;
        this.flag = flag;
        this.comments = comments;
        this.sourceUrl = sourceUrl;
    }

    public String getCatchId() {
        if(this.catchId == null){
            return "";
        }
        return catchId;
    }

    public void setCatchId(String catchId) {
        this.catchId = catchId;
    }

    public String getUrl() {
        if(this.url == null){
            return "";
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        if(this.title == null){
            return "";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if(this.description == null){
            return "";
        }
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public boolean isHideAd() {
        return hideAd;
    }

    public void setHideAd(boolean hideAd) {
        this.hideAd = hideAd;
    }

    @Override
    public String toString() {
        return "catchId:"+catchId+",url:"+url+",title:"+title+",description:"+description+",updateTime:"
                +updateTime+",coverPic:"+coverPic+",flag:"+flag+",sourceUrl:"+sourceUrl+",comments:"+ JSON.toJSONString(comments)
                + "，hasAd：" + hideAd ;
    }

    /*@Override
    public String toString() {
        return "catchId:"+catchId+",url:"+url+",sourceUrl:"+sourceUrl+",comments:"+ JSON.toJSONString(comments);
    }*/
}
