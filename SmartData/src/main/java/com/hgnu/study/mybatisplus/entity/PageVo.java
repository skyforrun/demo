package com.hgnu.study.mybatisplus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageVo<T> {
    Long current ;
    Long size;
    Long total;
    List<T> resultInfo;
}
