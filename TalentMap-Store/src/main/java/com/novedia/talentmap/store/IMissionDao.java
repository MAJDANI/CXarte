package com.novedia.talentmap.store;

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
	 * Get one Mission By Collab_ID
	 * @class IMissionDao.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	Mission getByCollabId(int collab_id) throws Exception;
	
	/**
	 * Update one Mission
	 * @class IMissionDao.java
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	int update(Mission mission) throws Exception;
	
}
