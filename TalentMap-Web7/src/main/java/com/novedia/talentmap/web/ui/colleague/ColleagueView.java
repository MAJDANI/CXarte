package com.novedia.talentmap.web.ui.colleague;

import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class ColleagueView extends VerticalLayout {

	private GridLayout gridLayout;
	
	private Panel profilPanel;
	
	private Button profilButton;
	
	private Button eaeButton;
	
	private Panel eaePanel;
	
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
		profilButton.setId(ComponentsId.PROFILE_BUTTON_ID);
		profilPanel.addComponent(profilButton);
		profilPanel.addStyleName("labelBtnDashboard");
		
		eaePanel.removeAllComponents();
		eaeButton.setCaption(Constants.EAE_LABEL);
		eaeButton.addStyleName(Reindeer.BUTTON_LINK);
		eaeButton.setId(ComponentsId.EAE_BUTTON_ID);
		eaePanel.addComponent(eaeButton);
		eaePanel.addStyleName("labelBtnDashboard");
		
		gridLayout.addComponent(profilPanel);
		gridLayout.addComponent(eaePanel);
		
		//composants ajoutés par défaut pour le style 
		Panel px = new Panel();
		px.addComponent(new Button("XX"));
		
		Panel py = new Panel();
		py.addComponent(new Button("YY"));
		py.addStyleName("top25");
		
		Panel pz = new Panel();
		pz.addComponent(new Button("ZZ"));
		pz.addStyleName("top25");
		
		Panel pw = new Panel();
		pw.addComponent(new Button("WW"));
		pw.addStyleName("top25");
		
		gridLayout.addComponent(px);
		gridLayout.addComponent(py);
		gridLayout.addComponent(pz);
		gridLayout.addComponent(pw);
		
		
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
	
	
}
