package com.szcti.lcloud.parameter.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;                // 条码表 t_barcode 主键

    private String value;       //数据键值

    private String label;       //标签

    private Long customTypeId;  //自定义字段类型表sys_custom_type） 类型 外键

    private String createDate;     //创建时间

    private Long createBy;           //创建者ID

    private Long libraryId ;        //图书馆ID

    private String remarks;          //备注

    private String type;        //类型

    private String description;     //描述;

}