package com.aftab.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aftab.sms.utils.EmailUtils;

@Service
public class MailSenderService {
@Autowired
	EmailUtils emailUtils;


public String emailSender(String subject,String body,String to) {
	boolean sendEmail = emailUtils.sendEmail(subject, body, to);
	if(sendEmail) {
		return "Message sent. If your message makes sense i will contact back soon.";
	}
	return "There was an issue sending the message";
}
}
