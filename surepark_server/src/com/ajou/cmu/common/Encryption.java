package com.ajou.cmu.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.util.encoders.Base64;

public class Encryption {
	public static Key pubKey = null;
	public static PrivateKey privKey = null;
	static Cipher cipher = null;
	
	public static String pubKeyStr = null;
	
	public String getPubKey(){
		return byteArrayToHex(pubKey.getEncoded());
	}
	
	/*
	public String getPrivKey(){
		return byteArrayToHex(privKey.getEncoded());
	}
	*/
	public Encryption(){
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		KeyPairGenerator generator = null;
		
		try {
			cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			generator = KeyPairGenerator.getInstance("RSA", "BC");
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SecureRandom random = new SecureRandom();
		
		generator.initialize(128, random); // 여기에서는 128 bit 키를 생성하였음
		KeyPair pair = generator.generateKeyPair();
	     
		pubKey = pair.getPublic();  // Kb(pub) 공개키
		privKey = pair.getPrivate();// Kb(pri) 개인키
		
		System.out.println("pubKeyHex:"+byteArrayToHex(pubKey.getEncoded()));
		System.out.println("pubKey:"+pubKey);
	     System.out.println("privKeyHex:"+byteArrayToHex(privKey.getEncoded()));
	     
	     //pubKeyStr = Base64.toBase64String(pubKey.getEncoded());
	     
	     pubKeyStr = byteArrayToHex(pubKey.getEncoded());
	}
	
	
	public static byte[] encoding(byte[] input){
		 // 공개키를 전달하여 암호화
	     try {
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal(input);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("cipher: ("+ cipherText.length +")"+ new String(cipherText));
	     
	     return cipherText;
	}
	
	public static void decoding(byte[] cipherText){
		 try {
			cipher.init(Cipher.DECRYPT_MODE, privKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 byte[] plainText = null;
		try {
			plainText = cipher.doFinal(cipherText);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     System.out.println("plain : " + new String(plainText));
	     System.out.println("plain : " + plainText.toString());
	}
	
	// hex string to byte[]
		private byte[] hexToByteArray(String hex) {
			if (hex == null || hex.length() == 0) {
				return null;
			}
			byte[] ba = new byte[hex.length() / 2];
			for (int i = 0; i < ba.length; i++) {
				ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
			}
			return ba;
		}
			  
		// byte[] to hex sting
		private static String byteArrayToHex(byte[] ba) {
			if (ba == null || ba.length == 0) {
				return null;
			}
			StringBuffer sb = new StringBuffer(ba.length * 2);
			String hexNumber;
			for (int x = 0; x < ba.length; x++) {
				hexNumber = "0" + Integer.toHexString(0xff & ba[x]);
				
				sb.append(hexNumber.substring(hexNumber.length() - 2));
			}
			return sb.toString();
		} 
}





