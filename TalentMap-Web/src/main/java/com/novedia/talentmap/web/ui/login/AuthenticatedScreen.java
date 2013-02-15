package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.novedia.talentmap.web.ui.admin.AdminView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Show view according to user's role
 * 
 * @author e.moumbe
 */
public class AuthenticatedScreen extends VerticalLayout {
	
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
	
	//
	//private TabMain mainTab;
	
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
		
		TabMain mainTab = application.getMainTab();
		Panel vpanel = new Panel();
		VerticalLayout vert = new VerticalLayout();
		        

		// Select the views to display
		if(role.equals(Role.AD)){
			mainTab.removeComponent(mainTab.getSearchView());
			mainTab.removeComponent(mainTab.getTabProfileSheet());
		}
		else{
			AdminView adminView = mainTab.getAdminView();
			mainTab.removeComponent(adminView);			
		}
		//Show logOut button
		
		//TODO:
		/*
		 * J'ai désactivé le code logout car les boutons enregistrer, modifier, valider, n'étaient plus
		 * visibles.
		 * Pour le remettre, il faudra au préalable mener une ébtude sur le positionnement des composants
		 * sru les écrans.
		 * ceci a été fait à la demande de jean max.
		 * D'autre part, Vanessa m'a fait remanqué une regression de la scroll bar horizontal qui ne 
		 * s'affiche plus.
		 * Bon courage!!!
		 */
		
		Button logout = application.getCloseButton();
		logout.addStyleName(Reindeer.BUTTON_LINK);   //rendre le bouton sous forme d'un lien
		//logout.addStyleName("logoutstyle");	
		vert.addComponent(logout);		
		vpanel.addComponent(vert);
		
		vpanel.addComponent(mainTab);
	
		//this.addComponent(application.getCloseButton());
		this.addComponent(vpanel);
		//this.addComponent(mainTab);
		//Align component on window
//		this.setComponentAlignment(this.application.getCloseButton(), Alignment.TOP_RIGHT);
//		this.setComponentAlignment(mainTab, Alignment.TOP_CENTER);
		
		return this;
	}

}
