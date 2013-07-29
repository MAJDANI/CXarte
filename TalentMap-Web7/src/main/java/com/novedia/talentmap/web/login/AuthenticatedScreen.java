package com.novedia.talentmap.web.login;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.colleague.ColleagueView;
import com.novedia.talentmap.web.ui.ia.IaView;
import com.novedia.talentmap.web.ui.rh.RhView;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The AuthenticatedScreen class
 * @author b.tiomofofou
 *
 */
@SuppressWarnings("serial")
public class AuthenticatedScreen extends VerticalLayout implements ClickListener{
	
	/**
	 * the logOutButton
	 */
	private Button logOutButton;
	
	/**
	 * the header layout
	 */
	private HorizontalLayout headerLayout;
	
	/**
	 * the colleagueService
	 */
	private IColleagueService colleagueService;
	
	/**
	 * the colleagueView
	 */
	private ColleagueView colleagueView;
	
	
	/**
	 * the adminView
	 */
	private AdminView adminView;
	
	/**
	 * the iaView
	 */
	private IaView iaView;
	
	/**
	 * the rhView
	 */
	private RhView rhView; 
	
	/**
	 * the ChangePasswordButton
	 */
	private Button changePasswordButton ;	
	
	private ChangePasswordScreen changePasswordScreen;
	
	/**
	 * Default constructor
	 */
	public AuthenticatedScreen(){
		super();
		setSpacing(true);
	}
	
	
	/**
	 * Build the view according to user's role
	 * @return a VerticalLayout
	 */
	public VerticalLayout selectedViewAccordingToUserRoles(){
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		removeAllComponents();
		buildHeaderLayout();
		addComponent(headerLayout);
		
		if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.CL.getId())){ //Collaborator
			addComponent(colleagueView.builColleagueContent());
		} else if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.AD.getId())) { // Admin
			addComponent(adminView.buildAdminContent());
		} else if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.CM.getId())) { // CM
			
		} else if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.IA.getId())) { // IA
			addComponent(iaView.buildIaContent());
		} else if (authentication.getAuthorization().getRoleId().equals(Authorization.Role.RH.getId())) { // RH
			addComponent(rhView.buildRhContent());
		}
		
		return this;
	}

	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(logOutButton)) {  
			logout();
		} else if (event.getButton().equals(changePasswordButton)) {
			getUI().addWindow(changePasswordScreen.buildChangePasswordForm());
		}
	}
	
	
	/**
	 * Build the header layout
	 */
	private void buildHeaderLayout(){
		Colleague currentColleague = colleagueService.getColleague(TalentMapApplication.getCurrent().getAuthentication().getColleagueId());
		headerLayout.removeAllComponents();
		headerLayout.setSpacing(true);
		Label helloLabel = new Label();
		helloLabel.setCaption(Constants.HELLO_LABEL +currentColleague.getFirstName() +", ");
		
		changePasswordButton.setCaption(Constants.CHANGE_PASSWORD_LABEL);
		changePasswordButton.addStyleName(Reindeer.BUTTON_LINK);
		changePasswordButton.addClickListener(this);
		
		logOutButton.addStyleName(Reindeer.BUTTON_LINK);
		logOutButton.setCaption(Constants.LOG_OUT_BUTTON_LABEL);
		logOutButton.addClickListener(this);
		
		headerLayout.addComponent(helloLabel);
		headerLayout.addComponent(changePasswordButton);
		headerLayout.addComponent(new Label("/"));
		headerLayout.addComponent(logOutButton);
		
	}
	

	/**
	 * manage the logout mechanism 
	 */
	private void logout() {
		VaadinRequest request = VaadinService.getCurrentRequest();
		request.getWrappedSession().invalidate();
		getUI().getPage().setLocation(request.getContextPath());
	}


	/**
	 * Get the logOutButton
	 * @return logOutButton
	 */
	public Button getLogOutButton() {
		return logOutButton;
	}


	/**
	 * Set the logOutButton 
	 * @param logOutButton logOutButton to set 
	 */
	public void setLogOutButton(Button logOutButton) {
		this.logOutButton = logOutButton;
	}


	/**
	 * Get headerLayout
	 * @return headerLayout
	 */
	public HorizontalLayout getHeaderLayout() {
		return headerLayout;
	}
	


	/**
	 * Set headerLayout
	 * @param header headerLayout to set
	 */
	public void setHeaderLayout(HorizontalLayout header) {
		this.headerLayout = header;
	}


	/**
	 * Get the colleagueService
	 * @return IcolleagueService
	 */
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	/**
	 * Set colleagueService 
	 * @param colleagueService colleagueService to set
	 */

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}


	/**
	 * Get the ChangePasswordButton
	 * @return ChangePasswordButton
	 */
	public Button getChangePasswordButton() {
		return changePasswordButton;
	}

	/**
	 * Set the ChangePasswordButton
	 * @param changePasswordButton ChangePasswordButton to set
	 */
	public void setChangePasswordButton(Button changePasswordButton) {
		this.changePasswordButton = changePasswordButton;
	}

	/**
	 * Get the colleagueView
	 * @return a colleagueView
	 */
	public ColleagueView getColleagueView() {
		return colleagueView;
	}

	/**
	 * Set the colleagueview
	 * @param colleagueView colleagueView to set
	 */
	public void setColleagueView(ColleagueView colleagueView) {
		this.colleagueView = colleagueView;
	}


	/**
	 * Get the AdminView
	 * @return AdminView 
	 */
	public AdminView getAdminView() {
		return adminView;
	}


	/**
	 * Set the AdminView
	 * @param adminView  adminView to set
	 */
	public void setAdminView(AdminView adminView) {
		this.adminView = adminView;
	}


	/**
	 * Get IaView
	 * @return IaView
	 */
	public IaView getIaView() {
		return iaView;
	}


	/**
	 * Set the IaView
	 * @param iaView iaView to set
	 */
	public void setIaView(IaView iaView) {
		this.iaView = iaView;
	}


	/**
	 * Get the RhView
	 * @return RhView
	 */
	public RhView getRhView() {
		return rhView;
	}


	/**
	 * Set the RhView
	 * @param rhView rhView to set
	 */
	public void setRhView(RhView rhView) {
		this.rhView = rhView;
	}


	public ChangePasswordScreen getChangePasswordScreen() {
		return changePasswordScreen;
	}


	public void setChangePasswordScreen(ChangePasswordScreen changePasswordScreen) {
		this.changePasswordScreen = changePasswordScreen;
	}
	
	
	
	
}
