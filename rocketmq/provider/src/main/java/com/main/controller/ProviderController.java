package com.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.main.domain.UserDO;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/15 15:38
 */

@RestController
@RequestMapping("/rocket")
public class ProviderController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    private final static String USER_DO_TOPIC = "userDOTopic";

    private final static String MESSAGE_TOPIC = "messageTopic";

    @RequestMapping("/rocket")
    public JSONObject pushMessage(){

        //payload方式发送
        UserDO userDO1 = new UserDO();
        userDO1.setUserId(1);
        userDO1.setUserName("LB先生1");
        userDO1.setUserAge(26);
        userDO1.setUserSex(1);
        SendResult sendResult1 = rocketMQTemplate.syncSend(USER_DO_TOPIC,userDO1);
        System.out.println("msgId1:  "+sendResult1.getMsgId()+"/n sendStatus1:  "+sendResult1.getSendStatus());

        //Message方式发送
        UserDO userDO2 = new UserDO();
        userDO2.setUserId(2);
        userDO2.setUserName("LB先生2");
        userDO2.setUserAge(27);
        userDO2.setUserSex(2);
        Message<UserDO> userDOMessage = MessageBuilder.withPayload(userDO2).build();
        SendResult sendResult2 = rocketMQTemplate.syncSend(MESSAGE_TOPIC,userDOMessage);
        System.out.println("msgId2:  "+sendResult2.getMsgId()+"/n sendStatus2:  "+sendResult2.getSendStatus());

        return null;
    }
}
