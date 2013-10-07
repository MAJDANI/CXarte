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
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SearchResults extends VerticalLayout implements ClickListener {

	private ResourceBundle resourceBundle;
	
	private GridLayout gridLayout;
	
	private IColleagueService colleagueService;
	
	private ProfileService profileService;
	
	private ThemeResource resourceBoy = new ThemeResource(Constants.IMG_NO_PHOTO_BOY);
	
	private ThemeResource resourceGirl = new ThemeResource(Constants.IMG_NO_PHOTO_GIRL);
	
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
			gridLayout.addStyleName("girdLayoutResult");
			gridLayout.setSpacing(true);
			
			for (Colleague colleague : listCollab) {
				Image photo;
				if(colleague.getTitle().equalsIgnoreCase(resourceBundle.getString("title.masculin.value"))) {
					photo = new Image(colleague.getFirstName(), resourceBoy);
				} else {
					photo = new Image(colleague.getFirstName(), resourceGirl);
				}
				
				photo.addStyleName("image");
				photo.addClickListener(this);
				photo.setId(colleague.getId().toString());
				
				Panel p = new Panel();
//				p.addStyleName("resultPanel");
				HorizontalLayout horizontalLayout = new HorizontalLayout();
				horizontalLayout.setSpacing(true);
				
				VerticalLayout secondBloc = new VerticalLayout();
				secondBloc.setSpacing(true);
				
				secondBloc.addComponent(new Label(profileService.getProfile(colleague.getProfileId()).getType()));
				secondBloc.addComponent(new Label(colleague.getEmail()));
				secondBloc.addComponent(new Label(colleague.getExperience() + resourceBundle.getString("experince.label.msg")));
				
				horizontalLayout.addComponent(photo);
				horizontalLayout.addComponent(secondBloc);
				p.addComponent(horizontalLayout);

				
				MissionDTO lastMission =  colleagueService.getLastMission(colleague.getId());
				if(lastMission != null){
					p.addComponent(buildLastMissionLayout(lastMission));
				}
				
				gridLayout.addComponent(p);
			}
		}
		addComponent(gridLayout);
		return this;
	}
	
	public VerticalLayout buildLastMissionLayout(MissionDTO lastMission){
		VerticalLayout lastMissionLayout = new VerticalLayout();
		lastMissionLayout.setSpacing(true);
		Label lastMissionLabel = new Label(resourceBundle.getString("last.mission.msg") +lastMission.getTitle());
		lastMissionLayout.addComponent(lastMissionLabel);
		lastMissionLayout.addComponent(new Label(resourceBundle.getString("customer.field.combobox.caption") +lastMission.getClient().getName()));
		String date = CUtils.DATE_FORMAT.format(lastMission.getStartDate());
		
		if(lastMission.getEndDate() != null){
			date += " "+resourceBundle.getString("until.msg") + " " + CUtils.DATE_FORMAT.format(lastMission.getEndDate());
			lastMissionLayout.addComponent(new Label(resourceBundle.getString("label.date.msg") +date));
		}
		else{
			date += " "+resourceBundle.getString("today.msg");
			lastMissionLayout.addComponent(new Label(resourceBundle.getString("label.date.msg") +date));
		}
		
		if(lastMission.getNotes() != null){
			lastMissionLayout.addComponent(new Label(resourceBundle.getString("form.mission.comment.caption") +lastMission.getNotes()));
		}
		
		String toolSet = new String();
		for (Tool tool : lastMission.getTools()) {
			toolSet += tool.getName() +", ";
		}
		
		lastMissionLayout.addComponent(new Label(resourceBundle.getString("label.techno.msg") +toolSet));
		
		return lastMissionLayout;
	}
	
	
	@Override
	public void click(ClickEvent event) {
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
