package cn.com.kxyt.controller;

import cn.com.kxyt.entity.User;
import cn.com.kxyt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1314:39
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/{id}")
    public User selectAll(@PathVariable String id){
        User user = userMapper.selectUserById(id);
        System.out.println(user);
        return user;
    }
}
