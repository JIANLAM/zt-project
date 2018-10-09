package com.szcti.lcloud.exchange.idc.service.impl;

import static java.util.Arrays.asList;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.szcti.lcloud.exchange.idc.entity.Authority;
import com.szcti.lcloud.exchange.idc.entity.User;
import com.szcti.lcloud.exchange.idc.exception.MyRuntimeException;
import com.szcti.lcloud.exchange.idc.repository.UserRepository;
import com.szcti.lcloud.exchange.idc.security.JwtTokenUtil;
import com.szcti.lcloud.exchange.idc.security.JwtUser;
import com.szcti.lcloud.exchange.idc.service.AuthService;
import com.szcti.lcloud.exchange.idc.vo.R;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
	@Override
	public User register(User userToAdd) {
		final String username = userToAdd.getAppid();
        if(userRepository.findByAppid(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getSecret();
        userToAdd.setEnabled(true);
        userToAdd.setSecret(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        Calendar rightNow = Calendar.getInstance();
    	rightNow.add(Calendar.YEAR,+5);
        userToAdd.setExpired(rightNow.getTime());
        Authority authority = new Authority();
        authority.setId(Long.valueOf(1));
        userToAdd.setAuthorities(asList(authority));
//        userToAdd.setRoles(asList("ROLE_USER"));
        return userRepository.save(userToAdd);
	}

	@Override
	public String login(String username, String password) {
		Objects.requireNonNull(username);
        Objects.requireNonNull(password);
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getExpired().getTime() < System.currentTimeMillis()) {
        	throw new MyRuntimeException(new R("SVC0004","帐户过期["+user.getExpired()+"]"));
        }
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
	}

	@Override
	public String refresh(String oldToken) {
//		final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(oldToken);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(oldToken, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
	}

}
