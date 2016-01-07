/**
 * 
 */
package com.enuminfo.school.model;

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
@Table (name = "tbl_course_subject")
public class CourseSubject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer courseSubjectId;
	private Course course;
	private Subject subject;
	
	public CourseSubject() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "course_subject_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@JsonIgnore
	public Integer getCourseSubjectId() {
		return courseSubjectId;
	}

	public void setCourseSubjectId(Integer courseSubjectId) {
		this.courseSubjectId = courseSubjectId;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "course_id")
	@JsonIgnore
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
