package com.szcti.lcloud.lendbuy.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 借购规则VO
 * @Description: 借购规则VO
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LendBuyRuleVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//id
	private String ruleName;//规则名称
	private Integer bookNameAllow;
	private String bookName;
	private Long createBy;//创建者
	private String creatorName;
	private String createTime;
	private Long updateBy;//修改者
	private String updaterName;
	private String updateTime;
	private Integer status;//1启用，0不启用
	private Integer duplicateQty;//复本数
	private String publishYear;//出版年份
	private String categoryNo;//分类号
	private Float bookPrice;//书的价格
	private String bookSize;//书的开本
	private Integer bookPages;//书的页数
	private String publisher;//出版社
	private Integer publishYearAllow;//出版年份1只允许，0不限制，-1只禁止
	private Integer categoryNoAllow;//分类号1只允许，0不限制，-1只禁止
	private Integer bookSizeAllow;//书的大小1只允许，0不限制，-1只禁止
	private Integer bookPagesAllow;//书的页数1只允许，0不限制，-1只禁止
	private Integer publisherAllow;//书的出版社1只允许，0不限制，-1只禁止
	private Integer readerType;
	private Integer readerTypeAllow;
	private Integer credit;//信用值
	private Integer lendBuyDays;  //允许借购的时间（天）
	private Integer autoReceivedDays; //自动签收时间（天）
	private Float overduePayRate; //逾期罚款率
	private Float lostPayRate; //丢失罚款率
	private Float brokenPayRate; //污损罚款率
	private Long libraryId;//图书馆id
	private String summary;

}
