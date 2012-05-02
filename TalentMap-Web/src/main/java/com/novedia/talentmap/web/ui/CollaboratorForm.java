package com.novedia.talentmap.web.ui;

import java.util.Vector;

import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.services.IProfileService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

public class CollaboratorForm extends Form implements ClickListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1195179317563179902L;
	
	/**
	 * Constants 
	 */
	private final String SAVE_CAPTION = new String("Enregistrer");
	private final String CANCEL_CAPTION = new String("Annuler");
	private Vector<Object> fieldOrder;
	public static final Object[] NAME_FIELD = new Object[]{"Nom", "Prénom", "Email", "Tél", "Date d'entrée Novedia", "Années d'expérience", "Ingénieur d'affaire","Profil"};
	public static final Object[] FIELD_ORDER = new Object[]{"last_name","first_name","email","phone","employment_date","experience","business_engineer","profile_id"};
	
	/**
	 * Vaadin Layout
	 */
	private HorizontalLayout hLayout;
	private GridLayout gLayout;
	
	/**
	 * Vaadin Buttons
	 */
	private Button save;
	private Button cancel;
	
	/**
	 * TalentMap service
	 */
	private ICollaboratorService collaboratorService;
	private IProfileService profileService;
	

	public CollaboratorForm(ICollaboratorService collaboratorService, IProfileService profileService, Button save, Button cancel, Vector<Object> fieldOrder, HorizontalLayout hLayout, GridLayout gLayout) {
		
		this.fieldOrder = fieldOrder;
		this.hLayout = hLayout;
		this.gLayout = gLayout;
		this.save = save;
		this.cancel = cancel;
		this.collaboratorService = collaboratorService;
		this.profileService = profileService;
		
		//Build Layout
		buildHorizontalLayout();
		buildGridLayout();
		
		//Build Buttons
		buildButtons();
		
		//Add Component to hLayout
		this.hLayout.addComponent(this.save);
		this.hLayout.addComponent(this.cancel);
		
		//Set content form
		setLayout(this.gLayout);
		
		try {
			
			//Set fields visibles
			setOrderForm(this.fieldOrder);
			setFormFieldFactory(new CollaboratorFormFieldFactory(this.profileService));
			
			
			@SuppressWarnings("unchecked")
			BeanItem<Item> collabBean = new BeanItem(this.collaboratorService.getCollaborator(1));
			
			setItemDataSource(collabBean,this.fieldOrder);
			
			//Set the good value for the Select Item
			int profileId = Integer.parseInt(this.collaboratorService.getCollaborator(1).getProfile_id());
			getField("profile_id").setValue(this.profileService.getProfile(profileId).getProfileType());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void buildHorizontalLayout(){
		this.hLayout.setMargin(true);
		this.hLayout.setSpacing(true);
	}
	
	private void buildGridLayout(){
		this.gLayout.setMargin(true);
		this.gLayout.setSpacing(true);
		this.gLayout.setColumns(4);
		this.gLayout.setRows(2);
	}
	
	private void buildButtons(){
		this.save.setCaption(SAVE_CAPTION);
		this.save.addListener((ClickListener) this);
		
		this.cancel.setCaption(CANCEL_CAPTION);
		this.cancel.addListener((ClickListener) this);
	}
	
	private void setOrderForm(Vector<Object> fieldOrder){
		
		for(Object field : FIELD_ORDER){
			fieldOrder.add(field);
		}
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		
	}

	/**
	 * Set the profileService value
	 * @param profileService the profileService to set
	 */
	public void setProfileService(ICollaboratorService profileService) {
		this.collaboratorService = profileService;
	}


	/**
	 * Set the save value
	 * @param save the save to set
	 */
	public void setSave(Button save) {
		this.save = save;
	}

	/**
	 * Set the cancel value
	 * @param cancel the cancel to set
	 */
	public void setCancel(Button cancel) {
		this.cancel = cancel;
	}
	
	/**
	 * Set the fieldOrder value
	 * @param fieldOrder the fieldOrder to set
	 */
	public void setFieldOrder(Vector<Object> fieldOrder) {
		this.fieldOrder = fieldOrder;
	}
	
	/**
	 * Set the hLayout value
	 * @param hLayout the hLayout to set
	 */
	public void sethLayout(HorizontalLayout hLayout) {
		this.hLayout = hLayout;
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
