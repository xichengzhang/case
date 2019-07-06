package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.pojo.CatchBean;
import com.netease.ssm.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.Range;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Created by bjzhangxicheng on 2017/6/27.
 */
public class MainUtil {

    public static String sign(long time, String json){
        try {
            // POST 参数JSONObject
            //long time = (new Date().getTime())/1000;

            // 计算sign：校验签名
            String postMd5 = getMD5(json);
            String data = "1" + time + postMd5;
            System.out.println(data);
            String hmacData = Signature.calculateRFC2104HMAC(data, "$88Yk5VvnifRbdUzJsh^E24rpGhYbb8I");
            System.out.println(hmacData);
            System.out.println(hmacData.substring(5,15));
            String sign = hmacData.substring(5,15);
            return sign;

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        /*try {
            // POST 参数JSONObject
            JSONObject picListJson = new JSONObject();
            picListJson.put("format", "joint_url");
            JSONObject msg = new JSONObject();
            msg.put("url_fmt", "http://vimg.nosdn.127.net/preview/20180410/Rpvwm9599_%s.jpg");
            msg.put("count", 50);
            picListJson.put("msg", msg);
            System.out.println(JSONObject.toJSONString(picListJson));
            long time = (new Date().getTime())/1000;

            // 计算sign：校验签名
*//*            String postMd5 = getMD5(JSONObject.toJSONString(picListJson));
            String data = "1" + time + postMd5;
            System.out.println(data);
            String hmacData = Signature.calculateRFC2104HMAC(data, "$88Yk5VvnifRbdUzJsh^E24rpGhYbb8I");
            System.out.println(hmacData);
            System.out.println(hmacData.substring(5,15));
            String sign = hmacData.substring(5,15);*//*
            System.out.println(">>>>>>>>>>" + JSONObject.toJSONString(picListJson));
            String sign = sign(time,JSONObject.toJSONString(picListJson));

            //发送获取最佳封面请求
            String bestCoverUrl = "http://vimg-enlarge.ws.netease.com/bestcover/v1/?appid=1&appkey=scrapy&sign="+sign+"&token="+time;
            long start = new Date().getTime();
            //System.out.println(new Date().getTime());
            String resultInfo = HttpUtil.doPostJson(bestCoverUrl,JSONObject.toJSONString(picListJson));
            System.out.println(new Date().getTime() - start);
            System.out.println(resultInfo);

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }*/



        /*DateUtil du = new DateUtil();
        int currenthour = du.getHour();
        System.out.println(currenthour);
        if (currenthour == 0) {
            String end = du.getDate(Calendar.DAY_OF_YEAR, -1);
            System.out.println(end);
        }else{
            String end = du.getDate();
            System.out.println("111" + "  "  + end);
        }

        System.out.println(increaseID("0001"));*/


/*       net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(new CatchBean());
        System.out.println(json.toString());*/
        String p = "http://cms-bucket.ws.126.net/2019/02/28/369ede2d2489465a9f76f57dd9b96903.jpeg.bak?imageView&amp;thumbnail=550x0";
/*        Pattern r = Pattern.compile(".*?\\.(gif|jpg|png|jpeg|bmp).*");
        Matcher m = r.matcher(p);
        if (m.find( )) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
        }
        String p1 = "1111http://cms-bucket.ws.126.net/2019/02/28/369ede2d2489465a9f76f57dd9b96903.jpeg.bak?imageView&amp;thumbnail=550x03333";
        String i = "http://cms-bucket.ws.126.net/2019/02/28/369ede2d2489465a9f76f57dd9b96903.jpeg";
        System.out.println(p1.substring(0,p1.indexOf(i)+i.length()));
        System.out.println(p1.substring(p1.indexOf(i)+i.length()+4,p1.length()));
        System.out.println(p1.substring(0,p1.indexOf(i)+i.length()) + p1.substring(p1.indexOf(i)+i.length()+4,p1.length()));
        System.out.println(p1.replace(".bak",""));
        System.out.println(picSuffix(p1));
        String suffix = picSuffix(p1);
        String newI = p1.replace(suffix, suffix + ".bak");
        System.out.println(newI);

        System.out.println(getBeforeDay("2019-02-28 00:00:00", 1));
        IntStream.range(0, 4)
                .forEach(x -> System.out.println(x + "..."));
        DateTime str1date = new DateTime(str1,DateTime.YEAR_TO_DAY);
        DateTime str2date = new DateTime(str2,DateTime.YEAR_TO_DAY);*/

/*        CloseableHttpClient httpClient= HttpClients.createDefault(); // 创建httpClient实例
        try {
            String url = "http://dingyue.nosdn.127.net/Z64=wId1YWzsC9xcok10S1Shx8xvXm0SqHLgGziUtecsa1472085121750.gif";
            HttpResponse response  = httpClient.execute(new HttpHead(url));
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String te = "T1467169465057\thttp://dingyue.nosdn.127.net/AJQy3tm8SKVegs1a1xFKWT1gF6lZF83P4mc54XLH2DEmP1467169463451.png";
        String[] tearray = te.split("\\t");
        for(String t : tearray){
            System.out.println(t);
        }
        System.out.println(tearray[tearray.length -1]);*/
/*
        final Set<String> z = new HashSet<>();
        z.add("zhang");
        z.forEach(i -> System.out.println(i));
        z.clear();
        z.forEach(i -> System.out.println(i));
        Set<String> x = new HashSet<>();
        x.add("xi");
        z.addAll(x);
        x.forEach(i -> System.out.println(i));

        IntStream.range(1,5).forEach(i-> System.out.println(i));*/

        /*StopWatch watch = new StopWatch();
        watch.start();
        try {
            Thread.sleep(5200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        watch.stop();
        System.out.println(watch.getTime());*/

        /*User user1 = new User();
        user1.setId(3);
        User user2 = new User();
        user2.setId(1);
        User user3 = new User();
        user3.setId(5);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.forEach(o -> System.out.println(o.getId()));


        System.out.println("------");
        Collections.sort(list, (o1, o2) -> o1.getId() - o2.getId() >0 ? 1 : -1);

        list.forEach(o -> System.out.println(o.getId()));*/

  /*      net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
        jsonObject.put("id", 123);
        jsonObject.put("username", "1231231");
        jsonObject.put("tt","1");

        User user = (User) net.sf.json.JSONObject.toBean(jsonObject,User.class);
        System.out.println(user.getId() + "  " + user.getUsername() + "  " + user.getAddress());

        System.out.println(new Double(0.123));*/

/*  String result = "{\"resultcode\":200,\"msg\":\"success\",\"data\":{\"count\":44766,\"pageSize\":1,\"page\":1,\"list\":[{\"words\":\"徐,挂掉\",\"id\":242471}]}}";
        JSONObject resultObject = JSON.parseObject(result);

        JSONObject dataObject = resultObject.getJSONObject("data");
        JSONArray wordList = dataObject.getJSONArray("list");
        wordList.forEach(item -> {
            JSONObject jsonObject = (JSONObject) item;
            System.out.println(jsonObject.getString("words"));
        });*/

        /*String title = "zhangxizhang";
        String[] newContent = {title};
        final String[] aa = {title};
        IntStream.range(0,2).forEach(i->{
            aa[0] = aa[0].replaceFirst("zhang","Q");
        });
        System.out.println(aa[0]);*/

        /*Set<String> set = new HashSet<>();
        set.add("zxc");
        set.add("cccc");
        String[] setss = new String[set.size()];
        System.out.println(set.toArray(setss));
        for(String s : setss){
            System.out.println(s);
        }
        System.out.println(Arrays.toString(setss));


        IntStream.range(1,2).forEach(i-> System.out.println(i));*/

        /*String summary = "12345";
        summary = summary.length() <= 7 ? summary : summary.substring(0, 7);
        System.out.println(summary);*/

        /*Map<String, String> param = new HashMap<>();
        String ts = String.valueOf(new Date().getTime());
        param.put("appkey", "wangyi");
        param.put("page", String.valueOf(1));
        param.put("sign", sign(ts, "wangyi"));
        param.put("ts", ts);
        String params = "?appkey=wangyi&page=1&sign="+sign(ts, "wangyi")+"&ts="+ts;
        String result = HttpUtil.httpget("http://openmp.baike.com/api/wikidoc/entrieslist"+params,"utf-8");
        try {
            FileOutputStream out = new FileOutputStream("d:/test/bk.txt",true); // 输出文件路径
            out.write(result.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        List<String> list = new ArrayList<>();
        list.add("z");
        list.add("x");
        list.add("c");
        list.add("v");
        list.add("gh");
        list  = list.subList(0,3);
        list.forEach(x -> System.out.println(x));
        System.out.println(StringUtils.join(list,","));


        Map<String, String> param = new HashMap<>();

        String ts = String.valueOf(new Date().getTime());
        param.put("appkey", "wangyi");
        param.put("page", String.valueOf(1));
        param.put("sign", sign(ts, "wangyi"));
        param.put("ts", ts);
        String params = "?appkey=wangyi&title=小罗伯特&sign="+sign(ts, "wangyi")+"&ts="+ts;
        String result = HttpUtil.httpget("http://openmp.baike.com/api/wikidoc/doctitles"+params,"utf-8");
        System.out.println(result);

    }

    public static String sign(String timestamp, String appKey) {
        if (StringUtils.isEmpty(timestamp)) {
            return "";
        }
        String hideKey = "RQs0MCjzOT";
        return DigestUtils.md5Hex(appKey + hideKey + timestamp);
    }

    public static boolean isLetterDigit(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        return str.matches(regEx);
    }


    private static boolean dl (String str){
        for(int i=0 ; i<str.length() ; i++){ //循环遍历字符串
            char s = str.charAt(i);
            if(Character.isDigit(s)){     //用char包装类中的判断数字的方法判断每一个字符
                return true;
            }
            if(Character.isLetter(s)){   //用char包装类中的判断字母的方法判断每一个字符
                return true;
            }
        }
        return false;
    }

    public static String getBeforeDay(String specifiedDay , int days) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - days);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }
    private static  String picSuffix(String picUrl) {
        if (StringUtils.isBlank(picUrl)) {
            return "";
        }
        Pattern r = Pattern.compile(".*?\\.(gif|jpg|png|jpeg|bmp).*");
        Matcher m = r.matcher(picUrl);
        if (m.find()) {
            return m.group(1);
        } else {
            return "";
        }
    }

    public static String increaseID(String id)
    {
        id = id.toUpperCase();
        String id1 = id.substring(0, id.length() - 4);
        String id2 = id.substring(id.length() - 4);
        /**
         * 对id2进行处理
         */
        int id2Length = id2.length();
        id2 = IntUtil.c10to32(IntUtil.c32to10(id2) + 1);
        while (id2.length() < id2Length)
        {
            id2 = "0" + id2;
        }
        return id1 + id2;
    }

    private static String handleUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        String[] lmuserArr = userName.split("@");
        String newName = userName;
        if (lmuserArr.length > 1 && lmuserArr[1].equals("163.com")) {
            newName = lmuserArr[0];
        }
        // 薄荷网易号化视频入库用户名过长 只截取@之前的字段 薄荷传过来的用户名最长为53 数据库最长45
        if (userName.length() <= 45) {
            return newName;
        }
        //非163邮箱包含@
        if (userName.contains("@")) {
            int index = userName.lastIndexOf("@");
            newName = userName.substring(0, index);
        }
        if (newName.length() > 45) {
            newName = userName.substring(0, 45);
        }
        return newName;
    }

    public static long getLongID() {
        long longtime = System.currentTimeMillis();
        if (longtime > l) {
            l = longtime;
        } else {
            l++;
        }
        return l;
    }
    public static final long DIFF_TIMESTAMP = 1061500000000L;
    private static long l = 0L;

    public static String getid() {
        try {
            String id = IntUtil.c10to32(getLongID() - DIFF_TIMESTAMP);
            while (id.length() < 8) {
                id = "0" + id;
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private final static char[] digits =
            {
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                    'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z', '{', '}'
            };

    private static String toUnsignedString(long i, int shift)
    {
        int bit = (int) Math.pow(2, shift);
        char[] buf = new char[bit];
        int charPos = bit;
        int radix = 1 << shift;
        long mask = radix - 1;
        do
        {
            buf[--charPos] = digits[(int) (i & mask)];
            i >>>= shift;
        }
        while (i != 0);
        return new String(buf, charPos, (bit - charPos));
    }

    public static int hash(String key) {
        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); ++i)
            hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i);
        return hash;
    }

    /**
     * 对字符串md5加密
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {

        }
        return null;
    }

}
