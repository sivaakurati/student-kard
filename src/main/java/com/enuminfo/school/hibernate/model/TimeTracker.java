/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
	private String startDateTime;
	private String endDateTime;
	private String repeatableForDayOfWeek;
	private Boolean allDay;
	
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

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "course_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "teacher_id")
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Column (name = "start_date_time")
	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	@Column (name = "end_date_time")
	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Column (name = "repeatable_for_day_of_week")
	public String getRepeatableForDayOfWeek() {
		return repeatableForDayOfWeek;
	}

	public void setRepeatableForDayOfWeek(String repeatableForDayOfWeek) {
		this.repeatableForDayOfWeek = repeatableForDayOfWeek;
	}

	@Column (name = "all_day")
	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
}
