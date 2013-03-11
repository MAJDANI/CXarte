package com.novedia.talentmap.web.ui.profile.mission;

import java.util.Date;
import java.util.Vector;

import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.MissionFormFieldFactory;
import com.novedia.talentmap.web.util.CUtils;
import com.novedia.talentmap.web.util.IMissionCollaboratorContent;
import com.novedia.talentmap.web.util.IObservable;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
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
	private IColleagueService collabService;
	
	private IClientService clientService;

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
	public static final int COLLEAGUE_ID = 2;
	public static final String SAVE_BUTTON_NAME = "Save";
	public static final String CANCEL_BUTTON_NAME = "Cancel";

	/**
	 *  Properties in Table ListMission
	 */
	public static final String INTITULE = "Title";
	public static final String CLIENT = "Customer";
	public static final String LIEU = "Place";
	public static final String DATE_DEBUT = "start Date";
	public static final String DATE_FIN = "End date";
	public static final String COMMENTAIRE = "Comment";

	//3	constants to identify which action is source of calling updateObservators()
	public static final String ACTION_CANCEL = "CANCEL";
	public static final String ACTION_SAVE = "SAVE";
	public static final String ACTION_DELETE = "DELETE";
	
	//2	constants to identify if "save" is an insert or update
	public static final String SAVE_MODE_UPDATE = "UPDATE";
	public static final String SAVE_MODE_INSERT = "INSERT";
	
	//3 constants to validate fields in MissionForm
	public static final int VALIDATION_FIELD_MISSING = 0;
	public static final int VALIDATION_INVALID_PERIOD = 1;
	public static final int VALIDATION_VALID_FORM = 2;
	
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
			GridLayout missionFormLayout, IColleagueService collabService, 
			IClientService clientService,
			Button save, Button cancel) {
		super();
		this.fieldOrderMission = fieldOrderMission;
		this.missionForm = missionForm;
		this.missionFormLayout = missionFormLayout;
		this.collabService = collabService;
		this.clientService = clientService;
		this.save = save;
		this.cancel = cancel;

		buildMain();
	}

	public void buildMain() {

		try {
			buildMissionLayout();
			buildMissionForm();
			buildButton();
			initMissionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initMissionList(){
		MissionContainer missionContainer = new MissionContainer(collabService);
		this.listMission = new ListMission(missionContainer);
	}

	public void buildMissionForm() throws Exception {

		this.missionForm.setLayout(this.missionFormLayout);

		CUtils.setOrderForm(this.fieldOrderMission, ConstantsEnglish.FIELD_ORDER_MISSION);
		
		this.missionForm.setFormFieldFactory(new MissionFormFieldFactory(this.clientService));
		
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
			
			int formValidation = validatedMissionForm(missionToInsert);
			switch (formValidation) {
			case VALIDATION_FIELD_MISSING :
				getWindow().showNotification(ConstantsEnglish.MSG_MISSING_FIELDS,
						Notification.TYPE_ERROR_MESSAGE);
				break;
			case VALIDATION_INVALID_PERIOD :
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_INVALID_PERIOD,
						Notification.TYPE_ERROR_MESSAGE);
				break;
			case VALIDATION_VALID_FORM :
				//Form's data are valid 
				if(SAVE_MODE_INSERT == getCurrentSaveMode()) {
					missionToInsert.setColleagueId(COLLEAGUE_ID);
					insertMission(missionToInsert);
				}
				if(SAVE_MODE_UPDATE == getCurrentSaveMode()) {
					updateMission(missionToInsert);
				}
				break;
			}
			
		}
		
		if(this.cancel == button){
			setCurrentAction(ACTION_CANCEL);
			cancelInsertMission();
		}
		
	}

	
	/**
	 * Builds an object Mission whith data given in parameters: itemMission and MissionId.
	 * The Mission object is then loaded in the mission form, to be displayed.
	 * @param itemMission 
	 * @param missionId
	 */
	public void fillMissionFormWithMission(Mission mission) {

		BeanItem<Mission> beanMissionToModify = new BeanItem<Mission>(mission);
		this.missionForm.setItemDataSource(beanMissionToModify, this.fieldOrderMission);

	}
	
	/**
	 * Empties all properties in missionForm
	 */
	public void emptyMissionForm() {
		BeanItem<Item> missionBean = new BeanItem(new Mission());//TODO
		this.missionForm.setItemDataSource(missionBean, this.fieldOrderMission);
	}
	
	
	/**
	 * Checks all mandatory mission's fields are not null and
	 * period is valid (begin date before end date)
	 * @param mission
	 * @return int : VALIDATION_FIELD_MISSING or VALIDATION_INVALID_PERIOD
	 * or VALIDATION_VALID_FORM
	 */
	private int validatedMissionForm (Mission mission) {
		
		if( 	!isNotEmpty(mission.getClient()) ||
				!isNotEmpty(mission.getTitle()) ||
				!isNotEmpty(mission.getPlace()) ||
				!isNotEmpty(mission.getClient()) ||
				!isNotEmpty(mission.getStartDate())  				
			) return VALIDATION_FIELD_MISSING;
		if(!isAValidPeriod(mission.getStartDate(),mission.getEndDate())
			) return VALIDATION_INVALID_PERIOD;
				
		return VALIDATION_VALID_FORM;
	}
	
	/**
	 * If end Date is specified, checks if startDate is before endDate. Without endDate
	 * the method considers the period valid.
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private boolean isAValidPeriod(Date startDate, Date endDate) {
		if((endDate == null) || ((endDate != null) && (endDate.after(startDate)))) return true;
		return false;
	}
	
	/**
	 * Check null values
	 * @param value
	 * @return false if the parameter value is null
	 */
	private boolean isNotEmpty(Object value) {
		if (value == null || value.toString() == "") {
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
			int result = this.collabService.addMission(missionToInsert); 
			if(result !=0){
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_DATA_INSERTED_OK, Notification.TYPE_TRAY_NOTIFICATION);
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO :  What to do in this case?
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_DATA_INSERTED_KO, Notification.TYPE_TRAY_NOTIFICATION);
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
			this.missionForm.commit();
			int result = this.collabService.saveMission(missionToUpdate);
			if(result !=0){
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_DATA_SAVED_OK, Notification.TYPE_TRAY_NOTIFICATION);
				
				//creates a new list
				refreshListMission();
				
			} else {
				//TODO : What to do in this case?
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_DATA_SAVED_KO, Notification.TYPE_ERROR_MESSAGE);
			}
				
		} catch (InvalidValueException invalidVE) {

		}
		
	}


	/**
	 * Calls the CollaboratorService to delete the mission in Data Base. After the insert
	 * the list of missions in the table is updated with fresh data.
	 * @param missionToDelete
	 */
	public void deleteMission(int idMissionToDelete) {
//		try {
////			int result = this.collabService.deleteMission(idMissionToDelete);
//			
//			if(this.collabService.deleteMission(idMissionToDelete)!=0){
//				setCurrentAction(ACTION_DELETE);
//
//				//TODO centraliser les messages
//				CUtils.showMessage("La mission a bien été supprimée", Message.INFO, getWindow());
//				
//				//creates a new list
//				refreshListMission();
//				
//			} else {
//				//TODO : que faire?
//				CUtils.showMessage("La mission N'A PAS été supprimée", Message.INFO, getWindow());
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private void cancelInsertMission() {
		this.updateObservateur();
	}

	/**
	 * This method creates a new object ListMission, filled with all elements founded
	 * in database, so a new mission created will be visible in the list 
	 */
	public void refreshListMission() {
		
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
	public void setCollabService(IColleagueService collabService) {
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

	public void updateObservateur(String currentAction) {
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
	public void updateObservateur() {
		if(ACTION_CANCEL == this.currentAction) {
			this.obs.cancelAddMission();
		}
		if(ACTION_SAVE == this.currentAction) {
			this.obs.updateListMission(this.listMission);
		}
		if(ACTION_DELETE == this.currentAction) {
			this.obs.updateListMission(this.listMission);
		}
	}
	
	@Override
	public void delObservateur() {
		this.obs = null;
	}
	
	public String getCurrentAction() {
		return this.currentAction;
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
