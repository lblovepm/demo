package com.config;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopConfig {

    /**
     * 异常增强
     * @param proceedingJoinPoint
     */
    @Around("execution(* com.controller.*.*(..))")
    public JSONObject paramValidate(ProceedingJoinPoint proceedingJoinPoint){
        try{
            return (JSONObject) proceedingJoinPoint.proceed();
        }catch (Throwable e){
            JSONObject errorJson = new JSONObject();
            errorJson.put("flag","error");
            errorJson.put("message",e.getMessage());
            return errorJson;
        }
    }

}