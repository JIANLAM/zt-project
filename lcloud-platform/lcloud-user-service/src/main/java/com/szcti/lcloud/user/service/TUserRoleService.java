package com.szcti.lcloud.user.service;

import com.szcti.lcloud.user.entity.TPerm;
import com.szcti.lcloud.user.entity.TUserRole;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author dw
 * @since 2018-06-13
 */
public interface TUserRoleService extends IService<TUserRole> {

	List<TPerm> getUserPerm(String loginName);

	TUserRole getUserRole(Long userid);

	List<String> getUserPerm(Long userid);

}
