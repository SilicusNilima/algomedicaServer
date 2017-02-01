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

import com.algomedica.entity.Customer;
import com.algomedica.form.CustomerForm;

/**
 * 
 * @author SQureshi
 *
 */
public interface CustomerService {
	/**
	 * It is responsible to get customer by its id
	 * @param id
	 * @return customer
	 */
	public Customer getCustomerById(Long id);
    /**
     * It is responsible for adding new customer.
     * @param customer
     */
    public Customer saveCustomer(CustomerForm customer);
    /**
     * It is responsible for editing existing customer.
     * @param customer
     */
    public Customer updateCustomer(CustomerForm customer);
    /**
     * It is responsible to get all customers.
     * @return list<customer>
     */
    public List<Customer> getAllCustomers();
    /**
     * It is responsible to get all customers which is added by user id.
     * @param id
     * @return List<Customer> 
     */
    public List<Customer> getAllCustomersByUserId(long id);
}
