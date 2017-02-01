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
package com.algomedica.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model class to capture customer related data from frontend
 * @author Aasif
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerForm {

	private Long id;
	private String orgEmail;
	private String orgFax;
	private String orgName;
	private String orgPersonEmail;
	private String orgPersonName;
	private String orgPersonPhone;
	private String orgPhone;
	private String addressLine1;
	private String addressLine2;
	private String cityName;
	private Integer orgStatus;
	private String postalCode;
	private String stateName;
	private String countryName;
	@JsonIgnoreProperties
	private Long userId;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(Integer orgStatus) {
		this.orgStatus = orgStatus;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getOrgFax() {
		return orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgPersonEmail() {
		return orgPersonEmail;
	}

	public void setOrgPersonEmail(String orgPersonEmail) {
		this.orgPersonEmail = orgPersonEmail;
	}

	public String getOrgPersonName() {
		return orgPersonName;
	}

	public void setOrgPersonName(String orgPersonName) {
		this.orgPersonName = orgPersonName;
	}

	public String getOrgPersonPhone() {
		return orgPersonPhone;
	}

	public void setOrgPersonPhone(String orgPersonPhone) {
		this.orgPersonPhone = orgPersonPhone;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}