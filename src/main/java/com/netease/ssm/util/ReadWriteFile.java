package com.netease.ssm.util;

import com.alibaba.fastjson.JSON;
import jxl.demo.ReadWrite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2017/7/12.
 */
public class ReadWriteFile {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String datetoString(Date date) {
        return timeFormat.format(date);
    }
    public static void writeFile() {
        String str = "this  is a program"; // 要写入的内容
        try {
            FileOutputStream out = new FileOutputStream("d:/test/ReadWriteshuchu.txt"); // 输出文件路径
            out.write(str.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFile() {
        try {
            FileInputStream in = new FileInputStream("d:/test/ceshi.txt"); // 读取文件路径
            byte bs[] = new byte[in.available()];
            in.read(bs);
            System.out.println("file content=\n" + new String(bs));
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*readFile();
        writeFile();*/

        /*Map<String,Object> map  = new HashMap<String,Object>();
        map.put("vid","weqweqwe");
        map.put("title","asssss");

        System.out.println(JSON.toJSONString(map));*/

        System.out.println(datetoString(new Date()));
    }
}
