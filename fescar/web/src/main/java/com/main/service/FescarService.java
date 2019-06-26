package com.main.service;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:22
 */
public interface FescarService {

    int placeOrder(String userId, String commodityCode, Integer count, Double money, Boolean exceptionFlag);
}
