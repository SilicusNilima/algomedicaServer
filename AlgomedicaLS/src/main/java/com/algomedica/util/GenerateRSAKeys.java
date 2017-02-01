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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;

;

public class GenerateRSAKeys {

	public GenerateRSAKeys() throws NoSuchAlgorithmException, NoSuchProviderException, IOException {
		// TODO Auto-generated constructor stub
		
	}
	/*
	 * public KeyPair LoadKeyPair(String path, String algorithm) throws
	 * IOException, NoSuchAlgorithmException, InvalidKeySpecException,
	 * NoSuchProviderException { // Read Public Key.
	 * 
	 * File filePublicKey = new File(path + "public.key"); byte[]
	 * encodedPublicKey = new byte[] {}; try (FileInputStream fis = new
	 * FileInputStream(filePublicKey)) { StringBuffer sb = new StringBuffer();
	 * char current; while (fis.available() > 0) { current = (char) fis.read();
	 * sb.append(current); } System.out.println(sb.toString()); encodedPublicKey
	 * = Base64.decode(sb.toString());
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * 
	 * FileInputStream fis = new FileInputStream(path + "public.key"); byte[]
	 * encodedPublicKey = new byte[(int) filePublicKey.length()];
	 * fis.read(encodedPublicKey); fis.close();
	 * 
	 * 
	 * // Read Private Key. File filePrivateKey = new File(path +
	 * "private.key"); byte[] encodedPrivateKey = new byte[] {}; try
	 * (FileInputStream fis = new FileInputStream(filePrivateKey)) {
	 * StringBuffer sb = new StringBuffer(); char current; while
	 * (fis.available() > 0) { current = (char) fis.read(); sb.append(current);
	 * } System.out.println(sb.toString()); encodedPrivateKey =
	 * Base64.decode(sb.toString());
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * 
	 * fis = new FileInputStream(path + "private.key"); byte[] encodedPrivateKey
	 * = new byte[(int) filePrivateKey.length()]; fis.read(encodedPrivateKey);
	 * fis.close();
	 * 
	 * 
	 * // Generate KeyPair. KeyFactory keyFactory =
	 * KeyFactory.getInstance(algorithm, "BC"); X509EncodedKeySpec publicKeySpec
	 * = new X509EncodedKeySpec(encodedPublicKey); PublicKey publicKey =
	 * keyFactory.generatePublic(publicKeySpec);
	 * 
	 * PKCS8EncodedKeySpec privateKeySpec = new
	 * PKCS8EncodedKeySpec(encodedPrivateKey); PrivateKey privateKey =
	 * keyFactory.generatePrivate(privateKeySpec);
	 * 
	 * return new KeyPair(publicKey, privateKey); }
	 */

	/**
	 * Use this method to Convert From String to RSA Encrypt Data
	 * 
	 * @param datatoEncrypt
	 * @throws Exception
	 * @throws IOException
	 */
	/*
	 * public String encryptData(String datatoEncrypt, String publicKeyString)
	 * throws Exception {
	 * 
	 *//**
		 * Hold object of public Key
		 */

