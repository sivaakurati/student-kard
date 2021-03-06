/**
 * 
 */
package com.enuminfo.student.hibernate.model;

import java.io.Serializable;
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
	private byte[] photo;
	private String emailAddress;
	private String address;
	private String gender;
	private Location location;
	private Long contactNo;
	private Integer mainParentId;
	private String imgName;
	private List<Student> students;
	private List<Parent> dependents;
	
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
	public Integer getMainParentId() {
		return mainParentId;
	}

	public void setMainParentId(Integer mainParentId) {
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
	public List<Parent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Parent> dependents) {
		this.dependents = dependents;
	}

	@Column (name = "img_name")
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
