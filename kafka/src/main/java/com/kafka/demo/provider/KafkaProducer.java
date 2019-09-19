package com.kafka.demo.provider;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

/**
 * 生产者
 */
@Component
public class KafkaProducer {

    private static KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 向topic中发送消息
     * @param topic
     * @param message
     * @throws Exception
     */
    public static void sendMessage(String topic, String message) throws Exception {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        SendResult<String, String> sendResult = future.get();
        System.out.println("isDone2----->"+future.isDone());
        RecordMetadata recordMetadata = sendResult.getRecordMetadata();
        System.out.println("offset----->"+recordMetadata.offset());
    }

    /**
     * 向topic中发送消息列表
     * @param topic
     * @param messageList
     */
    public static void sendMessageList(String topic, List<String> messageList) {
        if(null == messageList || messageList.size() == 0){
            throw new RuntimeException("消息列表不能为空!");
        }
        messageList.forEach(msg -> kafkaTemplate.send(topic, msg));
    }
}
