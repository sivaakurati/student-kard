/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_course")
public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private String courseName;
	private List<Subject> subjects;
	private List<Batch> batchs;
	private List<Student> students;
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "course_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column (name = "course_name")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable (name = "tbl_course_subject", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "subject_id")})
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable (name = "tbl_batch_course", joinColumns = {@JoinColumn(name = "course_id")}, inverseJoinColumns = {@JoinColumn(name = "batch_id")})
	@JsonIgnore
	public List<Batch> getBatchs() {
		return batchs;
	}

	public void setBatchs(List<Batch> batchs) {
		this.batchs = batchs;
	}

	@OneToMany (mappedBy = "course", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
