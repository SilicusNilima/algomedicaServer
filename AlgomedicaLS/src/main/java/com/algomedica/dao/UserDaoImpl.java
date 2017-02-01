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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.algomedica.entity.User;
import com.algomedica.exception.ORMException;

/**
 * @author SQureshi
 *
 */
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	/* (non-Javadoc)
	 * @see com.algomedica.dao.UserDao#getUserById(long)
	 */
	@Override
	public User getUserById(long id) {
		User user = null;
		try {
			user = getByKey(id);
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.UserDao#saveUser(com.algomedica.entity.User)
	 */
	@Override
	public void saveUser(User user) {
		try {
			persist(user);
		} catch (Exception ex) {
			throw new ORMException(ex);
		}

	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.UserDao#getAllUsers()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		List<User> users = null;
		try {
			Criteria criteria = createEntityCriteria();
			users = criteria.list();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return users;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.UserDao#getUserbyUsername(java.lang.String)
	 */
	@Override
	public User getUserbyUsername(String username) {
		User user = null;
		try {
			 Criteria criteria = createEntityCriteria();
		        criteria.add(Restrictions.eq("username",username));
		        user = (User) criteria.uniqueResult();
		} catch (Exception ex) {
			throw new ORMException(ex);
		}
		return user;
	}
}
