package com.init.springCloud.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-21 16:25
 */

@Configuration // ribbon和feign的配置类都不能放到与启动类
public class RuleConfig {

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

}
