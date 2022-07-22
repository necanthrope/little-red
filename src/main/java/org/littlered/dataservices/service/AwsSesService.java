package org.littlered.dataservices.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.logging.Logger;

@Service
public class AwsSesService {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private static final String CHAR_SET = "UTF-8";
	private final AmazonSimpleEmailService emailService;
	private final String sender;

	@Autowired
	public AwsSesService(AmazonSimpleEmailService emailService,
						 @Value("${email.sender}") String sender) {
		this.emailService = emailService;
		this.sender = sender;
	}

	public void sendEmail(HashMap<String, String> email) {
		try {
			// The time for request/response round trip to aws in milliseconds
			int requestTimeout = 3000;
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(
							new Destination().withToAddresses(email.get("to")))
					.withMessage(new Message()
							.withBody(new Body()
									.withText(new Content()
											.withCharset(CHAR_SET).withData(email.get("body"))))
							.withSubject(new Content()
									.withCharset(CHAR_SET).withData(email.get("subject"))))
					.withSource(sender).withSdkRequestTimeout(requestTimeout);
			emailService.sendEmail(request);
		} catch (RuntimeException e) {
			logger.severe("Error occurred sending email to " + email);
			e.printStackTrace();
		}
	}
}
