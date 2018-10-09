package com.szcti.lcloud.datacount.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借购书籍记录VO
 * @Description: 包含借购信息，以及借购书籍的信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class LendBuyBookVO implements Serializable {

    private Long id;    //借购书籍（t_lendbuy_book） 主键

    private Long readerId;  //读者id

    private String orderNo;    //订单号

    private Float univalent;    //单价

    private String backTime;    //还书日期

    private Integer online;  //1线上，0线下

    private Integer status; //0未到馆，1已到馆

    private Integer isSubmit;//0未提交，1已提交

    private String pic; //书的封面

    private String title;   //图书名称

    private String ISBN;    //图书ISBN编号

    private String author;  //作者

    private String bookType;    //分类

    private String publisher;   //出版社

    private String pubDate; //出版时间

    private Long bookCount; //总册数

    private Float total;    //总价
}
