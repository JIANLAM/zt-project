package com.szcti.lcloud.circulate.entity.vo;

import com.szcti.lcloud.circulate.entity.TLend;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReaderLendVO extends TLend {

	private static final long serialVersionUID = -5934717761726562827L;

	private Integer readerCardType;
	/**
	 * 证件号码
	 */
	private String readerCardNumber;
	/**
	 * 状态0禁用1正常2注销3挂失
	 */
	private Integer status;
	/**
	 * 读者类型
	 */
	private Integer readerType;
	private String readerTypeName;

	/**
	 * 所属图书馆id
	 */
	private Long libraryId;

	private String ownlibName;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;

	private String title;
	private String callNo;
	private String barcode;
	
	private String collAddress;
	private String actType;

}
