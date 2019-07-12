package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/11 14:37
 */
@RestController
@RequestMapping("/aop")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public JSONObject test(String flag){
        testService.test(flag);

        JSONObject resultJson = new JSONObject();
        return resultJson;
    }
}
