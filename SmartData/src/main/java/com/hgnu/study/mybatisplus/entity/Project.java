package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author zj
 * @Description 项目表
 * @Date 2021/3/12 15:41
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 全名
     */
    private String fullName;

    /**
     * 项目分类id,关联project_type表ID
     */
    private Long projectTypeId;

    /**
     * 启动日期
     */
    private LocalDate startDay;

    /**
     * 疾病ID,关联diseaseID
     */
    private Long diseaseId;

    /**
     * 适应症
     */
    private String indication;

    /**
     * 基金会ID，关联foundation表ID
     */
    private Long foundationId;

    /**
     * 捐赠企业,关联donate_company表ID
     */
    private Long donateCompanyId;

    /**
     * 协议捐赠金额
     */
    private BigDecimal planAmount;

    /**
     * 收到金额
     */
    private BigDecimal receiveAmount;

    /**
     * 预计捐赠药品总数
     */
    private Integer planDrugNum;

    /**
     * 简介
     */
    private String brief;

    /**
     * 图片
     */
    private String img;

    /**
     * 计划月数
     */
    private Integer planMonths;

    /**
     * 负责人
     */
    private String director;

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

    /**
     * 项目结项日期
     */
    private LocalDate endDay;
}
