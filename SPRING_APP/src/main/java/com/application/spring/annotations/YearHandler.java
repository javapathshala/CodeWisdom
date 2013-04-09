/**
 * 
 */
package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;



public class YearHandler extends AbstractPropertyValidationAnnotationHandler {

	public final static String DEFAULT_ERROR_CODE = "year";

	private final static String REGEXP = "((19|20){1}[0-9]{2}){1}";

	public YearHandler() {
		super(new Class[] { Year.class });
	}

	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new YearRule();
	}

	class YearRule extends AbstractValidationRule {

		/**
		 * Constructs a new rule.
		 */
		public YearRule() {
			super(DEFAULT_ERROR_CODE);
		}

		/**
		 * Returns the condition of this validation rule.
		 * 
		 * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
		 */
		public Condition getCondition() {
			return new YearCondition();
		}

	}

	class YearCondition extends RegExpStringCondition {

		public YearCondition() {
			super(REGEXP);
		}
	}
}
