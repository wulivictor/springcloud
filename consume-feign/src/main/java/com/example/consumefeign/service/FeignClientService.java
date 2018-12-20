package com.example.consumefeign.service;
import com.example.consumefeign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SERVICE-PROVIDER")
public interface FeignClientService {

    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id);

}
