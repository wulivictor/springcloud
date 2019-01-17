package com.example.service;

import com.example.entity.Good;
import com.example.entity.Order;
import com.example.mapper.GoodMapper;
import com.example.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public int rushOrder() {

        int count = this.getGoodCount();
        count--;
        Good good = new Good();
        if (count > 0) {
            good.setGoodId(1);
            good.setGoodCount(count);
            int update = goodMapper.updateByPrimaryKeySelective(good);

            if (update > 0) {

                Order order = new Order();
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

    public int getGoodCount(){
        return  goodMapper.selectByPrimaryKey(1).getGoodCount();
    }
}
