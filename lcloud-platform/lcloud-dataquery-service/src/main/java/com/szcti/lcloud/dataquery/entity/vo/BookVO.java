package com.szcti.lcloud.dataquery.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借阅图书信息VO
 * @Description: 借阅图书的信息
 * @author: fengda
 * @date: 2018/8/6 15:13
 */
@Data
public class BookVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long bookId;

    private Long libId;
    private String libName;

    private String title;

    private String author;

    private String publisher;

    private String pubDate;

    private String bookType;

    private String ISBN;

    private String callNo;

    private String barCode;

    private Double price;

    private Integer pages;


}
