package com.netease.ssm.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.HttpUtil;

import java.util.*;
import java.util.stream.IntStream;

public class User {
    private Integer id;

    private String username;

    private Date birthday;

    private String sex;

    private String address;

    private String password;

    private Double age;

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String toString() {
        return this.getUsername();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private static String test(String body, String keyword, String newKeyLabel) {
        StringBuilder stringBuilder = new StringBuilder();
        int firstIndex = body.indexOf(keyword);
        boolean repalced = false;
        if (firstIndex > body.length() / 2) {
            //往后找>
            while (firstIndex > 0) {
                for (int i = firstIndex; i < body.length(); i++) {
                    if (body.charAt(i) == '>' || (body.charAt(i) == '@' && body.charAt(i + 1) == '@')) {
                        break;
                    }
                    if (i == body.length() - 1 || body.charAt(i) == '<') {
                        // replace keyword
                        stringBuilder.append(body.substring(0, firstIndex))
                                .append(newKeyLabel)
                                .append(body.substring(firstIndex + keyword.length(), body.length()));
                        repalced = true;
                        break;
                    }
                }
                if (repalced) {
                    firstIndex = -1;
                } else {
                    firstIndex = body.indexOf(keyword, firstIndex + 1);
                }
            }

        } else {
            //往前找<
            int minCharAt = 0;
            while (firstIndex > 0) {
                for (int i = firstIndex; i > minCharAt; i--) {
                    if (body.charAt(i) == '<' || (body.charAt(i) == '@' && body.charAt(i - 1) == '@')) {
                        minCharAt = i;
                        break;
                    }
                    if (i == 0 || body.charAt(i) == '>') {
                        // replace keyword
                        stringBuilder.append(body.substring(0, firstIndex))
                                .append(newKeyLabel)
                                .append(body.substring(firstIndex + keyword.length(), body.length()));
                        repalced = true;
                        break;
                    }
                }
                if (repalced) {
                    firstIndex = -1;
                } else {
                    firstIndex = body.indexOf(keyword, firstIndex + 1);
                }
            }
        }

        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        }
        return body;

    }

    public static void main(String[] args) {

        String s = "12345678";

        System.out.println(s.substring(0, s.indexOf("45")));
        System.out.println(s.substring(s.indexOf("45") + "45".length(), s.length()));

        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.length());
        if (stringBuilder == null) {
            System.out.println("111111111");
        }

//        mmp("1234<21321321<","<");

        String body = "<p class=\"f_center\"><img alt=\"李亚鹏新女友街头唱歌视频曝光 烟嗓迷人酷似王菲\" \n" +
                "src=\"http://cms-bucket.ws.126.net/2019/05/15/adf798ab2112427493c6fb552b7cac07.jpeg?imageView&amp;thumbnail=550x0\" /><br  /></p>\n" +
                "@@video=\"http://flv.bn.netease.com/videolib1/1903/14/22wwcid0u2/SD/movie_index.m3u8,http://flv.bn.netease.com/videolib1/1903/14/22wwcid0u2/SD/22wwcid0u2-mobile.mp4\" \n" +
                "img=\"http://videoimg.nos-jd.163yun.com/cover/20190314/22wwcid0u22MTdN_cover.jpg\" alt=\"李亚鹏视频测DSFDSFDSFDSF\" \n" +
                "broadcast=\"in\" size=\"\" topicid=\"1000\" commentid=\"A9ASLLH3050835RB\" commentboard=\"video_bbs\" vid=\"VA9ASLLH3\"@@\n" +
                "<p><!--position:video#{\"vid\":\"VKEACG26M\"}--></p><p><b>网易娱乐专稿5月15日报道</b>&nbsp; &nbsp; &nbsp;5月15日，李亚鹏发文承认恋情，称：“不是企业家，没有500亿，\n" +
                "只是一位偶尔喜欢唱唱爵士的文字工作者，一位令人欣赏的独立女性。我理解媒体需要流量，但希望能给予一些基本的尊重，谢谢！”</p><p>随后，有网友曝出李亚鹏新女友街头唱歌的视频，\n" +
                "她长相气质和王菲酷似，烟嗓很迷人，唱歌的时候魅力十李亚鹏足不输王菲。</p><p><!--pkvote(v67028)--></p><p><!--StartFragment--><!李亚鹏--EndFragment--></p>";
        System.out.println(body);
        String newS = test(body, "李亚鹏", "$$$$$$$");
        System.out.println("----------------");
        System.out.println(newS);





















/*        String reslut = HttpUtil.httpget("http://10.122.63.46:8181/kw/api/external/words?bussinessType=WYH&pageSize=500&page=1","utf-8");
//        System.out.println(reslut);

        for(int i=1; i<(45215/500 + 3); i++){
            String result = HttpUtil.httpget("http://10.122.63.46:8181/kw/api/external/words?bussinessType=WYH&pageSize=500&page="+i,"utf-8");
            JSONObject resultObject = JSON.parseObject(result);
            if (null == resultObject) {
                return;
            }
            JSONObject dataObject = resultObject.getJSONObject("data");
            JSONArray wordList = dataObject.getJSONArray("list");
            for(int j=0; j<wordList.size(); j++){
                JSONObject jsonObject = wordList.getJSONObject(j);
                String word = jsonObject.getString("words");
                if(word.equals(new String("习近平"))){
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+i);
                }
            }
            System.out.println(i);
        }*/
    }

    private static List<String> check(List<String> list) {
        if (list.size() <= 1) {
            return list;
        }
        List<String> wordDtos = new ArrayList<>();
        wordDtos.addAll(list);
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(j).contains(list.get(i))) {
                    wordDtos.remove(list.get(i));
                }
            }
        }
        return wordDtos;
    }


}