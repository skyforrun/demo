package cn.com.kxyt.config.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: session过期策略
 * @author: zj
 * @time: 2020/12/29 14:19
 */

@Configuration
public class SessionExpireStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("您已被挤兑下线！");
    }

}
