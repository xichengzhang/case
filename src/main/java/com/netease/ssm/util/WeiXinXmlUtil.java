package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.netease.ssm.pojo.User;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bjzhangxicheng on 2017/4/13.
 */
public class WeiXinXmlUtil {

    private static final Logger logger = Logger.getLogger(WeiXinXmlUtil.class);

    private static String test = "123";
    public static String parserXml(String uri){
        try {

            if(uri == null || uri.equals("")){
                logger.info("uri of parserXml is null .");
                return null;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(uri);
            Node root = document.getFirstChild();
            NodeList rootChildren = root.getChildNodes();
            Node vd = rootChildren.item(5);
            NodeList vdChildren = vd.getChildNodes();
            Node vi = vdChildren.item(1);
            NodeList viChildren = vi.getChildNodes();
            Node url = viChildren.item(11);
            System.out.println(url.getNodeName());
            System.out.println(url.getTextContent());
            return url.getTextContent();
        }
        catch (Exception e){
            logger.error("WeiXinXmlUtil parserXml has error ! " + e);
            logger.error("catch uri :" + uri);
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*parserXml("http://vv.video.qq.com/geturl?vid=p03911uwpgk&otype=xml&platform=1&ran=0%2E9652906153351068");
        String zh = "自行车现场";
        String en = "zxcch";
        System.out.println(zh.length());
        System.out.println(en.length());
        System.out.println(zh.substring(0,3));

        String json = "123231,222222,4444";
        String j[]  = json.split(",");
        for (String s : j){
            System.out.println(s);
        }

        User user = new User();
        User user1 = new User();
        user.setUsername("在宣传宣传线程");
        user1.setUsername("aaaaaaaa");
        JSONObject jj = new JSONObject();
        jj.put("zxc",123);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(user);
        jsonArray.add(user1);
        jj.put("aqqq",jsonArray);


        String mp4 = "http://211.161.127.17/vhot2.qqvideo.tc.qq.com/y0394ro3ifd.mp4?vkey=7297D49DEC5D6BB25DED3726BFD1393B31E5F412355C05DCAC6A20DFE258842ABB20D1A93C1C7EE118637BE1CF830D157D25E89BE847EF5A8474287B57510BD8FD7ACB46014E87700D73FCF61F9C49B377F0B26A3B631CEF028050DD27B86572&amp;br=62550&amp;platform=1&amp;fmt=mp4&amp;level=0&amp;type=mp4";

        String vkpmp4 = "http://118.212.145.142/vkp.tc.qq.com/w001576gz92.mp4?vkey=C66088F6D916F43AAB240C3EA9C675719432670C6DADA163083939F59C7E9BC282AC740D1C6C37C3FE57663A7CA842736CE36F9A3975A9B12D6C8CA68C3CA8BBAAE7AD90D5629614CF449D290F2E535370376E5364030969D6350F5919A3EC7A&br=62680&platform=1&fmt=mp4&level=0&type=mp4";


        *//*System.out.println(vkpmp4.indexOf("vhot2"));

        System.out.println(vkpmp4.substring(mp4.indexOf("vhot2"),mp4.length()));*//*


        System.out.println(change(mp4));
        System.out.println(change(vkpmp4));*/

/*
        String random = "QZOutputJson={\"br\":62726.71875,\"s\":\"o\",\"sf\":0,\"sp\":0,\"sr\":0,\"vd\":{\"nt\":1,\"vi\":[{\"br\":60,\"ch\":0,\"dt\":2,\"dur\":\"181.068\",\"fmd5\":\"0715f10190ac3eb5e2323c1cbdf16931\",\"fs\":11357801,\"keyid\":\"z0394kxklgx.2\",\"level\":40,\"lnk\":\"z0394kxklgx\",\"st\":2,\"type\":24,\"url\":\"http://118.212.145.144/vhot2.qqvideo.tc.qq.com/z0394kxklgx.mp4?vkey=F7A0EDBA7F7A7C7BECF7CE3F38F5CD64B5BDE0E32BB11267855A8394E6214D0E28B958A78068447640DD4114F697DA0A4ADA41242CBAF870C6BE825BE4DD9658DC803F3A0F152DB2B012B6433211668C08EF29EF90AC3475FDA3D77D52F63B34&br=62726&platform=1&fmt=mp4&level=0&type=mp4\",\"urlbk\":{\"ui\":[{\"dt\":2,\"url\":\"http://118.212.145.152/vhot2.qqvideo.tc.qq.com/z0394kxklgx.mp4?vkey=F7A0EDBA7F7A7C7BECF7CE3F38F5CD64B5BDE0E32BB11267855A8394E6214D0E28B958A78068447640DD4114F697DA0A4ADA41242CBAF870C6BE825BE4DD9658DC803F3A0F152DB2B012B6433211668C08EF29EF90AC3475FDA3D77D52F63B34&br=62726&platform=1&fmt=mp4&level=0&type=mp4\",\"vt\":200},{\"dt\":2,\"url\":\"http://video.dispatch.tc.qq.com/z0394kxklgx.mp4?vkey=F7A0EDBA7F7A7C7BECF7CE3F38F5CD64B5BDE0E32BB11267855A8394E6214D0E28B958A78068447640DD4114F697DA0A4ADA41242CBAF870C6BE825BE4DD9658DC803F3A0F152DB2B012B6433211668C08EF29EF90AC3475FDA3D77D52F63B34&br=62726&platform=1&fmt=mp4&level=0&type=mp4\",\"vt\":0}]},\"videotype\":43,\"vt\":200}]}};";
        System.out.println(random.substring(13,(random.length()-1)));

        String another = "https://v.qq.com/iframe/preview.html?vid=e0393c5403q";
        System.out.println(another.substring(another.indexOf("?vid")+5,another.indexOf("?vid")+16));*/


        /*System.out.println(getSpecifiedDayBefore("2015-03-14 15:53:09"));
        System.out.println(new Date(1493811905000l));
        System.out.println(getSpecifiedAddHours(new Date(1493811905000l),2));
        System.out.println("2017-01-01 00:00:00".substring(0,10));

        Integer aa = 1;
        Integer bb = 1;
        Boolean b = (aa==bb);
        System.out.println(
                b
        );*/

        /*List<User> userList = new ArrayList<User>();

        User user0 = new User();
        user0.setPassword("1111");
        user0.setUsername("haha");
        userList.add(user0);

        User user1 = new User();
        user1.setPassword("1111");
        user1.setUsername("heihei");
        userList.add(user1);

        User user2 = new User();
        user2.setPassword("2222");
        user2.setUsername("lala");
        userList.add(user2);
        Integer qqqq = new Integer(1);
        Integer cccc = new Integer(1);
        System.out.println(qqqq == cccc );

        final int a = 1;
        userList = (List<User>) CollectionUtils.select(userList,
                new Predicate() {
                    public boolean evaluate(Object arg0) {
                        if(a == 1) {
                            User u = (User) arg0;
                            System.out.println(u.getUsername());
                            return "1111".equals(u.getPassword());
                        }else{
                            User u = (User) arg0;
                            return "2222".equals(u.getPassword());
                        }
                    }
               });

        System.out.println(userList);*/

        /*System.out.println(formatDateToString(new Date(1494575725000l)));*/

        /*User user = new User();
        System.out.println(hash(user));
        System.out.println(user.hashCode());
*/
        test = test + "456";
        System.out.println(test);

    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的添加若干个小时后的时间
     * @param d 指定的日期
     * @param hours 要加的小时数
     * @return
     * @throws Exception
     */
    public static String getSpecifiedAddHours(Date d,Integer hours) {
        SimpleDateFormat dateFormat = dateFormatHolder.get();
        Assert.isTrue(dateFormat != null);
        String specifiedDay = dateFormat.format(d);
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.HOUR, hours);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }


    public static String change(String url ){
        if(url.indexOf("vhot2") >= 0){
            return url.substring(url.indexOf("vhot2"),url.length());
        }else if(url.indexOf("vkp") >= 0){
            return url.substring(url.indexOf("vkp"),url.length());
        }else {
            return null;
        }
    }

    public static String formatDateToString(java.util.Date date) {
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
}
