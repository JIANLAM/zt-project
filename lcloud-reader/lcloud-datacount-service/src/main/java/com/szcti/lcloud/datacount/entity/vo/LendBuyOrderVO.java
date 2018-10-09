package com.szcti.lcloud.datacount.entity.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * @Title: 采访统计 借购订单记录VO
 * @Description: 借购订单信息
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Data
public class LendBuyOrderVO implements Serializable {

    private Long id;            //借购订单表主键

    private String orderNo;     //订单号

    private String expressNo;   //快递单号

    private Float limitMoney;   //预算金额

    private Float totalMoney;   //总金额

    private Long staffId;       //职工id

    private Long readerId;      //创建人id

    private String createTime;  //创建时间

    private String sendTime;  //发货时间

    private String takedTime;  //收货时间

    private Integer lendBuyStatus;  //0待处理，1已发货，2已收货，3已取消

    private Integer online;     // 1线上，0线下

    private String remark;  //备注

    private String createName;  //创建人姓名

    private String readerCardNumber;    //创建人读者证号

    private Integer bookCount;  //图书册数

}
