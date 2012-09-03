package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Mission;

/**
 * Interface Mission DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 30 mai 2012
 */
public interface IMissionDao {
	
	/**
	 * Get one mission by ID
	 * @class IMissionDao.java
	 * @param missionId
	 * @return
	 * @throws Exception
	 */
	Mission getById(int missionId) throws Exception;
	
	/**
	 * Get all Collaborators's Mission By Collab_ID
	 * @class IMissionDao.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	List<Mission> getByCollabId(int collabId) throws Exception;
	
	/**
	 * Inserts one Mission
	 * @class IMissionDao.java
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	public int insert(Mission mission) throws Exception;

	/**
	 * Update one Mission
	 * @class IMissionDao.java
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	int update(Mission mission) throws Exception;
	
	/**
	 * Adding a mission in database
	 * @deprecated
	 * @class IMissionDao.java
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	int add(Mission mission) throws Exception;
	
}
