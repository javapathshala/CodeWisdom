package com.jp.common.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class VisibleMessages {
	private ResourceBundle resourceBundle = null;

	public VisibleMessages() {
		resourceBundle = ResourceBundle.getBundle("messages");
	}

	public String retrieveFor(String key) {
		return retrieveFor(key, new Object[0]);
	}

	public String retrieveFor(String key, Object[] arguments) {
		if (key == null) {
			throw new IllegalArgumentException("Key is null");
		}
		try {
			String message = resourceBundle.getString(key);
			if (null == message || "".equals(message)) {
				message = "Message Not Avaialble";
			}
			return message;
		} catch (MissingResourceException e) {
			throw new IllegalArgumentException("No message defined for key " + key);
		}
	}
}
