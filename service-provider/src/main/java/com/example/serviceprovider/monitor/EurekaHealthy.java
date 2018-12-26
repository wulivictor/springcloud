package com.example.serviceprovider.monitor;

import com.example.serviceprovider.controller.UserController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @project; springcloud
 * @description: eureka健康监测
 * @author: wuli
 * @create: 2018-12-26 10:44
 */
@Component
public class EurekaHealthy implements HealthIndicator {
    @Override
    public Health health() {
        if (UserController.isCanLinkDb){
            return new Health.Builder(Status.UP).build();
        }else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
