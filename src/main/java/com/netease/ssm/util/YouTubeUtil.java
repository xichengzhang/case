package com.netease.ssm.util;

import com.netease.ssm.pojo.Youtube;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
 * Created by bjzhangxicheng on 2017/6/13.
 */
public class YouTubeUtil {

    //所有的栏目id
    private static List<String> categories = new ArrayList<String>();

    //栏目分页url
    private static String CATEGORY_PAGE_URL = "https://www.youtube.com/browse_ajax?action_continuation=1&continuation={continuation}";

    //根据栏目id获取视频列表url
    private static String CATEGORY_ID_URL = "https://www.youtube.com/playlist?list={categoryId}";

    //视频封面图
    private static String VIDEO_IMAGE_URL = "https://i.ytimg.com/vi/{vid}/hqdefault.jpg";

    //最终的视频播放页链接
    private static String VIDEO_URL = "https://www.youtube.com/watch?v={vid}";

    private static String CATEGORY_ID_FLAG = "amp;list=";

    private static String CATEGORY_PAGE_FLAG = "amp;continuation=";

    private static String VIDEO_ID_FLAG = "v=";

    /**
     * 获取YouTube视频相关信息
     * @param playListUrl  eg: https://www.youtube.com/user/SamSeder/playlists
     * @return
     */
    public static List<Youtube> getYoutubeVideos(String playListUrl){
        List<String> categories = categoryByPage(playListUrl);
        if(categories == null || categories.isEmpty()){
            return null;
        }
        List<Youtube> youtubeList = new ArrayList<Youtube>();
        for(String categoryId : categories){
            Set<String> vids = getVidByCategory(categoryId);
            if(vids == null){
                continue;
            }
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
        }
        return youtubeList;
    }

    /**
     * 根据栏目id获取vid
     * @param categoryId 栏目id
     * @return
     */
    private static Set<String> getVidByCategory(String categoryId){
        if(categoryId == null || categoryId.equals("")){
            return null;
        }
        String url = CATEGORY_ID_URL.replace("{categoryId}",categoryId);
        String content = sentGetForYoutube(url);
        if(content == null || content.equals("")){
            return null;
        }
        String[] vidContentArray = content.split(VIDEO_ID_FLAG);
        if(vidContentArray == null){
            return null;
        }
        Set<String> set = new HashSet<String>();
        for(int i=0; i<vidContentArray.length; i++){
            if(i == 0){
                continue;
            }
            String vid = vidContentArray[i].substring(0,11);
            set.add(vid);
        }
        return set;
    }

    /**
     * 抓取标题
     * @param content http请求返回数据
     * @return
     */
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

    /**
     * 抓取描述
     * @param content http请求返回数据
     * @return
     */
    private static String catchDesc(String content){
        Pattern patternDesc = Pattern.compile("(.*)(<meta name=\"description\" content=\")(.*)(\">      <meta name=\"keywords\")(.*)");
        Matcher matcherDesc = patternDesc.matcher(content);
        boolean matchedDesc = matcherDesc.find();
        if (matchedDesc) {
            System.out.println(matcherDesc.group(3));
            return matcherDesc.group(3);
        }
        return "";
    }

    /*private static Set<String> vidsByPage(String url){
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
        for(int i=0; i<vidContentArray.length; i++){
            if(i == 0){
                continue;
            }
            String vid = vidContentArray[i].substring(0,11);
            set.add(vid);
        }
        vidsCate.addAll(set);
        String[] continuationArray = content.split(CATEGORY_PAGE_FLAG);
        if(continuationArray == null && continuationArray.length >= 2){
            String continuation = continuationArray[1].substring(0,80);
            //如果有分页参数,递归
            String newUrl = CATEGORY_PAGE_URL.replace("{continuation}",continuation);
            vidsByPage(newUrl);
        }
        return vidsCate;
    }*/


