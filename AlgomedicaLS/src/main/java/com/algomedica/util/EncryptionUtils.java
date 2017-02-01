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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.algomedica.exception.ApplicationException;

/**
 * EncryptionUtil is used for encryption
 * 
 * @author SQureshi
 *
 */
public class EncryptionUtils {
	private static final Logger LOGGER = Logger.getLogger(EncryptionUtils.class);

	private EncryptionUtils() {
		super();
	}

	/**
	 * It is used encrypt a string.
	 * 
	 * @param str
	 * @return String
	 */
	public static String encryptString(String str) {
		LOGGER.info("String to be encrypted is : " + str);
		byte[] salt = new byte[16];
		byte[] hash = null;
		String hashString;
		for (int i = 0; i < 16; i++) {
			salt[i] = (byte) i;
		}
		try {
			KeySpec spec = new PBEKeySpec(str.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();

		} catch (NoSuchAlgorithmException nsale) {
			LOGGER.error(nsale);

		} catch (InvalidKeySpecException ikse) {
			LOGGER.error(ikse);
		}
		hashString = DatatypeConverter.printBase64Binary(hash);
		LOGGER.info("Encrypted string is : " + hashString);
		return hashString;
	}

	public static byte[] serialize(Object obj) throws IOException {
		LOGGER.info("Serializing object");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws ApplicationException  {
		Object objIn =null;
		try {
			LOGGER.info("Deserializing object");
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ObjectInputStream is = new ObjectInputStream(in);
			objIn = is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error("During deserialization ");
			throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR, e);
		}
	    return objIn;
	}

	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
		LOGGER.info("Zip file : " + filename);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename);
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}

}
