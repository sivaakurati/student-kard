/**
 * 
 */
package com.enuminfo.school.hibernate.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private String address;
	private Location location;
	private Long contactNo;
	private Parent mainParentId;
	private List<Parent> dependents;
	private List<Student> students;
	
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

	@Column (name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

	@OneToOne
	@JoinColumn (name = "main_parent_id")
	public Parent getMainParentId() {
		return mainParentId;
	}

	public void setMainParentId(Parent mainParentId) {
		this.mainParentId = mainParentId;
	}

	@OneToMany (mappedBy = "parent", fetch = FetchType.LAZY)
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
}
