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
public class TabProfileAdminstrativeData extends VerticalLayout{
	private ProfileCollaboratorContent profileCollabContent;

	public TabProfileAdminstrativeData(ProfileCollaboratorContent profileCollabContent) {
		super();
		this.profileCollabContent = profileCollabContent;
		addComponent(this.profileCollabContent);
	}

	/**
	 * @return the profileCollabContent
	 */
	public ProfileCollaboratorContent getProfileCollabContent() {
		return profileCollabContent;
	}

	/**
	 * @param profileCollabContent the profileCollabContent to set
	 */
	public void setProfileCollabContent(
			ProfileCollaboratorContent profileCollabContent) {
		this.profileCollabContent = profileCollabContent;
	}

	
}
