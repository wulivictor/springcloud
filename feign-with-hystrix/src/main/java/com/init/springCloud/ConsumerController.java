package com.init.springCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ConsumerController {

    @Autowired
    PersonClient PersonClient;

    @Autowired
    PersonClient2 PersonClient2;

    @RequestMapping(value = "/feign/{id}", method = RequestMethod.GET)
    public User getUserbyId(@PathVariable(value = "id") Long id) {
        return PersonClient.getUserbyId(id);
    }


    @RequestMapping(value = "/feign/userlist", method = RequestMethod.GET)
    public List<User> getUserlist(){
        return PersonClient.getAllUserList();
    }


    @RequestMapping(value = "/eureka/apps/{serviceName}", method = RequestMethod.GET)
    public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
        return this.PersonClient2.findmsg(serviceName);
    }

}