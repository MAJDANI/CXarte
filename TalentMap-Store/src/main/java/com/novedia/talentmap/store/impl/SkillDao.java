package com.novedia.talentmap.store.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.model.entity.Tool;
import com.novedia.talentmap.store.IDao;
import com.novedia.talentmap.store.ISkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class SkillDao extends SqlMapClientDaoSupport implements ISkillDao, IDao<Skill> {
	
	/**
	 * Tool DAO
	 */
	private IDao<Tool> toolDao;
	
	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public SkillDao (final SqlMapClient sqlMapClient){
		setSqlMapClient(sqlMapClient);
	}
		
	@Override
	public Skill get(Integer id) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Skill> getAll() throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public int save(Skill skill) throws DataAccessException {
		return this.getSqlMapClientTemplate().update(DBRequestsConstants.SAVE_SKILL, skill);
	}

	@Override
	public int add(Skill skill) throws DataAccessException {
		return (Integer) this.getSqlMapClientTemplate().insert(DBRequestsConstants.ADD_SKILL, skill);
	}

	@Override
	public int delete(Skill element) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Skill check(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> getAllCollaboratorSkill(int collaboratorId) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("skill.getAllCollaboratorSkill", collaboratorId);
	}

	@Override
	public Skill getOneCollaboratorSkill(int collaborator_id, int tool_id) throws DataAccessException {
		Map<String, Integer> mapId =new HashMap<String, Integer>();
		mapId.put("collaboratorId", collaborator_id);
		mapId.put("toolId", tool_id);
		
		return (Skill)this.getSqlMapClientTemplate().queryForObject("skill.getSkill", mapId);
	}

	/**
	 * Select all Collaborators Id By toolId
	 * @param toolId : a toolId
	 * @return all collaborator's id who has a competence on the tool specified
	 * @author v.guillemain
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllCollaboratorsIdByToolId(int toolId) throws DataAccessException {
		System.out.println("SDao ----- 1 -----  : entrée");
		System.out.println("SDao ----- 2 -----  : toolId="+toolId);
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATORS_BY_TOOL,toolId);
		
	}

	/**
	 * Select all collaborator's Id by a toolId and a list of collaboratorId.
	 * @author v.guillemain
	 * @class ISkillDao.java
	 * @param toolId : a toolId
	 * @param listCollabId : a list of collaborator's Id
	 * @return all collaborator's id who has the competence (toolId) and who is in the list of collaborator's id
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAllCollaboratorIdByToolIdAndCollabList(int toolId, List<Integer> listCollaboratorId) throws DataAccessException {
		System.out.println("SDao +++++ 1 +++++  : entrée");
		System.out.println("SDao +++++ 1.1 +++++  : toolId="+toolId);
		System.out.println("SDao +++++ 1.2 +++++  : listCollaboratorId="+listCollaboratorId);
		
		//************************************************************
		
		//************************************************************
		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setToolId(toolId);
		skillParameter.setListCollaborators(listCollaboratorId);
		
		List<Integer> listResult = this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATORID_BY_TOOL, skillParameter);
		for(Integer id : listResult) {
			System.out.println("SDao +++++ 2 +++++ id = " + id);
		}
		return listResult;		
	}

	@Override
	public Tool getToolByName(String name) throws DataAccessException {
		return toolDao.getByName(name);
	}

	public void setToolDao(IDao<Tool> toolDao) {
		this.toolDao = toolDao;
	}

	@Override
	public Skill getByName(String name) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Get alls skills by {@link Collaborator}
	 * @param collab
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Skill> getAllSkillsByCollaborator(Collaborator collab) {
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_SKILLS_BY_COLLABORATOR, collab);
	}	
	
	
}