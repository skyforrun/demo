package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/12 15:43
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hospital {

    private static final long serialVersionUID = 1L;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 区域ID,关联region表id
     */
    private Long regionId;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 备注信息
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
