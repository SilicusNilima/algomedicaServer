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
package com.algomedica.exception;

import org.springframework.validation.Errors;

/**
 * InvalidRequestException exceptions class that can be thrown when invalid parameters are in request.
 * @author SQureshi
 *
 */
public class InvalidRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private  transient Errors errors;
	private final int code;

	public InvalidRequestException(int code, String message, Errors errors) {
		super(message);
		this.code = code;
		this.errors = errors;
	}

	public Errors getErrors() {
		return errors;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

}