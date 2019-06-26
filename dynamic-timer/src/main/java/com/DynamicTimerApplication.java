package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DynamicTimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicTimerApplication.class, args);
    }

}
