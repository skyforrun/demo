package cn.com.kxyt.controller;

import cn.com.kxyt.entity.User;
import cn.com.kxyt.mapper2.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @Description:
 * @date 2020/11/1314:39
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/{id}")
    @Transactional("dataSourceTransactionManager2")
    public User selectAll(@PathVariable String id){
        System.out.println("进来了user？");
        User user = userMapper.selectUserWithId(id);
        System.out.println(user);
        return user;
    }
}
