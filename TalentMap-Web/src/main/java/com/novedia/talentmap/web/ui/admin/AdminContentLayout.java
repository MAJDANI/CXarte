package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.IAdminContentLayout;
import com.novedia.talentmap.web.util.IAdminView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AdminContentLayout extends HorizontalLayout {

	/**
	 * Util Observateur
	 */
	private IAdminView obs;

	/**
	 * All views
	 */
	private AdminNavigation adminNav;
	private ManageSkillContent manageSkillContent;
	private AdminAddSkillContent addSkillContent;
	private AdminDeleteColleagueContent deleteColleagueContent;


	/**
	 * Vaadin Components
	 */
	private HorizontalSplitPanel hSplitContent;
	
	/**
	 * Default constructor
	 */
	public AdminContentLayout(){
		super();
	}
	
	/**
	 * Build the AdminContentLayout view
	 * @return
	 */
	public AdminContentLayout buildViewAdminContentLayout(){
		removeAllComponents();
		mainBuild();
		buildObservators();
		return this;
	}


	/**
	 * Build the class AdminView.java
	 */
//	public AdminContentLayout(AdminNavigation adminNav, ManageSkillContent manageSkillContent, 
//			AdminAddSkillContent addSkillContent, HorizontalSplitPanel hSplitContent,AdminDeleteColleagueContent deleteColleagueContent) {
//		super();
//		this.adminNav = adminNav;
//		this.manageSkillContent = manageSkillContent;
//		this.addSkillContent = addSkillContent;
//		this.hSplitContent = hSplitContent;
//		
//		this.deleteColleagueContent = deleteColleagueContent;
//
//		mainBuild();
//
//		buildObservators();
//	}

	private void buildObservators() {

		this.adminNav.addObservateur(new IAdminContentLayout() {

			@Override
			public void updateAdminContentLayout(Class<?> cl) {

				if(cl == ManageSkillContent.class){
					manageSkillContent = manageSkillContent.buildViewManageSkillContent();
					AdminContentLayout.this.manageSkillContent.buildTreeSkill();
					AdminContentLayout.this.hSplitContent.setSecondComponent(AdminContentLayout.this.manageSkillContent);
					AdminContentLayout.this.manageSkillContent.getTitle().setCaption(ConstantsEnglish.LIST_TOOL_TITLE);
					
				} else if(cl == AdminAddSkillContent.class){

					//Rafraichit les catégories disponibles
					addSkillContent = addSkillContent.buildViewAdminAddSkillContent();
					AdminContentLayout.this.addSkillContent.refreshCategoriesAvailable();
					AdminContentLayout.this.hSplitContent.setSecondComponent(AdminContentLayout.this.addSkillContent);
					AdminContentLayout.this.addSkillContent.getTitle().setCaption(ConstantsEnglish.ADD_TOOL_TITLE);
				} else if(cl == AdminDeleteColleagueContent.class){
					deleteColleagueContent = deleteColleagueContent.mainBuild();
					AdminContentLayout.this.hSplitContent.setSecondComponent(AdminContentLayout.this.deleteColleagueContent);
				}
			}
		}, IAdminContentLayout.class);
		
	}

	/**
	 * 
	 * @class AdminView.java
	 */
	public void mainBuild() {

		//initView();
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setHeight(600);
		vLayout.addComponent(this.adminNav.buildAdminNavigation());
		this.hSplitContent.setFirstComponent(vLayout);
		this.hSplitContent.setSecondComponent(this.manageSkillContent.buildViewManageSkillContent());
		this.hSplitContent.setSplitPosition(20);
		hSplitContent.setLocked(true);
		addComponent(this.hSplitContent);
		setSizeFull();
		setExpandRatio(this.hSplitContent, 1);
	}
	
//	public void initView(){
//		
//		this.manageSkillContent.setVisible(true);
//		this.addSkillContent.setVisible(false);
//		this.deleteColleagueContent.setVisible(false);
//	}

	/**
	 * Set the navLink value
	 * 
	 * @param navLink
	 *            the navLink to set
	 */
	public void setAdminNav(AdminNavigation adminNav) {
		this.adminNav = adminNav;
	}

	/**
	 * Get the addSkillContent value
	 * 
	 * @return the addSkillContent
	 */
	public ManageSkillContent getManageSkillContent() {
		return manageSkillContent;
	}

	/**
	 * Set the addSkillContent value
	 * 
	 * @param addSkillContent
	 *            the addSkillContent to set
	 */
	public void setManageSkillContent(ManageSkillContent manageSkillContent) {
		this.manageSkillContent = manageSkillContent;
	}

	
	public AdminAddSkillContent getAddSkillContent() {
		return addSkillContent;
	}

	public void setAddSkillContent(AdminAddSkillContent addSkillContent) {
		this.addSkillContent = addSkillContent;
	}

	
	/**
	 * Set the hSplitContent value
	 * 
	 * @param hSplitContent
	 *            the hSplitContent to set
	 */
	public void sethSplitContent(HorizontalSplitPanel hSplitContent) {
		this.hSplitContent = hSplitContent;
	}

	public AdminDeleteColleagueContent getDeleteColleague() {
		return deleteColleagueContent;
	}

	public void setDeleteColleague(AdminDeleteColleagueContent deleteColleagueContent) {
		this.deleteColleagueContent = deleteColleagueContent;
	}

	public AdminDeleteColleagueContent getDeleteColleagueContent() {
		return deleteColleagueContent;
	}

	public void setDeleteColleagueContent(
			AdminDeleteColleagueContent deleteColleagueContent) {
		this.deleteColleagueContent = deleteColleagueContent;
	}

	

}
