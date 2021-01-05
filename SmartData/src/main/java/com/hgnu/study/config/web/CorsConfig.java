package com.hgnu.study.config.web;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        //允许任何域名使用
        response.setHeader("Access-Control-Allow-Origin", "*");
        //带上cookie信息
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //允许任何方法（post、get等）
        response.setHeader("Access-Control-Allow-Methods", "*");
        //时长，单位S
        response.setHeader("Access-Control-Max-Age", "3600");
        //允许任何请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //options请求放行
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, res);
        }
    }

    @Override
    public void destroy() {

    }


}