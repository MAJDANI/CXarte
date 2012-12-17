
package com.novedia.talentmap.services;

import com.novedia.talentmap.model.entity.Authentication;
import com.novedia.talentmap.model.entity.CredentialToken;
/**
 * Interface Authentification Services.
 * @author v.dibi
 * @author v.guillemain
 */

public interface IAuthenticationService {	

/**
 * Checks if user with credentials info exist in Database
 * @param authentication
 * @return
 */
Authentication checkUser(CredentialToken token) ;


}