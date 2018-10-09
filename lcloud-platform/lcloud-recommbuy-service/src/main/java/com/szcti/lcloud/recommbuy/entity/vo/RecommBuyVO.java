package com.szcti.lcloud.recommbuy.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 荐购表
 * 
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@Data
public class RecommBuyVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;

	//查询条件
	private String searchKey;//检索字段值为:readerCardNumber|title|isbn|author|publisher
	private String searchValue;//检索值
	private String timeKey;//时间字段
	private Long id;
	private Long recount;
	private Long libraryId;
	private Long readerId;
	private String readerCardNumber;//读者证号
	private String createTime;
	private Integer status;//0未购买，1购买中，2已入藏
	private Integer bookStatus;//书籍状态 1未入藏，2预借，3代借
	private String creatorName;
	private Long preBookId;
	private Long orderId;
	private String pic;
	private String title;
	private String author;
	private String publisher;
	private String pubdate;
	private String bookType;
	private String isbn;
	private Float price;
	private Integer inType;
	private Long createBy;
	private Integer source;
	private String goodsCode;
	private String summary;
	private String userName;
	private String recommFrom;
	private List preId;//书id


	private String  qualification;      //查询条件 微信端 根据书名或者作者查询
	private String  libraryName;        //所属图书馆名字
}
