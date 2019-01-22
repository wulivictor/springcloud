package com.example.test;

import com.example.SpringApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestRedis {
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//
//    @Autowired
//
//    private GoodMapper goodMapper;
//    @Test
//    public void testSet() {
//
//
//        this.get();
//
//
//    }
//    private  String lockedPrefix;
//    @CacheLock(lockedPrefix = "learn-")
//    public void get(){
//
//        System.out.println(lockedPrefix);
//
//    }



}