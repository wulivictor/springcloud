package com.init.springCloud;

import com.init.springCloud.config.RuleConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

@FeignClient(name = "SERVICE-PROVIDER", configuration = RuleConfig.class, fallback = ClientFallBack.class) //封装http服务
public interface PersonClient {

    //这里的地址对应的是服务提供者的真实地址  不支持RequestMapper    @PathVariable要变成@Param
    @RequestLine("GET /simple/{id}")
    public User getUserbyId(@Param(value = "id") Long id);

    // 获取所有的user
    @RequestLine("GET /simple/userlist")
    public List<User> getAllUserList();

}