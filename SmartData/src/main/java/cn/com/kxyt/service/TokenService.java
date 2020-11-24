package cn.com.kxyt.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zj
 * @Description: token服务类接口
 * @date 2020/11/23 15:00
 */
public interface TokenService {

    /**
     * 创建token
     * @return
     */
    String createToken();

    /**
     * 检验token
     * @param request
     * @return
     */
    void checkToken(HttpServletRequest request) throws Exception;
}
