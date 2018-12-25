package com.init.springCloud;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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


    @RequestMapping(value = "/eureka/apps/{serviceName}", method = RequestMethod.GET)
    public String findServiceInfoFromEurekaByServiceName(@PathVariable String serviceName) {
        return this.PersonClient2.findmsg(serviceName);
    }

}