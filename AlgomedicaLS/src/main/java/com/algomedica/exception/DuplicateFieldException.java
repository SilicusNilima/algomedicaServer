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

/**
 * DuplicateFieldException exceptions class that can be thrown when unique field is duplicated.
 * @author squreshi
 *
 */
public class DuplicateFieldException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateFieldException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateFieldException(Throwable cause) {
		super(cause);
	}

	public DuplicateFieldException(int code, String message) {
		super(code, message);
	}

	public DuplicateFieldException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

}
