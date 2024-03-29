package org.littlered.dataservices.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by Jeremy on 3/26/2017.
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private JWTUtil jwtUtil;

	public JWTLoginFilter(String url, AuthenticationManager authManager, JWTUtil jwtUtil) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.jwtUtil = jwtUtil;
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest req, HttpServletResponse httpServletResponse)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials creds = new ObjectMapper()
				.readValue(req.getInputStream(), AccountCredentials.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						creds.getUsername(),
						creds.getPassword(),
						Collections.emptyList()
				)
		);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService
				.addAuthentication(res, auth.getName(), jwtUtil);
	}
}
