package com.jp.spring.aop.types;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TracingAroundAdvice implements MethodInterceptor{

	public Object invoke(MethodInvocation arg0) throws Throwable {
		System.out.println("Around Advice Called::");
		String str=(String)arg0.getArguments()[0];
		Object str1=arg0.proceed();
		return str1;
	}

}
