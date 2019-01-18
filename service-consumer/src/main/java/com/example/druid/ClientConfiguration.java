package com.example.druid;

import com.example.serviceconsume.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @project; springcloud
 * @description:
 * @author: wuli
 * @create: 2018-12-20 14:01
 */
// 这个配置类不能写在app.jaca同目录，不能被ComponentsScan扫描  对service-provider的工程提供
@Configuration
@RibbonClient(name = "SERVICE-PROVIDER", configuration = RibbonConfiguration.class)
public class ClientConfiguration {

}
