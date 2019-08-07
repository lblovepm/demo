package com.main.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/5/27 18:57
 */
@Component
public class RedisTestDemo {

    @Autowired
    private Jedis jedis;

    public void test(String[] args) {

        //String
        jedis.set("lb","lblove2019");
        System.out.println("string----->"+jedis.get("lb"));

        //list
        jedis.lpush("lb_list","lb_list_1");
        jedis.lpush("lb_list","lb_list_2");
        System.out.println("list----->"+jedis.lrange("lb_list",0,-1));

        //hash
        jedis.hset("lb_hash","name1","lb1");
        jedis.hset("lb_hash","name2","lb2");
        System.out.println("hash----->"+jedis.hget("lb_hash","name1"));

        //set
        jedis.sadd("lb_set","set1");
        jedis.sadd("lb_set","set2");
        System.out.println("set----->"+jedis.smembers("lb_set"));

        //z-set(sorted-set)
        jedis.zadd("lb_zset",1,"lb1");
        jedis.zadd("lb_zset",2,"lb2");
        jedis.zadd("lb_zset",3,"lb3");
        jedis.zadd("lb_zset",4,"lb4");
        jedis.zadd("lb_zset",2,"lb5");

        System.out.println("zset----->"+jedis.zrange("lb_zset",0,4));
    }
}
