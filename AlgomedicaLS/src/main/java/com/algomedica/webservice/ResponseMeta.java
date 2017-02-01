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

public class ResponseMeta {

	/**
	 * Get the below parameters from HttpServlet request object.
	 */
	private String clientIp;

	private String clientPort;

	private String clientHost;

	/**
	 * @return the clientIp
	 */
	public String getClientIp() {
		return clientIp;
	}

	/**
	 * @param clientIp
	 *            the clientIp to set
	 */
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * @return the clientPort
	 */
	public String getClientPort() {
		return clientPort;
	}

	/**
	 * @param clientPort
	 *            the clientPort to set
	 */
	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	/**
	 * @return the clientHost
	 */
	public String getClientHost() {
		return clientHost;
	}

	/**
	 * @param clientHost
	 *            the clientHost to set
	 */
	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

}