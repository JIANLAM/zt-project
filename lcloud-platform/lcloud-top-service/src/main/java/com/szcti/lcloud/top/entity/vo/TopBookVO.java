package com.szcti.lcloud.top.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;
import java.io.Serializable;

/**
 * 荐购表
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TopBookVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;

	//查询条件
	private String searchKey;//检索字段值为:author|title|isbn|author|publisher
	private String searchValue;//检索值
	private String timeKey;//时间字段
	private String bookFrom;//书的来源:1本馆t_book表2新华(推荐图书功能用)
	private String source;//书的来源t_prebook表
	private String isLocal;//是否限于本馆排行0否1是
	private Long readerId;//读者id
	private String readerCardNumber;//读者证
	private String readerCardType;//读者类型
	private String sex;//性别
    private Long userId;//用户id
    private String userName;//名字
    private String nickName;//昵称
    private Long id;//馆藏id
	private Long recount;//次数
	private Long libraryId;//图书馆id
	private Long bookId;//书的id
	private String pic;//封面
	private String title;//书名
	private String author;//作者
	private String publisher;//出版社
	private String pubdate;//出版日期
	private String bookType;//书类号
	private String isbn;//isbn
	private Float price;//价格
	private String goodsCode;//商品编号
	private String summary;//简介
	private String libraryName; //所属图书馆名字
	private String callNo;//索书号
	private String signature;//签名
	private String icon;//图标
	private String export;//1:导出
}
