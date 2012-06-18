package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Manager;
import com.novedia.talentmap.model.entity.Mission;
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
	
	/**
	 * Update one Mission
	 * @class ICollaboratorService.java
	 * @param mission
	 * @return
	 * @throws Exception
	 */
	int updateMission(Mission mission) throws Exception;
	
	/**
	 * Get One Mission By Collab_ID
	 * @class ICollaboratorService.java
	 * @param collab_id
	 * @return
	 * @throws Exception
	 */
	Mission getMission(int collab_id) throws Exception;
	
	/**
	 * Get one Manager by ID
	 * @class ICollaboratorService.java
	 * @param manager_id
	 * @return
	 * @throws Exception
	 */
	Manager getManager(int manager_id) throws Exception;
}
