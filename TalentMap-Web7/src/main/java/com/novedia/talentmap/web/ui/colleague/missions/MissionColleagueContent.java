package com.novedia.talentmap.web.ui.colleague.missions;

import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.utils.MissionFieldLabel;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MissionColleagueContent extends VerticalLayout implements ClickListener {
	
	private IColleagueService collaboratorService;
	
	private Button addMissionButton;
	
    private Button modifyMissionButton;
    
    private Button deleteMissionButton;
    
    private Panel addMissionPanel;
    
    private Panel listMissionPanel;
    
    private ListMission listMission;
    
    public MissionColleagueContent(){
    	super();
    	setSpacing(true);
    	setWidth("800px");
    }

    
    
    
    public VerticalLayout buildViewMissionColleagueContent() {
    	removeAllComponents();
    	addMissionButton.setCaption(MissionFieldLabel.ADD_MISSION_LABEL);
    	addMissionButton.addClickListener(this);
    	
    	addComponent(addMissionButton);
    	addComponent(listMissionPanel);
    	buildListMissionPanel();
    	return this;
    }
    
    
    private void buildListMissionPanel(){
    	if(listMission.fillAllColleagueMission().size() > 0){
    		listMissionPanel.removeAllComponents();
    		listMissionPanel.addComponent(listMission.fillAllColleagueMission());
    		listMissionPanel.setVisible(true);
    	} else {
    		listMissionPanel.setVisible(false);
    	}
    	
    }
    
    
    @Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
    

	public IColleagueService getCollaboratorService() {
		return collaboratorService;
	}

	public void setCollaboratorService(IColleagueService collaboratorService) {
		this.collaboratorService = collaboratorService;
	}

	public Button getAddMissionButton() {
		return addMissionButton;
	}

	public void setAddMissionButton(Button addMissionButton) {
		this.addMissionButton = addMissionButton;
	}

	public Button getModifyMissionButton() {
		return modifyMissionButton;
	}

	public void setModifyMissionButton(Button modifyMissionButton) {
		this.modifyMissionButton = modifyMissionButton;
	}

	public Button getDeleteMissionButton() {
		return deleteMissionButton;
	}

	public void setDeleteMissionButton(Button deleteMissionButton) {
		this.deleteMissionButton = deleteMissionButton;
	}

	public Panel getListMissionPanel() {
		return listMissionPanel;
	}

	public void setListMissionPanel(Panel listMissionPanel) {
		this.listMissionPanel = listMissionPanel;
	}

	public ListMission getListMission() {
		return listMission;
	}

	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}




	
}
