package com.novedia.talentmap.model.entity;

import java.io.Serializable;

public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8766946810895701286L;
	private String id;
	private String profileType;
	/**
	 * Get the id value
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set the id value
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get the profileType value
	 * @return the profileType
	 */
	public String getProfileType() {
		return profileType;
	}
	/**
	 * Set the profileType value
	 * @param profileType the profileType to set
	 */
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
}
