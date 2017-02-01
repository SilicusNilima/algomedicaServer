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
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * The persistent class for the license_detail database table.
 * 
 */
@Entity(name = "license_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LicenseDetail implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties
	@Column(name = "created_by")
	private Long createdBy;

	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", insertable = false,updatable=false)
	private Date createdDate;

	@Column(name = "ls_category")
	private String lsCategory;

	@Column(name = "ls_cost")
	private BigDecimal lsCost;

	@Column(name = "ls_expiry_days")
	private BigDecimal lsExpiryDays;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ls_exipry_date")
	private Date lsExipryDate;

	@Column(name = "ls_license_key")
	private String lsLicenseKey;

	@Column(name = "ls_license_type")
	private String lsLicenseType;

	@Column(name = "ls_mac_address")
	private String lsMacAddress;

	@Column(name = "ls_model_name")
	private String lsModelName;

	@Column(name = "ls_model_number")
	private String lsModelNumber;

	@Column(name = "ls_ops_email")
	private String lsOpsEmail;

	@Column(name = "ls_ops_name")
	private String lsOpsName;

	@Column(name = "ls_ops_phone")
	private String lsOpsPhone;

	
	@Column(name = "reason")
	private String lsreason;
	
	@Column(name = "ls_status")
	private byte lsStatus;
	
	
	@JsonIgnoreProperties
	@Column(name = "updated_by")
	private Long updatedBy;
	
	
	@JsonIgnoreProperties
	@Column(name = "ls_key_data")
	private Blob blobkey;
	
	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", insertable = false,updatable=false)
	private Date updatedDate;

	// bi-directional many-to-one association to Customer
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;

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

	public String getLsCategory() {
		return this.lsCategory;
	}

	public void setLsCategory(String lsCategory) {
		this.lsCategory = lsCategory;
	}

	public BigDecimal getLsCost() {
		return this.lsCost;
	}

	public void setLsCost(BigDecimal lsCost) {
		this.lsCost = lsCost;
	}

	public Date getLsExipryDate() {
		return this.lsExipryDate;
	}

	public void setLsExipryDate(Date lsExipryDate) {
		this.lsExipryDate = lsExipryDate;
	}

	public String getLsLicenseKey() {
		return this.lsLicenseKey;
	}

	public void setLsLicenseKey(String lsLicenseKey) {
		this.lsLicenseKey = lsLicenseKey;
	}

	public String getLsLicenseType() {
		return this.lsLicenseType;
	}

	public void setLsLicenseType(String lsLicenseType) {
		this.lsLicenseType = lsLicenseType;
	}

	public String getLsMacAddress() {
		return this.lsMacAddress;
	}

	public void setLsMacAddress(String lsMacAddress) {
		this.lsMacAddress = lsMacAddress;
	}

	public String getLsModelName() {
		return this.lsModelName;
	}

	public void setLsModelName(String lsModelName) {
		this.lsModelName = lsModelName;
	}

	public String getLsModelNumber() {
		return this.lsModelNumber;
	}

	public void setLsModelNumber(String lsModelNumber) {
		this.lsModelNumber = lsModelNumber;
	}

	public String getLsOpsEmail() {
		return this.lsOpsEmail;
	}

	public void setLsOpsEmail(String lsOpsEmail) {
		this.lsOpsEmail = lsOpsEmail;
	}

	public String getLsOpsName() {
		return this.lsOpsName;
	}

	public void setLsOpsName(String lsOpsName) {
		this.lsOpsName = lsOpsName;
	}

	public String getLsOpsPhone() {
		return this.lsOpsPhone;
	}

	public void setLsOpsPhone(String lsOpsPhone) {
		this.lsOpsPhone = lsOpsPhone;
	}

	public byte getLsStatus() {
		return this.lsStatus;
	}

	public void setLsStatus(byte lsStatus) {
		this.lsStatus = lsStatus;
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

	public Customer getOrganization() {
		return this.customer;
	}

	public void setOrganization(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the lsExpiryDays
	 */
	public BigDecimal getLsExpiryDays() {
		return lsExpiryDays;
	}

	/**
	 * @param lsExpiryDays
	 *            the lsExpiryDays to set
	 */
	public void setLsExpiryDays(BigDecimal lsExpiryDays) {
		this.lsExpiryDays = lsExpiryDays;
	}

	/**
	 * @return the blobkey
	 */
	 @JsonIgnore
	public Blob getBlobkey() {
		return blobkey;
	}

	/**
	 * @param blobkey the blobkey to set
	 */
	public void setBlobkey(Blob blobkey) {
		this.blobkey = blobkey;
	}

	/**(non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return (LicenseDetail) super.clone();
	}

	/**
	 * @return the lsreason
	 */
	public String getLsreason() {
		return lsreason;
	}

	/**
	 * @param lsreason the lsreason to set
	 */
	public void setLsreason(String lsreason) {
		this.lsreason = lsreason;
	}
	
	

}