package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.Map;

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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class SkillColleagueContent extends VerticalLayout implements ClickListener {

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
	
	private Button modifyButton;
	
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
	
	private void buildAddSkillPanel(){
		addSkillPanel.removeAllComponents();
		
		buildButtons();
		
		addSkillPanel.addComponent(addSkillButton);
		addSkillPanel.addComponent(addSkillForm.buildAddSkillForm());
		
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(saveButton);
		hLayout.addComponent(cancelButton);
		
		
		addSkillPanel.addComponent(hLayout);
		addSkillForm.setVisible(false);
		
		addComponent(addSkillPanel);
	}
	
	private void buildSkillContentPanel(){
		skillContentPanel.removeAllComponents();
		
		buildCategoryView();
		addComponent(skillContentPanel);
	}
	
	private void buildButtons(){
		addSkillButton.setCaption("Add skill");
		addSkillButton.addClickListener(this);
		
		saveButton.setCaption("Save");
		saveButton.addClickListener(this);
		saveButton.setVisible(false);
		
		cancelButton.setCaption("Cancel");
		cancelButton.addClickListener(this);
		cancelButton.setVisible(false);
		
		
	}
	
	/**
	 * Display list of category
	 * @return
	 */
	private VerticalLayout buildCategoryView(){
		skillContentPanel.removeAllComponents();
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
		return this;
	}
	
	/**
	 * Display list of concept
	 * @param categoryId  
	 * @return
	 */
	private VerticalLayout buildConceptView(Integer categoryId){
		skillContentPanel.removeAllComponents();
		currentView = Constants.CONCEPT_VIEW;
		buildFilAriane();
		skillContentPanel.addComponent(filArianeLayout);
		Category currentCategory = Category.builder().id(categoryId).build();
		ConceptMapDTO conceptMapDto = categoryMapDto.getMapCategory().get(currentCategory);
		for (Map.Entry<Concept, ToolSkillMap> conceptMap : conceptMapDto.getMapConcept().entrySet()) {
			Button conceptButton = new Button(conceptMap.getKey().getName());
			conceptButton.addClickListener(this);
			conceptButton.addStyleName(Reindeer.BUTTON_LINK);
			conceptButton.setData(conceptMap.getKey().getId());
			skillContentPanel.addComponent(conceptButton);
		}
		return this;
	}
	
	/**
	 * Display list of tool
	 * @param conceptId
	 * @return
	 */
	private VerticalLayout buildToolView(Integer conceptId){
		skillContentPanel.removeAllComponents();
		currentView = Constants.TOOL_VIEW;
		buildFilAriane();
		skillContentPanel.addComponent(filArianeLayout);
		Concept currentConcept = Concept.builder().id(conceptId).build();
		Category currentCategory = Category.builder().id(currentCategoryId).build();
		ConceptMapDTO conceptMapDto1 = categoryMapDto.getMapCategory().get(currentCategory);
		Map<Tool, Skill> mapTool =  conceptMapDto1.getMapConcept().get(currentConcept).getMapTool();
		Table tableTools = new Table();
		tableTools.addStyleName("table");
		tableTools.setPageLength(mapTool.size());
		tableTools.setSelectable(true);
		tableTools.setNullSelectionAllowed(true);
		tableTools.addContainerProperty("Tool Name", String.class, null);
		tableTools.addContainerProperty("Note", Integer.class, null);
		for (Map.Entry<Tool, Skill> eTool : mapTool.entrySet()) {
			tableTools.addItem(new Object[]{eTool.getKey().getName(), 
					eTool.getValue().getAverageScore(),},
					eTool.getKey());
		}
		skillContentPanel.addComponent(tableTools);
		modifyButton.setCaption("Modify");
		modifyButton.addClickListener(this);
		modifyButton.setEnabled(false);
		skillContentPanel.addComponent(modifyButton);
		return this;
		
	}
	
	/**
	 * Build fil ariane layout
	 */
	private void buildFilAriane(){
		filArianeLayout.removeAllComponents();
		filArianeLayout.setSpacing(true);
		Button homeButton = new Button("Home");
		homeButton.addStyleName(Reindeer.BUTTON_LINK);
		homeButton.addClickListener(this);
		homeButton.setData(Constants.CATEGORY_VIEW);
		filArianeLayout.addComponent(homeButton);
		Label firstSeparator = new Label(Constants.FIL_ARIANE_SEPARATOR_LABEL);
		filArianeLayout.addComponent(firstSeparator);
		switch (currentView) {
			case Constants.CONCEPT_VIEW:{
				Category category = adminService.getCategory(currentCategoryId);
				Label categoryLabel = new Label(category.getName());
				filArianeLayout.addComponent(categoryLabel);
				break;
			}
			case Constants.TOOL_VIEW:{
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
	
	@Override
	public void buttonClick(ClickEvent event) {
		Button button = event.getButton();
		
		if(button.equals(addSkillButton)){
			addSkillForm.setVisible(true);
			addSkillButton.setEnabled(false);
			saveButton.setVisible(true);
			cancelButton.setVisible(true);
			modifyButton.setEnabled(false);
			
		} else if(button.equals(cancelButton)){
			addSkillForm.setVisible(false);
			saveButton.setVisible(false);
			cancelButton.setVisible(false);
			addSkillButton.setEnabled(true);
		} else if(button.equals(modifyButton)){
			addSkillForm.setVisible(true);
			addSkillButton.setEnabled(false);
			saveButton.setVisible(true);
			cancelButton.setVisible(true);
			modifyButton.setEnabled(false);
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

	public Button getModifyButton() {
		return modifyButton;
	}

	public void setModifyButton(Button modifyButton) {
		this.modifyButton = modifyButton;
	}

}
