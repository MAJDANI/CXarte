package com.novedia.talentmap.web.ui.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.novedia.talentmap.web.ui.profile.ProfileView;
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
public class AuthenticatedScreen extends VerticalLayout  {
	
	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticatedScreen.class);
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = -4155975573258279396L;

	/**
	 * The authentication
	 */
	private Authentication authentication;
	
	/**
	 * My Vaadin app
	 */
	private MyVaadinApplication application;
	private ProfileView profileView;
	
	//
	private TabMain mainTab;
	
	//
	
	/**
	 * Constructor
	 * @param application
	 */
	public AuthenticatedScreen (MyVaadinApplication application, Authentication authentication) {
		super();
		//Set style
		setSizeFull();
		setMargin(true);
		setStyleName(Reindeer.LAYOUT_WHITE);
		this.application = application;
		this.authentication = authentication;
		selectedViewAccordingToUserRoles();
	}
	
	/**
	 * Select view
	 */
	public void selectedViewAccordingToUserRoles () {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call selectedViewAccordingToUserRoles ()");
		}
		// Checks user role
		if (Authorization.Role.CL.getId().equals(this.authentication.getAuthorization().getRoleId())) {
			buildMainLayout(this.application, Authorization.Role.CL);
		} 
		else if (this.authentication.getAuthorization().getRoleId().equals(Authorization.Role.AD.getId())) {
			buildMainLayout(this.application, Authorization.Role.AD);
		}
		else if(Authorization.Role.RH.getId().equals(this.authentication.getAuthorization().getRoleId())){
			buildMainLayout(this.application, Authorization.Role.RH);			
		}
		else if(Authorization.Role.CM.getId().equals(this.authentication.getAuthorization().getRoleId())){
			buildMainLayout(this.application, Authorization.Role.CM);			
		}
		else if(Authorization.Role.IA.getId().equals(this.authentication.getAuthorization().getRoleId())){
			buildMainLayout(this.application, Authorization.Role.IA);			
		}
		else if(Authorization.Role.CL.getId().equals(this.authentication.getAuthorization().getRoleId())){
			buildMainLayout(this.application, Authorization.Role.CL);			
		}
		// this.application.getMainWindow().setContent(buildMainLayout());
	}
	
	/**
	 * Builds all layout
	 * @param application
	 * @param cl 
	 * @return
	 */
	private ComponentContainer buildMainLayout(MyVaadinApplication application, Role role ) {
		
		TabMain mainTab = application.getMainTab();

		AdminView adminView = mainTab.getAdminView();
		CmContentLayout cmContentLayout = application.getCmContentLayout();
		RhContentLayout rhContentLayout = application.getRhContentLayout();
		Panel vpanel = new Panel();
		VerticalLayout verticalLayout = new VerticalLayout();
		
		        
		// Select the views to display
		if(role.equals(Role.AD)){
			mainTab.removeComponent(mainTab.getSearchView());
			mainTab.removeComponent(mainTab.getTabProfileSheet());
		}
		else if(role.equals(Role.IA)){
			mainTab.removeComponent(mainTab.getTabProfileSheet());
			mainTab.removeComponent(adminView);	
		}
		else if(role.equals(Role.RH)){		
			mainTab.removeComponent(mainTab.getSearchView());
			mainTab.removeComponent(mainTab.getTabProfileSheet());
			mainTab.removeComponent(adminView);		
			addComponent(rhContentLayout);
		}
		else if(role.equals(Role.CM)){
			mainTab.removeComponent(mainTab.getSearchView());
			mainTab.removeComponent(mainTab.getTabProfileSheet());
			mainTab.removeComponent(adminView);		
			addComponent(cmContentLayout);
		}
		else if(role.equals(Role.CL)){
			mainTab.removeComponent(adminView);		
			mainTab.removeComponent(mainTab.getSearchView());
		}
		
//		else{
//			mainTab.removeComponent(adminView);			
//		}
		//Show logOut button

		// logout button
		Button logout = new Button("LogOut");
		logout.addStyleName(Reindeer.BUTTON_LINK); //transformer le bouton en lien
		logout.addListener(new Button.ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				logout();
			}
		});
		
		verticalLayout.addComponent(logout);		
		vpanel.addComponent(verticalLayout);
		vpanel.addComponent(mainTab);
	
		this.addComponent(vpanel);
		return this;
	}

	/**
	 * manage the logout buton
	 */
	public void logout() {
		application.getMainVerticalLayout().removeAllComponents();
		application.getWindow().removeAllComponents();
		application.close();
		WebApplicationContext webCtx = (WebApplicationContext)  application.getContext();
        HttpSession session = webCtx.getHttpSession();
        session.invalidate();
        application.setLogoutURL(null);
	}

}
