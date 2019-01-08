package com.example.hystrix.controller;

import com.example.hystrix.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2019-01-08 16:43
 */
@RestController
public class HystrixController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping(value = "movie/{id}")
    @HystrixCommand(fallbackMethod = "findByIdCallBack")
    public User findUserById(@PathVariable Long id) {

        return restTemplate.getForObject("http://SERVICE-PROVIDER/simple/" + id, User.class);
    }

    // 上面请求  异常的回调执行函数
    public User findByIdCallBack(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
    }





}
