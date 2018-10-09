package com.szcti.lcloud.user.service.impl;

import com.szcti.lcloud.user.entity.TPerm;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.TRolePerm;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.TUserRole;
import com.szcti.lcloud.user.mapper.TPermMapper;
import com.szcti.lcloud.user.mapper.TRolePermMapper;
import com.szcti.lcloud.user.mapper.TUserMapper;
import com.szcti.lcloud.user.mapper.TUserRoleMapper;
import com.szcti.lcloud.user.service.TUserRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author dw
 * @since 2018-06-13
 */
@Service("userRoleService")
public class TUserRoleServiceImpl extends ServiceImpl<TUserRoleMapper, TUserRole> implements TUserRoleService {

	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TUserRoleMapper userRoleMapper;
	@Autowired
	private TRolePermMapper rolePermMapper;
	@Autowired
	private TPermMapper permMapper;
	
	@Override
	public List<TPerm> getUserPerm(String loginName){
		
		List<TPerm> permlist =new ArrayList<>();
		TUser entity=new TUser();
		entity.setLoginName(loginName);
		TUser user = userMapper.selectOne(entity);
		if(user !=null) {
			TUserRole ur =new TUserRole();
			ur.setUserId(user.getId());
			TUserRole urole = userRoleMapper.selectOne(ur);
			if(urole !=null) {
				List<TRolePerm> rplist = rolePermMapper.selectList(new EntityWrapper<TRolePerm>().eq("role_id", urole.getId()));
				for (TRolePerm tRolePerm : rplist) {
					TPerm  perm = permMapper.selectById(tRolePerm.getPermId());
					permlist.add(perm);
				}
			}
		}
		
		return permlist;
	}
	@Override
	public List<String> getUserPerm(Long userid){
		
		   List<String> permlist =new ArrayList<>();
			TUserRole ur =new TUserRole();
			ur.setUserId(userid);
			TUserRole urole = userRoleMapper.selectOne(ur);
			if(urole !=null) {
				List<TRolePerm> rplist = rolePermMapper.selectList(new EntityWrapper<TRolePerm>().eq("role_id", urole.getId()));
				for (TRolePerm tRolePerm : rplist) {
					TPerm  perm = permMapper.selectById(tRolePerm.getPermId());
					permlist.add(perm.getUrl());
				}
			}
		
		
		return permlist;
	}
	
	@Override
	public TUserRole getUserRole(Long userid) {
		TUserRole ur =new TUserRole();
		ur.setUserId(userid);
		return userRoleMapper.selectOne(ur);
	}
}
