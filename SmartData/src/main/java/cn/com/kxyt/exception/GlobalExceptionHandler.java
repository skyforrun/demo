package cn.com.kxyt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zj
 * @Description: springboot全局异常统一处理
 * @date 2020/11/18 16:55
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = TipException.class)
    public static Object handleException(Exception e, HttpServletRequest request) throws Exception {
        //获取拦截器判断的返回结果类型
        Object o = request.getAttribute("view");
        if (o == null) {
            logger.error("发生了异常", e);
            throw e;
        }
        //是否是html/text
        boolean isView = (Boolean) o;

        //返回页面
        if (isView) {
            //配置需要跳转的Controller方法
            ModelAndView modelAndView = new ModelAndView("error");
            request.setAttribute("code", "-1");
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("stackTrace", e.getStackTrace());
            return modelAndView;
        } else {
            //返回json
            ModelAndView  modelAndView = new ModelAndView(new MappingJackson2JsonView());
            modelAndView.addObject("code", "500");
            modelAndView.addObject("message", e.getMessage());
            //modelAndView.addObject("cause", e.getStackTrace());
            return modelAndView;
        }
    }
}
