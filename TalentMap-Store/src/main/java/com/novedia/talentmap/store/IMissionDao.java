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
	 * @param missionId : the id of the mission to search
	 * @return Mission : the mission found with the missionId parameter
	 * @throws Exception
	 */
	Mission getById(int missionId) throws Exception;
	
	/**
	 * Get all Collaborators's Missions By Collab_ID
	 * @class IMissionDao.java
	 * @param collab_id : the collaborator's id
	 * @return List<Mission>
	 * @throws Exception
	 */
	List<Mission> getByCollabId(int collabId) throws Exception;
	
	/**
	 * Inserts one Mission
	 * @class IMissionDao.java
	 * @param mission : the mission to be inserted
	 * @return int : id of the mission inserted (id generated)
	 * @throws Exception
	 */
	public int insert(Mission mission) throws Exception;

	/**
	 * Update one Mission
	 * @class IMissionDao.java
	 * @param mission : the mission to be updated
	 * @return int TODO
	 * @throws Exception
	 */
	int update(Mission mission) throws Exception;

	/**
	 * Delete one Mission identified by the parameter idMission
	 * @class IMissionDao.java
	 * @param idMission the id of the mission to delete
	 * @return int TODO
	 * @throws Exception
	 */
	int delete(int idMission) throws Exception;
	
	/**
	 * Adding a mission in database
	 * @deprecated : use insert(Mission mission)
	 * @class IMissionDao.java
	 * @param mission
	 * @return int TODO
	 * @throws Exception
	 */
	int add(Mission mission) throws Exception;
	
}
