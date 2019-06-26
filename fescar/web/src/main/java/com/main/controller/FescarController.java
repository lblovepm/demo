package com.main.controller;

import com.main.service.FescarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:54
 */

@RestController
@RequestMapping("/fescar")
public class FescarController {

    @Autowired
    private FescarService fescarService;

    @RequestMapping("/test")
    public void test(String userId, String commodityCode, Integer count, Double money,Boolean exceptionFlag){
        fescarService.placeOrder(userId,commodityCode,count,money,exceptionFlag);
    }
}
