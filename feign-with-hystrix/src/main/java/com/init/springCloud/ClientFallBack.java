package com.init.springCloud;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @project; springcloud
 * @description: feign客户端的降级解决方案
 * @author: wuli
 * @create: 2019-01-09 14:34
 */
@Component
public class ClientFallBack implements PersonClient{

    //降级处理
    @Override
    public User getUserbyId(Long id) {

        User user = new User();
        user.setId(0L);

        return user;
    }

    @Override
    public List<User> getAllUserList() {
        return null;
    }
}
