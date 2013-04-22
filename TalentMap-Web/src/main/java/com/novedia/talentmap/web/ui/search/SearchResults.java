package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.jensjansson.pagedtable.PagedTable;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.ui.collab.ListMissionCollabWindow;
import com.novedia.talentmap.web.ui.collab.ProfileCollabWindow;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

public class SearchResults extends PagedTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1983028405136383549L;

	/**
	 * Factoriser cette constante qui est aussi utilisée dans
	 * MonitoringCollabContent VGU TODO
	 */
	public static final String VISUALIZE_PROFILE_NAME = "Display profile";
	public static final String VISUALIZE_MISSION_HISTORY_NAME = "Display missions";


	/**
	 * Vaadin UI VGU
	 */
	private ProfileCollabWindow profileCollabWindow;
	private ListMissionCollabWindow listMissionWindow;

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

			Button visualizeProfile = buildButton(new Button(
					VISUALIZE_PROFILE_NAME));
			Button visualizeMissionHistory = buildButton(new Button(
					VISUALIZE_MISSION_HISTORY_NAME));
			visualizeProfile.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
			visualizeProfile.setData(collab.getId());

			visualizeMissionHistory.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
			visualizeMissionHistory.setData(collab);

			hLayout.addComponent(visualizeProfile);
			hLayout.addComponent(visualizeMissionHistory);

			addItem(new Object[] { collab.getLastName(), collab.getFirstName(),
					collab.getEmail(), hLayout }, idResultsTable);
			idResultsTable++;
		}

	}

	private Button buildButton(Button button) {
		if (button.getCaption().equals(VISUALIZE_PROFILE_NAME)) {
			btnProfileEvent(button);
		}
		if (button.getCaption().equals(VISUALIZE_MISSION_HISTORY_NAME)) {
			btnMissionHistoryEvent(button);
		}
		return button;
	}

	// TODO : voir si on peut factoriser cette méthode
	// qui est copiée de la classe MonitoringCollabEvent
	private void btnProfileEvent(Button button) {
		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();

				SearchResults.this.profileCollabWindow
						.setCOLLAB_ID(idCollab);

				SearchResults.this.profileCollabWindow.mainBuild();

				getWindow().addWindow(
						SearchResults.this.profileCollabWindow);
			}
		});
	}


	private void btnMissionHistoryEvent(Button button) {
		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				Colleague currentColleague = (Colleague) btnListener.getData();

				SearchResults.this.listMissionWindow
						.setCurrentColleague(currentColleague);
				SearchResults.this.listMissionWindow.mainBuild();
				
				getWindow().addWindow(
						SearchResults.this.listMissionWindow);
			}
		});
	}

	public ProfileCollabWindow getProfileCollabWindow() {
		return profileCollabWindow;
	}

	public void setProfileCollabWindow(ProfileCollabWindow profileCollabWindow) {
		this.profileCollabWindow = profileCollabWindow;
	}

	public ListMissionCollabWindow getListMissionWindow() {
		return listMissionWindow;
	}

	public void setListMissionWindow(ListMissionCollabWindow listMissionWindow) {
		this.listMissionWindow = listMissionWindow;
	}

}
