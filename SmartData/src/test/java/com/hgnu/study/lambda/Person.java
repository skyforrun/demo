package com.hgnu.study.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @Author zj
 * @Description
 * @Date 2021/4/27 10:49
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    // 姓名
    private String name;
    // 薪资
    private int salary;
    // 年龄
    private int age;
    //性别
    private String sex;
    // 地区
    private String area;

    // 构造方法
    public Person(String name, int salary,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public static List<Person> createList(){
        List<Person> personList = new ArrayList<>(8);
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));
        return personList;
    }
}
