package com.main.facaed;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:02
 */
public interface OrderFacaed {

    int addOrder(String userId, String commodityCode, Integer count, Double money);
}
