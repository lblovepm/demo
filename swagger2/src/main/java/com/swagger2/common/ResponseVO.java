package com.swagger2.common;

import com.swagger2.constants.ResponseCodeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class ResponseVO<T> {

    @ApiModelProperty(value = "返回的code【0:成功响应;1:失败响应】",position = 0)
    private Integer responseCode;

    @ApiModelProperty(value = "返回的实体",position = 1)
    private T entity;

    public ResponseVO (Integer responseCode){
        this.responseCode = responseCode;
    }

    public ResponseVO (Integer responseCode,T entity){
        this.responseCode = responseCode;
        this.entity = entity;
    }

    public static <T> ResponseVO<T> createResponseBySuccess(T entity){
        return new ResponseVO<T>(ResponseCodeConstant.SUCCESS,entity);
    }

    public static <T> ResponseVO<T> createResponseByFail(){
        return new ResponseVO<T>(ResponseCodeConstant.FAIL);
    }
}
