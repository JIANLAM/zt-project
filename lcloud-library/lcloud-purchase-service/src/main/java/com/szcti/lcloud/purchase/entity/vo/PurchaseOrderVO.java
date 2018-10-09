package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PurchaseOrderVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	//查询条件
	private String searchKey;//检索字段值为:purchaseCode|orderCode|creatorAccount|creatorName|title|isbn|author|publisher
	private String searchValue;//检索值
	private String timeKey;//时间字段
	private String checkStatusSearch;//查询条件审核状态：0未审核，1通过，2不通过,3无需审核
	private String orderCodeHave;//订单号为空，0表示为空的查询，1表示订单号不能为空的查询，空则查所有
	//征订目录 页面结果
	private Long id;//订购ID
	private String purchaseCode;//订购号
	private String creatorAccount;//创建帐户-联表查询
	private String creatorName;//创建者姓名-联表查询
	private String createTime;//创建时间
	private Integer bookTypeQty;//图书种类数
	private Integer totalQuantity;//总册数
	private Float totalPrice;//总价
	private String checkerAccount;//审核者帐户-联表查询
	private String checkerName;//审核者姓名-联表查询
	private String checkTime;//审核时间
	private String checkStatus;//审核状态：0未审核，1通过，2不通过,3无需审核
	private String summary;//备注
	//订购单查询 页面结果 附加
	private Long budgetId;//经费预算id
	private String budgetCode;//经费编码
	private Long supplierId;//供应商
	private String supplierName;//供应商名
	private Float budgetAmount;//预算
	private Long libraryId;
	private String libraryName;
	private String orderCode;//订单号
	private String expressCode;//快递单号
	private String committerAccount;//提交者帐户-联表查询
	private String committerName;//提交者姓名-联表查询
	private String orderTime;//订单创建时间
	private String orderStatus;//订单状态：0申请中2已审批3已发货4部分验收5补发货6已完成7退回
	private String expressInfo;//快递信息
	private Long sendeeId;//收货人
	private String address;//收货地址
	private Long creator;//创建者id
	private Long checker;//审核者id
	private Long committer;//订单提交者id
	private Long ruleId;//订购规则id
	private String ruleName;//订购规则名称
	private List ids;
	private List<OrderBatchVO> batchs;//订单验收批次
	private List orderStatusList;//数组 订单状态
	private List checkStatusList;//数组 订购单提交前审核状态
}
