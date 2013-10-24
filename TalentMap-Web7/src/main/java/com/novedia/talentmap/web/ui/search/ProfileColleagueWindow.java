package com.novedia.talentmap.web.ui.search;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ProfileColleagueWindow extends Window {
	
	private ResourceBundle resourceBundle;
	
	private ISkillService skillService;
	
    private IProfileService profileService;
    
    private IColleagueService colleagueService;
    
    private ThemeResource resourceBoy = new ThemeResource(Constants.IMG_NO_PHOTO_BOY);
	
	private ThemeResource resourceGirl = new ThemeResource(Constants.IMG_NO_PHOTO_GIRL);
	
	private Tree treeSkills;
	
	private Accordion accordionSkill;
	
	
	
	public ProfileColleagueWindow(){
		super();
		setModal(true);
		setWidth("800px");
		setHeight("300px");
	}
	
	
	public Window buildProfileColleagueWindow(Integer colleagueId){
		removeAllComponents();
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		Colleague colleague =  colleagueService.getColleague(colleagueId);
		setCaption(colleague.getFirstName() + " " + colleague.getLastName());
		addComponent(buildDataLayout(colleague));
		CategoryMapDTO categoryMapDTO =  skillService.getAllCollaboratorSkill(colleagueId);
		treeSkills.removeAllItems();
//		addComponent(CUtils.buildTreeSkillsColleague(categoryMapDTO,treeSkills));
		accordionSkill.removeAllComponents();
		if(categoryMapDTO != null && !categoryMapDTO.getMapCategory().isEmpty()){
			addComponent(CUtils.buildAccordionOfSkill(categoryMapDTO, accordionSkill));
		}
		return this;
	}
	
	public HorizontalLayout buildDataLayout(Colleague colleague){
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);
		Image photo;
		if(colleague.getTitle().equalsIgnoreCase(resourceBundle.getString("title.masculin.value"))) {
			photo = new Image(colleague.getFirstName(), resourceBoy);
		} else {
			photo = new Image(colleague.getFirstName(), resourceGirl);
		}
		
		VerticalLayout secondBloc = new VerticalLayout();
		secondBloc.setSpacing(true);
		
		secondBloc.addComponent(new Label(profileService.getProfile(colleague.getProfileId()).getType()));
		secondBloc.addComponent(new Label(colleague.getEmail()));
		secondBloc.addComponent(new Label(colleague.getExperience() + " " + resourceBundle.getString("experince.label.msg")));
		
		horizontalLayout.addComponent(photo);
		horizontalLayout.addComponent(secondBloc);
		return horizontalLayout;
		
	}

	
	public IProfileService getProfileService() {
		return profileService;
	}


	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}


	public IColleagueService getColleagueService() {
		return colleagueService;
	}


	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}


	public ISkillService getSkillService() {
		return skillService;
	}


	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}


	public Tree getTreeSkills() {
		return treeSkills;
	}


	public void setTreeSkills(Tree treeSkills) {
		this.treeSkills = treeSkills;
	}


	public Accordion getAccordionSkill() {
		return accordionSkill;
	}


	public void setAccordionSkill(Accordion accordionSkill) {
		this.accordionSkill = accordionSkill;
	}
	

}
