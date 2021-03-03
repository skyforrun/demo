package com.hgnu.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zj
 * @Description: springboot主启动类
 * @date 2020/11/13 14:44
 */

@SpringBootApplication
public class SmartDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDataApplication.class, args);
        System.out.println("项目启动成功，请访问:http://localhost/swagger-ui.html");
    }

}
