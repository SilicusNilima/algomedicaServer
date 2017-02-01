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
 * InvalidSessionException exceptions class that can be thrown for invalid session.
 * @author SQureshi
 *
 */
public class InvalidSessionException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final int code;
	private String message;
	/**
	 * @param code
	 * @param message
	 */
	public InvalidSessionException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	
	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

}
