package com.novedia.talentmap.web;

import java.util.Locale;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.login.LoginScreen;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author b.tiomofofou
 *
 */
@Theme("talentmap")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class TalentMapApplication extends UI {
	
	/**
	 * main view
	 */
	private VerticalLayout view;
	
	/**
	 * loginScreen 
	 */
	private LoginScreen loginScreen;
	
	/**
	 * the authentication
	 */
	private Authentication authentication;
	
	private Locale locale ;
	
	@Override
	protected void init(VaadinRequest request) {
		Locale l = request.getLocale();
		if(!l.getLanguage().equalsIgnoreCase(locale.getLanguage())){
			locale = l;
		}
		view.setSizeFull();
		getPage().setTitle(Constants.TITLE);
		view.addComponent(loginScreen.buildLoginView());
	    setContent(view);
	}
	
	/**
	 * Get the current Application
	 * @return TalentMapApplication
	 */
	public static TalentMapApplication getCurrent(){
		return (TalentMapApplication) UI.getCurrent();
	}

	
	/**
	 * Get the view of application 
	 * @return a verticalLayout 
	 */
	public VerticalLayout getView() {
		return view;
	}


	/**
	 * set the main view of application
	 * @param view view to set
	 */
	public void setView(VerticalLayout view) {
		this.view = view;
	}


	/**
	 * Get the LoginScreen
	 * @return LoginScreen view
	 */
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	

	/**
	 * set the LoginScreen
	 * @param loginScreen LoginScreen to set
	 */
	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}
	
	/**
	 * Get the authentication object
	 * @return  authentication object
	 */
	public Authentication getAuthentication() {
		return authentication;
	}
	
	/**
	 * Set the authentication
	 * @param authentication authentication to set
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	
	
	
	
	
}

