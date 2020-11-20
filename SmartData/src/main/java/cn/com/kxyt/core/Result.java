package cn.com.kxyt.core;

import com.alibaba.fastjson.JSON;

/**
 * @author zj
 * @Description: API统一封装结果
 * @date 2020/11/18 16:51
 */
public class Result<T> {
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public Result() {
    }

    public Result(ResuleCode resuleCode) {
        this.code = resuleCode.getCode();
        this.message = resuleCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(ResuleCode.SUCCESS.getCode());
        result.setMessage(ResuleCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static Result error(ResuleCode resuleCode) {
        Result result = new Result();
        result.setCode(resuleCode.getCode());
        result.setMessage(resuleCode.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static Result error(String code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static Result error( String message) {
        Result result = new Result();
        result.setCode("-1");
        result.setMessage(message);
        result.setData(null);
        return result;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

