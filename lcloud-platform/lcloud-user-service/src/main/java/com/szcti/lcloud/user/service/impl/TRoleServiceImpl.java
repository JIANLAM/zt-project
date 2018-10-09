package com.szcti.lcloud.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.user.entity.TButton;
import com.szcti.lcloud.user.entity.TOrganizationType;
import com.szcti.lcloud.user.entity.TPerm;
import com.szcti.lcloud.user.entity.TPermButton;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.TRolePerm;
import com.szcti.lcloud.user.entity.TRolePermButton;
import com.szcti.lcloud.user.entity.vo.MenuVO.Menu;
import com.szcti.lcloud.user.entity.vo.QueryRoleVo;
import com.szcti.lcloud.user.entity.vo.RolePermButtonVO;
import com.szcti.lcloud.user.entity.vo.RolePermVO;
import com.szcti.lcloud.user.mapper.TButtonMapper;
import com.szcti.lcloud.user.mapper.TOrganizationTypeMapper;
import com.szcti.lcloud.user.mapper.TPermButtonMapper;
import com.szcti.lcloud.user.mapper.TPermMapper;
import com.szcti.lcloud.user.mapper.TRoleMapper;
import com.szcti.lcloud.user.mapper.TRolePermButtonMapper;
import com.szcti.lcloud.user.mapper.TRolePermMapper;
import com.szcti.lcloud.user.service.TRoleService;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author dw
 * @since 2018-06-13
 */
