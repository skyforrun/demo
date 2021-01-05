package com.hgnu.study.intercept;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: 全局异常拦截器
 * @author: zj
 * @time: 2020/12/22 14:50
 */

@Configuration
public class ExceptionHandleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        //Controller方法返回值是否为字符串
        boolean b1 = method.getReturnType().equals(String.class);
        //Controller方法是否使用@ResponseBody注解
        boolean b2 = method.isAnnotationPresent(ResponseBody.class);
        boolean flags = b1 || b2;
        request.setAttribute("view",flags);

        return true;
    }
}
