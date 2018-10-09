package com.szcti.lcloud.datacount.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

/**
 * @Title: 采访统计 条件查询 条件VO
 * @Description: 所有查询条件
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Data
public class ConditionVO extends QueryParam implements Serializable {
    private String purchaseCode;    //订购号

    private String createLoginName; //创建者账户 登录名

    private String createUserName;  //创建者姓名 真实姓名

    private String checkerLoginName; //审核人账户 登录名

    private String checkerUserName;    //审核人姓名 真实姓名

    private String orderNo;     //订单号

    private Long online;        //0线下,1线上

    private String readerCardNumber;    //读者证号

    private String createName;  //读者姓名

    private String placeOrderStartTime;         //下单开始时间

    private String placeOrderEndTime;         //下单结束时间

    private  String bookName;          //图书名称

    private String ISBN;    //图书ISBN编号

    private String author;  //作者

    private String bookType;    //分类

    private String publisher;   //出版社

    private Long lendBuyStatus;     //0待发货，1待收货，2待还书，3已取消,4已完成

    private Long prebookStatus;     //0未购买，1购买中，2已入藏

    private Integer exportCount;    //导出统计  1订购单统计 2借购单统计 3图书借购统计 4图书荐购统计

    private Long libraryId;     //图书馆ID


}
