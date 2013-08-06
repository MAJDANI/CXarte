package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class ColleagueView extends VerticalLayout implements ClickListener {

	/**
	 * Vaadin components
	 */
	private GridLayout gridLayout;
	
	private Panel profilPanel;
	
	private Button profilButton;
	
	private Button eaeButton;
	
	private Panel eaePanel;
	
	private ProfilePopIn profilePopIn;
	
	private Button formationButton;
	
	private Panel formationPanel;
	
	/**
	 * Default constructor
	 */
	public ColleagueView(){
		super();
		addStyleName("centerPanel");
	
	}
	
	
	/**
	 * Build colleague's content view
	 * @return VerticalLayout
	 */
	public VerticalLayout builColleagueContent(){
		removeAllComponents();
		buildContent();
		addComponent(gridLayout);
		return this;
	}
	
	/**
	 * Build content
	 */
	private void buildContent(){
		gridLayout.removeAllComponents();
		gridLayout.setSpacing(true);
		gridLayout.setRows(2);
		gridLayout.setColumns(3);
		gridLayout.setId("gridLayout");
		
		profilPanel.removeAllComponents();
		profilButton.setCaption(Constants.PROFILE_LABEL);
		profilButton.addStyleName(Reindeer.BUTTON_LINK);
		profilButton.addClickListener(this);
		profilButton.setId(ComponentsId.PROFILE_BUTTON_ID);
		profilPanel.addComponent(profilButton);
		profilPanel.addStyleName("labelBtnDashboard profilPanel");
		
		eaePanel.removeAllComponents();
		eaeButton.setCaption(Constants.EAE_LABEL);
		eaeButton.addStyleName(Reindeer.BUTTON_LINK);
		eaeButton.setId(ComponentsId.EAE_BUTTON_ID);
		eaePanel.addComponent(eaeButton);
		eaePanel.addStyleName("labelBtnDashboard eaePanel");
		
		formationPanel.removeAllComponents();
		formationButton.setCaption(Constants.FORMATION_LABEL);
		formationButton.addStyleName(Reindeer.BUTTON_LINK);
		formationButton.setId(ComponentsId.FORMATION_BUTTON_ID);
		formationPanel.addComponent(formationButton);
		formationPanel.addStyleName("labelBtnDashboard formationPanel");
		
		gridLayout.addComponent(profilPanel);
		gridLayout.addComponent(eaePanel);
		gridLayout.addComponent(formationPanel);
		
		profilePopIn.addStyleName("popinStyle");
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(profilButton)){
			getUI().addWindow(profilePopIn.buildProfilePopIn());
		}
		
	}


	public GridLayout getGridLayout() {
		return gridLayout;
	}


	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}


	public Panel getProfilPanel() {
		return profilPanel;
	}


	public void setProfilPanel(Panel profilPanel) {
		this.profilPanel = profilPanel;
	}


	public Panel getEaePanel() {
		return eaePanel;
	}


	public void setEaePanel(Panel eaePanel) {
		this.eaePanel = eaePanel;
	}


	public Button getProfilButton() {
		return profilButton;
	}


	public void setProfilButton(Button profilButton) {
		this.profilButton = profilButton;
	}


	public Button getEaeButton() {
		return eaeButton;
	}


	public void setEaeButton(Button eaeButton) {
		this.eaeButton = eaeButton;
	}


	public Button getFormationButton() {
		return formationButton;
	}


	public void setFormationButton(Button formationButton) {
		this.formationButton = formationButton;
	}


	public Panel getFormationPanel() {
		return formationPanel;
	}


	public void setFormationPanel(Panel formationPanel) {
		this.formationPanel = formationPanel;
	}
	
	public ProfilePopIn getProfilePopIn() {
		return profilePopIn;
	}


	public void setProfilePopIn(ProfilePopIn profilePopIn) {
		this.profilePopIn = profilePopIn;
	}

}
