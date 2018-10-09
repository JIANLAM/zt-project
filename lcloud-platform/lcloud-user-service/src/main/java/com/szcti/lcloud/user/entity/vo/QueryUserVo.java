package com.szcti.lcloud.user.entity.vo;

import java.io.Serializable;

import com.szcti.lcloud.common.utils.QueryParam;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QueryUserVo  extends QueryParam implements Serializable{
	
	private static final long serialVersionUID = 61786202695487711L;
	
	private String loginName;
   
    private String userName;
    
    private String roleName;
    private String createAccount;
    
    private Long userId;
    
    private Long orgId;
    
    private int type;

}
