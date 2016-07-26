package com.ajou.cmu.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.ajou.cmu.sensor.SensorStatus;

public class StartTest implements ApplicationListener{
	//변수와 setter를 선언하여 bean xml 로부터 값을 넘겨받을 수 있다.
	String var1;
	private static boolean isStarted = false;

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	//스프링 시스템 기동시 수행
	public void onApplicationEvent(ApplicationEvent applicationevent) {
		System.out.println("### Test.onApplicationEvent() > var1 : "+ var1 +"###");
		//rsaGenerator();
		if(!SensorStatus.getInitState())
			SensorStatus.init(4,1,1);
		
		if(!isStarted){
			Thread socket = new Thread(new ConnArduino());
			socket.start();
			
			isStarted = true;
		}
	}
	
	public void rsaGenerator(){
		 KeyPairGenerator clsKeyPairGenerator = null;
		try {
			clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 clsKeyPairGenerator.initialize(2048);
		   
		 KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();
		 Key clsPublicKey = clsKeyPair.getPublic();
		 Key clsPrivateKey = clsKeyPair.getPrivate();
		 
		 KeyFactory fact = null;
		 RSAPublicKeySpec clsPublicKeySpec = null;
		 RSAPrivateKeySpec clsPrivateKeySpec = null;
		try {
			fact = KeyFactory.getInstance("RSA");
			clsPublicKeySpec = fact.getKeySpec( clsPublicKey, RSAPublicKeySpec.class);
			clsPrivateKeySpec = fact.getKeySpec( clsPrivateKey, RSAPrivateKeySpec.class);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 System.out.println( "public key modulus(" + clsPublicKeySpec.getModulus( ) + ") exponent(" + clsPublicKeySpec.getPublicExponent( ) + ")" );
		 System.out.println( "private key modulus(" + clsPrivateKeySpec.getModulus( ) + ") exponent(" + clsPrivateKeySpec.getPrivateExponent( ) + ")" );
		
		 
		 // 암호화 한다.
		 String strPinNumber = "1234567890";
		   
		 Cipher clsCipher;
		try {
			clsCipher = Cipher.getInstance("RSA");
			clsCipher.init( Cipher.ENCRYPT_MODE, clsPublicKey );
			byte[] arrCipherData = clsCipher.doFinal( strPinNumber.getBytes( ) );
			String strCipher = new String( arrCipherData );
			System.out.println( "cipher(" + strCipher + ")" );
			
			 // 복호화 한다.
			 clsCipher.init( Cipher.DECRYPT_MODE, clsPrivateKey );
			 byte[] arrData = clsCipher.doFinal( arrCipherData );
			 
			 String strResult = new String( arrData );
			 System.out.println( "result(" + strResult + ")" );
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
