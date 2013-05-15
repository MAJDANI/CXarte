package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Profile;

/**
 * The Profile Service Interface.
 * 
 * @author j.collet
 */

public interface IProfileService {
	/**
	 * Select all Profiles.
	 * 
	 * @class IProfileService.java
	 * @return a list of Profile
	 */
	List<Profile> getAllProfile();

	/**
	 * Get One Profile By Id.
	 * 
	 * @class IProfileService.java
	 * @param id
	 *            a id
	 * @return a Profile
	 */
	Profile getProfile(Integer id);

	/**
	 * Get One Profile By Type.
	 * 
	 * @class IProfileService.java
	 * @param type
	 *            a type
	 * @return a Profile
	 */
	Profile getProfile(String type);
}