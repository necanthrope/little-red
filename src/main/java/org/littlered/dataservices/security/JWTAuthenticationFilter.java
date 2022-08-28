package org.littlered.dataservices.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

/**
 * Created by Jeremy on 3/26/2017.
 */
@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public void doFilter(ServletRequest request,
						 ServletResponse response,
						 FilterChain filterChain)
			throws IOException, ServletException {

		try {
			final String authorization = ((HttpServletRequest)request).getHeader("Authorization");
			if (hasText(authorization)){
				Authentication authentication = TokenAuthenticationService
						.getAuthentication((HttpServletRequest) request, jwtUtil);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException eje) {
			logger.info("Security exception for user {} - {}",
					eje.getClaims().getSubject(), eje.getMessage());

			logger.trace("Security exception trace: {}", eje);
			((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, eje.getMessage());
		}
	}

}
