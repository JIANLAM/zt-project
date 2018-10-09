package com.szcti.lcloud.purchase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import com.szcti.lcloud.purchase.entity.BookSize;
import com.szcti.lcloud.purchase.entity.Publisher;
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
public class PurchaseRuleVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String ruleName;//规则名称
	private Long checker;//审核者
	private String checkerName;
	private Long creator;//创建者
	private String createTime;//创建时间
	private Long budgetId;//预算id
	private String budgetPrice;//预算价格
	private String status;//0启用，1不启用
	private Integer duplicateQty;//复本数
	private String publishYear;//出版年份
	private String categoryNo;//分类号
	private String bookPrice;//书的价格
	private String bookSize;//书的大小尺寸
	private String pages;//书的页数
	private String publisher;//出版社
	private Long libraryId;//图书馆id
	private String publishYearAllow;//出版年份0只禁止，1只允许
	private String categoryNoAllow;//分类号0只禁止，1只允许
	private String bookSizeAllow;//书的大小0只禁止，1只允许
	private String bookPagesAllow;//书的页数0只禁止，1只允许
	private String publisherAllow;//书的出版社0只禁止，1只允许
	private String prefix;//订购号前缀
	private String totalnum;//订购号后面的随机总位数
	private String startnum;//最小的起始数字
	private String summary;//备注
	private List<BookSize> bookSizeList;
	private List<Publisher> publisherList;
}
