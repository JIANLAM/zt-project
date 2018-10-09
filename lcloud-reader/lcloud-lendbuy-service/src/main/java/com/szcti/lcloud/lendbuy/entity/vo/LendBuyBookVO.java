package com.szcti.lcloud.lendbuy.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借购书籍记录VO
 * @Description: 包含借购信息，以及借购书籍的信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class LendBuyBookVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long lendBuyOrderId;

    private String orderNo;

    private Long preBookId;

    private String pic;

    private Float price;

    private String backTime;    //还书日期

    private String dueBackTime;    //应还日期

    private Integer status;//0未到馆，1已到馆

    private Integer isSubmit;//0未提交，1已提交

    private Integer online;  //1线上，0线下

    private Long readerId;
    private String userName;

    private Long libId;
    private String libName;

    private String title;

    private String author;

    private String publisher;

    private String pubDate;

    private Integer bookPages;

    private String bookSize;

    private String bookType;

    private String summary;

    private String ISBN;

    private Integer bookQty;

    private  Integer bookCount;

    private String  qualification;      //查询条件 微信端 根据书名或者作者查询

    private String sendeeInfo;      //地址 电话 收件人 信息组合
}
