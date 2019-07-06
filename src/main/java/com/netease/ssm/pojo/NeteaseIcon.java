package com.netease.ssm.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author bjzhangxicheng
 * @since 2019-03-19
 */
public class NeteaseIcon {

    public static final String TYPE_JPG = "jpg";
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_PNG = "png";
    public static final String TYPE_JPEG = "jpeg";
    public static final String TYPE_BMP = "bmp";
    public static final String TYPE_UNKNOWN = "unknown";

    public static void main(String[] args) {

        try {
            String imageOk = "http://dingyue.nosdn.127.net/0OQXBahXxcsw4fkudQAbucNeAvy80pbSX2wYT7FjcFRpC1474361974871.jpg";
            String imageJpeg = "http://dingyue.nosdn.127.net/2GF=ERntQv5xsMCcv8I3F1l42052KziIc3hj6tBVXUqbV1540892123119.jpeg";
            String imageFail = "http://info-database.csdn.net/Upload/2010-1-30/735-60sap1030.jpg";
            String imageNo = "https://bbs.csdn.net/topics/350145090";
            URL url = new URL(imageFail);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            System.out.println(getFileType(in));
        }catch (Exception e){
            System.out.println("error");
        }
    }

    public static String getFileType(InputStream is) throws IOException {
        byte[] src = new byte[28];
        is.read(src, 0, 28);
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String type = stringBuilder.toString();
        if(type.startsWith("FFD8FF")){
            return TYPE_JPG;
        } else if (type.contains("89504E47")) {
            return TYPE_PNG;
        } else if (type.contains("47494638")) {
            return TYPE_GIF;
        } else if (type.contains("424D")) {
            return TYPE_BMP;
        }else{
            return TYPE_UNKNOWN;
        }
    }

}