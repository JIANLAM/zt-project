package com.szcti.lcloud.lendbuy.config;

import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.R;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtFilter extends GenericFilterBean {
	
	private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

	

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("Authorization");
		String path = request.getRequestURI();

		log.info(authHeader);
		
		if (path.matches(".*/token/auth")) {
			chain.doFilter(req, res);
			return;
		}

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {

			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//				throw new RRException("Missing or invalid Authorization header");
				R r = R.error(401, "Unauthorized");
				response.setStatus(401);
				PrintWriter pw = response.getWriter();
				pw.write(JSONUtil.object2String(r));
				pw.flush();
				pw.close();
				return;
			}

			try {
				
				HttpServletRequest reqs = (HttpServletRequest) request;
				ServletContext sc = reqs.getSession().getServletContext();
				WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
				String secret = cxt.getEnvironment().getProperty("jwt.secret");
				
				String token = authHeader.substring(7);
				
				Claims claims = decodeClaims(token,secret);
//				request.setAttribute("claims", claims);
//				long userid = Long.valueOf(String.valueOf(claims.get("userid")));
//				request.setAttribute("requestUserId", userid);
				
			}catch (Exception e) {
				e.printStackTrace();
				
				log.error(e.getLocalizedMessage());
				
				R r = R.error(401, "Unauthorized");
				response.setStatus(401);
				PrintWriter pw = response.getWriter();
				pw.write(JSONUtil.object2String(r));
				pw.flush();
				pw.close();
				return;
				
			}
					
//			if (cxt != null && cxt.getBean("userRoleService") != null && userRoleService == null) {
//				userRoleService = (TUserRoleService) cxt.getBean("userRoleService");
//			}
//
//			List<String> s = userRoleService.getUserPerm(userid);
//			boolean fg = true;
//			for (String string : s) {
//				if(path.matches(string)) {
//					fg = false;
//					break;
//				}
//			}
//			if (fg) {
//				throw new RRException("Insufficient permissions");
//			}

			chain.doFilter(req, res);
		}
	}

	
	public Claims decodeClaims(String token,String secret) {
		 Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		 return claims;
	}
}
