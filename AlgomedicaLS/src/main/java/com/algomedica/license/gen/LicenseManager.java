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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;

import com.algomedica.entity.LicenseDetail;
import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.KeyGenerationException;
import com.algomedica.util.ErrorCodesConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class {@link LicenseManager} is responsible for creating a license based
 * on the license properties and generating a activation code
 * 
 * @author SMurmu
 *
 */
public class LicenseManager {

	/**
	 * Identifier for signature data in the license key
	 */
	private static final String SIGNATURE = "signature";
	private static final Logger LOGGER = Logger.getLogger(LicenseManager.class);
	private EncryptionManager encryptionManager;

	/**
	 * Gets an instance of {@link EncryptionManager}. The instantiation 
	 * of {@link EncryptionManager} loads RSA public and private keys 
	 */
	public LicenseManager() {
		this.encryptionManager = EncryptionManager.getEncryptionManager();
	}

	/**
	 * Uses {@link KeyGenerator#encrypt(String, String)} to encrypt license properties (e.g., device Mac
	 * address, device model number, number of days valid, etc) and writes them
	 * to a file (license). Additionally, the properties are concatenated and
	 * encrypted using {@link KeyGenerator#encrypt(String, String)} to generate a unique string. The
	 * unique string is converted to a hexadecimal value and a substring (0-11)
	 * is taken to create a activation code
	 * 
	 * @param lc
	 * @return
	 * @throws IOException
	 * @throws GeneralSecurityException 
	 */
	public String generateLicenseKey(LicenseContent lc) throws KeyGenerationException {

		String mac = lc.getMacAddr();
		String name = lc.getName();
		String type = lc.getType();
		// this is in milliseconds
		String validity = lc.getValidity();

		String category = lc.getCategory();
		category = category.substring(0, 1);
		String data = new StringBuilder(String.valueOf(new Date().getTime())).append(mac).append(validity).append(type)
				.append(category).toString();
		String licenseKey = null;
		try {
			licenseKey = KeyGenerator.encrypt(data, "AES");
			LOGGER.info("License content contanated and encrypted using AES");
			mac = KeyGenerator.encrypt(mac, "AES");
			LOGGER.info("MAC address encrypted using AES");
			type = KeyGenerator.encrypt(type, "AES");
			LOGGER.info("License type  encrypted using AES");
			validity = KeyGenerator.encrypt(validity, "AES");
			LOGGER.info("License valid days encrypted using AES");
		} catch (GeneralSecurityException e) {
			LOGGER.error("Exception in generateLicense key Encryption", e);
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
		}
		lc.setProperty("licenseKey", licenseKey);
		lc.setProperty("data0", category);
		lc.setProperty("data1", validity);
		lc.setProperty("data2", type);
		lc.setProperty("data3", mac);
		lc.setProperty("data4", name);
		writeLicense(lc);
		String hex = new StringBuilder(lc.getType()).append(lc.getCategory().substring(0, 1))
				.append(toHexString(licenseKey.getBytes(StandardCharsets.UTF_8))).toString();
		return formatKey(hex);
	}

	/**
	 * This is an overloaded method of {@link #generateLicenseKey(LicenseContent)}
	 * 
	 * @param lc
	 * @param licenseDetail
	 * @return
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 * @throws GeneralSecurityException 
	 */
	public LicenseDetail generateLicenseKey(LicenseContent lc, LicenseDetail licenseDetail)
			throws KeyGenerationException {

		String mac = lc.getMacAddr();
		String type = lc.getType();
		String name = lc.getName();
		// this is in milliseconds
		String validity = lc.getValidity();
		String category = lc.getCategory();
		category = category.substring(0, 1);
		String data = new StringBuilder(String.valueOf(new Date().getTime())).append(mac).append(validity).append(type)
				.append(category).toString();
		String licenseKey = null;
		try {
			licenseKey = KeyGenerator.encrypt(data, "AES");
			mac = KeyGenerator.encrypt(mac, "AES");
			type = KeyGenerator.encrypt(type, "AES");
			validity = KeyGenerator.encrypt(validity, "AES");
		
		lc.setProperty("licenseKey", licenseKey);
		lc.setProperty("data0", category);
		lc.setProperty("data1", validity);
		lc.setProperty("data2", type);
		lc.setProperty("data3", mac);
		lc.setProperty("data4", name);
		licenseDetail.setBlobkey(new SerialBlob(writeLicense(lc)));
		String hex = new StringBuilder(lc.getType()).append(lc.getCategory().substring(0, 1))
				.append(toHexString(licenseKey.getBytes(StandardCharsets.UTF_8))).toString();
		String serialKey = formatKey(hex);
		licenseDetail.setLsLicenseKey(serialKey);
		} catch (GeneralSecurityException|SQLException e) {
			LOGGER.error("Exception in generateLicense key Encryption", e);
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR,e);
		}
		return licenseDetail;

	}

	/**
	 * The method accepts a string with length greater than 12, takes first 11
	 * characters and format them with a hypen
	 * 
	 * @param hex
	 * @return formated String
	 */
	private static String formatKey(String hex) {
		String tempHex = hex.substring(0, 13);
		tempHex = insert(tempHex, "-", 1);
		tempHex = insert(tempHex, "-", 6);
		tempHex = insert(tempHex, "-", 11);
		return tempHex;
	}

	private static String insert(String source, String helper, int index) {
		String bagBegin = source.substring(0, index);
		String bagEnd = source.substring(index);
		return bagBegin + helper + bagEnd;
	}

	/**
	 * Converts a byte array to Hexadecimal String representation
	 * @param bytes
	 * @return
	 */
	private static String toHexString(byte[] bytes) {
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * Writes License Content to a file system
	 * 
	 * Initially the content is digitally signed using {@link EncryptionManager#sign(byte[])}
	 * The signed data is encoded using Base64 scheme and the result is written to license file
	 * 
	 * @param lc
	 * @return
	 * @throws IOException
	 */
	private byte[] writeLicense(LicenseContent lc) throws KeyGenerationException {
		Properties prop = new Properties();
		ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
	
		// Write the property file
		try {
			ObjectOutputStream out = new ObjectOutputStream(dataStream);
			byte[] data =lc.getProperty("licenseKey").getBytes(StandardCharsets.UTF_8);// writeLicenseToByteArray(lc)
			// Then sign the byte array
			byte[] signature = this.encryptionManager.sign(data);

			String base64signature = Base64.getEncoder().encodeToString(signature);
			ObjectMapper mapper = new ObjectMapper();
			mapper.convertValue(lc, Map.class);
			// Create property file

			for (Entry<String, String> e : lc.getProperties().entrySet()) {
				prop.setProperty(e.getKey(), e.getValue());
			}
			prop.put(SIGNATURE, base64signature);

			prop.store(out, "License file");
		} catch (GeneralSecurityException|IOException e1) {

			LOGGER.error("Exception in Writing license", e1);
			throw new KeyGenerationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR,e1);
		}
		return dataStream.toByteArray();
	}

	/**
	 * Converts a license content to bytes
	 * @param lc
	 * @return
	 * @throws IOException
	 */
	protected byte[] writeLicenseToByteArray(LicenseContent lc) throws IOException {
		ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(dataStream);
		// Sort the key to have a predictable results.
		List<String> keys = new ArrayList<>(lc.getProperties().keySet());
			for (String key : keys) {
			String value = lc.getProperty(key);
			out.writeChars(key);
			out.writeChars(value);
		}
		out.flush();
		byte[] data = dataStream.toByteArray();
		out.close();
		return data;
	}

}
