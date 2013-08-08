package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Colleague;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class SearchResults extends PagedTable {

	private Integer roleId;

	public static final String VISUALIZE_PROFILE_NAME = "Display profile";
	public static final String VISUALIZE_MISSION_HISTORY_NAME = "Display missions";

	/**
	 * Vaadin components
	 */
	
	

//	private Button visualizeProfile;
//
//	private Button visualizeMissionHistory;

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
	public SearchResults buildSearchResultsView(List<Colleague> listCollab) {
		removeAllItems();
		mainBuild();
		buildResultsTable(listCollab);
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

//			buildButton();
//			buildListeners();
			Button visualizeProfile = new Button(VISUALIZE_PROFILE_NAME);
			Button visualizeMissionHistory = new Button(VISUALIZE_MISSION_HISTORY_NAME);

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



	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
