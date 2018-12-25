package com.init.springCloud;

import com.init.springCloud.config.EurekaConfig2;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "xxx", url = "http://localhost:8761", configuration = EurekaConfig2.class)
public interface PersonClient2 {

    @RequestLine("GET /eureka/apps/{serviceName}")
    public String findmsg(@Param(value = "serviceName") String serviceName);

}
