package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询参数
    private String searchKey;//检索字段值为:|title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Integer source;//书的来源：1新华集团2本馆图书3豆瓣图书4成员馆图书

    private String libraryName;
    private Long libraryId;
    private Long orderId;

    private Long id;

    private String pic;

    private String title;

    private String author;

    private String publisher;

    private String pubdate;

    private String bookType;

    private String isbn;

    private Float price;

    private Long createBy;

    private String createTime;

    private String summary;
    private  Integer pages;
    private List ids;
    private List libraryIdList;
    }