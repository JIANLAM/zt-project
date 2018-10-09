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
public class LibdataParaEntity extends QueryParam implements Serializable {
   private Long id;              //t_libdata_para主键

   private String libName;       //馆名称

   private String ipAddress;     //ip地址

   private Long portNumbers;    //端口号

   private String databaseName; //数据库名称

   private String userName;     //用户名

   private String password;     //密码

   private String characterset; //字符集

   private String createTime;   //创建时间

   private Long userId;         //外键 创建者

   private String remark;       //备注

   private Long libraryId;      //图书馆ID

}
