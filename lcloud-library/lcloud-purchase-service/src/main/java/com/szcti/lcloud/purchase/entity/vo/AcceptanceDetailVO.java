package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AcceptanceDetailVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:purchaseCode|acceptCode|title|isbn|author|publisher
    private String searchValue;//检索值
    private String timeKey;//时间字段

    private Long id;
    private Long acceptanceId;
    private Long orderbatchBookId;
    private String purchaseCode;//订购号
    private Long purchaseBookId;
    private Long orderId;//订购单ID
    private String isbn;//isbn
    private Long libraryId;
    private Integer shipQuantity;//发货数量
    private Integer acceptQuantity;//验收数量
    private Integer recount;//馆藏数量
    private Integer catalogQty;//编目数量
    private String acceptCode;//验收单号
    private String callNo;//索书号
    private String barcode;//条形码
    private String bookType;//图书分类名称
    private String subject;//分类号
    private String libraryName;//图书馆名称
    private String title;//书的标题
    private Float  price;//价格
    private String author;//作者
    private String publisher;//出版社
    private String pubdate;//出版日期
    private String orderTime;//下单时间
    private String acceptTime;//验收时间
}