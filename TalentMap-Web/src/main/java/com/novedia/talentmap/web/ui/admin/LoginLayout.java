package com.novedia.talentmap.web.ui.admin;

import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IAdminView;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.Message;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class LoginLayout extends VerticalLayout implements ClickListener, IObservable{
	
	/**
	 * Util Observateur
	 */
	private IAdminView obs;

	
	private TextField login;
	private TextField password;
	
	private Label pageTitle;
	
	private Button btnLogin;
	
	/**
	 * Flag
	 */
	public boolean isLogged;

	/**
	 * Build the class LoginLayout.java 
	 * @param login
	 * @param password
	 * @param pageTitle
	 * @param btnLogin
	 */
	public LoginLayout(TextField login, TextField password, Label pageTitle,
			Button btnLogin) {
		super();
		this.login = login;
		this.password = password;
		this.pageTitle = pageTitle;
		this.btnLogin = btnLogin;
		this.isLogged = false;
		
		mainBuild();
	}
	
	/**
	 * The main builder
	 * @class LoginLayout.java
	 */
	public void mainBuild(){
	
		setMargin(true);
		setSpacing(true);
			
		buildTitle();
		
		buildField();
		
		buildButton();
	}
	
	/**
	 * 
	 * @class LoginLayout.java
	 */
	public void buildTitle(){
		
		this.pageTitle.setCaption("Page de connection");
		this.pageTitle.setStyle(Reindeer.LABEL_H2);
		addComponent(this.pageTitle);
	}
	
	/**
	 * The field builder
	 * @class LoginLayout.java
	 */
	public void buildField(){
		
		this.login.setCaption("User login : ");
		this.login.setNullRepresentation("");
		
		this.password.setCaption("Password : ");
		this.password.setSecret(true);
		this.password.setNullRepresentation("");
		
		addComponent(this.login);
		addComponent(this.password);
	}
	
	/**
	 * The button builder
	 * @class LoginLayout.java
	 */
	public void buildButton(){
		
		this.btnLogin.setCaption("Se connecter");
		this.btnLogin.addListener(this);
		
		addComponent(this.btnLogin);
	}
	
	public void clearField(){
		
		this.login.setValue(null);
		this.password.setValue(null);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		
		if(button == this.btnLogin){
			
			String userLogin = (String) this.login.getValue();
			String userPassword = (String) this.password.getValue();
			
			if(userLogin.equals("user") && userPassword.equals("1234")){
				
				this.isLogged = true;
				updateObservateur();
				
			}else{
				
				this.isLogged = false;
				CUtils.showMessage("Login et/ou password sont incorrects", Message.ERROR, getWindow());
			}
		}
	}
	
	/**
	 * Get the login value
	 * @return the login
	 */
	public TextField getLogin() {
		return login;
	}

	/**
	 * Set the login value
	 * @param login the login to set
	 */
	public void setLogin(TextField login) {
		this.login = login;
	}

	/**
	 * Get the password value
	 * @return the password
	 */
	public TextField getPassword() {
		return password;
	}

	/**
	 * Set the password value
	 * @param password the password to set
	 */
	public void setPassword(TextField password) {
		this.password = password;
	}

	/**
	 * Get the pageTitle value
	 * @return the pageTitle
	 */
	public Label getPageTitle() {
		return pageTitle;
	}

	/**
	 * Set the pageTitle value
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(Label pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * Set the btnLogin value
	 * @param btnLogin the btnLogin to set
	 */
	public void setBtnLogin(Button btnLogin) {
		this.btnLogin = btnLogin;
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		this.obs = (IAdminView) observateur;
	}

	@Override
	public void updateObservateur() {
		this.obs.updateAdminContent(this.isLogged);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
	
	/**
	 * Get the isLogged value
	 * @return the isLogged
	 */
	public boolean isLogged() {
		return isLogged;
	}

	/**
	 * Set the isLogged value
	 * @param isLogged the isLogged to set
	 */
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}
