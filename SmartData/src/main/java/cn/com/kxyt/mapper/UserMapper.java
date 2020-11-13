package cn.com.kxyt.mapper;

import cn.com.kxyt.entity.User;


/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1314:32
 */

public interface UserMapper {

    User selectUserById(String id);
}
