package com.main.consumer;

import com.main.domain.UserDO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/15 16:06
 */
@Service
@RocketMQMessageListener(topic = "userDOTopic", selectorExpression = "tag1", consumerGroup = "my-group1")
public class UserDOConsumer implements RocketMQListener<UserDO> {

    @Override
    public void onMessage(UserDO userDO) {
        System.out.println("userId:  "+userDO.getUserId()
                +"/n userName:  "+userDO.getUserName()
                +"/n userAge:  "+userDO.getUserAge()
                +"/n userSex: "+userDO.getUserSex());
    }

}
