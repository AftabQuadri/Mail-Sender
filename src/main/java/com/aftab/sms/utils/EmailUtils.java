package com.aftab.sms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendEmail(String subject, String body, String to) {

		try {
			InternetAddress emailAddr = new InternetAddress(to);
			emailAddr.validate();
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		return true;
	}
}
