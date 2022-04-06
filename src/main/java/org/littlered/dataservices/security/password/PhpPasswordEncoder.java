package org.littlered.dataservices.security.password;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Jeremy on 3/31/2017.
 */
public class PhpPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence charSequence) {
		return new PhpPasswordHasher().createHash(charSequence.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return new PhpPasswordHasher().isMatch(rawPassword.toString(), encodedPassword);
	}
}
