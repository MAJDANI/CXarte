package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IProfileDao;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ProfileDao implements IProfileDao {
	
	private SqlMapClient sqlMapClient;
	
	/**
	 * Select all Profiles
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> selectAll() throws Exception{
		
		return sqlMapClient.queryForList("profile.getAllProfile");
	}
	
	/**
	 * Get One Profile By Id
	 */
	@Override
	public Profile getById(int id) throws Exception {
		
		return (Profile) sqlMapClient.queryForObject("profile.getProfile", id);
	}
	
	/**
	 * Get One Profile By Type
	 */
	public Profile getByType(String type) throws Exception{
		
		return (Profile) sqlMapClient.queryForObject("profile.getByType", type);
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
