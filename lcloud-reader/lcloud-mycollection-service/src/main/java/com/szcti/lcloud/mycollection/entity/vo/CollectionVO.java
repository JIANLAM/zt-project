package com.szcti.lcloud.mycollection.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 个人收藏记录VO
 * @Description: 包含个人收藏信息，以及收藏书籍的信息
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class CollectionVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long preBookId;

    private Long readerId;
    private String userName;

    private String createTime;

    private Integer collectFrom;

    private String title;

    private String pic;

    private String author;

    private String publisher;

    private String bookType;

    private String ISBN;

    private String summary;

    private Integer status;

    private String  qualification;      //查询条件 微信端 根据书名或者作者查询

    private String pubdate;         //出版日期

    private Float price;    //价格

    private Long collCount; //馆藏数

    private String barcode; //条码号

}
