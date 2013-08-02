package com.novedia.talentmap.web.ui.colleague.missions;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.utils.MissionFieldLabel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MissionColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {
	
	private IColleagueService collaboratorService;
	
	private Button addMissionButton;
	
    private Button editMissionButton;
    
    private Button deleteMissionButton;
    
    private Panel addMissionPanel;
    
    private Panel listMissionPanel;
    
    private ListMission listMission;
    
    private HorizontalLayout footerLayoutMissionButton;
    
    public MissionColleagueContent(){
    	super();
    	setSpacing(true);
    	setWidth("800px");
    	addStyleName("missionColleagueView");
    }

    
    
    
    public VerticalLayout buildViewMissionColleagueContent() {
    	removeAllComponents();
    	addMissionButton.setCaption(MissionFieldLabel.ADD_MISSION_LABEL);
    	addMissionButton.addClickListener(this);
    	
    	addComponent(addMissionButton);
    	buildListMissionPanel();
    	addComponent(listMissionPanel);
    	return this;
    }
    
    PagedTable missionTable;
	private void buildListMissionPanel(){
    	missionTable = listMission.fillAllColleagueMission();
    	missionTable.addValueChangeListener(this);
    	
    	missionTable.addStyleName("table");
    	missionTable.setImmediate(true);
    	missionTable.setSelectable(true);
    	listMissionPanel.addStyleName("listMissionPanel");
    	if(missionTable.size() > 0){
    		listMissionPanel.removeAllComponents();
    		listMissionPanel.addComponent(missionTable);
    		buildFooterMissionButton();
    		listMissionPanel.addComponent(footerLayoutMissionButton);
    		listMissionPanel.setVisible(true);
    	} else {
    		listMissionPanel.setVisible(false);
    	}
    	
    }
    
    
    private void buildFooterMissionButton(){
    	footerLayoutMissionButton.removeAllComponents();
    	footerLayoutMissionButton.addStyleName("footerLayoutMissionButton");
    	editMissionButton.setCaption(MissionFieldLabel.EDIT_MISSION_LABEL);
    	deleteMissionButton.setCaption(MissionFieldLabel.DELETE_MISSION_LABEL);
    	enableButton(false);
    	footerLayoutMissionButton.setSpacing(true);
    	footerLayoutMissionButton.addComponent(editMissionButton);
    	footerLayoutMissionButton.addComponent(deleteMissionButton);
    }
    
    @Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
    
    
	@Override
	public void valueChange(ValueChangeEvent event) {
    	Mission selectedMission = (Mission) missionTable.getValue();
    	if(selectedMission != null){
    		enableButton(true);
    	}else{
    		enableButton(false);
    	}
	}
    
    
    private void enableButton(boolean state){
    	editMissionButton.setEnabled(state);
		deleteMissionButton.setEnabled(state);
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

	public Button getEditMissionButton() {
		return editMissionButton;
	}

	public void setEditMissionButton(Button editMissionButton) {
		this.editMissionButton = editMissionButton;
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

	public Panel getAddMissionPanel() {
		return addMissionPanel;
	}

	public void setAddMissionPanel(Panel addMissionPanel) {
		this.addMissionPanel = addMissionPanel;
	}

	public HorizontalLayout getFooterLayoutMissionButton() {
		return footerLayoutMissionButton;
	}

	public void setFooterLayoutMissionButton(
			HorizontalLayout footerLayoutMissionButton) {
		this.footerLayoutMissionButton = footerLayoutMissionButton;
	}


}
