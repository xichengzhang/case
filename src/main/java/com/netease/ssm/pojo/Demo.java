package com.netease.ssm.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netease.ssm.util.HttpUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.poi.util.StringUtil;
import org.springframework.util.Assert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjzhangxicheng on 2016/12/22.
 */
public class Demo {
    private String vid;
    private String title;
    private String[] dome;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDome() {
        return dome;
    }

    public void setDome(String[] dome) {
        this.dome = dome;
    }


    public static String formatLmuser(String lmuser){
        if(lmuser == null || lmuser.equals("")){
            return null;
        }
        String[] lmuserArr = lmuser.split("@");
        if(lmuserArr.length < 2){
            return lmuser;
        }
        if(lmuserArr[1].equals("163.com")){
            return lmuserArr[0];
        }else{
            return lmuser;
        }
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static void main(String[] args) throws InterruptedException, ParseException {

        /*String xx = "分享到 \uE639 \uE638 \uE63A  \uE6BD\t3  0 \t收藏\n" +
                "管理应具有外行的心态\n" +
                "2017/02/10\t\uE641举报\n" +
                "管理 者应具 有外行 的心态，缺乏直接经 验 实际上 可以 作为 一 个优势，因为较少的历史经验不 会使他们的愿景发生混乱，他们可能以新的方式、新的视角看问题。这个全新的视角可能使他们产生很多新的理念和创新，这无疑也将导致新员工质疑现行惯例，而这些质疑可能导致产生新的方法、理念和创新。";

        //System.out.println(xx);
        System.out.println(replaceBlank(xx));*/
        /*String ss = "http://enc.bn.netease.com/snapshot/videolib2/2017/11/T/L/ED31KDQTL_";
        System.out.println(ss.substring(45,ss.length()));

        String newNosCorp = "http://img163.nos.netease.com/vimg/videolib2_snapshot/" + ss.substring(45,ss.length());

        //http://img163.nos.netease.com/vimg/videolib2_snapshot/2017/11/C/L/ED32O6BCL_7.jpg
        System.out.println(newNosCorp);*/


        /*String aa = "http://enc.bn.netease.com/snapshot/videolib2/2017/11/5/0/ED32OFI50_3.jpg";
        System.out.println(aa.substring(45,aa.length()));
        String newNosCorp = "http://img163.nos.netease.com/vimg/videolib2_snapshot/" + aa.substring(45,aa.length());

        System.out.println(newNosCorp);*/

        /*Map<String,Object> map = new HashMap<String,Object>();
        map.put("nospath","1222");
        map.put("type",0);
        System.out.println(map.toString());*/
        /*String rimag = "http://img163.nos.netease.com/vimg/videolib2_snapshot/2017/12/C/4/ED3RCM9C4_3.jpg";
        System.out.println(rimag.substring(0, rimag.indexOf("_") + 1));
        String rr = rimag.substring(0, rimag.indexOf("_") + 1);
        String newNosCorp = "http://img163.nos.netease.com/vimg/videolib2_snapshot/" + rr.substring(45,rr.length());
        System.out.println(newNosCorp);*/
        //readLineFile("D:/zhangzhang.txt");

        /*JSONObject encodeOkObject = new JSONObject();
        encodeOkObject.put("type","videoStatus");
        Map<String,Object> statusMap = new HashMap<String,Object>();
        statusMap.put("kafkaMsgType","videoStatus");
        encodeOkObject.put("data",statusMap);
        System.out.println(JSON.toJSONString(encodeOkObject));*/

        String x = "http://videocrawler.nos.netease.com/c27a6eb9ae53988ed1735811fc97cf35.mp4?uto=0&rvideoid=ED6ST816F&storeId=ce6d0342-d854-484e-a9c0-4528b064ded8";
        /*try {
            String enx = URLEncoder.encode(x,"UTF-8");
            System.out.println(enx);
            System.out.println(URLDecoder.decode(enx,"UTF-8"));
            enx =  URLDecoder.decode(enx,"UTF-8");
            enx = enx.replace("videocrawler.nos.netease.com","nos.netease.com/videocrawler");
            System.out.println(enx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        /*String xx = "有故事的女人不错。<i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_52_like\"></i><span class=\"emoji-name\">[赞]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span><i class=\"emoji emoji_58_rose\"></i><span class=\"emoji-name\">[玫瑰]</span>";
        System.out.println(xx);
        xx = "有故事的女人不错<123213213}>";
        System.out.println(xx.replaceAll("(?is)<.*?>", "").replaceAll("(?is)\\[.*?]",""));
        String cc = "#2017MAMA亚洲音乐盛典# 特别合作舞台泰民宣美《door》《Gashina》《Move》";
        System.out.println(cc.substring(0,40));
        String xxxxx = "hzzhangxiaocheng@corp.netease.com";
        System.out.println(xxxxx.length());
        if(xxxxx.length() > 32){
            System.out.println(xxxxx.substring(0,32));
        }

        JSONArray jsonArray = null;
        System.out.println(jsonArray.size());
*/
        //构造请求json
        /*Map<String,Object> jsonmap=new HashMap<String,Object>();
        List<String> docids=new ArrayList<String>();
        docids.add("VV7GR4KOJ");
        jsonmap.put("docids", docids);
        Map<String,Object> video= new HashMap <String,Object>();
        List<String> items= Arrays.asList("comment");
        video.put("keys", items);
        video.put("productId", 5);
        video.put("category", "all");
        video.put("comment", "潜规则法轮功阿打算打算美女大长腿打飞机");
        jsonmap.put("VV7GR4KOJ", video);
        String jsonres = HttpUtil.doPostJson("https://ar.ws.netease.com/kw/manage/searchKeyWord", JSON.toJSONString(jsonmap));
        System.out.println(JSON.toJSONString(jsonmap));
        System.out.println(jsonres);*/
        /*String xxx = "张希程";
        try {
            xxx = URLEncoder.encode(xxx,"UTF-8");
            System.out.println(xxx);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(paramUrlDec(xxx));*/

        //System.out.println(paramUrlDec("http://vimg.nosdn.127.net/snapshot/20180205/CuoV61687_0.jpg"));

        /*String pvCountResult = "{\"code\":1,\"msg\":\"success\",\"data\":[]}";
        JSONObject dataObject = JSON.parseObject(pvCountResult);


        JSONArray jsonArray = dataObject.getJSONArray("data");
        if(jsonArray == null || jsonArray.isEmpty()){
            System.out.println("null jsonarry");
        }
        int pvCount = 0;  //播放数
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            pvCount = jsonObject.getIntValue("pvCount");
            String vid = jsonObject.getString("vid");
            System.out.println("pvCountByVid info vid:"+vid+",pvCount:"+pvCount);
        }*/

        /*String wyh = "http://flv.bn.netease.com/videolib3/1802/22/scyrd6904/SD/scyrd6904.flv";
        String zq = "http://flv.bn.netease.com/tvmrepo/2018/1/3/T/scyrd6904/SD/scyrd6904.flv";
        String rvideoid = "scyrd6904";
        System.out.println(wyh.substring(wyh.indexOf(".com/")+5,wyh.indexOf(rvideoid)+9));
        System.out.println(zq.substring(zq.indexOf(".com/")+5,zq.indexOf(rvideoid)+9));



        int transcode_status = 3;
        int destStatus = 2;
        System.out.println(transcode_status & destStatus);

        System.out.println(format(new Date()));*/
        /*try {
            String vid = "VFBUD4S5V";
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("vid", "VFBUD4S5V");
            map.put("nosUrl", "http://videocrawler.nos.netease.com/55dc86c94cbe649ed01e66df45c5d32a.mp4?fingerKey=1231232131231321");
            System.out.println(("check/notWeChatCommit.do notifyCatch jsonParam :" + JSON.toJSONString(map)));
            String resultInfo = HttpUtil.postContent("http://test.nbot.netease.com/video/getVideoVid", map, "UTF-8");
            System.out.println(String.format("check/notWeChatCommit.do notifyCatch resultInfo : %s , vid : %s",resultInfo,vid));
        }catch (Exception e){

        }*/

        /*net.sf.json.JSONObject buttonInfo = new net.sf.json.JSONObject();
        buttonInfo.put("name","查看更多");
        buttonInfo.put("img",null);
        buttonInfo.put("url","2");
        net.sf.json.JSONObject extraButton = new net.sf.json.JSONObject();
        extraButton.put("extraButton",buttonInfo);
        System.out.println(JSON.toJSONString(extraButton));*/
        /*System.out.println(1&7);
        System.out.println(2&7);
        System.out.println(4&7);
        System.out.println(1&6);
        String z = "zxc123ppp";
        z.replace("zxc","qqq");
        System.out.println(z);
        File urlFile = new File("d:/test1/ceshi1.txt");
        urlFile.getParentFile().mkdirs();*/



        /*String pageUrl = "1222";
        System.out.println(pageUrl.substring(1));


        System.out.println(JSON.toJSONString(null));

        System.out.println(HttpUtil.httpGetStatus("http://xml.ws.126.net/video/O/D/1000_VKLEMNVOE.xml"));
        System.out.println(HttpUtil.httpGetStatus("http://xml.ws.126.net/video/O/D/1000_VKLEMNVOD.xml"));
        System.out.println(format(parse("2018-07-13 20:05:07")));
        System.out.println(getIDPath("VKLEMNVOD"));*/
        /*map.remove("vid2");
        System.out.println(JSON.toJSONString(map));

        String mp4Url = "http://flv.bn.netease.com/videolib3/1807/11/XlboT3605/SD/XlboT3605.flv";
        mp4Url = mp4Url.replaceAll("http://[^/]*//*", "http://flv0.bn.netease.com/");
        System.out.println(mp4Url);*/

        /*String pv = "{\"code\":1,\"msg\":\"success\",\"data\":[{\"vid\":\"VYIHBUJQL\",\"pvCount\":30595}]}";
        JSONObject pvo = JSON.parseObject(pv);
        JSONArray data = pvo.getJSONArray("data");
        if (!data.isEmpty()) {
            JSONObject dataOne = data.getJSONObject(0);
            System.out.println(dataOne.get("pvCount"));
        }

        String url = "https://v.qq.com/iframe/preview.html?vid=y0743ioteah&amp;width=500&amp;height=375&amp;auto=0&rvideoid=EDNA55D63&storeId=436fa61e-da3c-49bf-a4f1-bf15571a6c4f";
        String rvideoid = url.substring(url.lastIndexOf("rvideoid=")+9,url.lastIndexOf("rvideoid=")+18);
        String storeId = url.substring(url.lastIndexOf("storeId=")+8,url.length());
        System.out.println(rvideoid);
        System.out.println(storeId);*/

        /*String url = "http://v.163.com/paike/VDHLBOLA4/VDO8FTFPE.html";
        String vid = "";
        Pattern pattern = Pattern.compile("/[0-9a-zA-Z]+/[0-9A-Z]{9}/([0-9A-Z]{9}).html");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            vid = matcher.group(1);
        }
        System.out.println(vid);*/

        /*String url = "http://v.163.com/static/1/VDO8FTFPE.html";
        String vid = "";
        if (url.contains("/static/")) {
            Pattern pattern = Pattern.compile("/static/[0-9]/([0-9A-Z]{9}).html");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                vid = matcher.group(1);
            }
        }
        System.out.println(vid);

        long s = new Date().getTime();*/
        //System.out.println(vids.length);

//        String c = "http://pic-bucket.nosdn.127.net/photo/0080/2017-09-08/CTQSAK021RJ00080NOS.png";

//        System.out.println(getRealRvideoid("3870D42AF4A8AD49886258B41AEAB14D"));

//        String[] array = {"test","test","zhang","xi","zhang"};
/*        String[] array = {};
        Set<String> set = new HashSet<>();
        Collections.addAll(set, array);
        String[] arrayNew = set.toArray(new String[set.size()]);
        Arrays.asList(arrayNew).stream().forEach(System.out::println);*/
        /*for (int i=0; i<array.length; i++)
        {
            set.add(array[i]);
        }
        String[] arrayNew = (String[]) set.stream().map(i -> i).toArray();
        String[] arrayNew = set.toArray(new String[array.length]);
        Arrays.asList(arrayNew).stream().forEach(System.out::println);*/


        /*try {
            URL url = new URL("http://www.debugease.com/javaweb/504541.html");
// 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接。
            URLConnection uc = url.openConnection();
// 打开的连接读取的输入流。
            InputStream in = uc.getInputStream();
            System.out.println("有");
        } catch (Exception e) {
            System.out.println("没有");
        }

        String image = "http://info-database.csdn.net/Upload/2010-1-30/735-60sap1030.jpg";
        isImagesTrue(image);*/

        CatchBean catchBean = JSON.parseObject("{\"id\":\"123\"}", CatchBean.class);
        System.out.println(catchBean);

        printArray(new String[]{"1","2"});

        String s[]=new String[0];
        printArray(s);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String d=sdf.format(date);

        System.out.println(d);

        Date www = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-03-31 18:02:31");
        System.out.println(www);

        /*Date date =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("111111111111");
        System.out.println(date);*/

        String a = "qweq";
        String b = "fdfs";
        System.out.println(a.compareTo(b));


    }

    // 泛型方法 printArray
    public static <E> void printArray( E[] inputArray )
    {
        // 输出数组元素
        for ( E element : inputArray ){
            System.out.printf( "%s ", element );
        }
        System.out.println();
    }



    public static String isImagesTrue(String posturl) {
        try {
            URL url = new URL(posturl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("POST");
            urlcon.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println(HttpURLConnection.HTTP_OK + posturl
                        + ":posted ok!");
                return "200";
            } else {
                System.out.println(urlcon.getResponseCode() + posturl
                        + ":Bad post...");
                return "404";
            }
        }catch (Exception e){
            System.out.println("error");
            return "error";
        }
    }


    public static void aa(String id) {
        try{
            int i = 1/0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ex");
            return;
        }
        System.out.println("123");
    }


    public static String getRealRvideoid(String id){
        String rvideoid = id;
        if(StringUtils.isNotBlank(id) && id.length() > 9){
            try {
                rvideoid = desDecrypt(id, "@#$qwe90");
            } catch (Exception e) {

            }
        }
        return rvideoid;
    }


    public static String desDecrypt(String content, String key) throws Exception{
        if(StringUtils.isBlank(content) || StringUtils.isBlank(key)){
            return "";
        }
        byte[] bytes = parseHexStr2Byte(content);
//        byte[]  decryptResult = DES_CBC_Decrypt(new BASE64Decoder().decodeBuffer(content), key.getBytes(), lm);
        byte[]  decryptResult = DES_CBC_Decrypt(bytes, key.getBytes());
        if(null == decryptResult){
            return "";
        }
        String result = new String(decryptResult);
        return result;
    }

    public static byte[] DES_CBC_Decrypt(byte[] content, byte[] keyBytes){
        try {
            DESKeySpec keySpec=new DESKeySpec(keyBytes);
            SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
            SecretKey key=keyFactory.generateSecret(keySpec);

            Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(keyBytes));
            byte[] result=cipher.doFinal(content);
            return result;
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static String httpget(String url) {
        try {
            URL u = new URL(url);
            StringBuffer sb = new StringBuffer();
            String rLine = null;
            URLConnection conn = u.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((rLine = br.readLine()) != null) {
                sb.append(rLine);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "[]";
    }



    private static String getIDPath(String id) {
        String path = id.charAt(id.length() - 2) + "/" + id.charAt(id.length() - 1);
        return path;
    }

    public static java.util.Date parse(String strDate) {
        SimpleDateFormat dateFormat = dateFormatHolder.get();
        Assert.isTrue(dateFormat != null);
        try {
            return dateFormat.parse(strDate);
        } catch (Throwable ex) {
            throw new IllegalStateException(strDate + "为不合法的日期格式!", ex);
        }
    }



    public static String[] splitTag(String tag) {
        //return tag.split(" |,|;|\\||\\+|\\&|\\^|\\!|`|　|，|；");
        return tag.split(",|;|\\||\\+|\\&|\\^|\\!|`|，|；");
    }

    private static String paramUrlDec(String param){
        try {
            if (param != null && param.trim().length() > 0) {
                return URLDecoder.decode(param, "UTF-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return param;
    }

    public static void a() {
        try {
            System.out.println("111111");
            int a = 12/0;
            System.out.println("123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("444");
    }

    private static void frf (String data,File file){
        DataOutputStream dos;
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            dos = new DataOutputStream(new FileOutputStream(file));
                dos.write((data+"\n").getBytes());
            dos.flush();
            dos.close();
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readLineFile(String filename) {
        try {
            FileInputStream in = new FileInputStream(filename);
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;
            int i = 1;
            while ((line = bufReader.readLine()) != null) {
                System.out.println(line);
                i++;
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String format(java.util.Date date) {
        SimpleDateFormat dateFormat = dateFormatHolder.get();
        Assert.isTrue(dateFormat != null);
        return dateFormat.format(date);
    }

    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {

        @Override
        protected SimpleDateFormat initialValue() {

            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };


    public static void method3(String fileName, String content) {
        RandomAccessFile randomFile = null;
        try {
            // 打开一个随机访问文件流，按读写方式
            randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content+"\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }  finally{
            if(randomFile != null){
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
