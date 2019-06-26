package com.kafka.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 消费者
 */
@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 监听topic1主题,单条消费
     */
    @KafkaListener(topics = {"my-topic"},groupId = "test-consumer-group1")
    public void listen1(ConsumerRecord<String, String> record) {
        System.out.println("topic1开始收到消息------------>from"+record.partition());
        consumer(record);
    }

    /**
     * 监听topic2主题,单条消费
     */
    @KafkaListener(topics = {"my-topic"},groupId = "test-consumer-group1")
    public void listen2(ConsumerRecord<String, String> record) {
        System.out.println("topic2开始收到消息------------>from"+record.partition());
        consumer(record);
    }

    /**
     * 监听topic2主题,单条消费
     */
    @KafkaListener(topics = {"my-topic"},groupId = "test-consumer-group1")
    public void listen3(ConsumerRecord<String, String> record) {
        System.out.println("topic3开始收到消息------------>from"+record.partition());
        consumer(record);
    }

    /**
     * 监听topic3和topic4,单条消费
     */
    @KafkaListener(topics = {"my-topic"},groupId = "test-consumer-group1")
    public void listen4(ConsumerRecord<String, String> record) {
        System.out.println("topic4开始收到消息------------>from"+record.partition());
        consumer(record);
    }

    /**
     * 监听topic3和topic5,单条消费
     */
    @KafkaListener(topics = {"my-topic"},groupId = "test-consumer-group2")
    public void listen5(ConsumerRecord<String, String> record) {
        System.out.println("topic5开始收到消息------------>from"+record.partition());
        consumer(record);
    }

    /**
     * 监听topicX,批量消费
     */
    @KafkaListener(topics = {"my-topic"}, containerFactory = "batchFactory",groupId = "test-consumer-group2")
    public void listenX(List<ConsumerRecord<String, String>> records) {
        batchConsumer(records);
    }

    /**
     * 单条消费
     */
    public void consumer(ConsumerRecord<String, String> record) {
        logger.debug("主题:{}, 内容: {}", record.topic(), record.value());
    }

    /**
     * 批量消费
     */
    public void batchConsumer(List<ConsumerRecord<String, String>> records) {
        records.forEach(record -> {
            System.out.println("topicX开始收到消息------------>from"+record.partition());
            consumer(record);
        });
    }
}