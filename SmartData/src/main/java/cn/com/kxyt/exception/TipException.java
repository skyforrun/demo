package cn.com.kxyt.exception;

import cn.com.kxyt.core.ResoponseMessage;
import cn.com.kxyt.core.Result;

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
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public TipException() {
        super();
    }

    public TipException(Result result) {
        this.errorCode = result.getCode();
        this.errorMsg = result.getMessage();
    }

    public TipException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public TipException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public TipException(int errorCode, String errorMsg, Throwable cause) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
