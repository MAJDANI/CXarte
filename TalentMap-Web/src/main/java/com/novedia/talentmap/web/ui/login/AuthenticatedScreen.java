package com.novedia.talentmap.web.ui.login;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * Main screen of the application
 * 
 * @author e.moumbe
 */
public class AuthenticatedScreen extends VerticalLayout{
	
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
	
	/**
	 * Constructor
	 * @param application
	 */
	public AuthenticatedScreen (MyVaadinApplication application, Authentication authentication) {
		super();
		this.application = application;
		this.authentication = authentication;
		selectedViewAccordingToUserRoles();
	}
	
	/**
	 * Select view
	 */
	public void selectedViewAccordingToUserRoles () {
		
		// Checks user role
		if (Authorization.Role.CL.getId().equals(this.authentication.getAuthorization().getRoleId())) {
			this.addComponent(buildMainLayout(this.application, Authorization.Role.CL));
		} 
		else if (this.authentication.getAuthorization().getRoleId() == Authorization.Role.AD.getId()) {

		} 
		else {

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
		
		// Delete all components
		application.gethLayout().removeAllComponents();
		TabMain mainTab = application.getMainTab();
		
		// Select the views to display
		if(role.equals(Role.AD)){
			
			mainTab.removeComponent(mainTab.getSearchView());
			mainTab.removeComponent(mainTab.getTabProfileSheet());
		}
		else{
			AdminView adminView = mainTab.getAdminView();
			mainTab.removeComponent(adminView);			
		}
		
		application.gethLayout().addComponent(mainTab);
		
		return application.gethLayout(); 
	}
}
