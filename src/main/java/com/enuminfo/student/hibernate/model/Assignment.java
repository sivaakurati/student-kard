/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_assignment")
public class Assignment {

	private Integer assignmentId;
	private Batch batch;
	private Course course;
	private Subject subject;
	private String assignment;
	private Date lastSubmissionDate;
	private List<AssignmentResult> assignmentResults;
	
	private String lsd;
	
	public Assignment() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "assignment_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
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

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Column (name = "assignment_question")
	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	@Column (name = "last_submission_date")
	public Date getLastSubmissionDate() {
		return lastSubmissionDate;
	}

	public void setLastSubmissionDate(Date lastSubmissionDate) {
		this.lastSubmissionDate = lastSubmissionDate;
	}

	@OneToMany (mappedBy = "assignment", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<AssignmentResult> getAssignmentResults() {
		return assignmentResults;
	}

	public void setAssignmentResults(List<AssignmentResult> assignmentResults) {
		this.assignmentResults = assignmentResults;
	}

	@Transient
	public String getLsd() {
		return lsd;
	}

	public void setLsd(String lsd) {
		this.lsd = lsd;
	}
}
