package cn.com.kxyt.config.springsecurity;

import cn.com.kxyt.service.impl.UserServiceDetaiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description: springsecurity配置类
 * @author: zj
 * @time: 2020/12/24 10:24
 */

@Configuration
public class SecurityWebAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceDetaiImpl userServiceDetai;

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
        //表单登录
        http.formLogin()
                .loginPage("/demo/showLogin")  //指定登录路径
                .loginProcessingUrl("/login")  // 登陆行为，对应 action="/user/login"
                .successForwardUrl("/demo/main")  //登陆成功
                .failureForwardUrl("/demo/toerror");  //登陆失败

        //授权
        http.authorizeRequests()
                // 不用认证
                .antMatchers("/showLogin","/toerror","/page/login","/page/doLogin",
                "/error","/code/**","/city/**","/oss/**","/xxljob/**","/admin/**","/demo/**",
                        "/swagger-ui.html","/**/*.html","/**/*.js","/**/*.css","/**/*.woff",
                        "/**/*.ttf","/webjars/**","/v2/**","/swagger-resources/**","/user/login","/login").permitAll()
                .anyRequest().authenticated();  //认证

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
