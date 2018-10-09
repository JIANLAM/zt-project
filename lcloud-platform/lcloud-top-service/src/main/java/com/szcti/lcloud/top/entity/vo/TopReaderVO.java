package com.szcti.lcloud.top.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * 荐购表
 * 
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TopReaderVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;

	//查询条件
	private String searchKey;//检索字段值为:author|title|isbn|author|publisher
	private String searchValue;//检索值
	private String timeKey;//时间字段
	private Long id;
	private Long recount;
	private Long libraryId;
	private Long readerId;
	private Long bookId;
	private String pic;
	private String title;
	private String author;
	private String publisher;
	private String pubdate;
	private String bookType;
	private String isbn;
	private Float price;
	private Integer source;
	private String goodsCode;
	private String summary;
	private String userName;
	private String  libraryName; //所属图书馆名字
}
