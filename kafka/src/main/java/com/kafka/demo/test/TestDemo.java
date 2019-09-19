package com.kafka.demo.test;

import com.kafka.demo.provider.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestDemo {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostConstruct
    public void test() throws Exception {

        for(int i = 1;i < 6;i++){
            kafkaProducer.send("my-topic","开始发送my-topic"+i+"消息---->");
        }
    }
}
