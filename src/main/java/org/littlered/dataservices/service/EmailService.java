package org.littlered.dataservices.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	private String theWolf = "thewolf@bigbadcon.com";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sendEmail(HashMap<String, String> email) throws Exception {

		if(email == null){
			return;
		}

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(theWolf);
		message.setTo(email.get("to"));
		message.setSubject(email.get("subject"));
		message.setText(email.get("body"));

		logger.info("Sending to " + message.getTo().toString() + " regarding " + message.getSubject());
		javaMailSender.send(message);
		logger.info("Done sending email.");

	}

}
