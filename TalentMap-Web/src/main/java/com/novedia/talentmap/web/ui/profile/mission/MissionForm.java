package com.novedia.talentmap.web.ui.profile.mission;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.web.ui.formFactory.MissionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.Message;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window.Notification;

public class MissionForm extends FormLayout implements ClickListener, IObservable {

	/**
	* We add listMission in AddMissionPanel's attributes in order to
	* be able to refresh the list listMission when a new mission is
	* created or when a mission is modified
	 */
	private ListMission listMission;
	
	/**
	 * Declare the observer. The observer will be used when an action
	 * in AddMissionPanel will require update data in ListPanel
	 */
	private IMissionCollaboratorContent obs;

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
		this.save.addListener(this);
		
		this.cancel.setCaption(CANCEL_BUTTON_NAME);
		this.cancel.addListener(this);
		
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
			
			BeanItem<Mission> missionItem = (BeanItem<Mission>) this.missionForm.getItemDataSource();
			Mission missionToInsert = missionItem.getBean();

			if(isValidMission(missionToInsert)) {
				//TODO si données ok, insertion en base

				missionToInsert.setCollab_id(COLLAB_ID);
				insertMission(missionToInsert);
				
			} else {
				getWindow().showNotification(
						"Les champs ne sont pas tous remplis",
						Notification.TYPE_ERROR_MESSAGE);
			}
		}
		
		if(this.cancel == button){
			//TODO vider les champs du formulaire de saisie
//			System.out.println("MissionForm.ButtonClick() = cancel");
//			this.missionForm.setItemDataSource(null);
			
			
			//TODO faire disparaître le formulaire de saisie
//			this.missionForm.removeAllProperties();//???????
			//TODO faire disparaître des boutons

			//TODO rendre accessible addMissionButton
//			MissionCollaboratorContent.this.addMissionButton.setEnabled(true);
			
			
		}
		
	}

	/**
	 * Checks all mandatory mission's fields are not null
	 * @param mission
	 * @return false if one or more values missing
	 */
	private boolean isValidMission (Mission mission) {
		if( 	!isValidField(mission.getClient()) ||
				!isValidField(mission.getName()) ||
				!isValidField(mission.getPlace()) ||
				!isValidField(mission.getClient()) ||
				!isValidField(mission.getStart_date()) ||
				!isValidField(mission.getEnd_date()) 				
			) return false;
		return true;
	}
	
	/**
	 * Check null values
	 * @param value
	 * @return false if the parameter value is null
	 */
	private boolean isValidField(Object value) {
		if (value == null) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * Calls the CollaboratorService to insert the mission in Data Base
	 * @param missionToInsert
	 */
	private void insertMission(Mission missionToInsert) {
		try {
			if(this.collabService.insertMission(missionToInsert)!=0){
				//TODO centraliser les messages
				CUtils.showMessage("La mission a bien été ajoutée", Message.INFO, getWindow());
				
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO : que faire?
				CUtils.showMessage("La mission N'A PAS été ajoutée", Message.INFO, getWindow());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method creates a new object ListMission, filled with all elements founded
	 * in database, so a new mission created will be visible in the list 
	 */
	private void refreshListMission() {
		
		//creates a new list, filled with the elements in database
		MissionContainer missionContainer = new MissionContainer(collabService);
		this.listMission = new ListMission(missionContainer);
		
		//"sends" the new list to the observer (so the observer will be able to
		//new list can be displayed
		this.updateObservateur();
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

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		this.obs = (IMissionCollaboratorContent) observateur;
	}

	@Override
	public void updateObservateur() {
		System.out.println("MissionForm.updateObservateur this.listMission=" + this.listMission);
		this.obs.updateListMission(this.listMission);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
	

}
