package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
/**
 * Interface Collaborator Services
 * @author j.collet
 * @project TalentMap-Services
 * @package com.novedia.talentmap.services
 * @created 21 mai 2012
 */
public interface ICollaboratorService {
	
	/**
	 * Select all Collaborators
	 * @class ICollaboratorService.java
	 * @return
	 * @throws Exception
	 */
	List<Collaborator> getAllCollaborator() throws Exception;
	
	/**
	 * Get One Collaborator By Id
	 * @class ICollaboratorService.java
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Collaborator getCollaborator(int id) throws Exception;
	
	/**
	 * Update One Collaborator
	 * @class ICollaboratorService.java
	 * @param collaborator
	 * @return
	 * @throws Exception
	 */
	int updateCollaborator(Collaborator collaborator) throws Exception;
}
