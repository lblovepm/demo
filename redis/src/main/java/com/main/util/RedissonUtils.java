package com.main.util;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/5/27 19:01
 */
public class RedissonUtils {

    /**
     * 获取redisson实例
     * @return
     */
    public static RedissonClient getRedissonClientInstance(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        return Redisson.create(config);
    }

    /**
     * 获取锁
     * @param key
     * @return
     */
    public static RLock getLock(String key) throws InterruptedException {
        RLock lock = RedissonUtils.getRedissonClientInstance().getLock(key);

        Boolean tryResult = lock.tryLock(1L,2L,TimeUnit.SECONDS);
        lock.tryLock(1L,1L,TimeUnit.SECONDS);
        lock.isLocked();
        return lock;
    }

    public static void main(String[] args) throws InterruptedException {
        RLock lock = RedissonUtils.getRedissonClientInstance().getLock("111");
        System.out.println("lock---->"+lock.isLocked());
        System.out.println("tryLock----->"+lock.tryLock(1L,1L,TimeUnit.SECONDS));
        System.out.println("lock---->"+lock.isLocked());
        lock.unlock();
    }
}
