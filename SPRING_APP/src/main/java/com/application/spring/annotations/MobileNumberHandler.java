package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class MobileNumberHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "Mobile";

	private final static String REGEXP = "^(07[0-9-]*)$";

	public MobileNumberHandler() {
		super(new Class[] { Mobile.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new MobileNumberRule();
	}

	class MobileNumberRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public MobileNumberRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			return new MobileNumberCondition();
		}

	}

	class MobileNumberCondition extends RegExpStringCondition {

		/**
		 * Constructs a new MobileNumberCondition.
		 */
		public MobileNumberCondition() {
			super(REGEXP);
		}
	}
}
