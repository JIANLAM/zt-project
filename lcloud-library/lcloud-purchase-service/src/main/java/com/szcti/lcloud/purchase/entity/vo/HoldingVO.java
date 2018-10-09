package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class HoldingVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String callNo;

    private Long bookId;

    private String barcode;

    private Long ownlib;

    private Long curlib;

    private Long shelf;

    private String indate;

    private Float singleprice;

    private Float totalprice;

    private Integer source;

    private Integer status;

    //馆藏书信息
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
}