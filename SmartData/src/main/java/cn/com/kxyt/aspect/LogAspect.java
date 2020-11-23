package cn.com.kxyt.aspect;

import cn.com.kxyt.util.IPutil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * description: 接口日志aop
 * date: 2020/11/22 14:29
 * author: 曾军
 * version: 1.0
 */

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * cn.com.kxyt.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("请求的URL为 : " + request.getRequestURL().toString() + ",请求的IP为 : " + IPutil.getIpAddrByRequest(request) + ",请求的类为 : " + joinPoint.getSignature().getDeclaringTypeName() + "，请求的方法为：" + joinPoint.getSignature().getName() + ",方法的参数为: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) throws Throwable {
        // 处理完毕，返回内容
        logger.info("RESPONSE:" + object);
    }
}
