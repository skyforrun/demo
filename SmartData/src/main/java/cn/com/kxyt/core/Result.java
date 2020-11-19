package cn.com.kxyt.core;

import com.alibaba.fastjson.JSON;

/**
 * @author zj
 * @Description: API统一封装结果
 * @date 2020/11/18 16:51
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result setCode(ResuleCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

