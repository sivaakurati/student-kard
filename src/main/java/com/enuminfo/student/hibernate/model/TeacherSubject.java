/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_teacher_subject")
public class TeacherSubject {

	private Integer teacherSubjectId;
	private Teacher teacher;
	private Subject subject;
	
	public TeacherSubject() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "teacher_subject_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@JsonIgnore
	public Integer getTeacherSubjectId() {
		return teacherSubjectId;
	}

	public void setTeacherSubjectId(Integer teacherSubjectId) {
		this.teacherSubjectId = teacherSubjectId;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "teacher_id")
	@JsonIgnore
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "subject_id")
	@JsonIgnore
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
