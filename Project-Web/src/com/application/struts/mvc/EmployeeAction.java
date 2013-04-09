package com.application.struts.mvc;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeeAction extends ActionSupport {

    public static final String MESSAGE = "Struts 2 Hello World";

    public String execute() throws Exception {
        setMessage(MESSAGE);
        return SUCCESS;
    }

    private String message;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

	public String getCurrentTime(){
			return new Date().toString();
	}
}
