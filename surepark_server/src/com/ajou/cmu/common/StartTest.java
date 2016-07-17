package com.ajou.cmu.common;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class StartTest implements ApplicationListener{
	//������ setter�� �����Ͽ� bean xml �κ��� ���� �Ѱܹ��� �� �ִ�.
	String var1;

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	//������ �ý��� �⵿�� ����
	public void onApplicationEvent(ApplicationEvent applicationevent) {
		System.out.println("### Test.onApplicationEvent() > var1 : "+ var1 +"###");
		
		Thread socket = new Thread(new ConnArduino());
		socket.start();
	}
}