@Service
public class TRoleServiceImpl extends ServiceImpl<TRoleMapper, TRole> implements TRoleService {

	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TPermMapper permMapper;
	@Autowired
	private TRolePermMapper rolePermMapper;
	@Autowired
	private TOrganizationTypeMapper organizationTypeMapper;
	@Autowired
	private TRolePermButtonMapper rolePermButtonMapper;
	@Autowired
	private TPermButtonMapper permButtonMapper;
	@Autowired
	private TButtonMapper buttonMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addRole(RolePermVO rpv) {
		
		roleMapper.insert(rpv);
		List<TRolePerm>  rpls = rpv.getPermList();
		for (TRolePerm tRolePerm : rpls) {
			tRolePerm.setRoleId(rpv.getId());
			tRolePerm.setCreateTime(new Date());
			rolePermMapper.insert(tRolePerm);
		}
		List<TRolePermButton> bls = rpv.getPermButtonList();
		if(bls!=null) {
			for (TRolePermButton tRolePermButton : bls) {
				
				tRolePermButton.setRoleId(rpv.getId());
				rolePermButtonMapper.insert(tRolePermButton);
			}
		}
		return 0;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateRole(RolePermVO rpv) {
		
		roleMapper.updateById(rpv);
		rolePermMapper.delete(new EntityWrapper<TRolePerm>().eq("role_id", rpv.getId()));
		
		List<TRolePerm>  rpls = rpv.getPermList();
		
		for (TRolePerm tRolePerm : rpls) {
			tRolePerm.setRoleId(rpv.getId());
			tRolePerm.setCreateTime(new Date());
			rolePermMapper.insert(tRolePerm);
		}
		
		rolePermButtonMapper.delete(new EntityWrapper<TRolePermButton>().eq("role_id", rpv.getId()));
		
		List<TRolePermButton> bls = rpv.getPermButtonList();
		if(bls!=null) {
			for (TRolePermButton tRolePermButton : bls) {
				
				tRolePermButton.setRoleId(rpv.getId());
				rolePermButtonMapper.insert(tRolePermButton);
			}
		}
		
		return 0;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int copyRole(List<RolePermVO> rpvls) {
		for (RolePermVO rpv : rpvls) {
			
			Long rid = rpv.getId();
			
			TRole role = roleMapper.selectById(rid);
			role.setId(null);
			role.setRoleName("copy "+role.getRoleName());
			roleMapper.insert(role);
			
			List<TRolePerm> rpls  =rolePermMapper.selectList(new EntityWrapper<TRolePerm>().eq("role_id", rid));
			
			for (TRolePerm tRolePerm : rpls) {
				tRolePerm.setRoleId(role.getId());
				tRolePerm.setCreateTime(new Date());
				rolePermMapper.insert(tRolePerm);
			}
		}
		
		return 0;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int delRole(List<RolePermVO> rpvls) {
		for (RolePermVO rpv : rpvls) {
			Long rid = rpv.getId();
			roleMapper.deleteById(rid);
			rolePermMapper.delete(new EntityWrapper<TRolePerm>().eq("role_id", rid));
			rolePermButtonMapper.delete(new EntityWrapper<TRolePermButton>().eq("role_id", rid));
		}
		return 0;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo findRoleList(QueryRoleVo qrv,long libid){
		
		PageHelper.startPage(qrv.getPageNum(), qrv.getPageSize());
		EntityWrapper ew =new EntityWrapper();
		if(libid>-1) {
			ew.eq("lib_id", libid);
		}
		if(StringUtils.hasLength(qrv.getRoleName())) {
			ew.like("role_name", qrv.getRoleName());
		}
		if(StringUtils.hasLength(qrv.getRoleCode())) {
			ew.like("role_code", qrv.getRoleCode());
		}
		if(StringUtils.hasLength(qrv.getCreateAccount())) {
			ew.like("create_account", qrv.getCreateAccount());
		}
		if(StringUtils.hasLength(qrv.getCreateName())) {
			ew.like("create_name", qrv.getCreateName());
		}
		if(StringUtils.hasLength(qrv.getOrgType())) {
			
			List<String> ins =new ArrayList<>();
			List<TOrganizationType>  otls = organizationTypeMapper.selectList(new EntityWrapper<TOrganizationType>().like("name", qrv.getOrgType()));
			if(otls.size()>0) {
				for (TOrganizationType tOrganizationType : otls) {
					ins.add(String.valueOf(tOrganizationType.getId()));
				}
				ew.in("org_type", ins);
			}
		}
		List<TRole> rlist =  roleMapper.selectList(ew);
		
		PageInfo p = new PageInfo(rlist);
		
		List<RolePermVO> rpvlist = new ArrayList<>();
		
		for (TRole tRole : rlist) {
			RolePermVO rpo=new RolePermVO();
			BeanUtils.copyProperties(tRole, rpo);;
			List<TRolePerm> rplist = rolePermMapper.selectList(new EntityWrapper<TRolePerm>().eq("role_id", tRole.getId()));
			rpo.setPermList(rplist);
			rpvlist.add(rpo);
		}
		p.setList(rpvlist);
		
		return p;
	}
	
	@Override
	public List<Menu> findPermList(int type){
		List<String> columns=new ArrayList<>();
		columns.add("parent_id");
		columns.add("sort");
		String ty="library";
		if(type==0) {
			ty="reader";
		}
		if(type==2) {
			ty="operate";
		}
		List<TPerm> pls =  permMapper.selectList(new EntityWrapper<TPerm>().eq("perm_type", ty).orderAsc(columns));
		
		List<Menu> m1 = new ArrayList<>();
		for (TPerm TPerm : pls) {
			
			TPerm.setName(TPerm.getName().trim());
			TPerm.setPath(TPerm.getPath().trim());
			TPerm.setComponent(TPerm.getComponent().trim());
			
			Menu menu1 = new Menu();
			if (TPerm.getParentId() == 0) {

				BeanUtils.copyProperties(TPerm, menu1);

				m1.add(menu1);
			}
		}

		for (Menu menu : m1) {
			for (TPerm TPerm : pls) {
				if (TPerm.getParentId().longValue() == menu.getId().longValue()) {
					Menu menu2 = new Menu();

					BeanUtils.copyProperties(TPerm, menu2);
					
					List<TPermButton>  tpbls = permButtonMapper.selectList(new EntityWrapper<TPermButton>().eq("perm_id", TPerm.getId()));
					List<RolePermButtonVO> rpbvols = new ArrayList<>();
					for (TPermButton tRolePermButton : tpbls) {
						
						RolePermButtonVO vo = new RolePermButtonVO();
						TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
						BeanUtils.copyProperties(tRolePermButton, vo);
						vo.setName(butt.getName());
						vo.setCode(butt.getCode());
						vo.setIcon(butt.getIcon());
						vo.setId(butt.getId().longValue());
						rpbvols.add(vo);
					}
					menu2.setPower(rpbvols);
					
					menu.getChildren().add(menu2);
					
					for (TPerm p3 : pls) {
						if(p3.getParentId().longValue() == menu2.getId().longValue()) {
							Menu menu3 = new Menu();
							BeanUtils.copyProperties(p3, menu3);
							menu3.setParentParentId(menu.getId());
							
							List<TPermButton>  tpbls3 = permButtonMapper.selectList(new EntityWrapper<TPermButton>().eq("perm_id", p3.getId()));
							List<RolePermButtonVO> rpbvols3 = new ArrayList<>();
							for (TPermButton tRolePermButton : tpbls3) {
								
								RolePermButtonVO vo = new RolePermButtonVO();
								TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
								BeanUtils.copyProperties(tRolePermButton, vo);
								vo.setName(butt.getName());
								vo.setCode(butt.getCode());
								vo.setIcon(butt.getIcon());
								vo.setId(butt.getId().longValue());
								rpbvols3.add(vo);
							}
							menu3.setPower(rpbvols3);
							
							menu2.getChildren().add(menu3);
							
						}
					}
					
				}
			}
		}
		
		
		
		return m1;
	}
	@Override
	public List<Menu> findRoleDetail(Long roleid,String userType){
		

			List<TRolePerm> rplist = rolePermMapper
					.selectList(new EntityWrapper<TRolePerm>().eq("role_id", roleid));
			List<Long> l = new ArrayList<>();
			for (TRolePerm tRolePerm : rplist) {
				Long pid = tRolePerm.getPermId();
				l.add(pid);
			}
			
			List<TPerm> pls = permMapper.selectList(new EntityWrapper<TPerm>().eq("perm_type",userType).in("id", l));

			List<Menu> m1 = new ArrayList<>();
			for (TPerm TPerm : pls) {
				
				TPerm.setName(TPerm.getName().trim());
				TPerm.setPath(TPerm.getPath().trim());
				TPerm.setComponent(TPerm.getComponent().trim());
				
				Menu menu1 = new Menu();
				if (TPerm.getParentId() == 0) {

					BeanUtils.copyProperties(TPerm, menu1);

					m1.add(menu1);
				}
			}

			for (Menu menu : m1) {
				for (TPerm TPerm : pls) {
					if (TPerm.getParentId().longValue() == menu.getId().longValue()) {
						Menu menu2 = new Menu();

						BeanUtils.copyProperties(TPerm, menu2);
						
						List<TRolePermButton>  tpbls = rolePermButtonMapper.selectList(new EntityWrapper<TRolePermButton>().eq("perm_id", TPerm.getId()).and().eq("role_id", roleid));
						List<RolePermButtonVO> rpbvols = new ArrayList<>();
						for (TRolePermButton tRolePermButton : tpbls) {
							
							RolePermButtonVO vo = new RolePermButtonVO();
							TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
							BeanUtils.copyProperties(tRolePermButton, vo);
							vo.setName(butt.getName());
							vo.setCode(butt.getCode());
							vo.setIcon(butt.getIcon());
							vo.setId(butt.getId().longValue());
							rpbvols.add(vo);
						}
						menu2.setPower(rpbvols);
						
						menu.getChildren().add(menu2);
						
						for (TPerm p3 : pls) {
							if(p3.getParentId().longValue() == menu2.getId().longValue()) {
								Menu menu3 = new Menu();
								BeanUtils.copyProperties(p3, menu3);
								
								List<TRolePermButton>  tpbls3 = rolePermButtonMapper.selectList(new EntityWrapper<TRolePermButton>().eq("perm_id", p3.getId()).and().eq("role_id", roleid));
								List<RolePermButtonVO> rpbvols3 = new ArrayList<>();
								for (TRolePermButton tRolePermButton : tpbls3) {
									
									RolePermButtonVO vo = new RolePermButtonVO();
									TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
									BeanUtils.copyProperties(tRolePermButton, vo);
									vo.setName(butt.getName());
									vo.setCode(butt.getCode());
									vo.setIcon(butt.getIcon());
									vo.setId(butt.getId().longValue());
									rpbvols3.add(vo);
								}
								menu3.setPower(rpbvols3);
								menu3.setParentParentId(menu.getId().longValue());
								menu2.getChildren().add(menu3);
								
							}
						}
						
					}
				}
			}
			
			
			return m1;
			
		}
	
	@Override
	public List<TRole> findRoleList(long libid){
		EntityWrapper ew =new EntityWrapper();
		if(libid>-1) {
			ew.eq("lib_id", libid);
		}
		return roleMapper.selectList(ew);
	}
	
	
}
