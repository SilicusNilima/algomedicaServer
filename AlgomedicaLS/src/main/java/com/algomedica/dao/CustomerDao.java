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

import com.algomedica.entity.Customer;

/**
 * @author SQureshi
 *
 */
public interface CustomerDao {
	/**
	 * It is used to fetch {@link Customer} by customer id 
	 * @param id
	 * @return {@link Customer}
	 */
	public Customer getCustomerById(Long id);
   
	
    /**
     * It is used to save {@link Customer} details
     * @param {@link Customer}
     */
    void saveCustomer(Customer customer);
    
    /**
     * It is used to update {@link Customer} details
     * @param {@link Customer}
     */
    void updateCustomer(Customer customer);
    
    /**
     * It is used to fetch all {@link Customer} data
     * @return List of {@link Customer}
     */
    public List<Customer> getAllCustomers();
    
    /**
     * It is used to fetch {@link Customer} data based on role
     * @return List of {@link Customer}
     */
    public List<Customer> getCustomersByUserId(Long id);
    
  
    
}
