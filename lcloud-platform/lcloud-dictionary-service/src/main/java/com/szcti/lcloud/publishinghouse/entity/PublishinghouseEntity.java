package com.szcti.lcloud.publishinghouse.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 出版社数据表 实体     （sys_publishinghouse）
 * @Description: 描述
 * @author: fengda
 * @date: 2018/8/27 3:32
 */
@Data
public class PublishinghouseEntity extends QueryParam implements Serializable {

   private Long id;     //出版社表主键（sys_publishinghouse）

   private String code;   //出版社代码

   private String name;   //出版社名称

   private String address;    //出版地

   private String postalcode;   //邮政编码

   private String createDate;    //创建时间

   private String remarks;       //备注

}
