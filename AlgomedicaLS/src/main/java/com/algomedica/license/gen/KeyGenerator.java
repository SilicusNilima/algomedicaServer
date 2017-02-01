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

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * The class {@link KeyGenerator} provides methods for encryption and decryption
 * methods with desired encryption algorithm. It uses a salt created from a
 * secret word
 * 
 * @author Silicus
 *
 */
public class KeyGenerator {

	private KeyGenerator() {

	}

	private static final String SECRET_WORD = "YouCanNeverKnowP";

	/**
	 * Performs data encryption based on the desired algorithm
	 * The data is converted into bytes and encrypted using the specified algorithm
	 * The encrypted date is further encoded with Base64 scheme and final result is returned. 
	 * 
	 * @param data
	 * @param algorithm
	 * @return Encrypted String
	 */
	public static String encrypt(String data, String algorithm) throws GeneralSecurityException {
		if (null == data) {
			throw new GeneralSecurityException("No data supplied");
		}
		if (null == algorithm) {
			throw new GeneralSecurityException("Algorithm not specified");
		}

		SecretKey secretKey = getSecretKey(SECRET_WORD);
		Cipher c;
		byte encoded[] = null;
		try {
			c = Cipher.getInstance(algorithm);
			c.init(Cipher.ENCRYPT_MODE, secretKey);
			encoded = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {

			throw new GeneralSecurityException(e);
		}
		return Base64.getMimeEncoder().encodeToString(encoded);
	}

	/**
	 * Performs decryption of the encrypted value
	 * 
	 * The encrypted data is decoded with Base64 scheme. The decoded data is decrypted with 
	 * a specified algorithm, used for encryption.
	 * The method returns the original data which is encrypted 
	 * 
	 * @param ecryptedData
	 * @return Decrypted String
	 */
	public static String decrypt(String encryptedData, String algorithm) throws GeneralSecurityException {
		if (null == encryptedData) {
			throw new GeneralSecurityException("No data supplied");
		}
		SecretKey secretKey = getSecretKey(SECRET_WORD);
		Cipher c = null;
		byte[] decValue = null;
		try {
			c = Cipher.getInstance(algorithm);
			c.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
			decValue = c.doFinal(decordedValue);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			throw new GeneralSecurityException(e);
		}

		return new String(decValue);
	}

	/**
	 * Generates a secret key from the secret work specified as constant above.
	 * The secret word is reversed and used as salt.  
	 * A secret key is generated from the bytes of salt using AES algorithm
	 * 
	 * @return SecretKey
	 */
	private static SecretKey getSecretKey(String secret_word) {
		String salt = new StringBuilder(secret_word).reverse().toString();
		return new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), "AES");
	}

	
}
