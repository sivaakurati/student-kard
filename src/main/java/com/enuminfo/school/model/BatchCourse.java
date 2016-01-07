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
@Table (name = "tbl_batch_course")
public class BatchCourse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer batchCourseId;
	private Batch batch;
	private Course course;
	
	public BatchCourse() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "batch_course_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	@JsonIgnore
	public Integer getBatchCourseId() {
		return batchCourseId;
	}

	public void setBatchCourseId(Integer batchCourseId) {
		this.batchCourseId = batchCourseId;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "batch_id")
	@JsonIgnore
	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
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
}
