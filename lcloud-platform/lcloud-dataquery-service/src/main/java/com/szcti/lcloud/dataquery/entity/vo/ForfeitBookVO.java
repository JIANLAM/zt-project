package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 罚款书籍信息VO
 * @Description: 罚款书籍的相关信息
 * @author: fengda
 * @date: 2018/8/8 15:13
 */
@Data
public class ForfeitBookVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long libId;

    private String libName;

    private String barCode;

    private String title;

    private String author;

    private String publisher;

    private Long actType;
    private String actName;

    private String callNo;

    private String createDate;

    private String operatorName;

    private Double price;

    private Double forfeit;


    private Integer type;


}
