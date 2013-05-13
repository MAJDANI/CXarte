package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.web.ui.profile.CollaboratorSkillContent;
import com.novedia.talentmap.web.ui.profile.HistoryMissionColab;
import com.novedia.talentmap.web.ui.profile.ObjectiveEa;
import com.novedia.talentmap.web.util.ICollabLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

public class MonitoringCollabContentLayout extends HorizontalLayout {

	/**
	 * Vadin UI
	 */
	private MonitoringCollabContent monitoringCollabContent;
	private MonitoringCollabNavigation monitoringCollabNavigation;	
	private HistoryMissionColab historyMissionColab ;
	private ObjectiveEa objectiveEa;
	
	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitPanel;

	/**
	 * Default constructor
	 */
	public MonitoringCollabContentLayout(){
		super();
	}

	
	
	/**
	 * Build the class MonitoringCollabContentLayout.java
	 * 
	 * @param monitoringCollabContent
	 * @param monitoringCollabNavigation
	 */
	public MonitoringCollabContentLayout(
		MonitoringCollabContent monitoringCollabContent,
		MonitoringCollabNavigation monitoringCollabNavigation,
		HistoryMissionColab historyMissionColab,
		ObjectiveEa objectiveEa,
		HorizontalSplitPanel hSplitPanel) {
		super();
		this.monitoringCollabContent = monitoringCollabContent;
		this.monitoringCollabNavigation = monitoringCollabNavigation;
		this.historyMissionColab =  historyMissionColab;
		this.objectiveEa = objectiveEa;
		this.hSplitPanel = hSplitPanel;
		
		buildObservators();
		
		mainBuild();
	}
	
	/**
	 * Manage view
	 */
	public void buildObservators(){
		
		this.monitoringCollabNavigation.addObservateur(new ICollabLayout() {
			
			@Override
			public void updateCollabLayout(Class<?> cl) {

				if(cl == MonitoringCollabContent.class){
					
					MonitoringCollabContentLayout.this.monitoringCollabContent.setVisible(true);
					MonitoringCollabContentLayout.this.historyMissionColab.setVisible(false);
					MonitoringCollabContentLayout.this.objectiveEa.setVisible(false);										
					MonitoringCollabContentLayout.this.hSplitPanel.setSecondComponent(MonitoringCollabContentLayout.this.monitoringCollabContent);
				}
				
				if(cl == HistoryMissionColab.class){
					
					MonitoringCollabContentLayout.this.historyMissionColab.setVisible(true);
					MonitoringCollabContentLayout.this.monitoringCollabContent.setVisible(false);
					MonitoringCollabContentLayout.this.objectiveEa.setVisible(false);						
					MonitoringCollabContentLayout.this.hSplitPanel.setSecondComponent(MonitoringCollabContentLayout.this.historyMissionColab);
									
					}
				if(cl == ObjectiveEa.class){
					
					MonitoringCollabContentLayout.this.objectiveEa.setVisible(true);
					MonitoringCollabContentLayout.this.monitoringCollabContent.setVisible(false);
					MonitoringCollabContentLayout.this.historyMissionColab.setVisible(false);						
					MonitoringCollabContentLayout.this.hSplitPanel.setSecondComponent(MonitoringCollabContentLayout.this.objectiveEa);				
				}				
			}
		}, ICollabLayout.class);
		
	}
		
	public void mainBuild(){
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight("600px");
		this.monitoringCollabNavigation.mainBuild();
		vLayout.addComponent(this.monitoringCollabNavigation);
		this.hSplitPanel.setFirstComponent(vLayout);
		
		this.monitoringCollabContent.mainBuild();
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

	public HistoryMissionColab getHistoryMissionColab() {
		return historyMissionColab;
	}

	public void setHistoryMissionColab(HistoryMissionColab historyMissionColab) {
		this.historyMissionColab = historyMissionColab;
	}

	public ObjectiveEa getObjectiveEa() {
		return objectiveEa;
	}

	public void setObjectiveEa(ObjectiveEa objectiveEa) {
		this.objectiveEa = objectiveEa;
	}

	public MonitoringCollabContent getMonitoringCollabContent() {
		return monitoringCollabContent;
	}

	public MonitoringCollabNavigation getMonitoringCollabNavigation() {
		return monitoringCollabNavigation;
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
	public void setHSplitPanel(HorizontalSplitPanel hSplitPanel) {
		this.hSplitPanel = hSplitPanel;
	}

}
