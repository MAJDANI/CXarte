package com.novedia.talentmap.store.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * Mission DAO.
 * 
 * @author j.marie-sainte
 *
 */
public class MissionDao extends SqlMapClientDaoSupport  implements IDao<Mission> {

	/**
	 * Class builder based on sqlMapClient
	 * @param sqlMapClient
	 */
	public MissionDao(final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
	
	@Override
	public Mission get(Integer id) throws DataAccessException {
		return (Mission) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_MISSION, id);
	}

	@Override
	public List<Mission> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int save(Mission mission) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_MISSION, mission);
	}

	@Override
	public int add(Mission mission) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_MISSION, mission);
	}

	@Override
	public int delete(Mission mission) throws DataAccessException {
		return this.getSqlMapClientTemplate().delete(DBRequestsConstants.DELETE_MISSION, mission);
	}

	@Override
	public Mission check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mission getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}
}