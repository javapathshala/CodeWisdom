package com.application.spring.Interceptor.logging;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Interceptor for introducing logging info.
 * View the SpringWeb-servlet.xml for configuration 
 * @author dimit
 *
 */
public class MethodLoggingInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation method) throws Throwable {
		String classMethodIdentifier = method.getMethod().getDeclaringClass() + "." + method.getMethod().getName();
		System.out.println(classMethodIdentifier + " starts");
		Object result = method.proceed();
		System.out.println(classMethodIdentifier + " finished");
		return result;
	}

}
