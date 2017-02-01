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

import com.algomedica.entity.Country;

/**
 * @author SQureshi
 *
 */
public interface CountryDao {

	/**
	 * It is used to fetch list of all countries from database
	 * 
	 * @return list of {@link Country}
	 */
	public List<Country> getAllCountries();

	/**
	 * It is used top fetch specific country by county_id ;
	 * 
	 * @param id
	 * @return {@link Country}
	 */
	public Country getCountryById(Long id);

	/**
	 * It is used to check availability of specific county name by country name
	 * @param name
	 * @return {@link Country}
	 */
	public Country getCountryByName(String name);
}
