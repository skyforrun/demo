package com.hgnu.study.mybatisplus.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @Author zj
 * @Description
 * @Date 2021/3/12 16:14
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectHospitalDto {

    private Long id;

    private Long projectId;

    private Long hospitalId;

    /**
     * 状态 101:已删除  0:未启用 1:已启用
     */
    private Integer enable;

    private Integer pageSize = 10;

    private Integer pageIndex = 1;
}
