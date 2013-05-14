package com.novedia.talentmap.web.ui.collab;

import com.vaadin.ui.VerticalLayout;

/**
 * The view for Collab
 * 
 * @author e.moumbe
 * 
 */
public class MonitoringCollabView extends VerticalLayout {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Vaadin UI
	 */
	private MonitoringCollabContentLayout monitoringCollabContentLayout;

	/**
	 * Default constructor
	 */
	public MonitoringCollabView() {
		super();
	}

	/**
	 * Build the class MonitoringCollabView.java
	 * 
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
	 * 
	 * @class MonitoringCollabView.java
	 */
	public MonitoringCollabView mainBuild() {
		this.monitoringCollabContentLayout.mainBuild();
		addComponent(this.monitoringCollabContentLayout);
		return this;
	}

	/**
	 * Set the monitoringCollabContentLayout value
	 * 
	 * @param monitoringCollabContentLayout
	 *            the monitoringCollabContentLayout to set
	 */
	public void setMonitoringCollabContentLayout(
			MonitoringCollabContentLayout monitoringCollabContentLayout) {
		this.monitoringCollabContentLayout = monitoringCollabContentLayout;
	}

	public MonitoringCollabContentLayout getMonitoringCollabContentLayout() {
		return monitoringCollabContentLayout;
	}

}