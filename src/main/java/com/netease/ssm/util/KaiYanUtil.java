package com.netease.ssm.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2017/6/24.
 */
public class KaiYanUtil {

    private static String indexUrl="http://baobab.kaiyanapp.com/api/v4/tabs/selected";

    private static String baseUrl="http://baobab.kaiyanapp.com/api/v4/categories/videoList?strategy=date&num=100&id=";

    private static int [] catIds={36,18,2,14,20,6,24,22,4,30,26,10,32,12,28,8,38,34};


    public static void main(String[] args) {
        /*List<Map<String, Object>> maplist = getMapListByKaiYan(indexUrl);
        for(Map<String,Object> map : maplist){

            Map<String, Object> mapAttribute = (Map<String, Object>) map.get("data");
            System.out.println(">>>>>>>>>>>>>");
            System.out.println("title:" + mapAttribute.get("title"));
            System.out.println("description:" + mapAttribute.get("description"));
            JSONObject jb=JSONObject.fromObject(mapAttribute.get("cover"));
            System.out.println("coverPic:" + jb.get("feed"));
            System.out.println("playUrl:" + mapAttribute.get("playUrl"));
            System.out.println("<<<<<<<<<<<<<");
        }*/


        for(int catId:catIds){
            for (int i=1 ;i<=20; i++) {
                String crawlurl = baseUrl+catId+"&start="+i*100;
                System.out.println(crawlurl);
                List<Map<String, Object>> maplist = getMapListByKaiYan(crawlurl);
                if (maplist != null) {
                    for (Map<String, Object> map : maplist) {

                        Map<String, Object> mapAttribute = (Map<String, Object>) map.get("data");
                        System.out.println(">>>>>>>>>>>>>");
                        String kaiyanId =  mapAttribute.get("id").toString();
                        System.out.println("id id id : "+kaiyanId);
                        System.out.println("title:" + mapAttribute.get("title"));
                        System.out.println("description:" + mapAttribute.get("description"));
                        JSONObject jb=JSONObject.fromObject(mapAttribute.get("cover"));
                        System.out.println("coverPic:" + jb.get("feed"));
                        System.out.println("playUrl:" + mapAttribute.get("playUrl"));
                        System.out.println("<<<<<<<<<<<<<");
                    }
                }
            }
        }
    }


    /**
     * 解析开眼 接口的json串
     * @param requestUrl
     * @return
     */
    public static List<Map<String, Object>> getMapListByKaiYan(String requestUrl) {
        String jsonresult="";
        try {
            jsonresult = HttpUtil.sentGet(requestUrl);
            System.out.println(jsonresult);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        JSONObject jsonObject = JSONObject.fromObject(jsonresult);

        Map<String, Object> alljson = JSONObject.fromObject(jsonObject);
        for (Map.Entry<String, Object> entry : alljson.entrySet()) {
            Object strval1 = entry.getValue();
            if (strval1=="" | "".equals(strval1))
                continue;
            if ("itemList".equals(entry.getKey())) {
                JSONArray jsonObjectStrval1 = JSONArray.fromObject(strval1);
                List<Map<String, Object>> mapListJson = (List) jsonObjectStrval1;
                for (int i = 0; i < mapListJson.size(); i++) {
                    Map<String, Object> obj = mapListJson.get(i);

                    Map<String, Object> map = new HashMap<String, Object>();
                    for (Map.Entry<String, Object> entry2 : obj.entrySet()) {
                        String strkey2 = entry2.getKey();
                        Object strval2 = entry2.getValue();
                        if ("data".equals(strkey2)) {
                            JSONObject jsonObjectAttributes = JSONObject.fromObject(String.valueOf(strval2));
                            Map<String, Object> alljsonAttributes = JSONObject.fromObject(jsonObjectAttributes);
                            map.put(strkey2, alljsonAttributes);
                        } else {
                            map.put(strkey2, String.valueOf(strval2));
                        }
                    }
                    list.add(map);
                }
            }

        }
        return list;
    }

}
