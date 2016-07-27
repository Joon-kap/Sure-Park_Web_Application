package com.ajou.cmu.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Encryption {
	private Key pubKey = null;
	private Key privKey = null;
	
	public Encryption() throws NoSuchAlgorithmException, NoSuchProviderException {
		SecureRandom random = new SecureRandom();
		//KeyPairGenerator generator = KeyPairGenerator.getInstance("DiffieHellman", "SunJCE"); Not an RSA key: DH
		//KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "SunRsaSign"); // OK
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "SunJSSE"); // OK
		  
		generator.initialize(2048, random); // 여기에서는 2048 bit 키를 생성하였음
		KeyPair pair = generator.generateKeyPair();
		pubKey = pair.getPublic();  // Kb(pub) 공개키
		privKey = pair.getPrivate();// Kb(pri) 개인키

		System.out.println("pubKeyHex:"+byteArrayToHex(pubKey.getEncoded()));
		System.out.println("privKeyHex:"+byteArrayToHex(privKey.getEncoded()));
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
	private String byteArrayToHex(byte[] ba) {
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

	public void doEncryption() {
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING", "SunJCE");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchProviderException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pubKeyStr = "30820122300d06092a864886f70d01010105000382010f003082010a02820101009852cfb349e4c63501ea5124b62bd96fac2e46a4cbc08745f8ef446e495433ecf55619fcff9e57f00a2ea19a6955598cfde5a9ebd0c04be9e4102c25dfa8e7538c1b7f4697ee8811fa15e76b60767d08e61d3dbf713f33c90fd0584e5b7808fcdfcce1b4fc8eec983dbdc9ee4a02b6564a8abdc1abddc0db7b320f697434ddbc5bc789636083c79e8f1a05c6c8d5c4c75a7fc2473de0ca84c41d8ed9416f5e8a8d87cab787950347b1392ecb87d7732b9f382308211f9a31f6b17611ab273e896e862b42aec7d1bea4a0b08a26d91244900aaeba172ed5dc48f985ad1a980148ce20b06d4f9fdcd2437a16ab6e93411aad1885bc3675fe563a654ee547e3433f0203010001";
		
		// Turn the encoded key into a real RSA public key.
		// Public keys are encoded in X.509.
		X509EncodedKeySpec ukeySpec = new X509EncodedKeySpec(hexToByteArray( pubKey.toString()));
		KeyFactory ukeyFactory = null;
		try {
			ukeyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PublicKey publicKey = null;
		try {
			publicKey = ukeyFactory.generatePublic(ukeySpec);
			System.out.println("pubKeyHex:"+byteArrayToHex(publicKey.getEncoded()));
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 공개키를 전달하여 암호화
		byte[] input = "암호화된 문자열 abcdefg hijklmn".getBytes();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] cipherText = null;
		try {
			cipherText = cipher.doFinal(input);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("inputText:"+new String(input));
		System.out.println("inputHex:("+ input.length +"):"+byteArrayToHex(input));
		System.out.println("cipherHex:("+ cipherText.length +"):"+byteArrayToHex(cipherText));
	}
	
	
	public static void main(String[] args){
		Encryption ec = null;
		try {
			ec = new Encryption();
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ec.doEncryption();
	}
}



