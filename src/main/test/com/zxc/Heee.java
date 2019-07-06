package com.zxc;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.netease.photo.service.PhotoService240;
import com.netease.ssm.pojo.User;
import com.netease.ssm.service.RemoteFileService;
import com.netease.ssm.util.HttpUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author bjzhangxicheng
 * @since 2018-12-29
 */
public class Heee {


    /*@Before
    public void loginBefore() {
        //加载
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean
        photoService240 = (PhotoService240) beanFactory.getBean("photoService240");
    }*/


    @Test
    public void test03() throws Exception {


        HessianProxyFactory fc = new HessianProxyFactory();
        PhotoService240 photoService240 = (PhotoService240) fc.create(PhotoService240.class, "http://pic.ws.service.163.org/pic");

//		Map<String, String> article = photoService240.getParentTopic("1000");
//		Map<String, String> article = photoService240.getSetInfo("0344",942);
        Map<String, String> article = photoService240.getSetInfo("0009",16828);
//		List<Map<String, String>> article = photoService240.getPictures("0278",1451,"","","",0,100);
//        List<Map<String, String>> article = photoService240.getPhotoListBySetid("0005", 8364);
//        List<Map<String, String>> article = photoService240.getPVforPhotoset("0001",3,10);
        System.out.println(JSON.toJSONString(article));
    }


    private static final String UEL_MODEL_SERVICE = "http://model.cms.service.163.org/httpinvoke/modelFileService.do";
    private static final String UEL_SHAREFILE_SERVICE = "http://sharefile.cms.service.163.org/httpinvoke/shareFileService.do";

    /*@Test
    public void testModel() throws Exception {
        HessianProxyFactory fc = new HessianProxyFactory();
        RemoteFileService remoteFileService = (RemoteFileService) fc.create(RemoteFileService.class, UEL_MODEL_SERVICE);
//        String file = remoteFileService.read("0016video120507");
        boolean exist = remoteFileService.isExist("0016video120507");
//        System.out.println(file);
        System.out.println(exist);
    }*/

    public static void main(String[] args) {
        String url = "http://pic.news.163.com/photocenter/api/list/0001/00AN0001,00AO0001,00AP0001/11120/size/cacheMoreData.json";
        System.out.println( HttpUtil.httpget(url,"utf-8"));
        int size = 20;
        for(int i=0; i<100; i++){
            String s = String.valueOf(size + i);
            new Thread(() -> HttpUtil.httpget(url.replace("size",s),"utf-8"));
            System.out.println(i);

        }
    }
}