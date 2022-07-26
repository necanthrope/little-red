package org.littlered.dataservices.rest.controller.payment;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.littlered.dataservices.entity.wordpress.Users;
import org.littlered.dataservices.rest.params.payment.EventbriteToken;
import org.littlered.dataservices.service.UsersService;
import org.littlered.dataservices.service.payment.EventbritePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

@RestController
@RequestMapping("/payment/eventbrite")
@Api(description = "Operations pertaining to the Eventbrite payment processor.")
public class EventbritePaymentController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private EventbritePaymentService eventbritePaymentService;

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@ApiOperation(value = "Receives and stores a user token for Eventbrite.", notes = "The user must be logged into the UI. \n" +
			"This method will redirect back to the UI after completion.")
	@RequestMapping(value = "/token", method = RequestMethod.POST, produces = "application/json")
	public void receiveEventbriteUserToken(@RequestBody EventbriteToken eventbriteToken, HttpServletResponse response) throws Exception {
		Users user = usersService.getCurrentUser();
		eventbritePaymentService.receiveEventbriteUserToken(user, eventbriteToken.getCode());
		logger.info("Received eventbrite code " + eventbriteToken.getCode() + " for user " + user.getUserEmail());
	}

}
