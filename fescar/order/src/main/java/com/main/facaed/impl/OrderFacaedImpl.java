package com.main.facaed.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.main.entity.TOrder;
import com.main.facaed.OrderFacaed;
import com.main.facaed.StorageFacaed;
import com.main.mapper.TOrderMapper;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:10
 */

@Service
public class OrderFacaedImpl implements OrderFacaed {

    @Reference
    private StorageFacaed storageFacaed;

    @Resource
    private TOrderMapper tOrderMapper;

    @Override
    public int addOrder(String userId, String commodityCode, Integer count, Double money) {
        storageFacaed.reduceStorage(commodityCode,count);

        TOrder tOrder = new TOrder();
        tOrder.setAmount(money);
        tOrder.setCommodityCode(commodityCode);
        tOrder.setCount(count);
        tOrder.setUserId(userId);
        tOrder.setOrderNo(UUID.randomUUID().toString());
        tOrderMapper.insert(tOrder);

        return 2;
    }
}
