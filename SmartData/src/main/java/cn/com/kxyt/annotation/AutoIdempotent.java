package cn.com.kxyt.annotation;

import java.lang.annotation.*;

/**
 * @author zj
 * @Description: 凡是方法上有此注解，都会自动实现幂等性
 * @date 2020/11/23 14:58
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AutoIdempotent {
}
