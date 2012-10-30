package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.common.jdbc.DbcpConfiguration;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Profile;
import com.novedia.talentmap.store.IProfileDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class ProfileDao  extends SqlMapClientDaoSupport  implements IProfileDao {
	
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
	public List<Profile> selectAll() throws SQLException{
		
		try {
			
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_PROFIL);
			
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
		p.setId(id);
		p.setType(type);
		
		return p;
	}
	
	/**
	 * Get One Profile By Id
	 */
	@Override
	public Profile getById(int profile_id) throws SQLException {
		
		try {			
			return (Profile) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_PROFIL, profile_id);			
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummyProfile(profile_id, "technique");
		}
	}
	
	/**
	 * Get One Profile By Type
	 */
	public Profile getByType(String type) throws Exception{
		
		return (Profile) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_PROFIL_BYTYPE, type);
	}
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public ProfileDao(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
}