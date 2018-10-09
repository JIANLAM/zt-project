package com.szcti.lcloud.user.entity.vo;

import com.szcti.lcloud.user.entity.TRolePermButton;


public class RolePermButtonVO extends TRolePermButton {

	private static final long serialVersionUID = -259140757222221828L;

	private String name;
	private String code;
	private String icon;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
