package com.novedia.talentmap.web.ui.colleague.missions;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.utils.MissionFieldLabel;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MissionColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {
	
	private IColleagueService colleagueService;
	
	private Button addMissionButton;
	
    private Button editMissionButton;
    
    private Button deleteMissionButton;
    
    private Panel addMissionPanel;
    
    private Panel listMissionPanel;
    
    private ListMission listMission;
    
    private HorizontalLayout footerLayoutMissionButton;
    
    private Window windowConfirm;
    
    private Label confirmDeleteLabel;
    
    private HorizontalLayout confirmButtonContainer;
    
    private Button yesButton;
    
    private Button noButton;
    
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
    	addMissionPanel.removeAllComponents();
    	addMissionPanel.addComponent(new Label("add mission form"));
    	addComponent(addMissionButton);
    	addComponent(addMissionPanel);
    	buildListMissionPanel();
    	addComponent(listMissionPanel);
    	buildAddMissionPanel();
    	return this;
    }
    
    
    private void buildAddMissionPanel(){
    	if(listMission.size() > 0){
    		addMissionPanel.setVisible(false);
    		addMissionButton.setEnabled(true);
    	}else {
    		addMissionPanel.setVisible(true);
    		addMissionButton.setEnabled(false);
		}
    }
    
	private void buildListMissionPanel(){
		listMission.fillAllColleagueMission();
    	listMission.addValueChangeListener(this);
    	listMission.addStyleName("table");
    	listMissionPanel.addStyleName("listMissionPanel");
    	if(listMission.size() > 0){
    		listMissionPanel.removeAllComponents();
    		listMissionPanel.addComponent(listMission);
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
    	editMissionButton.addClickListener(this);
    	deleteMissionButton.setCaption(MissionFieldLabel.DELETE_MISSION_LABEL);
    	deleteMissionButton.addClickListener(this);
    	enableButton(false);
    	footerLayoutMissionButton.setSpacing(true);
    	footerLayoutMissionButton.addComponent(editMissionButton);
    	footerLayoutMissionButton.addComponent(deleteMissionButton);
    }
    
    @Override
	public void buttonClick(ClickEvent event) {

    	if(event.getButton().equals(deleteMissionButton)){
    		buildConfirmWindow();
    		getUI().addWindow(windowConfirm);
    	}else if (event.getButton().equals(editMissionButton)) {
    		addMissionButton.setEnabled(false);
    		enableButton(false);
    		Mission selectedMission = (Mission) listMission.getValue();
    		
		} else if (event.getButton().equals(addMissionButton)) {
			addMissionPanel.setVisible(true);
    		addMissionButton.setEnabled(false);
    		enableButton(false);
		} else if (event.getButton().equals(yesButton)) {
			windowConfirm.close();
			Mission selectedMission = (Mission) listMission.getValue();
			int result = colleagueService.deleteMission(selectedMission);
			if(result != 0){
				buildViewMissionColleagueContent();
			} else {
				Notification.show(MissionFieldLabel.ERROR_DELETE_MISSION_LABEL, Type.ERROR_MESSAGE);
			}
		}else if (event.getButton().equals(noButton)) {
			windowConfirm.close();
		}
	}
    
    
    private void buildConfirmWindow(){
    	windowConfirm.removeAllComponents();
    	windowConfirm.setCaption(MissionFieldLabel.WINDOW_CONFIRM_DELETE_TITLE);
    	windowConfirm.center();
    	windowConfirm.setModal(true);
    	windowConfirm.setReadOnly(true);
    	confirmDeleteLabel.setCaption(MissionFieldLabel.CONFIRM_DELETE_MESSAGE_MISSION);
    	confirmButtonContainer.setSpacing(true);
    	yesButton.setCaption(MissionFieldLabel.CONFIRM_DELETE_MISSION_LABEL);
    	yesButton.addClickListener(this);
    	noButton.setCaption(MissionFieldLabel.CANCEL_DELETE_MISSION_LABEL);
    	noButton.addClickListener(this);
    	confirmButtonContainer.addComponent(yesButton);
    	confirmButtonContainer.addComponent(noButton);
    	windowConfirm.addComponent(confirmDeleteLabel);
    	windowConfirm.addComponent(confirmButtonContainer);
    }
    
    
	@Override
	public void valueChange(ValueChangeEvent event) {
    	Mission selectedMission = (Mission) listMission.getValue();
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

	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
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

	public Window getWindowConfirm() {
		return windowConfirm;
	}

	public void setWindowConfirm(Window windowConfirm) {
		this.windowConfirm = windowConfirm;
	}

	public Label getConfirmDeleteLabel() {
		return confirmDeleteLabel;
	}

	public void setConfirmDeleteLabel(Label confirmDeleteLabel) {
		this.confirmDeleteLabel = confirmDeleteLabel;
	}

	public HorizontalLayout getConfirmButtonContainer() {
		return confirmButtonContainer;
	}

	public void setConfirmButtonContainer(HorizontalLayout confirmButtonContainer) {
		this.confirmButtonContainer = confirmButtonContainer;
	}

	public Button getYesButton() {
		return yesButton;
	}

	public void setYesButton(Button yesButton) {
		this.yesButton = yesButton;
	}

	public Button getNoButton() {
		return noButton;
	}

	public void setNoButton(Button noButton) {
		this.noButton = noButton;
	}

}
