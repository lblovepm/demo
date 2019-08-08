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

    @ApiModelProperty(value = "用户id",position = 0)
    private Integer userId;

    @ApiModelProperty(value = "用户姓名",position = 1)
    private String userName;

    @ApiModelProperty(value = "用户年龄",position = 2)
    private Integer age;

    @ApiModelProperty(value = "用户性别",notes = "0:未知;1:男;2:女",position = 3)
    private Integer sex;

    @ApiModelProperty(value = "用户性别",notes = "0:小学未毕业;1:小学;2:初中;3:高中;4:大专;5:本科;6:硕士;7:博士;8:博士后",position = 4)
    private Integer educationalLevel;

    @ApiModelProperty(value = "创建时间",position = 5)
    private Date createTime;
}
