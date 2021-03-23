package com.hgnu.study.core.aspect.idempotent;

import com.hgnu.study.core.annotation.ExtApiIdempotent;
import com.hgnu.study.core.annotation.ExtApiToken;
import com.hgnu.study.redis.RedisToken;
import com.hgnu.study.core.Constant;
import com.hgnu.study.core.Result;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ExtApiAopIdempotent {
    @Autowired
    private RedisToken redisToken;

    // 1.使用AOP环绕通知拦截所有访问（controller）
    @Pointcut("execution(public * com.hgnu.study.controller.*.*(..))")
    public void rlAop() {

    }

    /**
     * 封装数据
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    /**
     * 前置通知
     */
    @Before("rlAop()")
    public void before(JoinPoint point) {
        // 获取被增强的方法相关信息 - 查看方法上是否有次注解

        MethodSignature signature = (MethodSignature) point.getSignature();
        ExtApiToken extApiToken = signature.getMethod().getDeclaredAnnotation(ExtApiToken.class);
        if (extApiToken != null) {
            // 可以放入到AOP代码 前置通知
            getRequest().setAttribute("token", redisToken.getToken());
        }
    }

    /**
     * 环绕通知
     */
    @Around("rlAop()")
    public Object doBefore(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 获取被增强的方法相关信息 - 查看方法上是否有次注解
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        ExtApiIdempotent declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
        if (declaredAnnotation != null) {
            String values = declaredAnnotation.value();
            String token = null;
            HttpServletRequest request = getRequest();
            if (values.equals(Constant.EXTAPIHEAD)) {
                token = request.getHeader("token");
            } else {
                token = request.getParameter("token");
            }

            // 获取不到token
            if (StringUtils.isEmpty(token)) {
                return Result.error("参数错误");
            }

            // 接口获取对应的令牌,如果能够获取该(从redis获取令牌)令牌(将当前令牌删除掉) 就直接执行该访问的业务逻辑
            boolean isToken = redisToken.findToken(token);
            // 接口获取对应的令牌,如果获取不到该令牌 直接返回请勿重复提交
            if (!isToken) {
                return Result.error("错误选项");
            }
        }
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
