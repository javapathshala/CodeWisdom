package com.application.spring.annotations;


import java.lang.annotation.Annotation;

import org.apache.regexp.RE;
import org.springmodules.validation.bean.conf.loader.annotation.handler.AbstractPropertyValidationAnnotationHandler;
import org.springmodules.validation.bean.rule.AbstractValidationRule;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.string.AbstractStringCondition;


public class CardNumberHandler extends AbstractPropertyValidationAnnotationHandler {
    public final static String DEFAULT_ERROR_CODE = "cardNumber";
    private final static String REGEXP = "^([0-9]{13}|[0-9]{16}|[0-9]{18}|[0-9]{19})$";

	public CardNumberHandler()
	{
		super(new Class[] {CardNumber.class});
	}
	
	@Override
	protected AbstractValidationRule createValidationRule(Annotation annotation, Class calls, String propertyName) {
		return new CardNumberRule();
	}


	class CardNumberRule extends AbstractValidationRule {
	    /**
	     * Constructs a new rule.
	     */
	    public CardNumberRule() {
	        super(DEFAULT_ERROR_CODE);
	    }

	    /**
	     * Returns the condition of this validation rule.
	     *
	     * @see org.springmodules.validation.bean.rule.AbstractValidationRule#getCondition()
	     */
	    public Condition getCondition() {
	        return new CardNumberCondition();
	    }
		
	}
	
	class CardNumberCondition extends AbstractStringCondition {

	    public CardNumberCondition() {
	        super();
	    }

		/* (non-Javadoc)
		 * @see org.springmodules.validation.util.condition.string.AbstractStringCondition#checkString(java.lang.String)
		 */
		@Override
		protected boolean checkString(String value)
		{
			if(validator.match(value) && lunCheck(value))
				return true;
			return false;
		}
		/**
	     * This function checks the account number check digit, and returns true or false.
	     * This code was converted from the link 'C' function lnkutils/LUNcheck.c
	     * by Neil McGrane 8/6/2000.
	     */
	    private boolean lunCheck(String accountNo)
	    {
	        int i, digit, total = 0;
	        int weight = 2;
	        char account[];

	        account = accountNo.toCharArray();

	        for (i = (account.length) - 2; i >= 0; i--)
	        {
	            digit = (account[i] - '0') * weight;
	            if (digit > 9)
	            {
	                digit = ((digit / 10) + (digit % 10));
	            }
	            total = total + digit;
	            weight = 3 - weight;
	        }

	        total = (10 - (total % 10)) % 10;

	        if ((account[account.length - 1] - '0') == total)
	        {
	            return (true);
	        }
	        else
	        {
	            return (false);
	        }
	    }
	    
	}
	static private RE validator;

    static
    {
        try
        {
            validator = new RE(REGEXP);
        }
        catch ( Exception e )
        {
            System.err.println( "DebitCardNumberConverter: impossible error but ..." );
        }
    }
	
	
}
