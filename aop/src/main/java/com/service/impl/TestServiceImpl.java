package com.service.impl;

import com.annotation.LogOutput;
import com.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/11 14:39
 */
@Service
public class TestServiceImpl implements TestService {

    @LogOutput
    public int test(String flag) {

        if(flag.equals("0")){
            System.out.println(1/0);
        }else if(flag.equals("1")){
            return 1;
        }else {
            return 2;
        }

        return 0;
    }
}
