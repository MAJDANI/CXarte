package com.novedia.talentmap.web.login;

import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IAuthenticationService;
import com.novedia.talentmap.services.IChangePasswordService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ChangePasswordScreen extends Window implements ClickListener {

	/**
	 * the authenticationService
	 */
	private IAuthenticationService authenticationService;
	
	/**
	 * the changePasswordService
	 */
	 private IChangePasswordService changePasswordService;
	 
	 /**
	  * the changePasswordFormLayout
	  */
	 private FormLayout changePasswordFormLayout;
	 /**
	  * the oldPasswordField
	  */
	 private PasswordField oldPasswordField;
	 /**
	  * the newPasswordField
	  */
     private PasswordField newPasswordField;
     /**
      * the confirmPasswordField
      */
     private PasswordField confirmPasswordField;
     
     /**
      * the errorLabelOldPassword
      */
     private Label errorLabelOldPassword;
     /**
      * the errorLabelNewPassword
      */
     private Label errorLabelNewPassword;
     
     /**
      * the saveButton
      */
     private Button saveButton;
     
     private ResourceBundle resourceBundle;
	
	/**
	 * Deafault constructor
	 */
     public ChangePasswordScreen(){
    	 super();
    	 setModal(true);
     }
	
     
     /**
      * Build changePassword View
      * @return Window
      */
	public Window buildChangePasswordForm(){
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
		setCaption(resourceBundle.getString("password.form.view.title"));
		removeAllComponents();
		errorLabelOldPassword.setCaption(resourceBundle.getString("error.label.old.password"));
		errorLabelOldPassword.addStyleName("errorStyle");
		errorLabelNewPassword.setCaption(resourceBundle.getString("error.label.new.password"));
		errorLabelNewPassword.addStyleName("errorStyle");
		errorLabelOldPassword.setVisible(false);
		errorLabelNewPassword.setVisible(false);
		
		addComponent(errorLabelOldPassword);
		addComponent(errorLabelNewPassword);
		buildForm();
		addComponent(changePasswordFormLayout);
		return this;
		
	}
	
	/**
	 * Build changepassword form
	 */
	private void buildForm(){
		changePasswordFormLayout.removeAllComponents();
		oldPasswordField.setCaption(resourceBundle.getString("old.password.field.caption"));
		oldPasswordField.setValue("");
		oldPasswordField.setId(ComponentsId.OLD_PASSWORD_FIELD_ID);
		oldPasswordField.setRequired(true);
		
		newPasswordField.setCaption(resourceBundle.getString("new.password.field.caption"));
		newPasswordField.setValue("");
		newPasswordField.setId(ComponentsId.NEW_PASSWORD_FIELD_ID);
		newPasswordField.setRequired(true);
		
		confirmPasswordField.setCaption(resourceBundle.getString("confirm.password.field"));
		confirmPasswordField.setValue("");
		confirmPasswordField.setId(ComponentsId.CONFIRM_PASSWORD_FIELD_ID);
		confirmPasswordField.setRequired(true);
		
		saveButton.setCaption(resourceBundle.getString("save.button.caption"));
		saveButton.addClickListener(this);
		saveButton.setId(ComponentsId.SAVE_BTN_ID);
		
		//TODO : a supprimer car corriger dans la feuille de style.  		
//		VerticalLayout vLayout = new VerticalLayout();
//		vLayout.addComponent(oldPasswordField);
//		vLayout.addComponent(newPasswordField);
//		vLayout.addComponent(confirmPasswordField);
//		vLayout.addComponent(saveButton);
//		changePasswordFormLayout.addComponent(vLayout);
		
		changePasswordFormLayout.addComponent(oldPasswordField);
		changePasswordFormLayout.addComponent(newPasswordField);
		changePasswordFormLayout.addComponent(confirmPasswordField);
		changePasswordFormLayout.addComponent(saveButton);
		changePasswordFormLayout.setSpacing(true);
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(saveButton)) { 
			String oldPassword = (String) oldPasswordField.getValue();
			String newPassword = (String) newPasswordField.getValue();
			String confirmPassword = (String) confirmPasswordField.getValue();
			if(oldPassword.length() == 0 || newPassword.length() == 0 || confirmPassword.length() == 0){
				Notification.show(resourceBundle.getString("missing.all.fields.msg"), Type.WARNING_MESSAGE);
				return;
			}
			
			if (authenticationService.encodePassword(oldPassword).equals(
					TalentMapApplication.getCurrent().getAuthentication().getToken().getPassword())) {
				if (newPassword.equals(confirmPassword)) {
					String encodeNewPassword = authenticationService.encodePassword(newPassword);
					Authentication authenticationTmp = new Authentication();
					CredentialToken credentialTmp = new CredentialToken();
					credentialTmp.setLogin(TalentMapApplication.getCurrent().getAuthentication().getToken()
						.getLogin());
					credentialTmp.setPassword(encodeNewPassword);
					authenticationTmp.setColleagueId(TalentMapApplication.getCurrent().getAuthentication().getColleagueId());
					authenticationTmp.setToken(credentialTmp);
					int result = changePasswordService.savePassword(authenticationTmp);
					if (result != 0) {
						TalentMapApplication.getCurrent().getAuthentication().getToken().setPassword(encodeNewPassword);
						close();
						Notification.show(resourceBundle.getString("update.successful.msg"), Type.TRAY_NOTIFICATION);
					} else {
						Notification.show(resourceBundle.getString("update.error.msg"), Type.ERROR_MESSAGE);
					}
					
				} else {
					errorLabelOldPassword.setVisible(false);
					errorLabelNewPassword.setVisible(true);
				}
				
			} else { // bad old password 
				errorLabelOldPassword.setVisible(true);
				errorLabelNewPassword.setVisible(false);
			}
			
		}
	}

	/**
	 * Get the authenticationService
	 * @return IAuthenticationService
	 */
	public IAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * Set the authenticationService
	 * @param authenticationService authenticationService to set
	 */
	public void setAuthenticationService(
			IAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Get the changePasswordService
	 * @return IChangePasswordService
	 */
	public IChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	/**
	 * Set the changePasswordService
	 * @param changePasswordService changePasswordService to set
	 */
	public void setChangePasswordService(
			IChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	/**
	 * Get the changePasswordFormLayout
	 * @return FormLayout
	 */
	public FormLayout getChangePasswordFormLayout() {
		return changePasswordFormLayout;
	}

	/**
	 * Set the changePasswordFormLayout
	 * @param changePasswordFormLayout changePasswordFormLayout to set
	 */
	public void setChangePasswordFormLayout(FormLayout changePasswordFormLayout) {
		this.changePasswordFormLayout = changePasswordFormLayout;
	}

	/**
	 * Get the oldPasswordField
	 * @return PasswordField
	 */
	public PasswordField getOldPasswordField() {
		return oldPasswordField;
	}

	/**
	 * Set the oldPasswordField
	 * @param oldPasswordField oldPasswordField to set
	 */
	public void setOldPasswordField(PasswordField oldPasswordField) {
		this.oldPasswordField = oldPasswordField;
	}

	/**
	 * Get the newPasswordField
	 * @return PasswordField
	 */
	public PasswordField getNewPasswordField() {
		return newPasswordField;
	}

	/**
	 * Set the newPasswordField 
	 * @param newPasswordField newPasswordField to set
	 */
	public void setNewPasswordField(PasswordField newPasswordField) {
		this.newPasswordField = newPasswordField;
	}

	/**
	 * Get the confirmPasswordField
	 * @return PasswordField
	 */
	public PasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}

	/**
	 * Set the confirmPasswordField
	 * @param confirmPasswordField confirmPasswordField to set
	 */
	public void setConfirmPasswordField(PasswordField confirmPasswordField) {
		this.confirmPasswordField = confirmPasswordField;
	}

	/**
	 * Get errorLabelOldPassword
	 * @return Label
	 */
	public Label getErrorLabelOldPassword() {
		return errorLabelOldPassword;
	}

	/**
	 * Set the errorLabelOldPassword
	 * @param errorLabelPassword errorLabelPassword to set
	 */
	public void setErrorLabelOldPassword(Label errorLabelPassword) {
		this.errorLabelOldPassword = errorLabelPassword;
	}

	/**
	 * Get the errorLabelNewPassword
	 * @return Label
	 */
	public  Label getErrorLabelNewPassword() {
		return errorLabelNewPassword;
	}

	/**
	 * Set the errorLabelNewPassword
	 * @param errorLabelNewPassword errorLabelNewPassword to set
	 */
	public void setErrorLabelNewPassword(Label errorLabelNewPassword) {
		this.errorLabelNewPassword = errorLabelNewPassword;
	}

	/**
	 * Get the saveButton
	 * @return Button
	 */
	public Button getSaveButton() {
		return saveButton;
	}

	/**
	 * Set the saveButton
	 * @param saveButton saveButton to set
	 */
	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}
	

}
