/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kumar
 */
//@Entity
//@Table (name = "tbl_assignment_tracker")
public class AssignmentTracker {

	private Integer gradeTrackerId;
	private Batch batch;
	private Course course;
	private Student student;
}
