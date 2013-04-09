package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class PasscodeHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "Passcode";

	private final static String REGEXP = "[0-9]*";

	public PasscodeHandler() {
		super(new Class[] { Passcode.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new PasscodeRule();
	}

	class PasscodeRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public PasscodeRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			return new PasscodeCondition();
		}

	}

	class PasscodeCondition extends RegExpStringCondition {

		/**
	     */
		public PasscodeCondition() {
			super(REGEXP);
		}
	}
}
