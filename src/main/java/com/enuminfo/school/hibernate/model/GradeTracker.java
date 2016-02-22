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
//@Table (name = "tbl_grade_tracker")
public class GradeTracker {

	private Integer gradeTrackerId;
	private Batch batch;
	private Course course;
	private Student student;
}
