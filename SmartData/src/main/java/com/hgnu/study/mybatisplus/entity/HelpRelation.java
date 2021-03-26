package com.hgnu.study.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 13:39
 */

@TableName("help_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelpRelation {

    private Integer helpTopicId;

    private Integer helpKeywordId;
}
