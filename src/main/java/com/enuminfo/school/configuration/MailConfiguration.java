/**
 * 
 */
package com.enuminfo.school.configuration;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Kumar
 */
@Configuration
public class MailConfiguration {
	
	private static String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static String MAIL_SMTPS_AUTH = "mail.smtps.auth";
	private static String MAIL_SMTP_SSL_ENABLE = "mail.smtp.ssl.enable";
	private static String MAIL_DEBUG = "mail.debug";
	
	private static String MAIL_HOST = "mail.host";
	private static String MAIL_PORT = "mail.port";
	private static String MAIL_PROTOCOL = "mail.protocol";
	
	@Resource Environment environment;
	
	public @Bean Properties mailProperties(){
		Properties mailProperties = new Properties();
		mailProperties.put(MAIL_TRANSPORT_PROTOCOL, environment.getRequiredProperty(MAIL_TRANSPORT_PROTOCOL));
		mailProperties.put(MAIL_SMTPS_AUTH, environment.getRequiredProperty(MAIL_SMTPS_AUTH, Boolean.class));
		mailProperties.put(MAIL_SMTP_SSL_ENABLE, environment.getRequiredProperty(MAIL_SMTP_SSL_ENABLE, Boolean.class));
		mailProperties.put(MAIL_DEBUG, environment.getRequiredProperty(MAIL_DEBUG, Boolean.class));		
		return mailProperties;
	}
	
	public @Bean JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();		
		mailSender.setHost(environment.getRequiredProperty(MAIL_HOST));
		mailSender.setPort(environment.getRequiredProperty(MAIL_PORT, Integer.class));
		mailSender.setProtocol(environment.getRequiredProperty(MAIL_PROTOCOL));		
		mailSender.setJavaMailProperties(mailProperties());		
		return mailSender;
	}
}
