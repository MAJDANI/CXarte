package com.novedia.talentmap.web.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class ProfileView extends VerticalLayout {
	
	private CollaboratorForm collabForm;
	private ListSkill listSkill;
	
	public ProfileView(CollaboratorForm collabForm, ListSkill listSkill){
		super();
		this.collabForm = collabForm;
		this.listSkill = listSkill;
		
		Panel panel = new Panel();
		Label labelSkill = new Label("Liste des compétences");
		labelSkill.setStyle(Reindeer.LABEL_H2);
		
		panel.addComponent(labelSkill);
		panel.addComponent(listSkill);
		
		
		addComponent(collabForm);
		
		addComponent(panel);
		
		
	}
	
	/**
	 * Set the collabForm value
	 * @param collabForm the collabForm to set
	 */
	public void setCollabForm(CollaboratorForm collabForm) {
		this.collabForm = collabForm;
	}

	/**
	 * Set the listSkill value
	 * @param listSkill the listSkill to set
	 */
	public void setListSkill(ListSkill listSkill) {
		this.listSkill = listSkill;
	}
}
