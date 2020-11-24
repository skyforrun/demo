package cn.com.kxyt.service.impl;

import cn.com.kxyt.config.RedisConfig;
import cn.com.kxyt.exception.TipException;
import cn.com.kxyt.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author zj
 * @Description: token服务实现类
 * @date 2020/11/23 15:02
 */
@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";

    @Autowired
    private RedisConfig redisConfig;

    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString();
        try {
            redisConfig.setEx(token, token, 10000L);
            return token;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // header中不存在token
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                // parameter中也不存在token
                throw new TipException("参数不合法，必须带token参数");
            }
        }
        if (!redisConfig.exists(token)) {
            throw new TipException("请勿重复操作");
        }
        boolean remove = redisConfig.remove(token);
        // 必须再次判断是否移除成功，因为可能多个请求同时执行上面移除的代码，但是最终只有一个返回移除成功的
        // 如果不判断是否移除成功，就会失去幂等性的
        if (!remove) {
            throw new TipException("请勿重复操作");
        }
    }
}
