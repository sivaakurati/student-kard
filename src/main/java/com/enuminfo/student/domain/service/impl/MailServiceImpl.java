/**
 * 
 */
package com.enuminfo.student.domain.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.enuminfo.student.domain.service.MailService;
import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;

/**
 * @author Kumar
 */
@Service
public class MailServiceImpl implements MailService {

	@Autowired JavaMailSender mailSender;
	
	@Override
	public void sendTeacherDetail(Teacher teacher) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo("studentkard@gmail.com");
			mimeMessageHelper.setFrom("studentkard@gmail.com");
			mimeMessageHelper.setSubject("Lorem ipsum");
			mimeMessageHelper.setText("Lorem ipsum dolor sit amet [...]");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        mailSender.send(mimeMessage);
	}

	@Override
	public void sendParentDetail(Parent parent) {
		
	}

	@Override
	public void sendStudentDetail(Student student) {
		
	}
}
