package com.novedia.talentmap.web.ui.profile.mission;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.web.ui.formFactory.MissionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.Message;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

public class MissionForm extends FormLayout implements ClickListener {

	/**
	 * Talent Map Service
	 */
	private ICollaboratorService collabService;

	/**
	 * POJO
	 */
	private Vector<Object> fieldOrderMission;

	/**
	 * Vaadin Components
	 */
	private Form missionForm;
	private GridLayout missionFormLayout;
	private Button save;
	private Button cancel;

	/**
	 * Constants
	 */
	public static final int COLLAB_ID = 2;
	public static final Object[] NAME_FIELD_MISSION = new Object[] { "Intitulé", "Client",
			"Lieu", "Début mission", "Fin mission", "Commentaire" };
	public static final Object[] FIELD_ORDER_MISSION = new Object[] { "name", "client",
			"place", "start_date", "end_date", "notes" };
	public static final String SAVE_BUTTON_NAME = "Enregistrer";
	public static final String CANCEL_BUTTON_NAME = "Annuler";
	
	/**
	 * Build the class MissionForm.java
	 * 
	 * @param fieldOrderMission
	 * @param missionForm
	 * @param missionFormLayout
	 */
	public MissionForm(Vector<Object> fieldOrderMission, Form missionForm,
			GridLayout missionFormLayout, ICollaboratorService collabService,
			Button save, Button cancel) {
		super();
		this.fieldOrderMission = fieldOrderMission;
		this.missionForm = missionForm;
		this.missionFormLayout = missionFormLayout;
		this.collabService = collabService;
		this.save = save;
		this.cancel = cancel;

		buildMain();
	}

	public void buildMain() {

		try {
			
			buildMissionLayout();
			
			buildMissionForm();
			
			buildButton();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void buildMissionForm() throws Exception {

		this.missionForm.setLayout(this.missionFormLayout);

		CUtils.setOrderForm(this.fieldOrderMission, FIELD_ORDER_MISSION);
		
		this.missionForm.setFormFieldFactory(new MissionFormFieldFactory());
		
		BeanItem<Item> missionBean = new BeanItem(new Mission());
		this.missionForm.setItemDataSource(missionBean, this.fieldOrderMission);
		

		addComponent(this.missionForm);
	}
	
	public void buildMissionLayout(){
		
		this.missionFormLayout.setMargin(true);
		this.missionFormLayout.setSpacing(true);
		this.missionFormLayout.setColumns(3);
		this.missionFormLayout.setRows(2);
	}
	
	public void buildButton(){
		
		this.save.setCaption(SAVE_BUTTON_NAME);
		this.cancel.setCaption(CANCEL_BUTTON_NAME);
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setMargin(true);
		hLayout.setSpacing(true);
		hLayout.addComponent(this.save);
		hLayout.addComponent(this.cancel);
		this.missionForm.setFooter(hLayout);
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		
		Button button = event.getButton();
		System.out.println("MissionForm.buttonClick");
		
		if(this.save == button){
			System.out.println("bouton save");
			
			BeanItem<Mission> missionItem = (BeanItem<Mission>) this.missionForm.getItemDataSource();
			System.out.println("missionItem=" + missionItem);
			Mission missionAdded = missionItem.getBean();
			System.out.println("missionAdded=" + missionAdded);
			
			//Voir Mission dans model  intervertir String => Integer
			missionAdded.setCollab_id(COLLAB_ID);
			try {
				
				if(this.collabService.addMission(missionAdded)!=0){
					CUtils.showMessage("La mission a bien été ajoutée", Message.INFO, getWindow());
				}
				
			} catch (Exception e) {
				
				
				e.printStackTrace();
			}
		}
		
		if(this.cancel == button){
			
			
		}
		
	}
	
	/**
	 * Set the fieldOrderMission value
	 * 
	 * @param fieldOrderMission
	 *            the fieldOrderMission to set
	 */
	public void setFieldOrderMission(Vector<Object> fieldOrderMission) {
		this.fieldOrderMission = fieldOrderMission;
	}

	/**
	 * Set the missionForm value
	 * 
	 * @param missionForm
	 *            the missionForm to set
	 */
	public void setMissionForm(Form missionForm) {
		this.missionForm = missionForm;
	}

	/**
	 * Set the missionFormLayout value
	 * 
	 * @param missionFormLayout
	 *            the missionFormLayout to set
	 */
	public void setMissionFormLayout(GridLayout missionFormLayout) {
		this.missionFormLayout = missionFormLayout;
	}

	/**
	 * Set the collabService value
	 * 
	 * @param collabService
	 *            the collabService to set
	 */
	public void setCollabService(ICollaboratorService collabService) {
		this.collabService = collabService;
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
	 * Get the missionForm value
	 * @return the missionForm
	 */
	public Form getMissionForm() {
		return missionForm;
	}

}
