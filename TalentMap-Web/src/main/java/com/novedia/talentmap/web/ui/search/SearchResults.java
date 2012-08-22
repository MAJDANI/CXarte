package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.web.ui.collab.MonitoringCollabContent;
import com.novedia.talentmap.web.ui.collab.ProfileCollabWindow;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class SearchResults extends Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1983028405136383549L;

	/**
	 * Constants
	 */
	public static final int COLLAB_ID = 2;

	/**
	 * Factoriser cette constante qui est aussi utilisée dans MonitoringCollabContent
	 * VGU TODO
	 */
	public static final String VISUALIZE_PROFILE_NAME = "Visualiser Profil";

	/**
	 * Vaadin UI
	 * VGU
	 */
	private ProfileCollabWindow profileCollabWindow;

	/**
	 * Build the class SearchResults.java
	 * 
	 * @param collabService
	 */
	public SearchResults(ProfileCollabWindow profileCollabWindow) {
		super();
		System.out.println("SearchResults:constructeur");
		this.profileCollabWindow = profileCollabWindow;
		mainBuild();
	}

	public void mainBuild() {
		System.out.println("SearchResults.mainBuild()");
		addColumns();
	}

	public void addColumns() {

		addContainerProperty("Nom", String.class, null);
		addContainerProperty("Prénom", String.class, null);
		addContainerProperty("Email", String.class, null);
		addContainerProperty("Actions", HorizontalLayout.class, null);
	}

	public void buildResultsTable(List<Collaborator> listCollab) {
	
		addColumns();
		fillResultsTable(listCollab);
	}

	public void fillResultsTable(List<Collaborator> listCollab) {
		System.out.println("SearchResults.fillResultsTable()");

		int idResultsTable = 1;
		for (Collaborator collab : listCollab) {

			HorizontalLayout hLayout = new HorizontalLayout();
			hLayout.setMargin(true);
	
			
			Button visualizeProfile = buildButton(new Button(
					VISUALIZE_PROFILE_NAME));

			
			visualizeProfile.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
			visualizeProfile.setData(collab.getId());
			
			hLayout.addComponent(visualizeProfile);
			
			addItem(new Object[] { collab.getLast_name(),
					collab.getFirst_name(), collab.getEmail(), hLayout}, idResultsTable);
			idResultsTable++;
		}
	}

	private Button buildButton(Button button) {

		if (button.getCaption().equals(VISUALIZE_PROFILE_NAME)) {

			btnProfileEvent(button);
		}

		return button;
	}
	
	
	//TODO : voir si on peut factoriser cette méthode
	//qui est copiée de la classe MonitoringCollabEvent
	private void btnProfileEvent(Button button) {
		System.out.println("MonitoringCollabContent.btnProfileEvent()");
		button.addListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				Button btnListener = event.getButton();
				int idCollab = (Integer) btnListener.getData();
				System.out.println("SearchResults.btnProfileEvent() idCollab=" + idCollab);

				//TODO enlever les sysout
//				System.out.println("SearchResults=" + SearchResults);
//				System.out.println("SearchResults=" + SearchResults);
				
				SearchResults.this.profileCollabWindow
						.setCOLLAB_ID(idCollab);

				SearchResults.this.profileCollabWindow.mainBuild();

				getWindow().addWindow(
						SearchResults.this.profileCollabWindow);
			}
		});
	}
	
	public ProfileCollabWindow getProfileCollabWindow() {
		return profileCollabWindow;
	}

	public void setProfileCollabWindow(ProfileCollabWindow profileCollabWindow) {
		this.profileCollabWindow = profileCollabWindow;
	}

}
