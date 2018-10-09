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
public class BasicParamEntity extends QueryParam implements Serializable {

         private Long id;     //自定义字段表主键(sys_custom）

         private String value;   //字段代码

         private String label;   //字段值

         private String type;    //类型

         private String description;   //描述

         private Long libraryId;      //创建学校

         private String createDate;    //创建时间

         private Long createBy;     //创建者

         private String remarks;       //备注

}
