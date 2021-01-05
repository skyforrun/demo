package com.hgnu.study.entity;

/**
 * @author zj
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/1314:31
 */
public class User {


    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
