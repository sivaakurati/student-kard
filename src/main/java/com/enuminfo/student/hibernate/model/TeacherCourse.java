/**
 * 
 */
package com.enuminfo.student.hibernate.model;

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
@Table (name = "tbl_teacher_course")
public class TeacherCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer teacherCourseId;
	private Course course;
	private Teacher teacher;
	
	public TeacherCourse() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "teacher_course_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@JsonIgnore
	public Integer getTeacherCourseId() {
		return teacherCourseId;
	}

	public void setTeacherCourseId(Integer teacherCourseId) {
		this.teacherCourseId = teacherCourseId;
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
	@JoinColumn (name = "teacher_id")
	@JsonIgnore
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
