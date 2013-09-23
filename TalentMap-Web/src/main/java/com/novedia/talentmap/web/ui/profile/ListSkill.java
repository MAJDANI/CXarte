package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.dto.CategoryMapDTO;
import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.services.ISkillService;
import com.novedia.talentmap.web.util.CUtils;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * List of Collaborator Skills
 * 
 * @author j.collet
 * @author b.tiomofofou
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */
public class ListSkill extends VerticalLayout implements ItemClickListener {

    private static final long serialVersionUID = 1557665108285765557L;

    /**
     * TalentMap Services
     */
    private ISkillService skillService;

    private Authentication authentication;

    /**
     * JAVA Objects
     */
    private CategoryMapDTO categoryMapDto;

    /**
     * Vaadin Components
     */
    private Table tableTools;
    private Accordion accCategory;
    private Accordion accConcept;

    /**
     * Default Constructor
     */
    public ListSkill() {
	super();
	setImmediate(true);
	setMargin(true);
    }

    /**
     * Build the view of list skill
     * 
     * @return
     */
    public ListSkill buildListSkill() {
	mainBuild();
	return this;
    }

    /**
     * The main builder
     * 
     * @class ListSkill.java
     */
    public void mainBuild() {
	removeAllComponents();
	try {

	    this.categoryMapDto = this.skillService
		    .getAllCollaboratorSkill(authentication.getColleagueId());

	} catch (Exception e) {

	    e.printStackTrace();
	}

	// Test if the Collaborator have one skill
	if (categoryMapDto != null
		&& !categoryMapDto.getMapCategory().isEmpty()) {
	    initComponents();
	    addComponent(buildListSkill(this.categoryMapDto));
	} else {
	    setVisible(false);
	}
    }

    /**
     * Initialize all component
     * 
     * @class ListSkill.java
     */
    private void initComponents() {
	this.tableTools = new Table();
	this.accCategory = new Accordion();
	this.accConcept = new Accordion();
    }

    /**
     * Build the list of the Skills
     * 
     * @class ListSkill.java
     */
    public Accordion buildListSkill(CategoryMapDTO categoryMapDto) {
	return CUtils.MapSkillToAccordionSkill(categoryMapDto, this);
    }

    @Override
    public void itemClick(ItemClickEvent event) {

	this.tableTools.setValue(null);
	setTableTools((Table) event.getSource());
    }

    /**
     * Get the tableTools value
     * 
     * @return the tableTools
     */
    public Table getTableTools() {
	return tableTools;
    }

    /**
     * 
     * @class ListSkill.java
     * @param tableTools
     */
    public void setTableTools(Table tableTools) {
	this.tableTools = tableTools;
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
     * Get the accCategory value
     * 
     * @return the accCategory
     */
    public Accordion getAccCategory() {
	return accCategory;
    }

    /**
     * Set the accCategory value
     * 
     * @param accCategory
     *            the accCategory to set
     */
    public void setAccCategory(Accordion accCategory) {
	this.accCategory = accCategory;
    }

    /**
     * Get the accConcept value
     * 
     * @return the accConcept
     */
    public Accordion getAccConcept() {
	return accConcept;
    }

    /**
     * Set the accConcept value
     * 
     * @param accConcept
     *            the accConcept to set
     */
    public void setAccConcept(Accordion accConcept) {
	this.accConcept = accConcept;
    }

    public Authentication getAuthentication() {
	return authentication;
    }

    public void setAuthentication(Authentication authentication) {
	this.authentication = authentication;
    }

    public ISkillService getSkillService() {
	return skillService;
    }

    public CategoryMapDTO getCategoryMapDto() {
	return categoryMapDto;
    }

    public void setCategoryMapDto(CategoryMapDTO categoryMapDto) {
	this.categoryMapDto = categoryMapDto;
    }

}
