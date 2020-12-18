package cn.com.kxyt.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zj
 * @Description: 配置文件资源映射
 * @date 2020/11/17 11:28
 */
@Configuration
public class MvcConfigAdapter implements WebMvcConfigurer {

    @Autowired
    SessionIntercept sessionIntercept;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 浏览器地址栏输入/
         * 即可访问qrcode文件夹
         */
        registry.addResourceHandler("/**").addResourceLocations("file:public/qrcode/");

        /**
         * swagger2报404
         */
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration sessionInterceptorRegistry = registry.addInterceptor(sessionIntercept);
        // 排除不需要拦截的路径
        sessionInterceptorRegistry.excludePathPatterns("/page/login")
                .excludePathPatterns("/page/doLogin").excludePathPatterns("/error");
        // 需要拦截的路径
        sessionInterceptorRegistry.addPathPatterns("/**");
    }
}
