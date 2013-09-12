package com.novedia.talentmap.store;

import java.util.List;

import com.novedia.talentmap.model.entity.Colleague;

/**
 * Colleague interface.
 * 
 * @author j.marie-sainte
 */
public interface IColleague {

	/**
	 * Select all colleagues by managerId.
	 * 
	 * @param managerId
	 *            : the id of the manager
	 * @return all colleagues who depend on the manager managerId
	 * @author v.guillemain
	 */
	List<Colleague> getAllColleaguesByManagerId(Integer managerId);

	/**
	 * Select all colleagues for a list of colleague's id.
	 * 
	 * @author v.guillemain
	 * @class IColleagueDao.java
	 * @param listId
	 *            : a list of colleague's id
	 * @return all colleagues who has an id specified
	 * @throws Exception
	 */
	List<Colleague> getAllColleaguesByListId(List<Integer> listId);

	/**
	 * Select all colleagues by lastName.
	 * 
	 * @author v.guillemain
	 * @class IColleagueDao.java
	 * @param lastName
	 *            : a lastName
	 * @return all colleagues who has the lastName specified
	 * @throws Exception
	 */
	List<Colleague> getAllColleaguesByLastName(String lastName);
}
