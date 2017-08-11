package com.netease.ssm.kafka;

import com.alibaba.fastjson.JSON;
import com.netease.ssm.pojo.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bjzhangxicheng on 2017/7/27.
 */
public class Main {

    public static void main(String[] args) {
        /*String brokerList = "localhost:9092,localhost:9093,localhost:9094";
        String groupId = "group2";
        String topic = "test-topic";
        int workerNum = 5;

        ConsumerHandler consumers = new ConsumerHandler(brokerList, groupId, topic);
        consumers.execute(workerNum);
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException ignored) {}
        consumers.shutdown();*/
        List<User> ss = new ArrayList<User>();
        User user = new User();
        user.setId(123);
        ss.add(user);

        for(User u : ss){
            u.setId(1);
        }

        for(User cc : ss){
            System.out.println(cc.getId());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", "111");
        params.put("operator", "bjzhangxicheng");
        System.out.println(JSON.toJSONString(params));


    }

}
