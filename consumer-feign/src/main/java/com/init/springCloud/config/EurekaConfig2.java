package com.init.springCloud.config;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-24 09:17
 */
@Configuration
public class EurekaConfig2 {


    // 转发规则
    @Bean
    public Contract feignContractg() {
        return new feign.Contract.Default();
    }

    // 日志规则
    @Bean
    Logger.Level feign() {
        return Logger.Level.FULL;
    }

    // 访问限权
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("wuli", "pwd123456");
    }
}
