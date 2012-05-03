package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IProfileDao;

public class ProfileDao implements IProfileDao {
	
	private SqlMapClient sqlMapClient;

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> selectAll() throws Exception{
		
		return sqlMapClient.queryForList("profile.getAllProfile");
	}
	

	@Override
	public Profile getById(int id) throws Exception {
		
		return (Profile) sqlMapClient.queryForObject("profile.getProfile", id);
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
