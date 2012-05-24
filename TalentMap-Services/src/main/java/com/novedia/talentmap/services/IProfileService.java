package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;
/**
 * Interface Profile Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services
 * @created 21 mai 2012
 */
public interface IProfileService {
	
	/**
	 * Select all Profiles
	 * @class IProfileService.java
	 * @return
	 * @throws Exception
	 */
	List<Profile> getAllProfile() throws Exception;
	
	/**
	 * Get One Profile By Id
	 * @class IProfileService.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Profile getProfile(int id) throws Exception;
	
	/**
	 * Get One Profile By Type
	 * @class IProfileService.java
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Profile getProfile(String type) throws Exception;
}
