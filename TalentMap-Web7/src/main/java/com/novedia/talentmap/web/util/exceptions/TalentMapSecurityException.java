package com.novedia.talentmap.web.util.exceptions;

/**
 * The security exception for talent map
 * 
 * @author e.moumbe
 * 
 */
public class TalentMapSecurityException extends Throwable {

    /**
     * UID
     */
    private static final long serialVersionUID = -6206753574360295601L;

    /**
     * The constructor
     * 
     * @param message
     */
    public TalentMapSecurityException(String message) {
	super(message);
    }
}
