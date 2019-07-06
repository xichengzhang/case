package com.netease.ssm.pojo;

import org.apache.commons.lang.StringEscapeUtils;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Video implements Serializable {

	public static final Integer IS_SHOW_HIDE = Integer.valueOf(0);

	public static final Integer INFORMATION_YES = Integer.valueOf(1);

	public static final Integer INFORMATION_NO = Integer.valueOf(0);

	/**
	 * 是否mapping
	 */
	public static final String MAPPING_NO = "0";

	/**
	 * 
	 */
	private static final long serialVersionUID = -3195682724689282673L;


	private String vid = null;

	private String topicid = null;

	private String sid = null;

	private String stree = null;

	private String title = null;

	private String subtitle = null;

	private String description = null;
	private String tags = null;
	private String imgid = null;
	private String imgpath = null;
	private String rimgurl = null;
	private Long playlength = null;
	private Integer weight = null;
	private Date ptime = null;
	private Date distime = null;
	private Date ltime = null;
	private String username = null;
	private String lmuser = null;
	private Long hits = null;
	private Long total_score = null;
	private Long score_count;
	private Integer avg_score = null;
	private String rvideoid = null;
	private String rvideourl = null;
	private Integer encstate = null;
	private String sysmsg = null;
	private Integer isshow = null;
	private String rawfileid = null;
	private Integer copyrighted = null;
	private String pageurl = null;
	private String source = null;
	private String playcode = null;
	private String repovideourl = null;
	private Integer is_third_party = null;
	private String templet = null;
	private String commentid = null;
	private String commentboard = null;
	private String slink = null;
	private boolean isVideo = true;
	private String mapping = "0";
	private Integer playersize = null;
	private Integer continousplay = null;
	private String relatedvideo = null;
	private String guest = null;
	private Integer transcode_status = null;
	private Integer video_mult_versions = null;
	private Integer add_transcode_status = null;
	// 去除视频水印
	private String delogo = "0";
	// 0是横屏 1是竖屏 2是方屏   20171031
	private Integer isdanger = null;
	private String entry;//热词
	private String hot;//热度
	
	
	
	
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}



	/** 是否为全景视频 */
	private Integer vrvideo = null;
	
	/** 视频的二级分类 */
	private Integer categoryid = null;
	
	/** 大图的路径地址 */
	private String bigimgpath = null;
	
	/**  是否为网易号视频  ：0否，1是  （默认值为0）*/ 
	private Integer pgcvideo = null;
	
	/** 视频的截图数 */
	private Integer picnum = null;
	
	/** 视频的截图路径 ,复用：表示是不是素材，0素材，1短视频*/
	private String picpath = null;
	
	/** 重复视频id */
	private String is_repeat = null;
	
	private Integer recheck;  //  1：重新审核  默认0
	
	private String interestDisplay;
	
	private String interestDisplayPOI;
	
	
	public Integer getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getIs_repeat() {
		return is_repeat;
	}

	public void setIs_repeat(String is_repeat) {
		this.is_repeat = is_repeat;
	}

	public Integer getPgcvideo() {
		return pgcvideo;
	}

	public void setPgcvideo(Integer pgcvideo) {
		this.pgcvideo = pgcvideo;
	}

	public Integer getVrvideo() {
		return vrvideo;
	}

	public void setVrvideo(Integer vrvideo) {
		this.vrvideo = vrvideo;
	}



	/**
	 * 仅仅用作索引时的标记位1表示是资讯,0表是否
	 */
	private Integer information;

	public Integer getInformation() {
		return this.information;
	}

	public void setInformation(Integer information) {
		this.information = information;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}



	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return the score_count
	 */
	public Long getScore_count() {
		return score_count;
	}

	/**
	 * @param score_count the score_count to set
	 */
	public void setScore_count(Long score_count) {
		this.score_count = score_count;
	}

	/**
	 * @return the slink
	 */
	public String getSlink() {
		return slink;
	}

	/**
	 * @param slink the slink to set
	 */
	public void setSlink(String slink) {
		this.slink = slink;
	}

	public String getCommentid() {
		return commentid;
	}

	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}

	public String getCommentboard() {
		return commentboard;
	}

	public void setCommentboard(String commentboard) {
		this.commentboard = commentboard;
	}

	private int pagefile = 0;

	/**
	 * @return the pagefile
	 */
	public int getPagefile() {
		return pagefile;
	}

	/**
	 * @param pagefile the pagefile to set
	 */
	public void setPagefile(int pagefile) {
		this.pagefile = pagefile;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getStree() {
		return stree;
	}

	public void setStree(String stree) {
		this.stree = stree;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = StringEscapeUtils.unescapeHtml(title);
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getImgid() {
		return imgid;
	}

	public void setImgid(String imgid) {
		this.imgid = imgid;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getRimgurl() {
		return rimgurl;
	}

	public void setRimgurl(String rimgurl) {
		this.rimgurl = rimgurl;
	}

	public Long getPlaylength() {
		return playlength;
	}

	public void setPlaylength(Long playlength) {
		this.playlength = playlength;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Date getDistime() {
		return distime;
	}

	public void setDistime(Date distime) {
		this.distime = distime;
	}

	public Date getLtime() {
		return ltime;
	}

	public void setLtime(Date ltime) {
		this.ltime = ltime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLmuser() {
		return lmuser;
	}

	public void setLmuser(String lmuser) {
		this.lmuser = lmuser;
	}

	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}

	public Long getTotal_score() {
		return total_score;
	}

	public void setTotal_score(Long total_score) {
		this.total_score = total_score;
	}

	public Integer getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(Integer avg_score) {
		this.avg_score = avg_score;
	}

	public String getRvideoid() {
		return rvideoid;
	}

	public void setRvideoid(String rvideoid) {
		this.rvideoid = rvideoid;
	}

	public String getRvideourl() {
		return rvideourl;
	}

	public void setRvideourl(String rvideourl) {
		this.rvideourl = rvideourl;
	}

	public Integer getEncstate() {
		return encstate;
	}

	public void setEncstate(Integer encstate) {
		this.encstate = encstate;
	}

	public String getSysmsg() {
		return sysmsg;
	}

	public void setSysmsg(String sysmsg) {
		this.sysmsg = sysmsg;
	}

	public Integer getIsshow() {
		return isshow;
	}

	public void setIsshow(Integer isshow) {
		this.isshow = isshow;
	}

	public String getRawfileid() {
		return rawfileid;
	}

	public void setRawfileid(String rawfileid) {
		this.rawfileid = rawfileid;
	}

	public Integer getCopyrighted() {
		return copyrighted;
	}

	public void setCopyrighted(Integer copyrighted) {
		this.copyrighted = copyrighted;
	}

	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPlaycode() {
		return playcode;
	}

	public void setPlaycode(String playcode) {
		this.playcode = playcode;
	}

	public String getRepovideourl() {
		return repovideourl;
	}

	public void setRepovideourl(String repovideourl) {
		this.repovideourl = repovideourl;
	}

	public Integer getIs_third_party() {
		return is_third_party;
	}

	public void setIs_third_party(Integer is_third_party) {
		this.is_third_party = is_third_party;
	}

	public String getTemplet() {
		return templet;
	}

	public void setTemplet(String templet) {
		this.templet = templet;
	}

	public String getId() {
		return vid;
	}

	public void setId(String id) {
		this.vid = id;
	}

	public boolean isVideo() {
		return isVideo;
	}

	public void setVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}

	public Integer getContinousplay() {
		return continousplay;
	}

	public void setContinousplay(Integer continousplay) {
		this.continousplay = continousplay;
	}

	public Integer getPlayersize() {
		return playersize;
	}

	public void setPlayersize(Integer playersize) {
		this.playersize = playersize;
	}

	public String getRelatedvideo() {
		return relatedvideo;
	}

	public void setRelatedvideo(String relatedvideo) {
		this.relatedvideo = relatedvideo;
	}

	public Integer getTranscode_status() {
		return transcode_status;
	}

	public void setTranscode_status(Integer transcode_status) {
		this.transcode_status = transcode_status;
	}

	public Integer getVideo_mult_versions() {
		return video_mult_versions;
	}

	public void setVideo_mult_versions(Integer video_mult_versions) {
		this.video_mult_versions = video_mult_versions;
	}

	public Integer getAdd_transcode_status() {
		return add_transcode_status;
	}

	public void setAdd_transcode_status(Integer add_transcode_status) {
		this.add_transcode_status = add_transcode_status;
	}

	public String getBigimgpath() {
		return bigimgpath;
	}

	public void setBigimgpath(String bigimgpath) {
		this.bigimgpath = bigimgpath;
	}

	public String getDelogo() {
		return delogo;
	}

	public void setDelogo(String delogo) {
		this.delogo = delogo;
	}

	public Integer getIsdanger() {
		return isdanger;
	}

	public void setIsdanger(Integer isdanger) {
		this.isdanger = isdanger;
	}

	private Integer check_user; //0:所有, 1:系统处理, 2:审核员处理

	private Integer check_status;  //'0:未审核, 1:已通过, 2:已删除

	public Integer getCheck_user() {
		return check_user;
	}

	public void setCheck_user(Integer check_user) {
		this.check_user = check_user;
	}

	public Integer getCheck_status() {
		return check_status;
	}

	public void setCheck_status(Integer check_status) {
		this.check_status = check_status;
	}

	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Integer getRecheck() {
		return recheck;
	}

	public void setRecheck(Integer recheck) {
		this.recheck = recheck;
	}

	public String getInterestDisplay() {
		return interestDisplay;
	}

	public void setInterestDisplay(String interestDisplay) {
		this.interestDisplay = interestDisplay;
	}

	public String getInterestDisplayPOI() {
		return interestDisplayPOI;
	}

	public void setInterestDisplayPOI(String interestDisplayPOI) {
		this.interestDisplayPOI = interestDisplayPOI;
	}
	
	private String aiDescription = "";  //机器审核视频问题描述

	public String getAiDescription() {
		return aiDescription;
	}

	public void setAiDescription(String aiDescription) {
		this.aiDescription = aiDescription;
	}
}
