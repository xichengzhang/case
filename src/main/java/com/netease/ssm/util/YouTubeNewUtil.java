package com.netease.ssm.util;

import com.netease.ssm.pojo.Youtube;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2017/6/14.
 */
public class YouTubeNewUtil {

    //栏目分页url
    private static String CATEGORY_PAGE_URL = "https://www.youtube.com/browse_ajax?action_continuation=1&continuation={continuation}";

    //根据栏目id获取视频列表url
    private static String CATEGORY_ID_URL = "https://www.youtube.com/playlist?list={categoryId}";

    //视频封面图
    private static String VIDEO_IMAGE_URL = "https://i.ytimg.com/vi/{vid}/hqdefault.jpg";

    //最终的视频播放页链接
    private static String VIDEO_URL = "https://www.youtube.com/watch?v={vid}";

    private static String VIDEO_ID_FLAG = "v=";

    private static List<String> vidsArray = new ArrayList<String>();  //vids总量

    public static void main(String[] args) {
        /*List<String> vids = catchVids("https://www.youtube.com/user/mlswartz/videos");
        for(String vid : vids){
            System.out.println(VIDEO_URL.replace("{vid}",vid));
        }
        System.out.println(vids.size());*/

        List<Youtube> list = getYoutubeVideos("https://www.youtube.com/user/mlswartz/videos");
        if(list != null){
            for(Youtube youtube : list){
                System.out.println(youtube);
            }
            System.out.println("total:" + list.size());
        }

        //System.out.println(sentGetForYoutube("https://www.youtube.com/user/SamSeder/videos"));
        //System.out.println(catchContinuation(sentGetForYoutube("https://www.youtube.com/browse_ajax?action_continuation=1&continuation=4qmFsgI-EhhVQ1p0RFVtQzNXN2oyNVhIWldGVF9YZ1EaIkVnWjJhV1JsYjNNZ0FEZ0JZQUZxQUhvQ056UzRBUUElM0Q%253D")));
    }

    public static List<Youtube> getYoutubeVideos(String playListUrl){
        List<String> vids = catchVids(playListUrl);
        if(vids == null || vids.isEmpty()){
            return null;
        }
        List<Youtube> youtubeList = new ArrayList<Youtube>();
        for(String vid : vids){
            String videoUrl = VIDEO_URL.replace("{vid}",vid);
            String imageUrl = VIDEO_IMAGE_URL.replace("{vid}",vid);
            String content = sentGetForYoutube(videoUrl);
            Youtube youtube = new Youtube();
            youtube.setVid(vid);
            youtube.setUrl(videoUrl);
            youtube.setImagePath(imageUrl);
            youtube.setTitle(catchTitle(content));
            youtube.setDescription(catchDesc(content));
            youtubeList.add(youtube);
        }
        vidsArray.clear();
        return youtubeList;
    }


    private static String catchTitle(String content){
        Pattern patternTitle = Pattern.compile("(.*)(<title>)(.*)(</title>)(.*)");
        Matcher matcherTitle = patternTitle.matcher(content);
        boolean matchedTitle = matcherTitle.find();
        if (matchedTitle) {
            System.out.println(matcherTitle.group(3));
            return matcherTitle.group(3);
        }
        return "";
    }

    private static String catchDesc(String content){
        Pattern patternDesc = Pattern.compile("(.*)(<p id=\"eow-description\" class=\"\" >)(.*?)(<a href)(.*)");
        Matcher matcherDesc = patternDesc.matcher(content);
        boolean matchedDesc = matcherDesc.find();
        if (matchedDesc) {
            System.out.println(content);
            System.err.println(matcherDesc.group(3));
            String desc = matcherDesc.group(3);
            desc = desc.replace("<br />"," ");
            return desc;
        }
        return "";
    }

    private static List<String> catchVids(String url){
        if(url == null || url.equals("")){
            return null;
        }
        String content = sentGetForYoutube(url);
        if(content == null || content.equals("")){
            return null;
        }

        String[] vidContentArray = content.split(VIDEO_ID_FLAG);
        if(vidContentArray == null){
            return null;
        }
        Set<String> set = new HashSet<String>();
        for(int i=0; i<vidContentArray.length ; i++){
            if(i == 0){
                continue;
            }
            String vid = vidContentArray[i].substring(0,11);
            set.add(vid);
        }
        vidsArray.addAll(set);
        String continuation = catchContinuation(content);
        if(continuation != null && !continuation.equals("")){
            String newUrl = CATEGORY_PAGE_URL.replace("{continuation}",continuation);
            catchVids(newUrl);
        }
        return vidsArray;
    }

    private static String catchContinuation(String content){
        Pattern patternDesc = Pattern.compile("(.*)(amp;continuation=)(.*253D)(.*)");
        Matcher matcherDesc = patternDesc.matcher(content);
        boolean matchedDesc = matcherDesc.find();
        if (matchedDesc) {
            System.out.println(matcherDesc.group(3));
            System.out.println(vidsArray.size());
            return matcherDesc.group(3);
        }
        return "";
    }

    private static String sentGetForYoutube(String url) {
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
}
