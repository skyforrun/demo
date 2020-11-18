package cn.com.kxyt.annotation;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1716:32
 */

import java.lang.annotation.*;

/**
 * 多数据源事务注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface MultiDataSourceTransactional {

    /**
     * 事务管理器数组
     */
    String[] transactionManagers();
}
