package cn.com.kxyt.core;

/**
 * @author zj
 * @Description: http状态码响应枚举
 * @date 2020/11/18 16:47
 */

public enum ResuleCode {
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    FAIL(400,"失败"),
    /**
     * 签名错误
     */
    UNAUTHORIZED(401,"签名错误"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404,"接口不存在"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500,"服务器内部错误");

    private final int code;
    private final String message;

    ResuleCode(int code, String message) {

        this.code = code;
        this.message = message;
    }
    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
