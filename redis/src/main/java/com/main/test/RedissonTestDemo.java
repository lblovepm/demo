package com.main.test;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedissonTestDemo {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取锁
     * @param key
     * @return
     */
    public RLock getLock(String key) throws InterruptedException {
        RLock lock = redissonClient.getLock(key);

        Boolean tryResult = lock.tryLock(1L,2L, TimeUnit.SECONDS);
        lock.tryLock(1L,1L,TimeUnit.SECONDS);
        lock.isLocked();
        return lock;
    }

    public void main(String[] args) throws InterruptedException {
        RLock lock = redissonClient.getLock("");

        System.out.println("lock---->"+lock.isLocked());
        System.out.println("tryLock----->"+lock.tryLock(1L,1L,TimeUnit.SECONDS));
        System.out.println("lock---->"+lock.isLocked());
        lock.unlock();
    }
}
