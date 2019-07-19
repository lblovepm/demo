package com.hadoop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mr.LB
 * @description: HadoopSpringBootApplication
 * @date 2019/7/19 13:57
 */
@SpringBootApplication
public class HadoopSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopSpringBootApplication.class, args);
        System.out.println("================HADOOP START UP SUCCESS===============");
    }

}
