package com.netease.ssm.pojo;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.URLConnection;
import java.net.MalformedURLException;
import javax.net.ssl.HttpsURLConnection;
import java.net.*;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.spec.SecretKeySpec;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;


/* 这段程序只用于演示简化的OpenID认证过程，没有完善地检查，并不适合于生产环境中使用。
 * 这个程序用于演示 openid 协议中 consumer 的行为，
 * 主要有一下几个操作：
 * 1. consumer 与 openid server 进行关联（association）
 * 2. consumer 发起认证请求。
 * 3. consumer 对 openid 发送过来的消息进行签名验证，如果签名一致，则认证通过！
 * 4. check_authentication在以下情况下可以使用：
 * （1）consumer没有assoc_handle/mac_key对，并且直接进行第二步（即发起验证请求），此时consumer不能校验签名值，只能向服务器发送check_authentication请求。
 * （2）consumer的assoc_handle和服务器返回的assoc_handle不一致，此时consumer需要向openid server发送check_authentication请求。
 * check_authentication过程中，consumer只需要将 openid 发送过来的消息组织一下，再重新发送给 openid server，即可判断验证是否通过。
 * */
public class openid {
    private static String openid_server = "https://login.netease.com/openid/";
    /*通常来说，在WEB应用中，这个时候你需要把assoc_handle/mac_key保存在一个固定的地方（可以是session或者后端文件，又或者是数据库），但一定不能放在cookie里！
     * */
    private String assoc_handle = "";
    private String mac_key = "";
    private int expires_in = 0;

    private Map<String, String> assoc_data;
    private Map<String, String> redirect_data;
    private Map<String, String> auth_response;

    public void openid(){

    }


