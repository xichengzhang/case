package com.netease.ssm.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.Demo;
import com.netease.ssm.pojo.User;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bjzhangxicheng on 2016/12/22.
 */
public class JsonResult {

    public static String errJsonMsg(String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        if(msg != null && !msg.equals("")){
            jsonObject.put("msg",msg);
            return jsonObject.toJSONString();
        }
        jsonObject.put("msg","error");
        return jsonObject.toJSONString();
    }

    public static String errJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",1);
        jsonObject.put("msg","error");
        return jsonObject.toJSONString();
    }

    public static String successData(Object object){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        if(object == null){
            jsonObject.put("msg","success");
        }
        jsonObject.put("data",object);
        return jsonObject.toJSONString();
    }

    public static String success(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        jsonObject.put("msg","success");
        return jsonObject.toJSONString();
    }

    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {

            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    public static String formatToYyyyMMddHHmmssNumber(Date date) {
        SimpleDateFormat dateFormat = dateFormatHolder.get();
        Assert.isTrue(dateFormat != null);
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static void main(String[] args) {

        /*Demo demo1 = new Demo();
        demo1.setVid("VC800G97F");
        demo1.setTitle("王宝强离婚");
        Demo demo2 = new Demo();
        demo2.setVid("VC800G10D");
        demo2.setTitle("北京大雾霾");
        List<Demo> demos = new ArrayList<Demo>();
        demos.add(demo1);
        demos.add(demo2);
        JSONObject j  = new JSONObject();
        j.put("videos",demos);
        j.put("total",2);
        System.out.println(JsonResult.successData(j));
        Demo demo = new Demo();
        demo.setDome(new String[]{"张三审核通过","李四审核不过"});

        System.out.println(JsonResult.successData(demo));
        */

        /*System.out.println(JsonResult.formatToYyyyMMddHHmmssNumber(new Date()));

        System.out.println(new Date());*/

        /*String response = "{\"code\":200,\"msg\":\"ok\",\"result\":{\"taskId\":\"711134911f224f5380cdfd3214c4d3e7\",\"action\":1,\"labels\":[{\"label\":400,\"level\":1,\"details\":{\"hint\":[\"大盘\",\"大盘\"]}}]}}";

        JSONObject res = JSONObject.parseObject(response);
        int code = res.getInteger("code");
        JSONObject result = res.getJSONObject("result");
        int action = result.getInteger("action");
        String keys = "";
        JSONArray labels = result.getJSONArray("labels");
        JSONObject label = (JSONObject) labels.get(0);
        JSONObject details = (JSONObject) label.get("details");
        JSONArray hint = details.getJSONArray("hint");
        //hint = removeDuplicate(hint);
        for(int i=0; i<hint.size(); i++){
            System.out.println(hint.get(i));
        }*/

        String userInfoJson = "{\"code\":1,\"msg\":\"操作成功\",\"data\":{\"quality\":2,\"tname\":\"极品宅男\"}}";
        String author = "";  //网易号用户名称
        Integer level = 0;  //网易号用户等级
        if(userInfoJson != null){
            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
            if(jsonObject != null){
                if(jsonObject.getInteger("code") == 1){  // 接口调用成功
                    JSONObject data = jsonObject.getJSONObject("data");
                    author = data.getString("tname");
                    level = data.getInteger("quality");
                }
            }
        }
        System.out.println("tname:"+author + "   quality:"+level);

    }

    public static JSONArray removeDuplicate(JSONArray list)   {
        for  (int i = 0; i < list.size() - 1; i++ )   {
            for  (int j = list.size() - 1; j > i; j--)   {
                if  (list.get(j).equals(list.get(i)))   {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}
