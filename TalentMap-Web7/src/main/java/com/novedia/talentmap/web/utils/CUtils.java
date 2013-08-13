package com.novedia.talentmap.web.utils;

import java.text.SimpleDateFormat;
import java.util.Map;


public abstract class CUtils {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
		    "dd/MM/yyyy");
	
	/**
     * MANAGE ERROR MESSAGE
     */

    /**
     * 
     * @class CUtils.java
     * @param mapNotification
     */
    public static void showMessage(Map<String, Object> mapNotification) {
	Message msg = new Message(mapNotification);
	msg.show();
    }

}
