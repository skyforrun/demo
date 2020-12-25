package cn.com.kxyt.controller.springsecurity;

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
public class AdminController {

    @GetMapping("/demo")
    public String demo() {
        return "spring security demo";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}