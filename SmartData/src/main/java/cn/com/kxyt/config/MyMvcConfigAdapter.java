package cn.com.kxyt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zj
 * @Description: 配置文件资源映射
 * @date 2020/11/17 11:28
 */
@Configuration
public class MyMvcConfigAdapter implements WebMvcConfigurer {
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
}
