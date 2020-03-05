package com.study.kafka.msg;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Properties;

@Component
public class MsgScheduler implements BaseScheduler  {

    @Scheduled(cron = "*/10 * * * * ?")
    @Override
    public void process() {

        System.out.println("kafka Consumer start~!!");

        // FIXME 환경 변수 설정
        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");     // kafka server host 및 port
        configs.put("session.timeout.ms", "10000");             // session 설정
        configs.put("group.id", "M1");                // topic 설정
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");    // key deserializer
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  // value deserializer

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(configs);    // consumer 생성
        consumer.subscribe(Arrays.asList("M01"));      // topic 설정
        ConsumerRecords<String, String> records = consumer.poll(500);
        for (ConsumerRecord<String, String> record : records)
        {
            String s = record.topic();
            if ("M01".equals(s))
                System.out.println(record.value());
             else
                throw new IllegalStateException("get message on topic " + record.topic());
        }
    }
}
