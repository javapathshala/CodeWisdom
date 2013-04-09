/**
* 
 */
package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class MonthHandler extends AbstractPropertyValidationAnnotationHandler
{
    public final static String DEFAULT_ERROR_CODE = "month";
    private final static String REGEXP = "[ ]*[0]*([1-9]|1[0-2])[ ]*";

	public MonthHandler()
	{
		super(new Class[] {Month.class});
	}
	
	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new MonthRule();
	}


	class MonthRule extends AbstractValidationRule {
	    /**
	     * Constructs a new rule.
	     */
	    public MonthRule() {
	        super(DEFAULT_ERROR_CODE);
	    }

	    /**
	     * Returns the condition of this validation rule.
	     *
	     * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
	     */
	    public Condition getCondition() {
	        return new MonthCondition();
	    }
		
	}
	
	class MonthCondition extends RegExpStringCondition {

	    public MonthCondition() {
	        super(REGEXP);
	    }
	}
}
