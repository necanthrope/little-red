package org.littlered.dataservices.rest.params;

import java.util.logging.Logger;

public class AuthenticationResponse {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private String refreshToken;

	public AuthenticationResponse(String token) {
		this.setRefreshToken(token);
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
