package com.example.controller;

import com.example.entity.Good;
import com.example.mapper.GoodMapper;
import com.example.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2019-01-17 14:46
 */

@RestController
@RequestMapping("/")
@Transactional
public class GoodController {


    @Autowired
     private BuyService buyService;

    @RequestMapping("count")
    public int getJson(){

        return  buyService.getGoodCount();
    }


    @RequestMapping("buy")
    public int buyGood(){
        return buyService.rushOrder();
    }


    @RequestMapping("secKill")
    public int SecKill (){

        int buycount =(int) Math.round(5*Math.random());

        return this.buyService.secondKill(buycount);
    }
}
