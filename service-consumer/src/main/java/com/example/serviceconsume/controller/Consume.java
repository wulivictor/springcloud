package com.example.serviceconsume.controller;

import com.example.serviceconsume.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-19 11:02
 */
@RestController
public class Consume {

    @Autowired
    private  RestTemplate restTemplate;

//    @Value("${user.servicePath}")
//    private String servicePath;

    @GetMapping("/ribbon/{id}")
    public ResponseEntity<User> findbyId(@PathVariable(value = "id") Long id ){
        //远程调用 RPC
        // SERVICE-PROVIDER   这个东西是 vitural ip  这个虚拟的ip是微服务的服务id，在配置文件中配置的。
        return restTemplate.getForEntity("http://SERVICE-PROVIDER/simple/" + id, User.class);
        // restTemplate.patchForObject() 请求 post请求
    }
}
