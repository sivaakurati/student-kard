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
@Table (name = "tbl_batch")
public class Batch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer batchId;
	private String batchName;
	private List<Course> courses;
	private List<Student> students;
	
	public Batch() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "batch_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	@Column (name = "batch_name")
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tbl_batch_course", joinColumns = {@JoinColumn(name = "batch_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@OneToMany (mappedBy = "batch", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
