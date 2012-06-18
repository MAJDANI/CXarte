package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.vaadin.data.Item;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;

/**
 * The Factory of the Collaborator Form
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class CollaboratorFormFieldFactory implements FormFieldFactory {
	
	private static final long serialVersionUID = 1L;
	
	private IProfileService profileService;

	/**
	 * 
	 * Build the class CollaboratorFormFieldFactory.java 
	 * @param profileService
	 */
	public CollaboratorFormFieldFactory(IProfileService profileService){
		this.profileService = profileService;
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		
		for(int i=0; i<CollaboratorForm.FIELD_ORDER_COLLABORATOR.length;i++){
			
			if(propertyId.equals(CollaboratorForm.FIELD_ORDER_COLLABORATOR[i])){
				
				//We give a default format for all input except the employment_date and the profile_id input
				
				if(!propertyId.equals("employment_date") && !propertyId.equals("profile_id")){
					
					TextField field = new TextField((String) CollaboratorForm.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError("Le champs \""+ CollaboratorForm.NAME_FIELD_COLLABORATOR[i]+"\" est obligatoire");
					
					if(!propertyId.equals("phone") && !propertyId.equals("experience")){
						field.setReadOnly(true);
					}
					
					//We test every input name
					
					if(propertyId.equals("last_name")){
						field.setStyleName("last-name");
						
					}else if(propertyId.equals("first_name")){
						field.setStyleName("first-name");
						
					}else if(propertyId.equals("email")){
						field.setStyleName("email");
						
					}else if(propertyId.equals("phone")){
						field.setStyleName("phone");
						field.setRequired(false);
						
					}else if(propertyId.equals("experience")){
						field.setStyleName("experience");
						
					}else if(propertyId.equals("business_engineer")){
						field.setStyleName("business-engineer");
					}

					return field;
					
				}else if(propertyId.equals("employment_date")){
					
					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) CollaboratorForm.NAME_FIELD_COLLABORATOR[i]+" : ");
					datefield.setReadOnly(true);
					datefield.setStyleName("employment-date");
					
					return datefield;
					
				}else if(propertyId.equals("profile_id")){
					
					Select profilSelect = new Select((String) CollaboratorForm.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					try {
						for(Profile p : profileService.getAllProfile()){
							profilSelect.addItem(p.getType());
						}
						
						profilSelect.setNullSelectionAllowed(false);
						profilSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//Add Validator
					profilSelect.addValidator(new RegexpValidator("fonctionnel|technique", "Type de profil possible : fonctionnel ou technique."));
					
					profilSelect.setStyleName("type-profile");
					return profilSelect;
				}
				
			}
		}
		
		return null;
	}
	
	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

}
