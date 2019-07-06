package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.CatchBean;
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

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2017/8/28.
 */
public class CatchUtil {

    public static final Logger logger = Logger.getLogger(CatchUtil.class);

    //抓取项目组定的段子抓取projectId为18
    public static final int PROJECT_ID = 18;

    //redis存的最后的抓取时间
    public static final String REDIS_LAST_TIME = "segment_time";

    //段子项目组提供的公钥
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCuu6w4k+VLta3lGXyg4rc3Jw8u4kmpScE7BZ7NmMP+pdFgmpvU4ytZtIymglYkFGeFN+Ej4dCyeXoAjo6IjPjrvFPnlYScnEuYqwggTm2nVUDsN0Mpg3Dg8T7YOPWgWz7v47wafLCk0zvqE4DEsgbrvwhnD7L42e3fti8P0HN7aQIDAQAB";

    //段子项目组提供的接口
    public static final String REMOTE_URL = "http://bj.nbot.admin.netease.com/api/pull/record/pullDataByStartTime.do";

    public static List<CatchBean> getCatchBeanList(){
        try {
            List<CatchBean> catchBeanList = new ArrayList<CatchBean>();
            PoolingClientConnectionManager poolManager = new PoolingClientConnectionManager();
            Map<String,Object> params =new HashMap<String,Object>();
            params.put("page",1);
            params.put("projectId",PROJECT_ID);
            params.put("secretKey",signature(String.valueOf(System.currentTimeMillis()),PUBLIC_KEY));
            // redis取值updateTime
            //params.put("startTimestamp",Long.valueOf(RedisUtil.getValue(REDIS_LAST_TIME)));
            params.put("startTimestamp",1504081130000L);
            String nosResult = requestBody(poolManager,REMOTE_URL,params);
            if(nosResult == null || nosResult.equals("")){
                logger.info(String.format("getSegmentList nosResult null."));
                return null;
            }
            System.out.println(">>>>"+nosResult);
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
                logger.info(String.format("getSegmentList result null. nosResult:%s ", nosResult));
                return null;
            }

            for(int i=0; i<result.size(); i++){
                JSONObject resultOne = (JSONObject) result.get(i);
                //B站和西瓜的区分变量 1:B站 2:西瓜
                int flag = 0;
                String source = resultOne.getString("source");
                if(source == null || source.equals("")){
                    continue;
                }
                if(source.indexOf("space.bilibili.com") > 0){
                    flag = 1;
                }else if(source.indexOf("www.toutiao.com") > 0){
                    flag = 2;
                }else{
                    continue;
                }
                String catchId = "";
                if(flag == 1){
                    catchId = "bilibili_" + resultOne.getString("id");
                }else if(flag == 2){
                    catchId = "watermelon_" + resultOne.getString("id");
                }else{
                    continue;
                }
                String nos = resultOne.getString("nos");
                logger.info(String.format("catchId:%s , nos:%s",catchId,nos));
                String nosGetString = HttpUtil.httpget(nos,"utf-8");
                if(nosGetString == null){
                    continue;
                }
                logger.info(String.format("httpGet nosGetString:%s ",nosGetString));
                JSONArray nosGetArray = JSONArray.parseArray(nosGetString);
                if(nosGetArray == null || nosGetArray.isEmpty() || nosGetArray.size() == 0){
                    continue;
                }
                JSONObject nosObject = nosGetArray.getJSONObject(0);
                System.out.println(JSON.toJSONString(nosObject));
                String url = nosObject.getString("videoUrl");
                String title = nosObject.getString("title");
                String description = nosObject.getString("description");
                String coverPic = nosObject.getString("coverPic");
                Long updateTime = resultOne.getLong("update_time");
                String sourceUrl = resultOne.getString("url");
                JSONArray commentArray = nosObject.getJSONArray("comments");
                List<String> comments = getComments(commentArray);
                CatchBean catchBean = new CatchBean(catchId,url,title,description,updateTime,coverPic,flag,comments,sourceUrl);
                catchBeanList.add(catchBean);
                logger.info("catchBean :"+catchBean);
                System.out.println(catchBean);
                /*if(i == (result.size() - 1)){
                    //updateTime 存redis
                    RedisUtil.setKey(REDIS_LAST_TIME,String.valueOf(updateTime));
                }*/
            }
            return catchBeanList;
        }catch (Exception e){
            logger.info("catchBeanList has error !"+e);
        }
        return null;
    }

    private static List<String> getComments(JSONArray commentArray){
        if(commentArray == null || commentArray.isEmpty()){
            return null;
        }
        List<String> comments = new ArrayList<String>();
        for(int i=0;i<commentArray.size();i++){
            String comment = commentArray.getString(i);
            if(comment == null || comment.equals("")){
                continue;
            }
            comments.add(comment);
        }
        return comments;
    }

    private static String requestBody(PoolingClientConnectionManager poolManager,String remoteUrl,Map params){
       /* HttpResponse response = null;
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
        }*/
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

    public static void main(String[] args) {
        getCatchBeanList();
    }

}
