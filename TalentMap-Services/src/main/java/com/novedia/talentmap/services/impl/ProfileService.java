package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.store.IProfileDao;

/**
 * Profile Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class ProfileService implements IProfileService {
	
	private IProfileDao profileDao;

	/**
	 * Select all Profiles
	 */
	@Override
	public List<Profile> getAllProfile() throws Exception {
		
		return profileDao.selectAll();
	}
	
	/**
	 * Get One Profile By Id
	 */
	@Override
	public Profile getProfile(int id) throws Exception {
	
		return profileDao.getById(id);
	}
	
	/**
	 * Get One Profile By Type
	 */
	public Profile getProfile(String type) throws Exception{
		
		return this.profileDao.getByType(type);
	}
	
	/**
	 * Set the profileDao value
	 * @param profileDao the profileDao to set
	 */
	public void setProfileDao(IProfileDao profileDao) {
		this.profileDao = profileDao;
	}

}
