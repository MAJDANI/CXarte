package com.novedia.talentmap.web.ui.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Show view according to user's role
 * 
 * @author e.moumbe
 */


@SuppressWarnings("serial")
public class AuthenticatedScreen extends VerticalLayout implements ClickListener {
	
	/**
	 * The logger 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatedScreen.class);
	

	/**
	 * The authentication
	 */
	private Authentication authentication;
	
	private ChangePasswordForm changePasswordForm;
	
	/**
	 * My Vaadin app
	 */
	private MyVaadinApplication application;
	
	//
	private TabMain mainTab;
	
	
	private static final String LABEL_LOG_OUT_BUTTON = "Logout";
	private static final String LABEL_CHANGE_PASSWORD_BUTTON = "Change Password";
	
	//
	
	/**
	 * Constructor
	 * @param application
	 */
	
	public AuthenticatedScreen(){
		super();
	}
	
	
//	public AuthenticatedScreen (MyVaadinApplication application, Authentication authentication) {
//		super();
//		//Set style
//		setSizeFull();
//		setSpacing(true);
//		setMargin(true);
//		setStyleName(Reindeer.LAYOUT_WHITE);
//		this.application = application;
//		this.authentication = authentication;
////		selectedViewAccordingToUserRoles();
//	}
	
	
	/**
	 * Build according to user 
	 * @return
	 */
	public ComponentContainer selectedViewAccordingToUserRoles() {
		removeAllComponents();
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call selectedViewAccordingToUserRoles ()");
		}
		// Checks user role
		if (Authorization.Role.CL.getId().equals(this.authentication.getAuthorization().getRoleId())) {
			return buildMainLayout(Authorization.Role.CL);
		} 
		else if (this.authentication.getAuthorization().getRoleId().equals(Authorization.Role.AD.getId())) {
			return buildMainLayout(Authorization.Role.AD);
		}
		else if(Authorization.Role.RH.getId().equals(this.authentication.getAuthorization().getRoleId())){
			return buildMainLayout(Authorization.Role.RH);			
		}
		else if(Authorization.Role.CM.getId().equals(this.authentication.getAuthorization().getRoleId())){
			return buildMainLayout(Authorization.Role.CM);			
		}
		else if(Authorization.Role.IA.getId().equals(this.authentication.getAuthorization().getRoleId())){
			return buildMainLayout(Authorization.Role.IA);			
		}
		return null;
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	private ComponentContainer buildMainLayout(Role role) {
		Panel globalView = new Panel();
		VerticalLayout headerLayout = new VerticalLayout();
		headerLayout.setMargin(true);
		Button logOutButton = new Button(LABEL_LOG_OUT_BUTTON);
		logOutButton.addStyleName(Reindeer.BUTTON_LINK); //transformation du bouton en lien
		logOutButton.addListener(this);
		headerLayout.addComponent(logOutButton);	
		headerLayout.setComponentAlignment(logOutButton, Alignment.MIDDLE_RIGHT);
		
		Button changePasswordButton = new Button(LABEL_CHANGE_PASSWORD_BUTTON);
		changePasswordButton.addListener(this);
		headerLayout.addComponent(changePasswordButton);
		globalView.addComponent(headerLayout);
		mainTab.setAuthentication(getAuthentication());
		globalView.addComponent(mainTab.buildViewAccordingToUser(role));
		this.addComponent(globalView);
		return this;
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getButton().getCaption().equals(LABEL_LOG_OUT_BUTTON)){
			logout();
			return ;
		}
		if(event.getButton().getCaption().equals(LABEL_CHANGE_PASSWORD_BUTTON)){
			changePasswordForm.setAuthentication(getAuthentication());
			changePasswordForm.setMyVaadinApplication(application);
			getWindow().addWindow(changePasswordForm.buildChangePasswordFormView());
			return ;
		}
		
	}
	
	
	
	/**
	 * manage the logout buton
	 */
	public void logout() {
		application.getWindow().removeAllComponents();
		application.close();
		WebApplicationContext webCtx = (WebApplicationContext)  application.getContext();
        HttpSession session = webCtx.getHttpSession();
        session.invalidate();
        application.setLogoutURL(null);
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public MyVaadinApplication getApplication() {
		return application;
	}

	public void setApplication(MyVaadinApplication application) {
		this.application = application;
	}

	public TabMain getMainTab() {
		return mainTab;
	}

	public void setMainTab(TabMain mainTab) {
		this.mainTab = mainTab;
	}


	public ChangePasswordForm getChangePasswordForm() {
		return changePasswordForm;
	}


	public void setChangePasswordForm(ChangePasswordForm changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}


	

	
	
	

}
