package com.main.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.LB
 * @description: redission配置
 * @date 2019/5/27 17:31
 */
@Configuration
@EnableCaching
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedissonClient getRedissonClient(){
        String address = new StringBuffer("redis://").append(host).append(":").append(port).toString();
        Config config = new Config();
        config.useSingleServer().setAddress(address);

        return Redisson.create(config);
    }
}
