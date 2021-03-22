package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/15 11:11
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("project_health_worker")
public class ProjectHealthWorker {
    private Long id;

    private Long projectId;

    private Long healthworkerId;

    private Long createAccount;

    private Long updateAccount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer enable;
}
