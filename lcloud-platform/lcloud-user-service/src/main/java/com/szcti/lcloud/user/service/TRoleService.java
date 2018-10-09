package com.szcti.lcloud.user.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.vo.MenuVO.Menu;
import com.szcti.lcloud.user.entity.vo.QueryRoleVo;
import com.szcti.lcloud.user.entity.vo.RolePermVO;;;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author dw
 * @since 2018-06-13
 */
public interface TRoleService extends IService<TRole> {

	int addRole(RolePermVO rpv);

	List<Menu> findPermList(int type);

	PageInfo findRoleList(QueryRoleVo qrv,long libid);

	int updateRole(RolePermVO rpv);

	int copyRole(List<RolePermVO> rpvls);

	int delRole(List<RolePermVO> rpvls);

	List<TRole> findRoleList(long libid);

	List<Menu> findRoleDetail(Long roleid,String userType);

	
}
