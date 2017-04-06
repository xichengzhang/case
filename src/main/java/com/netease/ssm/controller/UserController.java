package com.netease.ssm.controller;

import com.netease.ssm.pojo.User;
import com.netease.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bjzhangxicheng on 2016/12/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //service类
    @Autowired
    private UserService userService;

    /**
     * 查找所用用户控制器方法
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUser")
    public ModelAndView findUser()throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        //调用service方法得到用户列表
        List<User> users = userService.findUser();
        //将得到的用户列表内容添加到ModelAndView中
        modelAndView.addObject("users",users);
        //设置响应的jsp视图
        modelAndView.setViewName("findUser");

        return modelAndView;
    }


    public static int exists(String URLName) {
        try {
            //设置此类是否应该自动执行 HTTP 重定向（响应代码为 3xx 的请求）。
            HttpURLConnection.setFollowRedirects(false);
            //到 URL 所引用的远程对象的连接
            HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			/* 设置 URL 请求的方法， GET POST HEAD OPTIONS PUT DELETE TRACE 以上方法之一是合法的，具体取决于协议的限制。*/
            con.setRequestMethod("HEAD");
            //从 HTTP 响应消息获取状态码
            //return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            return (con.getResponseCode());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getMp4Url(String url){
        String mp4_url="";
        if (url.lastIndexOf(".flv") > 0) {
            mp4_url = url.replace(".flv", "-mobile.mp4");
        } else if (url.lastIndexOf(".xml") > 0) {
            mp4_url = url.substring(0, url.lastIndexOf("/") + 1)
                    + url + "-mobile.mp4";
        }
        return mp4_url;
    }


    public static void main(String[] args) throws InterruptedException {
       /*List<User> ss = new ArrayList<User>();
        User user = new User();
        user.setId(123);
        ss.add(user);

        for(User u : ss){
            u.setId(1);
        }

        for(User cc : ss){
            System.out.println(cc.getId());
        }

        System.out.println(System.currentTimeMillis());
*/

       String zxc = "0001 0003 0004 0005 0006 0007 0008 0009 0010 0011 0012 0015 0016 0023 0025 0026 0029 0030 0031 0035 0036 0037 0038 0040 0041 0042 0076 0080 0082 0085 0087 0090 0092 0093 0095 0096";
        String[] ss = zxc.split(" ");
        System.out.println(zxc);
        for(String s : ss){
            System.out.print(s);
            System.out.print(" ");
        }
    }
}
