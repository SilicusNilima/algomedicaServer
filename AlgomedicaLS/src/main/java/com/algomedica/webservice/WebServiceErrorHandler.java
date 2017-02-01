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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.InvalidRequestException;
import com.algomedica.exception.InvalidSessionException;
import com.algomedica.util.ApplicationConstant;
import com.algomedica.util.ErrorCodesConstants;

/**
 * @author SQureshi
 *
 */
@ControllerAdvice
public class WebServiceErrorHandler {
	private static final Logger LOGGER = Logger.getLogger(WebServiceErrorHandler.class);

	@ExceptionHandler({ ApplicationException.class})
	protected ResponseEntity<ResponseInfo> handleApplicationException(Exception e,
	        WebRequest request) {
		ApplicationException ce = (ApplicationException) e;
		LOGGER.error("Got Application Exception : "+e.getMessage());
		ResponseInfo wsResponseBody = new ResponseInfo();
		wsResponseBody.setStatus(ApplicationConstant.ERROR);

		ErrorResource error = new ErrorResource(ce.getCode(), ce.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		wsResponseBody.setErrors(error);
		return new ResponseEntity<>(wsResponseBody, headers,
		        HttpStatus.OK);
	}
	@ExceptionHandler({ InvalidSessionException.class})
	protected ResponseEntity<ResponseInfo> handleSessionException(Exception e,
	        WebRequest request) {
		InvalidSessionException ce = (InvalidSessionException) e;
		LOGGER.error("Got Session Exception : "+e.getMessage());
		ResponseInfo wsResponseBody = new ResponseInfo();
		wsResponseBody.setStatus(ApplicationConstant.ERROR);
		ErrorResource error = new ErrorResource(ce.getCode(), ce.getMessage());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		wsResponseBody.setErrors(error);
		return new ResponseEntity<>(wsResponseBody, headers,
		        HttpStatus.OK);
	}
	@ExceptionHandler({ CannotCreateTransactionException.class})
	protected ResponseEntity<ResponseInfo> handleTransactionException(Exception e) {
		LOGGER.error("Got Transaction Exception : "+e.getMessage());
		ResponseInfo wsResponseBody = new ResponseInfo();
		wsResponseBody.setStatus(ApplicationConstant.ERROR);
		ErrorResource error = new ErrorResource(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		wsResponseBody.setErrors(error);
		return new ResponseEntity<>(wsResponseBody, headers,
		        HttpStatus.OK);
	}
	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<ErrorResource> handleInvalidRequest(Exception e,
	        WebRequest request) {
		LOGGER.error("Got Invalid Request Exception : "+e.getMessage());
		InvalidRequestException ire = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
		List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		LOGGER.debug("fieldErrors  :::::::::::::::::::::::::::::::::::::: \n"
		        + fieldErrors);
		for (FieldError fieldError : fieldErrors) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}
		ErrorResource error = new ErrorResource(ire.getCode(), ire.getMessage());
		error.setFieldErrors(fieldErrorResources);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<>(error, headers,
		        HttpStatus.UNPROCESSABLE_ENTITY);
	}
}