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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.apache.log4j.Logger;
public class LoadKeyPair {
	private static final Logger LOGGER = Logger.getLogger(LoadKeyPair.class);	
	private KeyPair keyPair;
	private static String ALGORITHM="RSA";
	public LoadKeyPair() throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
		
		GenerateRSAKeys generateRSAKeys=new GenerateRSAKeys();
		generateRSAKeys.generate();
		// Read Public Key.
		File filePublicKey = new File("public.key");
		byte[] encodedPublicKey = new byte[] {};
		try (FileInputStream fis = new FileInputStream(filePublicKey)) {
			StringBuffer sb = new StringBuffer();
			char current;
			while (fis.available() > 0) {
				current = (char) fis.read();
				sb.append(current);
			}
			LOGGER.info(sb.toString());
			encodedPublicKey = Base64.getDecoder().decode(sb.toString());

		} catch (IOException e) {
			LOGGER.error(e);
		}
		// Read Private Key.
		File filePrivateKey = new File("private.key");
		byte[] encodedPrivateKey = new byte[] {};
		try (FileInputStream fis = new FileInputStream(filePrivateKey)) {
			StringBuffer sb = new StringBuffer();
			char current;
			while (fis.available() > 0) {
				current = (char) fis.read();
				sb.append(current);
			}
			LOGGER.info(sb.toString());
			encodedPrivateKey =  Base64.getDecoder().decode(sb.toString());

		} catch (IOException e) {
			LOGGER.error(e);
		}
		// Generate KeyPair.
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
		setKeyPair(new KeyPair(publicKey, privateKey));
	}
	public KeyPair getKeyPair() {
		return keyPair;
	}
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
}