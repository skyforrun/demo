package com.hgnu.study.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zj
 * @Description: Swagger2配置文件
 * @date 2020/11/2016:06
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    /**
     *
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                .enable(true)
                .select()
                //此处根据情况自行添加需要将哪些接口纳入Swagger 文档管理。此处应用basePackage管理，还可以利用注解管理
                //如果填写错误的话会出现“No operations defined in spec!” 的问题。
                .apis(RequestHandlerSelectors.basePackage("com.hgnu.study.controll"))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo(){
        Contact contact = new Contact("Swagger2 demo", "http://localhost", "邮箱");
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("Spring Boot中使用Swagger2构建RESTFUL API")
                //文档描述
                .description("描述信息")
                //服务条款URL
                .termsOfServiceUrl("http://localhost")
                //版本号
                .version("1.0")
                //联系信息
                .contact(contact)
                .build();
    }
}
