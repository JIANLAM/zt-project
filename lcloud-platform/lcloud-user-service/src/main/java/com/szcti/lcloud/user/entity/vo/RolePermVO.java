package com.szcti.lcloud.user.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.TRolePerm;
import com.szcti.lcloud.user.entity.TRolePermButton;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RolePermVO extends TRole implements Serializable {

	private static final long serialVersionUID = -8905870444681517091L;

	List<TRolePerm> permList;
	List<TRolePermButton> permButtonList;
	
}
