package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/15 10:32
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("health_worker")
public class HealthWorker {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 岗位：医生 护士
     */
    private String post;

    /**
     * 通信地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 101:已删除  0:未启用 1:已启用
     */
    private Integer enable;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建者ID,关联sys_account表id
     */
    private Long createAccount;

    /**
     * 修改者ID,关联sys_account表id
     */
    private Long updateAccount;
}
