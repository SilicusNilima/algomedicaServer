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
package com.algomedica.service;

import java.util.Map;

import com.algomedica.entity.Customer;
import com.algomedica.form.LicenseForm;

public interface LicenseManagerService {

	/**
	 * It is responsible to create new License Details.
	 * @param licenseForm
	 * @param userId
	 * @return List<LicenseDetail>
	 */
	public Customer generateLicense(LicenseForm licenseForm,long userId);
	
	/**
	 * It is responsible for creating existing License Details.
	 * @param licenseForm
	 * @param userId
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public Customer updateLicense(LicenseForm licenseForm,long userId);

	/**
	 * @param id
	 * @return
	 */
	public Map<String, Object> getLicenseDetail(String id);
}
