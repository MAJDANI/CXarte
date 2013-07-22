package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmNotificationView extends VerticalLayout {

    private CmNotificationContentLayout cmNotificationContentLayout;
    private Authentication authentication;

    /**
     * Default constructor
     */
    public CmNotificationView() {
	super();
    }

    /**
     * Build the tabsheet that contains notification data
     * 
     * @return
     */
    public CmNotificationView buildCmNotificationView() {

	cmNotificationContentLayout.setAuthentication(getAuthentication());
	addComponent(cmNotificationContentLayout
		.buildViewCmNotificationContentLayout());
	return this;
    }

    /**
     * Get the authentication
     * 
     * @return the authentication
     */
    public Authentication getAuthentication() {
	return authentication;
    }

    /**
     * Set the authentication
     * 
     * @param authentication
     *            the authentication to set
     */
    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * Get the cmContentLayout value
     * 
     * @return the cmContentLayout
     */
    public CmNotificationContentLayout getCmNotificationContentLayout() {
	return cmNotificationContentLayout;
    }

    /**
     * Set the cmNotificationContentLayout value
     * 
     * @param cmNotificationContentLayout
     *            the cmNotificationContentLayout to set
     */
    public void setCmNotificationContentLayout(
	    CmNotificationContentLayout cmNotificationContentLayout) {
	this.cmNotificationContentLayout = cmNotificationContentLayout;
    }

}
