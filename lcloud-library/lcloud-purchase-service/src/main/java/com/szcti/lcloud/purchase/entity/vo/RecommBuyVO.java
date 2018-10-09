package com.szcti.lcloud.purchase.entity.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

/**
 * 荐购表
 * 
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RecommBuyVO extends QueryParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long prebookId;

	private Long readerId;

	private Date createTime;
	private Integer status;//状态0申请中，1审批，2运输中，3已到馆可借阅
	private Integer bookStatus;//书籍状态 1未入藏，2预借，3代借

}
