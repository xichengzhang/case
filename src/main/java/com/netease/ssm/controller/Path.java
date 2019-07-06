package com.netease.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.DesUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
/**
 * Created by bjzhangxicheng on 2018/3/14.
 */
public class Path {
    public static void main(String[] args) throws Exception {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.nanoTime());

        String path = "flv2.bn.netease.com/videolib3/1803/14/shlcu3686/SD/shlcu3686-mobile.mp4";

        String desEn = DesUtil.getDesEncrypt(path, "asdfghjk");
        long s = System.currentTimeMillis();
//        DesUtil.getDesDecrypt(desEn, "asdfghjk");
        DesUtil.getDesEncrypt(path, "asdfghjk");
        long m = System.currentTimeMillis();
        System.out.println(m - s);

        String desEn1 = DesUtil.getDesEncrypt("1803/14/shlcu3686/SD/shlcu3686-mobile.mp4", "asdfghjk");
        long s1 = System.currentTimeMillis();
//        DesUtil.getDesDecrypt(desEn1, "asdfghjk");
        md5ToLower("flv2.bn.netease.com/videolib3/1803/14/shlcu3686/SD/shlcu3686-mobile.mp4");
        long m1 = System.currentTimeMillis();
        System.out.println(m1 - s1);
    }

    public static String md5ToLower(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(src.getBytes("utf-8"));
        byte[] md5Bytes = digest.digest();
        return Hex.encodeHexString(md5Bytes);
    }

    public static String httpGet(String url, Map<String, Object> param, int timeout) {
        long startTime = System.currentTimeMillis();
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig).setRetryHandler(new DefaultHttpRequestRetryHandler(3, false))
                .build();
        String res = "";
        try {
            HttpGet get = new HttpGet(url);
            HttpResponse response = httpclient.execute(get);
            res = EntityUtils.toString(response.getEntity());
            get.abort();
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            if (e instanceof SocketTimeoutException) {
            }
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
            }
        }
        return res;
    }
}
