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

import com.algomedica.entity.City;

/**
 * @author SQureshi
 * 
 */
public interface CityDao {
	
	
	/**
	 * It is used to fetch all cities from Database
	 * @return list of  all {@link City} available in database
	 */
	public List<City> getAllCities();
	
	/**
	 * It is used to fetch city by specific city id
	 * @param id
	 * @return {@link City}
	 */
	public City getCityById(Long id);
	
	
	/**
	 *  It is used to fetch city by specific city name
	 * @param name
	 * @return {@link City}
	 */
	public City getCityByName(String name);

}
