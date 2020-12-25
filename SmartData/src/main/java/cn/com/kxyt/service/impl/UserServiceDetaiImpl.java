package cn.com.kxyt.service.impl;

import cn.com.kxyt.entity.springsecurity.Permission;
import cn.com.kxyt.entity.springsecurity.UserExample;
import cn.com.kxyt.exception.TipException;
import cn.com.kxyt.mapper4.PermissionMapper;
import cn.com.kxyt.mapper4.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: springsecurity认证
 * @author: zj
 * @time: 2020/12/24 10:08
 */

@Service
public class UserServiceDetaiImpl implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(value = "dataSourceTransactionManager4",rollbackFor = TipException.class)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("===============自定义登陆逻辑===============");
        //从数据库查询用户
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(s);
        userExample.setDistinct(true);
        List<cn.com.kxyt.entity.springsecurity.User> users = userMapper.selectByExample(userExample);

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(null != users){
            List<Permission> permissions = permissionMapper.selectByUserId(users.get(0).getId());
            //设置权限
            permissions.forEach(permission -> {
                if (null != permission && !StringUtils.isEmpty(permission.getEnname())){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                    authorities.add(grantedAuthority);
                }
            });
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            // 封装成UserDetails的实现类
            return new org.springframework.security.core.userdetails.User(
                    users.get(0).getUsername(),users.get(0).getPassword(),authorities);
        }else {
            throw new UsernameNotFoundException("用户名不存在");
        }
    }
}
