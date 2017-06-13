package com.netease.ssm.pojo;

import java.math.BigDecimal;

/**
 * Created by bjzhangxicheng on 2017/5/26.
 */
public class VideoUrlType {

    private Integer id;

    private String urlType;

    private BigDecimal probability;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public BigDecimal getProbability() {
        return probability;
    }

    public void setProbability(BigDecimal probability) {
        this.probability = probability;
    }
}
