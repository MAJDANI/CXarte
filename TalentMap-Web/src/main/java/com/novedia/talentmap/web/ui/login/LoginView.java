package com.novedia.talentmap.web.ui.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.util.LabelConstants;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;


/**
 * 
 * @author b.tiomofofou
 * @version TMP 2.1
 */
@SuppressWarnings("serial")
public class LoginView extends VerticalLayout {

	/**
	 * The logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);
	
	/**
	 * 
	 */
	private LoginViewListener loginViewListener;
	
	/**
	 * 
	 */
	MyVaadinApplication application;
	
	/**
	 * Vaadin component
	 */
	private Button signIn;
	private LoginForm loginForm;
	private Panel loginPanel;
	
	private String LABEL_BUTTON_SIGN_IN = "Sign in";
	
	/**
	 * 
	 * @param application
	 */
	public LoginView(MyVaadinApplication application){
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Call Login Screen constructor");
		}
		this.application = application;
		buildLoginForm();
		
	}
	
	
	/**
	 * Build the form login 
	 */
	public void buildLoginForm(){
		
//		ApplicationContext ctx =  new FileSystemXmlApplicationContext("E:/Boris/Workspace/NovTalentMap/TalentMap-Web/target/TalentMap-Web-2.1-SNAPSHOT/WEB-INF/vaadin-spring-context.xml");
//		ctx.getBeanFactory().registerScope("session", new SessionScope());		
//		MyVaadinApplication app = ctx.getBean("vaadinBean", MyVaadinApplication.class);
//		if(app != null){
//			System.out.println("app charger depuis le contexte");
//		} else{
//			System.out.println("app non chargerrrr");
//		}
		setSizeFull();
		setMargin(true);
		setStyleName(Reindeer.LAYOUT_WHITE);
		//Panel for login
		this.loginPanel = new Panel("Login");
		this.loginPanel.setWidth("400px");
		
		//The form
		this.loginForm = new LoginForm();
		this.loginForm.setUsernameCaption(LabelConstants.USER_LOGIN);
		this.loginForm.setPasswordCaption(LabelConstants.USER_PASSWORD);
		this.loginForm.setLoginButtonCaption(LabelConstants.LOGIN_BUTTON);
		loginViewListener = new LoginViewListener(application);
		this.loginForm.setHeight("150px");
		this.loginForm.addListener(loginViewListener);
		this.loginPanel.addComponent(this.loginForm);
		
		this.signIn = new Button(LABEL_BUTTON_SIGN_IN);
		this.signIn.addListener(loginViewListener);
		this.loginPanel.addComponent(this.signIn);
		addComponent(this.loginPanel);
		setComponentAlignment(this.loginPanel, Alignment.MIDDLE_CENTER);
		HorizontalLayout footer = new HorizontalLayout();
		footer.setHeight("50px");
		addComponent(footer);
	}
	
	
	
	/**    GETTERS/SETTERS   **/
	
	/**
	 * Get the loginViewListener
	 * @return loginViewListener
	 */
	public LoginViewListener getLoginViewListener() {
		return loginViewListener;
	}

	/**
	 * Set the loginViewListener
	 * @param loginViewListener
	 */
	public void setLoginViewListener(LoginViewListener loginViewListener) {
		this.loginViewListener = loginViewListener;
	}
	
	
	
}
