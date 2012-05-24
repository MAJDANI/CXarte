package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Interface Collaborator DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 21 mai 2012
 */
public interface ICollaboratorDao {
	
	/**
	 * Get One Collaborator By Id
	 * @class ICollaboratorDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Collaborator getById(int id) throws Exception;
	
	/**
	 * Select all Collaborators
	 * @class ICollaboratorDao.java
	 * @return
	 * @throws Exception
	 */
	List<Collaborator> selectAll() throws Exception;
	
	/**
	 * Update one Collaborator
	 * @class ICollaboratorDao.java
	 * @param collaborator
	 * @return
	 * @throws Exception
	 */
	int update(Collaborator collaborator) throws Exception;
	
}
