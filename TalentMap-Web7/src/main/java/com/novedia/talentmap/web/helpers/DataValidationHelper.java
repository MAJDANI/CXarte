package com.novedia.talentmap.web.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IRegistrationService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.Constants;
import com.novedia.talentmap.web.utils.ConstantsDB;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.server.UserError;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;

public class DataValidationHelper {
	
	
	/**
	 * TalentMap service
	 */
	private IRegistrationService registrationService;
	private IColleagueService colleagueService;
	private IBusinessEngineerService businessEngineerService;

	ResourceBundle resourceBundle;

	public DataValidationHelper() {
	}

	private void initResourceBundle() {
		if(resourceBundle == null) {
			Locale locale = TalentMapApplication.getCurrent().getLocale();
			resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
		}
	}
    /**
     * If the field is not empty, checks the login doesn't already exist in data base.
     * If the login is found a Notification is sent to the user.
     */
    public boolean validateLogin(TextField fieldLogin) {
    	initResourceBundle();
	   	boolean isValid = true;
		String login;
		fieldLogin.setComponentError(null);
		if (fieldLogin != null && fieldLogin.getValue() != "") {
			//Size control
		    login = (String) fieldLogin.getValue();
		    try {
				int nbFound = registrationService.countLogin(login);
				if (nbFound > 0) {
				    String message = resourceBundle.getString("error.exists.login.field.1")
					    + login
					    + resourceBundle.getString("error.exists.login.field.2");
				    Notification.show(message);
				    fieldLogin.setComponentError(new UserError(message));
				    fieldLogin.focus();
				    isValid = false;
				}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
		return isValid;
    }

    /**
     * If the field is not empty, checks the size is between MIN and MAX defined. If not, a 
     * Notification is sent to the user.
     */
   public boolean validateLastName(TextField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		int min = Constants.COLLEAGUE_LAST_NAME_MIN_LENGTH;
		int max = ConstantsDB.COLLEAGUE_LAST_NAME_MAX_LENGTH;
		if (field != null && field.getValue() != "" && field.getValue() != null) {
			int size = field.getValue().length();
			if(size < min || size > max) {
				 String message = CUtils.buildMessageSizeBetween(resourceBundle, "error.size.name.field", 
						 min, max);
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		}
		return isValid;
    }

   /**
    * If the field is not empty, checks the size is between MIN and MAX defined. If not, a 
    * Notification is sent to the user.
    */
   public boolean validateFirstName(TextField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		int min = Constants.COLLEAGUE_FIRST_NAME_MIN_LENGTH;
		int max = ConstantsDB.COLLEAGUE_FIRST_NAME_MAX_LENGTH;
		if (field != null && field.getValue() != "" && field.getValue() != null) {
			int size = field.getValue().length();
			if(size < min || size > max) {
				 String message = CUtils.buildMessageSizeBetween(resourceBundle, "error.size.first.name.field", 
						 min, max);
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		}
		return isValid;
   }

   /**
    * If the field is not empty, checks first the REGEX. 
    * If the validation fails, a Notification is sent to the user.
    */
   public boolean validatePhone(TextField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		Pattern pattern = Pattern.compile(Constants.REGEX_PHONE_VALIDATION);
		if (field != null && field.getValue() != null && field.getValue().length() > 0) {
			Matcher m = pattern.matcher(field.getValue());
			boolean b = m.matches();
			if(!b) {
				 String message = resourceBundle.getString("error.malformed.phone.field");
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		}
		return isValid;
   }

   
   
   
   /**
    * If the field is not empty, checks first the REGEX. If the Pattern is ok
    * then it calls validateUniqueEmail(mail) to check if the mail is unique.
    * If the validation fails, a Notification is sent to the user.
    */
   public boolean validateEmail(TextField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		String mail = field.getValue();
		Pattern pattern = Pattern.compile(Constants.REGEX_EMAIL_VALIDATION);
		if (mail != null && !mail.equals("")) {
			Matcher m = pattern.matcher(field.getValue());
			boolean b = m.matches();
			if(!b) {
				 String message = resourceBundle.getString("error.malformed.email.field");
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			} else {
				isValid = validateUniqueEmail(field);
			}
		}
		return isValid;
   }

   /**
    * If the field is not empty, checks first the REGEX. If the Pattern is ok
    * then it calls validateUniqueEmail(mail) to check if the mail is unique.
    * If the validation fails, a Notification is sent to the user.
    */
   public boolean validateEmailForOtherCO(TextField field, Colleague colleague){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		String mail = field.getValue();
		Pattern pattern = Pattern.compile(Constants.REGEX_EMAIL_VALIDATION);
		if (mail != null && !mail.equals("")) {
			Matcher m = pattern.matcher(field.getValue());
			boolean b = m.matches();
			if(!b) {
				 String message = resourceBundle.getString("error.malformed.email.field");
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			} else {
				isValid = validateNoOtherEmail(field, colleague);
			}
		}
		return isValid;
   }
   /**
    * Checks if the mail given doesn't already exist in data base.
    * @param email : the email to check
    * @return true if the mail is unique
    */
   private boolean validateNoOtherEmail(TextField field, Colleague colleague) {
	   initResourceBundle();
	   	boolean isValid = true;
	   String email = field.getValue();
	   colleague.setEmail(email);
	   
	   //TODO FINIR ::
	   
		Integer nbColleagueFound = 0;
	    try {
	    	nbColleagueFound = colleagueService.countMailForColleagueId(colleague);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if (nbColleagueFound > 0) {
			String message = resourceBundle.getString("error.exists.email.field.1");
			message += email;
			message += resourceBundle.getString("error.exists.email.field.2");
			Notification.show(message);
			field.setComponentError(new UserError(message));
			isValid = false;
	    }
		return isValid;
   }

   /**
    * Checks if the mail given doesn't already exist in data base.
    * @param email : the email to check
    * @return true if the mail is unique
    */
   private boolean validateUniqueEmail(TextField field) {
	   	initResourceBundle();
	   	boolean isValid = true;
	   String email = field.getValue();
		Integer nbColleagueFound = 0;
	    try {
	    	nbColleagueFound = registrationService.countMail(email);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    if (nbColleagueFound > 0) {
			String message = resourceBundle.getString("error.exists.email.field.1");
			message += email;
			message += resourceBundle.getString("error.exists.email.field.2");
			Notification.show(message);
			field.setComponentError(new UserError(message));
			isValid = false;
	    }
		return isValid;
   }
   
   /**
    * If the field is not empty, checks first the REGEX. If the Pattern is ok
    * then it checks the value of experience isn't over the max defined by
    * Constants.COLLEAGUE_EXPERIENCE_MAX_YEARS. If the validation fails,
    * a Notification is sent to the user.
    */
   public boolean validateExperience(TextField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		Pattern pattern = Pattern.compile(Constants.REGEX_EXPERIENCE_VALIDATION);
		if (field != null && field.getValue() != null && field.getValue().length() >0) {
			Matcher m = pattern.matcher(field.getValue());
			boolean b = m.matches();
			if(!b) {
				 String message = resourceBundle.getString("error.malformed.experience.field");
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			} else {
				Integer experience = new Integer(field.getValue());
				if (experience > Constants.COLLEAGUE_EXPERIENCE_MAX_YEARS) {
					 String message = resourceBundle.getString("error.oversized.experience.field") + Constants.COLLEAGUE_EXPERIENCE_MAX_YEARS;
					 Notification.show(message);
					 field.setComponentError(new UserError(message));
					 isValid = false;
				}
			}
		} else {
			String message = resourceBundle.getString("experience.field.missing.msg");
			Notification.show(message);
			field.setComponentError(new UserError(message));
			isValid = false;
		}
		return isValid;
   }

   /**
    * Checks if the date given has a date format and is before Today
    * @param field : the date to check
    * @return true if the format of the date is OK
    */
   public boolean validateDateField(PopupDateField field) {
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		if (field != null && field.getValue() != null ) {
			Date date = field.getValue();
			Date today = Calendar.getInstance().getTime();
			if (date.after(today)) {
				 String message = resourceBundle.getString("error.date.entry.to.late.msg");
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		} else {
			String message = resourceBundle.getString("error.date.entry.invalid.msg");
			Notification.show(message);
			field.setComponentError(new UserError(message));
			isValid = false;
		}
		return isValid;
   }
 
   /**
    * Validates the size is between MIN and MAX defined. If not, a 
    * Notification is sent to the user.
    */
   public boolean validatePassword(PasswordField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		int min = Constants.COLLEAGUE_PASSWORD_MIN_LENGTH;
		int max = ConstantsDB.REGISTRATION_PASSWORD_MAX_LENGTH;
		if (field != null && field.getValue() != "" && field.getValue() != null) {
			int size = field.getValue().length();
			if(size < min || size > max) {
				 String message = CUtils.buildMessageSizeBetween(resourceBundle, "error.size.password.field", 
						 min, max);
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		}
		return isValid;
  }

   /**
    * Validates the size is between MIN and MAX defined. If not, a 
    * Notification is sent to the user.
    */
   public boolean validateConfirmPassword(PasswordField field){
	   	initResourceBundle();
	   	boolean isValid = true;
		field.setComponentError(null);
		int min = Constants.COLLEAGUE_PASSWORD_MIN_LENGTH;
		int max = ConstantsDB.REGISTRATION_PASSWORD_MAX_LENGTH;
		if (field != null && field.getValue() != "" && field.getValue() != null) {
			int size = field.getValue().length();
			if(size < min || size > max) {
				 String message = CUtils.buildMessageSizeBetween(resourceBundle, "error.size.password.field", 
						 min, max);
				 Notification.show(message);
				 field.setComponentError(new UserError(message));
				 isValid = false;
			}
		}
		return isValid;
  }

   
	/**
	 * @return the registrationService
	 */
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	/**
	 * @param registrationService the registrationService to set
	 */
	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	/**
	 * @return the colleagueService
	 */
	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	/**
	 * @param colleagueService the colleagueService to set
	 */
	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	/**
	 * @return the businessEngineerService
	 */
	public IBusinessEngineerService getBusinessEngineerService() {
		return businessEngineerService;
	}

	/**
	 * @param businessEngineerService the businessEngineerService to set
	 */
	public void setBusinessEngineerService(
			IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

    
    
}
