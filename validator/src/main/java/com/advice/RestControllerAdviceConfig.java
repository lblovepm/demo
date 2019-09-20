package com.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常配置
 *
 * @RestControllerAdvice和@ControllerAdvice的区别: @RestControllerAdvice返回json格式的数据，@ControllerAdvice会跳转到指定页面
 *
 * @RestControllerAdvice和@ControllerAdvice的使用:
 * 1、结合方法型注解@ExceptionHandler，用于捕获Controller中抛出的指定类型的异常，从而达到不同类型的异常区别处理的目的；
 * 2、结合方法型注解@InitBinder，用于request中自定义参数解析方式进行注册，从而达到自定义指定格式参数的目的；
 * 3、结合方法型注解@ModelAttribute，表示其标注的方法将会在目标Controller方法执行之前执行
 */
@RestControllerAdvice
public class RestControllerAdviceConfig {

    @ExceptionHandler({Throwable.class})
    public JSONObject globalExceptionHandler(Throwable e){
        JSONObject resultJson = new JSONObject();
        resultJson.put("code","error");

        if(e instanceof BindingResult){
            //TODO @Valid或者@Validated效验产生的异常
            BindingResult bindingResult = (BindingResult) e;
            resultJson.put("message",bindingResult.getFieldError().getDefaultMessage());
        }else{
            resultJson.put("message",e.getLocalizedMessage());
        }
        return resultJson;
    }

}
