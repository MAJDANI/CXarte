package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;
import com.novedia.talentmap.model.entity.Manager;

/**
 * The Manager Service Interface.
 * 
 * @author v.guillemain
 */
public interface IManagerService {

/**
 * Get all colleagues
 * @param managerId the manager id
 * @return List<Collaborator>
 */
List<Colleague> getAllColleagues(Integer managerId);

/**
 * Get a colleague.
 * @param id a id
 * @return collaborator
 */
Colleague getCollaborator(int id);

}
