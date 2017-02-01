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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algomedica.dao.CityDao;
import com.algomedica.dao.CountryDao;
import com.algomedica.dao.CustomerDao;
import com.algomedica.dao.StateDao;
import com.algomedica.entity.Address;
import com.algomedica.entity.City;
import com.algomedica.entity.Country;
import com.algomedica.entity.Customer;
import com.algomedica.entity.State;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.ORMException;
import com.algomedica.form.CustomerForm;
import com.algomedica.util.ErrorCodesConstants;

/**
 * @author SQureshi
 *
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);	
	@Autowired
	CustomerDao customerDao;
	@Autowired
	CityDao cityDao;
	@Autowired
	StateDao stateDao;
	@Autowired
	CountryDao countryDao;
	
	/* (non-Javadoc)
	 * @see com.algomedica.service.CustomerService#getCustomerById(java.lang.Long)
	 */
	@Override
	public Customer getCustomerById(Long id) {
		Customer customer;
		if(id == null){
			throw new ApplicationException(ErrorCodesConstants.INVALID_INPUT_CODE, ErrorCodesConstants.INVALID_INPUT);
		}
		LOGGER.info("Get customer by id " + id);
		try {
			customer = customerDao.getCustomerById(id);
		} catch (ORMException ormExp) {
			LOGGER.error("Get customer failled due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExp);
		}
		return customer;
	}
	
	/* (non-Javadoc)
	 * @see com.algomedica.service.CustomerService#saveCustomer(com.algomedica.form.CustomerForm)
	 */
	@Override
	public Customer saveCustomer(CustomerForm customerForm) {
		Customer customer = new Customer();
		Address addres = new Address();
		LOGGER.info("Add new customer with name="+customerForm.getOrgName()+" email="+customerForm.getOrgEmail());
		City city;
		State state;
		Country country;
		String formCityName = customerForm.getCityName();
		String formStateName = customerForm.getStateName();
		String formCountryName = customerForm.getCountryName();
		if(customerForm.getOrgName() == null || formCityName == null || formStateName ==null || formCountryName == null){
			LOGGER.error("Add customer failled due to some field are invalid");
			throw new ApplicationException(ErrorCodesConstants.INVALID_INPUT_CODE,ErrorCodesConstants.INVALID_INPUT);
		}
		customer.setOrgPersonName(customerForm.getOrgPersonName());
		customer.setOrgName(customerForm.getOrgName());
		customer.setOrgEmail(customerForm.getOrgEmail());
		customer.setOrgPhone(customerForm.getOrgPhone());
		customer.setOrgStatus(customerForm.getOrgStatus());
		customer.setOrgPersonPhone(customerForm.getOrgPersonPhone());
		customer.setOrgStatus(1);
		addres.setAddress1(customerForm.getAddressLine1());
		addres.setAddress2(customerForm.getAddressLine2());
		customer.setCreatedBy(customerForm.getUserId());
		customer.setUpdatedBy(customerForm.getUserId());

		try {
			city = cityDao.getCityByName(formCityName);
			if (city != null) {
				addres.setCity(city);
			} else {
				city = new City();
				city.setCityName(formCityName);
				city.setZipCode(customerForm.getPostalCode());
				addres.setCity(city);
				state = stateDao.getStateByName(formStateName);
				if (state != null) {
					city.setState(state);
				} else {
					state = new State();
					state.setStateName(formStateName);
					city.setState(state);
					country = countryDao.getCountryByName(formCountryName);
					if (country != null) {
						state.setCountry(country);
					} else {
						country = new Country();
						country.setCountryName(formCountryName);
						state.setCountry(country);
					}
				}
			}
			customer.setAddress(addres);
			customerDao.saveCustomer(customer);
			LOGGER.info("Added customer successfully"+customer.getId());
		} catch (ORMException ormExp) {
			LOGGER.error("Add customer failled due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExp);
		}
		return customer;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.algomedica.service.CustomerService#updateCustomer(com.algomedica.form.CustomerForm)
	 */
	@Override
	public Customer updateCustomer(CustomerForm customerForm) {
		Customer customer = null;
		Address addres = null;
		try {
		 customer = getCustomerById(customerForm.getId());
		 if(customer == null){
			 LOGGER.error("No customer exist with this id = "+customerForm.getId());
				throw new ApplicationException(ErrorCodesConstants.NO_RECORD_EXIST_CODE,ErrorCodesConstants.NO_RECORD_EXIST);
		 }
		 addres = customer.getAddress();
		LOGGER.info("update customer with name="+customerForm.getOrgName()+" email="+customerForm.getOrgEmail());
		City city;
		State state;
		Country country;
		String formCityName = customerForm.getCityName();
		String formStateName = customerForm.getStateName();
		String formCountryName = customerForm.getCountryName();
		if(customerForm.getOrgName() == null || formCityName == null || formStateName ==null || formCountryName == null){
			LOGGER.error("update customer failled due to some field are invalid");
			throw new ApplicationException(ErrorCodesConstants.INVALID_INPUT_CODE,ErrorCodesConstants.INVALID_INPUT);
		}
		customer.setOrgPersonName(customerForm.getOrgPersonName());
		customer.setOrgName(customerForm.getOrgName());
		customer.setOrgEmail(customerForm.getOrgEmail());
		customer.setOrgPhone(customerForm.getOrgPhone());
		customer.setOrgPersonPhone(customerForm.getOrgPersonPhone());
		addres.setAddress1(customerForm.getAddressLine1());
		addres.setAddress2(customerForm.getAddressLine2());
		customer.setUpdatedBy(customerForm.getUserId());
			city = cityDao.getCityByName(formCityName);
			if (city != null) {
				addres.setCity(city);
			} else {
				city = new City();
				city.setCityName(formCityName);
				city.setZipCode(customerForm.getPostalCode());
				state = stateDao.getStateByName(formStateName);
				if (state != null) {
					city.setState(state);
				} else {
					state = new State();
					state.setStateName(formStateName);
					city.setState(state);
					country = countryDao.getCountryByName(formCountryName);
					if (country != null) {
						state.setCountry(country);
					} else {
						country = new Country();
						country.setCountryName(formCountryName);
						state.setCountry(country);
					}
				}
				addres.setCity(city);
			}
			LOGGER.info("Customer updated successfully");
		} catch (ORMException ormExp) {
			LOGGER.error("update customer failled due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExp);
		}
		return customer;
	}


	/* (non-Javadoc)
	 * @see com.algomedica.service.CustomerService#getAllCustomers()
	 */
	@Override
	public List<Customer> getAllCustomers() {
		LOGGER.info("Get all customers");
		List<Customer> organisactions = null;
		try {
			organisactions = customerDao.getAllCustomers();
		} catch (ORMException ormExp) {
			LOGGER.error("Get all Customers failled due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExp);
		}
		return organisactions;
	}

	/* (non-Javadoc)
	 * @see com.algomedica.service.CustomerService#getAllCustomersByUserId(long)
	 */
	@Override
	public List<Customer> getAllCustomersByUserId(long id) {
		LOGGER.info("Get all customers for user id  "+id);
		List<Customer> organisactions = null;
		try {
			organisactions = customerDao.getCustomersByUserId(id);
		} catch (ORMException ormExp) {
			LOGGER.error("Get customer for user failled due to internal error");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, ormExp);
		}
		return organisactions;
	}

}
