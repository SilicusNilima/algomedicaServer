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
 * Model class to capture License related data from frontend
 * @author KLele
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseForm {

	private long id;
	private String lsmacAddress;
	private String lsmodelNumber;
	private String lsmodelName;
	private String lsType;
	private String lsCost;
	private String lsCategory;
	private String lsregenerationReason;
	private String lsOpsEmail;
	private String lsOpsName;
	private String lsOpsPhone;
	
	
	private long lsExipryDays;

	private long customerId;

	private String lsStatus;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the lsmacAddress
	 */
	public String getLsmacAddress() {
		return lsmacAddress;
	}

	/**
	 * @param lsmacAddress the lsmacAddress to set
	 */
	public void setLsmacAddress(String lsmacAddress) {
		this.lsmacAddress = lsmacAddress;
	}

	/**
	 * @return the lsmodelNumber
	 */
	public String getLsmodelNumber() {
		return lsmodelNumber;
	}

	/**
	 * @param lsmodelNumber the lsmodelNumber to set
	 */
	public void setLsmodelNumber(String lsmodelNumber) {
		this.lsmodelNumber = lsmodelNumber;
	}

	/**
	 * @return the lsmodelName
	 */
	public String getLsmodelName() {
		return lsmodelName;
	}

	/**
	 * @param lsmodelName the lsmodelName to set
	 */
	public void setLsmodelName(String lsmodelName) {
		this.lsmodelName = lsmodelName;
	}

	/**
	 * @return the lsType
	 */
	public String getLsType() {
		return lsType;
	}

	/**
	 * @param lsType the lsType to set
	 */
	public void setLsType(String lsType) {
		this.lsType = lsType;
	}

	/**
	 * @return the lsCost
	 */
	public String getLsCost() {
		return lsCost;
	}

	/**
	 * @param lsCost the lsCost to set
	 */
	public void setLsCost(String lsCost) {
		this.lsCost = lsCost;
	}

	/**
	 * @return the lsCategory
	 */
	public String getLsCategory() {
		return lsCategory;
	}

	/**
	 * @param lsCategory the lsCategory to set
	 */
	public void setLsCategory(String lsCategory) {
		this.lsCategory = lsCategory;
	}

	/**
	 * @return the lsregenerationReason
	 */
	public String getLsregenerationReason() {
		return lsregenerationReason;
	}

	/**
	 * @param lsregenerationReason the lsregenerationReason to set
	 */
	public void setLsregenerationReason(String lsregenerationReason) {
		this.lsregenerationReason = lsregenerationReason;
	}

	/**
	 * @return the lsOpsEmail
	 */
	public String getLsOpsEmail() {
		return lsOpsEmail;
	}

	/**
	 * @param lsOpsEmail the lsOpsEmail to set
	 */
	public void setLsOpsEmail(String lsOpsEmail) {
		this.lsOpsEmail = lsOpsEmail;
	}

	/**
	 * @return the lsOpsName
	 */
	public String getLsOpsName() {
		return lsOpsName;
	}

	/**
	 * @param lsOpsName the lsOpsName to set
	 */
	public void setLsOpsName(String lsOpsName) {
		this.lsOpsName = lsOpsName;
	}

	/**
	 * @return the lsOpsPhone
	 */
	public String getLsOpsPhone() {
		return lsOpsPhone;
	}

	/**
	 * @param lsOpsPhone the lsOpsPhone to set
	 */
	public void setLsOpsPhone(String lsOpsPhone) {
		this.lsOpsPhone = lsOpsPhone;
	}



	/**
	 * @return the lsExipryDays
	 */
	public long getLsExipryDays() {
		return lsExipryDays;
	}

	/**
	 * @param lsExipryDays the lsExipryDays to set
	 */
	public void setLsExipryDays(long lsExipryDays) {
		this.lsExipryDays = lsExipryDays;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the lsStatus
	 */
	public String getLsStatus() {
		return lsStatus;
	}

	/**
	 * @param lsStatus the lsStatus to set
	 */
	public void setLsStatus(String lsStatus) {
		this.lsStatus = lsStatus;
	}
	
	
	
}
