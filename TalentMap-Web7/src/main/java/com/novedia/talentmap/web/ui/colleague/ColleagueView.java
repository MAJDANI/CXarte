package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.cm.eae.CMEAEPopIn;
import com.novedia.talentmap.web.ui.search.SearchPopIn;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ColleagueView extends VerticalLayout implements ClickListener {

	/**
	 * Vaadin components
	 */
	private GridLayout gridLayout;
	
	private Button profilButton;
	
	private Button eaeButton;

	private Button eaeCMButton;

	private ProfilePopIn profilePopIn;
	
	private Button formationButton;
	
	private PersonalEAEPopIn personalEAEPopIn;
	
	private SearchPopIn searchPopIn;

	private CMEAEPopIn cmEAEPopIn;

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
		//There is nothing to display yet
		//gridLayout.addComponent(formationButton);
		
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		if(authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())){
			searchButtonCm.addClickListener(this);
			searchButtonCm.addStyleName("labelBtnDashboard searchButton");
			gridLayout.addComponent(searchButtonCm);

			eaeCMButton.addStyleName("labelBtnDashboard eaeCMButton");
			eaeCMButton.addClickListener(this);
			eaeCMButton.setId(ComponentsId.EAE_CM_BUTTON_ID);
			gridLayout.addComponent(eaeCMButton);

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
		}else if(event.getButton().equals(eaeCMButton)){
			getUI().addWindow(cmEAEPopIn.buildCMEAEPopIn());
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

	public Button getSearchButtonCm() {
		return searchButtonCm;
	}


	public void setSearchButtonCm(Button searchButtonCm) {
		this.searchButtonCm = searchButtonCm;
	}


	/**
	 * @return the eaeCMButton
	 */
	public Button getEaeCMButton() {
		return eaeCMButton;
	}


	/**
	 * @param eaeCMButton the eaeCMButton to set
	 */
	public void setEaeCMButton(Button eaeCMButton) {
		this.eaeCMButton = eaeCMButton;
	}


	/**
	 * @return the cmEAEPopIn
	 */
	public CMEAEPopIn getCmEAEPopIn() {
		return cmEAEPopIn;
	}


	/**
	 * @param cmEAEPopIn the cmEAEPopIn to set
	 */
	public void setCmEAEPopIn(CMEAEPopIn cmEAEPopIn) {
		this.cmEAEPopIn = cmEAEPopIn;
	}
	
	
}
