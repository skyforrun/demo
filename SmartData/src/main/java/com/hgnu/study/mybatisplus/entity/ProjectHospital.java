package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/12 15:51
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("project_hospital")
public class ProjectHospital {

    private Long id;

    private Long projectId;

    private Long hospitalId;

    private Long createAccount;

    private Long updateAccount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer enable;
}
