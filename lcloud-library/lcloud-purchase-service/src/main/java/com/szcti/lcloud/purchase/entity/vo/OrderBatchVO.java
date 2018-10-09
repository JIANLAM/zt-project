package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderBatchVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:purchaseCode|orderCode|creatorName|checkerName|coding|supplierName
    private String searchValue;//检索值
    private String timeKey;//时间字段

    private Long id;//发运批id
    private Long libraryId;//图书馆id
    private String coding;//发运批号
    private String purchaseCode;//订购号
    private String orderCode;//订单号
    private String expressCode;//快递号
    private Long creator;//创建人id
    private String creatorName;//创建人名
    private String createTime;//创建时间
    private Integer bookTypeQty;//书种类数
    private Integer totalQuantity;//书的数量
    private Long supplierId;//供应商
    private String supplierName;//供应商名
    private Long butgetId;//经费预算id
    private String butgetCode;//经费编码
    private Long checker;//校验者
    private String checkerName;
    private String checkTime;//校验时间
    private String remark;//备注
    private String acceptStatus;//验收状态
    private String orderStatus;//订单状态：0申请中2已审批3已发货4部分验收5补发货6已完成7退回
    private List ids;
    private String acceptCode;//验收单号
    private List<OrderbatchBookVO> booklist;
}