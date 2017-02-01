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

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.log4j.Logger;

import com.algomedica.exception.ApplicationException;
import com.algomedica.exception.KeyGenerationException;
import com.algomedica.util.ErrorCodesConstants;

/**
 * The class {@link EncryptionManager} is responsible for loading public and
 * private keys from a specified directory. The keys are loaded from a File
 * system path pre-defined by a environment variable (AGL_KEYS)
 * 
 * This is a singleton class
 * 
 * @author Silicus
 *
 */
public class EncryptionManager {

	private static EncryptionManager instance;

	/*private static final String PUBLIC_KEY_FILE = System.getenv("HOME") + File.separator + "public_key.der";
	private static final String PRIVATE_KEY_FILE = System.getenv("HOME") + File.separator + "private_key.der";
*/
	
	  private static final String PUBLIC_KEY_FILE = "D:\\" +
	  File.separator + "public_key.der"; private static final String
	  PRIVATE_KEY_FILE = "D:\\" + File.separator +
	  "private_key.der";
	 

	private static final Logger LOGGER = Logger.getLogger(EncryptionManager.class);
	private static PublicKey publicKey;
	private static PrivateKey privateKey;

	protected EncryptionManager() throws GeneralSecurityException {

	}

	/**
	 * A factory method to get an instance of {@link EncryptionManager} It loads
	 * RSA private keys {@link #loadPrivateKey(String)} and public keys
	 * {@link #loadPublicKey(String)}} from a specified directory specified in
	 * an environment variable
	 * 
	 * @return instance of EncryptionManager
	 */
	public static EncryptionManager getEncryptionManager() {
		if (null == instance) {
			try {
				instance = new EncryptionManager();
			} catch (GeneralSecurityException e) {

				LOGGER.error("EncryptionManager Creating Instanse", e);
				throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
						ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
			}
			try {
				privateKey = loadPrivateKey(PRIVATE_KEY_FILE);
			} catch (Exception e) {

				LOGGER.error("EncryptionManager Load private Key Instanse", e);
				throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
						ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
			}
			try {
				publicKey = loadPublicKey(PUBLIC_KEY_FILE);
			} catch (Exception e) {
				LOGGER.error("EncryptionManager Load public Instanse", e);
				throw new ApplicationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
						ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
			}
		}
		return instance;
	}

	/**
	 * Loads the public key from a specified path The public key is part of the
	 * PKI cryptography key pairs generated using RSA algorithm
	 * 
	 * @param filename
	 * @return PublicKey
	 * @throws IOException
	 * @throws @throws
	 *             Exception
	 */
	private static PublicKey loadPublicKey(String filename) throws IOException, GeneralSecurityException {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		byte[] keyBytes = new byte[dis.available()];
		dis.readFully(keyBytes);
		dis.close();
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(publicKeySpec);
	}

	/**
	 * Loads the private key from a specified path The private key is part of
	 * the PKI cryptography key pairs generated using RSA algorithm
	 * 
	 * @param filename
	 * @return PrivateKey
	 * @throws Exception
	 */
	private static PrivateKey loadPrivateKey(String filename) throws IOException, GeneralSecurityException {
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		byte[] keyBytes = new byte[(int) f.length()];
		dis.readFully(keyBytes);
		dis.close();

		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	/**
	 * Verifies data with a public key to match the signature with which the
	 * data is signed It initialize signing algorithm SHA1withRSA with the RSA
	 * public key The methods true or false based on whether signature match
	 * 
	 * @param data
	 * @param sig
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static boolean verify(byte[] data, byte[] sig) throws GeneralSecurityException {
		// Initialize the signing algorithm with our public key
		Signature rsaSignature = Signature.getInstance("SHA1withRSA");
		rsaSignature.initVerify(publicKey);
		// Update the signature algorithm with the data.
		rsaSignature.update(data);
		// Validate the signature
		return rsaSignature.verify(sig);
	}

	/**
	 * Signs a data with a private key It initialize signing algorithm
	 * SHA1withRSA with the RSA private key The method returns a signature bytes
	 * 
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public byte[] sign(byte[] data) throws GeneralSecurityException {
		if (privateKey == null) {
			LOGGER.error("Can't sign when the private key is not available.");
			throw new KeyGenerationException(ErrorCodesConstants.INTERNAL_SYSTEM_ERROR_CODE,
					ErrorCodesConstants.INTERNAL_SYSTEM_ERROR);
		}
		// Initialize the signing algorithm with our private key
		Signature rsaSignature = Signature.getInstance("SHA1withRSA");
		rsaSignature.initSign(privateKey);
		rsaSignature.update(data);
		// Generate the signature.
		return rsaSignature.sign();
	}

}
