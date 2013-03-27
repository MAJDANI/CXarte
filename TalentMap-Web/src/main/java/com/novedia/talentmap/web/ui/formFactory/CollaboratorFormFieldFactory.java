package com.novedia.talentmap.web.ui.formFactory;

import com.novedia.talentmap.model.entity.BusinessEngineer;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IBusinessEngineerService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
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
	private IBusinessEngineerService businessEngineerService;
	private IColleagueService colleagueService;

	/**
	 * 
	 * Build the class CollaboratorFormFieldFactory.java 
	 * @param profileService
	 */
	public CollaboratorFormFieldFactory(IProfileService profileService, 
			IBusinessEngineerService businessEngineerService,
			IColleagueService colleagueService){
		this.profileService = profileService;
		this.businessEngineerService = businessEngineerService;
		this.colleagueService = colleagueService;
	}
	
	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		
		for(int i=0; i<ConstantsEnglish.FIELD_ORDER_COLLABORATOR.length;i++){
			
			if(propertyId.equals(ConstantsEnglish.FIELD_ORDER_COLLABORATOR[i])){
				
				//We give a default format for all input except the employmentDate, profileId input, Business Engineer and CM
				if(!propertyId.equals(ConstantsEnglish.FIELD_COLLAB_EMPLOYMENT_DATE) && !propertyId.equals(ConstantsEnglish.FIELD_COLLAB_PROFILE_ID)
						&& !propertyId.equals(ConstantsEnglish.FIELD_COLLAB_BUISINESS_ENGINEER) && !propertyId.equals(ConstantsEnglish.FIELD_COLLAB_MANAGER)){
					
					TextField field = new TextField((String) ConstantsEnglish.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					field.setRequired(true);
					field.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_COLLABORATOR[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);
					field.setNullRepresentation(ConstantsEnglish.FIELD_NULL_REPRESENTATION);
					
					//We test every input name
					
					if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_LAST_NAME)){
						field.setStyleName("last-name");
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_LAST_NAME_MAX_LENGTH);
					}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_FIRST_NAME)){
						field.setStyleName("first-name");
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_FIRST_NAME_MAX_LENGTH);
					}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_EMAIL)){
						field.setStyleName("email");
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_EMAIL_MAX_LENGTH);
					}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_PHONE)){
						field.setStyleName("phone");
						field.setRequired(false);
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_PHONE_MAX_LENGTH);
					}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_EXPERIENCE)){
						field.setStyleName("experience");
						field.setMaxLength(ConstantsEnglish.COLLEAGUE_EXPERIENCE_MAX_LENGTH);
					}

					return field;
					
				} else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_BUISINESS_ENGINEER)){		
						IndexedContainer ic = new IndexedContainer();
				        ic.addContainerProperty("value", String.class, null);
						
						Select bEngineerSelect = new Select((String) ConstantsEnglish.NAME_FIELD_COLLABORATOR[i]+" : ");
						
						try {
							for(BusinessEngineer businessEngineer : businessEngineerService.getAllBusinessEngineer()){
								item = ic.addItem(businessEngineer);
								item.getItemProperty("value").setValue(businessEngineer.getFirstName() + " " +businessEngineer.getLastName());
							}
							
							bEngineerSelect.setContainerDataSource(ic);
							bEngineerSelect.setItemCaptionPropertyId("value");
							
							bEngineerSelect.setNullSelectionAllowed(false);
							bEngineerSelect.setImmediate(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						return bEngineerSelect;		
					
					
				}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_EMPLOYMENT_DATE)){
					
					PopupDateField datefield = new PopupDateField();
					datefield.setDateFormat("dd/MM/yyyy");
					datefield.setCaption((String) ConstantsEnglish.NAME_FIELD_COLLABORATOR[i]+" : ");
					datefield.setStyleName("employment-date");
					datefield.setRequired(true);
					datefield.setRequiredError(ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART1 + ConstantsEnglish.NAME_FIELD_COLLABORATOR[i] + ConstantsEnglish.PROFILE_MSG_FIELD_REQUIRED_PART2);

					return datefield;
					
				}else if(propertyId.equals("profileId")){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty("value", String.class, null);
					
					Select profilSelect = new Select((String) ConstantsEnglish.NAME_FIELD_COLLABORATOR[i]+" : ");
					
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
					
					profilSelect.setStyleName("type-profile");
					return profilSelect;
					
				}else if(propertyId.equals(ConstantsEnglish.FIELD_COLLAB_MANAGER)){
					IndexedContainer ic = new IndexedContainer();
			        ic.addContainerProperty("value", String.class, null);
					
					Select managerSelect = new Select((String) ConstantsEnglish.NAME_FIELD_COLLABORATOR[i]+" : ");
					
					try {
						for(Manager manager : colleagueService.getAllManagers()){
							item = ic.addItem(manager.getId());
							item.getItemProperty("value").setValue(manager.getFirstName() + " " +manager.getLastName());
						}
						
						managerSelect.setContainerDataSource(ic);
						managerSelect.setItemCaptionPropertyId("value");
						
						managerSelect.setNullSelectionAllowed(false);
						managerSelect.setImmediate(true);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return managerSelect;		
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
	
	/**
	 * Set the businessEngineerService value
	 * @param businessEngineerService the businessEngineerService to set
	 */
	public void setBusinessEngineerService(IBusinessEngineerService businessEngineerService) {
		this.businessEngineerService = businessEngineerService;
	}

}
