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

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.MenuVO;
import com.szcti.lcloud.user.entity.vo.QueryUserVo;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;
import com.szcti.lcloud.user.service.UserService;

/**
 * @Title: 我的账户信息api
 * @Description: 我的账户信息pai
 * @author: fengda
 * @date: 2018/5/16 9:02
 */
@Component
@Path("user")
public class UserResource {

	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param jsonStr
	 *            {loginName:"d"}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R userlist(@QueryParam("jsonStr") String jsonStr) {
		try {
			QueryUserVo qrv = new QueryUserVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryUserVo) JSONUtil.json2Object(jsonStr, QueryUserVo.class);
			}

			PageInfo p = userService.getUserList(qrv);

			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Path("/admlist")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R admlist(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {
		try {
			TUser u = userService.getUserByToken(authToken);
			QueryUserVo qrv = new QueryUserVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryUserVo) JSONUtil.json2Object(jsonStr, QueryUserVo.class);
			}
			qrv.setType(1);
			qrv.setOrgId(u.getOrgId());

			PageInfo p = userService.getUserList(qrv);

			return R.ok().put("page", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@Path("/detail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R userdetail(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken) {

		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}

		try {
			QueryUserVo qrv = new QueryUserVo();
			if (StringUtils.isNotEmpty(jsonStr)) {
				qrv = (QueryUserVo) JSONUtil.json2Object(jsonStr, QueryUserVo.class);
				if (qrv.getUserId() != null) {
					UserInfoVO v = userService.getUserInfo(qrv.getUserId());

					R r = R.ok().put("user", v);
					// 获取菜单
					if (v.getType() == 1) {// 机构用户

						MenuVO mv = userService.getUserMenu(v.getUserId(), "library");
						r.put("menu", mv);
					} else if (v.getType() == 2) {// 系统用户
						MenuVO mv = userService.getUserMenu(v.getUserId(), "operate");
						r.put("menu", mv);
					} else {

					}

					return r;
				}
			}

			return R.error(400, "用户ID不能为空。");

		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@Path("/menu")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R usermenu(@HeaderParam("Authorization") String authToken) {

		try {
			TUser u = userService.getUserByToken(authToken);
			if (u != null) {
				log.info(u.getLoginName());

				UserInfoVO v = userService.getUserInfo(u.getId());

				R r = R.ok().put("user", v);
				// 获取菜单
				if (v.getType() == 1) {// 机构用户

					MenuVO mv = userService.getUserMenu(v.getUserId(), "library");
					r.put("menu", mv);
				} else if (v.getType() == 2) {// 系统用户
					MenuVO mv = userService.getUserMenu(v.getUserId(), "operate");
					r.put("menu", mv);
				} else if (v.getType() == 0) {
					MenuVO mv = userService.getUserMenu(v.getUserId(), "reader");
					r.put("menu", mv);
				}

				return r;

			} else {

				return R.error().put("msg", "此用户不存在。");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 
	 * @param data
	 *            {"loginName":"fd","userName":"冯达","password":"123456","status":1,"sex":1,"email":"1113158392@qq.com","phone":"13720201004","remark":null,"roleId":1}
	 * @return
	 */
	@Path("/add")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R saveUserInfo(String data, @HeaderParam("Authorization") String authToken) {

		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}

		try {
			UserInfoVO user = new UserInfoVO();
			if (StringUtils.isNotEmpty(data)) {
				user = (UserInfoVO) JSONUtil.json2Object(data, UserInfoVO.class);
				TUser tu = userService.getUserByName(user.getLoginName());
				if (tu != null) {
					return R.error(409, "用户名已存在。");
				} else {
					if (u != null) {
						user.setCreateAccount(u.getLoginName());
						user.setCreateBy(u.getId());
						user.setCreateName(u.getLoginName());
					}
					user.setType(0);
					userService.saveUserInfo(user);
				}
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}
	@Path("/admadd")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R admadd(String data, @HeaderParam("Authorization") String authToken) {
		
		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}
		
		try {
			UserInfoVO user = new UserInfoVO();
			if (StringUtils.isNotEmpty(data)) {
				user = (UserInfoVO) JSONUtil.json2Object(data, UserInfoVO.class);
				TUser tu = userService.getUserByName(user.getLoginName());
				if (tu != null) {
					return R.error(409, "用户名已存在。");
				} else {
					if (u != null) {
						user.setCreateAccount(u.getLoginName());
						user.setCreateBy(u.getId());
						user.setCreateName(u.getLoginName());
					}
					user.setType(1);
					user.setOrgId(u.getOrgId());
					userService.saveUserInfo(user);
				}
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 
	 * @param data
	 *            {"userId":1,"loginName":"fd","userName":"冯达","password":"123456","status":1,"sex":1,"email":"1113158392@qq.com","phone":"13720201004","remark":null,"roleId":1}
	 * @return
	 */
	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R updateUserInfo(String data, @HeaderParam("Authorization") String authToken) {

		TUser u = userService.getUserByToken(authToken);
		if (u != null) {
			log.info(u.getLoginName());
		}

		try {
			UserInfoVO user = new UserInfoVO();
			if (StringUtils.isNotEmpty(data)) {
				user = (UserInfoVO) JSONUtil.json2Object(data, UserInfoVO.class);

				if (StringUtils.isNotEmpty(user.getLoginName())) {
					TUser tu = userService.getUserByName(user.getLoginName());
					if (tu != null && tu.getId().longValue() != user.getUserId().longValue()) {
						return R.error(409, "用户名已存在。");
					}
				}
				userService.saveUserInfo(user);
			}
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 
	 * @param data
	 *            [{userId:2},{userId:3},{userId:1}]
	 * @return
	 */
	@Path("/delete")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R userdelete(String data) {
		try {
			Gson gson = new Gson();
			List<UserInfoVO> rpvls = gson.fromJson(data, new TypeToken<List<UserInfoVO>>() {
			}.getType());
			userService.deleteUserInfo(rpvls);

			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * 
	 * @param data
	 *            [{userId:2},{userId:3},{userId:1}]
	 * @return
	 */
	@Path("/copy")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R usercopy(String data) {
		try {
			Gson gson = new Gson();
			List<UserInfoVO> rpvls = gson.fromJson(data, new TypeToken<List<UserInfoVO>>() {
			}.getType());
			userService.copyUserInfo(rpvls);

			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

}
