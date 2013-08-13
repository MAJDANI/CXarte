package com.novedia.talentmap.web.utils;

import java.util.Map;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class Message {

    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;

    private String messageError;
    private int typeError;

    public Message(Map<String, Object> mapNotification) {

	if (mapNotification.get("typeError") != null) {

	    this.typeError = (Integer) mapNotification.get("typeError");
	}

	if (mapNotification.get("messageError") != null) {

	    this.messageError = (String) mapNotification.get("messageError");
	}
    }

    /**
     * Build the class Message.java
     * 
     * @param message
     */
    public Message(String messageError) {
	super();

	this.messageError = messageError;
	this.typeError = Message.INFO;
    }

    /**
     * Build the class Message.java
     * 
     * @param message
     * @param typeError
     */
    public Message(String messageError, int typeError) {
	super();

	this.messageError = messageError;
	this.typeError = typeError;
    }

    /**
     * Get the message value
     * 
     * @return the message
     */
    public String getMessageError() {
	return messageError;
    }

    /**
     * Set the message value
     * 
     * @param message
     *            the message to set
     */
    public void setMessageError(String messageError) {
	this.messageError = messageError;
    }

    /**
     * Get the typeError value
     * 
     * @return the typeError
     */
    public int getTypeError() {
	return typeError;
    }

    /**
     * Set the typeError value
     * 
     * @param typeError
     *            the typeError to set
     */
    public void setTypeError(int typeError) {
	this.typeError = typeError;
    }

    /**
     * Show the error message
     * 
     * @class Message.java
     */
    public void show() {

	switch (this.typeError) {
	case 1:

		Notification.show(this.messageError,Type.TRAY_NOTIFICATION);

	    break;

	case 2:

	    Notification.show(this.messageError,Type.WARNING_MESSAGE);
	    
	    break;

	case 3:

		Notification.show(this.messageError,Type.ERROR_MESSAGE);

	    break;

	default:

		Notification.show(this.messageError,Type.TRAY_NOTIFICATION);

	    break;
	}
    }

}
