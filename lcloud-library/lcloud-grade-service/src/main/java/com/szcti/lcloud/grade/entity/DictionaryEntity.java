package com.szcti.lcloud.grade.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 数据字典数据表 实体     （sys_dict）
 * @Description: 描述
 * @author: fd
 * @date: 2018/7/30 10:26
 */
@Data
public class DictionaryEntity extends QueryParam implements Serializable {

   private Long id;     //数据字典表主键（sys_dict）

   private String value;   //键值

   private String label;   //标签

   private String type;    //类型

   private String description;   //描述

   private Integer sort;      //排序

   private String createDate;    //创建时间

   private Integer createBy;     //创建者

   private String remarks;       //备注

}
