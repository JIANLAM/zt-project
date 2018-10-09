package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderbatchBookVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    //查询条件
    private String searchKey;//检索字段值为:
    private String searchValue;//检索值
    private String timeKey;//时间字段

    private Long id;//发运批中的书id
    private Long orderBatchId;//发运批id
    private Long purchaseBookId;//订购单的书id
    private String title;//书名
    private String bookType;//书分类号
    private String isbn;//isbn
    private String author;//作者
    private String publisher;//出版社
    private String pubdate;//出版日期
    private Float price;//单价
    private Integer orderQty;//订购数
    private Integer bookQty;//发运批中书的数量
    private Integer checkedQty;//验收数量
    private Long checker;//验收者
    private String checkTime;//验收时间
    private String status;//到货状态0未验收1完全到货2缺货
    private String checkmsg;//验收结果信息
    private String remark;//备注
    private String isHandIn;//是否已经提交
    //关联
    private String orderId;//订购单id
    private Long libraryId;
    private String purchaseCode;//订购号
    private String orderCode;//订单号
    private String ruleId;//规则id
    private String orderStatus;//订单状态
    private String orderTime;//订单下单时间
    private String createTime;//发货单创建时间
    private String coding;//发货批次号
    private String acceptStatus;//发货批状态0未验收1验收完2部分验收3有缺货
    private String supplierId;//供应商id
    private List ids;//验收单书列表id数组
    private List statusList;//数组 发货单验收书中状态 验收状态0未验收1通过2缺货
    private List orderStatusList;//数组 订单状态：0申请中2已审批3已发货4部分验收5补发货6已完成7退回
    private List acceptStatusList;//数组 发货单验收状态0未验收1验收完2部分验收3有缺货
}