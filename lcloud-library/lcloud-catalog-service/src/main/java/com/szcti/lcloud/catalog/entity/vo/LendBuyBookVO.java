package com.szcti.lcloud.catalog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LendBuyBookVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:isbn|title|author|publisher|pubdate|readerCardNumber|readerName
    private String searchValue;//检索值
    private String timeKey;//时间字段

    private Long id;//借购书记录id
    private Long prebookId;//书的id
    private Long libraryId;//图书馆id
    private String libraryName;//图书馆名称
    private String readerCardNumber;//读者证号
    private String isbn;//isbn
    private String bookType;//图书分类名称
    private String subject;//分类号
    private String title;//书的标题
    private String author;//作者
    private String publisher;//出版社
    private String pubdate;//出版时间
    private String source;//获得方式
    private int backCount;//已还书数量
    private int total;//总数
    private int recount;//副本数
    private String acceptCode;//验收单号
    private Book book;
    private List<Long> ids;//借购的书记录id
}