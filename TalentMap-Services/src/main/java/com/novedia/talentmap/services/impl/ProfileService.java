package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.store.IProfileDao;

public class ProfileService implements IProfileService {
	
	private IProfileDao profileDao;

	@Override
	public List<Profile> getAllProfile() throws Exception {
		
		return profileDao.selectAll();
	}
	
	@Override
	public Profile getProfile(int id) throws Exception {
	
		return profileDao.getById(id);
	}
	
	/**
	 * Set the profileDao value
	 * @param profileDao the profileDao to set
	 */
	public void setProfileDao(IProfileDao profileDao) {
		this.profileDao = profileDao;
	}

}
