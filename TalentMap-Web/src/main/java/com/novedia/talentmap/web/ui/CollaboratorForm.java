package com.novedia.talentmap.web.ui;

import java.util.Vector;

import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.IProfileService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;

/**
 * The form of Administrative Information of Collaborator
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class CollaboratorForm extends Form{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1195179317563179902L;
	
	/**
	 * Constants 
	 */
	private int COLLAB_ID = 2;
	private Vector<Object> fieldOrder;
	public static final Object[] NAME_FIELD = new Object[]{"Nom", "Prénom", "Email", "Tél", "Date d'entrée Novedia", "Années d'expérience", "Ingénieur d'affaire","Profil"};
	public static final Object[] FIELD_ORDER = new Object[]{"last_name","first_name","email","phone","employment_date","experience","business_engineer","profile_id"};
	
	/**
	 * Vaadin Layout
	 */
	private GridLayout gLayout;
	
	/**
	 * TalentMap service
	 */
	private ICollaboratorService collaboratorService;
	private IProfileService profileService;
	

	/**
	 * 
	 * Build the class CollaboratorForm.java 
	 * @param collaboratorService
	 * @param profileService
	 * @param fieldOrder
	 * @param gLayout
	 */
	public CollaboratorForm(ICollaboratorService collaboratorService, IProfileService profileService,Vector<Object> fieldOrder, GridLayout gLayout) {
		
		this.fieldOrder = fieldOrder;
		this.gLayout = gLayout;
		this.collaboratorService = collaboratorService;
		this.profileService = profileService;
		
		//Build Layout
		buildGridLayout();
		
		//Set content form
		setLayout(this.gLayout);
		
		try {
			
			//Set fields visibles
			setOrderForm(this.fieldOrder);
			setFormFieldFactory(new CollaboratorFormFieldFactory(this.profileService));
			
			
			@SuppressWarnings("unchecked")
			BeanItem<Item> collabBean = new BeanItem(this.collaboratorService.getCollaborator(COLLAB_ID));
			
			setItemDataSource(collabBean,this.fieldOrder);
			
			//Set the good value for the Select Item
			int profileId = Integer.parseInt(this.collaboratorService.getCollaborator(COLLAB_ID).getProfile_id());
			getField("profile_id").setValue(this.profileService.getProfile(profileId).getType());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Build the GridLayout
	 * @class CollaboratorForm.java
	 */
	private void buildGridLayout(){
		this.gLayout.setMargin(true);
		this.gLayout.setSpacing(true);
		this.gLayout.setColumns(4);
		this.gLayout.setRows(2);
	}
	
	/**
	 * Set the inputs order of the form
	 * @class CollaboratorForm.java
	 * @param fieldOrder
	 */
	private void setOrderForm(Vector<Object> fieldOrder){
		
		for(Object field : FIELD_ORDER){
			fieldOrder.add(field);
		}
	}

	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(ICollaboratorService profileService) {
		this.collaboratorService = profileService;
	}
	
	/**
	 * Set the fieldOrder value
	 * @param fieldOrder the fieldOrder to set
	 */
	public void setFieldOrder(Vector<Object> fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	/**
	 * Set the gLayout value
	 * @param gLayout the gLayout to set
	 */
	public void setgLayout(GridLayout gLayout) {
		this.gLayout = gLayout;
	}
	
	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(IProfileService profileService) {
		this.profileService = profileService;
	}

}
