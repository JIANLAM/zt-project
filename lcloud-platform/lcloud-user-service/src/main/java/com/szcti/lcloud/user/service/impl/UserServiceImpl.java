package com.szcti.lcloud.user.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.user.entity.TButton;
import com.szcti.lcloud.user.entity.TOrganization;
import com.szcti.lcloud.user.entity.TPeople;
import com.szcti.lcloud.user.entity.TPerm;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.TRolePerm;
import com.szcti.lcloud.user.entity.TRolePermButton;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.TUserRole;
import com.szcti.lcloud.user.entity.vo.MenuVO;
import com.szcti.lcloud.user.entity.vo.MenuVO.Menu;
import com.szcti.lcloud.user.entity.vo.QueryUserVo;
import com.szcti.lcloud.user.entity.vo.ReaderVO;
import com.szcti.lcloud.user.entity.vo.RolePermButtonVO;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;
import com.szcti.lcloud.user.mapper.TButtonMapper;
import com.szcti.lcloud.user.mapper.TOrganizationMapper;
import com.szcti.lcloud.user.mapper.TPeopleMapper;
import com.szcti.lcloud.user.mapper.TPermMapper;
import com.szcti.lcloud.user.mapper.TRoleMapper;
import com.szcti.lcloud.user.mapper.TRolePermButtonMapper;
import com.szcti.lcloud.user.mapper.TRolePermMapper;
import com.szcti.lcloud.user.mapper.TUserMapper;
import com.szcti.lcloud.user.mapper.TUserRoleMapper;
import com.szcti.lcloud.user.repository.UserDao;
import com.szcti.lcloud.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Value("${jwt.secret}")
	private String secret;
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private TUserRoleMapper userRoleMapper;
	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TRolePermMapper rolePermMapper;
	@Autowired
	private TPermMapper permMapper;
	@Autowired
	private TOrganizationMapper organizationMapper;
	@Autowired
	private TPeopleMapper peopleMapper;
	@Autowired
	private TRolePermButtonMapper rolePermButtonMapper;
	@Autowired
	private TButtonMapper buttonMapper;

	@Override
	public MenuVO getUserMenu(Long uid,String userType) {
		MenuVO m = new MenuVO();
		TUser u = userMapper.selectById(uid);
		if (u != null) {

			m.setUserId(uid);
			//m.setUserName(u.getLoginName());
			m.setUserType(u.getType() + "");

			TUserRole entity = new TUserRole();
			entity.setUserId(uid);
			TUserRole ur = userRoleMapper.selectOne(entity);

			if (ur != null) {

				List<TRolePerm> rplist = rolePermMapper
						.selectList(new EntityWrapper<TRolePerm>().eq("role_id", ur.getRoleId()));
				List<Long> l = new ArrayList<>();
				for (TRolePerm tRolePerm : rplist) {
					Long pid = tRolePerm.getPermId();
					l.add(pid);

				}
				List<String> columns=new ArrayList<>();
				columns.add("parent_id");
				columns.add("sort");
				List<TPerm> pls = permMapper.selectList(new EntityWrapper<TPerm>().eq("perm_type",userType).in("id", l).orderAsc(columns));

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
							
							List<TRolePermButton>  tpbls = rolePermButtonMapper.selectList(new EntityWrapper<TRolePermButton>().eq("role_id", ur.getRoleId()).and().eq("perm_id", TPerm.getId()));
							List<RolePermButtonVO> rpbvols = new ArrayList<>();
							for (TRolePermButton tRolePermButton : tpbls) {
								
								RolePermButtonVO vo = new RolePermButtonVO();
								TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
								BeanUtils.copyProperties(tRolePermButton, vo);
								vo.setName(butt.getName());
								vo.setCode(butt.getCode());
								rpbvols.add(vo);
							}
							menu2.setPower(rpbvols);
							
							menu.getChildren().add(menu2);
							
							for (TPerm p3 : pls) {
								if(p3.getParentId().longValue() == menu2.getId().longValue()) {
									Menu menu3 = new Menu();
									BeanUtils.copyProperties(p3, menu3);
									
									List<TRolePermButton>  tpbls3 = rolePermButtonMapper.selectList(new EntityWrapper<TRolePermButton>().eq("role_id", ur.getRoleId()).and().eq("perm_id", p3.getId()));
									List<RolePermButtonVO> rpbvols3 = new ArrayList<>();
									for (TRolePermButton tRolePermButton : tpbls3) {
										
										RolePermButtonVO vo = new RolePermButtonVO();
										TButton butt = buttonMapper.selectById(tRolePermButton.getButtonId());
										BeanUtils.copyProperties(tRolePermButton, vo);
										vo.setName(butt.getName());
										vo.setCode(butt.getCode());
										rpbvols3.add(vo);
									}
									menu3.setPower(rpbvols3);
									
									menu2.getChildren().add(menu3);
									
								}
							}
							
						}
					}
				}
				
				m.setRouters(m1);
			}
		}

		return m;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PageInfo getUserList(QueryUserVo qrv) {

		PageHelper.startPage(qrv.getPageNum(), qrv.getPageSize());

		List<UserInfoVO> rlist = userDao.getUserList(qrv.getUserName(), qrv.getLoginName(), qrv.getCreateAccount(),qrv.getType(),qrv.getOrgId());
		PageInfo p = new PageInfo(rlist);
		for (UserInfoVO userInfoVO : rlist) {
			Long orgid = userInfoVO.getOrgId();
			TOrganization org = organizationMapper.selectById(orgid);
			if (org != null) {
				userInfoVO.setOrganization(org.getName());
				if(org.getParentId()!=null ) {
					TOrganization porg = organizationMapper.selectById(org.getParentId());
					if(porg != null){
						userInfoVO.setOrganization(porg.getName() +"-"+org.getName());
					}else{
						userInfoVO.setOrganization(org.getName());
					}
				}
				
			}
			TUserRole entity = new TUserRole();
			entity.setUserId(userInfoVO.getUserId());
			TUserRole ur = userRoleMapper.selectOne(entity);
			if (ur != null) {
				TRole role = roleMapper.selectById(ur.getRoleId());
				if (role != null) {
					userInfoVO.setRoleId(ur.getRoleId());
					userInfoVO.setRoleName(role.getRoleName());
				}
			}
		}
		return p;
	}

	@Override
	public TUser getUserByName(String loginName) {
		TUser u = new TUser();
		u.setLoginName(loginName);
		return userMapper.selectOne(u);
	}

	@Override
	@Transactional(readOnly = true)
	public UserInfoVO getUserInfo(@NonNull Long userId) {
		UserInfoVO userInfoVO= userDao.getUserInfo(userId);
		
		Long orgid = userInfoVO.getOrgId();
		TOrganization org = organizationMapper.selectById(orgid);
		if (org != null) {
			userInfoVO.setOrganization(org.getName());
			if(org.getParentId()!=null ) {
				TOrganization porg = organizationMapper.selectById(org.getParentId());
				if(porg!=null) {
					
					userInfoVO.setOrganization(porg.getName() +"-"+org.getName());
				}else {
					userInfoVO.setOrganization("-");
				}
			}
			
		}
		TUserRole entity = new TUserRole();
		entity.setUserId(userInfoVO.getUserId());
		TUserRole ur = userRoleMapper.selectOne(entity);
		if (ur != null) {
			TRole role = roleMapper.selectById(ur.getRoleId());
			if (role != null) {
				userInfoVO.setRoleId(ur.getRoleId());
				userInfoVO.setRoleName(role.getRoleName());
			}
		}
		
		return userInfoVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String modifyPwd(@NonNull Long userId, @NonNull String oldPassword, @NonNull String newPassword) {
//		UserInfoVO userVO = getUserInfo(userId);
//		String flag;
//		if (userVO != null && oldPassword.equals(userVO.getPassword())) {
			userDao.modifyPwd(userId, newPassword);
//			flag = "success";
//		} else {
//			flag = "false";
//		}
		return "success";
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUserInfo(UserInfoVO userVO) {

		if (userVO != null && userVO.getUserId() != null) {
			userDao.updateUser(userVO);
			
			TPeople entity=new TPeople();
			entity.setUserId(userVO.getUserId());
			TPeople peo =peopleMapper.selectOne(entity);
			if(peo !=null) {
				userDao.updatePeople(userVO);
			}else {
				Long peopleId = IdGen.randomLong();
				userVO.setPeopleId(peopleId);
				userVO.setCreateTime(new Date());
				userDao.insertPeople(userVO);
			}

			if (userVO.getRoleId() != null) {
				userRoleMapper.delete(new EntityWrapper<TUserRole>().eq("user_id", userVO.getUserId()));

				TUserRole ur = new TUserRole();
				ur.setRoleId(userVO.getRoleId());
				ur.setUserId(userVO.getUserId());
				ur.setUpdateTime(new Date());
				userRoleMapper.insert(ur);
			}

		} else {
			Long userId = IdGen.randomLong();
			userVO.setUserId(userId);
			userVO.setPeopleId(userId);
			userVO.setCreateTime(new Date());
			userDao.insertUser(userVO);
			userDao.insertPeople(userVO);

			if (userVO.getRoleId() != null) {

				TUserRole ur = new TUserRole();
				ur.setRoleId(userVO.getRoleId());
				ur.setUserId(userId);
				ur.setUpdateTime(new Date());
				userRoleMapper.insert(ur);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void copyUserInfo(List<UserInfoVO> userVOList) {

		for (UserInfoVO userInfoVO : userVOList) {
			long userid = userInfoVO.getUserId();

			UserInfoVO uinfo = getUserInfo(userid);

			TUserRole entity = new TUserRole();
			entity.setUserId(userInfoVO.getUserId());
			TUserRole ur = userRoleMapper.selectOne(entity);
			if (ur != null) {
				uinfo.setRoleId(ur.getRoleId());
			}

			Long userId = IdGen.randomLong();
			Long peopleId = IdGen.randomLong();

			uinfo.setUserId(userId);
			uinfo.setPeopleId(peopleId);
			uinfo.setLoginName(uinfo.getLoginName() + userId);
			uinfo.setUserName("copy " + uinfo.getUserName());
			uinfo.setCardNumber("");
			uinfo.setCardType(0);
			uinfo.setEmail("");
			uinfo.setPhone("");

			userDao.insertUser(uinfo);
			userDao.insertPeople(uinfo);

			if (uinfo.getRoleId() != null) {

				TUserRole urole = new TUserRole();
				urole.setRoleId(uinfo.getRoleId());
				urole.setUserId(userId);
				urole.setUpdateTime(new Date());
				urole.setCreateTime(new Date());
				userRoleMapper.insert(urole);
			}

		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUserInfo(List<UserInfoVO> userVOList) {

		for (UserInfoVO userInfoVO : userVOList) {
			long userid = userInfoVO.getUserId();
			userMapper.deleteById(userid);
			userRoleMapper.deleteById(userid);
			peopleMapper.deleteById(userid);
			
			//userDao.deleteUser(userInfoVO.getUserId());
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveReader(ReaderVO readerVO) {
		if (readerVO != null && readerVO.getId() != null && readerVO.getPeopleId() != null) {
			userDao.updateReader(readerVO);
		} else {
			Long id = IdGen.randomLong();
			readerVO.setId(id);
			userDao.insertReader(readerVO);
		}
	}
	@Override
	public TUser getUserById(Long userid) {
		return userMapper.selectById(userid);
	}
	@Override
	public TUser getUserByToken(String  authHeader) {
		TUser user =null;
		try {
			if(StringUtils.hasLength(authHeader) && authHeader.length()>7) {
				
				String token = authHeader.substring(7);
				Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
				long userid = Long.valueOf(String.valueOf(claims.get("userid")));
				user =userMapper.selectById(userid);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
