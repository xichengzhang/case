package com.netease.ssm.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.HttpUtil;

import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author bjzhangxicheng
 * @since 2019-04-22
 */
public class GetSWords {

    public static void main(String[] args) {
        multiThreadWords();
    }

    public static void writeFile(String s) {
        String str = "words.add(\"" +s + "\");" + "\r\n"; // 要写入的内容
        try {
            FileOutputStream out = new FileOutputStream("d:/test/sWords.txt",true); // 输出文件路径
            out.write(str.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Set<String> multiThreadWords() {
        ExecutorService exs = Executors.newFixedThreadPool(1);
        try {

            int total = 85132;
            if (total == 0) {
                return null;
            }
            // 获取总数,按照100个每页拆分
            int taskCount = total / 100 + 1;
            Set<String> totalSet = new HashSet<>();

            IntStream.range(1, taskCount + 1).forEach(i -> {
                Set<String> result = null;
                try {
                    result = call(i);
//                    totalSet.addAll(result);
                    result.forEach(w -> writeFile(w));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return totalSet;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
        return null;
    }


    private static Set<String> call(Integer page){
        Set<String> oneSet = new HashSet<>();
        // http请求敏感词库并且解析处理
        Map<String, String> param = new HashMap<>();
        param.put("bussinessType", "WYH");
        param.put("pageSize", String.valueOf(100));
        param.put("page", String.valueOf(page));
        String par = "?bussinessType=WYH&pageSize=100&page="+page;
        String result = HttpUtil.httpget("http://kw.service.163.org/kw/api/external/words"+par, "UTF-8");
        JSONObject resultObject = JSON.parseObject(result);
        if (null == resultObject) {
            return null;
        }
        JSONObject dataObject = resultObject.getJSONObject("data");
        JSONArray wordList = dataObject.getJSONArray("list");
        wordList.forEach(item -> {
            JSONObject jsonObject = (JSONObject) item;
            String w = jsonObject.getString("words");
            if(w.contains(",") || dl(w) || w.length()>5){
                return;
            }
            oneSet.add(w);
        });
        System.out.println("sensitiveWordJob page:  " + page);
        return oneSet;
    }

    public static boolean dl(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        return !str.matches(regEx);
    }


}