package cn.com.kxyt.controller;

import cn.com.kxyt.entity.User;
import cn.com.kxyt.mapper2.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @author zj
 * @Description: restful风格CRUD
 * @date 2020/11/1314:39
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户接口", tags = "UserController", description = "PG库user接口相关")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    @Transactional("dataSourceTransactionManager2")
    public User selectAll(@PathVariable String id){
        User user = userMapper.selectUserWithId(id);
        System.out.println(user);
        return user;
    }
}
