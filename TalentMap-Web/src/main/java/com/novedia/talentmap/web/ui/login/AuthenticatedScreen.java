package com.novedia.talentmap.web.ui.login;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Authorization.Role;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.ui.TabMain;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * Show view according to user's role
 * 
 * @author b.tiomofofou
 * @author e.moumbe
 */

@SuppressWarnings("serial")
public class AuthenticatedScreen extends VerticalLayout implements
		ClickListener {

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuthenticatedScreen.class);

	/**
	 * The authentication
	 */
	private Authentication authentication;

	private ChangePasswordForm changePasswordForm;

	private ChangePictureForm changePictureForm;

	private IColleagueService colleagueService;

	/**
	 * My Vaadin app
	 */
	private MyVaadinApplication myVaadinApplication;

	//
	private TabMain mainTab;
	private Resource res;

	Embedded image;

	public static final String rep = "C:/Users/j.maquin/Desktop/tmp/";

	private static final String LABEL_LOG_OUT_BUTTON = "Logout";
	private static final String LABEL_CHANGE_PASSWORD_BUTTON = "Change Password";
	private static final String LABEL_CHANGE_PICTURE_BUTTON = "Change Picture";

	private static final String HELLO_LABEL = "Hello, ";

	/**
	 * Constructor
	 * 
	 * @param myVaadinApplication
	 */

	public AuthenticatedScreen() {
		super();
	}

	/**
	 * Build the view according to user
	 * 
	 * @return ComponentContainer object
	 */
	public ComponentContainer selectedViewAccordingToUserRoles() {
		removeAllComponents();
		getMyVaadinApplication().getMainWindow().setCaption(
				"Talent Map NovediaGroup");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call selectedViewAccordingToUserRoles ()");
		}
		// Checks user role
		if (Authorization.Role.CL.getId().equals(
				this.authentication.getAuthorization().getRoleId())) {
			return buildMainLayout(Authorization.Role.CL);
		} else if (this.authentication.getAuthorization().getRoleId()
				.equals(Authorization.Role.AD.getId())) {
			return buildMainLayout(Authorization.Role.AD);
		} else if (Authorization.Role.RH.getId().equals(
				this.authentication.getAuthorization().getRoleId())) {
			return buildMainLayout(Authorization.Role.RH);
		} else if (Authorization.Role.CM.getId().equals(
				this.authentication.getAuthorization().getRoleId())) {
			return buildMainLayout(Authorization.Role.CM);
		} else if (Authorization.Role.IA.getId().equals(
				this.authentication.getAuthorization().getRoleId())) {
			return buildMainLayout(Authorization.Role.IA);
		}
		return null;
	}

	/**
	 * 
	 * @param role
	 *            user's role
	 * @return ComponentContainer object
	 */
	private ComponentContainer buildMainLayout(Role role) {

		Panel globalView = new Panel();
		VerticalLayout headerLayout = new VerticalLayout();
		HorizontalLayout informationLayout = new HorizontalLayout();
		informationLayout.setSpacing(true);
		informationLayout.addStyleName("informationHeaderLayout");
		Button logOutButton = new Button(LABEL_LOG_OUT_BUTTON);
		Button addPictureButton = new Button(LABEL_CHANGE_PICTURE_BUTTON);

		if (testFile(rep, authentication.getToken().getLogin(), ".jpg")) {
			String path = rep + authentication.getToken().getLogin() + ".jpg";
			res = new ThemeResource(path);
		} else {
			res = new ThemeResource("./images/no_photo.png");
		}
		image = new Embedded("", res);
		logOutButton.addStyleName(Reindeer.BUTTON_LINK); // transformation du
															// bouton en lien
		logOutButton.addListener(this);
		addPictureButton.addListener(this);
		Colleague currentColleague = colleagueService
				.getColleague(authentication.getColleagueId());
		String pageTilte = currentColleague.getFirstName()
				+ " - Talent Map NovediaGroup";
		getMyVaadinApplication().getMainWindow().setCaption(pageTilte);
		Label helloLabel = new Label(HELLO_LABEL
				+ currentColleague.getFirstName());
		helloLabel.addStyleName("helloLabel");
		// informationLayout.addComponent(addPictureButton);
		// informationLayout.addComponent(image);
		informationLayout.addComponent(helloLabel);
		informationLayout.addComponent(logOutButton);
		headerLayout.addComponent(informationLayout);
		headerLayout.setMargin(true);
		headerLayout.setComponentAlignment(informationLayout,
				Alignment.MIDDLE_RIGHT);
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
		if (event.getButton().getCaption().equals(LABEL_LOG_OUT_BUTTON)) {
			logout();
			return;
		} else if (event.getButton().getCaption()
				.equals(LABEL_CHANGE_PASSWORD_BUTTON)) {
			changePasswordForm.setAuthentication(getAuthentication());
			changePasswordForm.setMyVaadinApplication(myVaadinApplication);
			getWindow().addWindow(
					changePasswordForm.buildChangePasswordFormView());
			return;
		} else if (event.getButton().getCaption()
				.equals(LABEL_CHANGE_PICTURE_BUTTON)) {
			changePictureForm.setAuthentication(getAuthentication());
			changePictureForm.setMyVaadinApplication(myVaadinApplication);
			getWindow().addWindow(
					changePictureForm.buildChangePictureFormView());
			String path = rep + authentication.getToken().getLogin() + ".jpg";
			res = new ThemeResource(path);
			image.setSource(res);
			return;
		}

	}

	/**
	 * manage the logout buton
	 */
	public void logout() {
		myVaadinApplication.getWindow().removeAllComponents();
		myVaadinApplication.close();
		WebApplicationContext webCtx = (WebApplicationContext) myVaadinApplication
				.getContext();
		HttpSession session = webCtx.getHttpSession();
		session.invalidate();
		myVaadinApplication.setLogoutURL(null);
	}

	/**
	 * Get the authentication
	 * 
	 * @return authentication
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * Set authentication
	 * 
	 * @param authentication
	 *            authentication to set
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	/**
	 * Get the vaadinApplication
	 * 
	 * @return myvaadinApplication
	 */
	public MyVaadinApplication getMyVaadinApplication() {
		return myVaadinApplication;
	}

	/**
	 * Set the myvaadinApplication
	 * 
	 * @param application
	 *            myvaadinApplication to set
	 */
	public void setMyVaadinApplicationApplication(
			MyVaadinApplication application) {
		this.myVaadinApplication = application;
	}

	/**
	 * Get the maintab
	 * 
	 * @return mainTab
	 */
	public TabMain getMainTab() {
		return mainTab;
	}

	/**
	 * Set the mainTab
	 * 
	 * @param mainTab
	 *            mainTab to set
	 */
	public void setMainTab(TabMain mainTab) {
		this.mainTab = mainTab;
	}

	/**
	 * Get the changePasswordForm
	 * 
	 * @return changePasswordForm
	 */
	public ChangePasswordForm getChangePasswordForm() {
		return changePasswordForm;
	}

	/**
	 * Set changePasswordForm
	 * 
	 * @param changePasswordForm
	 *            changePasswordForm to set
	 */
	public void setChangePasswordForm(ChangePasswordForm changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}

	/**
	 * Get the changePictureForm
	 * 
	 * @return changePictureForm
	 */
	public ChangePictureForm getChangePictureForm() {
		return changePictureForm;
	}

	/**
	 * Set changePictureForm
	 * 
	 * @param changePictureForm
	 *            changePictureForm to set
	 */
	public void setChangePictureForm(ChangePictureForm changePictureForm) {
		this.changePictureForm = changePictureForm;
	}

	/**
	 * Get the colleagueService
	 * 
	 * @return colleagueService
	 */
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	/**
	 * Set colleagueService
	 * 
	 * @param colleagueService
	 *            colleagueService to set
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	/**
	 * Get the resource
	 * 
	 * @return res
	 */
	public Resource getRes() {
		return res;
	}

	/**
	 * Set the resource
	 * 
	 * @param res
	 *            resource to set
	 */
	public void setRes(Resource res) {
		this.res = res;
	}

	public static boolean testFile(String repParent, String nom,
			String extension) {
		File img = new File(repParent, nom + extension);
		if (img.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
