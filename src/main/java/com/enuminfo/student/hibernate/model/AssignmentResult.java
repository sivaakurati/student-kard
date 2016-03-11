/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_assignment_result")
public class AssignmentResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer resultId;
	private Assignment assignment;
	private Student student;
	private String result;
	private Date submittedDate;
	
	private String dos;
	
	public AssignmentResult() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "assignment_result_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "assignment_id")
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
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

	@Column (name = "result")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column (name = "submitted_date")
	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	@Transient
	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}
}
