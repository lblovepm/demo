package com.main.consumer;

import com.main.domain.UserDO;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/15 16:06
 */
@Service
@RocketMQMessageListener(topic = "messageTopic", selectorExpression = "tag1", consumerGroup = "my-group1")
public class MessageConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        System.out.println("======================prepareStart===================");
    }

    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] messageBody = messageExt.getBody();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(messageBody);
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(object instanceof UserDO){
            UserDO userDO = (UserDO) object;
            System.out.println("userId:  "+userDO.getUserId()
                            +"/n userName:  "+userDO.getUserName()
                            +"/n userAge:  "+userDO.getUserAge()
                            +"/n userSex: "+userDO.getUserSex());
        }

    }

}
