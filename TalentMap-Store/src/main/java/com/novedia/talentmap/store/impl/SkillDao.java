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
public class SkillDao extends SqlMapClientDaoSupport implements ISkillDao,	IDao<Skill> {
	
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int save(Skill skill) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_SKILL, skill);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int add(final Skill skill) throws DataAccessException {
		// La méthode insert(DBRequestsConstants.ADD_SKILL, skill) revoie habituellement
		// l'identifiant du nouvel enregistrement créé. Dans ce cas précis, la table
		// Skill est une table de jointure, il n'y a pas d'identifiant de Skill créé
		// au moment de la création d'un nouvel enregistrement,
		// alors la méthode (DBRequestsConstants.ADD_SKILL, skill) renvoie null.
		// Par soucis de compatibilité avec les autres méthodes add(), on force
		// un return 1.
		this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_SKILL, skill);
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
	public List<Skill> getAllCollaboratorSkill(int colleagueId) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("skill.getAllCollaboratorSkill", colleagueId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill getCollaboratorSkillByTool(int collaboratorId, int toolId) throws DataAccessException {
		Map<String, Integer> mapId = new HashMap<String, Integer>();
		mapId.put("collaboratorId", collaboratorId);
		mapId.put("toolId", toolId);
		return (Skill) this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_SKILL, mapId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Tool getToolByName(final String name) throws DataAccessException {
		return toolDao.getByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Skill getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Select all Collaborators Id By toolId.
	 * 
	 * @param toolId
	 *            : a toolId
	 * @return all collaborator's id who has a competence on the tool specified
	 * @author v.guillemain
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllCollaboratorsIdByToolId(int toolId) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(	DBRequestsConstants.GET_ALL_COLLABORATORS_BY_TOOL, toolId);
	}

	/**
	 * Select all collaborator's Id by a toolId and a list of collaboratorId.
	 * 
	 * @author v.guillemain
	 * @class ISkillDao.java
	 * @param toolId
	 *            : a toolId
	 * @param listCollaboratorId
	 *            of collaborator's Id
	 * @return all collaborator's id who has the competence (toolId) and who is
	 *         in the list of collaborator's id
	 * @throws Exception
	 */
	// TODO : virer les sysout
	@SuppressWarnings("unchecked")
	public List<Integer> getAllCollaboratorIdByToolIdAndCollabList(int toolId, List<Integer> listCollaboratorId) throws DataAccessException {
		System.out.println("SDao +++++ 1 +++++  : entrée");
		System.out.println("SDao +++++ 1.1 +++++  : toolId=" + toolId);
		System.out.println("SDao +++++ 1.2 +++++  : listCollaboratorId= "
				+ listCollaboratorId);

		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setToolId(toolId);
		skillParameter.setListCollaborators(listCollaboratorId);

		List<Integer> listResult = this.getSqlMapClientTemplate().queryForList(
				DBRequestsConstants.GET_ALL_COLLABORATORID_BY_TOOL,
				skillParameter);

		for (Integer id : listResult) {
			System.out.println("SDao +++++ 2 +++++ id = " + id);
		}

		return listResult;
	}

	/**
	 * Set the tool DAO
	 * 
	 * @param toolDao to set
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
//	@SuppressWarnings("unchecked")
//	public List<Skill> getAllSkillsByCollaborator(final Colleague colleague) {
//		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_SKILLS_BY_COLLABORATOR, colleague);
//	}
}