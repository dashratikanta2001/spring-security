package com.ovms.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import com.ovms.dao.TokenDao;
import com.ovms.util.JwtUtil;

@Service
public class LogoutService implements LogoutHandler{
	
	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void logout(
			HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication
			
	) {
		
		String requestTokenHeader = request.getHeader("Authorization");
		String jwtToken = null;

		if (requestTokenHeader == null && !requestTokenHeader.startsWith("Bearer ")) {
			return;
		}
		jwtToken = requestTokenHeader.substring(7);
		
		
		
		
		try {
			tokenDao.deleteToken(jwtUtil.getUsernameFromToken(jwtToken));			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
