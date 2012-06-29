package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.Date;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Mission;
import com.novedia.talentmap.store.IMissionDao;

public class MissionDao implements IMissionDao {

	private SqlMapClient sqlMapClient;
	
	/**
	 * Builder of a dummy Mission if the database is down
	 * @class MissionDao.java
	 * @param id
	 * @return
	 */
	private Mission buildDummyMission(int id){
		
		Mission m = new Mission();
		m.setCollab_id(String.valueOf(id));
		m.setClient("SFR");
		m.setEnd_date(new Date());
		m.setStart_date(new Date());
		m.setId("1");
		
		return m;
	}
	
	@Override
	public Mission getByCollabId(
			int collab_id) {
		
		try {
			
			return (Mission) sqlMapClient.queryForObject("mission.getMission", collab_id);
//			return buildDummyMission(collab_id);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database down !");
			
			return buildDummyMission(collab_id);
//			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyMission(collab_id);
		}
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

}
