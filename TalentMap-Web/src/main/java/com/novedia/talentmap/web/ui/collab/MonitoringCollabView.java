package com.novedia.talentmap.web.ui.collab;

import com.vaadin.ui.VerticalLayout;

public class MonitoringCollabView extends VerticalLayout {
	
	/**
	 * Vaadin UI
	 */
	private MonitoringCollabContentLayout monitoringCollabContentLayout;
	
	/**
	 * Build the class MonitoringCollabView.java 
	 * @param monitoringCollabContentLayout
	 */
	public MonitoringCollabView(
			MonitoringCollabContentLayout monitoringCollabContentLayout) {
		super();
		this.monitoringCollabContentLayout = monitoringCollabContentLayout;
	
		mainBuild();
	}

	/**
	 * The main builder
	 * @class MonitoringCollabView.java
	 */
	public void mainBuild(){
		
		addComponent(this.monitoringCollabContentLayout);
	}

	/**
	 * Set the monitoringCollabContentLayout value
	 * @param monitoringCollabContentLayout the monitoringCollabContentLayout to set
	 */
	public void setMonitoringCollabContentLayout(
			MonitoringCollabContentLayout monitoringCollabContentLayout) {
		this.monitoringCollabContentLayout = monitoringCollabContentLayout;
	}
	
	
}
