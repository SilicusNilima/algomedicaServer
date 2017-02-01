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

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algomedica.entity.Customer;
import com.algomedica.exception.ORMException;
/**
 * @author SQureshi
 *
 */
@Transactional
@Repository
public class CustomerDaoImpl extends AbstractDao<Long, Customer> implements CustomerDao  {
	private static final Logger LOGGER = Logger.getLogger(CustomerDaoImpl.class);	
	/* (non-Javadoc)
	 * @see com.algomedica.dao.CustomerDao#getCustomerById(java.lang.Long)
	 */
	@Override
	public Customer getCustomerById(Long id) {
		Customer customer = null;
		LOGGER.info("Get customer by id = "+id);
		try{
		customer = getByKey(id);
		}catch(Exception exp){
			LOGGER.error(exp);
			throw new ORMException(exp);
		}
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CustomerDao#saveCustomer(com.algomedica.entity.Customer)
	 */
	@Override
	public void saveCustomer(Customer customer) {
		try{
			persist(customer);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new ORMException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CustomerDao#updateCustomer(com.algomedica.entity.Customer)
	 */
	@Override
	public void updateCustomer(Customer customer) {
		try{
			update(customer);
		}catch (Exception e) {
			LOGGER.error(e);
			throw new ORMException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CustomerDao#getAllCustomers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;
		try {
			 Criteria criteria = createEntityCriteria();
			 criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.add(Restrictions.eq("orgStatus", 1 ));
		        customers = criteria.list();
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new ORMException(ex);
		}
		return customers;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.dao.CustomerDao#getCustomersByUserId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomersByUserId(Long id) {
		List<Customer> customers = null;
		try {
			LOGGER.info("Cet customer by user id + "+id);
			 Criteria criteria = createEntityCriteria();
			 	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			 	criteria.add(Restrictions.eq("orgStatus", 1 ));
		        criteria.add(Restrictions.eq("createdBy",id));
		        customers = criteria.list();
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new ORMException(ex);
		}
		return customers;
	}

}
