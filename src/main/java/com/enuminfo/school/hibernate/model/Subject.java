/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;
import java.util.List;

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
@Table (name = "tbl_subject")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer subjectId;
	private String subjectName;
	private List<Course> courses;
	private List<Teacher> teachers;
	private List<TimeTracker> timeTrackers;
	
	public Subject() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "subject_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Column (name = "subject_name")
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_course_subject", joinColumns = {@JoinColumn(name = "subject_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
	@JsonIgnore
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@OneToMany (mappedBy = "subject", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	@OneToMany (mappedBy = "subject", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<TimeTracker> getTimeTrackers() {
		return timeTrackers;
	}

	public void setTimeTrackers(List<TimeTracker> timeTrackers) {
		this.timeTrackers = timeTrackers;
	}
}
