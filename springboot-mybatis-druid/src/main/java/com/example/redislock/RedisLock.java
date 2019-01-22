package com.example.redislock;

import com.example.annotation.CacheLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by VitoYi and construct by wuli on 2018/4/5.
 */
@Component
public class RedisLock {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key   商品id
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {     // 如果不存在则新增,返回true,存在则不新增false   对应redis setnx方法
            return true;
        }

        //避免死锁，且只让一个线程拿到锁
        String currentValue = redisTemplate.opsForValue().get(key);

        //如果锁过期了, 一般是这个操作发生异常了，那就将之前的锁清掉
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValues = redisTemplate.opsForValue().getAndSet(key, value);   // getset 把新的值赋值给key，返回之前的value
            /*
               只会让一个线程拿到锁
               如果旧的value和currentValue相等，只会有一个线程达成条件，因为第二个线程拿到的oldValue已经和currentValue不一样了
             */
            if (!StringUtils.isEmpty(oldValues) && oldValues.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
                logger.info("『redis分布式锁』解锁成功");
            }
        } catch (Exception e) {
            logger.error("『redis分布式锁』解锁异常，{}", e);
        }
    }
}