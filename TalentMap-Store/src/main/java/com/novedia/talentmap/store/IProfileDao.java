package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Profile;

/**
 * Interface Profile DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface IProfileDao {
	
	/**
	 * Get One Profile By Id
	 * @class IProfileDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Profile getById(int id) throws Exception;
	
	/**
	 * Get One Profile By Type
	 * @class IProfileDao.java
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Profile getByType(String type) throws Exception;
	
	/**
	 * Select all Profiles
	 * @class IProfileDao.java
	 * @return
	 * @throws Exception
	 */
	List<Profile> selectAll() throws Exception;
}
