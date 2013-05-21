package com.novedia.talentmap.web.ui;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.web.commons.ConstantsEnglish;
import com.novedia.talentmap.web.ui.profile.ProfileView;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * The Profile tab contains the profile view and also the collaborator view (CM)
 * 
 * @author j.collet
 * @project TalentMap-Web
 * @package com.novedia.talentmap.web.ui
 * @created 21 mai 2012
 */

@SuppressWarnings("serial")
public class TabProfileSheet extends TabSheet {

	/**
	 * All views
	 */
	private ProfileView profileView;

	private Authentication authentication;

	/**
	 * Default constructor
	 */
	public TabProfileSheet() {
		super();
	}

	/**
	 * content of Profile tabulation
	 */
	VerticalLayout tabProfileView;

	/**
	 * Build the profile sheet
	 * 
	 * @return
	 */
	public TabSheet buildTabSheetProfile() {
		this.removeAllComponents();
		setImmediate(true);
		profileView.setAuthentication(getAuthentication());
		tabProfileView = profileView.buildTabSheetProfilData();
		addTab(tabProfileView, ConstantsEnglish.TAB_PROFILE_NAME);
		return this;
	}

	/**
	 * Set the profileView value
	 * 
	 * @param profileView
	 *            the profileView to set
	 */
	public void setProfileView(ProfileView profileView) {
		this.profileView = profileView;
	}

	/**
	 * 
	 * @return
	 */
	public Authentication getAuthentication() {
		return authentication;
	}

	/**
	 * 
	 * @param authentication
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}