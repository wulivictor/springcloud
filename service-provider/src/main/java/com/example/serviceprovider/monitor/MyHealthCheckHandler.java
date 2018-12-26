package com.example.serviceprovider.monitor;


import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.health.Status;

/**
 * @project; springcloud
 * @description: 健康通知  > 通知eureka服务端
 * @author: wuli
 * @create: 2018-12-26 11:28
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    EurekaHealthy eurekaHealthy;

    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status status = eurekaHealthy.health().getStatus();
        if (status.equals(Status.UP)) {
            return InstanceInfo.InstanceStatus.UP;
        } else if (status.equals(Status.DOWN)) {
            return InstanceInfo.InstanceStatus.DOWN;

        } else {
            return null;
        }
    }
}
