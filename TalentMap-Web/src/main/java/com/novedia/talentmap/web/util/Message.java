package com.novedia.talentmap.web.util;

import java.util.Map;

import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class Message{
	
	public static final int INFO = 1;
	public static final int WARNING = 2;
	public static final int ERROR = 3;
	
	private String messageError;
	private int typeError;
	private Window mainWindow;
	
	public Message(Map<String, Object> mapNotification, Window window){
		
		this.mainWindow = window;
		
		if(mapNotification.get("typeError") != null){
			
			this.typeError = (Integer) mapNotification.get("typeError");
		}
		
		if(mapNotification.get("messageError") != null){
			
			this.messageError = (String) mapNotification.get("messageError");
		}
	}
	
	/**
	 * Build the class Message.java 
	 * @param message
	 */
	public Message(String messageError, Window window) {
		super();
		
		this.mainWindow = window;
		this.messageError = messageError;
		this.typeError = Message.INFO;
	}
	/**
	 * Build the class Message.java 
	 * @param message
	 * @param typeError
	 */
	public Message(String messageError, int typeError, Window window) {
		super();
		
		this.mainWindow = window;
		this.messageError = messageError;
		this.typeError = typeError;
	}
	/**
	 * Get the message value
	 * @return the message
	 */
	public String getMessageError() {
		return messageError;
	}
	/**
	 * Set the message value
	 * @param message the message to set
	 */
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
	/**
	 * Get the typeError value
	 * @return the typeError
	 */
	public int getTypeError() {
		return typeError;
	}
	/**
	 * Set the typeError value
	 * @param typeError the typeError to set
	 */
	public void setTypeError(int typeError) {
		this.typeError = typeError;
	}
	
	/**
	 * Show the error message
	 * @class Message.java
	 */
	public void show(){
		
		switch (this.typeError) {
		case 1:
			
			this.mainWindow.showNotification(this.messageError, Notification.TYPE_TRAY_NOTIFICATION);
			
			break;
			
		case 2:
			
			this.mainWindow.showNotification(this.messageError, Notification.TYPE_WARNING_MESSAGE);
			
			break;
			
		case 3:
			
			this.mainWindow.showNotification(this.messageError, Notification.TYPE_ERROR_MESSAGE);
			
			break;
			
		default:
			
			this.mainWindow.showNotification(this.messageError, Notification.TYPE_TRAY_NOTIFICATION);
			
			break;
		}
	}
	
}
