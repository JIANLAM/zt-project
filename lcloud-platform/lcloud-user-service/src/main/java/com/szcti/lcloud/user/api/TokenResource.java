package com.szcti.lcloud.user.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.szcti.lcloud.common.utils.HttpServletRequestUtil;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.user.entity.TLoginLog;
import com.szcti.lcloud.user.entity.TUser;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;
import com.szcti.lcloud.user.mapper.TLoginLogMapper;
import com.szcti.lcloud.user.mapper.TUserMapper;
import com.szcti.lcloud.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;

@Component
@Path("token")
public class TokenResource {

	@Autowired
	private UserService userService;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TLoginLogMapper loginLogMapper;

	@Value("${jwt.expires_in}")
	private int expires_in;
	
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * 
	 * @param data
	 *            {"loginName":"fd","password":"123456"}
	 * @return
	 */
	@Path("/auth")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public R auth(String data,@Context HttpServletRequest request, @Context HttpServletResponse respose) {
		try {
			UserInfoVO user = new UserInfoVO();
			if (StringUtils.isNotEmpty(data)) {
				user = (UserInfoVO) JSONUtil.json2Object(data, UserInfoVO.class);
				if(user.getLoginName()==null ) {
					return R.error().put("msg", "用户名不能为空。");
				}
				if( user.getPassword()==null) {
					return R.error().put("msg", "密码不能为空。");
				}
				if(user.getType()==null) {
					return R.error().put("msg", "登录平台不能为空。");
				}
				TUser u = userService.getUserByName(user.getLoginName());
				if (u != null ) {
					
					if(u.getType()==null || (u.getType().intValue() !=user.getType().intValue())) {
						return R.error().put("msg", "无权登录此平台！请联系管理员。");
					}
					
					if (u.getPassword().equals(user.getPassword())) {
                        UserInfoVO userInfoVO=userService.getUserInfo(u.getId());
						String jwtToken = Jwts.builder()
								.setHeaderParam("typ", "JWT")//
								.setSubject(u.getLoginName())//
								.claim("userid", u.getId())//
								.claim("readerid",userInfoVO.getReaderId())
								.claim("userType",userInfoVO.getType())
                                .claim("peopleid", userInfoVO.getPeopleId())
								.claim("libraryid", u.getOrgId())//登录图书馆端token加libraryid

                                //.claim("roleid", ur.getRoleId())//
								.setIssuedAt(new Date())//
								.setExpiration(new Date(System.currentTimeMillis() + expires_in*1000))
								.signWith(SignatureAlgorithm.HS256, secret)//
								.compact();

						R r= R.ok().put("token", jwtToken).put("user", u)
								.put("readerid", userInfoVO.getReaderId())
								.put("icon", userInfoVO.getIcon())
								.put("peopleid", userInfoVO.getPeopleId());
												
						Date d = u.getLoginDate();
						if(d ==null) {
							d =new Date();
						}
						u.setLoginDate(new Date());
						u.setLastLoginDate(d);
						userMapper.updateById(u);
						
						String ip = HttpServletRequestUtil.getIpAddr(request);
						
						TLoginLog entity=new TLoginLog();
						entity.setLoginIp(ip);
						entity.setLoginTime(new Date());
						entity.setUserId(u.getId());
						
						loginLogMapper.insert(entity);
						
						return r;
					}
					return R.error().put("msg", "用户名或密码错误。");
				}
				return R.error().put("msg", "此用户不存在。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}

	@Path("/refresh")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public R refresh(@HeaderParam("Authorization") String authToken) {
		try {
			if(StringUtils.isNotBlank(authToken) && authToken.length()>7) {
				
				String token = authToken.substring(7);
				Claims claims = Jwts.parser()
						.setSigningKey(secret)//
						.parseClaimsJws(token)//
						.getBody();
				claims.setIssuedAt(new Date());
				claims.setExpiration(new Date(System.currentTimeMillis() + expires_in*1000));
				
				String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
				
				return R.ok().put("token", jwtToken);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error(400, e.getLocalizedMessage());
		}
		
		return R.error(400 , "认证失败。");
	}
	
	
	@Path("/modifyPwd")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public R modifyPwd(String data) {
		try {
			UserInfoVO user = new UserInfoVO();
			if (StringUtils.isNotEmpty(data)) {
				user = (UserInfoVO) JSONUtil.json2Object(data, UserInfoVO.class);
				TUser u = userService.getUserById(user.getUserId());
				if (u != null) {
					if (u.getPassword().equals(user.getPassword())) {
						userService.modifyPwd(user.getUserId(),u.getPassword(),user.getNewPassword());
						
						return R.ok();
					}else {
						
						return R.error().put("msg", "原密码错误");
					}
				}
				return R.error().put("msg", "用户不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}

	public Claims decodeClaims(String token) {
		 Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		 return claims;
	}
}
