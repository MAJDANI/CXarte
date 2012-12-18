/**
 * 
 */
package com.novedia.talentmap.services.impl;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IAuthenticationService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.AuthenticationDao;

/**
 * This {@link AuthenticationService} authenticates user
 * @author v.dibi
 *
 */
public class AuthenticationService implements IAuthenticationService {

	/**
	 * The authentification DAO.
	 */
	private IDao<Authentication> authenticationDao;

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.services.IAuthentificationService#checkAuthentification(java.lang.String, java.lang.String)
	 */
	@Override
	public Authentication checkUser(CredentialToken token) {
		AuthenticationDao authentificationDao = (AuthenticationDao) this.authenticationDao;
		return authentificationDao.check(token);
	}

	/**
	 * Set the authentication DAO
	 * @param authenticationDao the authenticationDao to set
	 */
	public void setAuthenticationDao(IDao<Authentication> authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

}
