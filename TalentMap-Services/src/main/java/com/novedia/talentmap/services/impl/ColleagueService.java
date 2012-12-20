package com.novedia.talentmap.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.MissionDao;
import com.novedia.talentmap.store.impl.ColleagueDao;

/**
 * The colleague Service implementation.
 * 
 * @author j.collet
 */
public class ColleagueService implements IColleagueService {
	
	/**
	 * The collaborator DAO.
	 */
	private IDao<Colleague> colleagueDao;

	/**
	 * The mission DAO.
	 */
	private IDao<Mission> missionDao;
	
	/**
	 * The manager DAO.
	 */
	private IDao<Manager> managerDao;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Colleague> getAllColleagues() throws DataAccessException {
		return colleagueDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Colleague getColleague(Integer id) throws DataAccessException {
		return colleagueDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveColleague(Colleague collaborator) throws DataAccessException {
		return colleagueDao.save(collaborator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer deleteMission(Mission mission) throws DataAccessException {
		return missionDao.delete(mission);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addMission(final Mission mission) throws DataAccessException {
		return missionDao.add(mission);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveMission(final Mission mission) throws DataAccessException {
		return missionDao.save(mission);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mission getMission(Integer missionId) throws DataAccessException {
		// TODO : A implémenter
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Mission> getAllMissions(final Integer collabId)throws DataAccessException {
		MissionDao missionDao = (MissionDao) this.missionDao;
		return missionDao.getAllByColleagueId(collabId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Manager getManager(final Integer managerId) throws DataAccessException {
		return managerDao.get(managerId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Colleague> getAllColleaguesByLastName(String lastName) throws DataAccessException {
		return ((ColleagueDao) colleagueDao).getAllColleaguesByLastName(lastName);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Colleague> getAllColleaguesByClientName(String clientName) throws DataAccessException {
		return ((ColleagueDao) colleagueDao).getAllColleaguesByClientName(clientName);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Colleague> getAllCollaboratorsByToolId(Integer toolId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Colleague> getAllColleaguesByListToolId(Map toolIdMap) throws DataAccessException {
		return ((ColleagueDao) colleagueDao).getAllColleaguesByListToolId(toolIdMap);
	}

	/**
	 * This method allowd to make the spring injection.
	 * 
	 * @param collaboratorDao
	 */
	public void setColleagueDao(IDao<Colleague> colleagueDao) {
		this.colleagueDao = colleagueDao;
	}

	/**
	 * This method allowd to make the spring injection.
	 * 
	 * @param missionDao
	 */
	public void setMissionDao(IDao<Mission> missionDao) {
		this.missionDao = missionDao;
	}

	/**
	 * This method allows to make the spring injection.
	 * 
	 * @param managerDao
	 */
	public void setManagerDao(IDao<Manager> managerDao) {
		this.managerDao = managerDao;
	}
}