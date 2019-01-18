package com.example.entity;

public class RedisCount {


    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public static RedisCount getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(RedisCount ourInstance) {
        RedisCount.ourInstance = ourInstance;
    }

    private int num = 1000;
    private static RedisCount ourInstance = new RedisCount();

    public static RedisCount getInstance() {
        return ourInstance;
    }

    private RedisCount() {
    }
}
