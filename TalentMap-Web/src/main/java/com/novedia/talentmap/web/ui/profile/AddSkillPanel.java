package com.novedia.talentmap.web.ui.profile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.model.entity.UserNotification;
import com.novedia.talentmap.model.entity.VSkill;
import com.novedia.talentmap.services.IAdminService;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.services.INotificationService;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
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

	// TalentMap Services
	private ISkillService skillService;
	private INotificationService notificationService;
	private IColleagueService colleagueService;
	private IAdminService adminService;

	private Authentication authentication;

	// Vaadin Components
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
	 * .Vaadin Widget : RatingStars.
	 */
	private RatingStars stars;

	/**
	 * Object JAVA
	 */
	private static Map<Integer, String> valueOptions;

	/**
	 * . Util! Observator.!
	 */
	private IProfileView obs;

	/**
	 * Vaadin Buttons
	 */
	private Button validSkill;

	private String ADD_SKILL = "ADD";
	private String UPDATE_SKILL = "UPDATE";

	/**
	 * Flag
	 */
	private boolean isNewSkill;

	/**
	 * Default constructor
	 */
	public AddSkillPanel() {
		super();
	}

	/**
	 * Build view of buildAddSkillPanel
	 * 
	 * @return
	 */
	public AddSkillPanel buildAddSkillPanel() {
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

		Label addSkillLabel = new Label(ConstantsEnglish.ADD_SKILL_LABEL);
		addSkillLabel.addStyleName(TalentMapCSS.H2);

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
		this.categoryLabel.setCaption(ConstantsEnglish.CATEGORY_LABEL);
		this.categoryLabel.setImmediate(true);
		this.categoryLabel.setStyleName("category-select");

		// We build the Concept Select
		this.conceptLabel.setCaption(ConstantsEnglish.CONCEPT_LABEL);
		this.conceptLabel.setImmediate(true);
		this.conceptLabel.setStyleName("concept-select");

		// We build the Tool Select
		this.toolSelect.setCaption(ConstantsEnglish.TOOL_LABEL);
		this.toolSelect.setNullSelectionAllowed(false);
		this.toolSelect.addListener(this);
		this.toolSelect.addValidator(this);
		this.toolSelect.setImmediate(true);
		this.toolSelect.setStyleName("tool-select");
		this.toolSelect.setWidth("150px");

		// We build the Frequency Use Select
		this.frequencyUseSelect.setCaption(ConstantsEnglish.FREQUENCY_USE);
		this.frequencyUseSelect.setNullSelectionAllowed(false);
		this.frequencyUseSelect.setImmediate(true);
		this.frequencyUseSelect.setStyleName("frequency-use-select");

		// We build the No Using Time Select
		this.noUsingTimeSelect.setCaption(ConstantsEnglish.NO_TIME_USING);
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

		for (int i = 0; i < ConstantsEnglish.OPTIONS.length; i++) {
			stars.setValueCaption(i + 1, ConstantsEnglish.OPTIONS[i]);
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
						ConstantsEnglish.MSG_MISSING_FIELDS,
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

			skill.setColleagueId(authentication.getColleagueId());
			skill.setTool_id(tool.getId());
			skill.setScore(starsValue.intValue());
			skill.setUse_frequency(frequencyUseValue);
			skill.setNo_using_time(noUsingTimeValue);

			// Test if it's a new skill or not
			if (this.isNewSkill) {

				// Check if the Collaborator already have the Skill
				if (!hasSkill(tool.getId())) {

					addOneNewSkill(skill);

				} else {

					getWindow().showNotification("You already have this skill",
							Notification.TYPE_WARNING_MESSAGE);
				}

			} else {

				updateOneSkill(skill);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void updateOneSkill(Skill skill) throws Exception {
		NotifyCm(UPDATE_SKILL, skill);
		this.skillService.saveSkill(skill);

		getWindow().showNotification("Skill changed",
				Notification.TYPE_TRAY_NOTIFICATION);

		this.updateMapSkill();
	}

	private void addOneNewSkill(Skill skill) throws Exception {
		NotifyCm(ADD_SKILL, skill);
		this.skillService.addSkill(skill);

		getWindow().showNotification("Skill added",
				Notification.TYPE_TRAY_NOTIFICATION);

		this.updateMapSkill();

	}

	private void updateMapSkill() throws Exception {

		this.listSkill = listSkill.buildListSkill();

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

			skill = this.skillService.getSkillByToolId(
					authentication.getColleagueId(), tId);

		} catch (Exception e) {

			e.printStackTrace();
		}

		if (skill == null) {
			return false;
		} else {
			return true;

		}
	}

	/**
	 * The value change event
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();

		if (property == this.toolSelect) {

			try {
				// TODO : this.toolSelect.getValue().toString() lance une NPE
				// quand on est dans le cas où on fait
				// un "Add Skill" : il n'y a pas de value dans toolSelect
				String toolValue = "";
				if (this.toolSelect.getValue() != null) {
					toolValue = this.toolSelect.getValue().toString();
				}
				updateSelect(toolValue);

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}

	/**
	 * The Concept Select is updated by the choice of the Category TODO : a
	 * revoir !!
	 * 
	 * @class AddSkillPanel.java
	 * @param categorytName
	 * @throws Exception
	 */
	private void updateSelect(String toolName) throws Exception {
		VSkill skill = null;
		if (toolName != "" && toolName != null) {
			// TODO Question : un appel à la base à chaque sélection d'outil
			// dans la combo?
			skill = skillService.getSkillByTool(toolName);
		}
		if (skill != null) {
			this.conceptLabel.setValue(skill.getConceptName());
			this.categoryLabel.setValue(skill.getCategoryName());
		} else {
			this.conceptLabel.setValue("");
			this.categoryLabel.setValue("");
		}
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

	public void NotifyCm(String type, Skill skill) {

		if (type.equals(ADD_SKILL)) {
			Colleague c = colleagueService.getColleague(skill.getColleagueId());
			Tool t = adminService.getTool(skill.getTool_id());

			String comment = c.getFirstName() + " " + c.getLastName()
					+ " added tool " + t.getName();
			Date date = new Date();
			UserNotification notification = UserNotification.builder()
					.colleagueId(skill.getColleagueId()).notes(comment)
					.date(date).build();
			this.notificationService.saveNotification(notification);
		} else if (type.equals(UPDATE_SKILL)) {
			Colleague c = colleagueService.getColleague(skill.getColleagueId());
			Tool t = adminService.getTool(skill.getTool_id());

			String comment = c.getFirstName() + " " + c.getLastName()
					+ " updated tool " + t.getName();
			Date date = new Date();
			UserNotification notification = UserNotification.builder()
					.colleagueId(skill.getColleagueId()).notes(comment)
					.date(date).build();
			this.notificationService.saveNotification(notification);
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

	public Label getCategoryLabel() {
		return categoryLabel;
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

	public Label getConceptLabel() {
		return conceptLabel;
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

	public RatingStars getStars() {
		return stars;
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

	public Select getFrequencyUseSelect() {
		return frequencyUseSelect;
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

	public Select getNoUsingTimeSelect() {
		return noUsingTimeSelect;
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
	 * 
	 * @return the toolSelect
	 */
	public Select getToolSelect() {
		return toolSelect;
	}

	/**
	 * This Methods empties all selections in the Add Skill Panel : The tool
	 * selected, Stars given, no using time and frequency use. The first idea
	 * was to select an "empty" choice in each Select, but as it didn't work,
	 * here we empty each select and refill completely in order to have the
	 * expected behavior. TODO : change the solution
	 */
	public void eraseAllSelects() {
		toolSelect.removeAllItems();
		frequencyUseSelect.removeAllItems();
		noUsingTimeSelect.removeAllItems();
		fillSelect();
		stars.setValue(0);
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
	 * 
	 * @return the isNewSkill
	 */
	public boolean isNewSkill() {
		return isNewSkill;
	}

	/**
	 * Set the isNewSkill value
	 * 
	 * @param isNewSkill
	 *            the isNewSkill to set
	 */
	public void setNewSkill(boolean isNewSkill) {
		this.isNewSkill = isNewSkill;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public Accordion getSkillTab() {
		return skillTab;
	}

	public ListSkill getListSkill() {
		return listSkill;
	}

	public static Map<Integer, String> getValueOptions() {
		return valueOptions;
	}

	public Button getValidSkill() {
		return validSkill;
	}

	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public IColleagueService getColleagueService() {
		return colleagueService;
	}

	public void setColleagueService(IColleagueService colleagueService) {
		this.colleagueService = colleagueService;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

}
