package org.littlered.dataservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;

/**
 * Created by Jeremy on 8/30/2017.
 */
@Service
//@Transactional
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private AwsSesService awsSesService;

	@Value("${email.useSes}")
	private String useSes;

	@Value("${email.sender}")
	private String sender;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendEmail(HashMap<String, String> email) throws Exception {

		boolean ses = new Boolean(useSes);
		if(ses) {
			awsSesService.sendEmail(email);
			return;
		}

		if(email == null){
			return;
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(email.get("to"));
		message.setSubject(email.get("subject"));
		message.setText(email.get("body"));
		message.setFrom("info@goplaynw.org");
		message.setReplyTo("info@goplaynw.org");

		logger.info("Sending to " + message.getTo().toString() + " regarding " + message.getSubject());
		try {
			javaMailSender.send(message);
		} catch (Exception e ){
			e.printStackTrace();
			throw e;
		}
		logger.info("Done sending email.");

	}

}
