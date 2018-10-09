package com.szcti.lcloud.book.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 图书副本VO
 * @Description: 图书副本信息
 * @author: fengda
 * @date: 2018/5/30 15:13
 */
@Data
public class BookCopyVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long recId;

    private String callNo;

    private Long bookId;

    private String barCode;

    private Long ownLib;
    private String ownLibName;

    private Long curLib;
    private String curLibName;

    private Long shelf;

    private String inDate;

    private String publisher;

    private Float singlePrice;

    private Float totalPrice;

    private Integer book_source;

    private Integer status;

    private  String dueBackTime;
}
