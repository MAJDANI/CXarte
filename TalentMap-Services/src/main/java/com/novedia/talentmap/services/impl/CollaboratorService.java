package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.ICollaboratorDao;
import com.novedia.talentmap.store.IManagerDao;
import com.novedia.talentmap.store.IMissionDao;

/**
 * Collaborator Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class CollaboratorService implements ICollaboratorService {
	
	private ICollaboratorDao collabDao;
	private IMissionDao missionDao;
	private IManagerDao managerDao;

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
	 * Update one Mission
	 */
	@Override
	public int updateMission(Mission mission) throws Exception {
		
		return missionDao.update(mission);
	}
	
	/**
	 * Get One Mission By Collab_ID
	 */
	@Override
	public Mission getMission(int collab_id) throws Exception {
		
		return missionDao.getByCollabId(collab_id);
	}
	
	/**
	 * Get One Manager By ID
	 */
	@Override
	public Manager getManager(int manager_id) throws Exception {
		
		return managerDao.getById(manager_id);
	}
	
	/**
	 * Set the collabDao value
	 * @param collabDao the collabDao to set
	 */
	public void setCollabDao(ICollaboratorDao collabDao) {
		this.collabDao = collabDao;
	}
	
	/**
	 * Set the missionDao value
	 * @param missionDao the missionDao to set
	 */
	public void setMissionDao(IMissionDao missionDao) {
		this.missionDao = missionDao;
	}
	
	/**
	 * Set the managerDao value
	 * @param managerDao the managerDao to set
	 */
	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}


}
