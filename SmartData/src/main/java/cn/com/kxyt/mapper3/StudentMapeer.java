package cn.com.kxyt.mapper3;

import cn.com.kxyt.entity.Student;
import cn.com.kxyt.entity.User;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/17 16:41
 */

public interface StudentMapeer {

    int insertStudent(Student student);

    int deleteStudent(Integer sid);

    int updateStudent(Student student);

    Student queryStudent(Integer sid);

}
