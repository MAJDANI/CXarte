package com.novedia.talentmap.web.ui.cm;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.cm.eae.CMEAEPopIn;
import com.novedia.talentmap.web.ui.colleague.PersonalEAEPopIn;
import com.novedia.talentmap.web.ui.search.SearchPopIn;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class CmView extends VerticalLayout  implements ClickListener {
	
	
	private GridLayout gridLayout;
	
	private Panel searchPanel;
	
	/**
	 * Panel des EAE du CM, en tant que collaborateur
	 */
	private Panel eaePanel;
	
	/**
	 * Panel des EAE des collaborateurs rattachés au CM
	 */
	private Panel eaeCMPanel;
	
	private Button searchButton;

	private Button eaeButton;
	private Button eaeCMButton;
	
	private SearchPopIn searchPopIn;
	
	private PersonalEAEPopIn personalEAEPopIn;

	private CMEAEPopIn cmEAEPopIn;

	private ResourceBundle resourceBundle;

	/**
	 * Default constructor
	 */
	public CmView(){
		super();
	}
	
	/**
	 * Build CM's content view
	 * @return VerticalLayout
	 */
	public VerticalLayout buildCmContent(){
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		removeAllComponents();
		buildContent();
		addComponent(gridLayout);
		return this;
	}
	
	private void buildContent() {
		
		gridLayout.removeAllComponents();
		gridLayout.setSpacing(true);
		gridLayout.setRows(2);
		gridLayout.setColumns(3);
		gridLayout.setId("gridLayout");

		searchPanel.removeAllComponents();
		searchButton.setCaption(Constants.SEARCH_LABEL);
		searchButton.setId(ComponentsId.SEARCH_BUTTON_ID);
		searchButton.addStyleName(Reindeer.BUTTON_LINK);
		searchButton.addClickListener(this);
		searchPanel.addComponent(searchButton);
		searchPanel.addStyleName("labelBtnDashboard searchPanel");
	
		eaePanel.removeAllComponents();
		eaeButton.setCaption(resourceBundle.getString("eae.button.caption"));
		eaeButton.addStyleName(Reindeer.BUTTON_LINK);
		eaeButton.addClickListener(this);
		eaeButton.setId(ComponentsId.EAE_BUTTON_ID);
		eaePanel.addComponent(eaeButton);
		eaePanel.addStyleName("labelBtnDashboard eaePanel");
	
		eaeCMPanel.removeAllComponents();
		eaeCMButton.setCaption(resourceBundle.getString("eae.CM.button.caption"));
		eaeCMButton.addStyleName(Reindeer.BUTTON_LINK);
		eaeCMButton.addClickListener(this);
		eaeCMButton.setId(ComponentsId.EAE_CM_BUTTON_ID);
		eaeCMPanel.addComponent(eaeCMButton);
		eaeCMPanel.addStyleName("labelBtnDashboard eaeCMPanel");

		gridLayout.addComponent(searchPanel);
		gridLayout.addComponent(eaePanel);
		gridLayout.addComponent(eaeCMPanel);

		personalEAEPopIn.addStyleName("popinStyle");
		// La popIn des EAE est haute, alors on positionne une coordonnée Y plus
		// en haut de l'écran que par défaut
		personalEAEPopIn.setPositionY(100);
		personalEAEPopIn.setPositionX(100);

}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().equals(searchButton)){
			getUI().addWindow(searchPopIn.buildSearchPopIn());
		}
		else if(event.getButton().equals(eaeButton)){
			getUI().addWindow(personalEAEPopIn.buildPersonalEAEPopIn());
		} else if(event.getButton().equals(eaeCMButton)){
			getUI().addWindow(cmEAEPopIn.buildCMEAEPopIn());
		}
		
	}

	public GridLayout getGridLayout() {
		return gridLayout;
	}

	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}

	public Panel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(Panel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public Button getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(Button searchButton) {
		this.searchButton = searchButton;
	}

	public SearchPopIn getSearchPopIn() {
		return searchPopIn;
	}

	public void setSearchPopIn(SearchPopIn searchPopIn) {
		this.searchPopIn = searchPopIn;
	}

	/**
	 * @return the eaePanel
	 */
	public Panel getEaePanel() {
		return eaePanel;
	}

	/**
	 * @param eaePanel the eaePanel to set
	 */
	public void setEaePanel(Panel eaePanel) {
		this.eaePanel = eaePanel;
	}

	/**
	 * @return the eaeButton
	 */
	public Button getEaeButton() {
		return eaeButton;
	}

	/**
	 * @param eaeButton the eaeButton to set
	 */
	public void setEaeButton(Button eaeButton) {
		this.eaeButton = eaeButton;
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

	/**
	 * @return the eaeCMPanel
	 */
	public Panel getEaeCMPanel() {
		return eaeCMPanel;
	}

	/**
	 * @param eaeCMPanel the eaeCMPanel to set
	 */
	public void setEaeCMPanel(Panel eaeCMPanel) {
		this.eaeCMPanel = eaeCMPanel;
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

}
