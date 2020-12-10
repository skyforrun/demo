package cn.com.kxyt.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ViewExceptionHandler {

    @ExceptionHandler(value = TipException.class)
    public static ModelAndView handleRRException(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        //指定错误页面的模板页
        modelAndView.setViewName("error");
        modelAndView.addObject("code", -1);
        modelAndView.addObject("msg", e.getMessage());
        return modelAndView;
    }
}
