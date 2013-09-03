package com.novedia.talentmap.services.impl;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.services.IChangePasswordService;
import com.novedia.talentmap.store.IDao;

/**
 * ChangePasswordService class
 * 
 * @author b.tiomofofou
 * @version TMP 2.1
 */
public class ChangePasswordService implements IChangePasswordService {

    /**
     * authenticationDao
     */
    private IDao<Authentication> authenticationDao;

    @Override
    public Integer savePassword(Authentication authentication) {
	return authenticationDao.save(authentication);
    }

    /**
     * Get the authenticationDao
     * 
     * @return authenticationDao
     */
    public IDao<Authentication> getAuthenticationDao() {
	return authenticationDao;
    }

    /**
     * Set the authenticationDao
     * 
     * @param authenticationDao
     *            authenticationDao to set
     */
    public void setAuthenticationDao(IDao<Authentication> authenticationDao) {
	this.authenticationDao = authenticationDao;
    }

}
