/*************************************************************************
 * 
 * Algomedica CONFIDENTIAL
 * __________________
 * 
 *  [2017] Algomedica Pvt. Ltd. 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Algomedica Pvt. Ltd. The intellectual and technical 
 * concepts contained herein are proprietary to Algomedica Pvt. Ltd.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Algomedica Pvt. Ltd.
 * 
 */
package com.algomedica.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity(name="customer")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@JsonInclude(value=Include.NON_NULL)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties
	@Column(name="created_by")
	private Long createdBy;
	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date",insertable=false,updatable=false)
	private Date createdDate;

	@Column(name="org_email")
	private String orgEmail;

	@Column(name="org_fax")
	private String orgFax;

	@Column(name="org_name")
	private String orgName;


	@Column(name="org_person_name")
	private String orgPersonName;

	@Column(name="org_person_phone")
	private String orgPersonPhone;

	@Column(name="org_phone")
	private String orgPhone;

	@Column(name="org_status")
	private Integer orgStatus;
	@JsonIgnoreProperties
	@Column(name="updated_by")
	private Long updatedBy;
	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date",insertable=false,updatable=false)
	private Date updatedDate;

	//bi-directional many-to-one association to LicenseDetail
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="customer")
	@JsonManagedReference
	private List<LicenseDetail> licenseDetails;

	//bi-directional many-to-one association to Address
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getOrgEmail() {
		return this.orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getOrgFax() {
		return this.orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getOrgPersonName() {
		return this.orgPersonName;
	}

	public void setOrgPersonName(String orgPersonName) {
		this.orgPersonName = orgPersonName;
	}

	public String getOrgPersonPhone() {
		return this.orgPersonPhone;
	}

	public void setOrgPersonPhone(String orgPersonPhone) {
		this.orgPersonPhone = orgPersonPhone;
	}

	public String getOrgPhone() {
		return this.orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public Integer getOrgStatus() {
		return this.orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<LicenseDetail> getLicenseDetails() {
		return this.licenseDetails;
	}

	public void setLicenseDetails(List<LicenseDetail> licenseDetails) {
		this.licenseDetails = licenseDetails;
	}

	public LicenseDetail addLicenseDetail(LicenseDetail licenseDetail) {
		getLicenseDetails().add(licenseDetail);
		licenseDetail.setOrganization(this);

		return licenseDetail;
	}

	public LicenseDetail removeLicenseDetail(LicenseDetail licenseDetail) {
		getLicenseDetails().remove(licenseDetail);
		licenseDetail.setOrganization(null);
		return licenseDetail;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}