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
 * InvalidSessionException exceptions class that can be thrown for wrapping all persisting exception.
 * @author squreshi
 *
 */
public class ORMException extends ApplicationException {
	
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	public ORMException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ORMException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param code
	 * @param message
	 */
	public ORMException(int code, String message) {
		super(code, message);
	}

	/**
	 * @param code
	 * @param message
	 * @param cause
	 */
	public ORMException(int code, String message, Throwable cause) {
		super(code, message, cause);
	}

}
