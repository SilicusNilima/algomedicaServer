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

import com.algomedica.entity.User;

/**
 * @author SQureshi
 *
 */
public interface UserDao {

	/**
	 * It is used to fetch {@link User} by id
	 * 
	 * @param id
	 * @return {@link User}
	 */
	public User getUserById(long id);

	/**
	 * It is used to save {@link User}
	 * 
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * It is used to fetch all Users
	 * 
	 * @return list of all {@link User}
	 */
	public List<User> getAllUsers();

	/**
	 * It is used to fetch {@link User} by name
	 * 
	 * @param username
	 * @return {@link User}
	 */
	public User getUserbyUsername(String username);

}
