package cn.com.kxyt.controller;

import cn.com.kxyt.core.ResuleCode;
import cn.com.kxyt.core.Result;
import cn.com.kxyt.entity.User;
import cn.com.kxyt.mapper2.UserMapper;
import io.swagger.annotations.*;
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
@Api(value = "用户接口", tags = "对标PG—BYNE库")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @GetMapping("/{id}")
    @Transactional("dataSourceTransactionManager2")
    @ApiOperation(value="查询用户",notes="id为用户id")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户id",required=true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public Result selectAll(@PathVariable String id){
        User user = userMapper.selectUserWithId(id);
        if (null!=user){
            logger.info("查询成功，结果为："+user);
            return Result.success(user);
        }else {
            logger.error("查询失败，请检查输入的id是否存在");
            return Result.error(ResuleCode.FAIL);
        }

    }
}
