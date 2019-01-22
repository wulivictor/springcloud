package com.example.service;

import com.example.entity.Good;
import com.example.entity.Order;
import com.example.entity.RedisCount;
import com.example.mapper.GoodMapper;
import com.example.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2019-01-17 16:00
 */

@Transactional
@Service
public class BuyService {
    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(BuyService.class);


    public synchronized int rushOrder() {

        int count = this.getGoodCount();
        count--;
        Good good = new Good();
        if (count > 0) {
            good.setGoodId(1);
            good.setGoodCount(count);
            int update = goodMapper.updateByPrimaryKeySelective(good);

            if (update > 0) {

                Order order = new Order();
                order.setOrderId(UUID.randomUUID().toString());
                order.setOrderName("iPhonex");
                order.setOrderPrice(5656);
                order.setGoodCount(1);
                order.setGoodId(1);
                order.setRemarks("已买到");

                int insert = orderMapper.insertSelective(order);

                if (insert > 0) {
                    return 1;
                } else {
                    return 0;
                }

            }
        } else {
            return 0;
        }
        return 0;
    }

    public int getGoodCount() {
        return goodMapper.selectByPrimaryKey(1).getGoodCount();
    }


    /**
     * @Author: wuli
     * @Description: //TODO 使用redidis解决大并发问题
     * @Date:
     * @Param:
     * @return:
     **/

    public  int secondKill(int buycount) {


        RedisCount redisCount = RedisCount.getInstance();

        int tem = redisCount.getNum();
        if (tem - 1 > 0) {
            redisCount.setNum(tem - 1);
        } else {
            redisCount.setNum(1000);
            this.flushRedis();
        }

        List<String> orderLiser = (List<String>) redisTemplate.opsForValue().get("orderIdList");
        String count = String.valueOf(redisTemplate.opsForValue().get("count"));

        if ("null".equals(count)) {
            count = String.valueOf(this.getGoodCount());
            redisTemplate.opsForValue().set("count", count);
        }


        if (buycount >= Integer.parseInt(count) || Integer.parseInt(count) ==0) { //库存不够
            logger.warn("GoodCount is not enough!");
            this.flushRedis();

            return 0;

        } else { //购买


            if (null == orderLiser) {
                orderLiser = new Vector<>();
                redisTemplate.opsForValue().set("orderIdList", orderLiser);
            }

            int int_count = Integer.parseInt(count);
            int_count = int_count - buycount;

            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString());
            order.setGoodId(1);
            order.setOrderName("iPhoneX");
            order.setGoodCount(buycount);
            order.setOrderPrice(5656);
            order.setRemarks("success");

            orderLiser.add(order.getOrderId());

            redisTemplate.opsForValue().set("order" + order.getOrderId(), order);
            redisTemplate.opsForValue().set("count", int_count);
            redisTemplate.opsForValue().set("orderIdList", orderLiser);

            return buycount;

        }

    }


    /**
     * @Author: wuli
     * @Description: //TODO 清空缓存
     * @Date:
     * @Param:
     * @return:
     **/


    public boolean flushRedis() {
        List<String> list = new Vector<>();

        list = (Vector) redisTemplate.opsForValue().get("orderIdList");
        if (list == null){
            return false;
        }
        redisTemplate.delete("orderIdList");

        for (String s : list) {

            Order order = new Order();
            String key = "order" + s;
            order = (Order) redisTemplate.opsForValue().get(key);

            orderMapper.insert(order);
            redisTemplate.delete(key);

        }


        return true;
    }
}