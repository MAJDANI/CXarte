package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.commons.Constants;
import com.novedia.talentmap.web.ui.profile.CollaboratorForm;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
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
		
		
		
		for(int i=0; i<Constants.FIELD_ORDER_COLLABORATOR.length;i++){
			
			if(propertyId.equals(Constants.FIELD_ORDER_COLLABORATOR[i])){
				
				//We give a default format for all input except the employmentDate and the profileId input
				
				if(!propertyId.equals(Constants.FIELD_COLLAB_EMPLOYMENT_DATE) && !propertyId.equals(Constants.FIELD_COLLAB_PROFILE_ID)){
					
					TextField field = new TextField((String) Constants.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError(Constants.PROFILE_MSG_FIELD_REQUIRED_PART1 + Constants.NAME_FIELD_COLLABORATOR[i] + Constants.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setNullRepresentation(Constants.FIELD_NULL_REPRESENTATION);
					
					if(!propertyId.equals(Constants.FIELD_COLLAB_PHONE) && !propertyId.equals(Constants.FIELD_COLLAB_EXPERIENCE)){
						field.setReadOnly(true);
						field.setRequired(false);
					}
					
					//We test every input name
					
					if(propertyId.equals(Constants.FIELD_COLLAB_LAST_NAME)){
						field.setStyleName("last-name");
						
					}else if(propertyId.equals(Constants.FIELD_COLLAB_FIRST_NAME)){
						field.setStyleName("first-name");
						
					}else if(propertyId.equals(Constants.FIELD_COLLAB_EMAIL)){
						field.setStyleName("email");
						
					}else if(propertyId.equals(Constants.FIELD_COLLAB_PHONE)){
						field.setStyleName("phone");
						field.setRequired(false);
						
					}else if(propertyId.equals(Constants.FIELD_COLLAB_EXPERIENCE)){
						field.setStyleName("experience");
						
					}else if(propertyId.equals(Constants.FIELD_COLLAB_BUISINESS_ENGINEER)){
						field.setStyleName("business-engineer");
					}

					return field;
					
				}else if(propertyId.equals("employmentDate")){
					
					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) Constants.NAME_FIELD_COLLABORATOR[i]+" : ");
					datefield.setReadOnly(true);
					datefield.setStyleName("employment-date");
					
					return datefield;
					
				}else if(propertyId.equals("profileId")){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty("value", String.class, null);
					
					Select profilSelect = new Select((String) Constants.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					
					try {
						for(Profile p : profileService.getAllProfile()){
							item = ic.addItem(p.getId());
							item.getItemProperty("value").setValue(p.getType());
						}
						
						profilSelect.setContainerDataSource(ic);
						profilSelect.setItemCaptionPropertyId("value");
						
						profilSelect.setNullSelectionAllowed(false);
						profilSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//Add Validator
//					profilSelect.addValidator(new RegexpValidator("fonctionnel|technique", "Type de profil possible : fonctionnel ou technique."));
					
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
