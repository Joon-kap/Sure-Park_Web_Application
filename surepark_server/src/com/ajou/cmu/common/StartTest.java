package com.ajou.cmu.common;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

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
		
		if(!isStarted){
			Thread socket = new Thread(new ConnArduino());
			socket.start();
			isStarted = true;
		}
		
	
	}
}
