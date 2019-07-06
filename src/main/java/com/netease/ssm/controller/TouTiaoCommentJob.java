package com.netease.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.HttpUtil;
import com.netease.ssm.util.RedisUtil;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2018/1/15.
 */
public class TouTiaoCommentJob {

    public static final Logger logger = Logger.getLogger(TouTiaoCommentJob.class);

    //抓取项目组定的今日头条projectId为18
    public static final int PROJECT_ID = 37;

    //redis存的最后的抓取时间
    public static final String TOUTIAO_LAST_TIME = "toutiao_time";

    //抓取组提供的公钥
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLVSH4dNmcRdzYIVT2pf0VMVPmK/3xgaQ/c2R+XQ83bZelVieJSHkuE0ZwhQ+UbFrP3qNanuzPvWei/KcTSWIsU0YuBsK8dgF/qeXnII4qvEwY9E52U8JqYrTupsAMMPtKvkVTzoqABQOGR3Os34fxEq5ca6fpKvXxlDRa4FMasQIDAQAB";

    //抓取组提供的接口
    public static final String REMOTE_URL = "http://bj.nbot.admin.netease.com/api/pull/record/pullDataByStartTime.do";

    public void pushTouTiaoComment (){
        try{
            PoolingClientConnectionManager poolManager = new PoolingClientConnectionManager();
            Map<String,Object> params =new HashMap<String,Object>();
            params.put("page",1);
            params.put("projectId",PROJECT_ID);
            params.put("secretKey",signature(String.valueOf(System.currentTimeMillis()),PUBLIC_KEY));
            // redis取值updateTime
            params.put("startTimestamp",Long.valueOf(RedisUtil.getValue(TOUTIAO_LAST_TIME)));
            String nosResult = HttpUtil.requestBody(poolManager,REMOTE_URL,params);
            if(nosResult == null || nosResult.equals("")){
                logger.info(String.format("pushTouTiaoComment nosResult null."));
                return;
            }
            JSONObject nosResultObject = JSON.parseObject(nosResult);
            if(nosResultObject == null){
                logger.info(String.format("pushTouTiaoComment nosResultObject null. nosResult:%s ", nosResult));
                return;
            }
            int code = nosResultObject.getIntValue("code");
            if(code != 200){
                logger.info(String.format("pushTouTiaoComment code!=200. nosResult:%s ", nosResult));
                return;
            }
            JSONArray result = nosResultObject.getJSONArray("result");
            if(result == null || result.isEmpty() || result.size() == 0){
                logger.info(String.format("pushTouTiaoComment result null. nosResult:%s ", nosResult));
                return;
            }
            for(int i=0; i<result.size(); i++){
                JSONObject resultOne = (JSONObject) result.get(i);
                String nos = resultOne.getString("nos");
                logger.info(String.format("nos:%s",nos));
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
                JSONArray commentArray = nosObject.getJSONArray("comments");
                List<String> comments = getComments(commentArray);

            }

        }catch (Exception e){
            logger.info("pushTouTiaoComment has error !"+e);
            e.printStackTrace();
        }
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

    }
}
