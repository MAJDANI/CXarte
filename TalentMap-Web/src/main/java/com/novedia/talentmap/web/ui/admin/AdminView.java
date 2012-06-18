package com.novedia.talentmap.web.ui.admin;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class AdminView extends HorizontalSplitPanel {
	
	/**
	 * All views
	 */
	private NavigationLink navLink;
	private AddSkillContent addSkillContent;

	/**
	 * Build the class AdminView.java 
	 */
	public AdminView(NavigationLink navLink, AddSkillContent addSkillContent) {
		super();
		this.navLink = navLink;
		this.addSkillContent = addSkillContent;
		
		mainBuild();
		
	}
	
	/**
	 * 
	 * @class AdminView.java
	 */
	public void mainBuild(){
		
		setFirstComponent(this.navLink);
		setSecondComponent(this.addSkillContent);
		setSplitPosition(20);
	}
	
	/**
	 * Set the navLink value
	 * @param navLink the navLink to set
	 */
	public void setNavLink(NavigationLink navLink) {
		this.navLink = navLink;
	}
	
	/**
	 * Get the addSkillContent value
	 * @return the addSkillContent
	 */
	public AddSkillContent getAddSkillContent() {
		return addSkillContent;
	}

	/**
	 * Set the addSkillContent value
	 * @param addSkillContent the addSkillContent to set
	 */
	public void setAddSkillContent(AddSkillContent addSkillContent) {
		this.addSkillContent = addSkillContent;
	}
	
}
