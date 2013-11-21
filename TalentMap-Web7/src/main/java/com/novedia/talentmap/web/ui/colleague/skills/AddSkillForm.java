package com.novedia.talentmap.web.ui.colleague.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.TalentMapApplication;
import com.novedia.talentmap.web.utils.CUtils;
import com.novedia.talentmap.web.utils.PropertiesFile;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AddSkillForm extends VerticalLayout implements ValueChangeListener{
	
	/**
	 * Options for skills level
	 */
	public String[] OPTIONS ;
	
	/**
	 * Vaadin data binder
	 */
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
    
    private HorizontalLayout categoryAndConceptLayout;
    
    private HorizontalLayout toolAndScoreLayout;
    
    //TODO : à charger à l'instanciation avec une méthode afterpropertieSet
    private ResourceBundle resourceBundle;
    
    private HashMap<Integer, VSkill> skillMap = new HashMap<Integer, VSkill>();
    
    /**
     * Default constructor
     */
    public AddSkillForm() {
    	super();
    }
    
    /**
     * Build view of the skill form
     */
    public void buildAddSkillForm(Skill skill) {
    	Locale locale = TalentMapApplication.getCurrent().getLocale();
		resourceBundle = ResourceBundle.getBundle(PropertiesFile.TALENT_MAP_PROPERTIES, locale);
//		OPTIONS = new String[] { resourceBundle.getString("beginner"),
//				resourceBundle.getString("middle"),
//				resourceBundle.getString("confirmed"),
//				resourceBundle.getString("professional"),
//				resourceBundle.getString("expert") };
		OPTIONS = CUtils.buildOPTIONS(resourceBundle);
		
		removeAllComponents();
    	setImmediate(true);
		buildForm(skill);
    }
    
    /**
     * Create and initialize the form.
     */
    private void buildForm(Skill skill){
    	
    	categoryLabel.setCaption(resourceBundle.getString("category.label"));
    	conceptLabel.setCaption(resourceBundle.getString("concept.label"));
    	categoryAndConceptLayout.removeAllComponents();
    	toolAndScoreLayout.removeAllComponents();
    	toolAndScoreLayout.setSpacing(true);
    	categoryAndConceptLayout.setSpacing(true);
    	categoryAndConceptLayout.addComponent(categoryLabel);
    	categoryAndConceptLayout.addComponent(conceptLabel);
    	
    	//Init the Combos 
    	toolSelect = initSelectTools();
    	stars = initStars(skill);
		frequencyUseSelect = initSelectFrequencyUse();
		noUsingTimeSelect = initSelectTimeUse();
		
		
		binder = new BeanFieldGroup<Skill>(Skill.class);
		binder.setItemDataSource(skill);
		binder.setBuffered(false);
		binder.bindMemberFields(this);
		
		if(toolSelect.getValue() != null){
			categoryAndConceptLayout.setVisible(true);
		} else{
			categoryAndConceptLayout.setVisible(false);
		}
		
		toolAndScoreLayout.addComponent(categoryAndConceptLayout);
		toolAndScoreLayout.addComponent(toolSelect);
		toolAndScoreLayout.addComponent(stars);
		toolAndScoreLayout.addComponent(frequencyUseSelect);
		toolAndScoreLayout.addComponent(noUsingTimeSelect);
		addComponent(toolAndScoreLayout);
    }
    
    
    @Override
	public void valueChange(ValueChangeEvent event) {
    	if(toolSelect.getValue() != null){
    		Integer toolId = (Integer)toolSelect.getValue();
    		VSkill vSkill = skillMap.get(toolId);
    		categoryAndConceptLayout.setVisible(true);
    		categoryLabel.setValue(vSkill.getCategoryName());
    		conceptLabel.setValue(vSkill.getConceptName());
    	}
	}
    
    /**
     * Fill the RatingStars stars
     * @return the RatingStars filled
     */
    public RatingStars initStars(Skill skill) {
    	RatingStars stars = new RatingStars();
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
		return stars;
    }
    
    /**
     * Fill the Tools COmboBox
     * @return the ComboBox filled
     */
    public ComboBox initSelectTools() {

		List<VSkill> listVSkill = skillService.getAllVSkillOrdered();
		List<String> listToolName = new ArrayList<String>();
		List<String> listDoublonsToolName = new ArrayList<String>();
		ComboBox toolSelect = new ComboBox();
		
		for (VSkill vSkill : listVSkill) {
			String toolName = vSkill.getToolName();
			if(toolName != null) {
				//We fill the Map we will use when valueChange() is called
				skillMap.put(vSkill.getToolId(), vSkill);
				//we identify multiple tool names
				if (listToolName.contains(toolName)) {
					listDoublonsToolName.add(toolName);
				} else {
					listToolName.add(toolName);
				}
			}
		}
		for (VSkill vSkill : listVSkill) {
			String toolName = vSkill.getToolName();
			if(toolName != null) {
				String caption = toolName;
				if(listDoublonsToolName.contains(toolName)) {
					caption += " (" + vSkill.getCategoryName() + ")";
				}
				toolSelect.addItem(vSkill.getToolId());
				toolSelect.setItemCaption(vSkill.getToolId(), caption);
			}
		}
    	toolSelect.setCaption(resourceBundle.getString("tool.select.caption"));
		toolSelect.setNullSelectionAllowed(false);
		toolSelect.setImmediate(true);
		toolSelect.addValueChangeListener(this);

		return toolSelect;
		
    }
    
    /**
     * Fill the No Frequency Use ComboBox
     * @return the ComboBox filled
     */
    public ComboBox initSelectFrequencyUse() {
		// We fill the Frequency Use
		ComboBox frequencyUseSelect = new ComboBox();
   	
		for (FrequencyUse fu : FrequencyUse.values()) {
		    frequencyUseSelect.addItem(fu.getId());
		    frequencyUseSelect.setItemCaption(fu.getId(), resourceBundle.getString(fu.getValue()));
		}
		frequencyUseSelect.setCaption(resourceBundle.getString("frequency.use.select.caption"));
		frequencyUseSelect.setNullSelectionAllowed(false);
		frequencyUseSelect.setImmediate(true);
		return frequencyUseSelect;
    }
    
    /**
     * Fill the No Using Time ComboBox
     * @return the ComboBox filled
     */
    public ComboBox initSelectTimeUse() {
		// We fill the No Using Time
		ComboBox noUsingTimeSelect = new ComboBox();
		for (TimeUse tu : TimeUse.values()) {
			noUsingTimeSelect.addItem(tu.getId());
			noUsingTimeSelect.setItemCaption(tu.getId(),resourceBundle.getString(tu.getValue()));
		}
		noUsingTimeSelect.setCaption(resourceBundle.getString("no.using.time.select.caption"));
		noUsingTimeSelect.setNullSelectionAllowed(false);
		noUsingTimeSelect.setImmediate(true);
		return noUsingTimeSelect;
    }
    
    // GETTERS/ SETTERS
    
    /**
     * TODO : accessor javadoc
     * @return
     */
	public ISkillService getSkillService() {
		return skillService;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param skillService
	 */
	public void setSkillService(ISkillService skillService) {
		this.skillService = skillService;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public Label getCategoryLabel() {
		return categoryLabel;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param categoryLabel
	 */
	public void setCategoryLabel(Label categoryLabel) {
		this.categoryLabel = categoryLabel;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public Label getConceptLabel() {
		return conceptLabel;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param conceptLabel
	 */
	public void setConceptLabel(Label conceptLabel) {
		this.conceptLabel = conceptLabel;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public ComboBox getToolSelect() {
		return toolSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param toolSelect
	 */
	public void setToolSelect(ComboBox toolSelect) {
		this.toolSelect = toolSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public ComboBox getFrequencyUseSelect() {
		return frequencyUseSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param frequencyUseSelect
	 */
	public void setFrequencyUseSelect(ComboBox frequencyUseSelect) {
		this.frequencyUseSelect = frequencyUseSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public ComboBox getNoUsingTimeSelect() {
		return noUsingTimeSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param noUsingTimeSelect
	 */
	public void setNoUsingTimeSelect(ComboBox noUsingTimeSelect) {
		this.noUsingTimeSelect = noUsingTimeSelect;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public RatingStars getStars() {
		return stars;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param stars
	 */
	public void setStars(RatingStars stars) {
		this.stars = stars;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public BeanFieldGroup<Skill> getBinder() {
		return binder;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param binder
	 */
	public void setBinder(BeanFieldGroup<Skill> binder) {
		this.binder = binder;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @return
	 */
	public HorizontalLayout getCategoryAndConceptLayout() {
		return categoryAndConceptLayout;
	}
	
	/**
	 * TODO : accessor javadoc
	 * @param categoryAndConceptLayout
	 */
	public void setCategoryAndConceptLayout(HorizontalLayout categoryAndConceptLayout) {
		this.categoryAndConceptLayout = categoryAndConceptLayout;
	}

	public HorizontalLayout getToolAndScoreLayout() {
		return toolAndScoreLayout;
	}

	public void setToolAndScoreLayout(HorizontalLayout toolAndScoreLayout) {
		this.toolAndScoreLayout = toolAndScoreLayout;
	}
	
	
	
	
}
