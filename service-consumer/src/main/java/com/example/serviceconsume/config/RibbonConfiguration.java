package com.example.serviceconsume.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @project; springcloud
 * @description: ribbon 的额外配置
 * @author: wuli
 * @create: 2018-12-20 11:50
 */
@Configuration
// 为foo的客户端提供 一个配置
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        // 负载均衡规则，改为随机
        return new RandomRule();
    }
}
