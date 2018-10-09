package com.szcti.lcloud.circulate.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.szcti.lcloud.circulate.entity.TReader;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Title: 读者VO
 * @Description: 读者信息
 * @author: fengda
 * @date: 2018/5/15 18:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReaderVO extends TReader implements Serializable {

	private static final long serialVersionUID = -8098658150437157587L;

	private String libraryName = "";

	private String readerName = "";
	private String sex = "";

	private String IDCard;
	private String birthday;
	private String phone;
	private String readerTypeName;
	private String password;
	private Date createDate;
	/**
	 * 积分
	 */
	private Integer credit;// 积分
	/**
	 * 可借
	 */
	private Integer lendNum;// 可借
	/**
	 * 已借
	 */
	private Integer borrow;// 已借
	
	private List<LendBuyVO> lendbuyls;

	// public static void main(String[] args) {
	// System.out.println(JsonUtil.beanToJson(new ReaderVO()));
	// }
}
