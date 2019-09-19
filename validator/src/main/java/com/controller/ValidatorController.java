package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.model.ValidatorModel;
import com.validateGroup.UpdateGroup;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 效验demo
 *
 * @Validated: 用在方法入参上无法单独提供嵌套验证功能。不能用在成员属性（字段）上，也无法提示框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证。
 *
 * @Valid: 用在方法入参上无法单独提供嵌套验证功能。能够用在成员属性（字段）上，提示验证框架进行嵌套验证。能配合嵌套验证注解@Valid进行嵌套验证
 */
@RestController
public class ValidatorController {

    /**
     * 基本效验
     * @param validatorModel
     * @param bindingResult
     * @return
     */
    @RequestMapping("/test1")
    public JSONObject test1(@Valid ValidatorModel validatorModel, BindingResult bindingResult){
        JSONObject resultJson = new JSONObject();

        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            resultJson.put("flag",false);
            resultJson.put("message",fieldError.getDefaultMessage());
        }else{
            resultJson.put("flag",true);
            resultJson.put("result",validatorModel);
        }

        return resultJson;
    }

    /**
     * 分组效验 【id字段 更新时需要,插入时不需要】
     * @param validatorModel
     * @return
     */
    @RequestMapping("/test2")
    public JSONObject test2(@Validated(value = UpdateGroup.class) ValidatorModel validatorModel){
        JSONObject resultJson = new JSONObject();
        resultJson.put("flag",true);
        resultJson.put("result",validatorModel);

        System.out.print(1/0);

        return resultJson;
    }
}
