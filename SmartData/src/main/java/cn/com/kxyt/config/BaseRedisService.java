package cn.com.kxyt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class BaseRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object data, Long timeout) {
        if (data instanceof String) {
            String value = (String) data;
            // 往Redis存值
            stringRedisTemplate.opsForValue().set(key, value);
        }
        if (timeout != null) {
            // 带时间缓存
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    /**
     * 查看是否有值
     * @param key 值
     * @return
     */
    public Object getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 删除Redis
     * @param key 值
     */
    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
