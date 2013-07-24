package com.novedia.talentmap.web.login;


import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
@SuppressWarnings("serial")
public class AuthenticatedScreen extends VerticalLayout implements ClickListener{
	
	private Button logOutButton;
	
	
	public AuthenticatedScreen(){
		super();
	}
	
	
	public VerticalLayout buildAuthenticatedScreen(){
		removeAllComponents();
		addComponent(new Label("connection : " +TalentMapApplication.getCurrent().getColleagueId()));
		logOutButton.addStyleName(Reindeer.BUTTON_LINK);
		logOutButton.setCaption(Constants.LOG_OUT_BUTTON_LABEL);
		logOutButton.addClickListener(this);
		addComponent(logOutButton);
		return this;
	}
	
	
	public VerticalLayout selectedViewAccordingToUserRoles(){
		removeAllComponents();
//		if (Authorization.Role.CL.getId().equals(null)){
//			
//		}
		
		return buildAuthenticatedScreen();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(logOutButton)) {  
			logout();
		}
		
	}

	private void logout() {
		
		VaadinRequest request = VaadinService.getCurrentRequest();
		request.getWrappedSession().invalidate();
		getUI().getPage().setLocation(request.getContextPath());
		
	}


	public Button getLogOutButton() {
		return logOutButton;
	}


	public void setLogOutButton(Button logOutButton) {
		this.logOutButton = logOutButton;
	}


	


}
