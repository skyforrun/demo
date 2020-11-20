package cn.com.kxyt;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;


/**
 * @author zj
 * @Description: springboot主启动类
 * @date 2020/11/13 14:44
 */

@SpringBootApplication
public class SmartDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartDataApplication.class, args);
    }

}
