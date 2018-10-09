package com.szcti.lcloud.user.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.szcti.lcloud.user.entity.TPerm;
import com.szcti.lcloud.user.entity.TRolePermButton;

public class MenuVO {

	private Long userId;
	//private String userName;
	private String userType;
	private List<Menu> routers =new ArrayList<>();
	
	
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


//	public String getUserName() {
//		return userName;
//	}
//
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public List<Menu> getRouters() {
		return routers;
	}


	public void setRouters(List<Menu> routers) {
		this.routers = routers;
	}


	public static class Menu extends TPerm{
		
		private static final long serialVersionUID = 4326287681018617271L;
		
		private List<Menu> children =new ArrayList<>();
		
		private List<RolePermButtonVO> power =new ArrayList<>();

		private Long parentParentId=0L;
		
		public List<Menu> getChildren() {
			return children;
		}

		public void setChildren(List<Menu> children) {
			this.children = children;
		}

		public List<RolePermButtonVO> getPower() {
			return power;
		}

		public void setPower(List<RolePermButtonVO> power) {
			this.power = power;
		}

		public Long getParentParentId() {
			return parentParentId;
		}

		public void setParentParentId(Long parentParentId) {
			this.parentParentId = parentParentId;
		}
		
		
		
	}
}
