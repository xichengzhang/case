package com.netease.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.User;
import com.netease.ssm.util.JackSonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2017/4/12.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    @RequestMapping("/api/video_push.do")
    public void video_push(HttpServletRequest request,
                           HttpServletResponse response,
                           @RequestParam(value = "push", required = true) String push) {
        try{
            JSONObject pushOject = JSON.parseObject(push);


        }
        catch (Exception e){

        }
    }


    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setId(123);
        user.setUsername("zhangxicheng");
        user.setAddress("haha");
        user.setBirthday(new Date());
        user.setAge(1.67);
        System.out.println(123);
        System.out.println(234);

        Map<String, Object> params = JackSonUtil.objToStrMap(user);

        System.out.println(JSON.toJSONString(params));

        Map<String, Object> params1 = JackSonUtil.objectToMap(user);

        System.out.println(JSON.toJSONString(params1));


        Map<String, Object> params11 = JSONObject.parseObject(JSON.toJSONString(user),Map.class);
        System.out.println(JSON.toJSONString(params11));

        Map<String, Object> map = new HashMap<>();
        map.put("rvideoid", "zxcx");
        map.put("videoAccountClassify", null);
        String data = JSON.toJSONString(map);
        System.out.println(data);

    }


    public static String sentGetForWeixin(String url) throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient httpclient = httpClientBuilder.build();
        HttpHost proxy = new HttpHost("220.181.29.165", 8268);
        //RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        String result = "";
        try {
            // 创建get连接
            HttpGet httpget = new HttpGet(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(200000).setConnectTimeout(1000 * 10).setProxy(proxy)
                    .build();// 设置请求和传输超时时间
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept-Charset", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2");
            // 发送请求
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    entity.getContent(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            // 终止请求
            httpget.abort();
        } catch (ClientProtocolException e) {
            System.out.println("ClientProtocolException ==" + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException ==" + e);
            e.printStackTrace();
        } finally {
            // 终止请求
            httpclient.close();
        }
        return result;
    }



}
