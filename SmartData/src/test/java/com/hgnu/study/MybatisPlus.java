package com.hgnu.study;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hgnu.study.annotation.MultiDataSourceTransactional;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.Doctor;
import com.hgnu.study.mybatisplus.entity.PageVo;
import com.hgnu.study.mybatisplus.mapper.DoctorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlus {

    @Autowired
    DoctorMapper doctorMapper;

    @Test
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public void selectAll(){
        List<Doctor> doctors = doctorMapper.selectList(null);
        System.out.println(doctors);
    }

    @Test
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public void testPage(){
        //当前页,每页个数
        IPage<Doctor> page = new Page(2,2);
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper();
//        queryWrapper.gt("id",50);
        IPage<Doctor> iPage = doctorMapper.selectPage(page, null);
        System.out.println(iPage.toString());
    }
}
