package com.ajou.cmu.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AOPClass {
	@Before("execution(* com.ajou.cmu.reservation.ReservationController.retrieveAvailableSpot(..))")
	public void log(JoinPoint joinPoint){
		System.out.println("Before AOP");
	}

}
