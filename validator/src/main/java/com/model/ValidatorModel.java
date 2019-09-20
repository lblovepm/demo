package com.model;

import com.validateGroup.AddGroup;
import com.validateGroup.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ValidatorModel {

    @NotNull(groups = {UpdateGroup.class},message = "id不能为空")
    private Integer id;

    @NotNull(groups = {UpdateGroup.class, AddGroup.class},message = "名字不能为空")
    @Length(groups = {UpdateGroup.class, AddGroup.class},max = 6,min = 2,message = "名字长度在2~6之间")
    private String name;

    @NotNull(groups = {UpdateGroup.class, AddGroup.class},message = "时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
