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
public class CollaboratorService implements ICollaboratorService  {
	
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
	 * Inserts one Mission
	 */
	@Override
	public int insertMission(Mission mission) throws Exception {
		//TODO garder add(Mission) ou insert(Mission)
		return missionDao.insert(mission);
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
	public List<Collaborator> getAllCollaboratorsByToolId(int toolId) throws Exception {
		//TODO : cette méthode n'est appelée par personne??
		List<Integer> listCollaboratorId = skillDao.getAllCollaboratorsIdByToolId(toolId);
		return collabDao.getAllCollaboratorsByListId(listCollaboratorId);
	}
	
	/**
	 * Select All Collaborators for a list of toolId
	 * @param listToolId : a list of toolId
	 * @return all collaborators who has a competence on each tool of the list
	 * @author v.guillemain
	 */
	@Override
	public List<Collaborator> getAllCollaboratorsByListToolId (List<Integer> listToolId) throws Exception {
		System.out.println("CollaboratorService.getAllCollaboratorsByListToolId");
		List<Integer> listCollabId;
		List<Integer> listCollabId2;
		
		if (listToolId != null && !listToolId.isEmpty()) {
			System.out.println("CS ***** 1 ****** dans le if, listToolId=" + listToolId);
			int borneMax = listToolId.size();
			int firstTool = listToolId.get(0);
			
			//******************************************************************
			// POUR LE PREMIER TOOLID
			//******************************************************************
			System.out.println("CS ***** 2 ****** first tool ="+firstTool);
			listCollabId = skillDao.getAllCollaboratorsIdByToolId(firstTool);
			System.out.println("CS ***** 3 ****** listCollabId="+listCollabId+"pour tool="+firstTool);
		
			//******************************************************************
			// POUR LES TOOLID SUIVANTS
			//******************************************************************
			System.out.println("borneMax="+borneMax);
			for(int index=1; index < borneMax; index++) {
				System.out.println("index="+index);
				//si le premier toolId a donné un résultat
				if(!listCollabId.isEmpty()) {
					int toolId = listToolId.get(index);
					System.out.println(" CS ***** 4 ****** CollabService toolId="+toolId);
					listCollabId2 = skillDao.getAllCollaboratorIdByToolIdAndCollabList(toolId, listCollabId);
					listCollabId.clear();
					listCollabId.addAll(listCollabId2);
				}
			}
			/* then we get the Collaborators corresponding */
			List<Collaborator> listCollab = collabDao.getAllCollaboratorsByListId(listCollabId);
			
			for(Collaborator collab : listCollab) {
				System.out.println("CS ***** 4 ****** : collab ="+collab);
			}
			
			return listCollab;
		}
		return null;

	}

	
	@Override
	public List<Mission> getAllMission(int collabId) throws Exception {
		
		return this.missionDao.getByCollabId(collabId);
	}

	/**
	 * Adding a mission in database
	 * @deprecated
	 */
	@Override
	public int addMission(Mission mission) throws Exception {
		//TODO garder add(Mission) ou insert(Mission)
		return this.missionDao.add(mission);
	}

	@Override
	public int deleteMission(int idMission) throws Exception {
		return missionDao.delete(idMission);
	}
	

}
