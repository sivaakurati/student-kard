/**
 * 
 */
package com.enuminfo.school.hibernate.model;

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

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_time_tracker")
public class TimeTracker implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer timeTrackerId;
	private Course course;
	private Teacher teacher;
	private Subject subject;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private Boolean fullDay;
	
	public TimeTracker() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "time_tracker_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getTimeTrackerId() {
		return timeTrackerId;
	}

	public void setTimeTrackerId(Integer timeTrackerId) {
		this.timeTrackerId = timeTrackerId;
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
	@JoinColumn (name = "teacher_id")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Column (name = "start_date")
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column (name = "start_time")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column (name = "end_date")
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column (name = "end_time")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column (name = "full-day")
	public Boolean getFullDay() {
		return fullDay;
	}

	public void setFullDay(Boolean fullDay) {
		this.fullDay = fullDay;
	}
}
