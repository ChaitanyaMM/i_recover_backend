package com.irecover.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordEncryptDecryptor {

	private static String algorithm = "AES";
	private static byte[] keyValue = new byte[] { '0', '2', '3', '4', '5', '6', '7', '8', '9', '1', '2', '3', '4', '5',
			'6', '7' };

	// Performs Encryption
	public static String encrypt(String plainText) throws Exception {
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = chiper.doFinal(plainText.getBytes());
		String encryptedValue = new String(Base64.encodeBase64(encVal));
		/* new BASE64Encoder().encode(encVal) */;
		return encryptedValue;
	}

	// Performs decryption
	public static String decrypt(String encryptedText) throws Exception {
		// generate key
		Key key = generateKey();
		Cipher chiper = Cipher.getInstance(algorithm);
		chiper.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedText);
		byte[] decValue = chiper.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, algorithm);
		return key;
	}

}
