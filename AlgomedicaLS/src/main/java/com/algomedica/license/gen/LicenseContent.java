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
package com.algomedica.license.gen;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The class {@link LicenseContent} contains properties about a license
 * 
 * @author Silicus
 *
 */
public class LicenseContent {

	private String name;
	private String macAddr;
	private String type;
	private String validity;
	private String category;

	private Map<String, String> properties;

	
	public LicenseContent(String name, String macAddr, String type, String validity, String category) {
		this.name = name;
		this.macAddr = macAddr;
		this.type = type;
		this.validity = validity;
		this.category = category;
		// place holders to hold additional properties
		this.properties = new HashMap<>();
	}
	/**
	 * Return an unmodifiable map of properties.
	 * 
	 * @return
	 */
	public Map<String, String> getProperties() {
		return Collections.unmodifiableMap(this.properties);
	}

	/**
	 * Get the property value.
	 * 
	 * @param key
	 *            the property key
	 */
	public String getProperty(String key) {
		return this.properties.get(key);
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the property value.
	 * 
	 * @param key
	 *            the property key
	 * @param value
	 *            the property value.
	 */
	public void setProperty(String key, String value) {
		if (value == null) {
			this.properties.remove(key);
		} else {
			this.properties.put(key, value);
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the macAddr
	 */
	public String getMacAddr() {
		return macAddr;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the validity
	 */
	public String getValidity() {
		return validity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macAddr == null) ? 0 : macAddr.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LicenseContent)) {
			return false;
		}
		LicenseContent other = (LicenseContent) obj;
		if (macAddr == null) {
			if (other.macAddr != null) {
				return false;
			}
		} else if (!macAddr.equals(other.macAddr)) {
			return false;
		}
		return true;
	}

}
