package com.netease.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

        /*String zzzz = "{\"msg\":\"success\",\"result\":1}";
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(zzzz);
        int result = jsonObject.getInt("result");
        System.out.println(result);

        String total = sentGetForWeixin("http://mp.weixin.qq.com/s?src=3&timestamp=1491982337&ver=1&signature=LMOIsvFDXbN7k2QgD3vC1W2E*BkYf5gewwEIu5c5Y-JjL0MhCF6z35u1aEwih1PGoOfjtYOdl798JBXYNMnqPHAjNVcyVEFhN32yC5KvfGOGsyZbGmgQKrCFUGJkZRT-WLD-LvwpYLfa6i7*76WpLAtPbLBBn32CDhJwxWyvtZ8=");
        System.out.println(total.substring(total.indexOf("?vid=")+5,total.indexOf("&amp;width=")));*/

        String aaa = "PLOC5u3ZE5KnVJUANxxuL8nDSAvdlyedNw\" class=\"yt-pl-th";
        System.out.println(aaa.substring(0,34));

        String bb = "FL-3jIAlnQmbbVMV6gR7K8aQ\\\" class=\\";
        System.out.println(bb.substring(0,bb.indexOf("\\\"")));

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
