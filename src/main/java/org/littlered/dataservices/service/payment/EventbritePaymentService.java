package org.littlered.dataservices.service.payment;

import org.littlered.dataservices.Constants;
import org.littlered.dataservices.entity.wordpress.Usermeta;
import org.littlered.dataservices.entity.wordpress.Users;
import org.littlered.dataservices.repository.wordpress.interfaces.UsermetaJPAInterface;
import org.littlered.dataservices.service.UsersJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EventbritePaymentService {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private UsersJPAService usersJPAService;

	@Autowired
	private UsermetaJPAInterface usermetaJPAInterface;

	public void receiveEventbriteUserToken(Users user, String code ) throws Exception {

		Usermeta tokenMeta = usersJPAService.findUsermetaByUserIdAndMetaKey(user, Constants.EVENTBRITE_TOKEN_USERMETA_KEY);
		if (tokenMeta != null) {
			tokenMeta.setMetaValue(code);
			usermetaJPAInterface.save(tokenMeta);
			return;
		}
		usersJPAService.createUserMeta(user, Constants.EVENTBRITE_TOKEN_USERMETA_KEY, code);
	}
}
