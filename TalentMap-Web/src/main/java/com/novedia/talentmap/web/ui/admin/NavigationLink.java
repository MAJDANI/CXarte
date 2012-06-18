package com.novedia.talentmap.web.ui.admin;

import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

public class NavigationLink extends VerticalLayout {
	
	/**
	 * Vaddin Components
	 */
	private Link addSkillLink;
	private Link updateSkillLink;
	private Link deleteSkillLink;
	private Link deleteCollabLink;
	
	/**
	 * Constants
	 */
	public static final String ADD_SKILL_NAME = "Ajouter des compétences";
	public static final String UPDATE_SKILL_NAME = "Modifier des compétences";
	public static final String DELETE_SKILL_NAME = "Supprimer des compétences";
	public static final String DELETE_COLLAB_NAME = "Supprimer des collaborateurs";
	
	/**
	 * Build the class NavigationLink.java 
	 * @param addSkillLink
	 * @param updateSkillLink
	 * @param deleteSkillLink
	 * @param deleteCollabLink
	 */
	public NavigationLink(Link addSkillLink, Link updateSkillLink,
			Link deleteSkillLink, Link deleteCollabLink) {
		super();
		this.addSkillLink = addSkillLink;
		this.updateSkillLink = updateSkillLink;
		this.deleteSkillLink = deleteSkillLink;
		this.deleteCollabLink = deleteCollabLink;
		
		mainBuild();
		
	}
	
	public void mainBuild(){
		
		setMargin(true);
		setSpacing(true);
		
		buildLinks();
		
	}
	
	private void buildLinks(){
		
		this.addSkillLink.setCaption(ADD_SKILL_NAME);
		addComponent(this.addSkillLink);
		
		this.updateSkillLink.setCaption(UPDATE_SKILL_NAME);
		addComponent(this.updateSkillLink);
		
		this.deleteSkillLink.setCaption(DELETE_SKILL_NAME);
		addComponent(this.deleteSkillLink);
		
		this.deleteCollabLink.setCaption(DELETE_COLLAB_NAME);
		addComponent(this.deleteCollabLink);
	}
	
	/**
	 * Set the addSkillLink value
	 * @param addSkillLink the addSkillLink to set
	 */
	public void setAddSkillLink(Link addSkillLink) {
		this.addSkillLink = addSkillLink;
	}
	/**
	 * Set the updateSkillLink value
	 * @param updateSkillLink the updateSkillLink to set
	 */
	public void setUpdateSkillLink(Link updateSkillLink) {
		this.updateSkillLink = updateSkillLink;
	}
	/**
	 * Set the deleteSkillLink value
	 * @param deleteSkillLink the deleteSkillLink to set
	 */
	public void setDeleteSkillLink(Link deleteSkillLink) {
		this.deleteSkillLink = deleteSkillLink;
	}
	/**
	 * Set the deleteCollabLink value
	 * @param deleteCollabLink the deleteCollabLink to set
	 */
	public void setDeleteCollabLink(Link deleteCollabLink) {
		this.deleteCollabLink = deleteCollabLink;
	}
	
	/**
	 * Get the addSkillLink value
	 * @return the addSkillLink
	 */
	public Link getAddSkillLink() {
		return addSkillLink;
	}

	/**
	 * Get the updateSkillLink value
	 * @return the updateSkillLink
	 */
	public Link getUpdateSkillLink() {
		return updateSkillLink;
	}

	/**
	 * Get the deleteSkillLink value
	 * @return the deleteSkillLink
	 */
	public Link getDeleteSkillLink() {
		return deleteSkillLink;
	}

	/**
	 * Get the deleteCollabLink value
	 * @return the deleteCollabLink
	 */
	public Link getDeleteCollabLink() {
		return deleteCollabLink;
	}
}
