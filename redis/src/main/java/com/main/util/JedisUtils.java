package com.main.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/5/27 18:56
 */
public class JedisUtils {

    public static Jedis getJedisInstance(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        return jedisPool.getResource();
    }
}
