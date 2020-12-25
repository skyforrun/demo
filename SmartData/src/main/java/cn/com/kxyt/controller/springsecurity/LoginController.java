package cn.com.kxyt.controller.springsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: zj
 * @time: 2020/12/24 15:25
 */

@Controller
@RequestMapping("/demo")
public class LoginController {

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "springsecurity/login";
    }

    @PostMapping("/main")
    public String main() {
        return "redirect:/main.html";
    }

    @PostMapping("/toerror")
    public String error() {
        return "redirect:/error.html";
    }

    @PostMapping("/demo")
    public String demo(){
        return "springsecurity/demo";
    }
}
