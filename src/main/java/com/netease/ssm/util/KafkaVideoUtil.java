package com.netease.ssm.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bjzhangxicheng on 2017/7/25.
 */
public class KafkaVideoUtil {

    private static Logger logger = Logger.getLogger(KafkaVideoUtil.class);

    private static String BROKERS = "10.112.140.140:9092,10.112.140.141:9092,10.112.140.142:9092";

    public static boolean producerSend(String topic,String data){
        try {
            logger.info(String.format("kafka producer send begin . topic:%s,data:%s",topic,data));
            // 设置配置属性
            Properties props = new Properties();
            props.put("bootstrap.servers",BROKERS);
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", 16384);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<String, String>(props);
            logger.info(String.format("kafka producer send ready . topic:%s,data:%s",topic,data));
            long start = System.currentTimeMillis();
            producer.send(new ProducerRecord<String, String>(topic, data));
            logger.info("kafka send 耗时:" + (System.currentTimeMillis() - start));
            // 关闭producer
            logger.info(String.format("kafka producer send success . topic:%s,data:%s",topic,data));
            producer.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("kafka send error ~"+e);
            return false;
        }
    }

    public static String consumerGet(String topic){
        try{
            Properties props = new Properties();
            props.put("bootstrap.servers", "localhost:9092");
            props.put("group.id", "test");
            props.put("enable.auto.commit", "true"); //自动提交偏移量
            props.put("auto.commit.interval.ms", "1000"); //控制自动提交的频率
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
            consumer.subscribe(topic);

            //consumer.poll(100)拉取超时毫秒数，如果没有新的消息可供拉取，consumer会等待指定的毫秒数，到达超时时间后会直接返回一个空的结果集
            Map<String, ConsumerRecords<String, String>> topicMap = consumer.poll(100);
            for (Map.Entry<String, ConsumerRecords<String, String>> entry : topicMap.entrySet()) {
                logger.info("kafka consumer topic:"+entry.getKey());
                ConsumerRecords<String, String> records = entry.getValue();
                List<ConsumerRecord<String, String>> recordsList =  records.records();
                for(ConsumerRecord<String, String> record : recordsList){
                    logger.info(String.format("kafka consumer offset:%s key:%s value:%s",record.offset(),record.key(),record.value()));
                }
            }
            consumer.close();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("kafka send error ~"+e);
            return null;
        }
    }
}
