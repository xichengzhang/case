package com.netease.ssm.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by bjzhangxicheng on 2017/7/27.
 */
public class Worker implements Runnable  {

    private ConsumerRecord<String, String> consumerRecord;

    public Worker(ConsumerRecord record) {
        this.consumerRecord = record;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " consumed " + consumerRecord.partition()
                    + "th message with offset: " + consumerRecord.offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
