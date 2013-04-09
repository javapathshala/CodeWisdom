package com.jp.spring.aop.types;

import org.springframework.aop.ThrowsAdvice;

public class TracingThrowAdvice implements ThrowsAdvice{

	public void afterThrowing(Throwable throwable){
		System.out.println("Throw Advice");
		System.out.println("Exception is ::  "+throwable.getLocalizedMessage());
		
	}
}
