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
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnoreProperties
	@Column(name="created_by")
	private BigInteger createdBy;
	
	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@JsonIgnoreProperties
	@Column(name="password")
	private String password;
	
	@JsonIgnoreProperties
	@Column(name="upadted_by")
	private BigInteger upadtedBy;

	@JsonIgnoreProperties
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="username")
	private String username;

	@Column(name="usr_email")
	private String usrEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="usr_lastlogin")
	private Date usrLastlogin;

	@Column(name="usr_mobile1")
	private String usrMobile1;

	@Column(name="usr_mobile2")
	private String usrMobile2;

	@Column(name="usr_status")
	private byte usrStatus;

	//bi-directional many-to-one association to Address
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	//bi-directional many-to-one association to Distributor
	@OneToOne(cascade = CascadeType.ALL)
	private Distributor distributor;

	//bi-directional many-to-one association to Role
	@OneToOne(cascade = CascadeType.ALL)
	private Role role;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigInteger getUpadtedBy() {
		return this.upadtedBy;
	}

	public void setUpadtedBy(BigInteger upadtedBy) {
		this.upadtedBy = upadtedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public Date getUsrLastlogin() {
		return this.usrLastlogin;
	}

	public void setUsrLastlogin(Date usrLastlogin) {
		this.usrLastlogin = usrLastlogin;
	}

	public String getUsrMobile1() {
		return this.usrMobile1;
	}

	public void setUsrMobile1(String usrMobile1) {
		this.usrMobile1 = usrMobile1;
	}

	public String getUsrMobile2() {
		return this.usrMobile2;
	}

	public void setUsrMobile2(String usrMobile2) {
		this.usrMobile2 = usrMobile2;
	}

	public byte getUsrStatus() {
		return this.usrStatus;
	}

	public void setUsrStatus(byte usrStatus) {
		this.usrStatus = usrStatus;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Distributor getDistributor() {
		return this.distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}