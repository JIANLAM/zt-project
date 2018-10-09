package com.szcti.lcloud.book.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 图书信息VO
 * @Description: 图书信息
 * @author: fengda
 * @date: 2018/5/30 15:13
 */
@Data
public class BookVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String createTime;

    private String createBy;

    private String title;

    private String pic;

    private String summary;

    private String author;

    private Float price;

    private String publisher;

    private String pubDate;

    private String bookType;

    private String ISBN;

    private Integer bookCount;

    private Integer collectionCount;

    private Integer bookFrom;

}
