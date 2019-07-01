package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.LB
 * @description: ElasticSearch
 * @date 2019/7/1 11:18
 */
@SpringBootApplication
public class ElasticSearchBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchBootApplication.class, args);
        System.out.println("================Elastic-Search===============");
    }
}
