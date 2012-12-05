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
 * @author j.marie-sainte
 */
public class MissionDao extends SqlMapClientDaoSupport  implements IDao<Mission> {

/**
 * Class builder based on sqlMapClient.
 * @param sqlMapClient a sqlMapClient
 */
public MissionDao(final SqlMapClient sqlMapClient) {
setSqlMapClient(sqlMapClient);
}
/**
 * This method to get a mission by id.
 * @return mission
 * @param id of the mission
 * @throws DataAccessException
 */

@Override
public Mission get(final Integer id) throws DataAccessException {
return (Mission) this.getSqlMapClientTemplate()
.queryForObject(DBRequestsConstants.GET_MISSION, id);
}

/**
 * This method allow to get all mission.
 * @return a list of mission
 * @throws DataAccessException
 */
@Override
public List<Mission> getAll() throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to update a mission.
 * @param mission a mission
 * @throws DataAccessException
 */
@Override
public int save(final Mission mission) throws DataAccessException {
return this.getSqlMapClientTemplate()
.update(DBRequestsConstants.SAVE_MISSION, mission);
}

/**
 * This method allow to add a mission.
 * @param mission a mission
 * @throws DataAccessException
 */
@Override
public int add(final Mission mission) throws DataAccessException {
return (Integer) this.getSqlMapClientTemplate()
.insert(DBRequestsConstants.ADD_MISSION, mission);
}

/**
 * This method allow to delete a mission.
 * @param mission a mission
 * @throws DataAccessException
 */
@Override
public int delete(final Mission mission) throws DataAccessException {
return this.getSqlMapClientTemplate()
.delete(DBRequestsConstants.DELETE_MISSION, mission);
}

/**
 * This method allow to get a mission.
 * @param name a mission
 * @throws DataAccessException
 */
@Override
public Mission check(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}

/**
 * This method allow to get a mission name.
 * @param name a mission
 * @throws DataAccessException
 */
@Override
public Mission getByName(final String name) throws DataAccessException {
throw new UnsupportedOperationException();
}
}