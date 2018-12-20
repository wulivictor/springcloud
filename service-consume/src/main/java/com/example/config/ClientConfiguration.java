package com.example.config;

import com.example.serviceconsume.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-20 14:01
 */

@Configuration
@RibbonClient(name = "foo", configuration = RibbonConfiguration.class)
public class ClientConfiguration {

}
