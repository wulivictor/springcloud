package com.example.hystrix.controller;

import com.example.hystrix.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
        System.out.println("》》》消费者");

        return restTemplate.getForObject("http://SERVICE-PROVIDER/simple/" + id, User.class);
    }

    // 上面请求  异常的回调执行函数
    public User findByIdCallBack(Long id) {
        User user = new User();
        user.setId(0L);
        return user;
    }


/**
    THREAD(线程隔离）：使用该方式，HystrixCommand将会在单独的线程上执行，并发请求受线程池中线程数量的限制。
            SEMAPHORE（信号量隔离）：使用该方式，HystrixCommand将会在调用线程上执行，开销相对较小，并发请求受到信号量个数的限制。
            Hystrix中默认并且推荐使用线程隔离（THREAD)，因为这种方式有一个除网络超时以外的额外保护。
   一般来说，只有当调用负载异常高时（例如每个实例每秒调用数百次）才需要信号量隔离，因为这种场景下使用THREAD开销会比较高。信号量隔离一般仅适用于非网络调用的隔离。
**/



}
