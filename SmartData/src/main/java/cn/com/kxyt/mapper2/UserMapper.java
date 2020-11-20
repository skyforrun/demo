package cn.com.kxyt.mapper2;

import cn.com.kxyt.entity.User;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/17 16:41
 */

public interface UserMapper {
    User selectUserWithId(String id);

    User insertUser(User user);

    int deleteUser(String id);

    User updateUser(User user);
}
