package com.kafka.demo.test;

import com.kafka.demo.provider.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDemo {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostConstruct
    public void test() throws Exception {

        for(int i = 1;i < 6;i++){
            kafkaProducer.sendMessage("my-topic","开始发送my-topic"+i+"消息---->");
        }

        List<String> messageList = new ArrayList<>();
        messageList.add("开始发送my-topic"+0+"消息---->");
        messageList.add("开始发送my-topic"+1+"消息---->");
        messageList.add("开始发送my-topic"+2+"消息---->");
        messageList.add("开始发送my-topic"+3+"消息---->");
        messageList.add("开始发送my-topic"+4+"消息---->");
        kafkaProducer.sendMessageList("my-topic",messageList);

    }
}
