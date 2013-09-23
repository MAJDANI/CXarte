<<<<<<< HEAD
package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.role.CmContentLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmView extends VerticalLayout {

    private CmContentLayout cmContentLayout;

    private Authentication authentication;

    /**
     * Default constructor
     */
    public CmView() {
	super();
    }

    /**
     * Build the view's cm
     * 
     * @return
     */
    public CmView buildCmView() {
	removeAllComponents();
	cmContentLayout.setAuthentication(getAuthentication());
	cmContentLayout = cmContentLayout.buildViewCmContentLayout();
	addComponent(cmContentLayout);
	return this;
    }

    /**
     * Get the cmContentLayout value
     * 
     * @return the cmContentLayout
     */
    public CmContentLayout getCmContentLayout() {
	return cmContentLayout;
    }

    /**
     * Set the cmContentLayout value
     * 
     * @param cmContentLayout
     *            the cmContentLayout to set
     */
    public void setCmContentLayout(CmContentLayout cmContentLayout) {
	this.cmContentLayout = cmContentLayout;
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

}
=======
package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.role.CmContentLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmView extends VerticalLayout {

    private CmContentLayout cmContentLayout;

    private Authentication authentication;

    /**
     * Default constructor
     */
    public CmView() {
	super();
    }

    /**
     * Build the view's cm
     * 
     * @return
     */
    public CmView buildCmView() {
	removeAllComponents();
	cmContentLayout.setAuthentication(getAuthentication());
	cmContentLayout = cmContentLayout.buildViewCmContentLayout();
	addComponent(cmContentLayout);
	return this;
    }

    /**
     * Get the cmContentLayout value
     * 
     * @return the cmContentLayout
     */
    public CmContentLayout getCmContentLayout() {
	return cmContentLayout;
    }

    /**
     * Set the cmContentLayout value
     * 
     * @param cmContentLayout
     *            the cmContentLayout to set
     */
    public void setCmContentLayout(CmContentLayout cmContentLayout) {
	this.cmContentLayout = cmContentLayout;
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

}
>>>>>>> branch 'master' of https://github.com/Jean-Max/NovTalentMap.git
