package com.init.springCloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("SERVICE-PROVIDER")
public interface PersonClient {
//
//	@RequestMapping(method = RequestMethod.GET, value = "/hello/{name}")
//	String sayHello(@PathVariable("name") String name);
//
//	@RequestMapping(method = RequestMethod.GET, value = "/search/{id}")
//	Person getPersonById(@PathVariable("id") Integer id);


	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable(value = "id") Long id);
}