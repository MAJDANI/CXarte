package com.novedia.talentmap.web.ui.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.novedia.talentmap.web.ui.role.CmContentLayout;
import com.novedia.talentmap.web.ui.role.RhContentLayout;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
public class AuthenticatedScreen extends VerticalLayout  {
	
	/**
	 * The logger 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticatedScreen.class);
	

	/**
	 * The authentication
	 */
	private Authentication authentication;
	
	/**
	 * My Vaadin app
	 */
	private MyVaadinApplication application;
	
	//
	private TabMain mainTab;
	
	
	private static final String LOG_OUT_BUTTON = "LogOut";
	//
	
	/**
	 * Constructor
	 * @param application
	 */
	
	public AuthenticatedScreen(){
	}
	
	
	public AuthenticatedScreen (MyVaadinApplication application, Authentication authentication) {
		super();
		//Set style
		setSizeFull();
		setMargin(true);
		setStyleName(Reindeer.LAYOUT_WHITE);
		this.application = application;
		this.authentication = authentication;
//		selectedViewAccordingToUserRoles();
	}
	
	
	/**
	 * Build according to user 
	 * @return
	 */
	public ComponentContainer selectedViewAccordingToUserRoles() {
		
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
		Panel globalPannel = new Panel();
		VerticalLayout logOutLayout = new VerticalLayout();
		Button logOutButton = new Button(LOG_OUT_BUTTON);
		logOutButton.addStyleName(Reindeer.BUTTON_LINK); //transformation du bouton en lien
		logOutButton.addListener(new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				logout();
			}
		});
		
		logOutLayout.addComponent(logOutButton);		
		globalPannel.addComponent(logOutLayout);
		mainTab.setAuthentication(getAuthentication());
		globalPannel.addComponent(mainTab.buildViewAccordingToUser(role));
		this.addComponent(globalPannel);
		return this;
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

	
	
	

}
