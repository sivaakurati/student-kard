/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;

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
@Table (name = "tbl_grade")
public class Grade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer gradeId;
	private Student student;
	private Subject subject;
	private Long marks;
	
	public Grade() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "grade_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@JoinColumn (name = "marks")
	public Long getMarks() {
		return marks;
	}

	public void setMarks(Long marks) {
		this.marks = marks;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "student_id")
	@JsonIgnore
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
