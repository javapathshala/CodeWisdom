package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class CardSecurityNumberHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "cvv";

	private final static String REGEXP = "[A-Z0-9._%+-]";

	public CardSecurityNumberHandler() {
		super(new Class[] { CardSecurityNumber.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new CardSecurityNumberRule();
	}

	class CardSecurityNumberRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public CardSecurityNumberRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			return new CardSecurityNumberCondition();
		}

	}

	class CardSecurityNumberCondition extends RegExpStringCondition {

		public CardSecurityNumberCondition() {
			super(REGEXP);
		}
	}

}
