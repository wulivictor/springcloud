package com.init.springCloud;

import com.init.springCloud.config.RuleConfig;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "SERVICE-PROVIDER",configuration = RuleConfig.class) //封装http服务
public interface PersonClient {

	//这里的地址对应的是服务提供者的真实地址
	@RequestLine("GET /simple/{id}")
	public User getUserbyId(@Param(value = "id") Long id);

}