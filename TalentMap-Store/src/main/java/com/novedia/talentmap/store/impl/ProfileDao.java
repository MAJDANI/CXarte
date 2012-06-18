package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
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
	 * Builder of a list of Profile if the database is down
	 * @class ProfileDao.java
	 * @return
	 */
	private List<Profile> buildListDummyProfile(){
		
		List<Profile> lProfile = new ArrayList<Profile>();
		
		Profile p1 = buildDummyProfile(1, "technique");
		Profile p2 = buildDummyProfile(2, "fonctionnel");
		
		lProfile.add(p1);
		lProfile.add(p2);
		
		return lProfile;
	}
	
	/**
	 * Select all Profiles
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> selectAll(){
		
		try {
			
//			return sqlMapClient.queryForList("profile.getAllProfile");
			return buildListDummyProfile();
			
//		} catch (SQLException e) {
//			
//			//e.printStackTrace();
//			System.err.println("Database Down !");
//			
//			return buildListDummyProfile();
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			return buildListDummyProfile(); 
		}
	}
	
	/**
	 * Builder of a dummy Profile if the database is down
	 * @class ProfileDao.java
	 * @param id
	 * @param type
	 * @return
	 */
	private Profile buildDummyProfile(int id, String type){
		
		Profile p = new Profile();
		p.setId(String.valueOf(id));
		p.setType(type);
		
		return p;
	}
	
	/**
	 * Get One Profile By Id
	 */
	@Override
	public Profile getById(int profile_id) {
		
		try {
			
//			return (Profile) sqlMapClient.queryForObject("profile.getProfile", profile_id);
			return buildDummyProfile(profile_id, "technique");
			
//		} catch (SQLException e) {
//			
//			//e.printStackTrace();
//			System.err.println("Database down !");
//			
//			return buildDummyProfile(profile_id, "technique");
			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyProfile(profile_id, "technique");
		}
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
