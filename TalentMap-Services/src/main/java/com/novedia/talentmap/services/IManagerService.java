package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Interface Manager Services.
 * @author v.guillemain
 */
public interface IManagerService {

/**
 * @param managerId a id
 * @return List<Collaborator>
 */
List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId);

/**
 * Get One Collaborator By ID.
 * @class IManagerService.java
 * @param id a id
 * @return collaborator
 */
Collaborator getCollaborator(int id);
}
