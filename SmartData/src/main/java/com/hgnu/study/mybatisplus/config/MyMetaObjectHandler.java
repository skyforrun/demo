package com.hgnu.study.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 重写,对自动填充属性进行补充
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
       if (metaObject.hasGetter("created")){
           setFieldValByName("created",new Date(),metaObject);
           setFieldValByName("updated",new Date(),metaObject);
       }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updated")){
            setFieldValByName("updated",new Date(),metaObject);
        }
    }
}
