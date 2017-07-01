package com.netease.ssm.util;

import com.netease.ssm.pojo.Facebook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/6/20.
 */
public class FacebookUtil {

    private static String FACE_BOOK_VIDS_URL = "https://graph.facebook.com/{userId}/videos/uploaded?access_token={access_token}";

    private static String FACE_BOOK_VIDS_SINCE_URL = "https://graph.facebook.com/{userId}/videos/uploaded?since={since}T00%3A00%3A00&access_token={access_token}";

    //两个月时效，建议2017.08.15之后失效，需要换新的
    private static String ACCESS_TOKEN = "EAAE1j3GhZCSEBAIOcTOHuJ9SniKhDb9ZBKZB6OEBLo7Aj9JY7YabA9JjMv9mMMV5VpoHhY4avW8i8aWCqBXZBHhoDDAsRXa5OQ98QKnCJIdTPpJdURYANJTEsZCZCNydqYTo2LdwFGDoHmZB1RaGjEBNjlddTMj1OBwCrGna6qmegZDZD";

    private static String VIDEO_URL = "https://www.facebook.com/{userId}/videos/{vid}";

    private static List<Facebook> catchDone(String userId){
        if(userId == null || userId.equals("")){
            return null;
        }
        List<Facebook> facebookList = new ArrayList<Facebook>();
        String faceBookVidsUrl = FACE_BOOK_VIDS_URL.replace("{userId}",userId).replace("{access_token}",ACCESS_TOKEN);
        //String fvuResult = HttpUtil.httpProxyGet(faceBookVidsUrl,"UTF-8");
        String fvuResult = HttpUtil.httpProxyGet(faceBookVidsUrl);
        System.out.println(fvuResult);
        JSONObject fvuResultObject = JSONObject.fromObject(fvuResult);
        if(fvuResultObject == null){
            return null;
        }
        Object error = fvuResultObject.get("error");
        if(error != null){//构造新的url
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            String sinceParam = getShortDateFiveWeekAgo(new Date());
            String faceBookSinceVidsUrl = FACE_BOOK_VIDS_SINCE_URL.replace("{userId}",userId).replace("{access_token}",ACCESS_TOKEN).replace("{since}",sinceParam);
            facebookList = catchByPage(faceBookSinceVidsUrl,facebookList);
        }else{//走正常逻辑的抓取
            facebookList = catchByPage(faceBookVidsUrl,facebookList);
        }
        for(Facebook facebook : facebookList){
            String videoUrl = VIDEO_URL.replace("{userId}",userId).replace("{vid}",facebook.getVid());
            facebook.setUrl(videoUrl);
            String imageUrl = catchImage(sentGetForFacebook(videoUrl));
            if(imageUrl == null || imageUrl.equals("")){
                continue;
            }
            facebook.setImagePath(imageUrl);
        }
        return facebookList;
    }

    /**
     * 抓取封面图
     * @param content http请求返回数据
     * @return
     */
    private static String catchImage(String content){
        System.out.println(">>"+content+"<<");
        Pattern patternTitle = Pattern.compile("(.*)(style=\"background-image: url\\()(.*?)(\\))(.*)");
        Matcher matcherTitle = patternTitle.matcher(content);
        boolean matchedTitle = matcherTitle.find();
        if (matchedTitle) {
            String x = matcherTitle.group(3).replace("amp;","");
            System.out.println(x);
            return x;
        }
        return "";
    }


    //正常逻辑的抓取
    private static List<Facebook> catchByPage(String catchUrl, List<Facebook> facebooks){
        if(catchUrl == null || catchUrl.equals("")){
            return null;
        }
        //String jsonResult = HttpUtil.httpProxyGet(catchUrl,"UTF-8");
        String jsonResult = HttpUtil.httpProxyGet(catchUrl);
        if(jsonResult == null || jsonResult.equals("")){
            return facebooks;
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonResult);
        if(jsonObject == null){
            return facebooks;
        }
        JSONArray data = jsonObject.getJSONArray("data");
        if(data == null){
            return facebooks;
        }
        for(int i=0; i<data.size(); i++){
            JSONObject d = data.getJSONObject(i);
            if(d == null){
                continue;
            }
            Facebook facebook = new Facebook();
            Object id = d.get("id");
            if(id != null){
                facebook.setVid(id.toString());
            }
            Object description = d.get("description");
            if(description != null){
                String newDes = description.toString().replaceAll("\r|\n", "");
                facebook.setDescription(newDes);
                facebook.setTitle(newDes);
            }
            facebooks.add(facebook);
            System.out.println(facebook);
        }
        //System.out.println(">>>>"+jsonResult+"<<<<");
        JSONObject paging = jsonObject.getJSONObject("paging");
        if(paging == null){
            return facebooks;
        }
        Object next = paging.get("next");
        if(next == null ){  //最后一页
            return facebooks;
        }else{  //还有下一页
            catchByPage(next.toString(),facebooks);
        }
        return facebooks;
    }

    public static String sentGetForFacebook(String url) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient httpclient = httpClientBuilder.build();
        //HttpHost proxy = new HttpHost("113.108.225.182", 31298); //翻墙代理
        //RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        String result = "";
        try {
            // 创建get连接
            HttpGet httpget = new HttpGet(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(200000).setConnectTimeout(1000 * 10)
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
            try {
                httpclient.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获得一周之前的短日期
     * @param date
     * @return
     */
    public static String getShortDateFiveWeekAgo(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, -5);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    public static void main(String[] args) {

        String vidsUrl = "https://graph.facebook.com/MrCookingPanda/videos/uploaded?access_token=";

        String token = "EAAE1j3GhZCSEBAIOcTOHuJ9SniKhDb9ZBKZB6OEBLo7Aj9JY7YabA9JjMv9mMMV5VpoHhY4avW8i8aWCqBXZBHhoDDAsRXa5OQ98QKnCJIdTPpJdURYANJTEsZCZCNydqYTo2LdwFGDoHmZB1RaGjEBNjlddTMj1OBwCrGna6qmegZDZD";

        String facebookUrl = "https://www.facebook.com/technologyreview/videos/10155390607934798/";

        System.out.println(sentGetForFacebook("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=30971&editionType=default&source=qcloud"));

        //System.out.println(HttpUtil.httpget("https://graph.facebook.com/weebthings/videos/uploaded?access_token=EAAE1j3GhZCSEBAIOcTOHuJ9SniKhDb9ZBKZB6OEBLo7Aj9JY7YabA9JjMv9mMMV5VpoHhY4avW8i8aWCqBXZBHhoDDAsRXa5OQ98QKnCJIdTPpJdURYANJTEsZCZCNydqYTo2LdwFGDoHmZB1RaGjEBNjlddTMj1OBwCrGna6qmegZDZD","UTF-8"));

        /*List<Facebook> facebookList = catchDone("HomeCookingShow");
        for(Facebook facebook : facebookList){
            System.out.println(facebook);
        }
        System.out.println("total:"+facebookList.size());*/
    }
}
