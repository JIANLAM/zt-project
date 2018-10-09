package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 退订详情信息VO
 * @Description: 退订详情的信息
 * @author: fengda
 * @date: 2018/8/9 15:13
 */
@Data
public class BackDetailVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long backPurchaseId;

    private Long libId;

    private String title;

    private String author;

    private String publisher;

    private String bookType;

    private String ISBN;

    private Double price;

    private Integer purchaseNum;

    private Integer backNum;

}
