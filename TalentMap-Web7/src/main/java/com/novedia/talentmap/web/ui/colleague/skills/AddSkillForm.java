package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.List;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AddSkillForm extends FormLayout{
	
	/**
     * TalentMap Services
     */
    private ISkillService skillService;

    private Authentication authentication;

    /**
     * Vaadin UI
     */
//    private ListSkill listSkill;

    /**
     * Vaadin Selects UI
     */
    private Label categoryLabel;
    private Label conceptLabel;
    private ComboBox toolSelect;
    private ComboBox frequencyUseSelect;
    private ComboBox noUsingTimeSelect;

    /**
     * Vaadin Widget : RatingStars
     */
    private RatingStars stars;

    /**
     * Object JAVA
     */
    private static Map<Integer, String> valueOptions;
    
    /**
     * Vaadin Buttons
     */
    private Button validSkill;
    
    
    /**
     * Default constructor
     */
    public AddSkillForm() {
    	super();
    }
    
    /**
     * Build view of buildAddSkillPanel
     * 
     * @return
     */
    public AddSkillForm buildAddSkillForm() {
    	removeAllComponents();
    	setImmediate(true);
    	try {
    		buildWindow();

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return this;
    }
    
    /**
     * Build all components in the form
     * 
     * @class AddSkillForm.java
     * @throws Exception
     */
    private void buildWindow() throws Exception {

    	buildComboBox();

    	buildStars();

    	buildButton();

    	buildLayouts();
    }
    
    /**
     * Build all layouts in the main window
     * 
     * @class AddSkillPanel.java
     */
    private void buildLayouts() {

		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout hLayout = new HorizontalLayout();
		HorizontalLayout hLayoutLabel = new HorizontalLayout();
	
		Label addSkillLabel = new Label("etst");
		addSkillLabel.addStyleName("test");
	
		hLayoutLabel.addComponent(categoryLabel);
		hLayoutLabel.addComponent(this.conceptLabel);
		hLayoutLabel.setStyleName("skill-labels");
		hLayoutLabel.setSpacing(true);
	
		// We add all Selects in the Horizontal Layout
		hLayout.addComponent(hLayoutLabel);
		hLayout.addComponent(this.toolSelect);
		hLayout.addComponent(this.stars);
		hLayout.addComponent(this.noUsingTimeSelect);
		hLayout.addComponent(this.frequencyUseSelect);
	
		hLayout.setSpacing(true);
	
		// We add the Horizontal Layout and the Button in the Vertical Layout
		vLayout.addComponent(hLayout);
		vLayout.addComponent(this.validSkill);
		vLayout.setSpacing(true);
	
		// We set all components alignment in the middle
		vLayout.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);
		vLayout.setComponentAlignment(this.validSkill, Alignment.MIDDLE_CENTER);
	
		addComponent(addSkillLabel);
		addComponent(vLayout);
    }
    
    /**
     * Build all Select Input
     * 
     * @class AddSkillPanel.java
     * @throws Exception
     */
    private void buildComboBox() throws Exception {

		// We build the Category Select
		this.categoryLabel.setCaption("Category");
		this.categoryLabel.setImmediate(true);
		this.categoryLabel.setStyleName("category-select");
	
		// We build the Concept Select
		this.conceptLabel.setCaption("Concept");
		this.conceptLabel.setImmediate(true);
		this.conceptLabel.setStyleName("concept-select");
	
		// We build the Tool Select
		this.toolSelect.setCaption("tool selection");
		this.toolSelect.setNullSelectionAllowed(false);
//		this.toolSelect.addClickListener(this);
//		this.toolSelect.addValidator(this);
		this.toolSelect.setImmediate(true);
		this.toolSelect.setStyleName("tool-select");
		this.toolSelect.setWidth("150px");
	
		// We build the Frequency Use Select
		this.frequencyUseSelect.setCaption("frequencyuse");
		this.frequencyUseSelect.setNullSelectionAllowed(false);
		this.frequencyUseSelect.setImmediate(true);
		this.frequencyUseSelect.setStyleName("frequency-use-select");
	
		// We build the No Using Time Select
		this.noUsingTimeSelect.setCaption("no use");
		this.noUsingTimeSelect.setNullSelectionAllowed(false);
		this.noUsingTimeSelect.setImmediate(true);
		this.noUsingTimeSelect.setStyleName("no-using-time-select");
		this.noUsingTimeSelect.setWidth("210px");
	
		// Just fill the Tool Select, the Frequency Use Select and the No Using
		// Time Select
		fillSelect();
    }

    /**
     * Build the Rating Stars
     * 
     * @class AddSkillPanel.java
     */
    private void buildStars() {

	// We build the value options for stars
	stars.setCaption("Votre note pour l'outil : ");
	stars.setAnimated(true);
	stars.setMaxValue(5);
	stars.setWidth("150px");

	for (int i = 0; i < 5; i++) {
	    stars.setValueCaption(i + 1, "te");
	}

    }

    /**
     * Fill the Select Tool
     * 
     * @class AddSkillPanel.java
     * @throws Exception
     */
    private void fillSelect() {

	// We fill only the Tool Select
	List<Tool> listTool = skillService.getAllTools();
	// System.out.println("***listTool*** : "+ listTool);
	for (Tool t : listTool) {
	    this.toolSelect.addItem(t.getName());
	    // Item i = this.toolSelect.addItem(t.getName());
	    // Item i2 = this.toolSelect.addItem(t);
	    // i.addItemProperty(t.getId(), t.getName());
	}

	// We fill the Frequency Use
	for (FrequencyUse fu : FrequencyUse.values()) {
	    this.frequencyUseSelect.addItem(fu.getValue());
	}

	// We fill the No Using Time
	for (TimeUse tu : TimeUse.values()) {
	    this.noUsingTimeSelect.addItem(tu.getValue());
	}

    }

    /**
     * Build the Button
     * 
     * @class AddSkillPanel.java
     */
    private void buildButton() {

	this.validSkill.setCaption("OK");
//	this.validSkill.addListener((ClickListener) this);
    }

	public ISkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public Label getCategoryLabel() {
		return categoryLabel;
	}

	public void setCategoryLabel(Label categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	public Label getConceptLabel() {
		return conceptLabel;
	}

	public void setConceptLabel(Label conceptLabel) {
		this.conceptLabel = conceptLabel;
	}

	public ComboBox getToolSelect() {
		return toolSelect;
	}

	public void setToolSelect(ComboBox toolSelect) {
		this.toolSelect = toolSelect;
	}

	public ComboBox getFrequencyUseSelect() {
		return frequencyUseSelect;
	}

	public void setFrequencyUseSelect(ComboBox frequencyUseSelect) {
		this.frequencyUseSelect = frequencyUseSelect;
	}

	public ComboBox getNoUsingTimeSelect() {
		return noUsingTimeSelect;
	}

	public void setNoUsingTimeSelect(ComboBox noUsingTimeSelect) {
		this.noUsingTimeSelect = noUsingTimeSelect;
	}

	public RatingStars getStars() {
		return stars;
	}

	public void setStars(RatingStars stars) {
		this.stars = stars;
	}

	public static Map<Integer, String> getValueOptions() {
		return valueOptions;
	}

	public static void setValueOptions(Map<Integer, String> valueOptions) {
		AddSkillForm.valueOptions = valueOptions;
	}

	public Button getValidSkill() {
		return validSkill;
	}

	public void setValidSkill(Button validSkill) {
		this.validSkill = validSkill;
	}

}
