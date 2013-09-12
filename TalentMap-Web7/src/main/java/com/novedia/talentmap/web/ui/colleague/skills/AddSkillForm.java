package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class AddSkillForm extends HorizontalLayout implements ValueChangeListener{
	
	public String[] OPTIONS ;
	
	
	private BeanFieldGroup<Skill> binder;
	/**
     * TalentMap Services
     */
    private ISkillService skillService;

    /**
     * Vaadin Selects UI
     */
    private Label categoryLabel;
    private Label conceptLabel;
    
    @PropertyId("tool_id")
    private ComboBox toolSelect;
    
    @PropertyId("use_frequency")
    private ComboBox frequencyUseSelect;
    
    @PropertyId("no_using_time")
    private ComboBox noUsingTimeSelect;

    /**
     * Vaadin Widget : RatingStars
     */
    private RatingStars stars;
    
    private HorizontalLayout categoryAndConceptLayout ;
    
    private ResourceBundle resourceBundle;
    
    /**
     * Default constructor
     */
    public AddSkillForm() {
    	super();
    	setSpacing(true);
    }
    
    /**
     * Build view of buildAddSkillPanel
     * 
     * @return AddSkillForm
     */
    public void buildAddSkillForm(Skill skill) {
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.ADD_SKILL_FORM_PROPERTIES , locale);
		OPTIONS = new String[] { resourceBundle.getString("beginner"), resourceBundle.getString("middle"),resourceBundle.getString("professional"),
				resourceBundle.getString("master"), resourceBundle.getString("expert") };
    	removeAllComponents();
    	setImmediate(true);
		buildForm(skill);
    }
    
    
    private void buildForm(Skill skill){
    	
    	categoryLabel.setCaption(resourceBundle.getString("category.label"));
    	conceptLabel.setCaption(resourceBundle.getString("concept.label"));
    	categoryAndConceptLayout.removeAllComponents();
    	categoryAndConceptLayout.setSpacing(true);
    	categoryAndConceptLayout.addComponent(categoryLabel);
    	categoryAndConceptLayout.addComponent(conceptLabel);
    	
    	toolSelect.setCaption(resourceBundle.getString("tool.select.caption"));
		toolSelect.setNullSelectionAllowed(false);
		toolSelect.setImmediate(true);
		toolSelect.addValueChangeListener(this);
		
		stars.setCaption(resourceBundle.getString("stars.caption"));
		stars.setImmediate(true);
		stars.setAnimated(true);
		stars.setMaxValue(5);
		stars.setValue(new Integer(0).doubleValue());
		for (int i = 0; i < 5; i++) {
			stars.setValueCaption(i + 1, OPTIONS[i]);
		}
		if(skill.getScore() != null){
			stars.setValue(skill.getScore().doubleValue());
		}
		
		frequencyUseSelect.setCaption(resourceBundle.getString("frequency.use.select.caption"));
		frequencyUseSelect.setNullSelectionAllowed(false);
		frequencyUseSelect.setImmediate(true);
	
		// We build the No Using Time Select
		noUsingTimeSelect.setCaption(resourceBundle.getString("no.using.time.select.caption"));
		noUsingTimeSelect.setNullSelectionAllowed(false);
		noUsingTimeSelect.setImmediate(true);
		fillSelect();
		
		binder = new BeanFieldGroup<Skill>(Skill.class);
		binder.setItemDataSource(skill);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		if(toolSelect.getValue() != null){
			categoryAndConceptLayout.setVisible(true);
		} else{
			categoryAndConceptLayout.setVisible(false);
		}
		
		addComponent(categoryAndConceptLayout);
		addComponent(toolSelect);
		addComponent(stars);
		addComponent(frequencyUseSelect);
		addComponent(noUsingTimeSelect);
    }
    
    
    @Override
	public void valueChange(ValueChangeEvent event) {
    	if(toolSelect.getValue() != null){
    		VSkill vSkill = skillService.getSkillByTool(toolSelect.getItemCaption(toolSelect.getValue()));
    		categoryAndConceptLayout.setVisible(true);
    		categoryLabel.setValue(vSkill.getCategoryName());
    		conceptLabel.setValue(vSkill.getConceptName());
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
		for (Tool t : listTool) {
			toolSelect.addItem(t.getId());
			toolSelect.setItemCaption(t.getId(), t.getName());
		}
		// We fill the Frequency Use
		for (FrequencyUse fu : FrequencyUse.values()) {
		    frequencyUseSelect.addItem(fu.getId());
		    frequencyUseSelect.setItemCaption(fu.getId(), resourceBundle.getString(fu.getValue()));
		}
		// We fill the No Using Time
		for (TimeUse tu : TimeUse.values()) {
			noUsingTimeSelect.addItem(tu.getId());
			noUsingTimeSelect.setItemCaption(tu.getId(),resourceBundle.getString(tu.getValue()));
		}
    }

	public ISkillService getSkillService() {
		return skillService;
	}

	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
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

	public BeanFieldGroup<Skill> getBinder() {
		return binder;
	}

	public void setBinder(BeanFieldGroup<Skill> binder) {
		this.binder = binder;
	}

	public HorizontalLayout getCategoryAndConceptLayout() {
		return categoryAndConceptLayout;
	}

	public void setCategoryAndConceptLayout(
			HorizontalLayout categoryAndConceptLayout) {
		this.categoryAndConceptLayout = categoryAndConceptLayout;
	}
	
	

}
