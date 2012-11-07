package com.novedia.talentmap.web.ui.profile;

import java.util.List;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Category;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.data.FrequencyUse;
import com.novedia.talentmap.web.data.TimeUse;
import com.novedia.talentmap.web.util.IObservable;
import com.novedia.talentmap.web.util.IProfileView;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Select;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

/**
 * The Panel to add a Skill
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class AddSkillPanel extends Panel implements ClickListener,
		ValueChangeListener, Validator, IObservable {

	private static final long serialVersionUID = 3111770449952231327L;

	/**
	 * TalentMap Services
	 */
	private ISkillService skillService;

	/**
	 * Vaadin Components
	 */
	private Accordion skillTab;

	/**
	 * Vaadin UI
	 */
	private ListSkill listSkill;

	/**
	 * Vaadin Selects UI
	 */
	private Label categoryLabel;
	private Label conceptLabel;
	private Select toolSelect;
	private Select frequencyUseSelect;
	private Select noUsingTimeSelect;

	/**
	 * Vaadin Widget : RatingStars
	 */
	private RatingStars stars;

	/**
	 * Object JAVA
	 */
	private static Map<Integer, String> valueOptions;

	/**
	 * Util Observateur
	 */
	private IProfileView obs;

	/**
	 * Vaadin Buttons
	 */
	private Button validSkill;

	/**
	 * Constants
	 */
	private int COLLAB_ID = 2;
	private final String ADD_SKILL_LABEL = "Ajouter une compétence";
	private final String CATEGORY_LABEL = "Catégorie :";
	private final String CONCEPT_LABEL = "Concept :";
	private final String TOOL_LABEL = "Outil :";
	private final String FREQUENCY_USE = "Usage de l'outil :";
	private final String NO_TIME_USING = "Durée de non utilisation de l'outil :";
	private static final String[] OPTIONS = new String[] { "Débutant",
			"Intermédiare", "Professionnel", "Maitrise", "Expert" };
	
	/**
	 * Flag
	 */
	private boolean isNewSkill;
	
	/**
	 * 
	 * Build the class AddSkillPanel.java
	 * 
	 * @param categoryLabel
	 * @param conceptLabel
	 * @param toolSelect
	 * @param frequencyUseSelect
	 * @param noUsingTimeSelect
	 * @param stars
	 * @param valueOptions
	 * @param validSkill
	 * @param skillService
	 * @param listSkill
	 * @throws Exception
	 */
	public AddSkillPanel(Label categoryLabel, Label conceptLabel,
			Select toolSelect, Select frequencyUseSelect,
			Select noUsingTimeSelect, RatingStars stars,
			Map<Integer, String> valueOptions, Button validSkill,
			ISkillService skillService, ListSkill listSkill, Accordion skillTab)
			throws Exception {
		this.categoryLabel = categoryLabel;
		this.conceptLabel = conceptLabel;
		this.frequencyUseSelect = frequencyUseSelect;
		this.noUsingTimeSelect = noUsingTimeSelect;
		this.toolSelect = toolSelect;
		this.stars = stars;
		this.valueOptions = valueOptions;
		this.validSkill = validSkill;
		this.skillService = skillService;
		this.listSkill = listSkill;
		this.skillTab = skillTab;
		
		//Set the skill added like a new skill
		this.isNewSkill = true;

		setImmediate(true);

		buildWindow();
	}

	/**
	 * Build all components in the main window
	 * 
	 * @class AddSkillPanel.java
	 * @throws Exception
	 */
	private void buildWindow() throws Exception {

		buildSelect();

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

		Label addSkillLabel = new Label(ADD_SKILL_LABEL);
		addSkillLabel.setStyle(TalentMapCSS.H2);

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
	private void buildSelect() throws Exception {

		// We build the Category Select
		this.categoryLabel.setCaption(CATEGORY_LABEL);
		this.categoryLabel.setImmediate(true);
		this.categoryLabel.setStyleName("category-select");

		// We build the Concept Select
		this.conceptLabel.setCaption(CONCEPT_LABEL);
		this.conceptLabel.setImmediate(true);
		this.conceptLabel.setStyleName("concept-select");

		// We build the Tool Select
		this.toolSelect.setCaption(TOOL_LABEL);
		this.toolSelect.setNullSelectionAllowed(false);
		this.toolSelect.addListener(this);
		this.toolSelect.addValidator(this);
		this.toolSelect.setImmediate(true);
		this.toolSelect.setStyleName("tool-select");

		// We build the Frequency Use Select
		this.frequencyUseSelect.setCaption(FREQUENCY_USE);
		this.frequencyUseSelect.setNullSelectionAllowed(false);
		this.frequencyUseSelect.setImmediate(true);
		this.frequencyUseSelect.setStyleName("frequency-use-select");

		// We build the No Using Time Select
		this.noUsingTimeSelect.setCaption(NO_TIME_USING);
		this.noUsingTimeSelect.setNullSelectionAllowed(false);
		this.noUsingTimeSelect.setImmediate(true);
		this.noUsingTimeSelect.setStyleName("no-using-time-select");
		this.noUsingTimeSelect.setWidth(210);

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
		stars.setWidth(150);

		for (int i = 0; i < OPTIONS.length; i++) {
			stars.setValueCaption(i + 1, OPTIONS[i]);
		}

	}

	/**
	 * Fill the Select Tool
	 * 
	 * @class AddSkillPanel.java
	 * @throws Exception
	 */
	private void fillSelect() throws Exception {

		// We fill only the Tool Select
		List<Tool> listTool = skillService.getAllTools();

		for (Tool t : listTool) {
			this.toolSelect.addItem(t.getName());
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
		this.validSkill.addListener((ClickListener) this);
	}

	/**
	 * The button click event
	 */
	@Override
	public void buttonClick(ClickEvent event) {

		Button button = event.getButton();

		if (button == this.validSkill) {

			if (!isValid(toolSelect.getValue()) || !isValid(stars.getValue())
					|| !isValid(frequencyUseSelect.getValue())
					|| !isValid(noUsingTimeSelect.getValue())) {

				getWindow().showNotification(
						"Les champs ne sont pas tous remplis",
						Notification.TYPE_ERROR_MESSAGE);
			} else {
				
				this.addOneSkill();
			}

		}
	}

	private void addOneSkill() {

		try {

			Tool tool = skillService.getToolByName(this.toolSelect.getValue()
					.toString());

			Double starsValue = (Double) this.stars.getValue();
			int frequencyUseValue = 0;
			int noUsingTimeValue = 0;

			for (FrequencyUse fu : FrequencyUse.values()) {
				if (fu.getValue().equals(
						this.frequencyUseSelect.getValue().toString())) {
					frequencyUseValue = fu.getId();
				}
			}

			for (TimeUse tu : TimeUse.values()) {
				if (tu.getValue().equals(
						this.noUsingTimeSelect.getValue().toString())) {
					noUsingTimeValue = tu.getId();
				}
			}
			
			Skill skill = new Skill();

			skill.setCollaborator_id(COLLAB_ID);
			skill.setTool_id(tool.getId());
			skill.setScore(starsValue.intValue());
			skill.setUse_frequency(frequencyUseValue);
			skill.setNo_using_time(noUsingTimeValue);
			
			// Test if it's a new skill or not
			if(this.isNewSkill){
				
				// Check if the Collaborator already have the Skill
				if (!hasSkill(tool.getId())) {
					
					addOneNewSkill(skill);

				} else {

					getWindow().showNotification(
							"Vous possédez déjà cette compétence",
							Notification.TYPE_WARNING_MESSAGE);
				}
				
			}else{
				
				updateOneSkill(skill);
				
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	private void updateOneSkill(Skill skill) throws Exception{
		
		this.skillService.saveSkill(skill);

		getWindow().showNotification("Compétence modifiée",
				Notification.TYPE_TRAY_NOTIFICATION);

		this.updateMapSkill();
	}
	
	private void addOneNewSkill(Skill skill) throws Exception{
		
		this.skillService.addSkill(skill);

		getWindow().showNotification("Compétence ajoutée",
				Notification.TYPE_TRAY_NOTIFICATION);

		this.updateMapSkill();
		
	}
	
	private void updateMapSkill() throws Exception{
		
		Map<Category, Map> mapSkill = this.skillService
				.getAllCollaboratorSkill(COLLAB_ID);

		this.listSkill = new ListSkill(mapSkill);

		this.updateObservateur();
	}

	/**
	 * Test if the Collaborator already have the Skill
	 * 
	 * @class AddSkillPanel.java
	 * @param tId
	 * @return
	 * @throws Exception
	 */
	private boolean hasSkill(Integer tId) {
		Skill skill = null;
		
		try {

			skill = this.skillService.getSkillByToolId(COLLAB_ID, tId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (skill == null)
			return false;
		else
			return true;
	}

	/**
	 * The value change event
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();

		if (property == this.toolSelect) {

			try {

				updateSelect(this.toolSelect.getValue().toString());

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}

	/**
	 * The Concept Select is updated by the choice of the Category
	 * 
	 * @class AddSkillPanel.java
	 * @param categorytName
	 * @throws Exception
	 */
	private void updateSelect(String toolName) throws Exception {
		//TODO Question : un appel à la base à chaque sélection d'outil dans la combo?
		VSkill skill = skillService.getSkillByTool(toolName);

		this.conceptLabel.setValue(skill.getConcept_name());
		this.categoryLabel.setValue(skill.getCategory_name());

	}

	/**
	 * Validator methods
	 */

	@Override
	public void validate(Object value) throws InvalidValueException {

	}

	@Override
	public boolean isValid(Object value) {
		if (value == null) {
			return false;
		} else {
			return true;
		}
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
	 * Set the categorySelect value
	 * 
	 * @param categorySelect
	 *            the categorySelect to set
	 */
	public void setCategoryLabel(Label categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	/**
	 * Set the conceptSelect value
	 * 
	 * @param conceptSelect
	 *            the conceptSelect to set
	 */
	public void setConceptLabel(Label conceptLabel) {
		this.conceptLabel = conceptLabel;
	}

	/**
	 * Set the toolSelect value
	 * 
	 * @param toolSelect
	 *            the toolSelect to set
	 */
	public void setToolSelect(Select toolSelect) {
		this.toolSelect = toolSelect;
	}

	/**
	 * Set the valid value
	 * 
	 * @param valid
	 *            the valid to set
	 */
	public void setValidSkill(Button validSkill) {
		this.validSkill = validSkill;
	}

	/**
	 * Set the stars value
	 * 
	 * @param stars
	 *            the stars to set
	 */
	public void setStars(RatingStars stars) {
		this.stars = stars;
	}

	/**
	 * Set the valueOptions value
	 * 
	 * @param valueOptions
	 *            the valueOptions to set
	 */
	public void setValueOptions(Map<Integer, String> valueOptions) {
		this.valueOptions = valueOptions;
	}

	/**
	 * Set the frequencyUseSelect value
	 * 
	 * @param frequencyUseSelect
	 *            the frequencyUseSelect to set
	 */
	public void setFrequencyUseSelect(Select frequencyUseSelect) {
		this.frequencyUseSelect = frequencyUseSelect;
	}

	/**
	 * Set the noUsingTimeSelect value
	 * 
	 * @param noUsingTimeSelect
	 *            the noUsingTimeSelect to set
	 */
	public void setNoUsingTimeSelect(Select noUsingTimeSelect) {
		this.noUsingTimeSelect = noUsingTimeSelect;
	}

	/**
	 * Set the listSkill value
	 * 
	 * @param listSkill
	 *            the listSkill to set
	 */
	public void setListSkill(ListSkill listSkill) {
		this.listSkill = listSkill;
	}

	/**
	 * Set the skillTab value
	 * 
	 * @param skillTab
	 *            the skillTab to set
	 */
	public void setSkillTab(Accordion skillTab) {
		this.skillTab = skillTab;
	}
	
	/**
	 * Get the toolSelect value
	 * @return the toolSelect
	 */
	public Select getToolSelect() {
		return toolSelect;
	}

	@Override
	public void addObservateur(Object observateur, Class<?> cl) {
		this.obs = (IProfileView) observateur;
	}

	@Override
	public void updateObservateur() {
		this.obs.updateListSkill(this.listSkill);
	}

	@Override
	public void delObservateur() {
		this.obs = null;
	}
	
	/**
	 * Get the isNewSkill value
	 * @return the isNewSkill
	 */
	public boolean isNewSkill() {
		return isNewSkill;
	}

	/**
	 * Set the isNewSkill value
	 * @param isNewSkill the isNewSkill to set
	 */
	public void setNewSkill(boolean isNewSkill) {
		this.isNewSkill = isNewSkill;
	}


}
