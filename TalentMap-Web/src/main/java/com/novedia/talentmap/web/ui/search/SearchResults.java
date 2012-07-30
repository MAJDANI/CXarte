package com.novedia.talentmap.web.ui.search;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

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
	 * Build the class SearchResults.java
	 * 
	 * @param collabService
	 */
	public SearchResults() {
		super();
		
		mainBuild();
	}

	public void mainBuild() {

		addColumns();
	}

	public void addColumns() {

		addContainerProperty("Nom", String.class, null);
		addContainerProperty("Prénom", String.class, null);
		
		addContainerProperty("Email", String.class, null);
		
		addContainerProperty("Actions", HorizontalLayout.class, null);
	}

	public void fillResultsTable(List<Collaborator> listCollab) {

		int idResultsTable = 1;
		for (Collaborator collab : listCollab) {

			HorizontalLayout hLayout = new HorizontalLayout();
			hLayout.setMargin(true);
			
			Button visualizeProfile = new Button("Afficher le profil");
			visualizeProfile.addStyleName(TalentMapCSS.BUTTON_NAVIGATION);
			visualizeProfile.setData(collab.getId());
			
			hLayout.addComponent(visualizeProfile);
			
			addItem(new Object[] { collab.getLast_name(),
					collab.getFirst_name(), collab.getEmail(), hLayout}, idResultsTable);
			idResultsTable++;
		}
	}

	public void buildResultsTable(List<Collaborator> listCollab) {

		addColumns();

		fillResultsTable(listCollab);
	}
}
