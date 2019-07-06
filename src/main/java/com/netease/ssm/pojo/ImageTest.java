package com.netease.ssm.pojo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author bjzhangxicheng
 * @since 2019-03-20
 */
public class ImageTest {


    public static final String TYPE_JPG = "jpg";
    public static final String TYPE_GIF = "gif";
    public static final String TYPE_PNG = "png";
    public static final String TYPE_JPEG = "jpeg";
    public static final String TYPE_BMP = "bmp";
    public static final String TYPE_UNKNOWN = "unknown";

    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void convert(String source, String formatName, String result) {
        try {
            File f = new File(source);
            f.canRead();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据文件流判断图片类型
     * @param fis
     * @return jpg/png/gif/bmp
     */
    public static String getPicType(FileInputStream fis) {
        //读取文件的前几个字节来判断图片格式
        byte[] b = new byte[4];
        try {
            fis.read(b, 0, b.length);
            String type = bytesToHexString(b).toUpperCase();
            if (type.contains("FFD8FF")) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static int httpHead(String url){
        CloseableHttpClient httpClient= HttpClients.createDefault(); // 创建httpClient实例
        try {
            HttpResponse response  = httpClient.execute(new HttpHead(url));
            int statusCode = response.getStatusLine().getStatusCode();
            return statusCode;
        } catch (Exception e) {
            return 400;
        }
    }

    private static String downloadPicture(String urlList) {
        URL url;
        String imageName = "";
        if(urlList.contains(".jpg")){
            imageName =  "E:/downAndUpload/"+new Date().getTime()+".jpg";
        }else if(urlList.contains(".gif")){
            imageName =  "E:/downAndUpload/"+new Date().getTime()+".gif";
        }else if(urlList.contains(".bmp")){
            imageName =  "E:/downAndUpload/"+new Date().getTime()+".bmp";
        }else if(urlList.contains(".png")) {
            imageName = "E:/downAndUpload/" + new Date().getTime() + ".png";
        }else if(urlList.contains(".jpeg")) {
            imageName = "E:/downAndUpload/" + new Date().getTime() + ".jpeg";
        }else {
            imageName =  "E:/downAndUpload/"+new Date().getTime()+".jpg";
        }
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }


    public static void main(String[] args) {

/*        new Thread(() -> part("E:\\zzz000")).start();
        new Thread(() -> part("E:\\zzz001")).start();
        new Thread(() -> part("E:\\zzz002")).start();
        new Thread(() -> part("E:\\zzz003")).start();
        new Thread(() -> part("E:\\zzz004")).start();
        new Thread(() -> part("E:\\zzz005")).start();
        new Thread(() -> part("E:\\zzz006")).start();
        new Thread(() -> part("E:\\zzz007")).start();
        new Thread(() -> part("E:\\zzz008")).start();
        new Thread(() -> part("E:\\zzz009")).start();*/


/*        new Thread(() -> part("E:\\zzz010")).start();
        new Thread(() -> part("E:\\zzz011")).start();
        new Thread(() -> part("E:\\zzz012")).start();
        new Thread(() -> part("E:\\zzz013")).start();
        new Thread(() -> part("E:\\zzz014")).start();
        new Thread(() -> part("E:\\zzz015")).start();
        new Thread(() -> part("E:\\zzz016")).start();
        new Thread(() -> part("E:\\zzz017")).start();
        new Thread(() -> part("E:\\zzz018")).start();
        new Thread(() -> part("E:\\zzz019")).start();
        new Thread(() -> part("E:\\zzz020")).start();
        new Thread(() -> part("E:\\zzz021")).start();
        new Thread(() -> part("E:\\zzz022")).start();
        new Thread(() -> part("E:\\zzz023")).start();
        new Thread(() -> part("E:\\zzz024")).start();
        new Thread(() -> part("E:\\zzz025")).start();
        new Thread(() -> part("E:\\zzz026")).start();
        new Thread(() -> part("E:\\zzz027")).start();
        new Thread(() -> part("E:\\zzz028")).start();
        new Thread(() -> part("E:\\zzz029")).start();
        new Thread(() -> part("E:\\zzz030")).start();
        new Thread(() -> part("E:\\zzz031")).start();
        new Thread(() -> part("E:\\zzz032")).start();
        new Thread(() -> part("E:\\zzz033")).start();
        new Thread(() -> part("E:\\zzz034")).start();
        new Thread(() -> part("E:\\zzz035")).start();
        new Thread(() -> part("E:\\zzz036")).start();
        new Thread(() -> part("E:\\zzz037")).start();*/

        part("E:\\eee");

    }

    private static String uploadNos (String localUrl){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://t.dy.163.com/wemedia/upload/doBatchUpload");//建立HttpPost对象,改成自己的地址
        File file = new File(localUrl);//相对路径使用不了的话,使用图片绝对路径
        if(!file.exists()){//判断文件是否存在
            System.out.print("文件不存在");
            return null;
        }
        FileBody bin = new FileBody(file, ContentType.create("image/jpg", Consts.UTF_8));//创建图片提交主体信息
        HttpEntity entity = MultipartEntityBuilder
                .create()
                .setCharset(Charset.forName("utf-8"))
                .addPart("imgFile", bin)//添加到请求
                .build();
        httpPost.setEntity(entity);
        HttpResponse response;//发送Post,并返回一个HttpResponse对象
        try {
            response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200) {//如果状态码为200,就是正常返回
                String result = EntityUtils.toString(response.getEntity());
                if(!StringUtils.isEmpty(result)){
                    JSONObject re = JSONObject.fromObject(result);
                    JSONArray reArray = re.getJSONArray("urls");
                    return reArray.get(0).toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    private static void part(String FileName){
//        File file = new File("E:\\no200_result.txt");
        File file = new File("E:\\no200_eee.txt");
        DataOutputStream dos;
        DataOutputStream excep;
        DataOutputStream update;
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            dos = new DataOutputStream(new FileOutputStream(file, true));
//            excep = new DataOutputStream(new FileOutputStream(new File("E:\\exp_result.txt"), true));
            excep = new DataOutputStream(new FileOutputStream(new File("E:\\exp_eee.txt"), true));
//            update = new DataOutputStream(new FileOutputStream(new File("E:\\update_result.txt"), true));
            update = new DataOutputStream(new FileOutputStream(new File("E:\\update_eee.txt"), true));
            LineIterator lines = FileUtils.lineIterator(new File(FileName), "UTF-8");
            while (lines.hasNext()) {
                String line = lines.nextLine();
                String[] lineArray = line.split("\\t");
                if(lineArray != null && lineArray.length > 0) {
                    String imageUrl = lineArray[lineArray.length-1];
                    int status = httpHead(imageUrl);
                    if(status != 200) {
                        dos.write((line + "\n").getBytes());
                        continue;
                    }
                    if(imageUrl.contains(".gif")){
                        String local = downloadPicture(imageUrl);
                        convert(local,"JPG", local.replace(".gif",".jpg"));
                        String nosUrl = uploadNos(local.replace(".gif",".jpg"));
                        System.out.println((lineArray[0] + " "+ nosUrl + "\n"));
                        update.write((lineArray[0] + " "+ nosUrl + "\n").getBytes());
                        continue;
                    }
                    if(imageUrl.contains(".bmp")){
                        String local = downloadPicture(imageUrl);
                        convert(local,"JPG", local.replace(".bmp",".jpg"));
                        String nosUrl = uploadNos(local.replace(".bmp",".jpg"));
                        update.write((lineArray[0] + " "+ nosUrl + "\n").getBytes());
                        continue;
                    }
                    if(imageUrl.contains(".png")){
                        String nosUrl = uploadNos(downloadPicture(imageUrl));
                        update.write((lineArray[0] + " " + nosUrl + "\n").getBytes());
                        continue;
                    }
                    if(imageUrl.contains(".jpeg")){
                        String nosUrl = uploadNos(downloadPicture(imageUrl));
                        update.write((lineArray[0] + " " + nosUrl + "\n").getBytes());
                        continue;
                    }
                    if(!imageUrl.contains(".jpg") && !imageUrl.contains(".png") && !imageUrl.contains(".jpeg") ) {
                        String nosUrl = uploadNos(downloadPicture(imageUrl));
                        update.write((lineArray[0] + " " + nosUrl + "\n").getBytes());
                        continue;
                    }
                    if(!imageUrl.contains("dingyue.") && !imageUrl.contains("cms-bucket.")){
                        String nosUrl = uploadNos(downloadPicture(imageUrl));
                        update.write((lineArray[0] + " "+ nosUrl + "\n").getBytes());
                        continue;
                    }
                }else{
                    excep.write((line + "\n").getBytes());
                }
                System.out.println(line);
            }
            dos.flush();
            dos.close();
            excep.flush();
            excep.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}