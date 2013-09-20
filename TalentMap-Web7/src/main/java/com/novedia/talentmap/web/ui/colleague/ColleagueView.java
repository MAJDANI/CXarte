package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.search.SearchPopIn;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ColleagueView extends VerticalLayout implements ClickListener {

	/**
	 * Vaadin components
	 */
	private GridLayout gridLayout;
	
	private Button profilButton;
	
	private Button eaeButton;
	
	private ProfilePopIn profilePopIn;
	
	private Button formationButton;
	
	private PersonalEAEPopIn personalEAEPopIn;
	
	private SearchPopIn searchPopIn;
	
	private Panel searchCmPanel;
	
	private Button searchButtonCm;


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
	public VerticalLayout buildColleagueContent(){
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
		
		profilButton.addStyleName("labelBtnDashboard profilButton");
		profilButton.addClickListener(this);
		profilButton.setId(ComponentsId.PROFILE_BUTTON_ID);
		
		eaeButton.addStyleName("labelBtnDashboard eaeButton");
		eaeButton.addClickListener(this);
		eaeButton.setId(ComponentsId.EAE_BUTTON_ID);
		
		formationButton.addStyleName("labelBtnDashboard formationButton");
		formationButton.setId(ComponentsId.FORMATION_BUTTON_ID);
		
		gridLayout.addComponent(profilButton);
		gridLayout.addComponent(eaeButton);
		gridLayout.addComponent(formationButton);
		
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		if(authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())){
			searchButtonCm.addClickListener(this);
			searchButtonCm.addStyleName("labelBtnDashboard searchButton");
			gridLayout.addComponent(searchButtonCm);
		}
		
		
		profilePopIn.addStyleName("popinStyle");
		personalEAEPopIn.addStyleName("popinStyle");
		
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(profilButton)){
			getUI().addWindow(profilePopIn.buildProfilePopIn());
		}
		else if(event.getButton().equals(eaeButton)){
			getUI().addWindow(personalEAEPopIn.buildPersonalEAEPopIn());
		} else if (event.getButton().equals(searchButtonCm)) {
			getUI().addWindow(searchPopIn.buildSearchPopIn());
		}
		
	}


	public GridLayout getGridLayout() {
		return gridLayout;
	}


	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
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
	
	public ProfilePopIn getProfilePopIn() {
		return profilePopIn;
	}


	public void setProfilePopIn(ProfilePopIn profilePopIn) {
		this.profilePopIn = profilePopIn;
	}


	/**
	 * @return the personalEAEPopIn
	 */
	public PersonalEAEPopIn getPersonalEAEPopIn() {
		return personalEAEPopIn;
	}


	/**
	 * @param personalEAEPopIn the personalEAEPopIn to set
	 */
	public void setPersonalEAEPopIn(PersonalEAEPopIn personalEAEPopIn) {
		this.personalEAEPopIn = personalEAEPopIn;
	}


	public SearchPopIn getSearchPopIn() {
		return searchPopIn;
	}


	public void setSearchPopIn(SearchPopIn searchPopIn) {
		this.searchPopIn = searchPopIn;
	}


	public Panel getSearchCmPanel() {
		return searchCmPanel;
	}


	public void setSearchCmPanel(Panel searchCmPanel) {
		this.searchCmPanel = searchCmPanel;
	}


	public Button getSearchButtonCm() {
		return searchButtonCm;
	}


	public void setSearchButtonCm(Button searchButtonCm) {
		this.searchButtonCm = searchButtonCm;
	}
	
	
}
