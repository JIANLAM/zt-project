package com.szcti.lcloud.user.entity.vo;

import java.util.List;

import com.szcti.lcloud.user.entity.TOrganization;

public class OrganizationVO extends TOrganization {

	private static final long serialVersionUID = 8833487104299866669L;

	private List<OrganizationVO> children;

	public List<OrganizationVO> getChildren() {
		return children;
	}

	public void setChildren(List<OrganizationVO> children) {
		this.children = children;
	}

}
