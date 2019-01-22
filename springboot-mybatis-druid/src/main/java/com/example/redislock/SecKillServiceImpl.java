package com.example.redislock;

import ch.qos.logback.classic.gaffer.GafferConfigurator;
import com.alibaba.druid.util.StringUtils;
import com.example.annotation.CacheLock;
import com.example.entity.Good;
import com.example.entity.Order;
import com.example.entity.RedisCount;
import com.example.mapper.GoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.xml.ws.Action;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @project; springcloud
 * @description: 秒杀实现类
 * @author: wuli
 * @create: 2019-01-21 15:47
 */

@Component
public class SecKillServiceImpl {
    private final long expire_time = 5000L;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private RedisLock redisLock;

    Logger logger = LoggerFactory.getLogger(SecKillServiceImpl.class);


    public int secKill(int goodId, int buycount) {

        String value = String.valueOf(System.currentTimeMillis() + expire_time);

        boolean result = redisLock.lock("lock_id_" + goodId, value);

        //加锁
        if (result) {

            Good good = new Good();
            good = this.getGood(goodId);
            List<String> idlist = (List<String>) redisTemplate.opsForValue().get("idlist");

            String count = String.valueOf(redisTemplate.opsForValue().get("count"));

            if (StringUtils.isEmpty(count)) {
                count = String.valueOf(good.getGoodCount());
            }

            if (null == idlist) {
                idlist = new Vector<>();
            }


            int int_count = Integer.parseInt(count);
            int_count = int_count - buycount;

            Order order = new Order();
            String id = UUID.randomUUID().toString();
            order.setOrderId(id);
            order.setGoodId(good.getGoodId());
            order.setOrderName(good.getGoodName());
            order.setGoodCount(buycount);
            order.setOrderPrice(good.getGoodPrice());
            order.setRemarks("buy success" + id);

            idlist.add(id);

            redisTemplate.opsForValue().set("count", int_count);
            redisTemplate.opsForValue().set("idlist", idlist);
            redisTemplate.opsForValue().set(id, order);

            redisLock.unlock("lock_id_" + goodId, value);
            return buycount;
        }
        logger.warn("this seckill thread unlocked failed!");
        return 0;

    }


    private Good getGood(int goodId) {

        return goodMapper.selectByPrimaryKey(goodId);
    }


}
