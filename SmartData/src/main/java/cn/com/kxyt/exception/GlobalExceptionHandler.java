package cn.com.kxyt.exception;

import cn.com.kxyt.core.ResoponseMessage;
import cn.com.kxyt.core.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zj
 * @Description: springboot全局异常统一处理
 * @date 2020/11/1816:55
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 处理自定义的业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = TipException.class)
    @ResponseBody
    public Result bizExceptionHandler(TipException e){
        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
        return ResoponseMessage.genFailResult(e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(NullPointerException e){
        logger.error("发生空指针异常！原因是:",e);
        return ResoponseMessage.genNotFoundResult();
    }


    /**
     * 处理其他异常
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        logger.error("未知异常！原因是:",e);
        return ResoponseMessage.genServerErrorResult();
    }
}
