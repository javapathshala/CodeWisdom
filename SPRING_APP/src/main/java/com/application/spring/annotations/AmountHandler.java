package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class AmountHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "amount";

	private final static String REGEXP = "[ ]*(£[ ]*(\\+[ ]*)?|\\+[ ]*(£[ ]*)?)?([1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))|[0-9])(\\.[0-9]{0,2})?[ ]*";

	public AmountHandler() {
		super(new Class[] { Amount.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new AmountRule();
	}

	class AmountRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public AmountRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			return new AmountCondition();
		}

	}

	class AmountCondition extends RegExpStringCondition {

		public AmountCondition() {
			super(REGEXP);
		}
	}
}
