package com.novedia.talentmap.web.ui.collab;

import java.util.Vector;

import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.formFactory.EditMissionFormFieldFactory;
import com.novedia.talentmap.web.ui.profile.mission.ListMission;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The {@link ListMissionCollabWindow} is the main window for render missions of
 * colleagues.
 * 
 * @author VGU
 * 
 */
@SuppressWarnings("serial")
public class ListMissionCollabWindow extends Window implements
		ItemClickListener {

	/**
	 * Vaadin Components
	 */
	private ListMission listMission;
	private Form missionForm;
	private GridLayout missionFormLayout;
	private VerticalLayout vLayout;
	private Vector<Object> fieldOrderMission;

	/**
	 * Colleague concerned
	 */
	private Colleague currentColleague;

	private Integer roleId;

	/**
	 * Default constructor
	 */
	public ListMissionCollabWindow() {
		super();
	}

	/**
	 * The main builder
	 * 
	 * @class ProfileCollabWindow.java
	 */
	public void mainBuild() {
		setModal(true);
		center();
		this.setWidth("1135");
		removeAllComponents();
		buildCaption();
		buildListMissionWindow();
	}

	/**
	 * Builds the caption of the window with the Name of current colleague
	 * 
	 * @return
	 */
	private void buildCaption() {
		String caption = ConstantsEnglish.LIST_MISSION_WINDOW_TITLE
				+ currentColleague.getLastName() + " "
				+ currentColleague.getFirstName() + " :";
		this.setCaption(caption);
	}

	/**
	 * Build the missionForm Layout
	 */
	private void buildMissionFormLayout() {
		this.missionFormLayout = new GridLayout();
		this.missionFormLayout.setMargin(true);
		this.missionFormLayout.setSpacing(true);
		this.missionFormLayout.setColumns(3);
		this.missionFormLayout.setRows(3);
	}

	private void buildListMissionWindow() {
		vLayout = new VerticalLayout();

		this.listMission.setColleagueId(currentColleague.getId());
		ListMission listMissionResult = this.listMission
				.buildAllColleagueMission();
		if (listMissionResult.size() > 0) {
			// Le profil CM a le droit d'afficher le détail d'une mission
			if (Authorization.Role.CM.getId().equals(roleId)) {
				this.missionForm = new Form();
				buildMissionFormLayout();
				this.missionForm.setLayout(missionFormLayout);
				this.fieldOrderMission = new Vector<Object>();
				this.vLayout.addComponent(missionForm);
				this.missionForm.setVisible(false);
				this.addComponent(vLayout);
				this.addComponent(new Label(
						ConstantsEnglish.MSG_EDIT_MISSION_ON_CLICK));
				listMissionResult.addListener(this);
			} else {
				listMissionResult.setReadOnly(true);
			}
			this.addComponent(listMissionResult);
		} else {
			vLayout.addComponent(new Label(
					ConstantsEnglish.LIST_MISSION_WINDOW_NO_MISSIONS));
			this.addComponent(vLayout);
		}
	}

	public void setListMission(ListMission listMission) {
		this.listMission = listMission;
	}

	public Colleague getCurrentColleague() {
		return currentColleague;
	}

	public void setCurrentColleague(Colleague currentColleague) {
		this.currentColleague = currentColleague;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRole(Integer roleId) {
		this.roleId = roleId;
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		Mission selectedMission = (Mission) event.getItemId();
		initFormColleagueMission(selectedMission);
		this.missionForm.setVisible(true);
		this.vLayout.setVisible(true);
	}

	/**
	 * Init the value of the mission form with last mission datas
	 * 
	 * @param missionDto
	 */
	private void initFormColleagueMission(Mission selectedMission) {
		if (selectedMission != null) {
			BeanItem<Item> missionBean = new BeanItem(selectedMission);
			this.missionForm
					.setFormFieldFactory(new EditMissionFormFieldFactory(
							selectedMission));

			CUtils.setOrderForm(this.fieldOrderMission,
					ConstantsEnglish.FIELD_ORDER_MISSION);

			this.missionForm.setItemDataSource(missionBean,
					this.fieldOrderMission);
			this.missionForm.setReadOnly(true);
			this.missionForm.setVisible(true);

		} else {
			InvalidValueException invalidVE = new InvalidValueException(
					ConstantsEnglish.MESSAGE_COLLABORATOR_ID_NOT_FOUND);
			this.missionForm.setComponentError(invalidVE);
		}
	}

}
