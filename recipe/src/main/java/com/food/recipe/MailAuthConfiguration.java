package com.food.recipe;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailAuthConfiguration {
	
	@Value("${google.id}")
	String googleId;
	
	@Value("${google.pw}")
	String googlePw;
	
	@Bean(name="javaMailSender")
	public JavaMailSender javaMailSender() {
		Properties mailProperties = new Properties(); 
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.transport.protocol", "smtp");
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.starttls.required", true);
		mailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		mailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		mailProperties.put("mail.debug", true);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(googleId);
		mailSender.setPassword(googlePw);
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setJavaMailProperties(mailProperties);
		
		return mailSender;
		
	}
	
}