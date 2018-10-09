package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
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
    private Integer pages;
    private Integer bookrecno;
    private String rowid;
    private String seriesTitle;
    private String secondTitle;
    private String subjectWord;
    private String firstDuty;
    private String revision;
    private String language;
    private String carrierType;
    private String binding;
    private String summary;
    private String source;
    private String bookSize;
    private String parallelTitle;
}