package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IMissionDao;

public class MissionDao implements IMissionDao {

	private SqlMapClient sqlMapClient;
	
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
			int collabId) {
		
		try {
			
			return (List<Mission>) sqlMapClient.queryForList("mission.getAllMission", collabId);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database down !");
			return buildDummyMission(collabId);
			
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
	public int insert(Mission mission) throws Exception {
		//TODO garder add(Mission) ou insert(Mission)
		this.sqlMapClient.startTransaction();
		int missionId = (Integer) this.sqlMapClient.insert("mission.insertMission", mission);
		this.sqlMapClient.commitTransaction();
		this.sqlMapClient.endTransaction();
		return missionId;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#update(Mission mission)
	 */ 
	@Override
	public int update(Mission mission) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int value = this.sqlMapClient.update("mission.updateMission", mission);
		this.sqlMapClient.commitTransaction();
		this.sqlMapClient.endTransaction();
		
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#delete(int idMission)
	 */ 
	@Override
	public int delete(int idMission) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int id = (Integer) this.sqlMapClient.delete("mission.deleteMission", idMission);
		this.sqlMapClient.commitTransaction();
		this.sqlMapClient.endTransaction();
		
		return id;
	}

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#getById(int missionId)
	 */ 
	@Override
	public Mission getById(int missionId) throws Exception {
		
		return (Mission) this.sqlMapClient.queryForObject("mission.getMission", missionId);
	}

	/*
	 * (non-Javadoc)
	 * @see com.novedia.talentmap.store.IMissionDao#add(Mission mission)
	 */ 
	@Override
	public int add(Mission mission) throws Exception {
		//TODO garder add(Mission) ou insert(Mission)
		return (Integer) this.sqlMapClient.queryForObject("mission.addMission", mission);
	}

}
