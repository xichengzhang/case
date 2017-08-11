package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.TestEnum;
import com.netease.ssm.pojo.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public class MainUtil {

    private static final String BIG_IMG_HZ_URL = "http://dm.netease.com/cv-news/video_cover?docid={vid}&urls={url}";
    private static final String IR_ENCODING = "UTF-8";
    public static void main(String[] args) {

        String imgPathToHz = "http://dingyue.nosdn.127.net/i9pgskKGiSX=acef3rK4zLYkwZbqNFW2Xiwq6cEDp10Ur1498307730356" + ".jpg";
        String bigImgUrl = BIG_IMG_HZ_URL.replace("{vid}","CNNO5BUU05218K77");
        bigImgUrl = bigImgUrl.replace("{url}",imgPathToHz);
        System.out.println(String.format("开始发送封面图给杭研 VID:%s,imgPathToHz:%s,bigImgUrl:%s","CNNO5BUU05218K77",imgPathToHz,bigImgUrl));
        //String bigImgResult = HttpUtil.httpget(bigImgUrl, IR_ENCODING);
        String bigImgResult = "{\"cover\": {\"fm\": 2126.02,\"height\": 266,\"url\": \"http://vimg1.ws.126.net/image/snapshot/2017/7/J/1/VCPO3U7J1.jpg\",\"width\": 472},\"success\": true}";
        System.out.println(String.format("结束发送封面图给杭研 VID:%s,imgPathToHz:%s,bigImgUrl:%s,result:%s","CNNO5BUU05218K77",imgPathToHz,bigImgUrl,bigImgResult));

        JSONObject bigImgResultObject = JSON.parseObject(bigImgResult);
        boolean successObject = bigImgResultObject.getBoolean("success");
        String fm = "";
        if(successObject){
            JSONObject coverObject = bigImgResultObject.getJSONObject("cover");
            fm = coverObject.getString("fm");
        }
        System.out.println("fm:"+fm);

        Map mm = new HashMap();
        mm.put("article", "11");
        mm.put("operator","cms-video");
        System.out.println(mm.toString());

        User user = new User();
        user.setUsername("123");
        user.setId(1);
        System.out.println(JSON.toJSONString(user));


        String json = "123\t\t3333";
        System.out.println(json);
        json = replaceTabToBlank(json);
        System.out.println(json);


    }

    public void test(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }

    public static final String replaceTabToBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\t");
            Matcher m = p.matcher(str);
            dest = m.replaceAll(" ");
        }
        return dest;
    }

}
