package com.novedia.talentmap.services;

import java.util.List;

import com.novedia.talentmap.model.entity.Objective;

public interface IObjectiveService {

	/**
	 * Get an Objective By Id.
	 * 
	 * @class IObjectiveService.java
	 * @param id
	 *            the id of the Objective searched
	 * @return an Objective
	 */
	Objective getObjectiveById(Integer id);

	/**
	 * Adds the Objective.
	 * 
	 * @class IObjectiveService.java
	 * @param objective
	 *            the Objective to add
	 * @return an integer, result of the request
	 */
	int addObjective(Objective objective);

	/**
	 * Updates the Objective.
	 * 
	 * @class IObjectiveService.java
	 * @param objective
	 *            the Objective to update
	 * @return an integer, result of the request
	 */
	int saveObjective(Objective objective);

	/**
	 * Deletes the Objective.
	 * 
	 * @class IObjectiveService.java
	 * @param objective
	 *            the Objective to delete
	 * @return an integer, result of the request
	 */
	int deleteObjective(Objective objective);

	/**
	 * Gets, for a given EAE id, the list of Objective related
	 * 
	 * @param idEAE
	 *            : the id of the EAE we search Objectives related
	 * @return List<Objective> : a list of Objective attached to the EAE
	 * 
	 */
	List<Objective> getObjectivesByEAEId(Integer idEAE);

	/**
	 * Gets, for a given EAE id, the list of precedent objectives related, i.e.
	 * the objectives of the precedent EAE. These objectives are displayed
	 * in the part "Results" of an EAE
	 * 
	 * @param idEAE
	 *            : the id of the EAE we search Precedent Objectives related
	 * @return List<Objective> : a list of Precedent Objective attached to the
	 *         EAE
	 * 
	 */
	List<Objective> getPrecedentObjectivesByEAEId(Integer idEAE);

}
