/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_time_tracker_event")
public class TimeTrackerEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer eventId;
	private String title;
	private String start;
	private String end;
	private Boolean allDay;
	
	public TimeTrackerEvent() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "time_tracker_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column (name = "event_title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column (name = "event_start")
	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	@Column (name = "event_end")
	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Column (name = "event_all_day")
	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}
}
