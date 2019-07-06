package com.netease.ssm.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by bjzhangxicheng on 2018/1/29.
 */
public class Demo22 {
    public static void main(String[] args) throws Exception{
        /*Map<String,Object> jsonmap=new HashMap<String,Object>();
        List<String> docids=new ArrayList<String>();
        docids.add("VV7GR4KOJ");
        jsonmap.put("docids", docids);
        Map<String,Object> video= new HashMap <String,Object>();
        List<String> items= Arrays.asList("comment");
        video.put("keys", items);
        video.put("productId", 5);
        video.put("category", "all");
        String vvv = new String("法轮功大长腿打飞机性感美女".getBytes(),"UTF-8");
        video.put("comment", vvv);
        jsonmap.put("VV7GR4KOJ", video);
        String jsonres = doPostJson("https://ar.ws.netease.com/kw/manage/searchKeyWord", JSON.toJSONString(jsonmap));
        System.out.println(JSON.toJSONString(jsonmap));
        System.out.println(jsonres);

        String result = HttpUtil.httpget("http://statistic.dy.163.com/statistic/video/pv/get.do?vids=VB9SGE046,VB9VD0USM","UTF-8");
        JSONArray jsonArray = JSON.parseArray(result);
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println("vid:"+jsonObject.getString("vid"));
            System.out.println("pvCount:"+jsonObject.getIntValue("pvCount"));
        }*/

        int num = 20;
        int x = num/200;
        System.out.println(x);


    }

    public static String htmlSpecialChars(String str)
    {
        try
        {
            if (str.trim() == null)
            {
                return "";
            }
            StringBuffer sb = new StringBuffer();
            char ch = ' ';
            for (int i = 0; i < str.length(); i++)
            {
                ch = str.charAt(i);
                if (ch == '&')
                {
                    sb.append("&amp;");
                }
                else if (ch == '<')
                {
                    sb.append("&lt;");
                }
                else if (ch == '>')
                {
                    sb.append("&gt;");
                }
                else if (ch == '"')
                {
                    sb.append("&quot;");
                }
                else if (ch == '\'')
                {
                    sb.append("&#039;");
                }
                else if (ch == '(')
                {
                    sb.append("&#040;");
                }
                else if (ch == ')')
                {
                    sb.append("&#041;");
                }
                else if (ch == '@')
                {
                    sb.append("&#064;");
                }
                else
                {
                    sb.append(ch);
                }
            }
            if (sb.toString().replaceAll("&nbsp;", "").replaceAll("��", "")
                    .trim().length() == 0)
            {
                return "";
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            return "";
        }
    }

    public static String doPostJson(String url,String json){
        if(url == null || url.equals("")){
            return null;
        }
        if(json == null || json.equals("")){
            return null;
        }
        String result = null;
        try {
            HttpPost post = new HttpPost(url);
            //配置请求的超时设置
            RequestConfig params = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).
                    setSocketTimeout(4000).setExpectContinueEnabled(true).build();
            CloseableHttpClient httpClient = HttpClients.createDefault();
            StringEntity s = new StringEntity(json);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setConfig(params);
            post.setEntity(s);
            HttpResponse res = httpClient.execute(post);
            result = EntityUtils.toString(res.getEntity());// 返回json格式：
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
