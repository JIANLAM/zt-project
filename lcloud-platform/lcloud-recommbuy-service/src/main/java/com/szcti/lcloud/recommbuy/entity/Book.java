package com.szcti.lcloud.recommbuy.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Book {
    private Long id;

    private String pic;

    private String title;

    private String author;

    private String publisher;

    private Date pubdate;

    private String bookType;

    private String isbn;

    private Float price;

    private Long createBy;

    private Date createTime;

    private String summary;

    }