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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kumar
 */
@Entity
@Table (name = "tbl_country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer countryId;
	private String countryName;
	private String countryIsd;
	private List<Location> locations;
	
	public Country() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column (name = "country_id")
	@GeneratedValue (strategy = GenerationType.AUTO)
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column (name = "country_name")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column (name = "country_isd")
	public String getCountryIsd() {
		return countryIsd;
	}

	public void setCountryIsd(String countryIsd) {
		this.countryIsd = countryIsd;
	}

	@OneToMany (mappedBy = "country", fetch = FetchType.LAZY)
	@JsonIgnore
	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
}
