package cn.com.kxyt.controller.springsecurity;

import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zj
 * @time: 2020/12/24 15:26
 */

@RestController
@RequestMapping("/admin")
@Api(value = "springsecurity测试",tags = "springsecurity测试")
public class AdminController {

    @GetMapping("/demo")
    public String demo() {
        return "spring security demo";
    }

    @GetMapping("/index")
    public String index() {
        return "index"+getUsername();
    }

    /**
     * 通过上下文获取用户信息
     */
    private String getUsername(){
        // 获取当前登录的用户信息
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username =((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}