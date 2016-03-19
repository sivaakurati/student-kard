/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_student")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private String studentName;
	private byte[] photo;
	private String emailAddress;
	private String gender;
	private String address;
	private Long contactNo;
	private Location location;
	private Date dateOfBirth;
	private Date dateOfJoining;
	private Batch batch;
	private Course course;
	private Parent parent;
	
	private String dob;
	private String doj;
	
	private List<Grade> grades;
	private List<AssignmentResult> assignmentResults;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "student_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column (name = "student_name")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Lob
	@Column (name = "photo", columnDefinition = "mediumblog")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@Column (name = "email_address")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column (name = "gender")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	@JoinColumn (name = "location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "parent_id")
	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	@Column (name = "date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column (name = "date_of_joining")
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	@Transient
	public String getDob() {
		return dob;
	}

	@Transient
	public void setDob(String dob) {
		this.dob = dob;
	}

	@Transient
	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	@OneToMany (mappedBy = "student", fetch = FetchType.EAGER)
	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@OneToMany (mappedBy = "student", fetch = FetchType.EAGER)
	public List<AssignmentResult> getAssignmentResults() {
		return assignmentResults;
	}

	public void setAssignmentResults(List<AssignmentResult> assignmentResults) {
		this.assignmentResults = assignmentResults;
	}
	
	@Column (name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column (name = "contact_no")
	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
}
