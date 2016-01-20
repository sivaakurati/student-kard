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
	private String address;
	private Location location;
	private Long contactNo;
	private String qualification;
	private Parent mainParentId;
	private String relation;
	private String occupation;
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

	@Column (name = "qualification")
	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@OneToOne
	@JoinColumn (name = "main_parent_id")
	public Parent getMainParentId() {
		return mainParentId;
	}

	public void setMainParentId(Parent mainParentId) {
		this.mainParentId = mainParentId;
	}

	@Column (name = "relation")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Column (name = "occupation")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	@OneToMany (mappedBy = "parent", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
