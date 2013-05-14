package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmNotificationContent extends VerticalLayout implements
		ClickListener {

	private CmListNotification cmListNotification;

	private Authentication authentication;

	/***
	 * Vaadin Components
	 */
	private Panel listPanel;

	/**
	 * Default constructor
	 */
	public CmNotificationContent() {
		super();
	}

	/**
	 * Build the view of cm's notification
	 * 
	 * @return
	 */
	public CmNotificationContent buildViewNotificationContent() {
		removeAllComponents();
		cmListNotification.setColleagueId(getAuthentication().getColleagueId());
		cmListNotification = cmListNotification.buildAllCmNotification();
		buildMain();
		return this;
	}

	/**
	 * The main builder
	 * 
	 * @class CmNotificationContent.java
	 */
	public void buildMain() {
		setMargin(true);
		setSpacing(true);
		buildListPanelNotification();
	}

	public void buildListPanelNotification() {
		if (cmListNotification.size() > 0) {
			listPanel.removeAllComponents();
			this.listPanel.addComponent(this.cmListNotification);

			addComponent(this.listPanel);
			listPanel.setVisible(true);
		} else {
			listPanel.setVisible(false);
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}

	public CmListNotification getCmListNotification() {
		return cmListNotification;
	}

	public void setCmListNotification(CmListNotification cmListNotification) {
		this.cmListNotification = cmListNotification;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public Panel getListPanel() {
		return listPanel;
	}

	public void setListPanel(Panel listPanel) {
		this.listPanel = listPanel;
	}

}
