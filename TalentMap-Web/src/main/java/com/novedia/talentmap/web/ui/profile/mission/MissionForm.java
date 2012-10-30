package com.novedia.talentmap.web.ui.profile.mission;

import java.util.Date;
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
	 * 
	 */
	private static final long serialVersionUID = 6372102791482264327L;

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
	 *  Properties in Table ListMission
	 */
	public static final String INTITULE = "Intitulé";
	public static final String CLIENT = "Client";
	public static final String LIEU = "Lieu";
	public static final String DATE_DEBUT = "Date début";
	public static final String DATE_FIN = "Date fin";
	public static final String COMMENTAIRE = "Commentaire";

	//3	constants to identify which action is source of calling updateObservators()
	public static final String ACTION_CANCEL = "CANCEL";
	public static final String ACTION_SAVE = "SAVE";
	public static final String ACTION_DELETE = "DELETE";
	
	//2	constants to identify if "save" is an insert or update
	public static final String SAVE_MODE_UPDATE = "UPDATE";
	public static final String SAVE_MODE_INSERT = "INSERT";
	
	private String currentAction;
	private String currentSaveMode;
	
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
		// VGU
		// Set the form to act immediately on user input. This is
		// necessary for the validation of the fields to occur immediately
		// when the input focus changes and not just on commit.	
		this.missionForm.setImmediate(true);
		
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
		
		if(this.save == button){
			setCurrentAction(ACTION_SAVE);
			
			BeanItem<Mission> missionItem = (BeanItem<Mission>) this.missionForm.getItemDataSource();
			Mission missionToInsert = missionItem.getBean();
			
			
			if(isValidMission(missionToInsert)) {
				//TODO si données ok, insertion en base
				if(SAVE_MODE_INSERT == getCurrentSaveMode()) {
					missionToInsert.setCollab_id(COLLAB_ID);
					insertMission(missionToInsert);
				}
				if(SAVE_MODE_UPDATE == getCurrentSaveMode()) {
					updateMission(missionToInsert);
				}
			} else {
				getWindow().showNotification(
						"Les champs ne sont pas tous remplis",
						Notification.TYPE_ERROR_MESSAGE);
			}
		}
		
		if(this.cancel == button){
			setCurrentAction(ACTION_CANCEL);
			cancelInsertMission();
		}
		
	}

	/**
	 * Builds a TODO
	 * @author v.guillemain
	 * 
	 * @param itemMission
	 * @return Mission
	 */
	public Mission buildMissionFromItem(Item itemMission, Integer missionId) {

		String intitule = itemMission.getItemProperty(INTITULE).getValue().toString();
		String client = itemMission.getItemProperty(CLIENT).getValue().toString();
		String lieu = itemMission.getItemProperty(LIEU).getValue().toString();
		String dateDebut = itemMission.getItemProperty(DATE_DEBUT).getValue().toString();
		String dateFin = itemMission.getItemProperty(DATE_FIN).getValue().toString();
		String commentaire = null;
		if(itemMission.getItemProperty(COMMENTAIRE) != null && itemMission.getItemProperty(COMMENTAIRE).getValue() != null)
			commentaire = itemMission.getItemProperty(COMMENTAIRE).getValue().toString();
		//TODO MISSION
		Mission mission = new Mission(missionId, new Integer(2), intitule, lieu, client, commentaire, new Date(dateDebut), new Date(dateFin));
		return mission;
	}
	
	/**
	 * Builds an object Mission whith data given in parameters: itemMission and MissionId.
	 * The Mission object is then loaded in the mission form, to be displayed.
	 * @param itemMission 
	 * @param missionId
	 */
	public void fillMissionFormWithMission(Item itemMission, Integer missionId) {
		Mission missionToModify = buildMissionFromItem(itemMission, missionId);
		BeanItem<Mission> beanMissionToModify = new BeanItem<Mission>(missionToModify);
		this.missionForm.setItemDataSource(beanMissionToModify, this.fieldOrderMission);

		System.out.println(itemMission);

	}
	
	/**
	 * Empties all properties in missionForm
	 */
	public void emptyMissionForm() {
		BeanItem<Item> missionBean = new BeanItem(new Mission());//TODO
		this.missionForm.setItemDataSource(missionBean, this.fieldOrderMission);
	}
	
	
	/**
	 * Checks all mandatory mission's fields are not null
	 * @param mission
	 * @return boolean false if one or more values missing
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
		if (value == null || value.toString() == "" || value.toString().length() <2) {
			return false;
		} else {
			return true;
		}
	}


	/**
	 * Calls the CollaboratorService to insert the mission in Data Base. After the insert
	 * the list of missions in the table is updated with fresh data.
	 * @param missionToInsert
	 */
	private void insertMission(Mission missionToInsert) {
		try {
			int result = this.collabService.insertMission(missionToInsert); 
			if(result !=0){
				//TODO centraliser les messages
				CUtils.showMessage("La mission a bien été ajoutée. Result =" + result, Message.INFO, getWindow());
				
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO : que faire?
				CUtils.showMessage("La mission N'A PAS été ajoutée. Result =" + result, Message.INFO, getWindow());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Calls the CollaboratorService to update the mission in Data Base. After the insert
	 * the list of missions in the table is updated with fresh data.
	 * @param missionToUpdate
	 */
	private void updateMission(Mission missionToUpdate) {
		try {
			int result = this.collabService.updateMission(missionToUpdate);
			if(result !=0){
				//TODO centraliser les messages
				CUtils.showMessage("La mission a bien été mise à jour. Result =" + result, Message.INFO, getWindow());
				
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO : que faire?
				CUtils.showMessage("La mission N'A PAS été mise à jour. Result =" + result, Message.INFO, getWindow());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calls the CollaboratorService to delete the mission in Data Base. After the insert
	 * the list of missions in the table is updated with fresh data.
	 * @param missionToDelete
	 */
	public void deleteMission(int idMissionToDelete) {
		try {
//			int result = this.collabService.deleteMission(idMissionToDelete);
			
			if(this.collabService.deleteMission(idMissionToDelete)!=0){
				setCurrentAction(ACTION_DELETE);

				//TODO centraliser les messages
				CUtils.showMessage("La mission a bien été supprimée", Message.INFO, getWindow());
				
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO : que faire?
				CUtils.showMessage("La mission N'A PAS été supprimée", Message.INFO, getWindow());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cancelInsertMission() {
		this.updateObservateur();
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
	 * Gets the fieldOrderMission value
	 * 
	 * @return Vector<Object>
	 */
	public Vector<Object> getFieldOrderMission() {
		return this.fieldOrderMission;
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
		if(ACTION_CANCEL == currentAction) {
			this.obs.cancelAddMission();
		}
		if(ACTION_SAVE == currentAction) {
			this.obs.updateListMission(this.listMission);
		}
		if(ACTION_DELETE == currentAction) {
			this.obs.updateListMission(this.listMission);
		}
	}
	
	@Override
	public void delObservateur() {
		this.obs = null;
	}
	
	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public String getCurrentSaveMode() {
		return currentSaveMode;
	}

	public void setCurrentSaveMode(String currentSaveMode) {
		this.currentSaveMode = currentSaveMode;
	}

}
