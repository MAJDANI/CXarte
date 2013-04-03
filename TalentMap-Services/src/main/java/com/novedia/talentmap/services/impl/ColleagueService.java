package com.novedia.talentmap.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.novedia.talentmap.model.dto.MissionDto;
import com.novedia.talentmap.model.entity.Client;
import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.services.IColleagueService;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.impl.ColleagueDao;
import com.novedia.talentmap.store.impl.MissionDao;

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
	 * The mission DAO.
	 */
	private IDao<MissionDto> missionDaoForDto;
	
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
	public Integer addMission(final MissionDto missionDto) throws DataAccessException {
		Mission mission = createEntity(missionDto);
		return  missionDao.add(mission);
	}
	
	private Mission createEntity(MissionDto mDTO) {
		Mission m = null;
		if(mDTO != null){
			m = new Mission();
			List<Tool> toolsList = new ArrayList<Tool>();
			Set<Tool> tools = mDTO.getTools();
			if(tools.size()>0)
			{
				for(Tool t : tools)
				{
					toolsList.add(t);
				}
				m.setTools(toolsList);
			}
			// Recopie des attributs "simples"
			m.setId(mDTO.getId());
			m.setClient(mDTO.getClient());
			m.setStartDate(mDTO.getStartDate());
			m.setEndDate(mDTO.getEndDate());
			m.setTitle(mDTO.getTitle());
			m.setPlace(mDTO.getPlace());
			m.setNotes(mDTO.getNotes());
			m.setColleagueId(mDTO.getColleagueId());
		}
		return m;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer saveMission(final MissionDto missionDto) throws DataAccessException {
		Mission mission = createEntity(missionDto);
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
	public MissionDto getLastMission(final Integer colleagueId)throws DataAccessException {
		MissionDao missionDao = (MissionDao) this.missionDao;
		Mission m =  missionDao.getLastMissionByColleagueId(colleagueId);
		return createDTO(m);
	}
	
	private MissionDto createDTO(Mission m) {
		
			MissionDto mDTO = null;
			if(m != null){
				 mDTO = new MissionDto();
				// Recopie des attributs "simples"
				Set<Tool> toolsSet = new HashSet<Tool>();
				List<Tool> tools = m.getTools();
				
				if(tools.size()>0)
				{
					for(Tool t : tools)
					{
						toolsSet.add(t);
					}
					
					mDTO.setTools(toolsSet);
				}
				
				mDTO.setId(m.getId());
				mDTO.setClient(m.getClient());
				mDTO.setStartDate(m.getStartDate());
				mDTO.setEndDate(m.getEndDate());
				mDTO.setTitle(m.getTitle());
				mDTO.setPlace(m.getPlace());
				mDTO.setNotes(m.getNotes());
				mDTO.setColleagueId(m.getColleagueId());
				mDTO.setTools(toolsSet);
				
			}
			
	
			return mDTO;
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
	 * Counts colleagues in DB that have already the mail, with a different colleagueId.
	 * Used to check when a colleague wants to modify his email, if this email is
	 * not already use by someone else.
	 * @param the colleague who wants to change his email
	 * @return The count found.
	 */
	public Integer countMailForColleagueId(Colleague colleague) {
		return ((ColleagueDao) colleagueDao).countMailForColleagueId(colleague);
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

	public IDao<MissionDto> getMissionDaoForDto() {
		return missionDaoForDto;
	}

	public void setMissionDaoForDto(IDao<MissionDto> missionDaoForDto) {
		this.missionDaoForDto = missionDaoForDto;
	}
}