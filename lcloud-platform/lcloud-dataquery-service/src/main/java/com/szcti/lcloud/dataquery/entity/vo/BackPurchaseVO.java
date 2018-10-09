package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 退订单信息VO
 * @Description: 退订单的信息
 * @author: fengda
 * @date: 2018/8/9 15:13
 */
@Data
public class BackPurchaseVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long libId;

    private String purchaseCode;

    private String backCode;

    private String operatorName;

    private String createTime;

    private Integer bookCount;

    private Integer bookSum;

    private Double totalPrice;


}
