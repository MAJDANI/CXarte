package com.novedia.talentmap.store.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.novedia.talentmap.model.entity.Collaborator;
import com.novedia.talentmap.model.entity.Skill;
import com.novedia.talentmap.store.ISkillDao;

/**
 * 
 * @author j.collet
 * @project TalentMap-Store
 * @package com.novedia.talentmap.store.impl
 * @created 21 mai 2012
 */
public class SkillDao implements ISkillDao {
	
	private SqlMapClient sqlMapClient;

	/**
	 * Set the sqlMapClient value
	 * @param sqlMapClient the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
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
	public List<Skill> getAllCollaboratorSkill(int collaborator_id) {
		
		try {
			
			return sqlMapClient.queryForList("skill.getAllCollaboratorSkill",collaborator_id);
//			return buildDummySkills(collaborator_id);
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			System.err.println("Database Down !");
			
			return buildDummySkills(collaborator_id);
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
		
		return (Skill)sqlMapClient.queryForObject("skill.getSkill", mapId);
	}

	/**
	 * Add One Skill
	 */
	@Override
	public void addOneSkill(Skill skill) throws Exception {
		
		this.sqlMapClient.startTransaction();
		
		this.sqlMapClient.insert("skill.insertSkill", skill);
		this.sqlMapClient.commitTransaction();
		
		this.sqlMapClient.endTransaction();
	}
	
	/**
	 * Update One Skill
	 */
	@Override
	public void updateOneSkill(Skill skill) throws Exception{
		
		this.sqlMapClient.update("skill.updateSkill", skill);
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
		return (List<Integer>) sqlMapClient.queryForList("skill.getAllCollaboratorsIdByToolId",toolId);
		
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

		List<Integer> listResult = sqlMapClient.queryForList("skill.getAllCollaboratorIdByToolIdAndCollabList",skillParameter);
		for(Integer id : listResult) {
			System.out.println("SDao +++++ 2 +++++ id = " + id);
		}
		return listResult;		
	}



}
