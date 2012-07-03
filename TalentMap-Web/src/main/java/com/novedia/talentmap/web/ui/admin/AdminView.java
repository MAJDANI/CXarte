package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.util.IAdminView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class AdminView extends HorizontalLayout {
	
	/**
	 * All views
	 */
	private AdminNavigation adminNav;
	private ManageSkillContent manageSkillContent;
	
	/**
	 * Vaadin Components 
	 */
	private HorizontalSplitPanel hSplitContent;
	
	/**
	 * Constants
	 */
	public static final String ADD_SKILL_TITLE = "Ajouter une compétence à la liste";
	public static final String UPDATE_SKILL_TITLE = "Visualisation des compétences";
	
	/**
	 * Build the class AdminView.java 
	 */
	public AdminView(AdminNavigation adminNav, ManageSkillContent manageSkillContent, HorizontalSplitPanel hSplitContent) {
		super();
		this.adminNav = adminNav;
		this.manageSkillContent = manageSkillContent;
		this.hSplitContent = hSplitContent;
		
		mainBuild();
		
		buildObservators();
	}
	
	private void buildObservators(){
		
		this.adminNav.addObservateur(new IAdminView() {
			
			@Override
			public void updateManageSkillContent(boolean addNewSkill) {
				
				if(addNewSkill){
					
					AdminView.this.manageSkillContent.addView();
					AdminView.this.manageSkillContent.getTitle().setCaption(ADD_SKILL_TITLE);
				}else{
					
					AdminView.this.manageSkillContent.updateView();
					AdminView.this.manageSkillContent.getTitle().setCaption(UPDATE_SKILL_TITLE);
				}
			}
		});
	}
	
	/**
	 * 
	 * @class AdminView.java
	 */
	public void mainBuild(){
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight(600);
		vLayout.addComponent(this.adminNav);
		
		this.hSplitContent.setFirstComponent(vLayout);
		this.hSplitContent.setSecondComponent(this.manageSkillContent);
		this.hSplitContent.setSplitPosition(20);
		
		addComponent(this.hSplitContent);
		setSizeFull();
		setExpandRatio(this.hSplitContent, 1);
	}
	
	/**
	 * Set the navLink value
	 * @param navLink the navLink to set
	 */
	public void setAdminNav(AdminNavigation adminNav) {
		this.adminNav = adminNav;
	}
	
	/**
	 * Get the addSkillContent value
	 * @return the addSkillContent
	 */
	public ManageSkillContent getManageSkillContent() {
		return manageSkillContent;
	}

	/**
	 * Set the addSkillContent value
	 * @param addSkillContent the addSkillContent to set
	 */
	public void setManageSkillContent(ManageSkillContent manageSkillContent) {
		this.manageSkillContent = manageSkillContent;
	}
	
	/**
	 * Set the hSplitContent value
	 * @param hSplitContent the hSplitContent to set
	 */
	public void sethSplitContent(HorizontalSplitPanel hSplitContent) {
		this.hSplitContent = hSplitContent;
	}
	
}
