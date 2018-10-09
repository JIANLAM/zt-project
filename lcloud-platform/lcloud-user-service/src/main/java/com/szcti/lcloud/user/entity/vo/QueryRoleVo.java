package com.szcti.lcloud.user.entity.vo;

import java.io.Serializable;

import com.szcti.lcloud.common.utils.QueryParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QueryRoleVo  extends QueryParam implements Serializable{
	
	private static final long serialVersionUID = 6178620222695487711L;
	
	private String roleName;
   
    private String roleCode;
    private String createAccount;
    private String createName;
    private String orgType;
    private Long roleId;

}
