package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmEAEContent extends VerticalLayout implements ClickListener {

    private Authentication authentication;
    private CmEAEContentSynthesis cmEAEContentSynthesis;

    /**
     * Default constructor
     */
    public CmEAEContent() {
	super();
    }

    public CmEAEContent buildViewSynthesisContent() {
	removeAllComponents();
	cmEAEContentSynthesis.setAuthentication(getAuthentication());
	cmEAEContentSynthesis = cmEAEContentSynthesis.buildViewSynthesis();
	this.addComponent(cmEAEContentSynthesis);
	setMargin(true);
	setSpacing(true);
	return this;
    }

    @Override
    public void buttonClick(ClickEvent event) {
	// TODO Auto-generated method stub
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * @return the cmEAEContentSynthesis
     */
    public CmEAEContentSynthesis getCmEAEContentSynthesis() {
        return cmEAEContentSynthesis;
    }

    /**
     * @param cmEAEContentSynthesis the cmEAEContentSynthesis to set
     */
    public void setCmEAEContentSynthesis(CmEAEContentSynthesis cmEAEContentSynthesis) {
        this.cmEAEContentSynthesis = cmEAEContentSynthesis;
    }

}
