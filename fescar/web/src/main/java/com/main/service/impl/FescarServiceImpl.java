package com.main.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fescar.spring.annotation.GlobalTransactional;
import com.main.facaed.AccountFacaed;
import com.main.facaed.OrderFacaed;
import com.main.service.FescarService;
import org.springframework.stereotype.Service;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:25
 */
@Service
public class FescarServiceImpl implements FescarService {

    @Reference
    private OrderFacaed orderFacaed;

    @Reference
    private AccountFacaed accountFacaed;

    @Override
    @GlobalTransactional(timeoutMills = 5000,name = "demo")
    public int placeOrder(String userId, String commodityCode, Integer count, Double money,Boolean exceptionFlag) {

        accountFacaed.reduceAccount(userId,money);

        orderFacaed.addOrder(userId,commodityCode,count,money);

        if(null == exceptionFlag || exceptionFlag){
            throw new RuntimeException("回滚--------------->");
        }

        return 2;
    }
}
