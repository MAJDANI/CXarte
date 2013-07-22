package com.novedia.talentmap.web.ui.profile.skill;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.data.FrequencyUse;
import com.novedia.talentmap.web.data.TimeUse;
import com.novedia.talentmap.web.ui.profile.AddSkillPanel;
import com.novedia.talentmap.web.ui.profile.ListSkill;
import com.novedia.talentmap.web.util.IProfileView;
import com.novedia.talentmap.web.util.TalentMapCSS;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;

public class SkillCollaboratorContent extends VerticalLayout implements
	ClickListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6401799782431453193L;

    private ListSkill listSkill;
    private Panel listSkillPanel;
    private Panel skillPanel;
    private AddSkillPanel addSkillPanel;
    private VerticalLayout bodyLayout;
    private HorizontalLayout footerLayout;

    private Authentication authentication;

    /**
     * Vaadin Components
     */
    private Label skillLabel;

    private Button edit;
    private Button cancel;
    private Button addSkill;

    /**
     * Default constructor
     */
    public SkillCollaboratorContent() {
	super();
    }

    /**
     * Build the view of SkillCollaboratorContent
     * 
     * @return SkillCollaboratorContent
     */
    public VerticalLayout buildSkillCollaboratorContent() {
	setImmediate(true);
	listSkill.setAuthentication(getAuthentication());
	addSkillPanel.setAuthentication(getAuthentication());
	buildObersvators();
	mainBuild();
	return this;
    }

    /**
     * The main builder
     * 
     * @class ProfileView.java
     */
    public void mainBuild() {
	removeAllComponents();
	listSkill = listSkill.buildListSkill();
	addSkillPanel = addSkillPanel.buildAddSkillPanel();
	buildSkillLayout();
	buildButtonLayout();
	addComponent(this.bodyLayout);
	addComponent(this.footerLayout);
    }

    /**
     * Build method for the Skill Layout
     * 
     * @class ProfileView.java
     * @return
     */
    public void buildSkillLayout() {
	this.skillLabel.addStyleName(TalentMapCSS.H2);
	if (this.listSkill.getCategoryMapDto().getMapCategory() != null
		&& !listSkill.getCategoryMapDto().getMapCategory().isEmpty()) {
	    buildAddSkillPanel();
	    this.skillPanel.setVisible(false);
	    this.addSkillPanel.setVisible(false);
	    buildListSkillPanel();
	} else {
	    this.edit.setVisible(false);
	    buildAddSkillPanel();
	    skillPanel.setVisible(true);
	}

    }

    /**
     * Build method for the SkillPanel Layout
     * 
     * @class ProfileView.java
     * @param skillPanel
     */
    public void buildAddSkillPanel() {
	bodyLayout.removeAllComponents();
	VerticalLayout vLayout = new VerticalLayout();
	HorizontalLayout hLayout = new HorizontalLayout();
	HorizontalLayout hLayout2 = new HorizontalLayout();
	skillPanel.removeAllComponents();
	skillLabel.setCaption(ConstantsEnglish.ADD_SKILL_LABEL);
	this.skillPanel.addComponent(skillLabel);

	Label question = new Label("Would you choose your skills?");

	this.addSkill.setCaption("Yes");

	vLayout.setSpacing(true);
	vLayout.addComponent(question);
	vLayout.addComponent(this.addSkill);
	vLayout.setComponentAlignment(this.addSkill, Alignment.MIDDLE_CENTER);

	hLayout.setMargin(true);
	hLayout.addComponent(vLayout);

	hLayout2.addComponent(hLayout);
	hLayout2.setSizeFull();
	hLayout2.setComponentAlignment(hLayout, Alignment.MIDDLE_CENTER);

	this.skillPanel.addComponent(hLayout2);
	this.bodyLayout.addComponent(this.skillPanel);

	this.addSkillPanel.setVisible(false);
	this.bodyLayout.addComponent(this.addSkillPanel);
    }

    /**
     * Build the List of Skill
     * 
     * @class ProfileView.java
     * @param skillPanel
     */
    public void buildListSkillPanel() {
	this.listSkillPanel.removeAllComponents();

	this.addSkill.setCaption("Add skill");
	this.addSkill.setEnabled(true);

	this.skillLabel.setCaption(ConstantsEnglish.SKILL_LABEL);
	this.listSkillPanel.addComponent(this.skillLabel);
	this.listSkillPanel.addComponent(this.addSkill);
	this.listSkillPanel.addComponent(this.listSkill);
	this.skillLabel.addStyleName(TalentMapCSS.H2);

	this.bodyLayout.addComponent(this.listSkillPanel);
    }

    /**
     * Build method for the Button Layout
     * 
     * @class ProfileView.java
     * @return
     */

    public void buildButtonLayout() {
	HorizontalLayout hLayout = new HorizontalLayout();
	footerLayout.removeAllComponents();

	this.edit.setCaption(ConstantsEnglish.EDIT_CAPTION);
	this.edit.setDisableOnClick(true);
	this.edit.addListener(this);

	this.cancel.setCaption(ConstantsEnglish.CANCEL_CAPTION);
	this.cancel.addListener(this);

	this.addSkill.setDisableOnClick(true);
	this.addSkill.addListener(this);

	hLayout.setSpacing(true);
	hLayout.addComponent(this.edit);
	hLayout.addComponent(this.cancel);

	this.footerLayout.setSizeFull();
	this.footerLayout.setMargin(true);
	this.footerLayout.setStyleName(TalentMapCSS.FOOTER_PROFILE);

	this.footerLayout.addComponent(hLayout);
	this.footerLayout.setComponentAlignment(hLayout,
		Alignment.MIDDLE_CENTER);

    }

    /**
     * Button Click Listener
     */
    @Override
    public void buttonClick(ClickEvent event) {

	Button button = event.getButton();

	// Edit Button
	if (button == this.edit) {

	    if (this.setAddSkillPanelWithTool()) {
		this.addSkillPanel.setNewSkill(false);
		this.addSkillPanel.setVisible(true);
		this.addSkill.setEnabled(false);
	    } else {
		this.edit.setEnabled(true);
	    }

	    // Cancel Button
	} else if (button == this.cancel) {
	    edit.setEnabled(true);
	    addSkill.setEnabled(true);
	    mainBuild();

	    // Add Skill Panel button
	} else if (button == this.addSkill) {
	    this.edit.setEnabled(false);
	    this.skillPanel.setVisible(false);

	    this.addSkillPanel.setNewSkill(true);
	    this.addSkillPanel.setVisible(true);
	    this.addSkillPanel.getToolSelect().setReadOnly(false);

	    this.addSkillPanel.eraseAllSelects();
	    this.addSkillPanel.getCategoryLabel().setValue("");
	    this.addSkillPanel.getConceptLabel().setValue("");

	}
    }

    /**
     * We set the AddSkillPanel with the row selected in the tool table
     * 
     * @class ProfileView.java
     * @return
     */
    public boolean setAddSkillPanelWithTool() {

	Object rowId = this.listSkill.getTableTools().getValue(); // get the
	// selected
	// rows id

	if (rowId != null) {
	    // -------------------
	    // ToolName
	    // -------------------
	    String toolName = (String) this.listSkill.getTableTools()
		    .getContainerProperty(rowId, "Tool Name").getValue();
	    this.addSkillPanel.getToolSelect().setReadOnly(false);
	    this.addSkillPanel.getToolSelect().setValue(toolName);
	    this.addSkillPanel.getToolSelect().setNullSelectionAllowed(false);
	    this.addSkillPanel.getToolSelect().setReadOnly(true);

	    // -------------------
	    // toolScore
	    // -------------------
	    Integer toolScore = (Integer) this.listSkill.getTableTools()
		    .getContainerProperty(rowId, "score").getValue();
	    this.addSkillPanel.getStars().setValue(toolScore);

	    // -------------------
	    // toolUseFrequency
	    // -------------------
	    // TODO : trouver comment faire plus simplement, par exemple :
	    // this.addSkillPanel.getFrequencyUseSelect().setValue(toolUseFrequency);
	    Integer toolUseFrequency = (Integer) this.listSkill.getTableTools()
		    .getContainerProperty(rowId, "use_frequency").getValue();
	    for (FrequencyUse fu : FrequencyUse.values()) {
		if (fu.getId() == toolUseFrequency) {
		    this.addSkillPanel.getFrequencyUseSelect().setValue(
			    fu.getValue());
		    break;
		}
	    }

	    // -------------------
	    // toolNoUsingTime
	    // -------------------
	    // TODO : trouver comment faire plus simplement, par exemple :
	    // this.addSkillPanel.getNoUsingTimeSelect().setValue(toolNoUsingTime);
	    Integer toolNoUsingTime = (Integer) this.listSkill.getTableTools()
		    .getContainerProperty(rowId, "no_using_time").getValue();
	    for (TimeUse tu : TimeUse.values()) {
		if (tu.getId() == toolNoUsingTime) {
		    this.addSkillPanel.getNoUsingTimeSelect().setValue(
			    tu.getValue());
		    break;
		}
	    }
	    return true;

	} else {
	    getWindow().showNotification(
		    "Please select a tool in the array of skills",
		    Notification.TYPE_WARNING_MESSAGE);
	    return false;
	}

    }

    /**
     * Builder for all observator
     * 
     * @class ProfileView.java
     */
    private void buildObersvators() {

	/*
	 * Observable : AddSkillPanel
	 */
	this.addSkillPanel.addObservateur(new IProfileView() {

	    @Override
	    public void updateListSkill(ListSkill listSkill) {

		SkillCollaboratorContent.this.listSkill = listSkill;
		SkillCollaboratorContent.this.listSkill.setVisible(true);
		SkillCollaboratorContent.this.edit.setVisible(true);
		SkillCollaboratorContent.this.listSkill.setMargin(true);

		SkillCollaboratorContent.this.addSkillPanel.setVisible(false);
		SkillCollaboratorContent.this.edit.setEnabled(true);

		SkillCollaboratorContent.this.listSkillPanel
			.removeAllComponents();

		buildAddSkillPanel();
		buildListSkillPanel();

	    }

	}, IProfileView.class);

    }

    public ListSkill getListSkill() {
	return listSkill;
    }

    public void setListSkill(ListSkill listSkill) {
	this.listSkill = listSkill;
    }

    public Panel getListSkillPanel() {
	return listSkillPanel;
    }

    public void setListSkillPanel(Panel listSkillPanel) {
	this.listSkillPanel = listSkillPanel;
    }

    public Panel getSkillPanel() {
	return skillPanel;
    }

    public void setSkillPanel(Panel skillPanel) {
	this.skillPanel = skillPanel;
    }

    public AddSkillPanel getAddSkillPanel() {
	return addSkillPanel;
    }

    public void setAddSkillPanel(AddSkillPanel addSkillPanel) {
	this.addSkillPanel = addSkillPanel;
    }

    public VerticalLayout getBodyLayout() {
	return bodyLayout;
    }

    public void setBodyLayout(VerticalLayout bodyLayout) {
	this.bodyLayout = bodyLayout;
    }

    public HorizontalLayout getFooterLayout() {
	return footerLayout;
    }

    public void setFooterLayout(HorizontalLayout footerLayout) {
	this.footerLayout = footerLayout;
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    public Label getSkillLabel() {
	return skillLabel;
    }

    public void setSkillLabel(Label skillLabel) {
	this.skillLabel = skillLabel;
    }

    public Button getEdit() {
	return edit;
    }

    public void setEdit(Button edit) {
	this.edit = edit;
    }

    public Button getCancel() {
	return cancel;
    }

    public void setCancel(Button cancel) {
	this.cancel = cancel;
    }

    public Button getAddSkill() {
	return addSkill;
    }

    public void setAddSkill(Button addSkill) {
	this.addSkill = addSkill;
    }

}