	/*
	 * KeyPair publicKey = LoadKeyPair("", "RSA");
	 * 
	 * if (datatoEncrypt == null) { //
	 * LOGGER.debug("\n----------------ENCRYPTION STRING IS //
	 * NULL------------"); return datatoEncrypt; } // LOGGER.debug(
	 * "\n----------------ENCRYPTION STARTED------------"); byte[] dataToEncrypt
	 * = datatoEncrypt.getBytes(); byte[] encryptedData = null; String
	 * encodedbinary = null; try { // PublicKey pubKey =
	 * readPublicKeyFromFile(PUBLIC_KEY_FILE); Cipher cipher =
	 * Cipher.getInstance("RSA"); cipher.init(Cipher.ENCRYPT_MODE,
	 * publicKey.getPublic()); encryptedData = cipher.doFinal(dataToEncrypt);
	 * encodedbinary = DatatypeConverter.printBase64Binary(encryptedData); }
	 * catch (Exception e) { // LOGGER.error(
	 * "\n----------------ENCRYPTION ERROR------------",e); }
	 * 
	 * // LOGGER.debug("\n----------------ENCRYPTION COMPLETED------------");
	 * return encodedbinary == null ? null : encodedbinary; }
	 * 
	 *//**
		 * Encrypt Data
		 * 
		 * @param encrypteddata
		 * @throws IOException
		 * @throws NoSuchProviderException
		 * @throws NoSuchAlgorithmException
		 *//*
		 * public String decryptData(String encryptedData) {
		 * 
		 * if (encryptedData == null) { //LOGGER.debug(
		 * "\n----------------DECRYPTION STRING IS NULL------------"); return
		 * encryptedData; } //LOGGER.debug(
		 * "\n----------------DECRYPTION STARTED------------"); byte[]
		 * descryptedData = null;
		 * 
		 * try { KeyPair publicKey = LoadKeyPair("", "RSA"); byte[]
		 * encryptedTextBytes = DatatypeConverter.parseBase64Binary(new
		 * String(encryptedData)); Cipher cipher = Cipher.getInstance("RSA");
		 * cipher.init(Cipher.DECRYPT_MODE, publicKey.getPrivate());
		 * descryptedData = cipher.doFinal(encryptedTextBytes); } catch
		 * (Exception e) { //LOGGER.error(
		 * "\n----------------DECRYPTION ERROR------------",e);
		 * 
		 * } //LOGGER.debug("\n----------------DECRYPTION COMPLETED------------"
		 * ); return descryptedData == null ? null : new String(descryptedData);
		 * }
		 * 
		 * public static void main(String[] args) {
		 * 
		 * String publicKeyFilename = null; String privateKeyFilename = null;
		 * 
		 * GenerateRSAKeys generateRSAKeys = new GenerateRSAKeys();
		 * 
		 * 
		 * if (args.length < 2) { System.err.println("Usage: java "+
		 * generateRSAKeys.getClass().getName()+
		 * " Public_Key_Filename Private_Key_Filename"); System.exit(1); }
		 * 
		 * 
		 * publicKeyFilename = "public.key"; privateKeyFilename = "private.key";
		 * // generateRSAKeys.generate(publicKeyFilename, privateKeyFilename);
		 * 
		 * }
		 */

	public void generate() throws IOException, NoSuchAlgorithmException, NoSuchProviderException {

		//Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		// Create the public and private keys
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");

		SecureRandom random = createFixedRandom();
		// generator.initialize(1024);
		generator.initialize(1024, random);

		KeyPair pair = generator.generateKeyPair();
		Key pubKey = pair.getPublic();
		Key privKey = pair.getPrivate();
		String publicKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
		System.out.println("publicKey : " + publicKey);

		String privateKey = Base64.getEncoder().encodeToString(privKey.getEncoded());
		System.out.println("privateKey : " + privateKey);

		BufferedWriter out = new BufferedWriter(new FileWriter("public.key"));
		out.write(publicKey);
		out.close();

		out = new BufferedWriter(new FileWriter("private.key"));
		out.write(privateKey);
		out.close();
		// System.out.println("EncryptedData/////////////////////" +
		// encryptData("Hello bhai how r u", "public.key"));
		// System.out.println("Decryption/////////////////////" +
		// decryptData(encryptData("Hello bhai how r u", "public.key")));

	}

	public static SecureRandom createFixedRandom() {
		return new FixedRand();
	}

	 static class FixedRand extends SecureRandom {
		private static final long serialVersionUID = 1L;
		MessageDigest sha;
		byte[] state;

		FixedRand() {
			try {
				this.sha = MessageDigest.getInstance("SHA-1");
				this.state = sha.digest();
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("can't find SHA-1!");
			}
		}

		public void nextBytes(byte[] bytes) {

			int off = 0;

			sha.update(state);

			while (off < bytes.length) {
				state = sha.digest();

				if (bytes.length - off > state.length) {
					System.arraycopy(state, 0, bytes, off, state.length);
				} else {
					System.arraycopy(state, 0, bytes, off, bytes.length - off);
				}

				off += state.length;

				sha.update(state);
			}
		}
	}
}