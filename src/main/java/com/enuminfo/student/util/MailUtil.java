/**
 * 
 */
package com.enuminfo.student.util;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;
import com.enuminfo.student.hibernate.model.User;

/**
 * @author Kumar
 */
public class MailUtil {

	private Properties props = new Properties();
	
	public MailUtil() {
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
	}
	
	// https://www.google.com/settings/security/lesssecureapps
	// you will see turn off or turn on. Click "Turn on"
	
	public void sendTeacherDetail(Teacher teacher, User user) {
		Session session = Session.getDefaultInstance(props);
		//session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("studentkard@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("studentkard@gmail.com"));
			message.setSubject(teacher.getTeacherName() + ", You have successfully registered!!!");
			StringBuilder builder = new StringBuilder();
			builder.append("Hello " + teacher.getTeacherName() + ",\n\n");
			builder.append("Please check your login credentials as follows: \n\n");
			builder.append("Username: " + user.getUsername() + "\nPassword: " + user.getPassword() + "\n\n");
			builder.append("Thanks,\nSchool KARD Team!!!");
			message.setText(builder.toString());
			Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "studentkard@gmail.com", "student.kard");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Employee detail has sent and mail!!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendParentDetail(Parent parent, User user) {
		Session session = Session.getDefaultInstance(props);
		//session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("studentkard@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("studentkard@gmail.com"));
			message.setSubject(parent.getParentName() + ", You have successfully registered!!!");
			StringBuilder builder = new StringBuilder();
			builder.append("Hello " + parent.getParentName() + ",\n\n");
			builder.append("Please check your login credentials as follows: \n\n");
			builder.append("Username: " + user.getUsername() + "\nPassword: " + user.getPassword() + "\n\n");
			builder.append("Thanks,\nSchool KARD Team!!!");
			message.setText(builder.toString());
			Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "studentkard@gmail.com", "student.kard");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Parent detail has sent and mail!!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void sendStudentDetail(Student student, User user) {
		Session session = Session.getDefaultInstance(props);
		//session.setDebug(true);
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("studentkard@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("studentkard@gmail.com"));
			message.setSubject(student.getStudentName() + ", You have successfully registered!!!");
			StringBuilder builder = new StringBuilder();
			builder.append("Hello " + student.getStudentName() + ",\n\n");
			builder.append("Please check your login credentials as follows: \n\n");
			builder.append("Username: " + user.getUsername() + "\nPassword: " + user.getPassword() + "\n\n");
			builder.append("Thanks,\nSchool KARD Team!!!");
			message.setText(builder.toString());
			Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "studentkard@gmail.com", "student.kard");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
			System.out.println("Student detail has sent and mail!!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
