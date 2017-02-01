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

import com.algomedica.entity.State;

/**
 * @author SQureshi
 *
 */
public interface StateDao {
	/**
	 * It is used to fetch all states list
	 * 
	 * @return list of all {@link State}
	 */
	public List<State> getAllStates();

	/**
	 * It is used to fetch {@link State} by state name 
	 * @param name
	 * @return {@link State}
	 */
	public State getStateByName(String name);

	/**
	 * It is used to fetch {@link State} by state_id 
	 * @param id
	 * @return {@link State}
	 */
	public State getStateById(Long id);
}
