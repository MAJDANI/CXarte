package com.novedia.talentmap.web.login;

import com.novedia.talentmap.services.IChangePasswordService;
import com.novedia.talentmap.web.utils.ComponentsId;
import com.novedia.talentmap.web.utils.Constants;
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
	 * 
	 */
	 private IChangePasswordService changePasswordService;
	 
	 /**
	  * 
	  */
	 private FormLayout changePasswordFormLayout;
	 /**
	  * 
	  */
	 private PasswordField oldPasswordField;
	 /**
	  * 
	  */
     private PasswordField newPasswordField;
     /**
      * 
      */
     private PasswordField confirmPasswordField;
     /**
      * 
      */
     private Label errorLabelPassword;
     /**
      * 
      */
     private Label errorLabelNewPassword;
     
     /**
      * 
      */
     private Button saveButton;
	
	
     public ChangePasswordScreen(){
    	 super();
    	 setModal(true);
     }
	
	public Window buildChangePasswordForm(){
		removeAllComponents();
		buildForm();
		errorLabelPassword.setCaption(Constants.ERROR_OLD_PASSWORD);
		errorLabelNewPassword.setCaption(Constants.ERROR_NEW_PASSWORD);
		errorLabelPassword.setVisible(false);
		errorLabelNewPassword.setVisible(false);
		
		addComponent(errorLabelPassword);
		addComponent(errorLabelNewPassword);
		addComponent(changePasswordFormLayout);
		return this;
		
	}
	
	/**
	 * Build changepassword form
	 */
	private void buildForm(){
		changePasswordFormLayout.removeAllComponents();
		
		oldPasswordField.setCaption(Constants.OLD_PASSWORD_LABEL);
		oldPasswordField.setId(ComponentsId.OLD_PASSWORD_FIELD_ID);
		oldPasswordField.setRequired(true);
		
		newPasswordField.setCaption(Constants.NEW_PASSWORD_LABEL);
		newPasswordField.setId(ComponentsId.NEW_PASSWORD_FIELD_ID);
		newPasswordField.setRequired(true);
		
		confirmPasswordField.setCaption(Constants.CONFIRM_PASSWORD_LABEL);
		confirmPasswordField.setId(ComponentsId.CONFIRM_PASSWORD_FIELD_ID);
		confirmPasswordField.setRequired(true);
		
		saveButton.setCaption(Constants.SAVE_BUTTON_LABEL);
		saveButton.addClickListener(this);
		changePasswordFormLayout.addComponent(oldPasswordField);
		changePasswordFormLayout.addComponent(newPasswordField);
		changePasswordFormLayout.addComponent(confirmPasswordField);
		changePasswordFormLayout.addComponent(saveButton);
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(saveButton)) { 
			String oldPassword = (String) oldPasswordField.getValue();
			String newPassword = (String) newPasswordField.getValue();
			String confirmPassword = (String) confirmPasswordField.getValue();
			if(oldPassword.length() == 0 || newPassword.length() == 0 || confirmPassword.length() == 0){
				Notification.show(Constants.MISSING_FIELD_MSG, Type.WARNING_MESSAGE);
				return;
			}
			
			
		}
	}

	
	
	
	public IChangePasswordService getChangePasswordService() {
		return changePasswordService;
	}

	public void setChangePasswordService(
			IChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	public FormLayout getChangePasswordFormLayout() {
		return changePasswordFormLayout;
	}

	public void setChangePasswordFormLayout(FormLayout changePasswordFormLayout) {
		this.changePasswordFormLayout = changePasswordFormLayout;
	}

	public PasswordField getOldPasswordField() {
		return oldPasswordField;
	}

	public void setOldPasswordField(PasswordField oldPasswordField) {
		this.oldPasswordField = oldPasswordField;
	}

	public PasswordField getNewPasswordField() {
		return newPasswordField;
	}

	public void setNewPasswordField(PasswordField newPasswordField) {
		this.newPasswordField = newPasswordField;
	}

	public PasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}

	public void setConfirmPasswordField(PasswordField confirmPasswordField) {
		this.confirmPasswordField = confirmPasswordField;
	}

	public Label getErrorLabelPassword() {
		return errorLabelPassword;
	}

	public void setErrorLabelPassword(Label errorLabelPassword) {
		this.errorLabelPassword = errorLabelPassword;
	}

	public  Label getErrorLabelNewPassword() {
		return errorLabelNewPassword;
	}

	public void setErrorLabelNewPassword(Label errorLabelNewPassword) {
		this.errorLabelNewPassword = errorLabelNewPassword;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}
	
	

}
