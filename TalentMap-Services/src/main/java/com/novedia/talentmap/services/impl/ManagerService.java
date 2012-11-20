package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ManagerDao;

/**
 * The manager service
 * 
 * @author j.marie-sainte
 *
 */
public class ManagerService implements IManagerService {
	
	/**
	 * manager DAO
	 */
	private IDao<Manager> managerDao;
	
	/**
	 * collaborator DAO
	 */
	private  IDao<Collaborator> collaboratorDao;
	

	@Override
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId)
			throws DataAccessException {
		return ((ManagerDao) managerDao)
				.getAllCollaboratorsByManagerId(managerId);
	}
	
	/**
	 * Get One Collaborator By ID
	 */	
	@Override
	public Collaborator getCollaborator(int id) throws DataAccessException {
		return collaboratorDao.get(id);
	}
	
	/**
	 * Set the manager DAO
	 * @param managerDao
	 */
	public void setManagerDao(IDao<Manager> managerDao) {
		this.managerDao = managerDao;
	}
	
	/**
	 * Set the collaborator DAO
	 * @param collaboratorDao
	 */
	public void setCollaboratorDao(IDao<Collaborator> collaboratorDao) {
		this.collaboratorDao = collaboratorDao;
	}
	
	
}
