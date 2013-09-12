package com.novedia.talentmap.web.ui.login;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IChangePasswordService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.Message;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;

/**
 * Change password form class
 * 
 * @author b.tiomofofou
 * @version TMP 2.1
 */
@SuppressWarnings("serial")
public class ChangePasswordForm extends Window implements ClickListener {

    /**
     * authentication
     */
    private Authentication authentication;

    /**
     * changePasswordService
     */
    private IChangePasswordService changePasswordService;

    /**
     * the main application
     */
    private MyVaadinApplication myVaadinApplication;

    /**
     * vaadin componnent
     */
    private Button saveButton;
    private Button cancelButton;
    private Form changePasswordForm;
    private PasswordField oldPasswordField;
    private PasswordField newPasswordField;
    private PasswordField confirmPasswordField;
    private static Label errorLabelPassword;
    private static Label errorLabelNewPassword;

    /**
     * Constant message
     */
    private static final String LABEL_OLD_PASSWORD_FIELD = "Old Password";
    private static final String LABEL_NEW_PASSWORD_FIELD = "New Password";
    private static final String LABEL_CONFIRM_PASSWORD_FIELD = "Retype new Password";
    private static final String FORM_TITLE = "Change password Form";
    private static final String MISSING_FIELD_MSG = "All fields are required";
    private static final String UPADTE_SUCCESS_MSG = "Update successful";
    private static final String UPDATE_ERROR_MSG = "Update error";
    private static final String ERROR_OLD_PASSWORD = "Error : You misspelled your old password !!";
    private static final String ERROR_NEW_PASSWORD = "Error : New password fields do not match !!";

    /**
     * Default constructor
     */
    public ChangePasswordForm() {
	super();
	setCaption(FORM_TITLE);
	setReadOnly(true);
	setModal(true);
	addStyleName("changePasswordForm");
    }

    /**
     * Build the ChangePasswordForm view
     * 
     * @return
     */
    public final ChangePasswordForm buildChangePasswordFormView() {
	removeAllComponents();
	initForm();
	errorLabelPassword = new Label(ERROR_OLD_PASSWORD);
	errorLabelNewPassword = new Label(ERROR_NEW_PASSWORD);
	errorLabelPassword.setVisible(false);
	errorLabelNewPassword.setVisible(false);
	errorLabelPassword.addStyleName("errorLabel");
	errorLabelNewPassword.addStyleName("errorLabel");
	addComponent(errorLabelPassword);
	addComponent(errorLabelNewPassword);
	addComponent(changePasswordForm);
	return this;
    }

    /**
     * init change password form
     */
    private void initForm() {
	changePasswordForm = new Form();
	oldPasswordField = new PasswordField(LABEL_OLD_PASSWORD_FIELD);
	oldPasswordField.setRequired(true);

	newPasswordField = new PasswordField(LABEL_NEW_PASSWORD_FIELD);
	newPasswordField.setRequired(true);

	confirmPasswordField = new PasswordField(LABEL_CONFIRM_PASSWORD_FIELD);
	confirmPasswordField.setRequired(true);

	changePasswordForm.addField(LABEL_OLD_PASSWORD_FIELD, oldPasswordField);
	changePasswordForm.addField(LABEL_NEW_PASSWORD_FIELD, newPasswordField);
	changePasswordForm.addField(LABEL_CONFIRM_PASSWORD_FIELD,
		confirmPasswordField);

	saveButton = new Button(ConstantsEnglish.LABEL_SAVE_BUTTON);
	cancelButton = new Button(ConstantsEnglish.LABEL_CANCEL_BUTTON);
	saveButton.addListener(this);
	cancelButton.addListener(this);

	HorizontalLayout footerForm = new HorizontalLayout();
	footerForm.addStyleName("footerButton");
	footerForm.addComponent(saveButton);
	footerForm.addComponent(cancelButton);
	changePasswordForm.setFooter(footerForm);

    }

    @Override
    public final void buttonClick(final ClickEvent event) {
	if (event.getButton().equals(cancelButton)) {
	    close();
	    return;
	}
	if (event.getButton().equals(saveButton)) {
	    try {
		changePasswordForm.validate();
		String oldPassword = (String) oldPasswordField.getValue();
		String newPassword = (String) newPasswordField.getValue();
		String confirmPassword = (String) confirmPasswordField
			.getValue();
		if (CUtils.encodePassword(oldPassword).equals(
			authentication.getToken().getPassword())) {
		    if (newPassword.equals(confirmPassword)) {
			String encodeNewPassword = CUtils
				.encodePassword(newPassword);
			Authentication authenticationTmp = new Authentication();
			CredentialToken credentialTmp = new CredentialToken();
			credentialTmp.setLogin(this.authentication.getToken()
				.getLogin());
			credentialTmp.setPassword(encodeNewPassword);
			authenticationTmp.setColleagueId(this.authentication
				.getColleagueId());
			authenticationTmp.setToken(credentialTmp);
			int result = changePasswordService
				.savePassword(authenticationTmp);
			if (result != 0) {
			    this.authentication.getToken().setPassword(
				    encodeNewPassword);
			    close();
			    CUtils.showMessage(UPADTE_SUCCESS_MSG,
				    Message.INFO, getMyVaadinApplication()
					    .getMainWindow());
			} else {
			    close();
			    CUtils.showMessage(UPDATE_ERROR_MSG, Message.ERROR,
				    getMyVaadinApplication().getMainWindow());
			}

		    } else {
			errorLabelPassword.setVisible(false);
			errorLabelNewPassword.setVisible(true);
		    }

		} else {
		    errorLabelPassword.setVisible(true);
		    errorLabelNewPassword.setVisible(false);
		}

	    } catch (InvalidValueException e) {
		getWindow().showNotification(MISSING_FIELD_MSG,
			Notification.TYPE_ERROR_MESSAGE);
	    }
	    return;
	}

    }

    /**
     * get the authentication's user
     * 
     * @return authentication
     */
    public final Authentication getAuthentication() {
	return authentication;
    }

    /**
     * Set the authentication
     * 
     * @param authentication
     *            authentication to set
     */
    public final void setAuthentication(final Authentication authentication) {
	this.authentication = authentication;
    }

    /**
     * Get the password service
     * 
     * @return changePasswordService
     */
    public final IChangePasswordService getChangePasswordService() {
	return changePasswordService;
    }

    /**
     * Set the password service
     * 
     * @param changePasswordService
     *            password service to set
     */
    public final void setChangePasswordService(
	    final IChangePasswordService changePasswordService) {
	this.changePasswordService = changePasswordService;
    }

    /**
     * Get the main application
     * 
     * @return
     */
    public final MyVaadinApplication getMyVaadinApplication() {
	return myVaadinApplication;
    }

    /**
     * Set the main application
     * 
     * @param myVaadinApplication
     */
    public final void setMyVaadinApplication(
	    final MyVaadinApplication myVaadinApplication) {
	this.myVaadinApplication = myVaadinApplication;
    }

}
