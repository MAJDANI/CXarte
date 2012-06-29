package com.novedia.talentmap.web.util;

import java.util.Map;
import java.util.Vector;

import com.vaadin.ui.Window;

public abstract class CUtils {
	
	/**
	 * 
	 * @class CUtils.java
	 * @param fieldOrder
	 * @param order
	 */
	public static void setOrderForm(Vector<Object> fieldOrder, Object[] order) {

		for (Object field : order) {
			fieldOrder.add(field);
		}
	}
	
	
	/**
	 * MANAGE ERROR MESSAGE
	 */
	
	/**
	 * 
	 * @class CUtils.java
	 * @param mapNotification
	 */
	public static void showMessage(Map<String, Object> mapNotification, Window mainWindow){
		Message msg = new Message(mapNotification, mainWindow);
		msg.show();
	}
	
	/**
	 * 
	 * @class CUtils.java
	 * @param messageError
	 */
	public static void showMessage(String messageError, Window mainWindow){
		Message msg = new Message(messageError, mainWindow);
		msg.show();
	}
	
	/**
	 * 
	 * @class CUtils.java
	 * @param messageError
	 * @param typeError
	 */
	public static void showMessage(String messageError, int typeError, Window mainWindow){
		Message msg = new Message(messageError, typeError, mainWindow);
		msg.show();
	}
	
}
