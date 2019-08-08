package com.swagger2.controller;

import com.swagger2.common.ResponseVO;
import com.swagger2.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.LB
 * @description: Swagger2Controller
 * @date 2019/8/8 11:18
 */
@Api(value = "UserController",tags = "用户信息控制器")
@RestController
@RequestMapping("/swagger2")
public class UserController {

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/get_user_list")
    public ResponseVO<List<User>> getUserList(){

        List<User> userList = new ArrayList<>();

        User user;
        for(int i = 0;i < 10;i++){
            user = new User();
            user.setUserId(i);
            user.setUserName("LB"+i);
            user.setAge(25+i);
            user.setEducationalLevel(5);
            user.setSex(1);
            user.setCreateTime(new Date());

            userList.add(user);
        }

        return ResponseVO.createResponseBySuccess(userList);
    }
}
