package com.szcti.lcloud.datacount.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 采访统计 订购单统计
 * @Description: 所有订购单信息
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Data
public class PurchaseOrderVO implements Serializable {

    private Long id;    //订购单（t_purchase_order） 主键

    private String purchaseCode;  //订购号

    private String orderCode; //订单号

    private String createLoginName;   //创建者账号

    private String createUserName;  //创建者姓名

    private String orderTime;   //下单时间

    private String createTime;    //创建时间

    private Long totalQuantity;     //书本总数量

    private Long bookTypeCount; //图书种数

    private Float totalPrice;   //总价

    private String checkerLoginName;   //审核者账号

    private String checkerUserName;  //审核者姓名

    private Long checkStatus;   //审核状态 0待审核1通过2不通过3无需审核

    private String  checkTime;  //审核时间

}
