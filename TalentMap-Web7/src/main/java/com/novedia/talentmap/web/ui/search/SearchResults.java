package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Colleague;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickListener;

@SuppressWarnings("serial")
public class SearchResults extends PagedTable implements ClickListener {

	private Integer roleId;

	public static final String VISUALIZE_PROFILE_NAME = "Display profile";
	public static final String VISUALIZE_MISSION_HISTORY_NAME = "Display missions";

	/**
	 * Vaadin components
	 */

	private Button visualizeProfile;

	private Button visualizeMissionHistory;

	/**
	 * Default constructor
	 */
	public SearchResults() {
		super();
	}

	/**
	 * Build SearchResults view
	 * 
	 * @return
	 */
	public SearchResults buildSearchResultsView() {
		removeAllItems();
		mainBuild();
		return this;
	}

	public void mainBuild() {
		addColumns();
	}

	public void addColumns() {

		addContainerProperty("Name", String.class, null);
		addContainerProperty("First name", String.class, null);
		addContainerProperty("Email", String.class, null);
		addContainerProperty("Actions", HorizontalLayout.class, null);
	}

	public void buildResultsTable(List<Colleague> listCollab) {

		addColumns();
		fillResultsTable(listCollab);
	}

	public void fillResultsTable(List<Colleague> listCollab) {
		int idResultsTable = 1;
		for (Colleague collab : listCollab) {

			HorizontalLayout hLayout = new HorizontalLayout();
			hLayout.setMargin(true);

			buildButton();
			buildListeners();

			// Afficher le profil
			visualizeProfile.setData(collab.getId());
			hLayout.addComponent(visualizeProfile);

			// Afficher l'historique des missions pour les roles autorisés RH, CM et IA
			 if (Authorization.Role.RH.getId().equals(roleId) || Authorization.Role.CM.getId().equals(roleId) || Authorization.Role.IA.getId().equals(roleId)) {
				 visualizeMissionHistory.setData(collab);
				 hLayout.addComponent(visualizeMissionHistory);
			 }

			addItem(new Object[] { collab.getLastName(), collab.getFirstName(),
					collab.getEmail(), hLayout }, idResultsTable);
			idResultsTable++;
		}

	}

	private void buildButton() {
		visualizeProfile.setCaption(VISUALIZE_PROFILE_NAME);
		
		if (Authorization.Role.RH.getId().equals(roleId) || Authorization.Role.CM.getId().equals(roleId) || Authorization.Role.IA.getId().equals(roleId)) {
			visualizeMissionHistory.setCaption(VISUALIZE_MISSION_HISTORY_NAME);
		}

	}

	private void buildListeners() {
		visualizeProfile.addClickListener(this);
		visualizeMissionHistory.addClickListener(this);
	}

	// private Button buildButton(Button button) {
	// if (button.getCaption().equals(VISUALIZE_PROFILE_NAME)) {
	// btnProfileEvent(button);
	// }
	// if (button.getCaption().equals(VISUALIZE_MISSION_HISTORY_NAME)) {
	// btnMissionHistoryEvent(button);
	// }
	// return button;
	// }

	// private void btnProfileEvent(Button button) {
	//
	// button.addClickListener(this);
	//
	// }

	// @Override
	// public void buttonClick(ClickEvent event) {
	//
	// Button btnListener = event.getButton();
	// int idCollab = (Integer) btnListener.getData();
	//
	// SearchResults.this.profileCollabWindow.setCOLLAB_ID(idCollab);
	//
	// SearchResults.this.profileCollabWindow.mainBuild();
	//
	// getWindow().addWindow(SearchResults.this.profileCollabWindow);
	// }
	// });
	// }

//	private void btnMissionHistoryEvent(Button button) {
//		button.addListener(new ClickListener() {
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//
//				Button btnListener = event.getButton();
//				Colleague currentColleague = (Colleague) btnListener.getData();
//
//				SearchResults.this.listMissionWindow
//						.setCurrentColleague(currentColleague);
//				SearchResults.this.listMissionWindow.setRole(getRoleId());
//				SearchResults.this.listMissionWindow.mainBuild();
//
//				getWindow().addWindow(SearchResults.this.listMissionWindow);
//			}
//		});
//	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub

	}

	public Button getVisualizeProfile() {
		return visualizeProfile;
	}

	public void setVisualizeProfile(Button visualizeProfile) {
		this.visualizeProfile = visualizeProfile;
	}

	public Button getVisualizeMissionHistory() {
		return visualizeMissionHistory;
	}

	public void setVisualizeMissionHistory(Button visualizeMissionHistory) {
		this.visualizeMissionHistory = visualizeMissionHistory;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
