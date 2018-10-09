package com.szcti.lcloud.newbook.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NewBookVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    //查询参数
    private String search;//参数search：new/hot 按新书/热书查询
    private String searchKey;//检索字段值为:|title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段
    private Integer source;//书的来源：1新华集团2本馆图书3豆瓣图书4成员馆图书
    private Long libraryId;//图书馆id
    private String orderBydesc;
    private Long id;//id

    private String title;//图书名

    private String author;//作者

    private String publisher;//出版社

    private String pubdate;//出版日期

    private String bookType;//分类号

    private String isbn;//isbn

    private Float price;//单价

    private Byte inType;//

    private Byte status;//

    private Long createBy;//

    private String createTime;//

    private String summary;//简介
    private String storageTime;//入库时间
    }