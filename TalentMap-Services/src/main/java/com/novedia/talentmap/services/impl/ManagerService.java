package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.IManagerService;
import com.novedia.talentmap.store.ICollaboratorDao;
import com.novedia.talentmap.store.IManagerDao;

public class ManagerService implements IManagerService {

	private IManagerDao managerDao;
	private ICollaboratorDao collabDao;

	@Override
	public List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId)
			throws Exception {
		return managerDao.getAllCollaboratorsByManagerId(managerId);
	}
	
	/**
	 * Get One Collaborator By ID
	 */
	@Override
	public Collaborator getCollaborator(int id) throws Exception {
		
		return collabDao.getById(id);
	}
	
	/**
	 * Set the managerDao value
	 * @param managerDao the managerDao to set
	 */
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	
	/**
	 * Set the collabDao value
	 * @param collabDao the collabDao to set
	 */
	public void setCollabDao(ICollaboratorDao collabDao) {
		this.collabDao = collabDao;
	}
}
