package com.yst.entity.pojo;


import java.util.Date;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
/**
 * @creator: ly-yangst
 * @date: 2022/6/22
 */
/**
 * 基础数据类
 *
 * @author Jiaju Zhuang
 **/
@Getter
@Setter
@EqualsAndHashCode
public class UploadData {
    private String string;
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String date;
    private Double doubleData;
}
