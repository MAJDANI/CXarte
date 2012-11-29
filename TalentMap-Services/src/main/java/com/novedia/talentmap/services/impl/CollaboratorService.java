package com.novedia.talentmap.services.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.ICollaboratorService;
import com.novedia.talentmap.store.IDao;


/**
 * Collaborator Services.
 * @author j.collet
 */
public class CollaboratorService implements ICollaboratorService{
/**
 * The collaborator DAO.
 */
private IDao<Collaborator> collaboratorDao;

/**
 * The mission DAO.
 */
private IDao<Mission> missionDao;

/**
 * This method allowd to get from database all collaborator.
 * @return a list of collaborator
 * @throws DataAccessException
 */
@Override
public List<Collaborator> getAllCollaborator() throws DataAccessException {
return collaboratorDao.getAll();
}

/**
 * This method allowd to get from database a collaborator by id.
 * @return id of collaborator
 * @throws DataAccessException
 */
@Override
public Collaborator getCollaborator(Integer id) throws DataAccessException {
return collaboratorDao.get(id);
}

/**
 * This method allowd to save a collaborator.
 * @return int
 * @param collaborator a collaborator
 * @throws DataAccessException
 */
@Override
public int saveCollaborator(Collaborator collaborator) throws DataAccessException {
return collaboratorDao.save(collaborator);
}

/**
 * This method allowd delete a Mission.
 * @return int
 * @throws DataAccessException
 */
@Override
public int deleteMission(Mission mission) throws DataAccessException {
return missionDao.delete(mission);
}

/**
 * This method allowd add a Mission.
 * @return int
 * @param mission a mission
 * @throws DataAccessException
 */
@Override
public int addMission(final Mission mission) throws DataAccessException {
// TODO Auto-generated method stub
return 0;
}

/**
 * This method allowd save a Mission.
 * @return int
 * @param mission a mission
 * @throws DataAccessException
 */
@Override
public int saveMission(final Mission mission) throws DataAccessException {
// TODO Auto-generated method stub
return 0;
}

/**
 * This method allowd to get mission id.
 * @return int
 * @param missionId a id
 * @throws DataAccessException
 */
@Override
public Mission getMission(int missionId) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to get mission id.
 * @return a list of mission by collaborator
 * @param collabId a id
 * @throws DataAccessException
 */
@Override
public List<Mission> getAllMission(final int collabId) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to get a manager by id.
 * @return a manager ,manager
 * @param managerId a id
 * @throws DataAccessException
 */
@Override
public Manager getManager(final int managerId) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to get mission id.
 * @return a list of Collaborator by name
 * @param lastName a last name
 * @throws DataAccessException
 */
@Override
public List<Collaborator> getAllCollaboratorsByLastName(String lastName) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to get mission id.
 * @return a list of Collaborator by id
 * @param toolId a tool id
 * @throws DataAccessException
 */
@Override
public List<Collaborator> getAllCollaboratorsByToolId(int toolId) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to get mission id.
 * @return a list of Collaborator
 * @param listToolId
 * @throws DataAccessException
 */
@Override
public List<Collaborator> getAllCollaboratorsByListToolId(List<Integer> listToolId) throws DataAccessException {
// TODO Auto-generated method stub
return null;
}

/**
 * This method allowd to make the spring injection.
 * @param collaboratorDao
 */
public void setCollaboratorDao(IDao<Collaborator> collaboratorDao) {
	this.collaboratorDao = collaboratorDao;
}

/**
 * This method allowd to make the spring injection.
 * @param missionDao
 */
public void setMissionDao(IDao<Mission> missionDao) {
	this.missionDao = missionDao;
}
}