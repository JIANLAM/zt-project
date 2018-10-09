package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 财经记录VO
 * @Description: 财经记录信息
 * @author: fengda
 * @date: 2018/8/8 15:13
 */
@Data
public class FinanceVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String readerCardNumber;
    private String cardNumber;

    private String userName;

    private Long libId;
    private String libName;

    private String operatorName;

    private String typeName;

    private String financeDatetime;

    private Double forfeit;

    private String payMethod;


}
