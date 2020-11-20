package cn.com.kxyt.exception;

import cn.com.kxyt.core.ResuleCode;

/**
 * @author zj
 * @Description: 自定义异常
 * @date 2020/11/1816:57
 */
public class TipException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public TipException(ResuleCode resuleCode){
        super(resuleCode.getCode());
        this.errorCode = resuleCode.getCode();
        this.errorMsg = resuleCode.getMessage();
    }

    public TipException(ResuleCode resuleCode,Throwable cause){
        super(resuleCode.getCode(),cause);
        this.errorCode = resuleCode.getCode();
        this.errorMsg = resuleCode.getMessage();
    }

    public TipException(String message){
        super(message);
        this.errorMsg = message;
    }

    public TipException(String code,String message){
        super(code);
        this.errorMsg = message;
        this.errorCode = code;
    }

    public TipException(String errorCode,String message,Throwable cause){
        super(errorCode,cause);
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage(){
        return errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}

