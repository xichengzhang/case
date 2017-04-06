package com.netease.ssm.pojo;


import java.util.Date;

/**
 * Created by bjzhangxicheng on 2016/12/8.
 */
public class NginxLog implements Cloneable{
	/**
	 * 其他的一些日志信息
	 */
	private String path = "";
	private String host = "";
	private String timeStamp = "";
	// device node
	private String deviceId = "";  //设备号，用来识别设备用的（一个设备对应一个，相当于UUID）

	private String model = "";  //用来标识设备是iPhone，iPad，iPod或者模拟器等

	private String appVersion = "";  //用来标识设备上安装的发送该请求的app的版本

	private String bundleIdentifier = "";  //用来标识用户设备上是哪个app发送的请求（目前只有网易萝卜，但是不同渠道的萝卜app的bundleIdentifier一般不一样，所以也可以使用此字段标识app来自哪个渠道）

	private String platform = "";  //用来标识用户设备上的iOS版本

	// authorization
	private String authorization = "";  // 用来标识本地用户

	// info node
	private String currentPlaybackTime = "";  //一组表示现阶段打点时视频播放时间的数组

	private String loadState;  //：一组表示现阶段打点时视频播放状态的数组

	private String videoUrl = "";  //现阶段视频对应的url

	private Integer count;  //现阶段视频播放的时候具体的打点数

	private Date time;  //一组表示现阶段打点时本地时间的数组

	private String sdkVersion = "";  //当前杭研播放器SDK的版本号
	private String finishReason = "";

	// video node
	private String height;  //现阶段视频的高度

	private String codectype = "";  //视频编码器类型

	private String fps;  //现阶段打点时视频的播放帧率

	private String videoBitrate;  //现阶段打点时视频的播放码率

	private String width;  //现阶段视频的宽度

	// netInfo node
	//private String networkCode = "";
	private String MNC = "";  //移动设备网络代码（英语：Mobile Network Code，MNC）

	private String ios = "";  //用户所使用移动网络的供应商的ISO国家代码（参见标准 ISO 3166-1）

	// private String country = "";
	private String MCC = "";  //移动设备国家代码（Mobile Country Code，MCC）

	private String type = "";  //用户是使用wifi或者cellular（移动网络）

	private String name = "";  //中国联通 中国移动

	// requestTime
	private Date requestTime;  //是指本地向服务器发送统计数据的开始时间

	// queue node
	private String lastPts;

	private String nbPackets;

	private String firstPts;

	// audio node
	private String audioBitrate;  //当前音频的码率

	private String sampleRate;  //当前音频的采样频率

	private String numOfChannels;  //当前音频的采样频道数

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getBundleIdentifier() {
		return bundleIdentifier;
	}

	public void setBundleIdentifier(String bundleIdentifier) {
		this.bundleIdentifier = bundleIdentifier;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getCurrentPlaybackTime() {
		return currentPlaybackTime;
	}

	public void setCurrentPlaybackTime(String currentPlaybackTime) {
		this.currentPlaybackTime = currentPlaybackTime;
	}

	public String getLoadState() {
		return loadState;
	}

	public void setLoadState(String loadState) {
		this.loadState = loadState;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFps() {
		return fps;
	}

	public void setFps(String fps) {
		this.fps = fps;
	}

	public String getVideoBitrate() {
		return videoBitrate;
	}

	public void setVideoBitrate(String videoBitrate) {
		this.videoBitrate = videoBitrate;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLastPts() {
		return lastPts;
	}

	public void setLastPts(String lastPts) {
		this.lastPts = lastPts;
	}

	public String getNbPackets() {
		return nbPackets;
	}

	public void setNbPackets(String nbPackets) {
		this.nbPackets = nbPackets;
	}

	public String getFirstPts() {
		return firstPts;
	}

	public void setFirstPts(String firstPts) {
		this.firstPts = firstPts;
	}

	public String getAudioBitrate() {
		return audioBitrate;
	}

	public void setAudioBitrate(String audioBitrate) {
		this.audioBitrate = audioBitrate;
	}

	public String getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(String sampleRate) {
		this.sampleRate = sampleRate;
	}

	public String getNumOfChannels() {
		return numOfChannels;
	}

	public void setNumOfChannels(String numOfChannels) {
		this.numOfChannels = numOfChannels;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}


	public String getCodectype() {
		return codectype;
	}

	public void setCodectype(String codectype) {
		this.codectype = codectype;
	}

	public String getMNC() {
		return MNC;
	}

	public void setMNC(String MNC) {
		this.MNC = MNC;
	}

	public String getIos() {
		return ios;
	}

	public void setIos(String ios) {
		this.ios = ios;
	}

	public String getMCC() {
		return MCC;
	}

	public void setMCC(String MCC) {
		this.MCC = MCC;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public Object clone() {
		NginxLog o = null;
		try {
			o = (NginxLog) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}


	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFinishReason() {
		return finishReason;
	}

	public void setFinishReason(String finishReason) {
		this.finishReason = finishReason;
	}

	@Override
	public String toString() {
		return "NginxLog [path=" + path + ", host=" + host + ", timeStamp="
				+ timeStamp + ", deviceId=" + deviceId + ", model=" + model
				+ ", appVersion=" + appVersion + ", bundleIdentifier="
				+ bundleIdentifier + ", platform=" + platform
				+ ", authorization=" + authorization + ", currentPlaybackTime="
				+ currentPlaybackTime + ", loadState=" + loadState
				+ ", videoUrl=" + videoUrl + ", count=" + count + ", time="
				+ time + ", sdkVersion=" + sdkVersion + ", finishReason="
				+ finishReason + ", height=" + height + ", codectype="
				+ codectype + ", fps=" + fps + ", videoBitrate=" + videoBitrate
				+ ", width=" + width + ", MNC=" + MNC + ", ios=" + ios
				+ ", MCC=" + MCC + ", type=" + type + ", name=" + name
				+ ", requestTime=" + requestTime + ", lastPts=" + lastPts
				+ ", nbPackets=" + nbPackets + ", firstPts=" + firstPts
				+ ", audioBitrate=" + audioBitrate + ", sampleRate="
				+ sampleRate + ", numOfChannels=" + numOfChannels + "]";
	}



}
