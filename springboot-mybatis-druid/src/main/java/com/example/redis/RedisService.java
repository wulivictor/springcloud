//package com.example.redis;
//
//import com.alibaba.druid.util.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import sun.security.util.KeyUtil;
//
//
//@Component
//public class RedisService {
//
//    private Logger logger = LoggerFactory.getLogger(RedisService.class);
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//    /***
//     * 加锁
//     * @param key
//     * @param value 当前时间+超时时间
//     * @return 锁住返回true
//     */
//    public boolean lock(String key, String value) {
//        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {//setNX 返回boolean
//            return true;
//        }
//        //如果锁超时 ***
//        String currentValue = stringRedisTemplate.opsForValue().get(key);
//        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
//            //获取上一个锁的时间
//            String oldvalue = stringRedisTemplate.opsForValue().getAndSet(key, value);   // 获取原来的值并重新赋值
//            if (!StringUtils.isEmpty(oldvalue) && oldvalue.equals(currentValue)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /***
//     * 解锁
//     * @param key
//     * @param value
//     * @return
//     */
//
//    public void unlock(String key, String value) {
//        try {
//            String currentValue = stringRedisTemplate.opsForValue().get(key);
//            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
//                stringRedisTemplate.opsForValue().getOperations().delete(key);
//            }
//        } catch (Exception e) {
//            logger.error("解锁异常");
//        }
//    }
//
//
//
//    private static final int TIMEOUT= 10*1000;
//    @Transactional
//    public void orderProductMockDiffUser(String productId){
//        long time = System.currentTimeMillis()+TIMEOUT;
//        if(!redislock.lock(productId,String.valueOf(time)){
//            throw new SellException(101,"换个姿势再试试")
//        }
//        //1.查库存
//        int stockNum  = stock.get(productId);
//        if(stocknum == 0){
//            throw new SellException(ProductStatusEnum.STOCK_EMPTY);
//            //这里抛出的异常要是运行时异常，否则无法进行数据回滚，这也是spring中比较基础的
//        }else{
//            //2.下单
//            orders.put(KeyUtil.genUniqueKey(),productId);//生成随机用户id模拟高并发
//            sotckNum = stockNum-1;
//            try{
//                Thread.sleep(100);
//            } catch (InterruptedExcption e){
//                e.printStackTrace();
//            }
//            stock.put(productId,stockNum);
//        }
//        redisLock.unlock(productId,String.valueOf(time));
//    }
//}
//
