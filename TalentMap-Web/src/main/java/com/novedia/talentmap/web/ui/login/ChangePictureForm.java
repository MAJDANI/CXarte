package com.novedia.talentmap.web.ui.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IChangePasswordService;
import com.novedia.talentmap.web.MyVaadinApplication;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.Message;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.terminal.FileResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.Window;

/**
 * Change picture form class
 * 
 * @author j.maquin
 * @version TMP 2.2
 */
@SuppressWarnings("serial")
public class ChangePictureForm extends Window {

    /**
     * the main application
     */
    private MyVaadinApplication myVaadinApplication;

    /**
     * authentication
     */
    private Authentication authentication;

    /**
     * vaadin componnent
     */
    private Upload upload;

    /**
     * Constant message
     */
    private static final String FORM_TITLE = "Change picture";

    /**
     * Default constructor
     */
    public ChangePictureForm() {
	super();
	setCaption(FORM_TITLE);
	setModal(true);
	addStyleName("changePasswordForm");
    }

    /**
     * Build the ChangePictureForm view
     * 
     * @return
     */
    public ChangePictureForm buildChangePictureFormView() {
	removeAllComponents();
	initForm();

	return this;
    }

    /**
     * init change password form
     */
    private void initForm() {
	upload = new Upload();
	upload.setButtonCaption("Start Upload");
	addComponent(upload);

	// Implement both receiver that saves upload in a file and
	// listener for successful upload
	class ImageUploader implements Receiver, SucceededListener {
	    public File file;

	    public OutputStream receiveUpload(String filename, String mimeType) {
		// Create upload stream
		FileOutputStream fos = null; // Stream to write to
		try {
		    filename = authentication.getToken().getLogin() + ".jpg";
		    // Open the file for writing.
		    file = new File("C:/Users/j.maquin/Desktop/tmp/" + filename);
		    fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
		    // Notification.show(
		    // "Could not open file<br/>", e.getMessage(),
		    // Notification.TYPE_ERROR_MESSAGE);
		    return null;
		}
		return fos; // Return the output stream to write to
	    }

	    public void uploadSucceeded(SucceededEvent event) {
		close();
	    }
	}
	;
	final ImageUploader uploader = new ImageUploader();
	upload.setReceiver(uploader);
	upload.addListener(uploader);
    }

    /**
     * Get the main application
     * 
     * @return
     */
    public MyVaadinApplication getMyVaadinApplication() {
	return myVaadinApplication;
    }

    /**
     * Set the main application
     * 
     * @param myVaadinApplication
     */
    public void setMyVaadinApplication(MyVaadinApplication myVaadinApplication) {
	this.myVaadinApplication = myVaadinApplication;
    }

    /**
     * get the authentication's user
     * 
     * @return authentication
     */
    public Authentication getAuthentication() {
	return authentication;
    }

    /**
     * Set the authentication
     * 
     * @param authentication
     *            authentication to set
     */
    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

}