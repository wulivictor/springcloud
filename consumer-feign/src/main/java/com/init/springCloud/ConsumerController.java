package com.init.springCloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConsumerController {
	
	@Autowired PersonClient PersonClient;
	
//	@RequestMapping(method = RequestMethod.GET, value = "/router/{name}",
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public String router(@PathVariable String name){
//		return PersonClient.sayHello(name);
//	}
//
//	@RequestMapping(method = RequestMethod.GET, value = "/find/{id}",
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public Person getPersonById(@PathVariable Integer id){
//		return PersonClient.getPersonById(id);
//	}
//


	@RequestMapping(value = "feign/{id}", method = RequestMethod.GET)
	public User getUserbyId(@PathVariable(value = "id") Long id){
		return PersonClient.getUserById(id);

	}
	
}