/**
 * 
 */
package com.novedia.talentmap.services.impl;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
import com.novedia.talentmap.services.IAuthenticationService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.AuthenticationDao;

/**
 * This {@link AuthenticationService} authenticates user
 * 
 * @author v.dibi
 * 
 */
public class AuthenticationService implements IAuthenticationService {

    /**
     * The authentification DAO.
     */
    private IDao<Authentication> authenticationDao;

    /**
     * Inherited
     */
    @Override
    public Authentication checkUser(CredentialToken token) {
	AuthenticationDao authentificationDao = (AuthenticationDao) this.authenticationDao;
	return authentificationDao.check(token);
    }

    
    
    @Override
    public String encodePassword(String password) {
    	Md5PasswordEncoder md5Encoder = new Md5PasswordEncoder();
    	String encodedPassword = md5Encoder.encodePassword(password, null);
    	return encodedPassword;
    }
    
    
    
    /**
     * Set the authentication DAO
     * 
     * @param authenticationDao
     *            the authenticationDao to set
     */
    public void setAuthenticationDao(IDao<Authentication> authenticationDao) {
	this.authenticationDao = authenticationDao;
    }


}
