package com.hgnu.study.config.springsecurity;

import com.hgnu.study.service.impl.UserServiceDetaiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

/**
 * @description: springsecurity配置类
 * @author: zj
 * @time: 2020/12/24 10:24
 */

@Configuration
public class SecurityWebAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceDetaiImpl userServiceDetai;

    @Autowired
    SessionExpireStrategy sessionExpireStrategy;

    @Autowired
    @Qualifier("dataSource4")
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("admin")
//                //多用户
//                .and()
//                .withUser("sky")
//                .password(passwordEncoder().encode("123456"))
//                .roles("user");
        auth.userDetailsService(userServiceDetai);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        //表单登录
        http.formLogin()
                //指定登录路径
                .loginPage("/demo/showLogin")
                // 登陆行为，对应 action="/user/login"
                .loginProcessingUrl("/login")
                //登陆成功
                .successForwardUrl("/demo/main")
                //登陆失败
                .failureForwardUrl("/demo/toerror");

        //授权
        http.authorizeRequests()
                // 不用认证
                .antMatchers("/page/**", "/error","/code/**","/city/**","/oss/**","/xxljob/**","/admin/**","/demo/showLogin",
                        "/swagger-ui.html","/**/*.html","/**/*.js","/**/*.css","/**/*.woff","/demo/toerror",
                        "/**/*.ttf","/webjars/**","/v2/**","/swagger-resources/**","/user/login","/login").permitAll()
                .anyRequest().authenticated();  //认证

        http.sessionManagement()
                //session失效URL
                .invalidSessionUrl("/demo/toerror")
                //会话最大数量
                .maximumSessions(1)
                //达到最大会话数量阻止登陆
                .maxSessionsPreventsLogin(true)
                //过期策略
                .expiredSessionStrategy(sessionExpireStrategy);

        //记住我
        http.rememberMe()
                //设置持久化仓库
                .tokenRepository(jdbcTokenRepository)
                //超时时间,单位s 默认两周
                .tokenValiditySeconds(3600)
                //设置自定义登录逻辑
                .userDetailsService(userServiceDetai);

        http.logout()
                //退出URL
                .logoutUrl("/logout")
                //退出到哪
                .logoutSuccessUrl("/springsecurity/login.html");

        //关闭csrf
        http.csrf().disable();
    }

    /**
     * springsecurity密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 官方建议这种方式配置认证
     * @return
     * @throws Exception
     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new UserServiceDetaiImpl();
//    }
}
