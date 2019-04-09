package com.example.serviceprovider.controller;

import com.example.serviceprovider.entity.User;
import com.example.serviceprovider.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-19 10:10
 */
@RestController
public class UserController {

    Logger Logger=LoggerFactory.getLogger(UserController.class);

    // 健康监测
    public static Boolean isCanLinkDb = true;


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Long id) {
        User user = this.userRepository.getOne(id);
        System.out.println(user);
        return user;
    }


    @GetMapping("/simple/userlist")
    public List<User> userlist() {
        return userRepository.getUserbysql();

    }


    //可以设置此应用的健康状态    state，负载均衡
    @RequestMapping(value = "/linkDb/{can}", method = RequestMethod.GET)
    public void LinkDb(@PathVariable Boolean can) {
        isCanLinkDb = can;
        Logger.debug("isCanLinkDb:\t" + isCanLinkDb.toString());

    }

}
