package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.List;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.ISkillService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class AddSkillForm extends HorizontalLayout{
	
	public static final String[] OPTIONS = new String[] { "Beginner", "middle","professional", "Master", "Expert" };
	
	
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
    

    /**
     * Object JAVA
     */
//    private static Map<Integer, String> valueOptions;
    
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
    public AddSkillForm buildAddSkillForm(Skill skill) {
    	removeAllComponents();
    	setImmediate(true);
		buildForm(skill);
    	return this;
    }
    
    
    private void buildForm(Skill skill){
    	toolSelect.setCaption("Tool:");
		toolSelect.setNullSelectionAllowed(false);
		toolSelect.setImmediate(true);
		
		stars.setCaption("Tool Score: ");
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
		
		frequencyUseSelect.setCaption("Used Frequency:");
		frequencyUseSelect.setNullSelectionAllowed(false);
		frequencyUseSelect.setImmediate(true);
	
		// We build the No Using Time Select
		noUsingTimeSelect.setCaption("Not used time:");
		noUsingTimeSelect.setNullSelectionAllowed(false);
		noUsingTimeSelect.setImmediate(true);
		fillSelect();
		
		binder = new BeanFieldGroup<Skill>(Skill.class);
		binder.setItemDataSource(skill);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		addComponent(toolSelect);
		addComponent(stars);
		addComponent(frequencyUseSelect);
		addComponent(noUsingTimeSelect);
		
	
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
		    frequencyUseSelect.setItemCaption(fu.getId(), fu.getValue());
		}
		// We fill the No Using Time
		for (TimeUse tu : TimeUse.values()) {
			noUsingTimeSelect.addItem(tu.getId());
			noUsingTimeSelect.setItemCaption(tu.getId(),tu.getValue());
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
	
	

//	public static Map<Integer, String> getValueOptions() {
//		return valueOptions;
//	}

//	public static void setValueOptions(Map<Integer, String> valueOptions) {
//		AddSkillForm.valueOptions = valueOptions;
//	}

}
