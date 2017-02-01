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

import java.util.List;
import java.util.Map;

import com.algomedica.entity.User;

/**
 * @author SQureshi
 *
 */
public interface UserService {
	/**
	 *  It is responsible to login user
	 * @param user
	 * @return  {@link User}
	 */
	public User login(Map<String, String> user);

	/**
	 *  It is responsible for get all user.
	 * @return list of {@link User}
	 */
	public List<User> getAllUsers();
	
}
