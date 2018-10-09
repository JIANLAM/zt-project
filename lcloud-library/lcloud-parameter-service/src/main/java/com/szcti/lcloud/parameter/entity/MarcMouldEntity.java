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
public class MarcMouldEntity extends QueryParam implements Serializable {

   private Long id;                //MARC模板表（sys_marc_mould）

   private String value;     //模板代码

   private String label;    //模板名称

   private String mainField;      //主字段开始符

   private String sonField;      //子字段开始符

   private String tailed;        //MARC记录结束符

   private Integer status;            //是否默认 1是 0否

   private String marcData;         //marc数据

   private String createDate;          //创建时间

   private Long createBy;          //创建者

   private String remarks;          //备注

   private Long libraryId;          //图书馆ID

}
