package cn.com.kxyt.controller.springsession;

import cn.com.kxyt.core.Constant;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: session测试
 *          测试 重启程序，Session 不失效的场景
 *          1. 打开浏览器，访问首页：http://localhost/page/index
 *          2. 最开始未登录，所以会跳转到登录页：http://localhost/page/login?redirect=true 然后点击登录按钮
 *          3. 登录之后，跳转回首页，此时可以看到首页显示token信息。
 *          4. 重启程序。不关闭浏览器，直接刷新首页，此时不跳转到登录页。测试成功！
 * @author: zj
 * @time: 2020/12/18 16:21
 */

@Controller
@RequestMapping("/page")
public class SessionPageController {
    /**
     * 跳转到 首页
     *
     * @param request 请求
     */
    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        String token = (String) request.getSession().getAttribute(Constant.SESSION_KEY);
        mv.setViewName("index");
        mv.addObject("token", token);
        return mv;
    }

    /**
     * 跳转到 登录页
     *
     * @param redirect 是否是跳转回来的
     */
    @GetMapping("/login")
    public ModelAndView login(Boolean redirect) {
        ModelAndView mv = new ModelAndView();

        if (ObjectUtil.isNotNull(redirect) && ObjectUtil.equal(true, redirect)) {
            mv.addObject("message", "请先登录！");
        }
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/doLogin")
    public String doLogin(HttpSession session) {
        session.setAttribute(Constant.SESSION_KEY, IdUtil.fastUUID());

        return "redirect:/page/index";
    }
}
