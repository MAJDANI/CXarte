package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.IDao;


/**
 * Collaborator Services
 * 
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services.impl
 * @created 21 mai 2012
 */
public class CollaboratorService implements ICollaboratorService{
	
	/**
	 * The collaborator DAO
	 */
	private IDao<Collaborator> collaboratorDao;
	
	/**
	 * The mission DAO
	 */
	private IDao<Mission> missionDao;
	
	@Override
	public List<Collaborator> getAllCollaborator() throws DataAccessException {
		return collaboratorDao.getAll();
	}

	@Override
	public Collaborator getCollaborator(Integer id) throws DataAccessException {
		return collaboratorDao.get(id);
	}

	@Override
	public int saveCollaborator(Collaborator collaborator) throws DataAccessException {
		return collaboratorDao.save(collaborator);
	}

	@Override
	public int deleteMission(Mission mission) throws DataAccessException {
		return missionDao.delete(mission);
	}

	@Override
	public int addMission(Mission mission) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveMission(Mission mission) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mission getMission(int missionId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> getAllMission(int collabId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getManager(int managerId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborator> getAllCollaboratorsByToolId(int toolId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborator> getAllCollaboratorsByListToolId(List<Integer> listToolId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCollaboratorDao(IDao<Collaborator> collaboratorDao) {
		this.collaboratorDao = collaboratorDao;
	}

	public void setMissionDao(IDao<Mission> missionDao) {
		this.missionDao = missionDao;
	}	

	
}
