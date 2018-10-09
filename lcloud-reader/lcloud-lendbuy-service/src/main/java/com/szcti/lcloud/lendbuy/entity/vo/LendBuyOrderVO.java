package com.szcti.lcloud.lendbuy.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借购订单记录VO
 * @Description: 订单信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class LendBuyOrderVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String orderNo;

    private String expressNo;

    private Float limitMoney;

    private Float totalMoney;

    private Long staffId;

    private Integer online;// 1线上，0线下

    private Long readerId;
    private String userName;
    private String readerCardNumber;

    private Long libId;
    private String libName;

    private String createTime;

    private String sendTime;

    private String takedTime;

    private Integer status;//0未处理，1已发货，2已收货，3已取消

    private Integer bookCount;

    private String sendeeInfo;//收件人信息，“湖北武汉,冯达,13720201004”

    private String remark;

}
