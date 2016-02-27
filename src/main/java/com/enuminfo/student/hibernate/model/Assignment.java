/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kumar
 */
//@Entity
//@Table (name = "tbl_assignment")
public class Assignment {

	private Integer assignmentId;
	private Batch batch;
	private Course course;
	private Subject subject;
	private String assignment;
}
