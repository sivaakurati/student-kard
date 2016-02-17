/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.util.Date;

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
@Table (name = "tbl_student")
public class Student {

	private Integer studentId;
	private String studentName;
	private String photo;
	private Date dateOfBirth;
	private Date dateOfJoining;
	private Batch batch;
	private Course course;
	private Parent parent;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "student_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column (name = "student_name")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column (name = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "batch_id")
	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "course_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "parent_id")
	@JsonIgnore
	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Column (name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column (name = "date_of_joining")
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
}
