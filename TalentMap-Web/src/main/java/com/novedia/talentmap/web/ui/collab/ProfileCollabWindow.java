package com.novedia.talentmap.web.ui.collab;

import com.novedia.talentmap.web.ui.profile.CollaboratorForm;
import com.novedia.talentmap.web.ui.profile.CollaboratorFormFieldFactory;
import com.novedia.talentmap.web.ui.profile.ListSkill;
import com.vaadin.ui.Window;

public class ProfileCollabWindow extends Window {
	
	private ListSkill listSkill;
	private CollaboratorForm collaboratorForm;
	
	/**
	 * Constants
	 */
	private int COLLAB_ID = 2;

	/**
	 * Build the class ProfileCollabWindow.java 
	 * @param profileView
	 */
	public ProfileCollabWindow(ListSkill listSkill, CollaboratorForm collaboratorForm) {
		super();
		this.listSkill = listSkill;
		this.collaboratorForm = collaboratorForm;
	}
	
	public void mainBuild(){
		
		setModal(true);
		center();
		setWidth(1135);
		
		this.collaboratorForm.mainBuild();
		this.listSkill.mainBuild();
		
		removeAllComponents();
		
//		addComponent(this.collaboratorForm);
//		addComponent(this.listSkill);
		
	}

	/**
	 * Set the profileView value
	 * @param profileView the profileView to set
	 */
	public void setListSkill(ListSkill listSkill) {
		this.listSkill = listSkill;
	}
	
	/**
	 * Set the collaboratorForm value
	 * @param collaboratorForm the collaboratorForm to set
	 */
	public void setCollaboratorForm(CollaboratorForm collaboratorForm) {
		this.collaboratorForm = collaboratorForm;
	}
	
	/**
	 * Get the cOLLAB_ID value
	 * @return the cOLLAB_ID
	 */
	public int getCOLLAB_ID() {
		return COLLAB_ID;
	}

	/**
	 * Set the cOLLAB_ID value
	 * @param cOLLAB_ID the cOLLAB_ID to set
	 */
	public void setCOLLAB_ID(int cOLLAB_ID) {
		COLLAB_ID = cOLLAB_ID;
		this.collaboratorForm.setCOLLAB_ID(cOLLAB_ID);
		this.listSkill.setCOLLAB_ID(cOLLAB_ID);
	}
	
}