    public String MaptoString_url_utf8(Map<String, String> map){
		/* 将URL的参数以分段的方式进行URL utf8编码，并返回一个字符串
		 *
		 * */
        String arguments = "?";
        Iterator iter = map.entrySet().iterator();
        boolean first_arg = true;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            String key_str = (String) key;
            String val_str = (String) val;

            try {
                key_str = URLEncoder.encode(key_str, "UTF-8");
                val_str = URLEncoder.encode(val_str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (first_arg == false){
                arguments = arguments + "&";
            }
            first_arg = false;

            arguments = arguments + key_str;
            arguments = arguments + "=";
            arguments = arguments + val_str;
        }
        return arguments;
    }

    public void association(){
        assoc_data = new HashMap<String, String>();
        assoc_data.put("openid.mode", "associate");
        assoc_data.put("openid.assoc_type", "HMAC-SHA256");
        assoc_data.put("openid.session_type", "no-encryption");

        String arguments = MaptoString_url_utf8(assoc_data);

        URL url = null;

        try {
            url = new URL(openid_server + arguments);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			/*接收OpenID返回的值，将assoc_handle, expires_in, mac_key的值存起来*/
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String str="";

            do{
                try {
                    str=r.readLine();
                    if (str == null) break;
                    String [] temp_arrays = str.split(":");
                    if (temp_arrays[0].equals("assoc_handle")){
                        assoc_handle = temp_arrays[1];
                    }
                    else if (temp_arrays[0].equals("expires_in")){
                        String expires_str = temp_arrays[1];
                        expires_in = Integer.parseInt(expires_str);
                    }
                    else if (temp_arrays[0].equals("mac_key")){
                        mac_key = temp_arrays[1];
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(str);

            }while(str!=null);

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void authentication() throws IOException{
        redirect_data = new HashMap<String, String>();
        redirect_data.put("openid.ns", "http://specs.openid.net/auth/2.0");
        redirect_data.put("openid.mode", "checkid_setup");
        //redirect_data.put("openid.assoc_handle", assoc_handle);
        redirect_data.put("openid.return_to", "http://220.181.29.165:8099/consumer/");
        redirect_data.put("openid.claimed_id", "http://specs.openid.net/auth/2.0/identifier_select");
        redirect_data.put("openid.identity", "http://specs.openid.net/auth/2.0/identifier_select");
        redirect_data.put("openid.realm", "http://220.181.29.165:8099/");
        redirect_data.put("openid.ns.sreg", "http://openid.net/extensions/sreg/1.1");
        redirect_data.put("openid.sreg.required", "nickname,email,fullname");
        redirect_data.put("openid.ns.ax", "http://openid.net/srv/ax/1.0");
        redirect_data.put("openid.ax.mode", "fetch_request");
        redirect_data.put("openid.ax.type.empno", "https://login.netease.com/openid/empno/");
        redirect_data.put("openid.ax.type.dep", "https://login.netease.com/openid/dep/");
        redirect_data.put("openid.ax.required", "empno,dep");

        String arguments = MaptoString_url_utf8(redirect_data);
		/*生成完整的URL地址*/
        System.out.println(openid_server + arguments);
        System.out.println("将上述url贴到浏览器上");
        System.out.println("访问成功后，请将完整的返回URL提交此处：");


        auth_response = new HashMap<String, String>();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br= new BufferedReader(isr);
        String url = br.readLine();


	    /*将OpenID server返回的URL地址解析，获取参数，参数和值都需要UTF-8解码*/
	    /*注：这里最好不要先把整个URL进行UTF-8解码，然后再取出每个openid参数和值。
	      因为openid.return_to或者openid.realm指定的URL可能需要带参数，这样
	      会引入 ?, =, &等特殊符号，从而让openid的参数截取变得困难。*/


        String [] arrays = url.split("\\?|&");
        int size = arrays.length;
        for (int i = 0; i < size; i++){
            int index = arrays[i].indexOf("=");
            if (index == -1) continue;
            String arg = URLDecoder.decode(arrays[i].substring(0,index), "UTF-8");
            String val = URLDecoder.decode(arrays[i].substring(index+1, arrays[i].length()), "UTF-8");
            auth_response.put(arg, val);
        }
    }

    public void check_signature() throws IOException{

        if (auth_response.get("openid.mode").equals("id_res") == false) {
            System.out.println("openid.mode 返回值不是 id_res, 认证失败!");
            return;
        }

        if (auth_response.get("openid.assoc_handle").equals(assoc_handle) == false){
            System.out.println("assoc_handle 不一致，使用check authentication！");
            check_authentication();
            return;
        }
        String [] signed_items = auth_response.get("openid.signed").split(",");
        String signed_content = "";
        for (int i = 0; i < signed_items.length; i++){
            signed_content = signed_content + signed_items[i];
            signed_content = signed_content + ":";
            signed_content = signed_content + auth_response.get("openid." + signed_items[i]);
            signed_content = signed_content + "\n";
        }

        System.out.println("需要签名的参数和值:\n" + signed_content);
	     /*注意，mac_key也许要先进行base64解码*/
        byte [] decoded64 = (new sun.misc.BASE64Decoder()).decodeBuffer(mac_key);
        SecretKey signingKey = new SecretKeySpec(decoded64, "HMACSHA256");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HMACSHA256");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            mac.init(signingKey);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	     /*签名的字符串需要转换成 UTF-8 编码*/
        byte [] digest = mac.doFinal(signed_content.getBytes("UTF-8"));
	    /*计算得到消息摘要之后，需要进行base64编码*/
        String signature = (new sun.misc.BASE64Encoder()).encode(digest);
        System.out.println("openid server返回的签名是:" + auth_response.get("openid.sig"));
        System.out.println("consumer 计算出来的签名是:" + signature);
        if (signature.equals(auth_response.get("openid.sig")) == true){
            System.out.println("签名一致，验证成功！");
        }
        else {
            System.out.println("签名不一致，验证失败！");
        }
    }


    public void check_authentication(){
		/*将openid.mode参数的值设置为check_authentication，其他参数和值不变，发回给OpenID server*/
        auth_response.put("openid.mode", "check_authentication");

        String arguments = MaptoString_url_utf8(auth_response);
        URL url = null;
        try {

            url = new URL(openid_server + arguments);

            System.out.println(url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String str="";
            String auth_result = "";

            do{
                try {
                    str=r.readLine();

                    if (str == null) break;
                    String [] temp_arrays = str.split(":");
                    if (temp_arrays[0].equals("is_valid")){
                        auth_result = temp_arrays[1];
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(str);

            }while(str!=null);
            if (auth_result.equals("true")){
                System.out.println("check authentication 认证成功！");
            }
            else {
                System.out.println("check authentication 认证失败！");
            }

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        openid consumer = new openid();
        //consumer.association();

        try {
            consumer.authentication();
            //consumer.check_signature();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
