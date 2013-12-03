package com.novedia.talentmap.web;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.login.LoginScreen;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
		WebBrowser b = getSession().getBrowser();
		if(!l.getLanguage().equalsIgnoreCase(locale.getLanguage())){
			locale = l;
		}
		view.setSizeFull();
		Component mainPage; 
		if(b != null && b.isIE() &&(b.getBrowserMajorVersion() == 10)) {
			mainPage = buildNavigatorErrorMessage();
			view.addComponent(mainPage);
		} else {
			mainPage = loginScreen.buildLoginView();
			view.addComponent(mainPage);
		}
	    setContent(view);
	}
	
	
    /**
     * Internal initialization method, should not be overridden. This method is
     * not declared as final because that would break compatibility with e.g.
     * CDI.
     * 
     * @param request
     *            the initialization request
     * @param uiId
     *            the id of the new ui
     */
	@Override
    public void doInit(VaadinRequest request, int uiId) {
        if (super.getUIId() != -1) {
        	buildErrorMessage(request);
        	return;
        }
        super.doInit(request, uiId);
 
    }

	private VerticalLayout buildNavigatorErrorMessage() {
    	VerticalLayout hLayout = new VerticalLayout();
		ResourceBundle resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);;
    	Label label1 = new Label("");
    	label1.setHeight("100px");
    	label1.setWidth("1000px");
    	Label label2 = new Label(resourceBundle.getString("navigator.error.message1"));
    	label2.setHeight("50px");
    	label2.setWidth("1000px");
    	label2.setStyleName("erreurConnection");
    	hLayout.addComponent(label1);
    	hLayout.addComponent(label2);
    	hLayout.setComponentAlignment(label2, Alignment.MIDDLE_CENTER);
    	hLayout.setMargin(true);
    	hLayout.setWidth("100%");
    	return hLayout;
	}
	
	
	private void buildErrorMessage(VaadinRequest request){
		Locale l = request.getLocale();
		if(!l.getLanguage().equalsIgnoreCase(locale.getLanguage())){
			locale = l;
		}
		ResourceBundle resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);;
		Collection<UI> col = getSession().getUIs();
		System.out.println("col=" + col);
 	
		view.setSizeFull();
    	view.removeAllComponents();
    	
    	GridLayout gridLayout = new GridLayout();
    	
    	Label label0 = new Label(resourceBundle.getString("connection.error.message1"));
    	System.out.println(resourceBundle.getString("connection.error.message1"));
    	label0.setHeight("50px");
    	label0.setWidth("1000px");
    	label0.setStyleName("erreurConnection");
    	gridLayout.addComponent(label0);
    	gridLayout.setComponentAlignment(label0, Alignment.MIDDLE_CENTER);

    	Label label1 = new Label(resourceBundle.getString("connection.error.message2"));
    	label1.setHeight("50px");
    	label1.setWidth("1000px");
    	label1.setStyleName("erreurConnection");
    	gridLayout.addComponent(label1);
    	gridLayout.setComponentAlignment(label1, Alignment.MIDDLE_CENTER);

    	Label label2 = new Label(resourceBundle.getString("connection.error.message3"));
    	label2.setHeight("50px");
    	label2.setWidth("1000px");
    	label2.setStyleName("erreurConnection");
    	gridLayout.addComponent(label2);
    	gridLayout.setComponentAlignment(label2, Alignment.MIDDLE_CENTER);
    	
    	Label label3 = new Label(resourceBundle.getString("connection.error.message4"));
    	label3.setHeight("50px");
    	label3.setWidth("1000px");
    	label3.setStyleName("erreurConnection");
    	gridLayout.addComponent(label3);
    	gridLayout.setComponentAlignment(label3, Alignment.MIDDLE_CENTER);
    	
		view.addComponent(gridLayout);
    	view.setComponentAlignment(gridLayout,  Alignment.MIDDLE_CENTER);
	    setContent(view);
		getSession().getSession().invalidate();
		
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

