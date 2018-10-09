package com.szcti.lcloud.newbook.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NewBook {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long orderId;

    private String pic;

    private String title;

    private String author;

    private String publisher;

    private Date pubdate;

    private String bookType;

    private String isbn;

    private Float price;

    private Integer inType;

    private Integer status;

    private Long createBy;

    private Date createTime;

    private Byte source;

    private String goodsCode;

    private String summary;
}