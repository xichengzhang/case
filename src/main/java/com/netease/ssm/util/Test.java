package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SAMSUNG on 2017/11/12.
 */
public class Test {

    static Pattern p = Pattern.compile("vid=([0-9a-zA-Z]{11})");
    public static void main(String[] args){
        String s1 = "<iframe class=\"video_iframe\" frameborder=\"0\" scrolling=\"no\" allowfullscreen data-vidtype=\"2\" data-ratio=\"1.7666666666666666\" data-w=\"848\" data-src=\"c=\"https://v.qq.com/iframe/preview.html?vid=o0730l9un72\" st\" style=\"width:500px;height:375px;\" height=\"280\" width=\"500\"></iframe>";
        String s2 = "<iframe class=\"video_iframe\" data-vidtype=\"2\" data-cover=\"http%3A%2F%2F%2Fshp.qpic.cn%2Fq%2Fqqvideo_ori%2F0%2Fq0780cbv2gv_496_280%2F0\" allowfullscreen frameborder=\"0\" data-ratio=\"1.7777777777777777\" data-w=\"864\" src=\"c=\"https://v.qq.com/iframe/preview.html?width=500&amp;height=375&amp;auto=0&amp;vid=q0780cbv2gv\"></\"></iframe>";
        /*Matcher m1 = p.matcher(s1);
        if(m1.find()) {
            System.out.println(m1.group(1));
        }*/
        /*Matcher m2 = p.matcher(s2);
        if(m2.find()) {
            System.out.println(m2.group(1));
        }*/

//        System.out.println(JSON.toJSONString(ImmutableBiMap.of("firstFrameImg", "","hideTitle","")));

        String x = "[{\"vid\":\"VITKE5LHA\",\"comments\":[\"第一次吃到这种奶冰粽子\",\"这不就是冰皮月饼么\"],\"title\":\"在天津街头第一次吃到奶冰粽子：8元一个，感觉有点被坑了\"}]";
        JSONArray nosGetArray = JSONArray.parseArray(x);
        JSONObject nosObject = nosGetArray.getJSONObject(0);
        JSONArray commentsArray = nosObject.getJSONArray("comments");
        int num = 0;
        if(commentsArray != null && commentsArray.size() != 0){
            num = commentsArray.size();
        }
        String f = commentsArray.getString(0);
    }

 /*   public String getTopicUrl(String topicid, String stree) {
        if (StringUtil.isFine(stree)) {
            stree = stree.toUpperCase();
            if (stree.length() % 9 == 0) {
                int plus = stree.length() / 9;
                for (int j = 0; j < plus; j++) {
                    String sid = stree.substring(j * 9, j * 9 + 9);
                    if (StringUtil.isFine((String) this.topicUrlMap.get(sid))) {
                        return (String) this.topicUrlMap.get(sid);
                    }
                }
            }
        }
        String topicUrl = (String) this.topicUrlMap.get(topicid);
        if (topicUrl == null)
            return "";
        return topicUrl;
    }*/


    private static String tencentVid (String blockSrc){

        Pattern p = Pattern.compile("vid=([0-9a-zA-Z]{11})");
        Matcher m1 = p.matcher(blockSrc);
        if(m1.find()) {
            return m1.group(1);
        }
        return null;
    }

}
