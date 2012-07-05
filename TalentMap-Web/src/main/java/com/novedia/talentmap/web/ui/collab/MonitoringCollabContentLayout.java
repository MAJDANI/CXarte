package com.novedia.talentmap.web.ui.collab;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public class MonitoringCollabContentLayout extends HorizontalLayout {

	/**
	 * Vadin UI
	 */
	private MonitoringCollabContent monitoringCollabContent;
	private MonitoringCollabNavigation monitoringCollabNavigation;

	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitPanel;

	/**
	 * Build the class MonitoringCollabContentLayout.java
	 * 
	 * @param monitoringCollabContent
	 * @param monitoringCollabNavigation
	 */
	public MonitoringCollabContentLayout(
			MonitoringCollabContent monitoringCollabContent,
			MonitoringCollabNavigation monitoringCollabNavigation,
			HorizontalSplitPanel hSplitPanel) {
		super();
		this.monitoringCollabContent = monitoringCollabContent;
		this.monitoringCollabNavigation = monitoringCollabNavigation;
		this.hSplitPanel = hSplitPanel;
		
		mainBuild();
	}
	
	public void mainBuild(){
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight(600);
		vLayout.addComponent(this.monitoringCollabNavigation);
		
		this.hSplitPanel.setFirstComponent(vLayout);
		this.hSplitPanel.setSecondComponent(this.monitoringCollabContent);
		
		this.hSplitPanel.setSplitPosition(20);
		addComponent(this.hSplitPanel);
		setSizeFull();
		setExpandRatio(this.hSplitPanel, 1);
	}

	/**
	 * Set the monitoringCollabContent value
	 * 
	 * @param monitoringCollabContent
	 *            the monitoringCollabContent to set
	 */
	public void setMonitoringCollabContent(
			MonitoringCollabContent monitoringCollabContent) {
		this.monitoringCollabContent = monitoringCollabContent;
	}

	/**
	 * Set the monitoringCollabNavigation value
	 * 
	 * @param monitoringCollabNavigation
	 *            the monitoringCollabNavigation to set
	 */
	public void setMonitoringCollabNavigation(
			MonitoringCollabNavigation monitoringCollabNavigation) {
		this.monitoringCollabNavigation = monitoringCollabNavigation;
	}
	
	/**
	 * Set the hSplitPanel value
	 * @param hSplitPanel the hSplitPanel to set
	 */
	public void sethSplitPanel(HorizontalSplitPanel hSplitPanel) {
		this.hSplitPanel = hSplitPanel;
	}

}
