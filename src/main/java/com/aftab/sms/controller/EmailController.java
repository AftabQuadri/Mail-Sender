package com.aftab.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aftab.sms.binding.Visitor;
import com.aftab.sms.service.MailSenderService;


@RestController
public class EmailController {
	@Autowired
	MailSenderService service;
	@PostMapping(value="/sendEmail",consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public ResponseEntity<String> sendEmail(
			@ModelAttribute Visitor visitor
			){
		String msg="";
		if(visitor!=null && visitor.getVisitorName()!=null && !visitor.getVisitorName().isEmpty() && visitor.getVisitorEmail()!=null && !visitor.getVisitorEmail().isEmpty() && visitor.getVisitorMessage()!=null && !visitor.getVisitorMessage().isEmpty()) {
			String subject="Message from Portfolio ";
			String body=visitor.getVisitorMessage()+"<br><br><br>" +"Sender email id: "+visitor.getVisitorEmail() +"<br>"+"Sender Name: "+visitor.getVisitorName();
			msg = service.emailSender(subject, body, "skaftab984@gmail.com");
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	 @PostMapping("/getVD")
	    public String getVisitorDetails(
	            @RequestParam("visitorName") String visitorName,
	            @RequestParam("visitorEmail") String visitorEmail,
	            @RequestParam("visitorMessage") String visitorMessage) {
	        String str = visitorName + " " + visitorEmail + " " + visitorMessage;
	        System.out.println(str); // Optional: Print to console for debugging
	        return str;
	    }
}
