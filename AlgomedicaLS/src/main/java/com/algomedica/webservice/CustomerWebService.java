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
package com.algomedica.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.algomedica.entity.Customer;
import com.algomedica.entity.Role;
import com.algomedica.entity.User;
import com.algomedica.exception.InvalidSessionException;
import com.algomedica.form.CustomerForm;
import com.algomedica.form.LicenseForm;
import com.algomedica.service.CustomerService;
import com.algomedica.service.LicenseManagerService;
import com.algomedica.util.ApplicationConstant;
import com.algomedica.util.ErrorCodesConstants;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author SQureshi Custoemr web service is a controller for customer seb
 *         service.
 */
@RestController
public class CustomerWebService {

	private static final Logger LOGGER = Logger.getLogger(CustomerWebService.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	LicenseManagerService licenseManagerService;

	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo getAllcustomers(HttpServletRequest request, HttpSession session) {
		LOGGER.info("--Get Customer Start--");
		ResponseInfo responseBody = new ResponseInfo();
		List<Customer> customers;
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		User user = (User) userObj;
		Role role = user.getRole();
		if (role != null && ApplicationConstant.ADMIN_ROLE.equals(role.getName())) {
			customers = customerService.getAllCustomers();
		} else {
			customers = customerService.getAllCustomersByUserId(user.getId());
		}

		responseBody.setData(customers);
		LOGGER.info("--Get Customer End--");
		return responseBody;

	}

	/**
	 * customer veb service to add customer.
	 * 
	 */
	@PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo addCustomer(@RequestBody CustomerForm customerForm, HttpServletRequest request,
			HttpSession session) throws JsonProcessingException {
		LOGGER.info("--Add Customer Start--");
		ResponseInfo responseBody = new ResponseInfo();
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		User user = (User) userObj;
		customerForm.setUserId(user.getId());
		Customer customer = customerService.saveCustomer(customerForm);
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(customer);
		LOGGER.info("--Add Customer End--");
		return responseBody;
	}

	/**
	 * customer veb service to update customer.
	 * 
	 */
	@PutMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo editCustomer(@RequestBody CustomerForm customerform, HttpServletRequest request,
			HttpSession session) throws JsonProcessingException {
		LOGGER.info("--Update Customer Start--");
		ResponseInfo responseBody = new ResponseInfo();
		User user;
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		user = (User) userObj;
		customerform.setUserId(user.getId());
		Customer customer = customerService.updateCustomer(customerform);
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(customer);
		LOGGER.info("--Update Customer End--");
		return responseBody;
	}

	@PostMapping(value = "/customers/license", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo addLicense(@RequestBody LicenseForm licenseForm, HttpServletRequest request,
			HttpSession session) throws JsonProcessingException {
		LOGGER.info("--Add Customer License Start--");
		ResponseInfo responseBody = new ResponseInfo();
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		User user = (User) userObj;
		Customer customer = licenseManagerService.generateLicense(licenseForm, user.getId());
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(customer);
		LOGGER.info("--Add Customer License End--");
		return responseBody;
	}

	@PutMapping(value = "/customers/license", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo updateLicense(@RequestBody LicenseForm licenseForm, HttpServletRequest request,
			HttpSession session) throws JsonProcessingException {
		LOGGER.info("--Update Customer Start License--");
		ResponseInfo responseBody = new ResponseInfo();
		Object userObj = session.getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		User user = (User) userObj;
		Customer customer = licenseManagerService.updateLicense(licenseForm, user.getId());
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(customer);
		LOGGER.info("--Update Customer License End--");
		return responseBody;
	}

	@RequestMapping(value = "/customers/license/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		LOGGER.info("--Get getFile Start--");
		Map<String, Object> map;
		ResponseEntity<byte[]> respEntity;
		Object userObj = request.getSession().getAttribute("user");
		if (userObj == null) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		map = licenseManagerService.getLicenseDetail(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/zip");
		responseHeaders.add("Content-Disposition", "attachment; filename=" + map.get("filename") + ".zip");
		response.getOutputStream().write((byte[]) map.get("data"));
		respEntity = new ResponseEntity<byte[]>(responseHeaders, HttpStatus.OK);
		LOGGER.info("--Get getFile End--");
		return respEntity;

	}
}
