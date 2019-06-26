package com.main;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/4 19:21
 */
@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages="com.main.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        System.out.println("================WEB===============");
    }
}
