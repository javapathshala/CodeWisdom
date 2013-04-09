/*
 * Created on 24-Nov-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Mapping;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author dimit
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Events implements Serializable {
	
	private int id;	
	private String evnettitle;
	

	/**
	 * @return
	 */
	public String getEvnettitle() {
		return evnettitle;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setEvnettitle(String string) {
		evnettitle = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

}
