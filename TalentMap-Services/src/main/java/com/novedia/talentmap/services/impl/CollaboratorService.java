package com.novedia.talentmap.services.impl;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.ICollaboratorDao;
import com.novedia.talentmap.store.IManagerDao;
import com.novedia.talentmap.store.IMissionDao;
import com.novedia.talentmap.store.ISkillDao;

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
	private ISkillDao skillDao;

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
	 * Get One Mission By ID
	 */
	@Override
	public Mission getMission(int missionId) throws Exception {
		
		return missionDao.getById(missionId);
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
	
	/**
	 * Set the skillDao value
	 * @param skillDao the skillDao to set
	 */
	public void setSkillDao(ISkillDao skillDao) {
		this.skillDao = skillDao;
	}


	/**
	 * Select All Collaborators by lastName
	 * @param lastName : a lastName
	 * @return all collaborators who has the lastName specified
	 * @author v.guillemain
	 */
	@Override
	public List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws Exception {
		
		return collabDao.getAllCollaboratorsByLastName(lastName);
	}

	/**
	 * Select All Collaborators by toolId
	 * @param toolId : a toolId
	 * @return all collaborators who has a competence on the tool specified
	 * @author v.guillemain
	 */
	@Override
	public List<Collaborator> getAllCollaboratorsByToolId(String toolId) throws Exception {
		
		return skillDao.getAllCollaboratorsByToolId(toolId);
	}
	
	/**
	 * Select All Collaborators by toolId
	 * @param listToolId : a list of toolId
	 * @return all collaborators who has a competence on each tool of the list
	 * @author v.guillemain
	 */
	@Override
	public List<Collaborator> getAllCollaboratorsByListToolId(List<Integer> listToolId) throws Exception {
		
		/* we first get the collaborator's ID for all competences */
		List<Integer> listId = skillDao.getAllCollaboratorsIdByListToolId(listToolId);
		
		/* then we get the Collaborators corresponding */
		return (List<Collaborator>) collabDao.getAllCollaboratorsByListId(listId);
				
	}

	
	@Override
	public List<Mission> getAllMission(int collabId) throws Exception {
		
		return this.missionDao.getByCollabId(collabId);
	}

	/**
	 * Adding a mission in database
	 */
	@Override
	public int addMission(Mission mission) throws Exception {
		
		return this.missionDao.add(mission);
	}
	
	

}
