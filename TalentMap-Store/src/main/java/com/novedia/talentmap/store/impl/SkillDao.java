package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.store.ISkillDao;
import com.novedia.talentmap.store.utils.DBRequestsConstants;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class SkillDao extends SqlMapClientDaoSupport implements ISkillDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void  SkillDao (SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * Builder of a list of skills if the database is down
	 * @class SkillDao.java
	 * @param id
	 * @return
	 */
	private List<Skill> buildDummySkills(int id){
		
		List<Skill> lSkill = new ArrayList<Skill>();
		
		for(int i = 1; i <= 3; i++){
			Skill skill = new Skill();
			Random rd = new Random();
			
			skill.setCollaborator_id(id);
			skill.setTool_id(i);
			skill.setScore(rd.nextInt(5)+1);
			skill.setNo_using_time(rd.nextInt(4)+1);
			skill.setUse_frequency(rd.nextInt(3)+1);
			
			lSkill.add(skill);
		}
		
		return lSkill;
	}
	
	/**
	 * Select all Collaborator's Skill By Collaborator_ID
	 */
	@Override
	public List<Skill> getAllCollaboratorSkill(int collaborator_id) throws SQLException {
		
		try {
			
			return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATOR_SKILL,collaborator_id);
		} catch (NullPointerException npe){
			
			npe.printStackTrace();
			
			return buildDummySkills(collaborator_id);
		}
		
	}

	/**
	 * Get One Collaborator's Skill By Collaborator_ID and Tool_ID
	 */
	@Override
	public Skill getOneCollaboratorSkill(int collaborator_id, int tool_id)
			throws Exception {
		
		Map<String, Integer> mapId =new HashMap<String, Integer>();
		mapId.put("collaboratorId", collaborator_id);
		mapId.put("toolId", tool_id);
		
		return (Skill)this.getSqlMapClientTemplate().queryForObject(DBRequestsConstants.GET_ALL_SKILL, mapId);
	}

	/**
	 * Add One Skill
	 */
	@Override
	public void addOneSkill(Skill skill) throws Exception {
		
		this.sqlMapClient.startTransaction();

		this.getSqlMapClientTemplate().insert(DBRequestsConstants.INSERT_SKILL_REQUEST,skill);
		
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
	}
	
	/**
	 * Update One Skill
	 */
	@Override
	public void updateOneSkill(Skill skill) throws Exception{
		
		this.sqlMapClient.startTransaction();
		this.getSqlMapClientTemplate().update(DBRequestsConstants.UPDATE_SKILL_REQUEST,skill);
		this.sqlMapClient.commitTransaction();
		this.sqlMapClient.endTransaction();
	}

	/**
	 * Select all Collaborators Id By toolId
	 * @param toolId : a toolId
	 * @return all collaborator's id who has a competence on the tool specified
	 * @author v.guillemain
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllCollaboratorsIdByToolId(int toolId) throws Exception {
		System.out.println("SDao ----- 1 -----  : entrée");
		System.out.println("SDao ----- 2 -----  : toolId="+toolId);
		return this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATORS_BY_TOOLIB_REQUEST,toolId);
		
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
	@Override
	public List<Integer> getAllCollaboratorIdByToolIdAndCollabList(int toolId, List<Integer> listCollaboratorId) throws Exception {
		System.out.println("SDao +++++ 1 +++++  : entrée");
		System.out.println("SDao +++++ 1.1 +++++  : toolId="+toolId);
		System.out.println("SDao +++++ 1.2 +++++  : listCollaboratorId="+listCollaboratorId);
		
		//************************************************************
		
		//************************************************************
		SkillParameter skillParameter = new SkillParameter();
		skillParameter.setToolId(toolId);
		skillParameter.setListCollaborators(listCollaboratorId);
		
		List<Integer> listResult = this.getSqlMapClientTemplate().queryForList(DBRequestsConstants.GET_ALL_COLLABORATORID_BY_TOOL_REQUEST,skillParameter);
		for(Integer id : listResult) {
			System.out.println("SDao +++++ 2 +++++ id = " + id);
		}
		return listResult;		
	}
}