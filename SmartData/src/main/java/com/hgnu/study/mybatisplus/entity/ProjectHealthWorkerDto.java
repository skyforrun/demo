package com.hgnu.study.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/15 11:13
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectHealthWorkerDto {
    private Long id;

    private Long projectId;

    private Long healthworkerId;

    /**
     * 状态 101:已删除  0:未启用 1:已启用
     */
    private Integer enable;

    private Integer pageSize = 10;

    private Integer pageIndex = 1;
}
