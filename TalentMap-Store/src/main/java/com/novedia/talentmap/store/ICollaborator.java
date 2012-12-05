package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Collaborator;

/**
 * Collaborator interface.
 * @author j.marie-sainte
 *
 */
public interface ICollaborator {

/**
 * Select all Collaborators by managerId.
 * @param managerId : the id of the manager
 * @return all collaborators who depend on the manager managerId
 * @author v.guillemain
 */
List<Collaborator> getAllCollaboratorsByManagerId(Integer managerId);

/**
 * Select all Collaborators for a list of collaborator's id.
 * @author v.guillemain
 * @class ICollaboratorDao.java
 * @param listId : a list of collaborator's id
 * @return all collaborators who has an id specified
 * @throws Exception
 */
List<Collaborator> getAllCollaboratorsByListId(List<Integer> listId);

/**
 * Select all Collaborators by lastName.
 * @author v.guillemain
 * @class ICollaboratorDao.java
 * @param lastName : a lastName
 * @return all collaborators who has the lastName specified
 * @throws Exception
 */
List<Collaborator> getAllCollaboratorsByLastName(String lastName);
}
