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
	private Date startDate;
	private Date endDate;
	private Boolean fullDay;	
	private String tempStart;
	private String tempEnd;
	
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
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column (name = "end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column (name = "full_day")
	public Boolean getFullDay() {
		return fullDay;
	}

	public void setFullDay(Boolean fullDay) {
		this.fullDay = fullDay;
	}

	@Transient
	public String getTempStart() {
		return tempStart;
	}

	public void setTempStart(String tempStart) {
		this.tempStart = tempStart;
	}

	@Transient
	public String getTempEnd() {
		return tempEnd;
	}

	public void setTempEnd(String tempEnd) {
		this.tempEnd = tempEnd;
	}
}
