package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.web.ui.collab.MonitoringCollabNavigation;
import com.novedia.talentmap.web.util.ICollabLayout;
import com.vaadin.ui.HorizontalSplitPanel;

/**
 * 
 * @author v.dibi
 *
 */
public class CollabLayout implements ICollabLayout{
	
	//Chaque vue
	private CollaboratorSkillContent collaboratorSkillContent;
	private HistoryMissionColab historyMissionColab ;
	private ObjectiveEa objectiveEa;
	private MonitoringCollabNavigation mNavigation;
	
	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitPanel;
	
	public CollabLayout(CollaboratorSkillContent collaboratorSkillContent,
			HistoryMissionColab historyMissionColab, ObjectiveEa objectiveEa,
			MonitoringCollabNavigation mNavigation,HorizontalSplitPanel hSplitPanel) {
		super();
		this.collaboratorSkillContent = collaboratorSkillContent;
		this.historyMissionColab = historyMissionColab;
		this.objectiveEa = objectiveEa;
		this.mNavigation =  mNavigation;
		this.hSplitPanel =  hSplitPanel;
		
		buildObservators();
	}

	/**
	 * Manage view
	 */
	public void buildObservators(){
		
		this.mNavigation.addObservateur(new ICollabLayout() {
			
			@Override
			public void updateCollabLayout(Class<?> cl) {

				if(cl == CollaboratorSkillContent.class){
					
					CollabLayout.this.collaboratorSkillContent.setVisible(true);
					CollabLayout.this.historyMissionColab.setVisible(false);
					CollabLayout.this.objectiveEa.setVisible(false);
					
					
					CollabLayout.this.hSplitPanel.setSecondComponent(CollabLayout.this.collaboratorSkillContent);
				}
				
				if(cl == HistoryMissionColab.class){
					
					CollabLayout.this.historyMissionColab.setVisible(true);
					CollabLayout.this.collaboratorSkillContent.setVisible(false);
					CollabLayout.this.objectiveEa.setVisible(false);
						
					CollabLayout.this.hSplitPanel.setSecondComponent(CollabLayout.this.historyMissionColab);
				
					
					}
				if(cl == ObjectiveEa.class){
					CollabLayout.this.objectiveEa.setVisible(true);
					CollabLayout.this.historyMissionColab.setVisible(false);
					CollabLayout.this.collaboratorSkillContent.setVisible(false);
						
					CollabLayout.this.hSplitPanel.setSecondComponent(CollabLayout.this.objectiveEa);
				
				}
				
			}
		}, ICollabLayout.class);
		
	}
	
	
	

	/**
	 * @return the mNavigation
	 */
	public MonitoringCollabNavigation getmNavigation() {
		return mNavigation;
	}

	/**
	 * @param mNavigation the mNavigation to set
	 */
	public void setmNavigation(MonitoringCollabNavigation mNavigation) {
		this.mNavigation = mNavigation;
	}

	/**
	 * @return the hSplitPanel
	 */
	public HorizontalSplitPanel gethSplitPanel() {
		return hSplitPanel;
	}

	/**
	 * @param hSplitPanel the hSplitPanel to set
	 */
	public void sethSplitPanel(HorizontalSplitPanel hSplitPanel) {
		this.hSplitPanel = hSplitPanel;
	}

	/**
	 * @return the collaboratorSkillContent
	 */
	public CollaboratorSkillContent getCollaboratorSkillContent() {
		return collaboratorSkillContent;
	}



	/**
	 * @param collaboratorSkillContent the collaboratorSkillContent to set
	 */
	public void setCollaboratorSkillContent(
			CollaboratorSkillContent collaboratorSkillContent) {
		this.collaboratorSkillContent = collaboratorSkillContent;
	}



	/**
	 * @return the historyMissionColab
	 */
	public HistoryMissionColab getHistoryMissionColab() {
		return historyMissionColab;
	}



	/**
	 * @param historyMissionColab the historyMissionColab to set
	 */
	public void setHistoryMissionColab(HistoryMissionColab historyMissionColab) {
		this.historyMissionColab = historyMissionColab;
	}



	/**
	 * @return the objectiveEa
	 */
	public ObjectiveEa getObjectiveEa() {
		return objectiveEa;
	}



	/**
	 * @param objectiveEa the objectiveEa to set
	 */
	public void setObjectiveEa(ObjectiveEa objectiveEa) {
		this.objectiveEa = objectiveEa;
	}



	@Override
	public void updateCollabLayout(Class<?> cl) {

		
	}

}
