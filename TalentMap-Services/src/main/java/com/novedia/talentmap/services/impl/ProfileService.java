package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.services.IProfileService;
import com.novedia.talentmap.store.IDao;

/**
 * Profile Services.
 * 
 * @author j.collet
 */
public class ProfileService implements IProfileService {
    /**
     * profile DAO.
     */
    private IDao<Profile> profileDao;

    /**
     * get profile DAO.
     * 
     * @return
     */
    public IDao<Profile> getProfileDao() {
	return profileDao;
    }

    /**
     * This method allowd to make the spring injection.
     * 
     * @param profileDao
     */
    public void setProfileDao(IDao<Profile> profileDao) {
	this.profileDao = profileDao;
    }

    /**
     * @return a list of profile.
     * @throws DataAccessException
     */
    @Override
    public List<Profile> getAllProfile() throws DataAccessException {
	return profileDao.getAll();
    }

    /**
     * Get the profile
     * 
     * @return a profile.
     * @throws DataAccessException
     */
    @Override
    public Profile getProfile(Integer id) throws DataAccessException {
	return profileDao.get(id);
    }

    /**
     * Get the profile by type
     * 
     * @return a profile.
     * @throws DataAccessException
     */
    @Override
    public Profile getProfile(String type) throws DataAccessException {
	// TODO Auto-generated method stub
	return null;
    }
}