package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BookInfo  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long purchaseBookId;
    private Long libraryId;

    private String title;

    private String author;

    private String publisher;

    private String pubdate;

    private String bookType;

    private String isbn;

    private Float price;

    private String  barcode;//商品条码
    private String source;//图书来源
    private Integer orderStatus;//订单状态://状态0申请中，1审批，2运输中，3已到馆可借阅
    private Integer status;//订购书状态 审核状态：0未审核，1通过，2不通过,3运输中，4已到馆可借阅

 }