package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Interface Manager Services
 * @author v.guillemain
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services
 * @created 04 July 2012
 */
public interface IManagerService {

	/**
	 * Select all Collaborators depending on a manager
	 * @class IManagerService.java
	 * @param managerId
	 * @return all collaborators who depend on the manager 
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId) throws Exception;
	
	/**
	 * Get One Collaborator By ID
	 * @class IManagerService.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Collaborator getCollaborator(int id) throws Exception;
}
