package com.szcti.lcloud.user.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.MenuVO.Menu;
import com.szcti.lcloud.user.service.TRoleService;
import com.szcti.lcloud.user.service.UserService;

@Component
@Path("perm")
public class PermResource {

	@Autowired
	private TRoleService roleService;
	@Autowired
	private UserService userService;

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public R rolelist(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken) {
		try {

			TUser u = userService.getUserByToken(authToken);
			List<Menu> p = roleService.findPermList(u.getType());
			return R.ok().put("list", p);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

}
