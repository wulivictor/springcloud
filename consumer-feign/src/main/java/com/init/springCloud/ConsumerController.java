package com.init.springCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {

    @Autowired
    PersonClient PersonClient;

    @RequestMapping(value = "feign/{id}", method = RequestMethod.GET)
    public User getUserbyId(@PathVariable(value = "id") Long id) {
        return PersonClient.getUserById(id);

    }

}