/**
 * 
 */
package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.web.ui.profile.ProfileCollaboratorContent;
import com.vaadin.ui.VerticalLayout;

/**
 * @author v.dibi
 * 
 */
@SuppressWarnings("serial")
public class TabProfileAdminstrativeData extends VerticalLayout {

	private ProfileCollaboratorContent profileColleagueContent;

	/**
	 * Default constructor
	 */
	public TabProfileAdminstrativeData() {
		super();
	}

	public VerticalLayout buildProfileColleagueConten() {
		removeAllComponents();
		this.addComponent(profileColleagueContent
				.buildProfileColleagueContent());
		return this;
	}

	/**
	 * @return the profileCollabContent
	 */
	public ProfileCollaboratorContent getProfileColleagueContent() {
		return profileColleagueContent;
	}

	/**
	 * @param profileCollabContent
	 *            the profileCollabContent to set
	 */
	public void setProfileColleagueContent(
			ProfileCollaboratorContent profileColleagueContent) {
		this.profileColleagueContent = profileColleagueContent;
	}

}
