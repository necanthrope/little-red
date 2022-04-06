package org.littlered.dataservices.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Collections.emptyList;
import static org.springframework.util.StringUtils.hasText;

/***
 * If you activate this filter, turn off the identical functionality in JWTAuthenticationFilter.class.
 * It's there to allow any urls to authenticate with the key, but if you want to lock it down in SecurityConfig,
 * keep this class around.
 */
@Component
public class ApiKeyFilter extends GenericFilterBean {

	@Value("${api.key}")
	private String apiKey;

	@Value("${api.key.user}")
	private String apiKeyUser;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorization = ((HttpServletRequest)request).getHeader("x-api-key");
		final String prefix = "ApiKey ";
		if (hasText(authorization) && authorization.startsWith(prefix)) {
			String key = authorization.substring(prefix.length());
			if (apiKey.equals(key)) {
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						apiKeyUser, null, emptyList());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);

	}
}