/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;
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
@Table (name = "tbl_parent")
public class Parent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer parentId;
	private String parentName;
	private String photo;
	private String emailAddress;
	private String address;
	private String gender;
	private Location location;
	private Long contactNo;
	private Parent mainParentId;
	private List<Student> students;
	
	private Parent[] dependents;
	private Student[] childs;
	
	public Parent() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "parent_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column (name = "parent_name")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Column (name = "photo")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Column (name = "email_address")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Transient
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column (name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name = "location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Column (name = "contact_no")
	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	@Column (name = "main_parent_id")
	public Parent getMainParentId() {
		return mainParentId;
	}

	public void setMainParentId(Parent mainParentId) {
		this.mainParentId = mainParentId;
	}

	@OneToMany (mappedBy = "parent", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Transient
	public Parent[] getDependents() {
		return dependents;
	}

	public void setDependents(Parent[] dependents) {
		this.dependents = dependents;
	}

	@Transient
	public Student[] getChilds() {
		return childs;
	}

	public void setChilds(Student[] childs) {
		this.childs = childs;
	}
}
