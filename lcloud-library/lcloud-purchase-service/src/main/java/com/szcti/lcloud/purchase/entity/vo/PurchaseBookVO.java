package com.szcti.lcloud.purchase.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import com.szcti.lcloud.purchase.entity.PurchaseBook;

import lombok.Data;

/**
 * 
 * 
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PurchaseBookVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	//查询参数
	private String searchKey;//检索字段值为:purchaseCode|orderCode|creatorAccount|creatorName|title|isbn|author|publisher
	private String searchValue;//检索值
	private String timeKey;//时间字段
	private String checkStatusSearch;//审核状态条件
	private String source;//书的来源：1新华集团2本馆图书3豆瓣图书4成员馆图书
	private String orderCodeHave;//订单号为空，0表示为空的查询，1表示订单号不能为空的查询，空则查所有

	private Long id;//订购单中的书一条记录id
	private Long orderId;//订购id
	private String libraryName;//图书馆名
	private String purchaseCode;//订购号 关联表t_purchase_order
	private Long ruleId;//订购规则
	private String orderCode;//订单号 关联表t_purchase_order
	private String title;//书名 关联表t_prebook
	private String author;//作者 关联表t_prebook
	private String isbn;//
	private String publisher;//出版者 关联表t_prebook
	private String bookType;//书类型 关联表t_prebook
	private String pubdate;//出版日期 关联表t_prebook
	private Float price;//单价 关联表t_prebook
	private Integer bookQty;//订购书数量
	private Float totalPrice;//总价
	private String checkedmsg;//审核结果
	private String expressCode;//快递单号
	private String creatorName;//创建人
	private Long libraryId;//馆藏id
	private Long bookId; //关联表t_book
	private Long prebookId;//关联表t_prebook
	private Long prebookTempId;
	private String status;//书的状态:0待审核1通过2不通过3已提交4已发货5部分验收6验收完全7已到馆8退回
	private String orderStatus;//订单状态：1申请中2已审批3已发货4部分验收5补发货6已完成7退回
	private String checkStatus;//订购单的审核总状态：0待审核1通过2不通过3无需审核
	private Integer pages;//书的页数
	private Float bookSize;//书的尺寸大小
	private int batchQty;//发货数量
	private int checkedQty;//实际验收数
	private int remainQty;//剩余数量
	private String batchMsg;//验收结果
	private List ids;
	private List statusList;//数组 书的状态
	private List orderStatusList;//数组 订单状态
	private List checkStatusList;//数组 订购单提交前审核状态
	private List<PurchaseBook> purchasebooklist;
}
