package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.EmailStringCondition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

/**
 * This class does client side validations at controller level for email format
 */
public class EmailHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "email";

	private final static String REGEXP = "[A-Z0-9._%+-]";

	public EmailHandler() {
		super(new Class[] { Email.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new AS2EmailRule();
	}

	class AS2EmailRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public AS2EmailRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			Condition condition = (new AS2EmailCondition()).and(new EmailStringCondition());
			return condition;
		}

	}

	class AS2EmailCondition extends RegExpStringCondition {

		public AS2EmailCondition() {
			super(REGEXP);
		}
	}

}
