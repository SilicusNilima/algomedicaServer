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

/**
 * @author SQureshi
 *
 */
public class ResponseInfo {
	private ResponseMeta metaData;
	private String status;
	private Object data;
	private Object message;
	private ErrorResource errors;
	private int code;

	public ResponseMeta getMetaData() {
		return metaData;
	}

	public void setMetaData(ResponseMeta metaData) {
		this.metaData = metaData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ErrorResource getErrors() {
		return errors;
	}

	public void setErrors(ErrorResource errors) {
		this.errors = errors;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}