package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.ICollaboratorDao;

public class CollaboratorService implements ICollaboratorService {
	
	private ICollaboratorDao collabDao;
	
	@Override
	public List<Collaborator> getAllCollaborator() throws Exception {
		
		return collabDao.selectAll();
	}

	@Override
	public Collaborator getCollaborator(int id) throws Exception {
		
		return collabDao.getById(id);
	}

	@Override
	public void updateCollaborator(Collaborator collaborator) throws Exception {
		
		collabDao.update(collaborator);
	}
	
	/**
	 * Set the collabDao value
	 * @param collabDao the collabDao to set
	 */
	public void setCollabDao(ICollaboratorDao collabDao) {
		this.collabDao = collabDao;
	}
	

}
