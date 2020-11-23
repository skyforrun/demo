package cn.com.kxyt.controller;

import cn.com.kxyt.entity.Student;
import cn.com.kxyt.exception.TipException;
import cn.com.kxyt.mapper3.StudentMapeer;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * description: StudentController
 * date: 2020/11/21 12:38
 * author: 曾军
 * version: 1.0
 */

@RestController
@RequestMapping("/student")
@Api(value = "学生controller",tags = "对学生进行增删改查操作")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentMapeer studentMapeer;

    @PostMapping
    @Transactional(value = "dataSourceTransactionManager3",rollbackFor = TipException.class)
    @ApiOperation(value="增加学生",notes="sid必须为数字")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sid",value="学生id",required=true,dataType="Integer"),
            @ApiImplicitParam(name="sname",value="学生名字",required=true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public void insertStudent(@RequestParam(value = "sid",required = true) Integer sid,
                              @RequestParam(value = "sname",required = false) String sname){
        Student student = new Student(sid,sname);
        int i = studentMapeer.insertStudent(student);
        if (i>0){
            logger.info("插入学生成功，学生的信息为："+student);
        }else {
            logger.error("插入学生失败");
        }

    }

    @DeleteMapping
    @Transactional(value = "dataSourceTransactionManager3",rollbackFor = TipException.class)
    @ApiOperation(value="删除学生",notes="sid必须为数字")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sid",value="学生id",required=true,dataType="Integer")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public void deleteStudent(@RequestParam(value = "sid") Integer sid){
        int i = studentMapeer.deleteStudent(sid);
        if (i>0){
            logger.info("删除学生成功");
        }else {
            logger.error("删除学生失败");
        }

    }

    @PutMapping
    @Transactional(value = "dataSourceTransactionManager3",rollbackFor = TipException.class)
    @ApiOperation(value="修改学生信息",notes="sid必须为数字")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sid",value="学生id",required=true,dataType="Integer"),
            @ApiImplicitParam(name="sname",value="学生名字",required=true)
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public void updateStudent(@RequestParam(value = "sid",required = true) Integer sid,
                              @RequestParam(value = "sname",required = false) String sname){
        Student student = new Student(sid,sname);
        int i = studentMapeer.updateStudent(student);
        if (i>0){
            logger.info("修改学生成功，学生的信息为："+student);
        }else {
            logger.error("修改学生失败");
        }

    }

    @GetMapping
    @Transactional(value = "dataSourceTransactionManager3",rollbackFor = TipException.class)
    @ApiOperation(value="查询学生",notes="sid必须为数字")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sid",value="学生id",required=true,dataType="Integer")
    })
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public Student queryStudent(@RequestParam(value = "sid") Integer sid){
        Student student = studentMapeer.queryStudent(sid);
        if (null!=student){
            logger.info("查询学生成功，学生的信息为："+student);
        }else {
            logger.error("查询学生失败");
        }
        return student;
    }
}
