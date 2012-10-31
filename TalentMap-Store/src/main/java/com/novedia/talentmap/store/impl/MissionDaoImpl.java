package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Concept;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IGenericDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

public class MissionDaoImpl extends SqlMapClientDaoSupport  implements IGenericDao<Mission> {

	private SqlMapClient sqlMapClient;
	private int missionId;
	private int value;
	private int id ;
	
	/**
	 * Builder of a dummy Mission if the database is down
	 * @class MissionDao.java
	 * @param id : a collaborator's id, the collaborator concerned by the list of dummy missions
	 * @return List<Mission>
	 * @deprecated faut-il conserver cette fonction?
	 */
	private List<Mission> buildDummyMission(int id){
		
		List<Mission> listMission = new ArrayList<Mission>();
		
		Mission m = new Mission();
		m.setCollab_id(id);
		m.setClient("SFR");
		m.setEnd_date(new Date());
		m.setStart_date(new Date());
		m.setId(1);
		
		listMission.add(m);
		
		return listMission;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#getByCollabId(int collabId)
	 */ 
	@Override
	public List<Mission> getByCollabId(
			int collabId) throws DataAccessException {
		
		try {			
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_MISSION, collabId);
		
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
		
			return buildDummyMission(collabId);
		}
	}
		
	
	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#update(Mission mission)
	 */ 
	@Override
	public int insert(Mission mission) throws DataAccessException {
		//TODO garder add(Mission) ou insert(Mission)
		try {
			this.sqlMapClient.startTransaction();
			missionId = (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.INSERT_MISSION_REQUEST, mission);
			this.sqlMapClient.commitTransaction();
			this.sqlMapClient.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return missionId;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#update(Mission mission)
	 */ 
	@Override
	public int update(Mission mission) throws DataAccessException {
		
		try {
			this.sqlMapClient.startTransaction();
			int value = this.getSqlMapClientTemplate().update(DBRequestsConstants.UPDATE_MISSION_REQUEST, mission);
			this.sqlMapClient.commitTransaction();
			this.sqlMapClient.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#delete(int idMission)
	 */ 
	@Override
	public int delete(int idMission) throws DataAccessException {
		
		try {
			this.sqlMapClient.startTransaction();
			id = (Integer) this.sqlMapClient.delete("mission.deleteMission", idMission);
			this.sqlMapClient.commitTransaction();
			this.sqlMapClient.endTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public MissionDaoImpl(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#getById(int missionId)
	 */ 
	@Override
	public Mission getById(int missionId) throws DataAccessException {
		
		return (Mission) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_MISSION, missionId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#add(Mission mission)
	 */ 
	@Override
	public int add(Mission mission) throws DataAccessException {
		//TODO garder add(Mission) ou insert(Mission)
		return(Integer)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.ADD_MISSION_REQUEST,mission);
	}

	@Override
	public List<Mission> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Mission object) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Mission checkCategory(String name) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> getAllCollaboratorsByLastName(String lastName)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> getAllCollaboratorsByListId(List<Integer> listId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Concept> selectAllByCategoryId(int categoryId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mission checkConcept(String name, int category_id)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> getAllCollaboratorsByManagerId(Integer managerId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mission getByType(String type) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Mission> selectAllByConceptId(int conceptId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mission getByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tool checkTool(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}