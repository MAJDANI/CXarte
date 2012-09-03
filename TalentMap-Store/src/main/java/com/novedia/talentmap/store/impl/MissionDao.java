package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.store.IMissionDao;

public class MissionDao implements IMissionDao {

	private SqlMapClient sqlMapClient;
	
	/**
	 * Builder of a dummy Mission if the database is down
	 * @class MissionDao.java
	 * @param id
	 * @return
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
	
	@Override
	public List<Mission> getByCollabId(
			int collabId) {
		
		try {
			
			return (List<Mission>) sqlMapClient.queryForList("mission.getAllMission", collabId);
//			return buildDummyMission(collab_id);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database down !");
			
			return buildDummyMission(collabId);
//			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyMission(collabId);
		}
	}
	
	/**
	 * Add One Mission
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

	
	@Override
	public int update(Mission mission) throws Exception {
		
		this.sqlMapClient.startTransaction();
		int value = this.sqlMapClient.update("mission.updateMission", mission);
		this.sqlMapClient.commitTransaction();
		this.sqlMapClient.endTransaction();
		
		return value;
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * Getting Mission by id
	 */
	@Override
	public Mission getById(int missionId) throws Exception {
		
		return (Mission) this.sqlMapClient.queryForObject("mission.getMission", missionId);
	}

	/**
	 * Adding mission
	 * @deprecated
	 */
	@Override
	public int add(Mission mission) throws Exception {
		//TODO garder add(Mission) ou insert(Mission)
		return (Integer) this.sqlMapClient.queryForObject("mission.addMission", mission);
	}

}
