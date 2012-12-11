/**
 * 
 */
package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Authentification;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.IAuthentificationService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.AuthentificationDao;

/**
 * @author v.dibi
 *
 */
public class AuthentificationService implements IAuthentificationService{

	/**
	 * The authentification DAO.
	 */
	private IDao<Authentification> authentificationDao;
	

	@Override
	public int saveAuthentification(Authentification authentification) {
		return authentificationDao.save(authentification);
	}

	@Override
	public Authentification getAuthentification(Integer id) {
		return authentificationDao.get(id);
	}

	@Override
	public List<Authentification> getAllAuthentification() {
		return authentificationDao.getAll();
	}

	@Override
	public int addAuthentification(Authentification element) {
		return authentificationDao.add(element);
	}

	@Override
	public Authentification checkAuthentification(String login, String password) {
		return ((AuthentificationDao) authentificationDao).check(login, password);
	}

	@Override
	public Collaborator getCollaboratorByLogin(String login) {
		return ((AuthentificationDao) authentificationDao).getCollaboratorByLogin(login);
	}

	/**
	 * @param authentificationDao the authentificationDao to set
	 */
	public void setAuthentificationDao(IDao<Authentification> authentificationDao) {
		this.authentificationDao = authentificationDao;
	}


	
}
