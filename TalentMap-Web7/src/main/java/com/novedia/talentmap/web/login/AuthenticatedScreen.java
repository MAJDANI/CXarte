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
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
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
	private VerticalLayout headerLayout;
	
	private HorizontalLayout settingsLayout;
	
	private HorizontalLayout logoTalentMap;
	
	private HorizontalLayout logoNovedia;
	
	private HorizontalLayout helloLayout;
	
	private HorizontalLayout dashBoardLayout;
	
	/**
	 * the colleagueService
	 */
	private IColleagueService colleagueService;
	
	/**
	 * the colleagueView
	 */
	private ColleagueView colleagueView;
	
	private GridLayout footerGridLayout;
	
	private HorizontalLayout footerLayout;
	
	
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
		addStyleName("headerContent");
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
		buildFooterLayout();
		addComponent(footerLayout);
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
		headerLayout.setId(ComponentsId.HEADER_LAYOUT_ID);
		
		helloLayout.removeAllComponents();
		helloLayout.setSpacing(true);
		
		logoNovedia.removeAllComponents();
		logoNovedia.setId(ComponentsId.LOGO_NOVEDIA_ID);
		logoNovedia.addComponent(new Label("logo Novedia"));
		
		settingsLayout.removeAllComponents();
		settingsLayout.setSpacing(true);
		settingsLayout.setId(ComponentsId.SETTINGS_LAYOUT_ID);
		
		Label helloLabel = new Label();
		helloLabel.setCaption(Constants.HELLO_LABEL +currentColleague.getFirstName() +", ");
		
		changePasswordButton.setCaption(Constants.CHANGE_PASSWORD_LABEL);
		changePasswordButton.addStyleName(Reindeer.BUTTON_LINK);
		changePasswordButton.addClickListener(this);
		
		logOutButton.addStyleName(Reindeer.BUTTON_LINK);
		logOutButton.setCaption(Constants.LOG_OUT_BUTTON_LABEL);
		logOutButton.addClickListener(this);
		
		settingsLayout.addComponent(helloLabel);
		settingsLayout.addComponent(changePasswordButton);
		settingsLayout.addComponent(new Label("/"));
		settingsLayout.addComponent(logOutButton);
		
		helloLayout.addComponent(logoNovedia);
		helloLayout.addComponent(settingsLayout);

		logoTalentMap.removeAllComponents();
		logoTalentMap.setSpacing(true);
		logoTalentMap.setId(ComponentsId.LOGO_TMP_ID);
		logoTalentMap.addComponent(new Label("Logo Talentmap"));
		
		dashBoardLayout.removeAllComponents();
		dashBoardLayout.addComponent(new Label(Constants.DASHBOARD_LABEL));
		dashBoardLayout.setId(ComponentsId.DASHBOARD_ID);
		
		headerLayout.addComponent(helloLayout);
		headerLayout.addComponent(logoTalentMap);
		headerLayout.addComponent(dashBoardLayout);
		
	}
	
	private void buildFooterLayout(){
		footerLayout.removeAllComponents();
		footerLayout.setId("footerLayer");
		footerGridLayout.removeAllComponents();
		footerGridLayout.setRows(1);
		footerGridLayout.setColumns(3);
		VerticalLayout bloc1 = new VerticalLayout();
		bloc1.addComponent(new Label("eeeeeee"));
		VerticalLayout bloc2 = new VerticalLayout();
		bloc2.addComponent(new Label("eeeeeee2"));
		VerticalLayout bloc3 = new VerticalLayout();
		bloc3.addComponent(new Label("eeeeeee3"));
		
		bloc1.addStyleName("footerStyle");
		bloc2.addStyleName("footerStyle spacer");
		bloc3.addStyleName("footerStyle");
		
		footerGridLayout.addComponent(bloc1);
		footerGridLayout.addComponent(bloc2);
		footerGridLayout.addComponent(bloc3);
		
		footerLayout.addComponent(footerGridLayout);
		
		
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
	public VerticalLayout getHeaderLayout() {
		return headerLayout;
	}
	


	/**
	 * Set headerLayout
	 * @param header headerLayout to set
	 */
	public void setHeaderLayout(VerticalLayout header) {
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


	public HorizontalLayout getSettingsLayout() {
		return settingsLayout;
	}


	public void setSettingsLayout(HorizontalLayout settingsLayout) {
		this.settingsLayout = settingsLayout;
	}


	public HorizontalLayout getLogoTalentMap() {
		return logoTalentMap;
	}


	public void setLogoTalentMap(HorizontalLayout logoTalentMap) {
		this.logoTalentMap = logoTalentMap;
	}


	public HorizontalLayout getLogoNovedia() {
		return logoNovedia;
	}


	public void setLogoNovedia(HorizontalLayout logoNovedia) {
		this.logoNovedia = logoNovedia;
	}


	public HorizontalLayout getHelloLayout() {
		return helloLayout;
	}


	public void setHelloLayout(HorizontalLayout helloLayout) {
		this.helloLayout = helloLayout;
	}


	public HorizontalLayout getDashBoardLayout() {
		return dashBoardLayout;
	}


	public void setDashBoardLayout(HorizontalLayout dashBoardLayout) {
		this.dashBoardLayout = dashBoardLayout;
	}


	public HorizontalLayout getFooterLayout() {
		return footerLayout;
	}


	public void setFooterLayout(HorizontalLayout footerLayout) {
		this.footerLayout = footerLayout;
	}


	public GridLayout getFooterGridLayout() {
		return footerGridLayout;
	}


	public void setFooterGridLayout(GridLayout footerGridLayout) {
		this.footerGridLayout = footerGridLayout;
	}
	
	
	
	
	
	
}
