package com.novedia.talentmap.web.ui.profile;

import com.novedia.talentmap.model.entity.Authentication;
import com.vaadin.ui.VerticalLayout;

/**
 * The profile View
 * @author e.moumbe
 *
 */

@SuppressWarnings("serial")
public class ProfileView extends VerticalLayout{

	/**
	 * profil Layout
	 */
	private ProfileLayout profileLayout;
	
	private Authentication authentication;
	
	/**
	 * Default constructor
	 */
	public ProfileView(){
		super();
	}
	
	
	/**
	 * Build the tabsheet that contains profil data
	 * @return
	 */
	public VerticalLayout buildTabSheetProfilData(){
		VerticalLayout profileView = new VerticalLayout();
		profileLayout.setAuthentication(getAuthentication());
		profileView.addComponent(profileLayout.buildProfileLayout());
		return profileView;
	}
	

	/**
	 * Get the profileLayout value
	 * @return the profileLayout
	 */
	public ProfileLayout getProfileLayout() {
		return profileLayout;
	}

	/**
	 * Set the profileLayout value
	 * @param profileLayout the profileLayout to set
	 */
	public void setProfileLayout(ProfileLayout profileLayout) {
		this.profileLayout = profileLayout;
	}


	public Authentication getAuthentication() {
		return authentication;
	}


	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	
	
	
}
