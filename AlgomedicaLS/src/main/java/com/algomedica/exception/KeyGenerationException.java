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
 * KeyGenerationException exceptions class that can be thrown for wrapping all application exception. 
 * @author SQureshi
 *
 */
public class KeyGenerationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;

	/**
	 * 
	 */
	public  KeyGenerationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KeyGenerationException(Throwable cause)
	{
		super(cause);
	}

	public KeyGenerationException(int code, String message)
	{
		super(code + " : " + message);
		this.code = code;
		this.message = message;
	}

	public KeyGenerationException(int code, String message, Throwable cause)
	{
		super(code + " : " + message, cause);
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
