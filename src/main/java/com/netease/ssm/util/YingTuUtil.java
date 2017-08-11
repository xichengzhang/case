package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by bjzhangxicheng on 2017/7/3.
 */
public class YingTuUtil {

    public static void main(String[] args) {
        int  catIdStart=0;//类别id起始
        int  catIdEnd=21;//类别id结束
        String baseUrl="https://app.yingtu.co/v1/interaction/topic/suggest/list";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("deviceId", "I_M_m_b_358962070062954,807abf56a077");
        params.put("sign", "0428202c5db1957c8744c8fc296842b7d692b699");
        params.put("source", "android");
        params.put("timestamp", new Date().getTime()/1000);
        //params.put("timestamp", 1499159767);
        params.put("longitude", "116.28245926226585");
        params.put("latitude", "40.04944338010543");
        params.put("userId", "875191629797145600");
        //params.put("refreshType",2);
        Map<String,Object> dataMap=new HashMap<String,Object>();
        dataMap.put("categoryId","18");
        dataMap.put("pageId",3);
        dataMap.put("refreshType",2);
        dataMap.put("timestamp",new Date().getTime()/1000);

        for(int cat=3;cat<=3;cat++){
            params.put("data", dataMap);
            System.out.println(JSON.toJSONString(params));
            //params.put("categoryId", "3");
            for (int i=0 ;i<=0; i++) {
                //params.put("pageId", i);
                System.out.println(baseUrl);
                List<Map<String, Object>> maplist = getMapListByYingTu(baseUrl,params);
                if (maplist != null) {
                    System.out.println("maplist :" + maplist.size());
                    for (Map<String, Object> map : maplist) {
                        System.out.println(">>>>>");
                        String title = (String) map.get("videoName");  //匹配视频标题
                        System.out.println("title:"+title);
                        String description = (String) map.get("videoIntro");   //匹配视频摘要
                        System.out.println("description:"+description);
                        String videoDownloadUrl = (String) map.get("videoPlayUrl");
                        System.out.println("videoDownloadUrl:"+videoDownloadUrl);
                        String coverPic = (String) map.get("videoCoverUrl");
                        System.out.println("coverPic:"+coverPic);
                        System.out.println("<<<<<");
                    }
                }
            }
        }

    }

    /**
     * 解析映兔 接口的json串
     * @param requestUrl
     * @return
     */
    public static List<Map<String, Object>> getMapListByYingTu(
            String requestUrl,Map<String,Object> params) {
        String jsonresult="";
        try {


            jsonresult = HttpUtil.postBody(requestUrl, params, "utf-8", 10000, true);
            System.out.println(jsonresult);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        JSONObject jsonObject = JSONObject.fromObject(jsonresult);
        List<Map<String, Object>> map =new ArrayList<Map<String, Object>>();
        Map<String, Object> alljson = JSONObject.fromObject(jsonObject);
        for (Map.Entry<String, Object> entry : alljson.entrySet()) {
            Object strval1 = entry.getValue();
            if (strval1=="" | "".equals(strval1))
                continue;
            if ("data".equals(entry.getKey())) {


                JSONObject json2 = JSONObject.fromObject(strval1);
                if(json2.get("suggestTopicList") == null || json2.get("suggestTopicList").equals("null")){
                    System.out.println(json2.get("suggestTopicList"));
                    continue;
                }
                JSONArray json3=json2.getJSONArray("suggestTopicList");

                for(int i=0;i<json3.size();i++){

                    JSONObject json4=JSONObject.fromObject(json3.get(i));

                    map = JSONArray.fromObject(json4.getJSONArray("videoList"));
                    list.addAll(map);
                    //System.out.println(list.size());
                }



            }

        }
        return list;
    }
}
