package com.szcti.lcloud.parameter.entity;


import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: mrc模板 实体     （sys_marc）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */
@Data
public class MarcFieldEntity extends QueryParam implements Serializable {

   private Long id;                //MARC字段表（sys_marc_field）

   private String description;     //字段意义 描述

   private String name;    //字段名称

   private Integer designator;      //指示符

   private String createDate;          //创建时间

   private Long createBy;          //创建者

   private String remarks;          //备注

   private Long libraryId;          //图书馆ID

   private String mustValues;       //必选子字段

   private String selectValues;     //可选子字段

   private String designatorTest;      //指示符

}
