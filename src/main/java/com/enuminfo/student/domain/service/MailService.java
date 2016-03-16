/**
 * 
 */
package com.enuminfo.student.domain.service;

import com.enuminfo.student.hibernate.model.Parent;
import com.enuminfo.student.hibernate.model.Student;
import com.enuminfo.student.hibernate.model.Teacher;

/**
 * @author Kumar
 */
public interface MailService {

	void sendTeacherDetail(Teacher teacher);
	void sendParentDetail(Parent parent);
	void sendStudentDetail(Student student);
}
