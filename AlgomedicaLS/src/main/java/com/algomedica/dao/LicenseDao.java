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
package com.algomedica.dao;

import java.util.List;

import com.algomedica.entity.Customer;
import com.algomedica.entity.LicenseDetail;

/**
 * @author KLele
 *
 */
public interface LicenseDao {

	// License
	/**
	 * It is used to check duplicate license via unique macAddress
	 * 
	 * @param macAddress
	 * @return {@link LicenseDetail}
	 */
	public LicenseDetail getLicenceByMacAddress(String macAddress);

	/**
	 * It is used to get license details via customer_id
	 * 
	 * @param id
	 * @return {@link LicenseDetail}
	 */
	public LicenseDetail getLicenseById(long id);

	/**
	 * It is used to save {@link LicenseDetail}
	 * 
	 * @param licenseDetail
	 * @return void
	 */
	public void saveLicense(LicenseDetail licenseDetail);

	/**
	 * It is used to Update {@link LicenseDetail}
	 * 
	 * @param licenseDetail
	 * @return void
	 */
	void updateLicense(LicenseDetail licenseDetail);

	/**
	 * It is used fetch all license belongs to Customer
	 * 
	 * @param customer
	 * @return list of {@link LicenseDetail}
	 */
	public List<LicenseDetail> getLicenseDetailsByCustomerId(Customer customer);
}
