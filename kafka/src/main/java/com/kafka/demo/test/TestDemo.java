package com.kafka.demo.test;

import com.kafka.demo.consumer.KafkaConsumer;
import com.kafka.demo.provider.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestDemo {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @PostConstruct
    public void test() throws Exception {

        for(int i = 1;i < 6;i++){
            kafkaProducer.send("my-topic","开始发送my-topic"+i+"消息---->");
        }

//        Scanner scanner = new Scanner(System.in);
//        String inputContext = null;
//
//        for(;;){
//            inputContext = scanner.next();
//
//            switch (inputContext){
//                case "topic1":
//                    System.out.println("开始发送topic1消息---->");
//                    kafkaProducer.send(inputContext,"开始发送topic1消息---->");
//                    break;
//                case "topic2":
//                    System.out.println("开始发送topic2消息---->");
//                    kafkaProducer.send(inputContext,"开始发送topic2消息---->");
//                    break;
//                case "topic3":
//                    System.out.println("开始发送topic3消息---->");
//                    kafkaProducer.send(inputContext,"开始发送topic3消息---->");
//                    break;
//                case "topic4":
//                    System.out.println("开始发送topic4消息---->");
//                    kafkaProducer.send(inputContext,"开始发送topic4消息---->");
//                    break;
//                case "topic5":
//                    System.out.println("开始发送topic5消息---->");
//                    kafkaProducer.send(inputContext,"开始发送topic5消息---->");
//                    break;
//                default:
//                    System.out.println("消息未知!");
//            }
//        }
    }
}
