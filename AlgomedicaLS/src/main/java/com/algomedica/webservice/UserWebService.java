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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.algomedica.entity.User;
import com.algomedica.exception.InvalidSessionException;
import com.algomedica.service.UserService;
import com.algomedica.util.ApplicationConstant;
import com.algomedica.util.ErrorCodesConstants;

/**
 * @author SQureshi
 *
 */
@RestController
public class UserWebService {

	private static final Logger LOGGER = Logger.getLogger(UserWebService.class);
	@Autowired
	UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseInfo> getAllUser(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("---Get user start---");
		ResponseInfo responseBody = new ResponseInfo();
		List<User> responseData;
		responseData = userService.getAllUsers();
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(responseData);
		LOGGER.info("---Get user end---");
		return new ResponseEntity<ResponseInfo>(responseBody, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo login(@RequestBody Map<String, String> user, HttpServletRequest request, HttpSession session) {
		LOGGER.info("--Login User Start-- " + user.get("username"));
		ResponseInfo responseBody = new ResponseInfo();
		User responseData;
		responseData = userService.login(user);
		session.setAttribute("user", responseData);
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		responseBody.setData(responseData);
		LOGGER.info("--Login User Ends--");
		return responseBody;
	}

	@GetMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo logout(HttpSession session) {
		LOGGER.info("--Logout User Start--");
		ResponseInfo responseBody = new ResponseInfo();
		session.invalidate();
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		LOGGER.info("--Logout User End--");
		return responseBody;
	}

	@GetMapping(value = "/session", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseInfo validateSession(HttpServletRequest request) {
		LOGGER.info("--validating session User Start--");
		if (!request.isRequestedSessionIdValid()) {
			throw new InvalidSessionException(ErrorCodesConstants.INVALID_SESSION_CODE,
					ErrorCodesConstants.INVALID_SESSION);
		}
		ResponseInfo responseBody = new ResponseInfo();
		responseBody.setStatus(ApplicationConstant.SUCCESS);
		LOGGER.info("--validating session User End--");
		return responseBody;
	}
}