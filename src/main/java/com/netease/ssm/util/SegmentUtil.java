package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.Segment;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static com.netease.ssm.util.HttpUtil.httpget;


/**
 * Created by bjzhangxicheng on 2017/8/11.
 */
public class SegmentUtil {

    public static final Logger logger = Logger.getLogger(SegmentUtil.class);

    public static void main(String[] args) {

        PoolingClientConnectionManager poolManager = new PoolingClientConnectionManager();
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuu6w4k+VLta3lGXyg4rc3Jw8u4kmpScE7BZ7NmMP+pdFgmpvU4ytZtIymglYkFGeFN+Ej4dCyeXoAjo6IjPjrvFPnlYScnEuYqwggTm2nVUDsN0Mpg3Dg8T7YOPWgWz7v47wafLCk0zvqE4DEsgbrvwhnD7L42e3fti8P0HN7aQIDAQAB";
        String remoteUrl = "http://bj.nbot.admin.netease.com/api/pull/record/pullDataByStartTime.do";
        Map<String,Object> params =new HashMap<String,Object>();
        params.put("page",1);
        params.put("projectId",17);
        try {
            params.put("secretKey",signature(String.valueOf(System.currentTimeMillis()),publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("startTimestamp",1472757081000L);

        String zxc = requestBody(poolManager,remoteUrl,params);
        System.out.println(zxc);
        System.out.println(new Date().getTime());

        /*List<Segment> segmentList = getSegmentList();
        System.out.println(segmentList.size());
        for(Segment segment : segmentList){
            System.out.println(segment);
        }*/

    }

    private static List<Segment> getSegmentList(){
        try {
            List<Segment> segmentList = new ArrayList<Segment>();
            PoolingClientConnectionManager poolManager = new PoolingClientConnectionManager();
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuu6w4k+VLta3lGXyg4rc3Jw8u4kmpScE7BZ7NmMP+pdFgmpvU4ytZtIymglYkFGeFN+Ej4dCyeXoAjo6IjPjrvFPnlYScnEuYqwggTm2nVUDsN0Mpg3Dg8T7YOPWgWz7v47wafLCk0zvqE4DEsgbrvwhnD7L42e3fti8P0HN7aQIDAQAB";
            String remoteUrl = "http://bj.nbot.admin.netease.com/api/pull/record/pullDataByStartTime.do";
            Map<String,Object> params =new HashMap<String,Object>();
            params.put("page",1);
            params.put("projectId",17);
            params.put("secretKey",signature(String.valueOf(System.currentTimeMillis()),publicKey));
            // redis取值updateTime
            params.put("startTimestamp",Long.valueOf(RedisUtil.getValue("segment_time")));
            String nosResult = requestBody(poolManager,remoteUrl,params);
            if(nosResult == null || nosResult.equals("")){
                logger.info(String.format("getSegmentList nosResult:%s ", nosResult));
                return null;
            }
            JSONObject nosResultObject = JSON.parseObject(nosResult);
            if(nosResultObject == null){
                logger.info(String.format("getSegmentList nosResultObject null. nosResult:%s ", nosResult));
                return null;
            }
            int code = nosResultObject.getIntValue("code");
            if(code != 200){
                logger.info(String.format("getSegmentList code!=200. nosResult:%s ", nosResult));
                return null;
            }
            JSONArray result = nosResultObject.getJSONArray("result");
            if(result == null || result.isEmpty() || result.size() == 0){
                System.out.println("getSegmentList nosResult result null ");
                logger.info(String.format("getSegmentList result null. nosResult:%s ", nosResult));
                return null;
            }
            for(int i=0; i<result.size(); i++){
                JSONObject resultOne = (JSONObject) result.get(i);
                String segmentId = "segment_"+resultOne.getString("id");
                String nos = resultOne.getString("nos");
                //System.out.println(nos);
                String nosGetString = HttpUtil.httpget(nos,"utf-8");
                if(nosGetString == null){
                    continue;
                }
                //System.out.println(nosGetString);
                JSONObject nosObject = null;
                if(nosGetString.charAt(0) == '{'){
                    JSONObject nosGetObject = JSON.parseObject(nosGetString);
                    if(nosGetObject == null){
                        continue;
                    }
                    nosObject = nosGetObject;
                }else{
                    JSONArray nosGetArray = JSONArray.parseArray(nosGetString);
                    if(nosGetArray == null || nosGetArray.isEmpty() || nosGetArray.size() == 0){
                        continue;
                    }
                    nosObject = nosGetArray.getJSONObject(0);
                }
                String url = nosObject.getString("video");
                String title = nosObject.getString("title");
                String description = nosObject.getString("content");
                Long updateTime = resultOne.getLong("update_time");
                Segment segment = new Segment(segmentId,url,title,description,updateTime);
                segmentList.add(segment);
                //System.out.println(segment);
                if(i == (result.size() - 1)){
                    //updateTime 存redis
                    RedisUtil.setKey("segment_time",String.valueOf(updateTime));
                }
            }
            return segmentList;
        }catch (Exception e){
            logger.info("getSegmentList has error !"+e);
        }
        return null;
    }

    private static String requestBody(PoolingClientConnectionManager poolManager,String remoteUrl,Map params){
        HttpResponse response = null;
        try {
            HttpClient httpClient = new DefaultHttpClient(poolManager);
            httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpRequestRetryHandler(3,false));
            HttpPost post = new HttpPost(remoteUrl);
            StringEntity body = new StringEntity(JSONObject.toJSONString(params),"UTF-8");
            post.setEntity(body);
            post.setHeader("Content-Type","application/json");
            response = httpClient.execute(post);
            if(response == null){
                return null;
            }
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA加密工具
     */
    public static String signature(String text,String publicKey) throws Exception{
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(decryptBASE64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] cipherText = cipher.doFinal(text.getBytes());
        return (new BASE64Encoder()).encodeBuffer(cipherText);
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
}
