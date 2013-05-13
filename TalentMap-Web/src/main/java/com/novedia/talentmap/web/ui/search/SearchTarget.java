package com.novedia.talentmap.web.ui.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Authorization;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IClientService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.ISearchContent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class SearchTarget extends VerticalLayout implements ClickListener,
		TextChangeListener, IObservable {

	/**
	 * Utils Observator
	 */
	private ISearchContent obs;

	private Authentication authentication;

	/**
	 * TalentMap Services
	 */
	private IColleagueService collabService;
	private ISkillService skillService;
	private IClientService clientService;

	/**
	 * Vaadin Components
	 */
	private Panel searchByClientPanel;
	private Panel searchByNamePanel;
	private Panel searchBySkillsPanel;

	private Select clientNameSelect;
	private TextField fieldName;
	// Il manque les Skills !!! ??

	private Button search;


	/**
	 * POJO
	 */
	private List<Colleague> listCollab;
	private Tree treeSkills;

	/**
	 * Constructeur par défaut
	 */
	public SearchTarget() {
		super();
	}

	/**
	 * Build SearchTarget view
	 * 
	 * @return
	 */
	public SearchTarget buildSearchTargetView() {
		removeAllComponents();
		buildMain();
		return this;
	}

	/**
	 * The main builder
	 * 
	 * @class SearchTarget.java
	 */
	public void buildMain() {

		setMargin(true);
		setSpacing(true);

		try {

			buildField();

		} catch (Exception e) {

			e.printStackTrace();
		}

		buildButton();
	}

	/**
	 * The field builder
	 * 
	 * @throws Exception
	 * 
	 * @class SearchTarget.java
	 */
	public void buildField() throws Exception {

		// Build the Client Panel
		List<Client> listClient = this.clientService.getAllClients();
		Collections.sort(listClient);

		BeanItemContainer<Client> container = new BeanItemContainer<Client>(
				Client.class);

		for (Client client : listClient) {
			container.addItem(client);
		}

		this.clientNameSelect = new Select("Customer : ", container);
		this.clientNameSelect
				.setItemCaptionMode(Select.ITEM_CAPTION_MODE_PROPERTY);
		this.clientNameSelect.setItemCaptionPropertyId("name");
		this.clientNameSelect.setNullSelectionAllowed(false);
		this.clientNameSelect.setImmediate(true);

		this.searchByClientPanel.addComponent(clientNameSelect);

		this.fieldName.setCaption("Name of collaborator: ");
		this.fieldName.addListener(this);
		this.searchByNamePanel.addComponent(this.fieldName);

		// Build the Skills Panel
		treeSkills = buildTreeSkills();

		this.searchBySkillsPanel.addComponent(treeSkills);
		addComponent(this.searchByClientPanel);
		addComponent(this.searchByNamePanel);
		addComponent(this.searchBySkillsPanel);

		initField();
	}

	/**
	 * Construit l'arbre des compétences
	 * 
	 * @return
	 */
	public Tree buildTreeSkills() {
		Tree treeSkills = new Tree("Skills");
		treeSkills.setMultiSelect(true);

		List<VSkill> listVSkill = this.skillService.getAllVSkillOrdered();
		Integer currentCategoryId;
		Integer currentConceptId;
		Integer lastCategoryId = -1;
		Integer lastConceptId = -1;

		for (VSkill vSkill : listVSkill) {

			currentCategoryId = vSkill.getCategoryId();
			currentConceptId = vSkill.getConceptId();
			Category categorie = Category.builder().build();
			Concept concept = Concept.builder().build();
			Tool tool = Tool.builder().build();

			// Si on a une nouvelle catégorie à traiter
			if (currentCategoryId != lastCategoryId) {
				// On ajoute la categorie
				String catName = vSkill.getCategoryName();
				categorie = Category.builder().id(currentCategoryId)
						.name(catName).build();
				treeSkills.addItem(categorie);
				treeSkills.setItemCaption(categorie, catName);
				lastCategoryId = currentCategoryId;
			}

			// Si on a un nouveau concept à traiter
			if (currentConceptId != lastConceptId) {
				String conceptName = vSkill.getConceptName();
				concept = Concept.builder().id(currentConceptId)
						.name(conceptName).build();
				// On ajoute le concept en tant que nouvel Item.
				treeSkills.addItem(concept);
				treeSkills.setItemCaption(concept, conceptName);

				// On le positionne comme enfant de la categorie
				treeSkills.setParent(concept, categorie);
				lastConceptId = currentConceptId;
			}
			String toolName = vSkill.getToolName();
			tool = Tool.builder().id(vSkill.getToolId()).name(toolName).build();
			// On ajoute le tool en tant que nouvel Item.
			treeSkills.addItem(tool);
			treeSkills.setItemCaption(tool, toolName);
			// On le positionne comme enfant de la categorie
			treeSkills.setParent(tool, concept);

			// Make the moons look like leaves.
			treeSkills.setChildrenAllowed(tool, false);

		}
		return treeSkills;
	}

	/**
	 * Default init for the fields
	 * 
	 * @class SearchTarget.java
	 */
	public void initField() {
		switchByNamePanel();
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchByNamePanel() {

		this.searchByNamePanel.setVisible(true);
		this.searchByClientPanel.setVisible(false);
		this.searchBySkillsPanel.setVisible(false);
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchByClientPanel() {

		this.searchByNamePanel.setVisible(false);
		this.searchByClientPanel.setVisible(true);
		this.searchBySkillsPanel.setVisible(false);
	}

	/**
	 * 
	 * @class SearchTarget.java
	 */
	public void switchBySkillsPanel() {

		this.searchByNamePanel.setVisible(false);
		this.searchByClientPanel.setVisible(false);
		this.searchBySkillsPanel.setVisible(true);
	}

	/**
	 * The button builder
	 * 
	 * @class SearchTarget.java
	 */
	public void buildButton() {

		this.search.setCaption("Search");
		this.search.addListener(this);

		addComponent(this.search);
	}

	/**
	 * Gestion des événments clic sur Boutons
	 * 
	 */
	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();
		if (button == this.search) {

			// ---------------------------------------------------------
			// The Panel "Search by customer" is visible
			// ---------------------------------------------------------
			if (this.searchByClientPanel.isVisible()) {
				Client client = (Client) this.clientNameSelect.getValue();
				if (client != null) {
					try {
						if (authentication != null) {
							if (Authorization.Role.CM.getId().equals(this.authentication.getAuthorization().getRoleId())) {

							
									this.listCollab = this.collabService.getCmColleaguesByClient(client.getId(),authentication.getColleagueId());
							}
						} else {
							this.listCollab = this.collabService
									.getAllColleaguesByClient(client);
						}
						updateObservateur();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					getWindow().showNotification(
							ConstantsEnglish.NO_CUSTUMER_SELECT_MSG,
							Notification.TYPE_WARNING_MESSAGE);
				}

			}

			// ---------------------------------------------------------
			// The Panel "Search by name" is visible
			// ---------------------------------------------------------
			if (this.searchByNamePanel.isVisible()) {
				String collabName = (String) this.fieldName.getValue();
				try {
					if (authentication != null) {
						if (Authorization.Role.CM.getId().equals(
								this.authentication.getAuthorization()
										.getRoleId())) {
							this.listCollab = this.collabService
									.getCmColleaguesByName(collabName,
											authentication.getColleagueId());
						} else {
							this.listCollab = this.collabService
								.getAllColleaguesByName(collabName);
						}
						updateObservateur();
					} else {
						//DO STH
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// ---------------------------------------------------------
			// The Panel "Search by skills" is visible
			// ---------------------------------------------------------
			if (this.searchBySkillsPanel.isVisible()) {
				// Get all collaborators who has all skills requested
				this.listCollab = getListColleagueForTooIdChecked();
				updateObservateur();
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public void textChange(TextChangeEvent event) {

		String valueField = event.getText();

		if (valueField.length() >= 3) {

			if (this.searchByClientPanel.isVisible()) {

			}

			if (this.searchByNamePanel.isVisible()) {

				try {
					if (authentication != null) {
						if (Authorization.Role.CM.getId().equals(
								this.authentication.getAuthorization()
										.getRoleId())) {
							this.listCollab = this.collabService
									.getCmColleaguesByName(valueField,
											authentication.getColleagueId());
						
						} else {
							this.listCollab = this.collabService
									.getAllColleaguesByName(valueField);
						}
					}
					updateObservateur();

				} catch (Exception e) {

					e.printStackTrace();
				}

			}

		}
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		if (cl == ISearchContent.class) {
			this.obs = (ISearchContent) observateur;
		}
	}

	@Override
	public void updateObservateur() {
		this.obs.changeSearchResults(this.listCollab, false);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}

	/**
	 * Renvoie la liste des Colleagues correspondant aux outils cochés dans le
	 * Panel de checherche par Compétence
	 * 
	 * @return List<Colleague> la liste des colleagues. Elle peut être vide si
	 *         l'utilisateur n'a rien coché (pa de requête effectuée) ou si la
	 *         sélection d'outils ne renvoie pas de résultat
	 */
	public List<Colleague> getListColleagueForTooIdChecked() {
		List<Colleague> listColleague = new ArrayList<Colleague>();
		Boolean atLeastOneIsSelected = false;
		List<Integer> listColleagueId = new ArrayList<Integer>();

		atLeastOneIsSelected = parseList(listColleagueId);

		if (!atLeastOneIsSelected) {
			getWindow().showNotification(
					ConstantsEnglish.SEARCH_SKILLS_MSG_PLEASE_SELECT,
					Notification.TYPE_WARNING_MESSAGE);
		} else {
			// On charge les collaborateurs correspondant à notre liste
			// définitive de colleagueId
			if (listColleagueId.isEmpty()) {
				getWindow().showNotification(
						ConstantsEnglish.SEARCH_SKILLS_MSG_NO_COLLEAGUE_FOUND,
						Notification.TYPE_WARNING_MESSAGE);
			} else {
				listColleague = this.collabService
						.getAllColleagueByColleagueIdList(listColleagueId);
			}
		}
		return listColleague;
	}

	/**
	 * Renvoie une liste d'Id de colleague remplie ou vide. Cette liste est
	 * construite en cherchant en base les colleague qui ont une compétence
	 * correspondant à la sélection faite dans l'arbre de compétences.
	 * 
	 * @param atLeastOneIsSelected
	 *            : permet à l'appelant de savoir si au moins un élément de
	 *            l'arbre des compétences a été sélectionné
	 * @return la liste des Id colleague trouvés
	 */
	public Boolean parseList(List<Integer> listColleagueId) {
		Boolean atLeastOneIsSelected = false;
		List<Integer> listColleagueIdTemp = new ArrayList<Integer>();
		Collection<Object> lesItemId = (Collection<Object>) this.treeSkills
				.getContainerDataSource().getItemIds();

		Boucle: for (Object item : lesItemId) {

			if (this.treeSkills.isSelected(item)) {
				atLeastOneIsSelected = true;
				if (item instanceof Category) {
					// On veut des comptétences sur une catégorie entière
					// On va collecter tous les outils de la catégorie
					// et identifier les id collaborateur qui ont une compétence
					// sur
					// au moins un de ces outils
					Collection lesConceptsFils = this.treeSkills
							.getChildren(item);

					List<Integer> listToolIdForCategory = new ArrayList<Integer>();

					for (Object concepFils : lesConceptsFils) {
						Collection lesToolsPetitsFils = this.treeSkills
								.getChildren(concepFils);
						for (Object toolPetitFils : lesToolsPetitsFils) {
							Tool tool = (Tool) toolPetitFils;
							listToolIdForCategory.add(tool.getId());
						}
					}
					// Récupérer les id collab ayant au moins un tool de
					// la
					// catégorie
					if (authentication != null) {
						if (Authorization.Role.CM.getId().equals(
								this.authentication.getAuthorization()
										.getRoleId())) {
							listColleagueIdTemp.addAll(this.skillService
									.getCmColleagueIdByListToolId(
											listToolIdForCategory,
											authentication.getColleagueId()));
						}
					} else {
						// Récupérer les id collab ayant au moins un tool de la
						// catégorie
						listColleagueIdTemp
								.addAll(this.skillService
										.getAllColleagueIdByListToolId(listToolIdForCategory));
					}
					// Si notre liste résultat listColleagueId contenait déjà
					// des id collab, on ne garde que les
					// id collab en commun dans les 2 listes
					// sinon, on ajoute simplement les éléments de
					// listColleagueIdTemp à la liste résultat
					if (!listColleagueId.isEmpty()) {
						listColleagueId.retainAll(listColleagueIdTemp);
					} else {
						listColleagueId.addAll(listColleagueIdTemp);
					}
					// Si la séléction utilisateur ne donne déjà pas de
					// résultat, on sort
					// de la boucle
					if (listColleagueId.isEmpty()) {
						break Boucle;
					}
					listColleagueIdTemp.clear();

				} else if (item instanceof Concept) {
					Collection lesToolFils = this.treeSkills.getChildren(item);

					// On veut des comptétences sur un concept entier
					// On va collecter tous les outils du concept
					// et identifier les id collaborateur qui ont une compétence
					// sur
					// au moins un de ces outils
					List<Integer> listToolIdForConcept = new ArrayList<Integer>();
					for (Object toolFils : lesToolFils) {
						Tool tool = (Tool) toolFils;
						listToolIdForConcept.add(tool.getId());
					}
					// Récupérer les id collab ayant au moins un tool du concept
					if (authentication != null) {
						if (Authorization.Role.CM.getId().equals(
								this.authentication.getAuthorization()
										.getRoleId())) {
							listColleagueIdTemp.addAll(this.skillService
									.getCmColleagueIdByListToolId(
											listToolIdForConcept,
											authentication.getColleagueId()));
						}
					} else {
						listColleagueIdTemp
								.addAll(this.skillService
										.getAllColleagueIdByListToolId(listToolIdForConcept));
					}
					if (!listColleagueId.isEmpty()) {
						listColleagueId.retainAll(listColleagueIdTemp);
					} else {
						listColleagueId.addAll(listColleagueIdTemp);
					}
					if (listColleagueId.isEmpty()) {
						break Boucle;
					}
					listColleagueIdTemp.clear();

				} else {
					// C'est un Tool qui est sélectionné
					Tool toolSelected = (Tool) item;
					// Récupérer les id collab ayant une compétence sur ce tool
					if (authentication != null) {
						if (Authorization.Role.CM.getId().equals(
								this.authentication.getAuthorization()
										.getRoleId())) {
							listColleagueIdTemp.addAll(this.skillService
									.getCmColleagueIdByToolId(
											toolSelected.getId(),
											authentication.getColleagueId()));
						} else {
							listColleagueIdTemp.addAll(this.skillService
									.getAllColleagueIdByToolId(toolSelected
											.getId()));
						}
					}
					if (!listColleagueId.isEmpty()) {
						listColleagueId.retainAll(listColleagueIdTemp);
					} else {
						listColleagueId.addAll(listColleagueIdTemp);
					}
					if (listColleagueId.isEmpty()) {
						break Boucle;
					}
					listColleagueIdTemp.clear();
				}
			}
		}
		return atLeastOneIsSelected;
	}

	/**
	 * Get the searchByClientPanel value
	 * 
	 * @return the searchByClientPanel
	 */
	public Panel getSearchByClientPanel() {
		return searchByClientPanel;
	}

	/**
	 * Set the searchByClientPanel value
	 * 
	 * @param searchByClientPanel
	 *            the searchByClientPanel to set
	 */
	public void setSearchByClientPanel(Panel searchByClientPanel) {
		this.searchByClientPanel = searchByClientPanel;
	}

	/**
	 * Get the searchByNamePanel value
	 * 
	 * @return the searchByNamePanel
	 */
	public Panel getSearchByNamePanel() {
		return searchByNamePanel;
	}

	/**
	 * Set the searchByNamePanel value
	 * 
	 * @param searchByNamePanel
	 *            the searchByNamePanel to set
	 */
	public void setSearchByNamePanel(Panel searchByNamePanel) {
		this.searchByNamePanel = searchByNamePanel;
	}

	/**
	 * Get the searchBySkillsPanel value
	 * 
	 * @return the searchBySkillsPanel
	 */
	public Panel getSearchBySkillsPanel() {
		return searchBySkillsPanel;
	}

	/**
	 * Set the searchBySkillsPanel value
	 * 
	 * @param searchBySkillsPanel
	 *            the searchBySkillsPanel to set
	 */
	public void setSearchBySkillsPanel(Panel searchBySkillsPanel) {
		this.searchBySkillsPanel = searchBySkillsPanel;
	}

	/**
	 * Get the select client Name
	 * 
	 * @return the fieldClient
	 */
	public Select getClientNameSelect() {
		return clientNameSelect;
	}

	/**
	 * Set the client name select
	 * 
	 * @param fieldClient
	 *            the fieldClient to set
	 */
	public void setClientNameSelect(Select clientNameSelect) {
		this.clientNameSelect = clientNameSelect;
	}

	/**
	 * Get the fieldName value
	 * 
	 * @return the fieldName
	 */
	public TextField getFieldName() {
		return fieldName;
	}

	/**
	 * Set the fieldName value
	 * 
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(TextField fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Get the search value
	 * 
	 * @return the search
	 */
	public Button getSearch() {
		return search;
	}

	/**
	 * Set the search value
	 * 
	 * @param search
	 *            the search to set
	 */
	public void setSearch(Button search) {
		this.search = search;
	}

	/**
	 * Get the collabService value
	 * 
	 * @return the collabService
	 */
	public IColleagueService getCollabService() {
		return collabService;
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
	 * Get the skillService value
	 * 
	 * @return the skillService
	 */
	public ISkillService getSkillService() {
		return skillService;
	}

	/**
	 * Set the skillService value
	 * 
	 * @param skillService
	 *            the skillService to set
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	/**
	 * Get the listCollab value
	 * 
	 * @return the listCollab
	 */
	public List<Colleague> getListCollab() {
		return listCollab;
	}

	/**
	 * Set the listCollab value
	 * 
	 * @param listCollab
	 *            the listCollab to set
	 */
	public void setListCollab(List<Colleague> listCollab) {
		this.listCollab = listCollab;
	}

	public Tree getTreeSkills() {
		return treeSkills;
	}

	public void setTreeSkills(Tree treeSkills) {
		this.treeSkills = treeSkills;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}
