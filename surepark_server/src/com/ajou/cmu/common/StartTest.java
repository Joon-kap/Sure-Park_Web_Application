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
	//������ setter�� �����Ͽ� bean xml �κ��� ���� �Ѱܹ��� �� �ִ�.
	String var1;
	private static boolean isStarted = false;

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	//������ �ý��� �⵿�� ����
	public void onApplicationEvent(ApplicationEvent applicationevent) {
		System.out.println("### Test.onApplicationEvent() > var1 : "+ var1 +"###");
		//rsaGenerator();
		if(!SensorStatus.getInitState())
			SensorStatus.init(4,1,1);
		
		if(!isStarted){
			Thread socket = new Thread(new ConnArduino());
			socket.start();
			
			isStarted = true;
			Encryption ep = new Encryption();
			
		}

		//byte[] input = "jkpark".getBytes();
		//byte[] en = ep.encoding(input);
		//ep.decoding(en);
		
		
		
	}
	
}
