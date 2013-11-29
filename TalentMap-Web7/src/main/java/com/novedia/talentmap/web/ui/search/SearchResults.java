package com.novedia.talentmap.web.ui.search;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.dto.MissionDTO;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.impl.ProfileService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchResults extends VerticalLayout implements LayoutClickListener {

	private ResourceBundle resourceBundle;
	
	private GridLayout gridLayout;
	
	private IColleagueService colleagueService;
	
	private ProfileService profileService;
	
	private ProfileColleagueWindow profileColleagueWindow; 
	
	/**
	 * Default constructor
	 */
	public SearchResults() {
		super();
	}
	
	

	public VerticalLayout buildResultView(List<Colleague> listCollab){
		removeAllComponents();
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		
		if(listCollab != null){
			int nbRows = (int) Math.round(listCollab.size()/2.0);
			gridLayout.removeAllComponents();
			gridLayout.setRows(nbRows);
			gridLayout.setColumns(2);
			gridLayout.setSpacing(true);
			
			for (Colleague colleague : listCollab) {
				VerticalLayout v = new VerticalLayout();
				v.addStyleName("resultBloc");
				
				HorizontalLayout profilLayout = new HorizontalLayout();
				profilLayout.addLayoutClickListener(this);
				profilLayout.setId(colleague.getId().toString());
				profilLayout.addStyleName("profilLayoutResult");
				VerticalLayout photoLayout = new VerticalLayout();
				photoLayout.addStyleName("photoResultLayout");
				VerticalLayout personnalDataResultLayout = new VerticalLayout();
				
				Label colleagueName = new Label(colleague.getFirstName() + " " + colleague.getLastName());
				colleagueName.addStyleName("colleagueName");
				
				Label profileColleague = new Label(profileService.getProfile(colleague.getProfileId()).getType());
				profileColleague.addStyleName("profileColleague");
				
				Label emailColleague = new Label(colleague.getEmail());
				emailColleague.addStyleName("emailColleague");
				
				Label colleagueExperience = new Label(colleague.getExperience() + " " + resourceBundle.getString("experince.label.msg"));
				colleagueExperience.addStyleName("colleagueExperience");
				
				personnalDataResultLayout.addComponent(colleagueName);
				personnalDataResultLayout.addComponent(profileColleague);
				personnalDataResultLayout.addComponent(emailColleague);
				personnalDataResultLayout.addComponent(colleagueExperience);
				profilLayout.addComponent(photoLayout);
				profilLayout.addComponent(personnalDataResultLayout);
				v.addComponent(profilLayout);
				
				MissionDTO lastMission =  colleagueService.getLastMission(colleague.getId());
				if(lastMission != null){
					v.addComponent(buildLastMissionLayout(lastMission));
				}
				
				gridLayout.addComponent(v);
			}
		}
		addComponent(gridLayout);
		return this;
	}
	
	public VerticalLayout buildLastMissionLayout(MissionDTO lastMission){
		//TODO ROLE MISSION ?
		VerticalLayout lastMissionLayout = new VerticalLayout();
		lastMissionLayout.addStyleName("lastMissionLayout");
		
		HorizontalLayout lastMissionTitleLayout = new HorizontalLayout();
		lastMissionTitleLayout.setSpacing(true);
		Label lastMissionTitleLabel = new Label(resourceBundle.getString("last.mission.msg"));
		lastMissionTitleLabel.addStyleName("lastMissionLabel");
		Label lastMissionTitleValue = new Label(lastMission.getTitle());
		lastMissionTitleValue.addStyleName("lastMissionLabelValue");
		lastMissionTitleLayout.addComponent(lastMissionTitleLabel);
		lastMissionTitleLayout.addComponent(lastMissionTitleValue);
		lastMissionLayout.addComponent(lastMissionTitleLayout);

		HorizontalLayout lastMissionRoleLayout = new HorizontalLayout();
		lastMissionRoleLayout.setSpacing(true);
		Label lastMissionRoleLabel = new Label(resourceBundle.getString("form.mission.role.caption"));
		lastMissionRoleLabel.addStyleName("lastMissionLabel");
		Label lastMissionRoleValue = new Label(lastMission.getRole());
		lastMissionTitleValue.addStyleName("lastMissionLabelValue");
		lastMissionRoleLayout.addComponent(lastMissionRoleLabel);
		lastMissionRoleLayout.addComponent(lastMissionRoleValue);
		lastMissionLayout.addComponent(lastMissionRoleLayout);

		HorizontalLayout lastMissionClientLayout = new HorizontalLayout();
		lastMissionClientLayout.setSpacing(true);
		Label lastMissionClientLabel = new Label(resourceBundle.getString("customer.field.combobox.caption"));
		lastMissionClientLabel.addStyleName("lastMissionLabel");
		Label lastMissionClientValue = new Label(lastMission.getClient().getName());
		lastMissionClientValue.addStyleName("lastMissionLabelValue");
		lastMissionClientLayout.addComponent(lastMissionClientLabel);
		lastMissionClientLayout.addComponent(lastMissionClientValue);
		lastMissionLayout.addComponent(lastMissionClientLayout);
		
		String date = CUtils.DATE_FORMAT.format(lastMission.getStartDate());
		if(lastMission.getEndDate() != null){
			date += " "+resourceBundle.getString("until.msg") + " " + CUtils.DATE_FORMAT.format(lastMission.getEndDate());
		}
		else{
			date += " "+resourceBundle.getString("today.msg");
		}
		HorizontalLayout lastMissionDateLayout = new HorizontalLayout();
		lastMissionDateLayout.setSpacing(true);
		Label lastMissionDateLabel = new Label(resourceBundle.getString("form.mission.dates.caption"));
		lastMissionDateLabel.addStyleName("lastMissionLabel");
		Label lastMissionDateValue = new Label(date);
		lastMissionDateValue.addStyleName("lastMissionLabelValue");
		lastMissionDateLayout.addComponent(lastMissionDateLabel);
		lastMissionDateLayout.addComponent(lastMissionDateValue);
		lastMissionLayout.addComponent(lastMissionDateLayout);
		
		if(lastMission.getNotes() != null){
			String description = lastMission.getNotes();
    	    if (description != null && description.length() >= 25) {
    	    	description = description.substring(0, 25) + "...";
    	    }
    	    HorizontalLayout lastMissionDescriptionLayout = new HorizontalLayout();
    	    lastMissionDescriptionLayout.setSpacing(true);
    	    Label lastMissionDescriptiontLabel = new Label(resourceBundle.getString("form.mission.comment.caption"));
    	    lastMissionDescriptiontLabel.addStyleName("lastMissionLabel");
    		Label lastMissionDescriptionTitle = new Label(description);
    		lastMissionDescriptionTitle.addStyleName("lastMissionLabelValue");
    		lastMissionDescriptionLayout.addComponent(lastMissionDescriptiontLabel);
    		lastMissionDescriptionLayout.addComponent(lastMissionDescriptionTitle);
    		lastMissionLayout.addComponent(lastMissionDescriptionLayout);
    	    
		}
		
		
		String toolSet = new String();
		int count = 0;
		for (Tool tool : lastMission.getTools()) {
			count += 1;
			if(count==1) { 
				toolSet += tool.getName();
			}
			else {
				toolSet +=", " + tool.getName();
			}
    	    if (toolSet != null && toolSet.length() >= 22) {
    	    	toolSet = toolSet.substring(0, 22) + "...";
    	    }

		}
		HorizontalLayout lastMissionTechnoLayout = new HorizontalLayout();
		lastMissionTechnoLayout.setSpacing(true);
		Label lastMissionToolsLabel = new Label(resourceBundle.getString("label.techno.msg"));
		lastMissionToolsLabel.addStyleName("lastMissionLabel");
		Label lastMissionToolsTitle = new Label(toolSet);
		lastMissionToolsTitle.addStyleName("lastMissionLabelValue");
		lastMissionTechnoLayout.addComponent(lastMissionToolsLabel);
		lastMissionTechnoLayout.addComponent(lastMissionToolsTitle);
		lastMissionLayout.addComponent(lastMissionTechnoLayout);
		
		return lastMissionLayout;
	}
	
	
	@Override
	public void layoutClick(LayoutClickEvent event) {
		Integer colleagueId = new Integer (event.getComponent().getId());
		getUI().addWindow(profileColleagueWindow.buildProfileColleagueWindow(colleagueId));
		
	}
	
	


	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public GridLayout getGridLayout() {
		return gridLayout;
	}

	public void setGridLayout(GridLayout gridLayout) {
		this.gridLayout = gridLayout;
	}



	public ProfileColleagueWindow getProfileColleagueWindow() {
		return profileColleagueWindow;
	}



	public void setProfileColleagueWindow(
			ProfileColleagueWindow profileColleagueWindow) {
		this.profileColleagueWindow = profileColleagueWindow;
	}

}
