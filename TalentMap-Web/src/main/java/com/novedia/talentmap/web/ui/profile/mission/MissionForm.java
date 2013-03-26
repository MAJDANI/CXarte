package com.novedia.talentmap.web.ui.profile.mission;

import java.util.Date;
import java.util.Set;
import java.util.Vector;

import com.novedia.talentmap.model.dto.MissionDto;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;
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

@SuppressWarnings("serial")
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
	private IColleagueService collabService;
	
	private IClientService clientService;
	private ISkillService skillService;

	/**
	 * POJO
	 */
	private Vector<Object> fieldOrderMission;

	/**
	 * Vaadin Components
	 */
	private Form missionForm;
	private Button save;
	private Button cancel;

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
	public static final String OUTILS1 = "Outils n°1";
	public static final String OUTILS2 = "Outils n°2";
	public static final String OUTILS3 = "Outils n°3";

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
	public static final int VALIDATION_INVALID_SELECTION = 2;
	public static final int VALIDATION_VALID_FORM = 3;
	
	private String currentAction;
	private String currentSaveMode;
	
	
	private Authentication authentication;
	
	/**
	 * Default constructor
	 */
	public MissionForm(){
		super();
	}
	
	
	/**
	 * Build the form mission of colleague
	 * @return
	 */
	public MissionForm buildMissionFormColleague(){
		buildMain();
		return this;
	}
	
	/**
	 * Build the class MissionForm.java
	 * 
	 * @param fieldOrderMission
	 * @param missionForm
	 * @param missionFormLayout
	 */
//	public MissionForm(Vector<Object> fieldOrderMission, Form missionForm,
//			GridLayout missionFormLayout, IColleagueService collabService, 
//			IClientService clientService,
//			Button save, Button cancel) {
//		super();
//		this.fieldOrderMission = fieldOrderMission;
//		this.missionForm = missionForm;
//		this.missionFormLayout = missionFormLayout;
//		this.collabService = collabService;
//		this.clientService = clientService;
//		this.save = save;
//		this.cancel = cancel;
//
//		buildMain();
//	}

	
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
		//MissionContainer missionContainer = new MissionContainer(collabService);
		this.listMission = listMission.buildAllColleagueMission();
		//this.listMission = new ListMission(missionContainer);
	}

	public void buildMissionForm() throws Exception {
		//this.missionForm.setLayout(this.missionFormLayout);
		CUtils.setOrderForm(this.fieldOrderMission, ConstantsEnglish.FIELD_ORDER_MISSION);
		this.missionForm.setFormFieldFactory(new MissionFormFieldFactory(this.clientService,this.skillService,false));
		
		BeanItem<Item> missionBean = new BeanItem(new MissionDto());
		this.missionForm.setItemDataSource(missionBean, this.fieldOrderMission);
		this.missionForm.setImmediate(true);
		
		addComponent(this.missionForm);
	}
	
	public void buildMissionLayout(){
		GridLayout missionFormLayout = new GridLayout();
		missionFormLayout.setMargin(true);
		missionFormLayout.setSpacing(true);
		missionFormLayout.setColumns(3);
		missionFormLayout.setRows(3);
		missionForm.setLayout(missionFormLayout);
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
			
			BeanItem<MissionDto> missionItem = (BeanItem<MissionDto>) this.missionForm.getItemDataSource();
			MissionDto missionToInsert = missionItem.getBean();
			

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
			case VALIDATION_INVALID_SELECTION :
				getWindow().showNotification(ConstantsEnglish.MISSION_MSG_INVALID_SELECTION,
						Notification.TYPE_ERROR_MESSAGE);
				break;
			case VALIDATION_VALID_FORM :
				//Form's data are valid 
				if(SAVE_MODE_INSERT == getCurrentSaveMode()) {
					missionToInsert.setColleagueId(authentication.getColleagueId());
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
	public void fillMissionFormWithMission(MissionDto missionDto) {

		BeanItem<MissionDto> beanMissionToModify = new BeanItem<MissionDto>(missionDto);
		this.missionForm.setItemDataSource(beanMissionToModify, this.fieldOrderMission);

	}
	
	/**
	 * Empties all properties in missionForm
	 */
	public void emptyMissionForm() {
		BeanItem<Item> missionBean = new BeanItem(new MissionDto());//TODO
		this.missionForm.setItemDataSource(missionBean, this.fieldOrderMission);
	}
	
	
	/**
	 * Checks all mandatory mission's fields are not null and
	 * period is valid (begin date before end date)
	 * @param mission
	 * @return int : VALIDATION_FIELD_MISSING or VALIDATION_INVALID_PERIOD
	 * or VALIDATION_VALID_FORM
	 */
	private int validatedMissionForm (MissionDto mission) {
		
		if( 	!isNotEmpty(mission.getClient()) ||
				!isNotEmpty(mission.getTitle()) ||
				!isNotEmpty(mission.getPlace()) ||
				!isNotEmpty(mission.getClient()) ||
				!isNotEmpty(mission.getStartDate())  				
			) return VALIDATION_FIELD_MISSING;
		if(!isAValidPeriod(mission.getStartDate(),mission.getEndDate())
			) return VALIDATION_INVALID_PERIOD;
		if(!isAValidSelection(mission.getTools()))
			return VALIDATION_INVALID_SELECTION;
			
				
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
	 * Check if selected tools are between 1 and 3
	 * @param tools
	 * @return false if the parameter value is not between 1 and 3
	 */
	private boolean isAValidSelection(Set<Tool> tools) {
		if(tools!=null){
			if(tools.size()<=3)
				return true;
		}
		
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
	private void insertMission(MissionDto missionToInsert) {
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
	private void updateMission(MissionDto missionToUpdate) {
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




	private void cancelInsertMission() {
		this.updateObservateur();
	}

	/**
	 * This method creates a new object ListMission, filled with all elements founded
	 * in database, so a new mission created will be visible in the list 
	 */
	public void refreshListMission() {
		
		//creates a new list, filled with the elements in database
		//MissionContainer missionContainer = new MissionContainer(collabService);
		listMission = listMission.buildAllColleagueMission();
		//this.listMission = new ListMission(missionContainer);
		//"sends" the new list to the observer (so the observer will be able to
		//new list can be displayed
		this.updateObservateur();
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
			System.out.println("suppressiion");
			this.obs.updateListMission(this.listMission);
		}
	}
	
	@Override
	public void delObservateur() {
		this.obs = null;
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

	public ISkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}


	public Authentication getAuthentication() {
		return authentication;
	}


	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}


	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}


	public ListMission getListMission() {
		return listMission;
	}


	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}


	
	
	
}
