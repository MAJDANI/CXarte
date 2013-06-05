package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.ISkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * The Skill DAO handles query for table Skill.
 * 
 * @author j.collet
 */
public class SkillDao extends SqlMapClientDaoSupport implements ISkillDao,
		IDao<Skill> {

	/**
	 * Tool DAO.
	 */
	private IDao<Tool> toolDao;

	/**
	 * Set the sqlMapClient value.
	 * 
	 * @param sqlMapClient
	 *            the sqlMapClient to set
	 */
	public SkillDao(final SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill get(final Integer id) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Skill> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tool> getAllByConcept(int conceptId) {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_TOOLS_BY_CONCEPT, conceptId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(Skill skill) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(
				DBRequestsConstants.SAVE_SKILL, skill);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final Skill skill) throws DataAccessException {
		// La méthode insert(DBRequestsConstants.ADD_SKILL, skill) revoie
		// habituellement
		// l'identifiant du nouvel enregistrement créé. Dans ce cas précis, la
		// table
		// Skill est une table de jointure, il n'y a pas d'identifiant de Skill
		// créé
		// au moment de la création d'un nouvel enregistrement,
		// alors la méthode (DBRequestsConstants.ADD_SKILL, skill) renvoie null.
		// Par soucis de compatibilité avec les autres méthodes add(), on force
		// un return 1.
		this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_SKILL,
				skill);
		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int delete(final Skill element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getAllCollaboratorSkill(int colleagueId)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"skill.getAllCollaboratorSkill", colleagueId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill getCollaboratorSkillByTool(int collaboratorId, int toolId)
			throws DataAccessException {
		Map<String, Integer> mapId = new HashMap<String, Integer>();
		mapId.put("collaboratorId", collaboratorId);
		mapId.put("toolId", toolId);
		return (Skill) this.getSqlMapClientTemplate().queryForObject(
				DBRequestsConstants.GET_SKILL, mapId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tool getToolByName(final String name) throws DataAccessException {
		// return toolDao.getByName(name);
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Select all Collaborators Id By a toolId.
	 * 
	 * @param toolId
	 *            : a tool Id
	 * @return all collaborator's id who has a competence on the tool specified
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllColleagueIdByToolId(Integer toolId)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUE_ID_BY_TOOL_ID, toolId);
	}

	/**
	 * Select CM Collaborators Id By a toolId.
	 * 
	 * @param toolId
	 *            : a tool Id managerId : a manager Id
	 * @return all collaborator's id who has a competence on the tool specified
	 * @author j.maquin
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getCmColleagueIdByToolId(Integer toolId,
			Integer managerId) throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("toolId", toolId);
		parameters.put("managerId", managerId);
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_CM_COLLEAGUE_ID_BY_TOOL_ID, parameters);
	}
	
	/**
	 * Select all Collaborators Id By a conceptId.
	 * 
	 * @param conceptId
	 *            : a concept Id
	 * @return all collaborator's id who has a competence on the concept specified
	 * @author j.maquin
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllColleagueIdByConceptId(Integer conceptId)
			throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUE_ID_BY_CONCEPT_ID, conceptId);
	}
	
	/**
	 * Select all CM Collaborators Id By a conceptId.
	 * 
	 * @param conceptId
	 *            : a concept Id
	 * @param managerId
	 *            : a manager Id
	 * @return all CM collaborator's id who has a competence on the concept specified
	 * @author j.maquin
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllCmColleagueIdByConceptId(Integer conceptId,Integer managerId)
			throws DataAccessException {
		HashMap parameters = new HashMap();
		parameters.put("conceptId", conceptId);
		parameters.put("managerId", managerId);
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_CM_COLLEAGUE_ID_BY_CONCEPT_ID, parameters);
	}

	/**
	 * Select all Collaborators Id By a list of toolId.
	 * 
	 * @param listToolId
	 *            : a list of toolId
	 * @return all collaborator's id who has a competence on one or more of the
	 *         tools specified
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllColleagueIdByListToolId(List<Integer> listToolId)
			throws DataAccessException {
		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setListTools(listToolId);
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID,
				skillParameter);
	}

	/**
	 * Select CM Collaborators Id By a list of toolId.
	 * 
	 * @param listToolId
	 *            : a list of toolId
	 * @return CM collaborator's id who has a competence on one or more of the
	 *         tools specified
	 * @author j.maquin
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getCmColleagueIdByListToolId(List<Integer> listToolId,
			int managerId) throws DataAccessException {
		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setListTools(listToolId);
		skillParameter.setManagerId(managerId);
		return this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_CM_COLLEAGUE_ID_BY_LIST_OF_TOOL_ID,
				skillParameter);
	}

	/**
	 * Set the tool DAO
	 * 
	 * @param toolDao
	 *            to set
	 */
	public void setToolDao(IDao<Tool> toolDao) {
		this.toolDao = toolDao;
	}

	/**
	 * Get alls skills by {@link Colleague}.
	 * 
	 * @param colleague
	 *            a colleague
	 * @return a list of skill
	 */
	// @SuppressWarnings("unchecked")
	// public List<Skill> getAllSkillsByCollaborator(final Colleague colleague)
	// {
	// return
	// this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_SKILLS_BY_COLLABORATOR,
	// colleague);
	// }
}