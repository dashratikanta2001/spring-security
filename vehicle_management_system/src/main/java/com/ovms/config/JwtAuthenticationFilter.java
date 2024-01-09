package com.ovms.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ovms.response.CustomeResponse;
import com.ovms.response.ErrorResponse;
import com.ovms.service.impl.CustomUserDetailsService;
import com.ovms.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

			jwtToken = requestTokenHeader.substring(7);

			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (ExpiredJwtException e) {
				// TODO: handle exception
				ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Token Expired");
				sendJsonResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
				return;
			} catch (Exception e) {
				// TODO: handle exception
				ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");

				sendJsonResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
				return;
			}

		}
		else if(requestTokenHeader !=null && !requestTokenHeader.startsWith("Bearer ")){
			ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid Token");

			sendJsonResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
			return;
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			try {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			} catch (UsernameNotFoundException e) {
				// TODO: handle exception

				ErrorResponse<?> errorResponse = new ErrorResponse<>(HttpStatus.UNAUTHORIZED.value(), "User not found");

				sendJsonResponse(response, errorResponse, HttpStatus.UNAUTHORIZED);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private void sendJsonResponse(HttpServletResponse response, ErrorResponse<?> errorResponse, HttpStatus status)
			throws IOException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = objectMapperBuilder.build();

		response.setStatus(status.value());
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		response.getWriter().flush();

	}

}