    /**
     * 获取所有的栏目id (递归)
     * @param url  eg: https://www.youtube.com/user/SamSeder/playlists
     * @return
     */
    private static List<String> categoryByPage(String url){
        if(url == null || url.equals("")){
            return null;
        }
        String content = sentGetForYoutube(url);
        if(content == null || content.equals("")){
            return null;
        }
        String[] firstCategoryArray = content.split(CATEGORY_ID_FLAG);
        if(firstCategoryArray == null){
            return null;
        }
        System.out.println(firstCategoryArray.length);
        List<String> realList = new ArrayList<String>();
        for(int i=0; i<firstCategoryArray.length; i++){
            if(i == 0){
                continue;
            }
            String realListString = firstCategoryArray[i].substring(0,34);
            System.out.println(realListString);
            if(realListString != null && !realListString.equals("")){
                realList.add(realListString);
            }
        }
        categories.addAll(realList); //添加到栏目
        String[] continuationArray = content.split(CATEGORY_PAGE_FLAG);
        if(continuationArray != null && continuationArray.length >= 2){
            String continuation = continuationArray[1].substring(0,272);
            //如果有分页参数,递归
            String newUrl = CATEGORY_PAGE_URL.replace("{continuation}",continuation);
            categoryByPage(newUrl);
        }

        //再次准确过滤一遍
        List<String> realCategories = new ArrayList<String>();
        if(categories != null && !categories.isEmpty()){
            for(String cate : categories){
                if(cate.indexOf("\\\"") > 0){
                    cate = cate.substring(0,cate.indexOf("\\\""));
                }else if(cate.indexOf("\"") > 0){
                    cate = cate.substring(0,cate.indexOf("\""));
                }
                realCategories.add(cate);
            }
        }

        return realCategories;
    }


    public static String sentGetForYoutube(String url) {
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


    public static void main(String[] args) {
        try {
            //System.out.println(sentGetForYoutube("http://www.youtube.com/user/twappledailyfood/playlists"));
            //System.out.println(sentGetForYoutube("https://www.youtube.com/playlist?list=PLkj7J_l_ZHJKi-u33KDTJKEZ1S26c6a5u"));
            //System.out.println(sentGetForYoutube("http://www.youtube.com/watch?v=Zh7lCrIk4wg&list=PLkj7J_l_ZHJKi-u33KDTJKEZ1S26c6a5u&index=1"));

            /*List<String> list = categoryByPage("https://www.youtube.com/user/twappledailyfood/playlists");
            System.out.println(">>>>>"+list.size());
            for(String sss : list){
                System.out.println(sss);
            }*/

            /*Set<String> set = getVidByCategory("PLkj7J_l_ZHJKi-u33KDTJKEZ1S26c6a5u");
            Iterator<String> it = set.iterator();
            while (it.hasNext()){
                String str = it.next();
                System.out.println(str);
            }*/

            /*List<Youtube> list = getYoutubeVideos("https://www.youtube.com/user/twappledailyfood/playlists");
            if(list != null){
                for(Youtube youtube : list){
                    System.out.println(youtube);
                }
                System.out.println("total:" + list.size());
            }*/

            System.out.println(sentGetForYoutube("https://www.youtube.com/watch?v=VJVJKwVmXok"));

            //System.out.println(sentGetForYoutube("https://www.youtube.com/playlist?list=PLkj7J_l_ZHJKi-u33KDTJKEZ1S26c6a5u"));

            /*System.out.println(sentGetForYoutube("https://www.youtube.com/browse_ajax?action_continuation=1&continuation=" +
                    "4qmFsgLFARIYVUMtM2pJQWxuUW1iYlZNVjZnUjdLOGFRGqgBRWdsd2JHRjViR2x6ZEhNZ0FUZ0JZQUZxQUhwbVVWVnNVR0V4YXpWU1YwWm1ZVVpGTTJOSFd" +
                    "qWmxTRXBwVmxWYVJFNHhWa3BPUkVwdlV6RnNibEZXYUVOT01UaDNaVlJTTTA0elRsWlhWMFpDVVROQ1lWZEdUakpTTWpGRVdUQldlRkp1YUU5YVZGcENVMWRLY2xneE9EQk9WVnB1V2taQ2RtVlJ1QUVB" +
                    "&direct_render=1"));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
