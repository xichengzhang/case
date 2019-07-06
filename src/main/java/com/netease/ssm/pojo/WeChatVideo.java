package com.netease.ssm.pojo;

import java.util.Date;

/**
 * Created by bjzhangxicheng on 2017/9/22.
 */
public class WeChatVideo {

    private Integer id;  //主键

    private String store_id;  //文章id

    private String title;  //文章标题

    private String url;  //文章路径

    private String block_id;  //片段id

    private String mp4url;  //视频MP4播放地址

    private Integer download;  //是否下载 0未下载 1处理中 2已下载

    private Date create_time;   //创建时间

    private String uczzPicUrl;  // m.uczzd.cn获取的封面图

    private String uczzMp4Url;  //  m.uczzd.cn获取的mp4地址

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getMp4url() {
        return mp4url;
    }

    public void setMp4url(String mp4url) {
        this.mp4url = mp4url;
    }

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUczzPicUrl() {
        return uczzPicUrl;
    }

    public void setUczzPicUrl(String uczzPicUrl) {
        this.uczzPicUrl = uczzPicUrl;
    }

    public String getUczzMp4Url() {
        return uczzMp4Url;
    }

    public void setUczzMp4Url(String uczzMp4Url) {
        this.uczzMp4Url = uczzMp4Url;
    }
}
