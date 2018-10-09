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
public class TitleNumberEntity extends QueryParam implements Serializable {

   private Long id;                //种次号表 (t_titlenumber)

   private String classNumber;     //分类号

   private Integer currSeednumber;    //当前种号

   private String createTime;      //创建时间

   private Long userId;            //创建者

   private String remark;          //备注

   private Long libraryId;          //图书馆ID

   private String currSeednumberTest;    //当前种号 文本

}
