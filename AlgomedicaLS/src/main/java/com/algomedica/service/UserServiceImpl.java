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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algomedica.dao.UserDao;
import com.algomedica.entity.User;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.ORMException;
import com.algomedica.util.EncryptionUtils;
import com.algomedica.util.ErrorCodesConstants;
import com.algomedica.webservice.UserWebService;

/**
 * @author SQureshi
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = Logger.getLogger(UserWebService.class);
	@Autowired
	UserDao userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.service.UserService#login(java.util.Map)
	 */
	@Override
	public User login(Map<String, String> loginUser) {
		String username = loginUser.get("username");
		String password = loginUser.get("password");
		LOGGER.info("user is login with username " + username);
		User user = null;
		try {
			user = userDao.getUserbyUsername(username);
			if (user == null || !user.getPassword().equals(EncryptionUtils.encryptString(password))) {

				LOGGER.error("Login failed wrong user credentials");
				throw new ApplicationException(ErrorCodesConstants.INVALID_LOGIN_CODE,
						ErrorCodesConstants.INVALID_LOGIN);
			}
			user.setUsrLastlogin(new Date());
			LOGGER.info("user is login successfully");
		} catch (ORMException ormExpt) {
			LOGGER.error("Login failed due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExpt);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.algomedica.service.UserService#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() {
		List<User> userList = null;
		try {
			userList = userDao.getAllUsers();
		} catch (ORMException ormExpt) {
			LOGGER.error("internal server error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExpt);
		}
		return userList;
	}

}
