package com.netease.ssm.controller;

import com.netease.photo.service.PhotoService240;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * @author : xuchonggao
 * @since : 2018-12-26
 */
@Configuration
public class HessianConfig {


    @Bean
    public HessianProxyFactoryBean photoHessService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
//        factory.setServiceUrl("http://10.102.135.91:8181/pic");
        factory.setServiceUrl("http://service.pic.ws.netease.com/pic");
        factory.setServiceInterface(PhotoService240.class);
        return factory;
    }
}
