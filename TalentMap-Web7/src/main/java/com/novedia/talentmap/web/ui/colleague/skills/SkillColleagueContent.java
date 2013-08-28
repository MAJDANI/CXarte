package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.dto.ConceptMapDTO;
import com.novedia.talentmap.model.dto.ToolSkillMap;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.Constants;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SkillColleagueContent extends VerticalLayout implements ClickListener, ValueChangeListener {

	private Integer currentCategoryId ; 
	
	private Integer currentConceptId ;
	
	private int currentView ;
	
	private IAdminService adminService;
	
	private ISkillService skillService;
	
	private CategoryMapDTO categoryMapDto;
	
	private HorizontalLayout filArianeLayout;
	
	private Button addSkillButton;
	
	private Panel addSkillPanel;
	
	private Panel skillContentPanel;
	
	private AddSkillForm addSkillForm;
	
	private Button saveButton;
	
	private Button cancelButton;
	
	private Button editSkillButton;
	
	private Table conceptTable;
	
	private Table toolTable;
	
	private HorizontalLayout skillFormButtonLayout;
	
	/**
	 * Default constructor
	 */
	public SkillColleagueContent(){
		super();
		setSpacing(true);
		setMargin(true);
	}
	
	public VerticalLayout buildSkillColleagueContent(){
		removeAllComponents();
		initToolTable();
		initConceptTable();
		getAllSkillColleague();
		buildAddSkillPanel();
		buildSkillContentPanel();
		return this;
	}
	
	
	/**
	 * Get all collaborator skill
	 */
	private void getAllSkillColleague(){
		Authentication authentication = TalentMapApplication.getCurrent().getAuthentication();
		categoryMapDto = this.skillService.getAllCollaboratorSkill(authentication.getColleagueId());
	}
	
	/**
	 * init table of concept
	 */
	private void initConceptTable(){
		conceptTable.addStyleName("table");
		conceptTable.addContainerProperty("Concept Name", Component.class, null);
		conceptTable.addContainerProperty("Level", Component.class, null);
	}
	
	/**
	 * init table of tool
	 */
	private void initToolTable(){
		toolTable.addStyleName("table");
		toolTable.setSelectable(true);
		toolTable.setNullSelectionAllowed(true);
		toolTable.setImmediate(true);
		toolTable.addContainerProperty("Tool Name", String.class, null);
		toolTable.addContainerProperty("Note", Integer.class, null);
	}
	
	private void buildAddSkillPanel(){
		addSkillPanel.removeAllComponents();
		addSkillButton.setCaption("Add skill");
		addSkillButton.setEnabled(true);
		addSkillButton.addClickListener(this);
		addSkillPanel.addComponent(addSkillButton);
		
		skillFormButtonLayout.removeAllComponents();
		skillFormButtonLayout.setSpacing(true);
		skillFormButtonLayout.addStyleName("containerButton");
		saveButton.setCaption(Constants.SAVE_BUTTON_LABEL);
		saveButton.addClickListener(this);
		cancelButton.setCaption(Constants.CANCEL_BUTTON_LABEL);
		cancelButton.addClickListener(this);
		skillFormButtonLayout.addComponent(saveButton);
		skillFormButtonLayout.addComponent(cancelButton);
		
		addSkillPanel.addComponent(addSkillForm);
		addSkillPanel.addComponent(skillFormButtonLayout);
		addSkillForm.setVisible(false);
		skillFormButtonLayout.setVisible(false);
		addComponent(addSkillPanel);
	}
	
	/**
	 * Build list skill view
	 */
	private void buildSkillContentPanel(){
		skillContentPanel.removeAllComponents();
		buildCategoryView();
		addComponent(skillContentPanel);
	}
	
	/**
	 * Display list of category
	 * @return
	 */
	private void buildCategoryView(){
		skillContentPanel.removeAllComponents();
		skillContentPanel.addComponent(new Label("List of Skill : Categories"));
		currentView = Constants.CATEGORY_VIEW;
		if(categoryMapDto != null&& !categoryMapDto.getMapCategory().isEmpty()){
			for (Map.Entry<Category, ConceptMapDTO> categoryMap : categoryMapDto.getMapCategory().entrySet()) {
				Button categButton = new Button(categoryMap.getKey().getName());
				categButton.addClickListener(this);
				categButton.addStyleName(Reindeer.BUTTON_LINK);
				categButton.setData(categoryMap.getKey().getId());
				skillContentPanel.addComponent(categButton);
			}
		}
	}
	
	/**
	 * Display list of concept
	 * @param categoryId  
	 * @return
	 */
	private void buildConceptView(Integer categoryId){
		skillContentPanel.removeAllComponents();
		skillContentPanel.addComponent(new Label("List of Skill : Concepts"));
		currentView = Constants.CONCEPT_VIEW;
		buildFilAriane();
		skillContentPanel.addComponent(filArianeLayout);
		Category currentCategory = Category.builder().id(categoryId).build();
		ConceptMapDTO conceptMapDto = categoryMapDto.getMapCategory().get(currentCategory);
		conceptTable.removeAllItems();
		conceptTable.setPageLength(conceptMapDto.getMapConcept().size());
		for (Map.Entry<Concept, ToolSkillMap> conceptMap : conceptMapDto.getMapConcept().entrySet()) {
			Button conceptButton = new Button(conceptMap.getKey().getName());
			conceptButton.addClickListener(this);
			conceptButton.addStyleName(Reindeer.BUTTON_LINK);
			conceptButton.setData(conceptMap.getKey().getId());
			int scoreConcept = (int) Math.round(conceptMap.getKey().getScore());
			RatingStars rateConcept ;
			if (scoreConcept != 0) {
				rateConcept = new RatingStars();
				rateConcept.setMaxValue(scoreConcept);
				rateConcept.setReadOnly(true);
			} else {
				rateConcept = null;
			}
			conceptTable.addItem(new Object[]{conceptButton,rateConcept},conceptButton);
		}
		skillContentPanel.addComponent(conceptTable);
	}
	
	/**
	 * Display list of tool
	 * @param conceptId
	 * @return
	 */
	private void buildToolView(Integer conceptId){
		skillContentPanel.removeAllComponents();
		currentView = Constants.TOOL_VIEW;
		skillContentPanel.addComponent(new Label("List of Skill : Tools"));
		buildFilAriane();
		skillContentPanel.addComponent(filArianeLayout);
		Concept currentConcept = Concept.builder().id(conceptId).build();
		Category currentCategory = Category.builder().id(currentCategoryId).build();
		ConceptMapDTO conceptMapDto1 = categoryMapDto.getMapCategory().get(currentCategory);
		Map<Tool, Skill> mapTool =  conceptMapDto1.getMapConcept()
				.get(currentConcept).
				getMapTool();
		toolTable.removeAllItems();
		toolTable.setPageLength(mapTool.size());
		for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
			toolTable.addItem(new Object[]{eTool.getKey().getName(), 
					eTool.getValue().getAverageScore(),},
					eTool.getValue());
		}
		toolTable.addValueChangeListener(this);
		skillContentPanel.addComponent(toolTable);
		editSkillButton.setCaption(Constants.EDIT_BUTTON_LABEL);
		editSkillButton.addClickListener(this);
		editSkillButton.setEnabled(false);
		skillContentPanel.addComponent(editSkillButton);
		
	}
	
	/**
	 * Build fil ariane layout
	 */
	private void buildFilAriane(){
		filArianeLayout.removeAllComponents();
		filArianeLayout.setSpacing(true);
		Button homeButton = new Button(Constants.ALL_SKILL_LABEL);
		homeButton.addStyleName(Reindeer.BUTTON_LINK);
		homeButton.addClickListener(this);
		homeButton.setData(Constants.CATEGORY_VIEW);
		filArianeLayout.addComponent(homeButton);
		Label firstSeparator = new Label(Constants.FIL_ARIANE_SEPARATOR_LABEL);
		filArianeLayout.addComponent(firstSeparator);
		switch (currentView) {
			case Constants.CONCEPT_VIEW:{  //vue des concepts
				Category category = adminService.getCategory(currentCategoryId);
				Label categoryLabel = new Label(category.getName());
				filArianeLayout.addComponent(categoryLabel);
				break;
			}
			case Constants.TOOL_VIEW:{   //vue des outils
				Category category = adminService.getCategory(currentCategoryId);
				Button categoryButton = new Button(category.getName());
				categoryButton.addStyleName(Reindeer.BUTTON_LINK);
				categoryButton.setData(currentCategoryId);
				categoryButton.addClickListener(this);
				filArianeLayout.addComponent(categoryButton);
				Label secondSeparator = new Label(Constants.FIL_ARIANE_SEPARATOR_LABEL);
				filArianeLayout.addComponent(secondSeparator);
				Concept concept = adminService.getConcept(currentConceptId);
				Label conceptLabel = new Label(concept.getName());
				filArianeLayout.addComponent(conceptLabel);
				break;
			}
		}
	}
	
	
	private void enabledSkillForm(boolean state){
		addSkillButton.setEnabled(!state);
		addSkillForm.setVisible(state);
		skillFormButtonLayout.setVisible(state);
	}
	
	
	private boolean checkSkillForm(){
		if(addSkillForm.getToolSelect().getValue() != null && addSkillForm.getFrequencyUseSelect().getValue() != null 
				&& addSkillForm.getNoUsingTimeSelect().getValue() != null && addSkillForm.getStars().getValue() != null ){
			return true;
		} else{
			return false;
		}
			
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		Button button = event.getButton();
		
		if(button.equals(addSkillButton)){
			addSkillForm.buildAddSkillForm(new Skill());
			enabledSkillForm(true);
		} else if(button.equals(cancelButton)){
			enabledSkillForm(false);
		}else if (button.equals(saveButton)) {
			if (checkSkillForm()) {
				Skill currentSkill = addSkillForm.getBinder().getItemDataSource().getBean();
				currentSkill.setScore(addSkillForm.getStars().getValue().intValue());
				currentSkill.setColleagueId(TalentMapApplication.getCurrent().getAuthentication().getColleagueId());
				skillService.saveSkill(currentSkill);
//				addSkillButton.setEnabled(true);
				buildSkillColleagueContent();
				addSkillButton.setEnabled(true);
			} else {
				Notification.show("All fields are required", Type.WARNING_MESSAGE);
			}
		} else if(button.equals(editSkillButton)){
			Skill currentSkill = (Skill) toolTable.getValue();
			addSkillForm.buildAddSkillForm(currentSkill);
			addSkillForm.getToolSelect().setReadOnly(true);
			enabledSkillForm(true);
		} else {
		
			switch (currentView) {
				case Constants.CATEGORY_VIEW:{
					currentCategoryId = (Integer) button.getData();
					buildConceptView(currentCategoryId);
					break;
				}
				case Constants.CONCEPT_VIEW:{
					Integer value = (Integer) button.getData();
					if (value.equals(Constants.CATEGORY_VIEW)) {
						buildCategoryView();
					} else {
						currentConceptId = value;
						buildToolView(currentConceptId);
					}
					break;
				}	
				case Constants.TOOL_VIEW:{
					Integer value = (Integer) button.getData();
					if (value.equals(Constants.CATEGORY_VIEW)) {
						buildCategoryView();
					} else {
						currentCategoryId = value;
						buildConceptView(currentCategoryId);
					}
					break;
				}
				default:
					break;
			}
		}
	}
	
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		Skill selectedSkill = (Skill) toolTable.getValue();
		if (selectedSkill != null) {
			editSkillButton.setEnabled(true);
		} else {
			editSkillButton.setEnabled(false);
		}
	}


	public ISkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	public CategoryMapDTO getCategoryMapDto() {
		return categoryMapDto;
	}

	public void setCategoryMapDto(CategoryMapDTO categoryMapDto) {
		this.categoryMapDto = categoryMapDto;
	}

	public HorizontalLayout getFilArianeLayout() {
		return filArianeLayout;
	}

	public void setFilArianeLayout(HorizontalLayout filArianeLayout) {
		this.filArianeLayout = filArianeLayout;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public Integer getCurrentCategoryId() {
		return currentCategoryId;
	}

	public void setCurrentCategoryId(Integer currentCategoryId) {
		this.currentCategoryId = currentCategoryId;
	}

	public Integer getCurrentConceptId() {
		return currentConceptId;
	}

	public void setCurrentConceptId(Integer currentConceptId) {
		this.currentConceptId = currentConceptId;
	}

	public int getCurrentView() {
		return currentView;
	}

	public void setCurrentView(int currentView) {
		this.currentView = currentView;
	}

	public Button getAddSkillButton() {
		return addSkillButton;
	}

	public void setAddSkillButton(Button addSkillButton) {
		this.addSkillButton = addSkillButton;
	}

	public Panel getAddSkillPanel() {
		return addSkillPanel;
	}

	public void setAddSkillPanel(Panel addSkillPanel) {
		this.addSkillPanel = addSkillPanel;
	}

	public Panel getSkillContentPanel() {
		return skillContentPanel;
	}

	public void setSkillContentPanel(Panel skillContentPanel) {
		this.skillContentPanel = skillContentPanel;
	}

	public AddSkillForm getAddSkillForm() {
		return addSkillForm;
	}

	public void setAddSkillForm(AddSkillForm addSkillForm) {
		this.addSkillForm = addSkillForm;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

	public Button getEditSkillButton() {
		return editSkillButton;
	}

	public void setEditSkillButton(Button editSkillButton) {
		this.editSkillButton = editSkillButton;
	}

	public Table getConceptTable() {
		return conceptTable;
	}

	public void setConceptTable(Table conceptTable) {
		this.conceptTable = conceptTable;
	}

	public Table getToolTable() {
		return toolTable;
	}

	public void setToolTable(Table toolTable) {
		this.toolTable = toolTable;
	}

	
	public HorizontalLayout getSkillFormButtonLayout() {
		return skillFormButtonLayout;
	}

	public void setSkillFormButtonLayout(HorizontalLayout skillFormButtonLayout) {
		this.skillFormButtonLayout = skillFormButtonLayout;
	}

}
