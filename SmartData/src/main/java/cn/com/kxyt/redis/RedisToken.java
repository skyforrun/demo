package cn.com.kxyt.redis;

import cn.com.kxyt.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RedisToken {

    @Autowired
    private RedisUtil redisUtil;

    /** 缓存指定时间200秒 */
    private static final long TOKENTIMEOUT = 200;

    /**
     * 生成Token
     */
    public String getToken(){
        String token = UUID.randomUUID().toString();
        // 将token放到Redis中，用UUID保证唯一性
        redisUtil.set(token,token,TOKENTIMEOUT);
        return token;
    }

    public synchronized boolean findToken(String tokenKey) {
        String tokenValue= (String)redisUtil.get(tokenKey);

        // 如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
        if (StringUtils.isEmpty(tokenValue)) {
            return false;
        }
        // 保证每个接口对应的token 只能访问一次，保证接口幂等性问题，用完直接删掉
        redisUtil.del(tokenValue);
        return true;
    }
}
