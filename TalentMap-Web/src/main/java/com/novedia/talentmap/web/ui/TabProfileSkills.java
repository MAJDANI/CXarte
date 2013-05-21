/**
 * 
 */
package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.profile.skill.SkillCollaboratorContent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author v.dibi
 * 
 */
public class TabProfileSkills extends VerticalLayout {
    private SkillCollaboratorContent skillCollaboratorContent;

    public TabProfileSkills(SkillCollaboratorContent skillCollaboratorContent) {
	super();
	this.skillCollaboratorContent = skillCollaboratorContent;
	mainBuild();
    }

    public void mainBuild() {
	addComponent(this.skillCollaboratorContent);
    }

    /**
     * @return the skillCollaboratorContent
     */
    public SkillCollaboratorContent getSkillCollaboratorContent() {
	return skillCollaboratorContent;
    }

    /**
     * @param skillCollaboratorContent
     *            the skillCollaboratorContent to set
     */
    public void setSkillCollaboratorContent(
	    SkillCollaboratorContent skillCollaboratorContent) {
	this.skillCollaboratorContent = skillCollaboratorContent;
    }

}