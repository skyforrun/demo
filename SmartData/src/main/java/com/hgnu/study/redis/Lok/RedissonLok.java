package com.hgnu.study.redis.Lok;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zj
 * @Description
 *          redisson实现分布式锁
 * @Date 2021/3/5 15:03
 */
public class RedissonLok {
    @Autowired
    private Redisson redisson;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/deduct_stock")
    public String deductStock(){
        String lockKey="lockKey";
        //拿到锁对象
        RLock redisId=redisson.getLock(lockKey);
        /**
         *  问题一:
         *  stringRedisTemplate.delete(lockKey);//释放点这个锁，如果在这段代码出现异常了的话， 这个锁没有释放掉，别的线程进来的话拿不到锁，会陷入一种死锁状态。
         *  给这段代码加异常进行处理，让他最后都能释放掉这把锁，不要陷入死锁的情况中
         */
        try {
            redisId.lock();
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock=stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
            }else{
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            redisId.unlock();
        }
        return "end";
    }
}
