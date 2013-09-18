package com.novedia.talentmap.web.ui.search;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SearchResults extends Table implements ClickListener {

	private ResourceBundle resourceBundle;
	
	/**
	 * Default constructor
	 */
	public SearchResults() {
		super();
		addStyleName("searchResult table");
	}

	/**
	 * Build SearchResults view
	 * 
	 * @return
	 */
	public SearchResults buildSearchResultsView(List<Colleague> listCollab) {
		Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES , locale);
		removeAllItems();
		mainBuild();
		buildResultsTable(listCollab);
		return this;
	}

	public void mainBuild() {
		addColumns();
	}

	public void addColumns() {
		addContainerProperty(resourceBundle.getString("name.table.header.caption"), String.class, null);
		addContainerProperty(resourceBundle.getString("firstName.table.header.caption"), String.class, null);
		addContainerProperty(resourceBundle.getString("email.table.header.caption"), String.class, null);
		addContainerProperty(resourceBundle.getString("actions.table.header.caption"), HorizontalLayout.class, null);
	}

	public void buildResultsTable(List<Colleague> listCollab) {
		addColumns();
		fillResultsTable(listCollab);
	}

	public void fillResultsTable(List<Colleague> listCollab) {
		for (Colleague collab : listCollab) {
			HorizontalLayout hLayout = new HorizontalLayout();
			Button visualizeProfile = new Button(resourceBundle.getString("visualizeProfile.button.caption"));
			visualizeProfile.addStyleName("styleButton");
//			Button visualizeMissionHistory = new Button(resourceBundle.getString("visualizeMissionHistory.button.caption"));
//			visualizeMissionHistory.addStyleName("styleButton visualizeMissionHistory");
			// Afficher le profil
			visualizeProfile.setData(collab.getId());
			visualizeProfile.addClickListener(this);
			hLayout.addComponent(visualizeProfile);
			// Afficher l'historique des missions pour les roles autorisés RH, CM et IA
//			 if (Authorization.Role.RH.getId().equals(roleId) || Authorization.Role.CM.getId().equals(roleId) || Authorization.Role.IA.getId().equals(roleId)) {
//				 visualizeMissionHistory.setData(collab);
//				 hLayout.addComponent(visualizeMissionHistory);
//			 }
			addItem(new Object[] { collab.getLastName(), collab.getFirstName(),
					collab.getEmail(), hLayout }, collab);
		}

	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}

}
