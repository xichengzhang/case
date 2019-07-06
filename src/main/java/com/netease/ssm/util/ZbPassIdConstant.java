package com.netease.ssm.util;

import net.sf.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 不需要折标的用户
 * Created by bjzhangxicheng on 2017/12/9.
 */
public class ZbPassIdConstant {

    public final static List<String> zbPassId = new ArrayList<String>();

    static {
        zbPassId.add("wangqiuyao@126.com");
        zbPassId.add("13716299343");
        zbPassId.add("callmeheaven");
        zbPassId.add("jingjiadameinv");
        zbPassId.add("13686464495");
        zbPassId.add("jocelyn.ljx@alibaba-inc.com");
        zbPassId.add("wwjyouku");
        zbPassId.add("zhaoyanbrave");
        zbPassId.add("yk3468616261");
        zbPassId.add("yk3585355942");
        zbPassId.add("m3173088681");
        zbPassId.add("yk13810048918");
        zbPassId.add("yk2441773817");
        zbPassId.add("yk2681985848");
        zbPassId.add("yk13810049478");
        zbPassId.add("yk13810049558");
        zbPassId.add("yk13810074218");
        zbPassId.add("yk13810063458");
        zbPassId.add("yk13810083408");
        zbPassId.add("yk13810084268");
        zbPassId.add("yk13810084308");
        zbPassId.add("m13810084838");
        zbPassId.add("m13810085468");
        zbPassId.add("yk3475992819");
        zbPassId.add("yk2185443667");
        zbPassId.add("yk3338038730");
        zbPassId.add("yk1847639268");
        zbPassId.add("yk2676607704");
        zbPassId.add("yk2774967317");
        zbPassId.add("yk2016675287");
        zbPassId.add("oi378937");
        zbPassId.add("muliqiusi");
        zbPassId.add("2533846168@qq.com");
        zbPassId.add("jiemetest");
        zbPassId.add("3385554520@qq.com");
        zbPassId.add("3403474969@qq.com");
        zbPassId.add("3109477868@qq.com");
        zbPassId.add("3134581579@qq.com");
        zbPassId.add("2030193889@qq.com");
        zbPassId.add("3332905097@qq.com");
        zbPassId.add("3471570861@qq.com");
        zbPassId.add("2401887066@qq.com");
        zbPassId.add("2150466312@qq.com");
        zbPassId.add("well0611@sina.cn");
        zbPassId.add("759129423@qq.com");
        zbPassId.add("jocelyn559");
        zbPassId.add("mangguotvzongyi");
        zbPassId.add("mangguotvyingshi");
        zbPassId.add("mangguotvzixun");
    }

    public static void main(String[] args) {
        //System.out.println(matchHtmlUrl("<iframe class=\"video_iframe\" data-vidtype=\"2\" allowfullscreen frameborder=\"0\" data-ratio=\"1.7666666666666666\" data-w=\"848\" scrolling=\"no\" src=\"https://v.qq.com/iframe/preview.html?vid=x0515l14ggp&amp;width=500&amp;height=375&amp;auto=0\"></iframe>"));
        /*String imagepath  = "http://ugcv.ws.netease.com/snapshot/20171211/LTig93826_0.jpg";
        System.out.println(imagepath.substring(imagepath.lastIndexOf("snapshot")));*/
        /*String imagepath = "http://crawl.nos.netease.com/25ddf48407912ba64d8cc3e4f23cee65.jpeg";

        //JSONObject strObject = JSONObject.fromObject(vi);

        if (imagepath.contains("nosdn.127.net") | imagepath.contains("nos.netease.com")) {
            System.out.println(1);
        }
        */

        int[] a=returnImgWH("http://img95.699pic.com/photo/50077/0472.jpg_wh300.jpg");
        if(a==null){
            System.out.println("图片未找到!");
        }else{
            System.out.println("宽为" + a[0]);
            System.out.println("高为" + a[1]);
        }



    }


    //读取远程url图片,得到宽高
    public static int[] returnImgWH(String imgurl) {
        boolean b=false;
        try {
            //实例化url
            URL url = new URL(imgurl);
            //载入图片到输入流
            java.io.BufferedInputStream bis = new BufferedInputStream(url.openStream());
            //实例化存储字节数组
            byte[] bytes = new byte[100];
            //设置写入路径以及图片名称
            OutputStream bos = new FileOutputStream(new File( "C:\\thetempimg.jpg"));
            int len;
            while ((len = bis.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            bis.close();
            bos.flush();
            bos.close();
            //关闭输出流
            b=true;
        } catch (Exception e) {
            //如果图片未找到
            b=false;
        }
        int[] a = new int[2];
        if(b){    //图片存在
            //得到文件
            java.io.File file = new java.io.File("C:\\thetempimg.jpg");
            BufferedImage bi = null;
            try {
                //读取图片
                bi = javax.imageio.ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            a[0] = bi.getWidth(); //获得 宽度
            a[1] = bi.getHeight(); //获得 高度
            //删除文件
            file.delete();
        }else{     //图片不存在
            a=null;
        }
        return a;

    }

    public static String[] splitTag(String tag) {
        return tag.split(",|;|\\||\\+|\\&|\\^|\\!|`|　|，|；");
    }
    public static void aa(Map map){
        map.put("z",2);
    }
    private static String matchHtmlUrl(String content){
        Pattern patternTitle = Pattern.compile("<iframe.*data-src=\"([^\"]*?)\"");
        Matcher matcherTitle = patternTitle.matcher(content);
        boolean matchedTitle = matcherTitle.find();
        if (matchedTitle) {
            String x = matcherTitle.group(1);
            return x;
        }
        return "";
    }

    private static String matchHtmlUrlSrc(String content){
        Pattern patternTitle = Pattern.compile("<iframe.*src=\"([^\"]*?)\"");
        Matcher matcherTitle = patternTitle.matcher(content);
        boolean matchedTitle = matcherTitle.find();
        if (matchedTitle) {
            String x = matcherTitle.group(1);
            return x;
        }
        return "";
    }
}
