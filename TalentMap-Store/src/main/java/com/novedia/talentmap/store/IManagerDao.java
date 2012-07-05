package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;

/**
 * Interface Manager DAO
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store
 * @created 31 mai 2012
 */
public interface IManagerDao {
	
	/**
	 * Get one Manager By ID
	 * @class IManagerDao.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Manager getById(int id) throws Exception;
	
	/**
	 * Select all Collaborators by managerId
	 * @param managerId : the id of the manager
	 * @return all collaborators who depend on the manager managerId
	 * @author v.guillemain
	 */
	List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId) throws Exception;
	
}
