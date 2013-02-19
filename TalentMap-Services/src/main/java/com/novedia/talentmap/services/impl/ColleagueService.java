package com.novedia.talentmap.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ManagerDao;
import com.novedia.talentmap.store.impl.MissionDao;
import com.novedia.talentmap.store.impl.ColleagueDao;

/**
 * The colleague Service implementation.
 * 
 * @author j.collet
 */
public class ColleagueService implements IColleagueService {
	
	/**
	 * The colleague DAO.
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
	public Integer saveColleague(Colleague colleague) throws DataAccessException {
		return colleagueDao.save(colleague);
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
		return missionDao.get(missionId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Mission> getAllMissions(final Integer colleagueId)throws DataAccessException {
		MissionDao missionDao = (MissionDao) this.missionDao;
		return missionDao.getAllByColleagueId(colleagueId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mission getLastMission(final Integer colleagueId)throws DataAccessException {
		MissionDao missionDao = (MissionDao) this.missionDao;
		return missionDao.getLastMissionByColleagueId(colleagueId);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Manager getManager(final Integer managerId) throws DataAccessException {
		return managerDao.get(managerId);
	}


	/**
	 * Get all managers
	 */
	@Override
	public List<Manager> getAllManagers() {
		return ((ColleagueDao) colleagueDao).getAllManagers();
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
	public List<Colleague> getAllColleaguesByClient(Client client) throws DataAccessException {
		return ((ColleagueDao) colleagueDao).getAllColleaguesByClient(client);
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
	 * {@inheritDoc}
	 */
	@Override
	public List<Colleague> getAllColleagueByColleagueIdList(List<Integer> listColleagueId) throws DataAccessException {
		return ((ColleagueDao) colleagueDao).getAllColleagueByColleagueIdList(listColleagueId);
	}

	/**
	 * This method allows to make the spring injection.
	 * 
	 * @param colleagueDao
	 */
	public void setColleagueDao(IDao<Colleague> colleagueDao) {
		this.colleagueDao = colleagueDao;
	}

	/**
	 * This method allows to make the spring injection.
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