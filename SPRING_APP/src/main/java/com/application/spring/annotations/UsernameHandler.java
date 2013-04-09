package com.application.spring.annotations;

import java.lang.annotation.Annotation;

import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.RegExpStringCondition;

public class UsernameHandler extends AbstractPropertyValidationAnnotationHandler
{
    public final static String DEFAULT_ERROR_CODE = "Username";
    private final static String REGEXP = "([a-zA-Z0-9]*[0-9]+[a-zA-Z0-9]*[a-zA-Z]+[a-zA-Z0-9]*)|([a-zA-Z0-9]*[a-zA-Z]+[a-zA-Z0-9]*[0-9]+[a-zA-Z0-9]*)";

	public UsernameHandler()
	{
		super(new Class[] {Username.class});
	}
	
	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new UsernameRule();
	}


	class UsernameRule extends AbstractValidationRule {
	    /**
	     * Constructs a new rule.
	     */
	    public UsernameRule() {
	        super(DEFAULT_ERROR_CODE);
	    }

	    /**
	     * Returns the condition of this validation rule.
	     *
	     * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
	     */
	    public Condition getCondition() {
	        return new UsernameCondition();
	    }
		
	}
	
	class UsernameCondition extends RegExpStringCondition {

	    public UsernameCondition() {
	        super(REGEXP);
	    }
	}
}
