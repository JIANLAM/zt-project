package com.szcti.lcloud.dataquery.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 馆藏信息VO
 * @Description: 借书馆藏的信息
 * @author: fengda
 * @date: 2018/8/7 15:13
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HoldingVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long bookId;

    private Long libId;
    private String libName;

    private String barCode;

    private String readerCardNumber;

    private String bookType;

    private String callNo;

    private Long actType;
    private String actName;

    private Long shelf;

    private Integer status;

    private String lendTime;

    private String dueBackTime;

    private Double price;

    private Integer reLendCount;




    private String title;

    private String author;

    private Integer lendStatus;

    private String cardNumber;

}
