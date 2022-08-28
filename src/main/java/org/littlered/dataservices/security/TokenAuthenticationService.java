package org.littlered.dataservices.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * Created by Jeremy on 3/26/2017.
 */
public class TokenAuthenticationService {

	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, String username, JWTUtil jwtUtil) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + jwtUtil.getJwtExpirationInMs()))
				.signWith(SignatureAlgorithm.HS512, jwtUtil.getSecret())
				.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static Authentication getAuthentication(HttpServletRequest request, JWTUtil jwtUtil) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
					.setSigningKey(jwtUtil.getSecret())
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			return user != null ?
					new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
					null;
		}
		return null;
	}

}
