package com.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Mr.LB
 * @description: 切面配置
 * @date 2019/7/11 14:09
 */
@Component
@Aspect
public class AspectConfig {

//    @Pointcut("execution(* com.*.*(..))")
    public void pointCut(){}

    /**
     * 异常增强
     * @param ex
     */
    @AfterThrowing(throwing="ex",pointcut = "execution(* com.controller.*.*(..))")
    public JSONObject paramValidate(Throwable ex){
        JSONObject resultJson = new JSONObject();

        resultJson.put("flag","error");
        resultJson.put("message",ex.getMessage());

        return resultJson;
    }

}
