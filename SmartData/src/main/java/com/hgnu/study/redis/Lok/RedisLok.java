package com.hgnu.study.redis.Lok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author zj
 * @Description
 *      setNx实现分布式锁
 * @Date 2021/3/5 14:56
 */
public class RedisLok {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "lockKey";
        String redisId = UUID.randomUUID().toString();
        /**
         *  问题一:
         *  stringRedisTemplate.delete(lockKey);//释放点这个锁，如果在这段代码出现异常了的话， 这个锁没有释放掉，别的线程进来的话拿不到锁，会陷入一种死锁状态。
         *  给这段代码加异常进行处理，让他最后都能释放掉这把锁，不要陷入死锁的情况中
         */
        try {
            /**
             * 问题二:
             * 如果还没有释放掉锁时，系统突然宕机了，finally后面的代码执行不了了，怎么办呢
             * 可以给这个key设置10秒钟的过期时间，当时间到了的时候，redis会自动删除这个key
             */
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "lockkey", 10, TimeUnit.SECONDS);
            if (!result) {
                return "err";
            }
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            /**
             * 问题三:
             * 在超高并发场景下，我们设置key'的过期时间是10秒，如果第一个线程过来会执行15秒的话，在高并发的情况下，很有可能会出现第一个线程释放掉了第二个线程的锁，而导致锁失效
             * 我们给每一个线程生成一个不重复的字符串，在释放的锁的时候去验证一下是不是我们这个线程自己的锁
             */
            if (stringRedisTemplate.opsForValue().get(lockKey).equals(redisId)) {
                //释放点这个锁
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "end";
        /**
         * 问题四:
         * 如果我们代码执行了10秒钟的话，这个key已经过期了，代码还没有执行完，这时应该怎么解决的
         * 我们可以在这个key过期后去演演唱这个key的生命时间去给他续命，我们可以用redisson去实现
         */
    }
}
