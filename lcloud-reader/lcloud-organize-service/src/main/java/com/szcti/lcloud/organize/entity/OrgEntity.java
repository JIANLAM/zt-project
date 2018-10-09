package com.szcti.lcloud.organize.entity;

import com.szcti.lcloud.common.utils.BaseTree;
import lombok.Data;

import java.io.Serializable;

/**
 * 组织机构信息表
 * 
 * @author fengda
 * @email ${email}
 * @date 2018-06-09 17:03:30
 */
@Data
public class OrgEntity extends BaseTree implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;

	private String linkmanName;

	private String linkmanPhone;

	private String city;

	private Integer sex;

	private Integer type;

	private String remark;

	private Integer saleType;

	private Integer isLendbuy;

}
