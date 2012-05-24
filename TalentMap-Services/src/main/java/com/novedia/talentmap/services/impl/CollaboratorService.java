package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.ICollaboratorDao;

/**
 * Collaborator Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class CollaboratorService implements ICollaboratorService {
	
	private ICollaboratorDao collabDao;
	
	
	/**
	 * Select All Collaborators
	 */
	@Override
	public List<Collaborator> getAllCollaborator() throws Exception {
		
		return collabDao.selectAll();
	}
	
	/**
	 * Get One Collaborator By ID
	 */
	@Override
	public Collaborator getCollaborator(int id) throws Exception {
		
		return collabDao.getById(id);
	}
	
	/**
	 * Update One Collaborator
	 */
	@Override
	public int updateCollaborator(Collaborator collaborator) throws Exception {
		
		return collabDao.update(collaborator);
	}
	
	/**
	 * Set the collabDao value
	 * @param collabDao the collabDao to set
	 */
	public void setCollabDao(ICollaboratorDao collabDao) {
		this.collabDao = collabDao;
	}
	

}
