/**
 * 
 */
package com.enuminfo.school.model;

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
import javax.persistence.Table;

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

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "tbl_batch_course", joinColumns = {@JoinColumn(name = "batch_id")}, inverseJoinColumns = {@JoinColumn(name = "course_id")})
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
