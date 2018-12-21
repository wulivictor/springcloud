package com.init.springCloud;

import com.init.springCloud.config.RuleConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SERVICE-PROVIDER",configuration = RuleConfig.class)
public interface PersonClient {
	
	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable(value = "id") Long id);

}