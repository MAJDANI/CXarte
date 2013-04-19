package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.MissionTool;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Mission DAO handles query for table Mission.
 * 
 * @author j.marie-sainte
 */
public class MissionDao extends SqlMapClientDaoSupport implements IDao<Mission> {

	/**
	 * Class builder based on sqlMapClient.
	 * 
	 * @param sqlMapClient
	 *            a sqlMapClient
	 */
	public MissionDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mission get(final Integer id) throws DataAccessException {
		return (Mission) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_MISSION, id);
	}

	/**
	 * Gets all mission for a colleague identifies by colleagueId, ordered by START_DATE.
	 * 
	 * @param colleagueId colleague identifier
	 * @return List<Mission> mission list
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<Mission> getAllByColleagueId(final Integer colleagueId) throws DataAccessException {
		return (List<Mission>) this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_MISSION_BY_COLLEAGUE_ID, colleagueId);
	}
	
	/**
	 * Gets the last Mission for a colleague identifies by colleagueId
	 * 
	 * @param colleagueId colleague identifier
	 * @return List<Mission> mission list
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public Mission getLastMissionByColleagueId(final Integer colleagueId) throws DataAccessException {	
		return (Mission) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_LAST_MISSION_BY_COLLEAGUE_ID, colleagueId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Mission> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(final Mission mission) throws DataAccessException {
		Integer res = (Integer)this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_MISSION, mission);
		if(res != 0){
			this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_MISSION_TOOL,mission.getId());
			for (Tool t : mission.getTools()) {
				MissionTool missionTool=new MissionTool();
				missionTool.setMissionId(mission.getId());
				missionTool.setToolId(t.getId());
				this.getSqlMapClientTemplate().update(DBRequestsConstants.ADD_MISSION_TOOL, missionTool);
			}
			
		}
		
		return res;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public int add(final Mission mission) throws DataAccessException {
		Integer res = (Integer)this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_MISSION, mission);
		if(res != 0){
			
			for (Tool t : mission.getTools()) {
				MissionTool missionTool=new MissionTool();
				missionTool.setMissionId(mission.getId());
				missionTool.setToolId(t.getId());
				this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_MISSION_TOOL, missionTool);
			}
			
		}
		
		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final Mission mission) throws DataAccessException {
		return this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_MISSION, mission.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mission check(final String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Mission getByName(final String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
	
	
	
	
}