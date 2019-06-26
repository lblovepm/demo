package com.main.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/15 15:50
 */

@Setter
@Getter
public class UserDO implements Serializable {

    private Integer userId;

    private String userName;

    private Integer userAge;

    private Integer userSex;

}
