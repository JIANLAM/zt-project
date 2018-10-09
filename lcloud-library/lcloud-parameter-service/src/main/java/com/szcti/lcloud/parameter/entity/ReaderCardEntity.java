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
public class ReaderCardEntity extends QueryParam implements Serializable {

   private Long id;                //读者证表 (t_readerCard)

   private String value;   //字段代码

   private String label;   //字段值

   private Long libraryId;      //创建学校

   private Long museumRule;    //馆内规则

   private Long interlibraryRule;   //馆际规则

   private String createDate;    //创建时间

   private Long createBy;     //创建者

   private String remarks;       //备注

   private String museumRuleTest;    //馆内规则文本

   private String interlibraryRuleTest;   //馆际规则文本

}
