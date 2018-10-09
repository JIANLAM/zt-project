package com.szcti.lcloud.user.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.entity.TRole;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.MenuVO.Menu;
import com.szcti.lcloud.user.entity.vo.QueryRoleVo;
import com.szcti.lcloud.user.entity.vo.RolePermVO;
import com.szcti.lcloud.user.service.TRoleService;
import com.szcti.lcloud.user.service.UserService;

@Component
@Path("role")
public class RoleResource {
	
	private static final Logger log = LoggerFactory.getLogger(RoleResource.class);

	@Autowired
	private TRoleService roleService;
	
	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param jsonStr 	{roleName:"新华"} / {roleCode:"A001",pageNum:2,pageSize:20}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R rolelist(@QueryParam("jsonStr") String jsonStr , @HeaderParam("Authorization") String authToken) {
		log.debug(jsonStr);
		try {
			
			TUser u = userService.getUserByToken(authToken);
			long libid=-1;
			if (u != null) {
				log.info(u.getLoginName());
				int ut = u.getType();
				if(ut==1) {
					libid=u.getOrgId();
				}
			}
			
			QueryRoleVo qrv = new QueryRoleVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryRoleVo) JSONUtil.json2Object(jsonStr, QueryRoleVo.class);
			}

			PageInfo p = roleService.findRoleList(qrv,libid);
			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@Path("/detail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R roledetail(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken) {
		log.debug(jsonStr);
		try {
			TUser u = userService.getUserByToken(authToken);
			
			QueryRoleVo qrv = new QueryRoleVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryRoleVo) JSONUtil.json2Object(jsonStr, QueryRoleVo.class);
			}
			if(qrv.getRoleId() !=null) {
				
				TRole t = roleService.selectById(qrv.getRoleId());
				String ty="library";
				if(u.getType()==2) {
					ty="operate";
				}
				if(u.getType()==0) {
					ty="reader";
				}
				List<Menu> p = roleService.findRoleDetail(qrv.getRoleId(),ty);
				return R.ok().put("role", t).put("list", p);
			}
			return R.error().put("msg", "角色ID不能为空。");
			
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 角色list，用于下拉框
	 * @return
	 */
	@Path("/litelist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R rolelitelist( @HeaderParam("Authorization") String authToken) {
		try {
			
			TUser u = userService.getUserByToken(authToken);
			long libid=-1;
			if (u != null) {
				log.info(u.getLoginName());
				int ut = u.getType();
				if(ut==1) {
					libid=u.getOrgId();
				}
			}
			
			List<TRole> p = roleService.findRoleList(libid);
			return R.ok().put("list", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 
	 * @param jsonStr {roleName:"新角色",roleCode:"R101",status:1,remark:"beizhu备注",permList:[{permId:1},{permId:2}]}
	 * @return
	 */
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R roleadd(String jsonStr, @HeaderParam("Authorization") String authToken) {
   		
   		TUser u = userService.getUserByToken(authToken);
   		long libid=0;
   		if(u!=null) {
   			log.info(u.getLoginName());
   			int ut = u.getType();
			if(ut==1) {
				libid=u.getOrgId();
			}
   		}
   		
		log.debug(jsonStr);
		try {
			Gson gson = new Gson();
			RolePermVO rpv = gson.fromJson(jsonStr, new TypeToken<RolePermVO>(){}.getType());
			
			int c = roleService.selectCount(new EntityWrapper<TRole>().eq("role_name", rpv.getRoleName()));
			if(c>0) {
				return R.error(409,"角色名称重复。");
			}
			if(u!=null) {
				rpv.setCreateAccount(u.getLoginName());
				rpv.setCreateBy(u.getId());
				rpv.setCreateName(u.getLoginName());
				rpv.setLibId(libid);
			}
			
			int r = roleService.addRole(rpv);
			if (r == 0) {
				return R.ok();
			} else {
				return R.error(r, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 
	 * @param jsonStr {id:7,roleName:"新角色88",roleCode:"R10188",status:1,remark:"beizhu备注",permList:[{permId:1},{permId:2},{permId:3}]}
	 * @return
	 */
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R roleupdate(String jsonStr, @HeaderParam("Authorization") String authToken) {
   		
   		TUser u = userService.getUserByToken(authToken);
   		long libid=0;
   		if(u!=null) {
   			log.info(u.getLoginName());
   			int ut = u.getType();
			if(ut==1) {
				libid=u.getOrgId();
			}
   		}
		log.debug(jsonStr);
		try {
			Gson gson = new Gson();
			RolePermVO rpv = gson.fromJson(jsonStr, new TypeToken<RolePermVO>(){}.getType());
			rpv.setLibId(libid);
			int r = roleService.updateRole(rpv);
			if (r == 0) {
				return R.ok();
			} else {
				return R.error(r, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	/**
	 * 
	 * @param jsonStr [{id:9},{id:10}]
	 * @return
	 */
	@Path("/copy")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R rolecopy(String jsonStr) {
		log.debug(jsonStr);
		try {
			Gson gson = new Gson();
			List<RolePermVO> rpv = gson.fromJson(jsonStr, new TypeToken<List<RolePermVO>>(){}.getType());
						
			int r = roleService.copyRole(rpv);
			if (r == 0) {
				return R.ok();
			} else {
				return R.error(r, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	/**
	 * 
	 * @param jsonStr  [{id:9},{id:10}]
	 * @return
	 */
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R roledelete(String jsonStr) {
		log.debug(jsonStr);
		try {
			Gson gson = new Gson();
			List<RolePermVO> rpv = gson.fromJson(jsonStr, new TypeToken<List<RolePermVO>>(){}.getType());
						
			int r = roleService.delRole(rpv);
			if (r == 0) {
				return R.ok();
			} else {
				return R.error(r, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
}
