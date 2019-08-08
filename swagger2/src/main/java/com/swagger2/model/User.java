package com.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel
public class User {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "用户年龄")
    private Integer age;

    @ApiModelProperty(value = "用户性别",notes = "0:未知;1:男;2:女")
    private Integer sex;

    @ApiModelProperty(value = "用户性别",notes = "0:小学未毕业;1:小学;2:初中;3:高中;4:大专;5:本科;6:硕士;7:博士;8:博士后")
    private Integer educationalLevel;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
