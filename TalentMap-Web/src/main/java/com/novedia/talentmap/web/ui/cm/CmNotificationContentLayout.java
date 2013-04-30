package com.novedia.talentmap.web.ui.cm;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.util.INotificationLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CmNotificationContentLayout extends HorizontalLayout {

	/**
	 * Vaadin UI
	 */
	private CmNotificationContent cmNotificationContent;
	private CmNotificationNavigation cmNotificationNavigation;
	private CmNotificationOption cmNotificationOption;
	
	
	private Authentication authentication;

	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitContent;

	/**
	 * default constructor
	 */
	public CmNotificationContentLayout(){
		super();
	}
	
	
	
	/**
	 * Build the profil Layout of user
	 * @return
	 */
	public CmNotificationContentLayout buildViewCmNotificationContentLayout(){
		removeAllComponents();
		hSplitContent = new HorizontalSplitPanel();
		cmNotificationContent.setAuthentication(getAuthentication());
		cmNotificationOption.setAuthentication(getAuthentication());
		buildObservators();
		mainBuild();
		return this;
	}
	
	
	
	public void buildObservators(){
		
		this.cmNotificationNavigation.addObservateur(new INotificationLayout() {
			
			@Override
			public void updateNotificationLayout(Class<?> cl) {
				if(cl == CmNotificationContent.class){
					cmNotificationContent = cmNotificationContent.buildViewNotificationContent();
					CmNotificationContentLayout.this.hSplitContent.setSecondComponent(cmNotificationContent);
				}
				else if(cl == CmNotificationOption.class){
					cmNotificationOption = cmNotificationOption.buildViewNotificationOptionContent();
					CmNotificationContentLayout.this.hSplitContent.setSecondComponent(cmNotificationOption);
				}
			}
		}, INotificationLayout.class);
		
		
	
	}





	/**
	 * The main builder
	 * 
	 * @class ProfileLayout.java
	 */
	public void mainBuild() {
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(cmNotificationNavigation.buildCmNotificationNavigation());
		vLayout.setHeight("800px");

		this.hSplitContent.setFirstComponent(vLayout);
		cmNotificationContent = cmNotificationContent.buildViewNotificationContent();
		hSplitContent.setSecondComponent(cmNotificationContent);
	
		this.hSplitContent.setSplitPosition(20);
		this.hSplitContent.setLocked(true);

		addComponent(this.hSplitContent);
		setSizeFull();
		setExpandRatio(this.hSplitContent, 1);
	}
	
	
	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public CmNotificationNavigation getCmNotificationNavigation() {
		return cmNotificationNavigation;
	}

	public void setCmNotificationNavigation(
			CmNotificationNavigation cmNotificationNavigation) {
		this.cmNotificationNavigation = cmNotificationNavigation;
	}
	
	public CmNotificationContent getCmNotificationContent() {
		return cmNotificationContent;
	}

	public void setCmNotificationContent(CmNotificationContent cmNotificationContent) {
		this.cmNotificationContent = cmNotificationContent;
	}



	public CmNotificationOption getCmNotificationOption() {
		return cmNotificationOption;
	}



	public void setCmNotificationOption(CmNotificationOption cmNotificationOption) {
		this.cmNotificationOption = cmNotificationOption;
	}



	public HorizontalSplitPanel gethSplitContent() {
		return hSplitContent;
	}



	public void sethSplitContent(HorizontalSplitPanel hSplitContent) {
		this.hSplitContent = hSplitContent;
	}
}
