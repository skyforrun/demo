package cn.com.kxyt.controller;

import cn.com.kxyt.entity.User;
import cn.com.kxyt.mapper2.UserMapper;
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
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @PostMapping
    @Transactional("dataSourceTransactionManager2")
    public User insertUser(@RequestBody User user){
        logger.info("开始新增用户："+user);
        User insertUser = userMapper.insertUser(user);
        return insertUser;
    }

    @DeleteMapping
    @Transactional("dataSourceTransactionManager2")
    public boolean deleteUser(@RequestParam(value = "id") String id){
        logger.info("开始删除用户，ID为："+id);
        int i = userMapper.deleteUser(id);
        return i>0?true:false;
    }

    @PutMapping
    @Transactional("dataSourceTransactionManager2")
    public User updateUser(@RequestBody User user){
        logger.info("开始修改用户："+user);
        User updateUser = userMapper.updateUser(user);
        return updateUser;
    }

    @GetMapping
    @Transactional("dataSourceTransactionManager2")
    public User selectUser(@RequestParam(value = "id") String id){
        logger.info("开始查询用户，id为"+id);
        User user = userMapper.selectUserWithId(id);
        return user;
    }

    @GetMapping("/{id}")
    @Transactional("dataSourceTransactionManager2")
    public User selectAll(@PathVariable String id){
        User user = userMapper.selectUserWithId(id);
        System.out.println(user);
        return user;
    }
}
