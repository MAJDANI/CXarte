package com.novedia.talentmap.store.impl;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
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
	 * Select all Collaborator's Skill By Collaborator_ID
	 */
	@Override
	public List<Skill> getAllCollaboratorSkill(int collaborator_id)
			throws Exception {
		
		return sqlMapClient.queryForList("skill.getAllCollaboratorSkill",collaborator_id);
	}

	/**
	 * Get One Collaborator's Skill By Collaborator_ID and Tool_ID
	 */
	@Override
	public Skill getOneCollaboratorSkill(int collaborator_id, int tool_id)
			throws Exception {
		
		return (Skill)sqlMapClient.queryForObject("skill.getSkill", collaborator_id, tool_id);
	}

	/**
	 * Add One Skill
	 */
	@Override
	public void addOneSkill(Skill skill) throws Exception {
		
		this.sqlMapClient.insert("skill.insertSkill", skill);
	}

}
