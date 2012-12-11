
package com.novedia.talentmap.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Authentification;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.utils.DBRequestsConstants;
/**
 * Interface Authentification Services.
 * @author v.dibi
 * @author v.guillemain
 */

public interface IAuthentificationService {	

/**
 * Get One Profile By Login.
 * @class IAuthentificationService.java
 * @param login a login
 * @return a Authentification
 */
Collaborator getCollaboratorByLogin(String login);

/**
 * Update one Authentification.
 * @class IAuthentificationService.java
 * @param authentification a Authentification
 * @return int
 */
int saveAuthentification(Authentification authentification);

/**
 * Gets one Authentification.
 * @class IAuthentificationService.java
 * @param id the id of authentification to get
 * @return Authentification
 */
Authentification getAuthentification(Integer id);

/**
 * Gets all Authentifications.
 * @class IAuthentificationService.java
 * @return List<Authentification> the list of Authentifications
 */
List<Authentification> getAllAuthentification();

/**
 * Adds one Authentification.
 * @class IAuthentificationService.java
 * @param authentification the Authentification to add
 * @return int
 */
int addAuthentification(Authentification element);

/**
 * Checks one Authentification.
 * @class IAuthentificationService.java
 * @param String login and String password of the authentification to check
 * @return Authentification : the authentification found or null if not found
 */
Authentification checkAuthentification(String login, String password) ;


}