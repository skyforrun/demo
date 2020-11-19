package cn.com.kxyt.core;

/**
 * @author zj
 * @Description: 响应结果生成工具
 * @date 2020/11/18 16:53
 */
public class ResoponseMessage {

    /**
     * 响应成功
     * @return
     */
    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResuleCode.SUCCESS)
                .setData(data);
    }

    /**
     * 响应失败
     * @param message
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResuleCode.FAIL)
                .setMessage(message);
    }

    /**
     * 未授权
     * @return
     */
    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResuleCode.UNAUTHORIZED);
    }

    /**
     * 接口不存在
     * @return
     */
    public static Result genNotFoundResult() {
        return new Result()
                .setCode(ResuleCode.NOT_FOUND);
    }

    /**
     * 服务器内部错误
     * @return
     */
    public static Result genServerErrorResult() {
        return new Result()
                .setCode(ResuleCode.INTERNAL_SERVER_ERROR);
    }
}
