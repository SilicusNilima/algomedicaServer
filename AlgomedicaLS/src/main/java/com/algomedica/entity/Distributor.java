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
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the distributor database table.
 * 
 */
@Entity(name="distributor")
public class Distributor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="created_by")
	private BigInteger createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="dist_email")
	private String distEmail;

	@Column(name="dist_name")
	private String distName;

	@Column(name="dist_person_email")
	private String distPersonEmail;

	@Column(name="dist_person_name")
	private String distPersonName;

	@Column(name="dist_person_phone")
	private String distPersonPhone;

	@Column(name="dist_phone")
	private String distPhone;

	@Column(name="dist_status")
	private short distStatus;

	@Column(name="updated_by")
	private BigInteger updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;

	//bi-directional many-to-one association to Address
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDistEmail() {
		return this.distEmail;
	}

	public void setDistEmail(String distEmail) {
		this.distEmail = distEmail;
	}

	public String getDistName() {
		return this.distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getDistPersonEmail() {
		return this.distPersonEmail;
	}

	public void setDistPersonEmail(String distPersonEmail) {
		this.distPersonEmail = distPersonEmail;
	}

	public String getDistPersonName() {
		return this.distPersonName;
	}

	public void setDistPersonName(String distPersonName) {
		this.distPersonName = distPersonName;
	}

	public String getDistPersonPhone() {
		return this.distPersonPhone;
	}

	public void setDistPersonPhone(String distPersonPhone) {
		this.distPersonPhone = distPersonPhone;
	}

	public String getDistPhone() {
		return this.distPhone;
	}

	public void setDistPhone(String distPhone) {
		this.distPhone = distPhone;
	}

	public short getDistStatus() {
		return this.distStatus;
	}

	public void setDistStatus(short distStatus) {
		this.distStatus = distStatus;
	}

	public BigInteger getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(BigInteger updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}