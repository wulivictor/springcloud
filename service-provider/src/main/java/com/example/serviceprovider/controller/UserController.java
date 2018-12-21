package com.example.serviceprovider.controller;

import com.example.serviceprovider.entity.User;
import com.example.serviceprovider.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Long id){
            User user = new User();
            user = this.userRepository.getOne(id);
        System.out.println(user);
        return user;
    }


    @GetMapping("/simple/userlist")
    public List<User> userlist(){
        return  userRepository.getUserbysql();

    }

}
