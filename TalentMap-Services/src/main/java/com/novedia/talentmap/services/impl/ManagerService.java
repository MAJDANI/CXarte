package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ManagerDao;

/**
 * The manager service.
 * 
 * @author j.marie-sainte
 */
public class ManagerService implements IManagerService {

	/**
	 * manager DAO.
	 */
	private IDao<Manager> managerDao;

	/**
	 * collaborator DAO
	 */
	private IDao<Colleague> colleagueDao;

	/**
	 * This method allow to return all Colleagues by manager.
	 * 
	 * @return a list of collaborator
	 * @param managerId
	 * @throws DataAccessException
	 */
	@Override
	public List<Colleague> getAllColleagues(Integer managerId)
			throws DataAccessException {
		return ((ManagerDao) managerDao).getAllCollaborators(managerId);
	}

	/**
	 * Get One Collaborator By ID.
	 * 
	 * @param id
	 * @return a collaborator
	 * @throws DataAccessException
	 */
	@Override
	public Colleague getCollaborator(int id) throws DataAccessException {
		return colleagueDao.get(id);
	}

	/**
	 * This method allowd to make the spring injection. Set the manager DAO.
	 * 
	 * @param managerDao
	 */
	public void setManagerDao(IDao<Manager> managerDao) {
		this.managerDao = managerDao;
	}

	/**
	 * This method allowd to make the spring injection. Set the collaborator DAO
	 * 
	 * @param colleagueDao
	 */
	public void setColleagueDao(IDao<Colleague> colleagueDao) {
		this.colleagueDao = colleagueDao;
	}

}