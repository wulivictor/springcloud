package com.example.test;
import javax.annotation.Resource;

import com.example.SpringApplication;
import com.example.entity.Good;
import com.example.mapper.GoodMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

@SpringBootTest(classes = SpringApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestRedis {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Autowired

    private GoodMapper goodMapper;
    @Test
    public void testSet() {

//        this.redisTemplate.opsForValue().set("study", "java");
//        System.out.println(this.redisTemplate.opsForValue().get("study"));

//        Good good = new Good();
//        good = goodMapper.selectByPrimaryKey(1);
//
//        this.redisTemplate.opsForValue().set("good",good);
//       int a = (int) this.redisTemplate.opsForValue().get("good");


        List<String> list = new Vector<>();

        list = (Vector)redisTemplate.opsForValue().get("orderIdList");
        System.out.println(list.size());
    }
}