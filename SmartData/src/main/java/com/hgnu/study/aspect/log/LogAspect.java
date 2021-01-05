package com.hgnu.study.aspect.log;

import com.hgnu.study.util.IPutil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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
    long startTime ;
    long endTime;

    @Pointcut("execution(public * cn.com.kxyt.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        startTime = System.currentTimeMillis();
        //获取方法上的注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //获取方法
        Method method = methodSignature.getMethod();
        //方法上注解

        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        if (null == apiOperation){
            logger.info("操作时间为："+startTime+"，请求的URL为：" + request.getRequestURL().toString() + ",请求的IP为 : " + IPutil.getIpAddrByRequest(request) + ",请求的类为 : " + joinPoint.getSignature().getDeclaringTypeName() + "，请求的方法为：" + joinPoint.getSignature().getName() + ",方法的参数为: " + Arrays.toString(joinPoint.getArgs()));
        }else {
            String value = apiOperation.value();
            String notes = apiOperation.notes();
            // 记录下请求内容
            logger.info("操作时间为："+startTime+",注意事项为：" + notes + "，操作为：" + value + "，请求的URL为：" + request.getRequestURL().toString() + ",请求的IP为 : " + IPutil.getIpAddrByRequest(request) + ",请求的类为 : " + joinPoint.getSignature().getDeclaringTypeName() + "，请求的方法为：" + joinPoint.getSignature().getName() + ",方法的参数为: " + Arrays.toString(joinPoint.getArgs()));
        }
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturning(Object object) throws Throwable {
        endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        // 处理完毕，返回内容
        logger.info("接口调用花费"+time);
        logger.info("RESPONSE:" + object);
    }

}
