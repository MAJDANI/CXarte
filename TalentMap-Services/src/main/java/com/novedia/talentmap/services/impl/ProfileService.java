package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.store.IDao;

/**
 * Profile Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class ProfileService implements IProfileService {
	
	/**
	 * profile DAO
	 */
	private IDao<Profile> profileDao;

	public IDao<Profile> getProfileDao() {
		return profileDao;
	}

	public void setProfileDao(IDao<Profile> profileDao) {
		this.profileDao = profileDao;
	}

	@Override
	public List<Profile> getAllProfile() throws DataAccessException {
		return profileDao.getAll();
	}

	@Override
	public Profile getProfile(Integer id) throws DataAccessException {	
		return profileDao.get(id);
	}

	@Override
	public Profile getProfile(String type) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}