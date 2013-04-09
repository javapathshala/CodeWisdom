package com.application.spring.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springmodules.validation.bean.conf.loader.annotation.handler.ValidationRule;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
@ValidationRule
public @interface CardNumber {
	
	  String errorCode() default "cardNumber";
	  String message() default "cardNumber";
	  String args() default "";
	  String applyIf() default "";
	  String[] contexts() default {};
}
