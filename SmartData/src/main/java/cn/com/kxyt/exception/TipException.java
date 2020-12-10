package cn.com.kxyt.exception;

import cn.com.kxyt.core.ResuleCode;

/**
 * @author zj
 * @Description: 自定义异常
 * @date 2020/11/1816:57
 */
public class TipException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TipException(String errorMsg){
        super(errorMsg);
    }

    public TipException(Throwable cause){
        super(cause);
    }

    public TipException(String msg,Throwable cause){
        super(msg,cause);
    }
}

