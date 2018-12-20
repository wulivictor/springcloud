package com.example.consumefeign.controller;

import com.example.consumefeign.entity.User;
import com.example.consumefeign.service.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-20 14:37
 */
@RestController
public class ConsumerFeignController {


    @Autowired
    public FeignClientService feignClientService;


    @RequestMapping(value = "/feignconsumer/{id}",method = RequestMethod.GET)
    public User getConsumer (@PathVariable String id ) {

        return  feignClientService.getUserById(Long.parseLong(id));

    }

    @GetMapping(value = "/hello")
    public String helloworld (){
        return "hello world";
    }

}
