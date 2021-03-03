package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor_apply")
public class Doctor {
    Integer id;
    String name;
    String phone;

    /**
     * 创建时间,自动填充
     *  FieldFill.DEFAULT         默认不填充
     *  FieldFill.INSERT          插入时填充
     *  FieldFill.UPDATE          更新时填充
     *  FieldFill.INSERT_UPDATE   插入、更新时填充
     */
    @TableField(fill = FieldFill.INSERT)
    LocalDateTime created;
    /**
     * 最后修改时间,自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updated;
}
