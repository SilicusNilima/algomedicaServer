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
package com.algomedica.util;

/**
 * ErrorCodesConstants has constant of error codes and messages. 
 * @author SQureshi
 *
 */
public class ErrorCodesConstants {
	public static final int INVALID_SESSION_CODE = 1000;
	public static final  String INVALID_SESSION = "Invalid Session,please login again";
	
	public static final int INVALID_INPUT_CODE = 1001;
	public static final  String INVALID_INPUT = "Invalid Input Parameters";
	
	public static final int DUPLICATE_MAC_ADDRESS_CODE = 1002;
	public static final  String DUPLICATE_MAC_ADDRESS = "Duplicate Mac Address";
	
	public static final int INTERNAL_SYSTEM_ERROR_CODE = 1003;
	public static final String INTERNAL_SYSTEM_ERROR = "Internal server error";
	
	public static final int INVALID_LOGIN_CODE = 1005;
	public static final String INVALID_LOGIN = "Invalid user credentials";
	
	public static final int INVALID_LICENSE_ID_CODE=1006;
	public static final String INVALID_LICENSE_ID="Invalid license Id";
	
	public static final int INVALID_DAYS_CODE = 1007;
	public static final String INVALID_DAYS = "Invalid Expiry Days";
	
	public static final int NO_RECORD_EXIST_CODE = 2001;
	public static final String NO_RECORD_EXIST = "No record exist";
	
	private ErrorCodesConstants(){
		super();
	}
	
}
