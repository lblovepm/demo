package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/6/3 19:38
 */
@EnableDubbo
@SpringBootApplication
@MapperScan(basePackages="com.main.mapper")
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
        System.out.println("================TSTORAGE===============");
    }
}
