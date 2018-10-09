package com.szcti.lcloud.parameter.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: Z39.50地址数据表 实体     （t_libdata_para）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/25 11:04
 */
@Data
public class CustomTypeEntity extends QueryParam implements Serializable {

   private Long id;                //自定义字段类型表

   private String type;     //类型

   private String description;      //描述

   private Long libraryId;            //图书馆ID

}
