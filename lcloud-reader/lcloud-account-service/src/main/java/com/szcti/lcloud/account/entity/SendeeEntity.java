package com.szcti.lcloud.account.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 收件人信息表
 * 
 * @author fengda
 * @email ${email}
 * @date 2018-05-04 17:03:30
 */
@Data
public class SendeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long peopleId;

	private String name;

	private String address;

	private String sendeePhone;

	private Integer isDefault;

}
